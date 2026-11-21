# Build and test notes

## Supported legacy build target

This repository's active build is configured for:

- Minecraft 1.6.4
- Forge 9.11.1.964
- ForgeGradle 1.0-SNAPSHOT
- Gradle 2.14.1
- JDK 8 (the CI toolchain)

The build uses Mojang's official 1.6.4 client/server artifact URLs to avoid ForgeGradle 1.0's obsolete S3 endpoints. Run commands from this directory:

```bash
gradle --version
gradle clean build --no-daemon
```

Build one module:

```bash
gradle :Controlling164:build
gradle :Apple164:build
gradle :RecipeViewer164:build
gradle :Backup164:build
gradle :Tooltip164:build
```

Do not change the Forge target to 9.11.1.1345 or another build without a separate dependency-resolution and runtime test.

## Validation levels

1. **Core unit tests** — exercise key search/conflict indexing, hunger preview clamping, recipe indexing, backup naming, and tooltip wrapping.
2. **Forge compile test** — compile all five mod entrypoints against Forge 9.11.1.964.
3. **Client smoke test** — launch a clean 1.6.4 instance and confirm all five mod IDs load and the title screen, inventory, and controls screen open.
4. **Feature tests** — verify each GUI/HUD/backup feature after its integration milestone.
5. **Dedicated server test** — especially Backup164 and common-side class loading.

## Current verified status

GitHub Actions has a legacy Forge build workflow and publishes five small Alpha JARs when the build succeeds. Those JARs contain the current scaffold/core code; they are not complete feature ports. No claim is made that the GUI, HUD, recipe browser, backup scheduler, or tooltip renderer is implemented or that the game has passed in-game testing. Treat the published JARs as development artifacts only.
