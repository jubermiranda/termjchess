@echo off
echo Running all tests...

java -cp out;lib\junit-platform-console-standalone-1.11.4.jar ^
    org.junit.platform.console.ConsoleLauncher ^
    --scan-classpath


