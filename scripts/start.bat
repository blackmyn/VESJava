@echo off
cd ../src/main/java

javac com/ecosystem/*.java com/ecosystem/utils/*.java

if %errorlevel% == 0 (
    echo Запуск приложения...
    java com.ecosystem.App
) else (
    echo Ошибка компиляции!
)

pause
