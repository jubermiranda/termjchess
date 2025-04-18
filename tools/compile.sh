echo "Compilling..."
javac -d out/ -sourcepath src/main/java \
    src/main/java/com/juber/termjchess/*.java \
    src/main/java/com/juber/termjchess/model/board/*.java \
    src/main/java/com/juber/termjchess/model/piece/*.java \
    src/main/java/com/juber/termjchess/exception/*.java 
echo "[DONE]"

echo "set resources"
cp -r src/main/resources/* out

