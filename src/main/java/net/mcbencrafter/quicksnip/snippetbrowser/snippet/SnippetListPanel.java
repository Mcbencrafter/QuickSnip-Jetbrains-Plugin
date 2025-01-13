package net.mcbencrafter.quicksnip.snippetbrowser.snippet;

import com.intellij.ui.components.JBList;
import net.mcbencrafter.quicksnip.QuickSnipContainer;
import net.mcbencrafter.quicksnip.cache.type.CachedCategory;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;
import net.mcbencrafter.quicksnip.snippetbrowser.category.CategoryListPanel;
import net.mcbencrafter.quicksnip.unit.ListSelectedHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SnippetListPanel extends JPanel {

    private final QuickSnipContainer quickSnipContainer;
    private final CachedCategory category;

    private final JBList<CachedSnippet> snippetList;

    private final JPanel topPanel;

    private final GridBagConstraints gridBagConstraints;

    public SnippetListPanel(QuickSnipContainer quickSnipContainer, CachedCategory category) {
        this.quickSnipContainer = quickSnipContainer;
        this.category = category;

        this.setLayout(new BorderLayout());

        JLabel categoryNameLabel = new JLabel(category.name());
        categoryNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        topPanel = new JPanel(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        topPanel.add(categoryNameLabel, gridBagConstraints);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(0, 10));
        this.add(spacer, BorderLayout.NORTH);

        List<CachedSnippet> snippets = category.cachedSnippets();

        snippetList = new JBList<>(snippets);
        snippetList.setCellRenderer(new SnippetListCellRenderer());
        this.add(new JScrollPane(snippetList), BorderLayout.CENTER);
    }

    public void showOnPanel(JPanel panel) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> {
            CategoryListPanel categoryListPanel = new CategoryListPanel(
                    quickSnipContainer.getLanguageStoreService().getCachedLanguageByName(category.language()),
                    quickSnipContainer
            );

            categoryListPanel.showOnPanel(panel);
        });

        gridBagConstraints.gridy = 1;

        topPanel.add(backButton, this.gridBagConstraints);
        this.add(topPanel, BorderLayout.NORTH);

        panel.removeAll();
        panel.add(this, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();

        ListSelectedHelper.registerSelectionListeners(snippetList, (snippet) -> {
            if (snippet == null)
                return;

            SnippetDisplayWindow snippetDisplayWindow = new SnippetDisplayWindow(snippet);
            snippetDisplayWindow.show();
        });
    }
}