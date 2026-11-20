#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$ROOT/build-core-check"
rm -rf "$OUT" && mkdir -p "$OUT"
find "$ROOT" -path '*/src/main/java/*/core/*.java' -print0 | xargs -0 javac -source 8 -target 8 -Xlint:-options -d "$OUT"
javac -source 8 -target 8 -Xlint:-options -cp "$OUT" -d "$OUT" "$ROOT/scripts/CoreSmoke.java"
java -cp "$OUT" CoreSmoke
echo "Core Java syntax/runtime checks passed. (JDK 8 bytecode check only; Forge build not run.)"
