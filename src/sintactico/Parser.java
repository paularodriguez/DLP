//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 4 "../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
import java.util.*;
import ast.*;
import ast.sent.*;
import ast.tipos.*;
import ast.expr.*;
//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INTEGER=258;
public final static short REAL=259;
public final static short CHARACTER=260;
public final static short VAR=261;
public final static short DIM=262;
public final static short AS=263;
public final static short CTE_ENTERA=264;
public final static short PRINT=265;
public final static short FUNCTION=266;
public final static short PROC=267;
public final static short END=268;
public final static short TYPE=269;
public final static short IF=270;
public final static short THEN=271;
public final static short ELSE=272;
public final static short WHILE=273;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    5,    7,    7,    4,
    8,    8,   10,   10,   11,   11,    9,   12,   12,   12,
   12,   12,    6,    6,    6,    6,   13,   14,   14,   15,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    5,    2,    0,    4,    9,
    0,    1,    5,    3,    2,    1,    3,    1,    1,    3,
    3,    3,    1,    1,    1,    1,    6,    0,    3,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    3,    4,    5,    8,    0,    0,
    2,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   26,    0,    0,    0,    0,   28,    6,    0,    0,    0,
    9,    0,   14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   29,   17,    0,   13,   27,    0,   10,
    0,    0,   30,
};
final static short yydgoto[] = {                          3,
    4,    5,    6,    7,    9,   20,   12,   23,   35,   24,
    0,    0,   21,   32,   44,
};
final static short yysindex[] = {                      -257,
 -251, -250,    0, -257,    0,    0,    0,    0, -255,  -31,
    0,  -80, -256, -245, -249,    0,    0,    0, -241,  -42,
    0, -244,  -23,  -24,  -72,    0,    0, -256, -243, -234,
    0,  -59,    0, -232, -242, -236, -240, -231,  -29, -239,
 -256,  -26, -225,    0,    0,  -25,    0,    0, -228,    0,
 -256,  -22,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,   36,    0,    0,    0,    0,    0,    0,
    0, -224,    0,   -3,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -1,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   37,    0,    0,    0,  -27,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=209;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   33,   16,   17,   18,    1,    8,   10,   13,   14,    2,
   15,   22,   19,   47,   25,   26,   27,   29,   28,   30,
   31,   34,   36,   52,   39,   40,   41,   46,   42,   45,
   43,   49,   48,   50,   51,    1,   53,   11,    7,   12,
   11,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         59,
   28,  258,  259,  260,  262,  257,  257,  263,   40,  267,
   91,  257,  269,   41,  264,  257,   59,   41,  263,   44,
   93,  265,  257,   51,  257,  268,  263,  267,  269,   59,
  262,  257,   59,   59,  263,    0,   59,   41,  263,   41,
    4,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=273;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IDENT","INTEGER","REAL","CHARACTER","VAR",
"DIM","AS","CTE_ENTERA","PRINT","FUNCTION","PROC","END","TYPE","IF","THEN",
"ELSE","WHILE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_declaraciones",
"lista_declaraciones : lista_declaraciones declaracion",
"lista_declaraciones : declaracion",
"declaracion : definicion_variable",
"declaracion : definicion_proc",
"definicion_variable : DIM variable AS tipo ';'",
"variable : IDENT array",
"array :",
"array : array '[' CTE_ENTERA ']'",
"definicion_proc : PROC IDENT '(' listaParametrosOpcional ')' sentencia END PROC ';'",
"listaParametrosOpcional :",
"listaParametrosOpcional : listaParametros",
"listaParametros : listaParametros ',' IDENT AS tipo",
"listaParametros : IDENT AS tipo",
"listaSentencias : listaSentencias sentencia",
"listaSentencias : sentencia",
"sentencia : PRINT IDENT ';'",
"expresion : IDENT",
"expresion : CTE_ENTERA",
"expresion : expresion '*' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"tipo : INTEGER",
"tipo : REAL",
"tipo : CHARACTER",
"tipo : tipoStruct",
"tipoStruct : TYPE IDENT listaCampos END TYPE ';'",
"listaCampos :",
"listaCampos : listaCampos ';' campo",
"campo : DIM IDENT AS tipo ';'",
};

//#line 121 "../src/sintactico/sintactico.y"

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
//#line 308 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 39 "../src/sintactico/sintactico.y"
{this.ast = new Programa((List)val_peek(0));}
break;
case 2:
//#line 43 "../src/sintactico/sintactico.y"
{ ((List)val_peek(1)).add(val_peek(0)); yyval = val_peek(1);}
break;
case 3:
//#line 44 "../src/sintactico/sintactico.y"
{yyval = new ArrayList(); ((List)yyval).add(val_peek(0));}
break;
case 4:
//#line 48 "../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 5:
//#line 49 "../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 10:
//#line 65 "../src/sintactico/sintactico.y"
{yyval = new Procedimiento((String)val_peek(7), (Object)val_peek(5), (Sentencia)val_peek(3)); }
break;
case 11:
//#line 69 "../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); }
break;
case 12:
//#line 70 "../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 74 "../src/sintactico/sintactico.y"
{((List)val_peek(4)).add((Tipo)val_peek(2)); yyval = val_peek(4); }
break;
case 14:
//#line 75 "../src/sintactico/sintactico.y"
{yyval = val_peek(2);}
break;
case 15:
//#line 79 "../src/sintactico/sintactico.y"
{ ((List<Sentencia>)val_peek(1)).add((Sentencia)val_peek(0)); yyval = val_peek(1); }
break;
case 16:
//#line 80 "../src/sintactico/sintactico.y"
{ List<Sentencia> lista = new ArrayList<Sentencia>();
													  lista.add((Sentencia)val_peek(0));
													  yyval = lista;}
break;
case 17:
//#line 86 "../src/sintactico/sintactico.y"
{yyval = new Print((String)val_peek(1));}
break;
case 18:
//#line 90 "../src/sintactico/sintactico.y"
{yyval = new Variable((String)val_peek(0));}
break;
case 19:
//#line 91 "../src/sintactico/sintactico.y"
{yyval = new LiteralEntero((int)val_peek(0));}
break;
case 20:
//#line 92 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2),"*", (Expresion)val_peek(0));}
break;
case 21:
//#line 93 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2),"+", (Expresion)val_peek(0));}
break;
case 22:
//#line 94 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2),"-", (Expresion)val_peek(0));}
break;
case 23:
//#line 98 "../src/sintactico/sintactico.y"
{yyval = TipoEntero.getInstancia();}
break;
case 24:
//#line 99 "../src/sintactico/sintactico.y"
{yyval = TipoReal.getInstancia();}
break;
case 25:
//#line 100 "../src/sintactico/sintactico.y"
{yyval = TipoChar.getInstancia();}
break;
case 26:
//#line 101 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 27:
//#line 105 "../src/sintactico/sintactico.y"
{yyval = new TipoStruct((String)val_peek(4), (List)val_peek(3));}
break;
case 28:
//#line 108 "../src/sintactico/sintactico.y"
{ List<Campo> lista = new ArrayList<Campo>();
								yyval = lista;
					}
break;
case 29:
//#line 112 "../src/sintactico/sintactico.y"
{
								List<Campo> lista = (List<Campo>) val_peek(2);
								lista.add((Campo) val_peek(0));
								yyval = lista;
							}
break;
case 30:
//#line 118 "../src/sintactico/sintactico.y"
{yyval = new Campo((Tipo)val_peek(1), (String)val_peek(3));}
break;
//#line 569 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
