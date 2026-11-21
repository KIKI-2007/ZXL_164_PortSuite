# ZXL Better 1.6.4 Port Suite

Five separately packaged Minecraft 1.6.4 Forge modules:

| Module | Mod ID | Current implementation | Remaining work |
|---|---|---|---|
| Controlling164 | `zxl_controlling164` | Client key-binding browser with search, conflict and unbound filters | In-game acceptance testing and compatibility pass |
| Apple164 | `zxl_apple164` | Read-only held-food hunger/saturation preview HUD | Tooltip integration, positioning/configuration and in-game acceptance |
| RecipeViewer164 | `zxl_recipeviewer164` | Read-only crafting-output browser with search and paging (R key) | Ingredient layouts, furnace recipes, recipe detail pages and in-game acceptance |
| Backup164 | `zxl_backup164` | Safe timestamp naming core model | Server-safe save, ZIP rotation and restore |
| Tooltip164 | `zxl_tooltip164` | Text-wrapping core model | Tooltip event hook, width/height clamp and styling |

## Status

The project is in **Alpha development**. Controlling164 and Apple164 have client-side integrations. RecipeViewer164 now has an initial read-only crafting-output browser; it does not yet display ingredient layouts or furnace recipes. Backup164 and Tooltip164 remain at the core-scaffold stage. The full modpack has not yet passed in-game or dedicated-server acceptance testing. Published JARs are development builds, not final releases.

## Build target

- Minecraft 1.6.4
- Forge 9.11.1.964
- ForgeGradle 1.0-SNAPSHOT
- Gradle 2.14.1
- JDK 8 (CI)

See `docs/BUILD_AND_TEST.md` for build instructions and validation status.

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

See `THIRD_PARTY_NOTICES.md`. No upstream JARs or third-party source files are included in this clean-room scaffold.
