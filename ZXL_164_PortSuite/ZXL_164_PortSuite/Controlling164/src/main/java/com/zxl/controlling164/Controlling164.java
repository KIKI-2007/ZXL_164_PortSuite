package com.zxl.controlling164;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.FMLLog;

@Mod(modid = "zxl_controlling164", name = "ZXL Controlling 1.6.4", version = "0.1.0-alpha")
public class Controlling164 {
    @EventHandler public void init(FMLInitializationEvent event) {
        FMLLog.info("[ZXL Controlling] Loaded core; GUI integration is the next milestone.");
    }
}
