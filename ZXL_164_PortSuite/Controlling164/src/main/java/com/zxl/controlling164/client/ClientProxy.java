package com.zxl.controlling164.client;

import com.zxl.controlling164.CommonProxy;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        // Forge 9.11.1.x uses the legacy KeyBindingRegistry/KeyHandler API.
        KeyBindingRegistry.registerKeyBinding(new BrowserKeyHandler());
    }
}
