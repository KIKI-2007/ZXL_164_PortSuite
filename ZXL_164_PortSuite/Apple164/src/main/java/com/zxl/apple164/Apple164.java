package com.zxl.apple164;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.FMLLog;
@Mod(modid="zxl_apple164", name="ZXL Apple HUD 1.6.4", version="0.1.0-alpha")
public class Apple164 {
 @EventHandler public void init(FMLInitializationEvent event) { FMLLog.info("[ZXL Apple HUD] Loaded core; HUD overlay integration is the next milestone."); }
}
