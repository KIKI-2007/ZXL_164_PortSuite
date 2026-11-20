package com.zxl.recipeviewer164.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/** Stable recipe representation independent of Forge's version-specific recipe classes. */
public final class RecipeEntry {
    public final String outputId;
    public final List<String> inputIds;
    public final String kind;
    public RecipeEntry(String outputId, List<String> inputIds, String kind) {
        if (outputId == null || outputId.length() == 0) throw new IllegalArgumentException("outputId is required");
        this.outputId = outputId; this.kind = kind == null ? "crafting" : kind;
        this.inputIds = Collections.unmodifiableList(new ArrayList<String>(inputIds == null ? new ArrayList<String>() : inputIds));
    }
}
