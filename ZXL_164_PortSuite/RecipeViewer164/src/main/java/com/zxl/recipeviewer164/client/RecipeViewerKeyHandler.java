package com.zxl.recipeviewer164.client;

import java.util.EnumSet;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class RecipeViewerKeyHandler extends KeyHandler {
    private static final KeyBinding OPEN =
        new KeyBinding("key.zxl_recipeviewer164.open", Keyboard.KEY_R);

    public RecipeViewerKeyHandler() {
        super(new KeyBinding[] { OPEN }, new boolean[] { false });
    }

    @Override
    public String getLabel() {
        return "ZXL Recipe Viewer";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding binding,
                        boolean tickEnd, boolean isRepeat) {
        if (tickEnd && binding == OPEN) {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            if (minecraft != null && minecraft.currentScreen == null) {
                minecraft.displayGuiScreen(new GuiRecipeViewer());
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
