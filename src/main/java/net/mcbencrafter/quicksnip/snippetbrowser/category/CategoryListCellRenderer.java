package net.mcbencrafter.quicksnip.snippetbrowser.category;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import net.mcbencrafter.quicksnip.cache.type.CachedCategory;

import javax.swing.*;
import java.awt.*;

public class CategoryListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        CachedCategory category = (CachedCategory) value;
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list,
                category.name(),
                index,
                isSelected,
                cellHasFocus
        );

        label.setBorder(
                JBUI.Borders.empty(5)
        );

        label.setOpaque(false);
        label.setBackground(isSelected ? JBColor.DARK_GRAY : JBColor.LIGHT_GRAY);

        label.setFont(new Font("Arial", Font.BOLD, 30));

        return label;
    }
}