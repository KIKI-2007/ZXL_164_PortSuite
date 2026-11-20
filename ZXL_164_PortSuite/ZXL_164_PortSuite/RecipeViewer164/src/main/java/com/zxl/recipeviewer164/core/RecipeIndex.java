package com.zxl.recipeviewer164.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class RecipeIndex {
 private final List<RecipeEntry> recipes = new ArrayList<RecipeEntry>();
 public void add(RecipeEntry recipe) { if (recipe != null) recipes.add(recipe); }
 public List<RecipeEntry> byOutput(String itemId) { List<RecipeEntry> out=new ArrayList<RecipeEntry>(); for(RecipeEntry r:recipes) if(r.outputId.equals(itemId)) out.add(r); return Collections.unmodifiableList(out); }
 public List<RecipeEntry> searchOutput(String text) { String q=text==null?"":text.toLowerCase(); List<RecipeEntry> out=new ArrayList<RecipeEntry>(); for(RecipeEntry r:recipes) if(r.outputId.toLowerCase().contains(q)) out.add(r); return Collections.unmodifiableList(out); }
}
