package net.mcbencrafter.quicksnip.cache.type;

public class LanguageResponse {

    String name;
    String icon;

    public LanguageResponse(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getIconPath() {
        return icon;
    }

    public String getLanguage() {
        return name;
    }
}
