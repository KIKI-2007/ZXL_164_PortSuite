package com.zxl.backup164;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.FMLLog;
@Mod(modid="zxl_backup164", name="ZXL World Backup 1.6.4", version="0.1.0-alpha")
public class Backup164 {
 @EventHandler public void init(FMLInitializationEvent event) { FMLLog.info("[ZXL Backup] Loaded core; server-safe backup scheduler is the next milestone."); }
}
