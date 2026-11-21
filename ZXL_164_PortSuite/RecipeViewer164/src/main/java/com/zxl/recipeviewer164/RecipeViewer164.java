package com.zxl.recipeviewer164;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "zxl_recipeviewer164", name = "ZXL Recipe Viewer 1.6.4", version = "0.2.0-alpha")
public class RecipeViewer164 {
    @SidedProxy(
        clientSide = "com.zxl.recipeviewer164.client.ClientProxy",
        serverSide = "com.zxl.recipeviewer164.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
