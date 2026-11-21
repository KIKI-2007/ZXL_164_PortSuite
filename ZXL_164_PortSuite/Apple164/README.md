# ZXL Apple HUD 1.6.4

Client-side, read-only food preview for Minecraft 1.6.4 Forge.

- When holding an `ItemFood` while hunger is below 20, show the predicted hunger and saturation after eating
- Uses the vanilla 1.6.4 `ItemFood` and `FoodStats` values
- Does not consume items, change hunger, or alter saturation
- Suppresses the preview while a GUI is open

This is an early integration. The preview is a client-side estimate and does not account for custom server-side food rules or special always-edible behavior at full hunger. In-game testing with the target modpack is still required.
