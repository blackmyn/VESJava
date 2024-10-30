@echo off
REM Переходим в директорию проекта
cd /d "%~dp0"

REM Компилируем все .java файлы в папке src/main/java и выводим скомпилированные .class файлы в папку out
echo Компиляция файлов...
javac -d out -sourcepath src\main\java src\main\java\ecosystem\Main.java

REM Проверяем, успешно ли прошла компиляция
if %ERRORLEVEL% neq 0 (
    echo Ошибка компиляции!
    exit /b %ERRORLEVEL%
)

REM Запускаем основной класс из папки out
echo Запуск программы...
java -cp out ecosystem.Main

REM Ожидание перед закрытием окна
pause
