@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
javac -cp "lib\mysql-connector-j-8.0.33.jar" -d out src\*.java src\database\*.java src\model\*.java src\repository\*.java src\service\*.java src\ui\*.java
java -cp "out;lib\mysql-connector-j-8.0.33.jar" App
