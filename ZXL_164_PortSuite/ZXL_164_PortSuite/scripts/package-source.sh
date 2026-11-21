#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$(dirname "$ROOT")"
zip -qr ZXL_164_PortSuite_source.zip "$(basename "$ROOT")" -x '*/build-core-check/*' '*/.gradle/*' '*/build/*'
echo "Created $(dirname "$ROOT")/ZXL_164_PortSuite_source.zip"
