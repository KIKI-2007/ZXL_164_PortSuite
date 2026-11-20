package com.zxl.tooltip164;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.FMLLog;
@Mod(modid="zxl_tooltip164", name="ZXL Tooltip 1.6.4", version="0.1.0-alpha")
public class Tooltip164 {
 @EventHandler public void init(FMLInitializationEvent event) { FMLLog.info("[ZXL Tooltip] Loaded core; tooltip renderer integration is the next milestone."); }
}
