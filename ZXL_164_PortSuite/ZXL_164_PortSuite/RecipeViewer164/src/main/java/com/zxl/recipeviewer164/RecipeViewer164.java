package com.zxl.recipeviewer164;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.FMLLog;
@Mod(modid="zxl_recipeviewer164", name="ZXL Recipe Viewer 1.6.4", version="0.1.0-alpha")
public class RecipeViewer164 {
 @EventHandler public void init(FMLInitializationEvent event) { FMLLog.info("[ZXL Recipe Viewer] Loaded core; recipe indexing and GUI integration are next milestones."); }
}
