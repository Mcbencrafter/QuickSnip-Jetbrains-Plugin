package net.mcbencrafter.quicksnip;

import net.mcbencrafter.quicksnip.cache.LanguageStoreService;
import net.mcbencrafter.quicksnip.cache.InformationFetcher;

public class QuickSnipContainer {

    private final LanguageStoreService languageStoreService;
    private final InformationFetcher informationFetcher;

    public QuickSnipContainer() {
        this.languageStoreService = new LanguageStoreService();
        this.informationFetcher = new InformationFetcher(this);

        this.languageStoreService.addCachedLanguages(
            this.informationFetcher.fetchLanguages()
        );
    }

    public InformationFetcher getInformationFetcher() {
        return informationFetcher;
    }

    public LanguageStoreService getLanguageStoreService() {
        return languageStoreService;
    }
}
