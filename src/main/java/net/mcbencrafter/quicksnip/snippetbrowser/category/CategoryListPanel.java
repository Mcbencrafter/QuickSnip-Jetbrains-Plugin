package net.mcbencrafter.quicksnip.snippetbrowser.category;

import com.intellij.ui.components.JBList;
import net.mcbencrafter.quicksnip.QuickSnipContainer;
import net.mcbencrafter.quicksnip.cache.type.CachedCategory;
import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;
import net.mcbencrafter.quicksnip.snippetbrowser.snippet.SnippetListPanel;
import net.mcbencrafter.quicksnip.unit.ListSelectedHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryListPanel extends JPanel {

    private final QuickSnipContainer quickSnipContainer;
    private final JBList<CachedCategory> categoryList;

    private final JPanel topPanel;

    private final GridBagConstraints gridBagConstraints;

    public CategoryListPanel(CachedLanguage language, QuickSnipContainer quickSnipContainer) {
        this.quickSnipContainer = quickSnipContainer;
        
        this.setLayout(new BorderLayout());

        JLabel languageIconLabel = new JLabel(new ImageIcon(language.getIcon()));
        JLabel languageNameLabel = new JLabel(language.getName());
        languageNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        this.topPanel = new JPanel(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        this.topPanel.add(languageIconLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.topPanel.add(languageNameLabel, gridBagConstraints);

        List<CachedCategory> categories = quickSnipContainer.getLanguageStoreService()
                .getCachedLanguageByName(language.getName())
                .getOrFetchCategories();

        categoryList = new JBList<>(categories);
        categoryList.setCellRenderer(new CategoryListCellRenderer());
        this.add(new JScrollPane(categoryList), BorderLayout.CENTER);
    }

    public void showOnPanel(JPanel panel) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> {
            panel.removeAll();
            quickSnipContainer.getLanguageListToolWindow().addContentsToPanel(panel);

            panel.revalidate();
            panel.repaint();
        });

        gridBagConstraints.gridy = 2;

        topPanel.add(backButton, gridBagConstraints);
        this.add(this.topPanel, BorderLayout.NORTH);

        panel.removeAll();
        panel.add(this, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();

        ListSelectedHelper.registerSelectionListeners(categoryList, (category) -> {
            if (category == null)
                return;

            SnippetListPanel snippetListPanel = new SnippetListPanel(quickSnipContainer, category);
            snippetListPanel.showOnPanel(panel);
        });
    }
}