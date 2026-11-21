package com.zxl.controlling164.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.zxl.controlling164.core.KeyBindingIndex;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

/**
 * Read-only key binding browser. It deliberately does not alter key mappings;
 * users can still edit them in Minecraft's normal Controls screen.
 */
public class GuiKeyBindingBrowser extends GuiScreen {
    private static final int FILTER_ALL = 0;
    private static final int FILTER_CONFLICTS = 1;
    private static final int FILTER_UNBOUND = 2;

    private GuiTextField searchField;
    private int filter = FILTER_ALL;
    private int page;
    private int rowsPerPage = 10;
    private List<KeyBinding> visible = new ArrayList<KeyBinding>();

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        rowsPerPage = Math.max(1, Math.min(14, (height - 126) / 14));
        searchField = new GuiTextField(fontRenderer, width / 2 - 100, 34, 200, 18);
        searchField.setMaxStringLength(64);
        searchField.setFocused(true);
        rebuildVisible();

        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 100, 58, 62, 20, "All"));
        buttonList.add(new GuiButton(1, width / 2 - 33, 58, 76, 20, "Conflicts"));
        buttonList.add(new GuiButton(2, width / 2 + 47, 58, 53, 20, "Unbound"));
        buttonList.add(new GuiButton(3, width / 2 - 100, height - 30, 55, 20, "Previous"));
        buttonList.add(new GuiButton(4, width / 2 - 27, height - 30, 55, 20, "Next"));
        buttonList.add(new GuiButton(5, width / 2 + 46, height - 30, 54, 20, "Done"));
        updatePageButtons();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        searchField.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
            return;
        }
        if (searchField.textboxKeyTyped(typedChar, keyCode)) {
            page = 0;
            rebuildVisible();
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        searchField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (!button.enabled) {
            return;
        }
        if (button.id == FILTER_ALL || button.id == FILTER_CONFLICTS || button.id == FILTER_UNBOUND) {
            filter = button.id;
            page = 0;
            rebuildVisible();
        } else if (button.id == 3 && page > 0) {
            --page;
        } else if (button.id == 4 && (page + 1) * rowsPerPage < visible.size()) {
            ++page;
        } else if (button.id == 5) {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
            return;
        }
        updatePageButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Key Binding Browser", width / 2, 12, 0xFFFFFF);
        drawString(fontRenderer, "Search:", width / 2 - 100, 23, 0xA0A0A0);
        searchField.drawTextBox();

        String mode = filter == FILTER_CONFLICTS ? "Showing conflicting bindings"
            : filter == FILTER_UNBOUND ? "Showing unbound bindings" : "Showing all bindings";
        drawCenteredString(fontRenderer, mode, width / 2, 84, 0xB0B0B0);

        int start = page * rowsPerPage;
        int end = Math.min(visible.size(), start + rowsPerPage);
        if (start >= end) {
            drawCenteredString(fontRenderer, "No matching key bindings", width / 2, 112, 0xFFFFFF);
        } else {
            for (int i = start; i < end; ++i) {
                KeyBinding binding = visible.get(i);
                int y = 101 + (i - start) * 14;
                int code = binding.keyCode;
                String keyName = code == 0 ? "UNBOUND" : GameSettings.getKeyDisplayString(code);
                boolean conflict = isConflict(binding);
                int color = conflict ? 0xFF7777 : (code == 0 ? 0xAAAAAA : 0xFFFFFF);
                String description = I18n.getString(binding.keyDescription);
                drawString(fontRenderer, trim(description, 185), width / 2 - 100, y, color);
                drawString(fontRenderer, keyName, width / 2 + 62, y, color);
                if (conflict) {
                    drawString(fontRenderer, "!", width / 2 + 92, y, 0xFF5555);
                }
            }
        }

        int pages = Math.max(1, (visible.size() + rowsPerPage - 1) / rowsPerPage);
        drawCenteredString(fontRenderer, "Page " + (page + 1) + " / " + pages
            + "   (" + visible.size() + " bindings)", width / 2, height - 43, 0xA0A0A0);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void rebuildVisible() {
        KeyBindingIndex index = new KeyBindingIndex();
        Map<KeyBindingIndex.Entry, KeyBinding> source = new IdentityHashMap<KeyBindingIndex.Entry, KeyBinding>();
        KeyBinding[] bindings = mc.gameSettings.keyBindings;
        for (int i = 0; i < bindings.length; ++i) {
            KeyBinding binding = bindings[i];
            if (binding != null) {
                KeyBindingIndex.Entry entry = new KeyBindingIndex.Entry(binding.keyDescription, binding.keyCode);
                index.add(entry);
                source.put(entry, binding);
            }
        }

        Set<KeyBindingIndex.Entry> conflicts = new HashSet<KeyBindingIndex.Entry>(index.conflicts());
        List<KeyBindingIndex.Entry> matches = index.search(searchField.getText());
        visible = new ArrayList<KeyBinding>();
        for (int i = 0; i < matches.size(); ++i) {
            KeyBindingIndex.Entry entry = matches.get(i);
            if ((filter == FILTER_CONFLICTS && conflicts.contains(entry))
                || (filter == FILTER_UNBOUND && entry.keyCode == 0)
                || filter == FILTER_ALL) {
                visible.add(source.get(entry));
            }
        }
        updatePageButtons();
    }

    private boolean isConflict(KeyBinding target) {
        int matches = 0;
        KeyBinding[] bindings = mc.gameSettings.keyBindings;
        for (int i = 0; i < bindings.length; ++i) {
            if (bindings[i] != null && target.keyCode != 0 && bindings[i].keyCode == target.keyCode) {
                ++matches;
            }
        }
        return matches > 1;
    }

    private String trim(String value, int maxWidth) {
        return fontRenderer.trimStringToWidth(value == null ? "" : value, maxWidth);
    }

    private void updatePageButtons() {
        if (buttonList.size() >= 6) {
            ((GuiButton)buttonList.get(3)).enabled = page > 0;
            ((GuiButton)buttonList.get(4)).enabled = (page + 1) * rowsPerPage < visible.size();
        }
    }
}
