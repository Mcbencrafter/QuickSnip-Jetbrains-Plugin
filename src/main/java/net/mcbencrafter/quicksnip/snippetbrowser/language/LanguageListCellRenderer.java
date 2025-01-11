package net.mcbencrafter.quicksnip.snippetbrowser.language;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;

import javax.swing.*;
import java.awt.*;

public class LanguageListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        CachedLanguage language = (CachedLanguage) value;
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list,
                language.getName(),
                index,
                isSelected,
                cellHasFocus
        );

        label.setIcon(
                new ImageIcon(language.getIcon())
        );

        label.setIconTextGap(15);

        label.setBorder(
                JBUI.Borders.empty(5)
        );

        label.setOpaque(false);
        label.setBackground(isSelected ? JBColor.DARK_GRAY : JBColor.LIGHT_GRAY);

        label.setFont(new Font("Arial", Font.BOLD, 30));

        return label;
    }
}