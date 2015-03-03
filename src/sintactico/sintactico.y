

%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
import java.util.*;
import ast.*;
import ast.sent.*;
import ast.tipos.*;
import ast.expr.*;
%}

// * Declaraciones Yacc
%token IDENT
%token INTEGER
%token REAL
%token CHARACTER
%token VAR
%token DIM
%token AS
%token CTE_ENTERA
%token PRINT
%token FUNCTION
%token PROC
%token END
%token TYPE
%token IF
%token THEN
%token ELSE
%token WHILE
%left '+', '-'
%left '*', '/'
%%
// * Gramática y acciones Yacc

programa:  lista_declaraciones						{this.ast = new Programa((List)$1);}	
	;

lista_declaraciones
	: lista_declaraciones declaracion				{ ((List)$1).add($2); $$ = $1;}
	| declaracion									{$$ = new ArrayList(); ((List)$$).add($1);}
	;
	
declaracion
	: definicion_variable							{ $$ = $1;}
	| definicion_proc								{ $$ = $1;}
	;

definicion_variable
	: DIM variable AS tipo ';'
	;
	
variable
	: IDENT array
	;
	
array
	:
	| array '['CTE_ENTERA']'
	
definicion_proc
	: PROC IDENT '(' listaParametrosOpcional ')' sentencia END PROC ';'			{$$ = new Procedimiento((String)$2, (Object)$4, (Sentencia)$6); }
	;
	
listaParametrosOpcional
	:  	 				{ $$ = new ArrayList<Expresion>(); }
	| listaParametros   { $$ = $1; }
	;
	
listaParametros 
	: listaParametros ',' IDENT AS tipo 			{((List)$1).add((Tipo)$3); $$ = $1; }
	| IDENT AS tipo 								{$$ = $1;}
	;
	
listaSentencias
	: listaSentencias sentencia 	 	 			{ ((List<Sentencia>)$1).add((Sentencia)$2); $$ = $1; }
	| sentencia										{ List<Sentencia> lista = new ArrayList<Sentencia>();
													  lista.add((Sentencia)$1);
													  $$ = lista;}
	;
	
sentencia
	: PRINT IDENT ";"	 							{$$ = new Print((String)$2);}
		

expresion
	: IDENT											{$$ = new Variable((String)$1);}
	| CTE_ENTERA									{$$ = new LiteralEntero((int)$1);}
	| expresion '*' expresion						{$$ = new Aritmetica((Expresion)$1,"*", (Expresion)$3);}
	| expresion '+' expresion						{$$ = new Aritmetica((Expresion)$1,"+", (Expresion)$3);}
	| expresion '-' expresion						{$$ = new Aritmetica((Expresion)$1,"-", (Expresion)$3);}
	;

tipo
	: INTEGER										{$$ = TipoEntero.getInstancia();} 
	| REAL											{$$ = TipoReal.getInstancia();} 
	| CHARACTER										{$$ = TipoChar.getInstancia();} 
	| tipoStruct									{$$ = $1;}
	; 		
	
tipoStruct
	: TYPE IDENT listaCampos END TYPE ';'			{$$ = new TipoStruct((String)$2, (List)$3);}
	;
	
listaCampos:   		{ List<Campo> lista = new ArrayList<Campo>();
								$$ = lista;
					}
				| listaCampos ";" campo
							{
								List<Campo> lista = (List<Campo>) $1;
								lista.add((Campo) $3);
								$$ = lista;
							};

campo:  DIM IDENT AS tipo ';'	  {$$ = new Campo((Tipo)$4, (String)$2);}
	
%%

private Lexico lexico;

//*Árbol AST
public NodoAST ast;


// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * El yylval no es un atributo público
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}
