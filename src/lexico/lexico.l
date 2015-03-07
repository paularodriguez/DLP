// ************  CÛdigo a incluir ********************

package lexico;
import sintactico.Parser;
import gestorErrores.GestorErrores;
import java.io.Reader;

%%
// ************  Opciones ********************
// % debug // * OpciÛn para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y mÈtodos ********************
// * El analizador sint·ctico
private Parser parser;
private GestorErrores gestor;

public Lexico(Reader in, GestorErrores gestor) {
	this(in);
	this.gestor = gestor;
}


public void setParser(Parser parser) {
	this.parser=parser;
}

public int line() { 
	return yyline+1;
}

public int column() { 
	return yycolumn+1;
}

%}

DIGITO = [0-9]
CARACTER =[A-Za-z·ÈÌÛ˙¡…Õ”⁄Ò—_]
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
{Real}								{parser.setYylval(new Double(yytext())); return Parser.REAL;}
"," 								{parser.setYylval(yytext()); return (int)yycharat(0);}
";" 								{parser.setYylval(yytext()); return (int)yycharat(0);}
[0-9]+								{parser.setYylval(new Integer(yytext())); return Parser.CTE_ENTERA;}	
\'[^\']\'   						{parser.setYylval(yytext().charAt(1)); return Parser.CHARACTER;}

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

. 									{ gestor.error("Error lÈxico en la lÌnea " + line() + " y columna " + column() + ".");}