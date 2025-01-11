package net.mcbencrafter.quicksnip.snippetbrowser.snippet;

import com.intellij.ui.components.JBList;
import net.mcbencrafter.quicksnip.cache.type.CachedCategory;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;
import net.mcbencrafter.quicksnip.unit.ListSelectedHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SnippetListPanel extends JPanel {

    private final JBList<CachedSnippet> snippetList;

    private final SnippetListCellRenderer listCellRenderer;

    public SnippetListPanel(CachedCategory category) {
        this.setLayout(new BorderLayout());

        JLabel categoryNameLabel = new JLabel(category.name());
        categoryNameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(categoryNameLabel);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(0, 10));
        this.add(spacer, BorderLayout.NORTH);

        List<CachedSnippet> snippets = category.cachedSnippets();

        snippetList = new JBList<>(snippets);
        listCellRenderer = new SnippetListCellRenderer();
        snippetList.setCellRenderer(listCellRenderer);
        this.add(new JScrollPane(snippetList), BorderLayout.CENTER);
    }

    public void showOnPanel(JPanel panel) {
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