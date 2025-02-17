package net.mcbencrafter.quicksnip.cache;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.intellij.util.SVGLoader;
import com.intellij.util.ui.JBUI;
import net.mcbencrafter.quicksnip.QuickSnipContainer;
import net.mcbencrafter.quicksnip.cache.type.CachedCategory;
import net.mcbencrafter.quicksnip.cache.type.CachedLanguage;
import net.mcbencrafter.quicksnip.cache.type.CachedSnippet;
import net.mcbencrafter.quicksnip.cache.type.LanguageResponse;
import net.mcbencrafter.quicksnip.constants.QuickSnipConstants;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DefaultInformationFetcher implements InformationFetcher {

    private final QuickSnipContainer quickSnipContainer;

    public DefaultInformationFetcher(QuickSnipContainer quickSnipContainer) {
        this.quickSnipContainer = quickSnipContainer;
    }

    @Override
    public List<CachedLanguage> fetchLanguages() {
        List<CachedLanguage> languages = new ArrayList<>();
        Gson gson = new Gson();

        try {
            URL url = new URL(QuickSnipConstants.BASE_LANGUAGE_URL + "/_index.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            List<LanguageResponse> languageResponses = gson.fromJson(
                    response.toString(),
                    new TypeToken<List<LanguageResponse>>() {
                    }.getType()
            );

            for (LanguageResponse languageResponse : languageResponses) {
                CachedLanguage cachedLanguage = new CachedLanguage(
                        quickSnipContainer,
                        languageResponse.getLanguage(),
                        this.fetchLanguageIcon(languageResponse.getIconPath())
                );

                languages.add(cachedLanguage);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return languages;
    }

    @Override
    public Image fetchLanguageIcon(String name) {
        try {
            URL iconUrl = new URL(QuickSnipConstants.BASE_URL + name);
            return SVGLoader.load(iconUrl, JBUI.scale(1));
        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public List<CachedCategory> fetchCategoriesByLanguage(String name) {
        List<CachedCategory> categories = new ArrayList<>();
        Gson gson = new Gson();

        try {
            URL url = new URL(QuickSnipConstants.BASE_LANGUAGE_URL + "/" + name + ".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonArray categoryArray = gson.fromJson(
                    response.toString(),
                    JsonArray.class
            );

            for (int i = 0; i < categoryArray.size(); i++) {
                JsonObject categoryObject = categoryArray.get(i).getAsJsonObject();
                String categoryName = categoryObject.get("name").getAsString();
                JsonArray snippetsArray = categoryObject.getAsJsonArray("snippets");

                List<CachedSnippet> snippets = new ArrayList<>();
                for (int j = 0; j < snippetsArray.size(); j++) {
                    JsonObject snippetObject = snippetsArray.get(j).getAsJsonObject();
                    String title = snippetObject.get("title").getAsString();
                    String description = snippetObject.get("description").getAsString();
                    String author = snippetObject.get("author").getAsString();
                    String code = snippetObject.get("code").getAsString();

                    JsonArray tagsArray = snippetObject.get("tags").getAsJsonArray();
                    List<String> tags = new ArrayList<>();

                    tagsArray.forEach(tag -> tags.add(tag.getAsString()));

                    CachedSnippet cachedSnippet = new CachedSnippet(title, description, author, code, tags);
                    snippets.add(cachedSnippet);
                }

                CachedCategory cachedCategory = new CachedCategory(name, categoryName, snippets);
                categories.add(cachedCategory);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return categories;
    }
}
