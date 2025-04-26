@echo off
echo Compiling Tests...

javac -d out -sourcepath src\test\java ^
    -cp out;lib\junit-platform-console-standalone-1.11.4.jar ^
    src\test\java\com\juber\termjchess\model\board\*Test.java ^
    src\test\java\com\juber\termjchess\model\piece\*Test.java

echo [DONE]