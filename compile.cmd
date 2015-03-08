
cls
cd %~dp0
cd src
java -cp ..\tool\JFlex.jar JFlex.Main -d lexico lexico\lexico.l
pause
cd..
cd tool
yacc.exe -J -v -Jpackage=sintactico -Jsemantic=Object "../src/sintactico/sintactico.y"
move Parser.java ../src/sintactico
move y.output ../src/sintactico
pause