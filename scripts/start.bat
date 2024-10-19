@echo off

cd /d "%~dp0"

echo Compiling Java files...
for %%a in (..\src\main\java\com\ecosystem\*.java) do javac -d "..\classes" %%a
for %%a in (..\src\main\java\com\ecosystem\utils\*.java) do javac -d "..\classes" %%a

if %errorlevel% == 0 (
    echo Starting program...
    cd ..
    java -cp "..\classes" D:\skillss\VESJava\src\main\java\ecosystem\Main.java
) else (
    echo Compilation Error! Check for problems in your Java code.
)

pause Press Enter to close... 