# ZXL Better 1.6.4 Port Suite

Five separately packaged Minecraft 1.6.4 Forge modules:

| Module | Mod ID | Current core model | Planned integration |
|---|---|---|---|
| Controlling164 | `zxl_controlling164` | keybinding search/conflict index | Controls GUI search field, conflict/free-key filters |
| Apple164 | `zxl_apple164` | hunger/saturation preview calculation | HUD overlays, held-food preview, food tooltip |
| RecipeViewer164 | `zxl_recipeviewer164` | recipe entry/index | searchable item list, crafting/furnace recipe pages |
| Backup164 | `zxl_backup164` | safe timestamp naming | server scheduler, world save, ZIP rotation and restore |
| Tooltip164 | `zxl_tooltip164` | text wrapping | tooltip event hook, width/height clamp and styling |

## Important status

This is **alpha-0.1.0 engineering scaffold**, not five finished feature-equivalent mods. Each module has a Forge `@Mod` entrypoint and isolated, Java-7-compatible core logic. The actual 1.6.4 GUI, HUD, recipe registration, server backup scheduler, and tooltip render hooks are explicitly staged as follow-up work. Do not mistake a successful Java-core test for an in-game Forge compatibility test.

## Target environment

- Minecraft 1.6.4
- Forge 9.11.1.1345 (the build script target; change only after testing the user's exact Forge build)
- Java 7 runtime/toolchain for the legacy ForgeGradle build
- Gradle 2.x era ForgeGradle 1.2-SNAPSHOT

The current build environment used to prepare this archive has Java 21 and no installed Gradle. ForgeGradle 1.2 is a legacy toolchain and cannot be honestly claimed to have completed a full Forge build here. Use a Java 7/8 legacy build environment or import into an era-compatible ForgeGradle workspace. See `docs/BUILD_AND_TEST.md`.

## Build all modules

From the root, with a compatible Gradle/Java toolchain:

```bash
gradle clean build
```

Build an individual mod:

```bash
gradle :Controlling164:build
gradle :Apple164:build
gradle :RecipeViewer164:build
gradle :Backup164:build
gradle :Tooltip164:build
```

Each module outputs its own JAR under its `build/libs/` directory.

## Licensing

See `THIRD_PARTY_NOTICES.md`. No upstream JARs or third-party source files are included in this scaffold.
