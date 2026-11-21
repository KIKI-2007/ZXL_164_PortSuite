package com.zxl.apple164.client;

import com.zxl.apple164.core.FoodPreview;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * Displays a read-only estimate for the food item currently held by the
 * player. It never consumes the stack or modifies FoodStats.
 */
@SideOnly(Side.CLIENT)
public class FoodPreviewOverlay {
    private final Minecraft minecraft = Minecraft.getMinecraft();

    @ForgeSubscribe
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        EntityPlayer player = minecraft.thePlayer;
        if (player == null || minecraft.currentScreen != null) {
            return;
        }

        ItemStack held = player.getCurrentEquippedItem();
        if (held == null || !(held.getItem() instanceof ItemFood)) {
            return;
        }

        FoodStats stats = player.getFoodStats();
        int currentFood = stats.getFoodLevel();
        // Ordinary food cannot be eaten at full hunger; avoid showing a
        // misleading preview in that state.
        if (currentFood >= 20) {
            return;
        }

        ItemFood food = (ItemFood)held.getItem();
        FoodPreview preview = new FoodPreview(
            currentFood,
            stats.getSaturationLevel(),
            food.getHealAmount(),
            food.getSaturationModifier()
        );

        String foodLine = "Food: " + currentFood + " -> " + preview.hungerAfter
            + " (+" + food.getHealAmount() + ")";
        String saturationLine = "Saturation: " + oneDecimal(stats.getSaturationLevel())
            + " -> " + oneDecimal(preview.saturationAfter);

        ScaledResolution resolution = new ScaledResolution(
            minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight
        );
        int x = 6;
        int y = 6;
        int textWidth = Math.max(
            minecraft.fontRenderer.getStringWidth(foodLine),
            minecraft.fontRenderer.getStringWidth(saturationLine)
        );
        int boxWidth = textWidth + 10;

        Gui.drawRect(x, y, x + boxWidth, y + 35, 0x90000000);
        minecraft.fontRenderer.drawStringWithShadow("Food preview", x + 5, y + 3, 0xFFFFFF);
        minecraft.fontRenderer.drawStringWithShadow(foodLine, x + 5, y + 14, 0x80FF80);
        minecraft.fontRenderer.drawStringWithShadow(saturationLine, x + 5, y + 24, 0x80C0FF);
    }

    private String oneDecimal(float value) {
        return String.valueOf(Math.round(value * 10.0F) / 10.0F);
    }
}
