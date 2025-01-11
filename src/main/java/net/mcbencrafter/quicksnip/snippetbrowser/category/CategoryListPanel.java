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

    private final JBList<CachedCategory> categoryList;

    public CategoryListPanel(CachedLanguage language, QuickSnipContainer quickSnipContainer) {
        this.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel languageIconLabel = new JLabel(new ImageIcon(language.getIcon()));
        JLabel languageNameLabel = new JLabel(language.getName());
        languageNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        headerPanel.add(languageIconLabel);
        headerPanel.add(languageNameLabel);

        this.add(headerPanel, BorderLayout.NORTH);

        List<CachedCategory> categories = quickSnipContainer.getLanguageStoreService()
                .getCachedLanguageByName(language.getName())
                .getOrFetchCategories();

        categoryList = new JBList<>(categories);
        categoryList.setCellRenderer(new CategoryListCellRenderer());
        this.add(new JScrollPane(categoryList), BorderLayout.CENTER);
    }

    public void showOnPanel(JPanel panel) {
        panel.removeAll();
        panel.add(this, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();

        ListSelectedHelper.registerSelectionListeners(categoryList, (category) -> {
            if (category == null)
                return;

            SnippetListPanel snippetListPanel = new SnippetListPanel(category);
            snippetListPanel.showOnPanel(panel);
        });
    }
}