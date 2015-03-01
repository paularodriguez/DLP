

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
%token STRUCT
%left '+', '-'
%left '*', '/'
%%
// * Gramática y acciones Yacc

programa:  lista_declaraciones 						{this.ast = new Programa((List)$1);}			
	;

lista_declaraciones
	: lista_declaraciones declaracion				{ ((List)$1).add($2); $$ = $1;}
	| declaracion									{$$ = new ArrayList(); ((List)$$).add($1);}
	;
	
declaracion
	: DIM IDENT AS tipo ';'							{$$ = new DefinicionVariable((Tipo)$4, (String)$2);}
	//| definicion_proc								{ $$ = $1;}
	;
	
/*definicion_proc
	: PROC IDENT '(' listaParametrosOpcional ')' listaSentencias END PROC ';'
	
listaParametrosOpcional
	:  	 				{ $$ = new ArrayList<Expresion>(); }
	| listaParametros   { $$ = $1; }
	;
	*/
tipo
	: INTEGER										{$$ = TipoEntero.getInstancia();} 
	| REAL											{$$ = TipoReal.getInstancia();} 
	| CHARACTER										{$$ = TipoChar.getInstancia();} 
	; 		
	
				
	
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
