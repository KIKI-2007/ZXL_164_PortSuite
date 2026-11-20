package com.zxl.recipeviewer164.core;
import java.util.Arrays; import org.junit.Test; import static org.junit.Assert.*;
public class RecipeIndexTest { @Test public void indexesOutputs() { RecipeIndex i=new RecipeIndex(); i.add(new RecipeEntry("minecraft:torch",Arrays.asList("minecraft:stick","minecraft:coal"),"crafting")); assertEquals(1,i.byOutput("minecraft:torch").size()); } }
