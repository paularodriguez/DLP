

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
import ast.def.*;
%}

// * Declaraciones Yacc
%token IDENT
%token INTEGER
%token REAL
%token CHARACTER
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
%token MAYORIGUAL
%token MENORIGUAL
%token DISTINTO
%token IGUALDAD
%token RETURN
%token READ
%token AND
%token OR
%token NOT
%token DO
%left '+', '-'
%left '*', '/'
%%
// * Gramática y acciones Yacc

programa
		: 	definiciones						
		;

definiciones
		: 	definicion							
		|	definiciones definicion				
		;

definicion
		:  	definicion_variable					{$$ = $1;}
		|	definicion_struct					{$$ = $1;}
		|	definicion_funcion					{$$ = $1;}
		|	definicion_procedimiento			{$$ = $1;}
		;
		
definiciones_variable_opc
		:	/*vacio*/
		|	definiciones_variable;
		
definiciones_variable
		:	definiciones_variable definicion_variable
		|	definicion_variable
		;

definicion_variable
		: 	DIM IDENT array_opc AS tipo ';'						
		;
	
array_opc
		:	/*vacio*/
		| 	lista_dimensiones
		;
	
lista_dimensiones
		: 	'['CTE_ENTERA']'
		| 	lista_dimensiones'['CTE_ENTERA']'       /*devolver array dimensiones para en definion de variable crear las dimensiones y el tipo array*/
		;
	
definicion_struct
		:	TYPE IDENT lista_campos END TYPE ';'	
		;
		
definicion_funcion
		: 	FUNCTION IDENT '(' listaParametrosOpcional ')' AS tipo definiciones_variable_opc sentencias END FUNCTION ';'
		;
		
definicion_procedimiento
		:	PROC IDENT '(' listaParametrosOpcional ')' definiciones_variable_opc sentencias END PROC ';'
		
listaParametrosOpcional
		:  	/*vacío*/ 				
		| 	listaParametros  
		;
	
listaParametros 
		: 	listaParametros ',' IDENT AS tipo 			
		| 	IDENT AS tipo 								
		;
		
sentencias_opc
		:	/*vacio*/
		| 	sentencias
		;
		
sentencias
		: 	sentencias sentencia 	 	 			
		| 	sentencia																						  
		;

sentencia
		: 	PRINT expresion ';'
		|	READ expresion ';'
		|	expresion '=' expresion ";"
		|	RETURN expresion ";"
		|	RETURN ";"
		|	WHILE expresion DO sentencias END WHILE ';'
		|	IF expresion THEN sentencias_opc END IF ';'
		|	IF expresion THEN sentencias_opc ELSE sentencias_opc END IF ';'
		|  	IDENT '(' expresiones_opc ')' ";"
		;

expresiones_opc
		:	/*vacio*/
		| 	expresiones
		;
		
expresiones
		:	expresiones ',' expresion
		|	expresion
		;

expresion
		: 	IDENT
		| 	CTE_ENTERA
		| 	CHARACTER
		|	REAL
		|	expresion '+' expresion
		|	expresion '-' expresion
		|	expresion '*' expresion
		|	expresion '/' expresion
		|	expresion '%' expresion
		|	expresion '[' expresion ']'
		|	expresion '.' expresion
		|	expresion '<' expresion
		|	expresion '>' expresion
		|	expresion MENORIGUAL expresion
		|	expresion MAYORIGUAL expresion
		|	expresion AND expresion
		|	expresion OR expresion
		|	NOT expresion
		|	expresion IGUALDAD expresion
		|	expresion DISTINTO expresion
		| 	IDENT '(' expresiones_opc ')' 
		;
		
tipo
		: 	INTEGER									{$$ = TipoEntero.getInstancia();} 
		| 	REAL									{$$ = TipoReal.getInstancia();} 
		| 	CHARACTER								{$$ = TipoChar.getInstancia();} 
		| 	IDENT											
		; 		

lista_campos
		:   /*vacío*/					
		| 	lista_campos campo
							
campo
		:  	IDENT array_opc AS tipo ';'	  
		
	
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
