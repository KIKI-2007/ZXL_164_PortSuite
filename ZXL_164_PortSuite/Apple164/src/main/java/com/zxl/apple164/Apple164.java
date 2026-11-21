package com.zxl.apple164;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "zxl_apple164", name = "ZXL Apple HUD 1.6.4", version = "0.2.0-alpha")
public class Apple164 {
    @SidedProxy(
        clientSide = "com.zxl.apple164.client.ClientProxy",
        serverSide = "com.zxl.apple164.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
