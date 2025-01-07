package net.mcbencrafter.quicksnip.cache.type;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CachedLanguage {

    private final String name;
    private final Image icon;

    private final List<CachedCategory> cachedCategories;

    public CachedLanguage(String name, Image icon) {
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

    public List<CachedCategory> getCachedCategories() {
        return cachedCategories;
    }
}
