package net.mcbencrafter.quicksnip.cache;

import net.mcbencrafter.quicksnip.cache.type.CachedCategory;
import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;

import java.awt.*;
import java.util.List;

public interface InformationFetcher {

    List<CachedLanguage> fetchLanguages();
    Image fetchLanguageIcon(String name);

    List<CachedCategory> fetchCategoriesByLanguage(String name);
}
