# Implementation roadmap

## Milestone 0 — repository scaffold (this archive)
- Separate Gradle subprojects and mod IDs.
- Forge 1.6.4 target declaration.
- Java-7-compatible core models.
- Third-party license review and clean-room policy.

## Milestone 1 — first real in-game integrations
- Controlling: intercept/replace the controls screen safely; add search, conflict and free-key filters without changing key registration semantics.
- Apple: read vanilla food stats and food item heal/saturation values; render preview without changing hunger mechanics. Multiplayer accuracy needs a compatible server sync protocol; client-only estimates must be labeled estimates.
- Tooltip: hook the 1.6.4 tooltip rendering path; wrap using actual FontRenderer widths, clamp to screen bounds, avoid changing item stack NBT.

## Milestone 2 — recipe browser
- Index vanilla and Forge `IRecipe` entries plus furnace recipes.
- Add pagination, item search, recipe/use views, and modded recipe support.
- Handle shapeless, shaped, ore dictionary and container-item recipes.

## Milestone 3 — world backup
- Server-side scheduled backups only after a safe world-save boundary.
- Exclude transient lock files; use temporary archive then atomic rename where supported.
- Retention policy, configurable destination, restore command with confirmation.
- Never delete the source world; test with interrupted archive and disk-full cases.

## Milestone 4 — compatibility and release
- Test exact Forge build and Java runtime.
- Test dedicated server/client split, integrated server, and multiplayer.
- Test with the user's Better 1.6.4 mod list, especially coremods, GUI replacements, OptiFine/Shaders, and large recipe registries.
- Publish independent JARs only after acceptance tests pass.
