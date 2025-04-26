@echo off
echo Compiling...

javac -d out\ -sourcepath src\main\java ^
    src\main\java\com\juber\termjchess\*.java ^
    src\main\java\com\juber\termjchess\model\board\*.java ^
    src\main\java\com\juber\termjchess\model\piece\*.java ^
    src\main\java\com\juber\termjchess\exception\*.java

echo [DONE]

echo Setting resources...
xcopy /E /I /Y src\main\resources out\
