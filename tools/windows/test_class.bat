@echo off
REM Script para rodar testes JUnit 5 de uma classe específica
REM Uso: test_class.bat model.board.Board

IF "%~1"=="" (
    echo Usage: %~nx0 ^<relative_package_path^>
    echo Example: %~nx0 model.board.Board
    exit /b 1
)

SET "PREFIX=com.juber.termjchess"
SET "FULL_CLASS_NAME=%PREFIX%.%~1Test"

java -cp out;lib\junit-platform-console-standalone-1.11.4.jar ^
    org.junit.platform.console.ConsoleLauncher ^
    --select-class=%FULL_CLASS_NAME%
