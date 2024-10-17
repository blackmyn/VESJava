#!/bin/bash

# Change to the script's directory
cd "$(dirname "$0")"

echo "Compiling Java files..."
for file in ../src/main/java/com/ecosystem/*.java; do
  javac -d ../classes "$file"
done
for file in ../src/main/java/com/ecosystem/utils/*.java; do
  javac -d ../classes "$file"
done

if [ $? -eq 0 ]; then
    echo "Starting program..."
    cd ..
    java -cp ".:./classes" D:\skillss\VESJava\src\main\java\Main.java
else
    echo "Compilation Error! Check for problems in your Java code."
fi
