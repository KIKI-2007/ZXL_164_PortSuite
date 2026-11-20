# Build and test notes

## Legacy toolchain warning

Forge 1.6.4 is from the legacy ForgeGradle 1.2 generation. The build script targets Forge `1.6.4-9.11.1.1345`. This is not a modern ForgeGradle project. Use a clean JDK 7/8 + Gradle 2.x compatible environment (install Gradle separately; no wrapper is included yet). A modern JDK/Gradle may fail before compilation because the old Gradle/ForgeGradle plugins use APIs removed from modern runtimes.

## Build

```bash
gradle --version
gradle clean build
```

If ForgeGradle cannot resolve, verify access to `https://maven.minecraftforge.net/` and Maven Central. Do not silently switch the target Forge version.

## Test layers

1. **Core unit tests** — add JUnit 4 tests for search/conflict indexing, hunger preview clamping, recipe indexing, backup naming, and tooltip wrapping.
2. **Forge compile test** — compile all five entrypoints against the exact 1.6.4 Forge dependency.
3. **Client smoke test** — launch a clean instance, confirm all five mod IDs load and the title screen/inventory/controls screen open.
4. **Feature tests** — verify each GUI/HUD/backup feature after its integration milestone.
5. **Dedicated server test** — especially Backup164 and common-side class loading.

## Current verified status

The version-independent `core` Java source files were syntax-compiled with the available JDK using Java 7 source/target compatibility. The complete ForgeGradle build and in-game tests were not run in this environment (JDK 21 only; Gradle not installed). Therefore this archive is a source scaffold and not yet a validated release JAR set.
