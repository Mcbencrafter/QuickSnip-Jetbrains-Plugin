package net.mcbencrafter.quicksnip.cache.type;

import java.util.List;

public record CachedSnippet(String name, String description, String author, String code, List<String> tags) {
}
