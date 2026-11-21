package com.zxl.recipeviewer164.client;

import com.zxl.recipeviewer164.CommonProxy;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        KeyBindingRegistry.registerKeyBinding(new RecipeViewerKeyHandler());
    }
}
