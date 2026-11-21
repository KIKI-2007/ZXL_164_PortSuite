package com.zxl.controlling164.client;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

/**
 * Forge 1.6.4 key callback. This intentionally uses the legacy FML tick
 * registry rather than the post-1.7 event bus API.
 */
@SideOnly(Side.CLIENT)
public class BrowserKeyHandler extends KeyHandler {
    private static final KeyBinding OPEN_BROWSER =
        new KeyBinding("key.zxl_controlling164.open", Keyboard.KEY_P);

    public BrowserKeyHandler() {
        super(new KeyBinding[] { OPEN_BROWSER }, new boolean[] { false });
    }

    @Override
    public String getLabel() {
        return "ZXL Controlling Browser";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding binding, boolean tickEnd, boolean isRepeat) {
        if (tickEnd && binding == OPEN_BROWSER) {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            if (minecraft != null && minecraft.currentScreen == null) {
                minecraft.displayGuiScreen(new GuiKeyBindingBrowser());
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding binding, boolean tickEnd) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }
}
