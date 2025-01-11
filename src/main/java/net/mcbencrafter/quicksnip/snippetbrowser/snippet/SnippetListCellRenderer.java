package net.mcbencrafter.quicksnip.snippetbrowser.snippet;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;

import javax.swing.*;
import java.awt.*;

public class SnippetListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        CachedSnippet snippet = (CachedSnippet) value;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JBLabel nameLabel = new JBLabel(snippet.name());
        JBLabel descriptionLabel = new JBLabel(snippet.description());
        JBLabel authorLabel = new JBLabel("Author: " + snippet.author());

        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        authorLabel.setFont(new Font("Arial", Font.ITALIC, 18));

        panel.add(nameLabel);

        panel.add(descriptionLabel);
        panel.add(authorLabel);

        panel.setBorder(JBUI.Borders.empty(5));
        panel.setOpaque(false);
        panel.setBackground(isSelected ? JBColor.DARK_GRAY : JBColor.LIGHT_GRAY);

        return panel;
    }
}