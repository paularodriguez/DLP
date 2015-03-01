// ************  C�digo a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opci�n para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y m�todos ********************
// * El analizador sint�ctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al n�mero de l�nea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n�mero de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

DIGITO = [0-9]
CARACTER =[A-Za-z������������_]
ComentariosLinea = "//" ~ \n
ComentariosMultiLinea = "/*" ~ "*/"
Real = ([0-9]* \.[0-9]+) | [0-9][eE][+-]?[0-9]+ | [0-9]+ \. [0-9]* [eE]-?[0-9]+
%%
{ComentariosLinea} 					{}
{ComentariosMultiLinea} 			{}
dim									{return Parser.DIM;}
"integer" 							{parser.setYylval(yytext()); return Parser.INTEGER;}
"double" 							{parser.setYylval(yytext()); return Parser.REAL;}
"character" 						{parser.setYylval(yytext()); return Parser.CHARACTER;}
as									{return Parser.AS;}
while 								{System.out.println("SENT WHILE");}
if 									{System.out.println("SENT IF");}
else 								{System.out.println("SENT ELSE");}
{Real}								{return Parser.REAL;}
";" 								{parser.setYylval(yytext()); return (int)yycharat(0);}
[0-9]+								{return Parser.CTE_ENTERA;}	
\'[^\']\'   						{return Parser.CHARACTER;}

{CARACTER}({CARACTER}|{DIGITO})* 	{parser.setYylval(yytext()); return Parser.IDENT;}

[ \n\r\t]							{}

. 									{System.out.println("Error: " + yytext());}