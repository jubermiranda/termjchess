echo "Compilling Tests..."
javac -d out -sourcepath src/test/java \
    -cp out:lib/junit-platform-console-standalone-1.11.4.jar \
    src/test/java/com/juber/termjchess/model/board/*Test.java
echo "[DONE]"
