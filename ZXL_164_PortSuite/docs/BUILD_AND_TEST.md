# Build and test notes

## Supported legacy build target

- Minecraft 1.6.4
- Forge 9.11.1.964
- ForgeGradle 1.0-SNAPSHOT
- Gradle 2.14.1
- JDK 8

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

1. **Core unit tests** — key search/conflict indexing, hunger preview clamping, recipe indexing, backup naming, and tooltip wrapping.
2. **Forge compile test** — compile all five mod entrypoints against Forge 9.11.1.964.
3. **Client smoke test** — launch a clean 1.6.4 instance and confirm all five mod IDs load; open the title screen, inventory, controls screen, and Recipe Viewer (R).
4. **Feature tests** — verify key browser filters, Apple164 food preview, RecipeViewer search/paging, backup rotation/restore, and tooltip rendering.
5. **Dedicated server test** — especially Backup164 and common-side class loading.

## Current verified status

Five Alpha JARs have previously been published for Forge 9.11.1.964. The current RecipeViewer164 client integration was added after that artifact publication and therefore is **not yet included in those JARs**. The source changes in this iteration have been committed to GitHub, but a fresh ForgeGradle compile and in-game smoke test have not yet been verified. Do not treat the earlier Alpha JARs as containing this new RecipeViewer GUI or as final releases.
