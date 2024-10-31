@echo off
cd /d "%~dp0"
echo Compilation
javac -d out -sourcepath src\main\java src\main\java\ecosystem\Main.java

if %ERRORLEVEL% neq 0 (
    echo Error
    exit /b %ERRORLEVEL%
)

echo Starting program...
java -cp out ecosystem.Main

pause
