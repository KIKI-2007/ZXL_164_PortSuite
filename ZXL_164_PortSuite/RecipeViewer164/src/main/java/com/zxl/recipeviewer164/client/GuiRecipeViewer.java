package com.zxl.recipeviewer164.client;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

/**
 * Read-only crafting recipe browser. It reads registered recipe outputs and
 * never mutates the crafting registry or consumes items.
 */
public class GuiRecipeViewer extends GuiScreen {
    private GuiTextField search;
    private final List<ItemStack> outputs = new ArrayList<ItemStack>();
    private List<ItemStack> visible = new ArrayList<ItemStack>();
    private int page;
    private int rowsPerPage = 12;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        rowsPerPage = Math.max(1, Math.min(16, (height - 112) / 14));
        search = new GuiTextField(fontRenderer, width / 2 - 110, 34, 220, 18);
        search.setMaxStringLength(64);
        search.setFocused(true);
        refreshRecipes();
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 90, height - 30, 55, 20, "Previous"));
        buttonList.add(new GuiButton(1, width / 2 - 27, height - 30, 55, 20, "Next"));
        buttonList.add(new GuiButton(2, width / 2 + 36, height - 30, 55, 20, "Done"));
        updateButtons();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        search.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            close();
            return;
        }
        if (search.textboxKeyTyped(typedChar, keyCode)) {
            page = 0;
            filterRecipes();
            updateButtons();
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        search.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (!button.enabled) return;
        if (button.id == 0 && page > 0) --page;
        else if (button.id == 1 && (page + 1) * rowsPerPage < visible.size()) ++page;
        else if (button.id == 2) {
            close();
            return;
        }
        updateButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Recipe Viewer", width / 2, 12, 0xFFFFFF);
        drawString(fontRenderer, "Search output:", width / 2 - 110, 23, 0xA0A0A0);
        search.drawTextBox();

        int start = page * rowsPerPage;
        int end = Math.min(visible.size(), start + rowsPerPage);
        if (start >= end) {
            drawCenteredString(fontRenderer, "No matching crafting outputs", width / 2, 92, 0xFFFFFF);
        } else {
            for (int i = start; i < end; ++i) {
                ItemStack stack = visible.get(i);
                String line = stack.getDisplayName() + (stack.stackSize > 1 ? " x" + stack.stackSize : "");
                drawString(fontRenderer, fontRenderer.trimStringToWidth(line, 215),
                    width / 2 - 105, 72 + (i - start) * 14, 0xFFFFFF);
            }
        }
        int pages = Math.max(1, (visible.size() + rowsPerPage - 1) / rowsPerPage);
        drawCenteredString(fontRenderer, "Page " + (page + 1) + " / " + pages
            + "   (" + visible.size() + " results; " + outputs.size() + " registered recipes)",
            width / 2, height - 43, 0xA0A0A0);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void refreshRecipes() {
        outputs.clear();
        List recipes = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipes.size(); ++i) {
            Object value = recipes.get(i);
            if (!(value instanceof IRecipe)) continue;
            ItemStack output = ((IRecipe)value).getRecipeOutput();
            if (output != null) outputs.add(output.copy());
        }
        filterRecipes();
    }

    private void filterRecipes() {
        String query = search.getText() == null ? "" : search.getText().toLowerCase();
        visible = new ArrayList<ItemStack>();
        for (int i = 0; i < outputs.size(); ++i) {
            ItemStack stack = outputs.get(i);
            String display = stack.getDisplayName().toLowerCase();
            String internal = stack.getUnlocalizedName().toLowerCase();
            if (query.length() == 0 || display.contains(query) || internal.contains(query)) {
                visible.add(stack);
            }
        }
        int maxPage = Math.max(0, (visible.size() - 1) / rowsPerPage);
        if (page > maxPage) page = maxPage;
    }

    private void updateButtons() {
        if (buttonList.size() >= 3) {
            ((GuiButton)buttonList.get(0)).enabled = page > 0;
            ((GuiButton)buttonList.get(1)).enabled = (page + 1) * rowsPerPage < visible.size();
        }
    }

    private void close() {
        mc.displayGuiScreen(null);
        mc.setIngameFocus();
    }
}
