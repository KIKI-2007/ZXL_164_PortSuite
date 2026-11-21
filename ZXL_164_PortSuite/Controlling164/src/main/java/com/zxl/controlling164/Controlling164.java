package com.zxl.controlling164;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "zxl_controlling164", name = "ZXL Controlling 1.6.4", version = "0.2.0-alpha")
public class Controlling164 {
    @SidedProxy(
        clientSide = "com.zxl.controlling164.client.ClientProxy",
        serverSide = "com.zxl.controlling164.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
