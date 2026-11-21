# ZXL Better 1.6.4 Port Suite

Five separately packaged Minecraft 1.6.4 Forge modules:

| Module | Mod ID | Current core model | Planned integration |
|---|---|---|---|
| Controlling164 | `zxl_controlling164` | keybinding search/conflict index | Controls GUI search field, conflict/free-key filters |
| Apple164 | `zxl_apple164` | hunger/saturation preview calculation | HUD overlays, held-food preview, food tooltip |
| RecipeViewer164 | `zxl_recipeviewer164` | recipe entry/index | searchable item list, crafting/furnace recipe pages |
| Backup164 | `zxl_backup164` | safe timestamp naming | server scheduler, world save, ZIP rotation and restore |
| Tooltip164 | `zxl_tooltip164` | text wrapping | tooltip event hook, width/height clamp and styling |

## Status

This is **alpha-0.1.0 engineering scaffold**, not five finished feature-equivalent mods. Each module has a Forge `@Mod` entrypoint and isolated Java core logic. The actual 1.6.4 GUI, HUD, recipe registration, server backup scheduler, and tooltip render hooks remain to be implemented. The published Alpha JARs are development artifacts, not finished releases.

## Build target

- Minecraft 1.6.4
- Forge 9.11.1.964
- ForgeGradle 1.0-SNAPSHOT
- Gradle 2.14.1
- JDK 8 (CI)

See `docs/BUILD_AND_TEST.md` for build instructions and the exact validation status.

## Build all modules

From this directory, with the legacy toolchain above:

```bash
gradle clean build --no-daemon
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
