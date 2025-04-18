#!/bin/bash

# Script to run JUnit 5 tests for a specific test class
# should call this script from root project
# Usage: ./tools/test_class.sh <relative_package_path>
# Example: ./tools/test_class.sh model.board.Board
# Runs tests for BoardTest

# Check if exactly one parameter is provided
if [ $# -ne 1 ]; then
    echo "Usage: $0 <relative_package_path>"
    echo "Example: $0 model.board.Board"
    exit 1
fi

# Relative package path (e.g., model.vehicles.CarTest)
RELATIVE_PATH="$1"

# Fixed prefix for fully qualified class name
PREFIX="com.juber.termjchess"

# Construct fully qualified class name
FULL_CLASS_NAME="${PREFIX}.${RELATIVE_PATH}Test"


java -cp out:lib/junit-platform-console-standalone-1.11.4.jar \
    org.junit.platform.console.ConsoleLauncher \
    --select-class="$FULL_CLASS_NAME"
