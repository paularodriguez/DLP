// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * El analizador sintáctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

DIGITO = [0-9]
CARACTER =[A-Za-záéíóúÁÉÍÓÚñÑ_]
ComentariosLinea = "//" ~ \n
ComentariosMultiLinea = "/*" ~ "*/"
Real = ([0-9]* \.[0-9]+) | [0-9][eE][+-]?[0-9]+ | [0-9]+ \. [0-9]* [eE]-?[0-9]+
%%
{ComentariosLinea} 					{}
{ComentariosMultiLinea} 			{}
dim									{return Parser.DIM;}
integer 							{parser.setYylval(yytext()); return Parser.INTEGER;}
real 								{parser.setYylval(yytext()); return Parser.REAL;}
character 							{parser.setYylval(yytext()); return Parser.CHARACTER;}
as									{return Parser.AS;}
end									{return Parser.END;}
proc								{return Parser.PROC;}
type								{return Parser.TYPE;}
print								{return Parser.PRINT;}
while 								{return Parser.WHILE;}
do									{return Parser.DO;}
if 									{return Parser.IF;}
else 								{return Parser.ELSE;}
then 								{return Parser.THEN;}
function							{return Parser.FUNCTION;}
read								{return Parser.READ;}
return								{return Parser.RETURN;}
{Real}								{parser.setYylval(yytext()); return Parser.REAL;}
"," 								{parser.setYylval(yytext()); return (int)yycharat(0);}
";" 								{parser.setYylval(yytext()); return (int)yycharat(0);}
[0-9]+								{parser.setYylval(yytext()); return Parser.CTE_ENTERA;}	
\'[^\']\'   						{parser.setYylval(yytext()); return Parser.CHARACTER;}

"<=" 								{parser.setYylval(yytext()); return Parser.MENORIGUAL;}
">=" 								{parser.setYylval(yytext()); return Parser.MAYORIGUAL;}
"==" 								{parser.setYylval(yytext()); return Parser.IGUALDAD;}
"<>" 								{parser.setYylval(yytext()); return Parser.DISTINTO;}
"and" 								{return Parser.AND;}
"or" 								{return Parser.OR;}
"not" 								{return Parser.NOT;}


")" |
"(" |
">" |
"<" |
"*" |
"/" |
"%" |
"-" |
"+" |
"=" |
"}" |
"{" |
"!" |
"[" |
"]" |
"." {parser.setYylval(yytext()); return (int)yycharat(0);}

{CARACTER}({CARACTER}|{DIGITO})* 	{parser.setYylval(yytext()); return Parser.IDENT;}

[ \n\r\t]							{}

. 									{System.out.println("Error: " + yytext());}