import com.zxl.controlling164.core.KeyBindingIndex;
import com.zxl.apple164.core.FoodPreview;
import com.zxl.recipeviewer164.core.RecipeEntry;
import com.zxl.recipeviewer164.core.RecipeIndex;
import com.zxl.tooltip164.core.TooltipLayout;
import java.util.Arrays;
public class CoreSmoke {
 public static void main(String[] args) {
  KeyBindingIndex k=new KeyBindingIndex(); k.add(new KeyBindingIndex.Entry("Open Map",42)); k.add(new KeyBindingIndex.Entry("Open Menu",42));
  if(k.search("map").size()!=1 || k.conflicts().size()!=2) throw new AssertionError("key index");
  FoodPreview f=new FoodPreview(19,19,6,0.8F); if(f.hungerAfter!=20 || f.saturationAfter>20) throw new AssertionError("food preview");
  RecipeIndex r=new RecipeIndex(); r.add(new RecipeEntry("minecraft:torch",Arrays.asList("minecraft:stick","minecraft:coal"),"crafting")); if(r.byOutput("minecraft:torch").size()!=1) throw new AssertionError("recipe index");
  if(TooltipLayout.wrap("one two three",7).size()!=2) throw new AssertionError("tooltip wrap");
  System.out.println("PASS: Controlling, Apple, RecipeViewer and Tooltip core smoke tests");
 }
}
