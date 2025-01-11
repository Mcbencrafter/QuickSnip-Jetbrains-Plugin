package net.mcbencrafter.quicksnip.unit;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class ListSelectedHelper {

    public static <T> void registerSelectionListeners(JList<T> list, Consumer<T> callback) {
        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() != 2)
                    return;

                callback.accept(list.getSelectedValue());
            }
        });

        list.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() != KeyEvent.VK_ENTER)
                    return;

                callback.accept(list.getSelectedValue());
            }
        });
    }
}
