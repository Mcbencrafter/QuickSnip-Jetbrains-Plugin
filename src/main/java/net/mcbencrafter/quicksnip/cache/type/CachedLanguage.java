package net.mcbencrafter.quicksnip.cache.type;

import net.mcbencrafter.quicksnip.QuickSnipContainer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CachedLanguage {

    private final QuickSnipContainer quickSnipContainer;

    private final String name;
    private final Image icon;

    private final List<CachedCategory> cachedCategories;

    public CachedLanguage(QuickSnipContainer quickSnipContainer, String name, Image icon) {
        this.quickSnipContainer = quickSnipContainer;
        this.name = name;
        this.icon = icon;
        this.cachedCategories = new ArrayList<>();
    }

    public Image getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void addCategory(CachedCategory cachedCategory) {
        cachedCategories.add(cachedCategory);
    }

    public void addCategories(List<CachedCategory> categories) {
        cachedCategories.addAll(categories);
    }

    public List<CachedCategory> getOrFetchCategories() {
        if (!cachedCategories.isEmpty())
            return this.getCachedCategories();

        this.addCategories(
                quickSnipContainer.getInformationFetcher().fetchCategoriesByLanguage(name)
        );

        return this.getCachedCategories();
    }

    public List<CachedCategory> getCachedCategories() {
        return Collections.unmodifiableList(cachedCategories);
    }
}
