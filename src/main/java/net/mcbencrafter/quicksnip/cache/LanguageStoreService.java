package net.mcbencrafter.quicksnip.cache;

import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;

import java.util.ArrayList;
import java.util.List;

public class LanguageStoreService {

    private final List<CachedLanguage> cachedLanguages;

    public LanguageStoreService() {
        this.cachedLanguages = new ArrayList<>();
    }

    public CachedLanguage getCachedLanguageByName(String name) {
        return cachedLanguages.stream()
                .filter(language -> language.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void addCachedLanguages(CachedLanguage[] cachedLanguages) {
        addCachedLanguages(List.of(cachedLanguages));
    }

    public void addCachedLanguages(List<CachedLanguage> cachedLanguages) {
        this.cachedLanguages.addAll(cachedLanguages);
    }

    public void addCachedLanguage(CachedLanguage cachedLanguage) {
        this.cachedLanguages.add(cachedLanguage);
    }

    public List<CachedLanguage> getCachedLanguages() {
        return cachedLanguages;
    }
}
