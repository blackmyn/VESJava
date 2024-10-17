#!/bin/bash
cd ../src/main/java

javac com/ecosystem/*.java com/ecosystem/utils/*.java

if [ $? -eq 0 ]; then
    echo "Запуск приложения..."
    java com.ecosystem.App
else
    echo "Ошибка компиляции!"
fi
