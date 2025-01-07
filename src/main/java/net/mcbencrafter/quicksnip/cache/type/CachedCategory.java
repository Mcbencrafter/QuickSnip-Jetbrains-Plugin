package net.mcbencrafter.quicksnip.cache.type;

import java.util.List;

public record CachedCategory(String name, List<CachedSnippet> cachedSnippets) {

    public void addCachedSnippet(CachedSnippet cachedSnippet) {
        cachedSnippets.add(cachedSnippet);
    }
}
