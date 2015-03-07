

%{
// * Declaraciones de c�digo Java
// * Se sit�an al comienzo del archivo generado
// * El package lo a�ade yacc si utilizamos la opci�n -Jpackage
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
%right '=' 
%left AND OR DISTINTO
%left IGUALDAD MAYORIGUAL MENORIGUAL '<' '>' 
%left '+', '-'
%left '*', '/', '%'
%right NOT	
%nonassoc '(' ')' '[' ']'
%left '.'

%%
// * Gram�tica y acciones Yacc

programa
		: 	definiciones						{this.ast = new Programa((List<Definicion>)$1);}						
		;

definiciones
		: 	definicion							{List<Definicion> lista = new ArrayList<Definicion>();
												lista.add((Definicion)$1);
												$$ = lista;}	
		|	definiciones definicion				{List<Definicion> lista = (List<Definicion>) $1;
												lista.add((Definicion) $2);
												$$ = lista;};
		;

definicion
		:  	definicion_variable					{$$ = $1;}
		|	definicion_struct					{$$ = $1;}
		|	definicion_funcion					{$$ = $1;}
		|	definicion_procedimiento			{$$ = $1;}
		;
		
definiciones_variable_opc
		:	/*vacio*/							{$$ = new ArrayList<DefinicionVariable>();}
		|	definiciones_variable;				{$$ = $1;}
		
definiciones_variable
		:	definiciones_variable definicion_variable			{List<DefinicionVariable> lista = (List<DefinicionVariable>) $1;
																lista.add((DefinicionVariable)$2);
																$$ = lista;};
		|	definicion_variable									{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
																lista.add((DefinicionVariable)$1);
																$$ = lista;}
		;

definicion_variable
		: 	DIM IDENT array_opc AS tipo ';'		{$$ = new DefinicionVariable((Tipo)$5, (List<Integer>)$3, (String)$2);}						
		;
	
array_opc
		:	/*vacio*/							{$$ = null;}
		| 	lista_dimensiones					{$$ = $1;}
		;
	
lista_dimensiones
		: 	'['CTE_ENTERA']'					{List<Integer> lista = new ArrayList<Integer>();
												lista.add(Integer.parseInt((String)$2));
												$$ = lista;}
		| 	lista_dimensiones'['CTE_ENTERA']'   {List<Integer> lista = (List<Integer>) $1;
												lista.add(Integer.parseInt((String)$3));
												$$ = lista;};
		;
	
definicion_struct
		:	TYPE IDENT lista_campos END TYPE ';'			{$$ = new DefinicionStruct((String)$2, (List<Campo>)$3);}	
		;
		
definicion_funcion
		: 	FUNCTION IDENT '(' listaParametrosOpcional ')' AS tipo definiciones_variable_opc sentencias END FUNCTION ';'		{TipoFuncion tipoFuncion = new TipoFuncion((Tipo)$7, (String)$2, (List<DefinicionVariable>)$4);
																																$$ = new DefinicionFuncion(tipoFuncion, (List<DefinicionVariable>)$8, (List<Sentencia>)$9);}
		;
		
definicion_procedimiento
		:	PROC IDENT '(' listaParametrosOpcional ')' definiciones_variable_opc sentencias END PROC ';'						{$$ = new DefinicionProcedimiento((String)$2, (List<DefinicionVariable>)$4, (List<DefinicionVariable>)$6, (List<Sentencia>)$7);}
		
listaParametrosOpcional
		:  	/*vac�o*/							{$$ = new ArrayList<DefinicionVariable>();} 				
		| 	listaParametros  					{$$ = $1;}
		;
	
listaParametros 
		: 	listaParametros ',' IDENT AS tipo 	{List<DefinicionVariable> lista = (List<DefinicionVariable>) $1;
												lista.add(new DefinicionVariable((Tipo)$5,null,(String)$3));
												$$ = lista;};
		| 	IDENT AS tipo						{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
												lista.add(new DefinicionVariable((Tipo)$3,null,(String)$1));
												$$ = lista;}								
		;
		
sentencias_opc
		:	/*vacio*/							{$$ = new ArrayList<Sentencia>();}
		| 	sentencias							{$$ = $1;}
		;
		
sentencias
		: 	sentencias sentencia 	 	 		{List<Sentencia> lista = (List<Sentencia>) $1;
												lista.add((Sentencia)$2);
												$$ = lista;};	
		| 	sentencia							{List<Sentencia> lista = new ArrayList<Sentencia>();
												lista.add((Sentencia)$1);
												$$ = lista;}																						  
		;

sentencia
		: 	PRINT expresion ';'														{$$ = new Print((Expresion)$2);}
		|	READ expresion ';'														{$$ = new Read((Expresion)$2);}
		|	expresion '=' expresion ";"												{$$ = new Asignacion((Expresion)$1,(Expresion)$3);}
		|	RETURN expresion ";"													{$$ = new Return((Expresion)$2);}
		|	RETURN ";"																{$$ = new Return(null);}
		|	WHILE expresion DO sentencias END WHILE ';'								{$$ = new While((Expresion)$2, (List<Sentencia>)$4);}
		|	IF expresion THEN sentencias_opc END IF ';'								{$$ = new IF((Expresion)$2, (List<Sentencia>)$4);}
		|	IF expresion THEN sentencias_opc ELSE sentencias_opc END IF ';'			{$$ = new IF((Expresion)$2, (List<Sentencia>)$4, (List<Sentencia>)$6);}
		|  	IDENT '(' expresiones_opc ')' ";"										{$$ = new InvocacionProcedimiento((String)$1, (List<Expresion>)$3);}
		;

expresiones_opc
		:	/*vacio*/
		| 	expresiones
		;
		
expresiones
		:	expresiones ',' expresion			{List<Expresion> lista = (List<Expresion>) $1;
												lista.add((Expresion)$3);
												$$ = lista;};
		|	expresion							{List<Expresion> lista = new ArrayList<Expresion>();
												lista.add((Expresion)$1);
												$$ = lista;}
		;

expresion
		: 	IDENT								{$$ = new Variable((String)$1);}
		| 	CTE_ENTERA							{$$ = new LiteralEntero(Integer.parseInt((String)$1));}
		| 	CHARACTER							{$$ = new LiteralCaracter((Character)$1);}
		|	REAL								{$$ = new LiteralReal(Double.parseDouble((String)$1));}
		|	expresion '+' expresion				{$$ = new Aritmetica((Expresion)$1, "+", (Expresion)$3);}
		|	expresion '-' expresion				{$$ = new Aritmetica((Expresion)$1, "-", (Expresion)$3);}
		|	expresion '*' expresion				{$$ = new Aritmetica((Expresion)$1, "*", (Expresion)$3);}
		|	expresion '/' expresion				{$$ = new Aritmetica((Expresion)$1, "/", (Expresion)$3);}	
		|	expresion '%' expresion				{$$ = new Aritmetica((Expresion)$1, "%", (Expresion)$3);}
		|	expresion '[' expresion ']'			{$$ = new AccesoArray((Expresion)$1, (Expresion)$3);}
		|	expresion '.' expresion				{$$ = new AccesoCampo((Expresion)$1, (Expresion)$3);}
		|	expresion '<' expresion				{$$ = new Comparacion((Expresion)$1, "<", (Expresion)$3);}
		|	expresion '>' expresion				{$$ = new Comparacion((Expresion)$1, ">", (Expresion)$3);}
		|	expresion MENORIGUAL expresion		{$$ = new Comparacion((Expresion)$1, "<=", (Expresion)$3);}
		|	expresion MAYORIGUAL expresion		{$$ = new Comparacion((Expresion)$1, ">=", (Expresion)$3);}
		|	expresion AND expresion				{$$ = new Logica((Expresion)$1, "and", (Expresion)$3);}
		|	expresion OR expresion				{$$ = new Logica((Expresion)$1, "or", (Expresion)$3);}
		|	NOT expresion						{$$ = new Negacion((Expresion)$2);}
		|	expresion IGUALDAD expresion		{$$ = new Comparacion((Expresion)$1, "==", (Expresion)$3);}
		|	expresion DISTINTO expresion		{$$ = new Comparacion((Expresion)$1, "<>", (Expresion)$3);}
		| 	IDENT '(' expresiones_opc ')' 		{$$ = new InvocacionFuncion((String)$1, (List<Expresion>)$3);}
		;
		
tipo
		: 	INTEGER								{$$ = TipoEntero.getInstancia();} 
		| 	REAL								{$$ = TipoReal.getInstancia();} 
		| 	CHARACTER							{$$ = TipoChar.getInstancia();} 
		| 	IDENT								{$$ = new TipoStruct((String)$1);} 		
		; 		

lista_campos
		:	campo								{List<Campo> lista = new ArrayList<Campo>();
												lista.add((Campo)$1);
												$$ = lista;}					
		| 	lista_campos campo					{List<Campo> lista = (List<Campo>) $1;
												lista.add((Campo)$2);
												$$ = lista;};
							
campo
		:  	IDENT array_opc AS tipo ';'			{$$ = new Campo((String)$1, (Tipo)$4);}	  
		
	
%%

private Lexico lexico;

//*�rbol AST
public NodoAST ast;


// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error L�xico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Error Sint�ctico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * El yylval no es un atributo p�blico
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sint�ctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}
