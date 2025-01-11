package net.mcbencrafter.quicksnip.snippetbrowser.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import net.mcbencrafter.quicksnip.QuickSnipContainer;
import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;
import net.mcbencrafter.quicksnip.snippetbrowser.category.CategoryListPanel;
import net.mcbencrafter.quicksnip.unit.ListSelectedHelper;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class LanguageListToolWindow implements ToolWindowFactory {

    private final QuickSnipContainer quickSnipContainer;

    public LanguageListToolWindow() {
        this.quickSnipContainer = new QuickSnipContainer();
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JBList<CachedLanguage> languageList = new JBList<>(
                quickSnipContainer.getLanguageStoreService().getCachedLanguages().stream()
                        .toList()
        );

        languageList.setCellRenderer(new LanguageListCellRenderer());

        mainPanel.add(
                new JScrollPane(languageList),
                BorderLayout.CENTER
        );

        ListSelectedHelper.registerSelectionListeners(languageList, (language) -> {
            if (language == null)
                return;

            CategoryListPanel categoryListPanel = new CategoryListPanel(language, quickSnipContainer);
            categoryListPanel.showOnPanel(mainPanel);
        });

        Content content = toolWindow.getContentManager().getFactory().createContent(mainPanel, "", false);

        toolWindow.getContentManager().addContent(content);
    }

    public QuickSnipContainer getQuickSnipContainer() {
        return quickSnipContainer;
    }
}
