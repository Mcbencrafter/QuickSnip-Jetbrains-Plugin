package net.mcbencrafter.quicksnip;

import net.mcbencrafter.quicksnip.cache.InformationFetcher;
import net.mcbencrafter.quicksnip.cache.LanguageStoreService;
import net.mcbencrafter.quicksnip.cache.DefaultInformationFetcher;
import net.mcbencrafter.quicksnip.snippetbrowser.language.LanguageListToolWindow;

public class QuickSnipContainer {

    private final LanguageStoreService languageStoreService;
    private final InformationFetcher informationFetcher;

    private final LanguageListToolWindow languageListToolWindow;

    public QuickSnipContainer(LanguageListToolWindow languageListToolWindow) {
        this.languageListToolWindow = languageListToolWindow;
        this.languageStoreService = new LanguageStoreService();
        this.informationFetcher = new DefaultInformationFetcher(this);

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

    public LanguageListToolWindow getLanguageListToolWindow() {
        return languageListToolWindow;
    }
}
