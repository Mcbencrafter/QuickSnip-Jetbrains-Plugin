package net.mcbencrafter.quicksnip.cache.type;

public class LanguageResponse {

    String lang;
    String icon;

    public LanguageResponse(String lang, String icon) {
        this.lang = lang;
        this.icon = icon;
    }

    public String getIconPath() {
        return icon;
    }

    public String getLanguage() {
        return lang;
    }
}
