package net.mcbencrafter.quicksnip.snippetbrowser.snippet;

import com.intellij.ide.startup.importSettings.chooser.ui.RoundedBorder;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        codeEditor.setFont(new Font("Courier New", Font.PLAIN, 14));

        // TODO: add syntax highlighting

        JScrollPane scrollPane = new JBScrollPane(codeEditor.getComponent());
        snippetWindow.add(scrollPane, BorderLayout.CENTER);

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));

        informationPanel.add(
                createDescriptionPanel(snippet.description())
        );

        informationPanel.add(
                createAuthorPanel(snippet.author())
        );

        informationPanel.add(createTagPanel(snippet.tags()));

        snippetWindow.add(informationPanel, BorderLayout.SOUTH);
    }

    public JPanel createDescriptionPanel(String description) {
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JBLabel descriptionLabel = new JBLabel("Description:");
        descriptionLabel.setFont(new Font("Source Sans 3", Font.BOLD, 15));

        JBLabel descriptionText = new JBLabel(description);
        descriptionText.setFont(new Font("Source Sans 3", Font.PLAIN, 15));

        descriptionLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        descriptionText.setAlignmentY(Component.TOP_ALIGNMENT);

        // TODO: description is not shown when it's too long

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionText);

        return descriptionPanel;
    }

    public JPanel createAuthorPanel(String author) {
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        Font font = new Font("Source Sans 3", Font.BOLD, 15);

        JBLabel authorLabel = new JBLabel("Created by");
        authorLabel.setFont(font);

        JBLabel authorText = new JBLabel("<html><a href=''>" + author + "</a></html>");
        authorText.setFont(font);

        authorText.setCursor(new Cursor(Cursor.HAND_CURSOR));

        authorText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/" + author));
                } catch (IOException | URISyntaxException ignored) {
                }
            }
        });

        authorPanel.add(authorLabel);
        authorPanel.add(authorText);

        return authorPanel;
    }

    public JPanel createTagPanel(List<String> tags) {
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (String tag : tags) {
            JPanel tagBox = new JPanel();

            tagBox.setBackground(new JBColor(Gray._230, Gray._50));

            tagBox.setBorder(BorderFactory.createLineBorder(JBColor.GRAY, 1));
            tagBox.setLayout(new BorderLayout());

            JBLabel tagLabel = new JBLabel(tag);
            tagLabel.setFont(new Font("Source Sans 3", Font.PLAIN, 14));
            tagLabel.setForeground(JBColor.DARK_GRAY);
            tagLabel.setHorizontalAlignment(SwingConstants.CENTER);

            tagLabel.setBorder(JBUI.Borders.empty(2, 5));

            tagBox.add(tagLabel, BorderLayout.CENTER);
            tagPanel.add(tagBox);
        }

        return tagPanel;
    }

    public void show() {
        snippetWindow.setVisible(true);
    }
} 