package com.zxl.controlling164.client;

import org.lwjgl.input.Keyboard;

import com.zxl.controlling164.CommonProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    private KeyBinding openBrowser;

    @Override
    public void init() {
        openBrowser = new KeyBinding("key.zxl_controlling164.open", Keyboard.KEY_P);
        ClientRegistry.registerKeyBinding(openBrowser);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        while (openBrowser.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiKeyBindingBrowser());
        }
    }
}
