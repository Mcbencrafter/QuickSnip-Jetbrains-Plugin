package net.mcbencrafter.quicksnip.snippetbrowser.snippet;

import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBScrollPane;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;

import javax.swing.*;
import java.awt.*;

public class SnippetDisplayWindow {

    private final JFrame snippetWindow;

    public SnippetDisplayWindow(CachedSnippet snippet) {
        snippetWindow = new JFrame("[QuickSnip] " + snippet.name());
        snippetWindow.setSize(700, 400);
        snippetWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        snippetWindow.setLocationRelativeTo(null);
        snippetWindow.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel(snippet.name());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        snippetWindow.add(titleLabel, BorderLayout.NORTH);

        // Code area
        EditorTextField codeEditor = new EditorTextField();
        codeEditor.setText(snippet.code());
        codeEditor.setOneLineMode(false);
        codeEditor.setViewer(true);

        // TODO: add syntax highlighting

        JScrollPane scrollPane = new JBScrollPane(codeEditor.getComponent());
        snippetWindow.add(scrollPane, BorderLayout.CENTER);

        // Description
        JLabel descriptionLabel = new JLabel(snippet.description());
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        snippetWindow.add(descriptionLabel, BorderLayout.SOUTH);
    }

    public void show() {
        snippetWindow.setVisible(true);
    }
} 