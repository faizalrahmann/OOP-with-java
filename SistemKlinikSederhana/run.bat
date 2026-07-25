@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
javac -d out -cp . src\*.java src\database\*.java src\model\*.java src\repository\*.java src\service\*.java src\ui\*.java
java -cp out App
