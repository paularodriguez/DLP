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
import ast.def.*;
import gestorErrores.GestorErrores;
//#line 30 "Parser.java"




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
public final static short DIM=261;
public final static short AS=262;
public final static short CTE_ENTERA=263;
public final static short PRINT=264;
public final static short FUNCTION=265;
public final static short PROC=266;
public final static short END=267;
public final static short TYPE=268;
public final static short IF=269;
public final static short THEN=270;
public final static short ELSE=271;
public final static short WHILE=272;
public final static short MAYORIGUAL=273;
public final static short MENORIGUAL=274;
public final static short DISTINTO=275;
public final static short IGUALDAD=276;
public final static short RETURN=277;
public final static short READ=278;
public final static short AND=279;
public final static short OR=280;
public final static short NOT=281;
public final static short DO=282;
public final static short CTYPE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    7,    7,    8,
    8,    3,    9,    9,   11,   11,    4,    5,    6,   13,
   13,   15,   15,   14,   14,   16,   16,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   19,   19,   20,   20,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   10,   10,   10,   10,   12,   12,   21,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    1,    0,    1,    2,
    1,    6,    0,    1,    3,    4,    6,   12,   10,    0,
    1,    5,    3,    0,    1,    2,    1,    3,    3,    4,
    3,    2,    7,    7,    9,    5,    0,    1,    3,    1,
    1,    1,    1,    1,    3,    3,    3,    3,    3,    4,
    3,    3,    3,    3,    3,    3,    3,    3,    2,    3,
    3,    4,    6,    1,    1,    1,    1,    1,    2,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    4,    5,    6,
    7,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,   68,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   69,   15,   67,   64,   65,   66,    0,
    0,    0,    0,    0,    0,    0,    0,   12,   16,   23,
    0,    0,   11,    0,    0,    0,   17,    0,    0,    0,
   44,   43,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   27,    0,   10,   70,    0,   22,    0,
    0,    0,    0,    0,   32,    0,    0,    0,    0,    0,
    0,   26,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   28,    0,    0,   31,   29,    0,   54,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   51,    0,    0,    0,
    0,    0,    0,    0,   19,   30,   50,    0,   36,    0,
   62,    0,    0,    0,    0,   18,    0,    0,    0,   63,
   34,    0,   33,    0,   35,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   10,   11,   54,   55,   18,   40,
   19,   23,   29,   72,   30,   73,   74,   75,  111,  112,
   24,
};
final static short yysindex[] = {                       -45,
 -256, -243, -237, -228,    0,  -45,    0,    0,    0,    0,
    0,  -54,   13,   19, -216,    0, -198, -184,   -7, -167,
 -167,  -54, -254,    0,   -2, -208, -171, -169,   54,   63,
   69, -148, -153,    0,    0,    0,    0,    0,    0,   57,
   25, -208, -138, -129, -131, -208,   73,    0,    0,    0,
 -208, -126,    0,  322, -131,   85,    0, -131, -208,  105,
    0,    0,    0,  291,  291,  291,    7,  291,  291,  107,
  291, -119,  322,    0,   80,    0,    0,  322,    0,  291,
  109,   92,  116,   59,    0,  127,  155,  -34, -208,  282,
 -111,    0,  291,  291,  291,  291,  291,  291,  291,  291,
  291,  291,  291,  291,  291,  291,  291,  291, -107,  439,
  115,  121,  291,    0,  322,  322,    0,    0,  122,    0,
  108,   66,   66,  733,   66,  733,  733,  166,   66,   66,
  138,  138,  -34,  -34,  -34,  376,    0,  -97,  118,  291,
  140, -241,  -88,  291,    0,    0,    0,  123,    0,  439,
    0,  -81,  322,  -82,  406,    0,  132,  -74,  135,    0,
    0,  -73,    0,  136,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  199,    0,    0,    0,    0,
    0,  -58,    0,    0,    0,    0,    0,    0,  -57,  165,
  165,  -58,    0,    0,    0,    0,    0,    0,    0,  169,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  246,    0,    0,    0,    0,    0,
    0,    0,    0,  -48,  306,    0,    0,  246,    0,  417,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -240,    0,    0,    0,    0,  -48,    0,  181,
  -37,    0,    0,    0,    0,    0,    0,  -26,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   14,
    0,  183,  181,    0, -239,  -48,    0,    0,    0,    0,
    0,  484,  576,  684,  602,  690,  706,    0,  613,  676,
  552,  565,    1,   27,   38,    0,    0,    0,  428,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   32,
    0,    0,  -48,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  221,   88,    0,    0,    0,  172,    0,  210,  209,
    0,    0,  213,  -76,    0,    0,  158,  834,  128,    0,
  212,
};
final static int YYTABLESIZE=1009;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   12,  109,   22,   41,   41,   41,   41,   41,   41,   41,
   59,  108,   33,   13,   59,   59,   59,   59,   59,   14,
   59,   41,   41,   41,   41,  152,   25,   24,   15,  153,
   25,   24,   59,   59,   59,   59,   17,   47,  142,  143,
   22,   47,   47,   47,   47,   47,   71,   47,   36,   37,
   38,   39,   20,   41,   40,   41,  107,   40,   21,   47,
   47,   47,   47,   48,   25,   85,   59,   48,   48,   48,
   48,   48,   39,   48,   49,   39,  158,   26,   49,   49,
   49,   49,   49,   27,   49,   48,   48,   48,   48,   28,
   35,   41,   42,   47,   43,  106,   49,   49,   49,   49,
  104,  102,  106,  103,  108,  105,   44,  104,  102,   45,
  103,  108,  105,   46,   47,   48,  106,   49,  100,   48,
  101,  104,  102,   51,  103,  108,  105,   52,  106,    1,
   49,   57,   53,  104,  102,   59,  103,  108,  105,  100,
   99,  101,   76,   77,   80,   53,   89,   91,  113,  107,
  114,  100,  106,  101,  121,  139,  107,  104,  102,  138,
  103,  108,  105,  106,  140,  144,  145,  148,  104,  102,
  107,  103,  108,  105,  106,  100,  149,  101,  154,  104,
  151,  156,  107,  108,  105,  117,  100,  157,  101,  159,
  161,  106,  162,  163,  165,  164,  104,  102,    1,  103,
  108,  105,  106,   13,   14,   20,  107,  104,  102,   21,
  103,  108,  105,  118,  100,    1,  101,  107,   24,    2,
    3,   37,    4,   38,  146,  100,   16,  101,  107,   78,
   92,   32,   41,   31,   34,   41,   41,   41,   41,    0,
  141,   41,   41,   59,   41,  107,   59,   59,   59,   59,
   50,    0,   59,   59,   56,   59,  107,    0,    0,   58,
    0,    0,    0,   81,    0,   61,   62,   79,    0,   63,
   47,    0,    0,   47,   47,   47,   47,    0,    0,   47,
   47,    0,   47,    0,    0,    8,    0,   69,    0,   70,
    0,    0,    0,    0,    0,    0,   48,  119,    0,   48,
   48,   48,   48,    0,    0,   48,   48,   49,   48,    0,
   49,   49,   49,   49,    0,    0,   49,   49,  106,   49,
    0,    0,  120,  104,  102,    0,  103,  108,  105,    0,
   71,   93,   94,   95,   96,    0,    0,   97,   98,    0,
  116,  100,    0,  101,    0,    9,    0,    0,    0,    0,
    0,    0,   93,   94,   95,   96,    0,    0,   97,   98,
    0,   71,    0,    0,   93,   94,   95,   96,    0,    0,
   97,   98,  107,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  115,    0,    0,   93,   94,
   95,   96,    0,    0,   97,   98,    0,    0,    0,   93,
   94,   95,   96,    0,    0,   97,   98,    0,    0,    0,
    0,    0,  106,    0,    0,    0,    0,  104,  102,    0,
  103,  108,  105,    0,    0,    0,    0,   93,   94,   95,
   96,    0,    0,   97,   98,  100,    0,  101,   93,   94,
   95,   96,  106,    0,   97,   98,  160,  104,  102,    0,
  103,  108,  105,   41,    0,    0,    0,    0,   41,   41,
    0,   41,   41,   41,   62,  100,  107,  101,  147,   62,
   62,    0,   62,   62,   62,  106,   41,   41,   41,    0,
  104,  102,    0,  103,  108,  105,    0,   62,   62,   62,
    0,    0,    0,    0,    0,    0,  107,    0,  100,    0,
  101,    0,    8,    0,    8,    8,    0,   41,    8,    8,
    0,    0,    8,    0,    8,    0,    0,    8,   62,    0,
    0,    0,    8,    8,   56,    0,    8,   56,    8,  107,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,   56,   56,   56,    0,   81,    0,   61,
   62,    0,    0,   63,   93,   94,   95,   96,    0,    0,
   97,   98,    9,    0,    9,    9,    0,    0,    9,    9,
    0,   69,    9,   70,    9,    0,   56,    9,   60,    0,
   61,   62,    9,    9,   63,   64,    9,    0,    9,    0,
   65,    0,   45,   66,   45,   45,   45,    0,   67,   68,
    0,    0,   69,    0,   70,   46,    0,   46,   46,   46,
   45,   45,   45,   45,    0,    0,   55,    0,    0,   55,
    0,    0,    0,   46,   46,   46,   46,    0,    0,    0,
    0,    0,    0,    0,   55,   55,   55,   55,    0,    0,
    0,    0,   60,    0,   45,   60,    0,    0,   93,   94,
   95,   96,    0,   52,   97,   98,   52,   46,    0,    0,
   60,   60,   60,   60,    0,    0,    0,    0,   55,    0,
    0,   52,   52,   52,   52,    0,    0,    0,   93,   94,
   95,   96,    0,    0,   97,   98,    0,    0,    0,   41,
   41,   41,   41,    0,   60,   41,   41,    0,    0,    0,
   62,   62,   62,   62,    0,   52,   62,   62,    0,    0,
    0,   93,   94,   95,   96,    0,   53,   97,   98,   53,
    0,    0,    0,    0,   61,    0,    0,   61,    0,    0,
   57,    0,    0,   57,   53,   53,   53,   53,    0,    0,
    0,    0,   61,    0,   61,    0,   58,    0,   57,   58,
   57,    0,    0,   56,    0,    0,   56,   56,   56,   56,
    0,    0,   56,   56,   58,   56,   58,    0,   53,  106,
    0,    0,    0,    0,  104,  102,   61,  103,  108,  105,
    0,    0,   57,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  100,    0,  101,    0,    0,    0,   58,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,    0,  107,   45,   45,   45,   45,    0,    0,
   45,   45,    0,   45,   46,    0,    0,   46,   46,   46,
   46,    0,    0,   46,   46,   55,   46,    0,   55,   55,
   55,   55,    0,    0,   55,   55,    0,   55,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   60,    0,    0,   60,   60,   60,   60,    0,    0,
   60,   60,   52,   60,    0,   52,   52,   52,   52,    0,
    0,   52,   52,    0,   52,    0,    0,   82,   83,   84,
   86,   87,   88,    0,   90,    0,    0,    0,    0,    0,
    0,    0,    0,  110,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  122,  123,  124,  125,
  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,
  136,  137,    0,    0,    0,   53,  110,    0,   53,   53,
   53,   53,    0,   61,   53,   53,    0,   53,   61,   57,
    0,    0,   61,   61,   57,   61,    0,    0,   57,   57,
    0,   57,    0,  150,    0,   58,    0,  155,    0,    0,
   58,    0,    0,    0,   58,   58,    0,   58,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   93,   94,    0,   96,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  257,   78,  257,   41,   42,   43,   44,   45,   46,   47,
   37,   46,  267,  257,   41,   42,   43,   44,   45,  257,
   47,   59,   60,   61,   62,  267,  267,  267,  257,  271,
  271,  271,   59,   60,   61,   62,   91,   37,  115,  116,
  257,   41,   42,   43,   44,   45,   40,   47,  257,  258,
  259,  260,   40,   91,   41,   93,   91,   44,   40,   59,
   60,   61,   62,   37,  263,   59,   93,   41,   42,   43,
   44,   45,   41,   47,   37,   44,  153,  262,   41,   42,
   43,   44,   45,   91,   47,   59,   60,   61,   62,  257,
   93,  263,  262,   93,   41,   37,   59,   60,   61,   62,
   42,   43,   37,   45,   46,   47,   44,   42,   43,   41,
   45,   46,   47,  262,  268,   59,   37,   93,   60,   93,
   62,   42,   43,  262,   45,   46,   47,  257,   37,  261,
   93,   59,   45,   42,   43,  262,   45,   46,   47,   60,
   61,   62,   55,   59,   40,   58,   40,  267,   40,   91,
   59,   60,   37,   62,  266,   41,   91,   42,   43,  267,
   45,   46,   47,   37,   44,   44,   59,  265,   42,   43,
   91,   45,   46,   47,   37,   60,   59,   62,  267,   42,
   41,   59,   91,   46,   47,   59,   60,  269,   62,  272,
   59,   37,  267,   59,   59,  269,   42,   43,    0,   45,
   46,   47,   37,  262,  262,   41,   91,   42,   43,   41,
   45,   46,   47,   59,   60,  261,   62,   91,  267,  265,
  266,   41,  268,   41,   59,   60,    6,   62,   91,   58,
   73,   22,  270,   21,   23,  273,  274,  275,  276,   -1,
  113,  279,  280,  270,  282,   91,  273,  274,  275,  276,
   42,   -1,  279,  280,   46,  282,   91,   -1,   -1,   51,
   -1,   -1,   -1,  257,   -1,  259,  260,   59,   -1,  263,
  270,   -1,   -1,  273,  274,  275,  276,   -1,   -1,  279,
  280,   -1,  282,   -1,   -1,   40,   -1,  281,   -1,  283,
   -1,   -1,   -1,   -1,   -1,   -1,  270,   89,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,  270,  282,   -1,
  273,  274,  275,  276,   -1,   -1,  279,  280,   37,  282,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,
   40,  273,  274,  275,  276,   -1,   -1,  279,  280,   -1,
  282,   60,   -1,   62,   -1,   40,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,   -1,  279,  280,
   -1,   40,   -1,   -1,  273,  274,  275,  276,   -1,   -1,
  279,  280,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  270,   -1,   -1,  273,  274,
  275,  276,   -1,   -1,  279,  280,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,   -1,   -1,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,   -1,  279,  280,   60,   -1,   62,  273,  274,
  275,  276,   37,   -1,  279,  280,   41,   42,   43,   -1,
   45,   46,   47,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   37,   60,   91,   62,   93,   42,
   43,   -1,   45,   46,   47,   37,   60,   61,   62,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   60,   -1,
   62,   -1,  257,   -1,  259,  260,   -1,   91,  263,  264,
   -1,   -1,  267,   -1,  269,   -1,   -1,  272,   91,   -1,
   -1,   -1,  277,  278,   41,   -1,  281,   44,  283,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,  257,   -1,  259,
  260,   -1,   -1,  263,  273,  274,  275,  276,   -1,   -1,
  279,  280,  257,   -1,  259,  260,   -1,   -1,  263,  264,
   -1,  281,  267,  283,  269,   -1,   93,  272,  257,   -1,
  259,  260,  277,  278,  263,  264,  281,   -1,  283,   -1,
  269,   -1,   41,  272,   43,   44,   45,   -1,  277,  278,
   -1,   -1,  281,   -1,  283,   41,   -1,   43,   44,   45,
   59,   60,   61,   62,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   41,   -1,   93,   44,   -1,   -1,  273,  274,
  275,  276,   -1,   41,  279,  280,   44,   93,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,   -1,  279,  280,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,   93,  279,  280,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,   93,  279,  280,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,   41,  279,  280,   44,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   59,   -1,   61,   -1,   41,   -1,   59,   44,
   61,   -1,   -1,  270,   -1,   -1,  273,  274,  275,  276,
   -1,   -1,  279,  280,   59,  282,   61,   -1,   93,   37,
   -1,   -1,   -1,   -1,   42,   43,   93,   45,   46,   47,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  270,   -1,   91,  273,  274,  275,  276,   -1,   -1,
  279,  280,   -1,  282,  270,   -1,   -1,  273,  274,  275,
  276,   -1,   -1,  279,  280,  270,  282,   -1,  273,  274,
  275,  276,   -1,   -1,  279,  280,   -1,  282,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  270,   -1,   -1,  273,  274,  275,  276,   -1,   -1,
  279,  280,  270,  282,   -1,  273,  274,  275,  276,   -1,
   -1,  279,  280,   -1,  282,   -1,   -1,   64,   65,   66,
   67,   68,   69,   -1,   71,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   80,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   94,   95,   96,
   97,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,  108,   -1,   -1,   -1,  270,  113,   -1,  273,  274,
  275,  276,   -1,  270,  279,  280,   -1,  282,  275,  270,
   -1,   -1,  279,  280,  275,  282,   -1,   -1,  279,  280,
   -1,  282,   -1,  140,   -1,  270,   -1,  144,   -1,   -1,
  275,   -1,   -1,   -1,  279,  280,   -1,  282,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,   -1,  276,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INTEGER","REAL","CHARACTER",
"DIM","AS","CTE_ENTERA","PRINT","FUNCTION","PROC","END","TYPE","IF","THEN",
"ELSE","WHILE","MAYORIGUAL","MENORIGUAL","DISTINTO","IGUALDAD","RETURN","READ",
"AND","OR","NOT","DO","CTYPE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definicion",
"definiciones : definiciones definicion",
"definicion : definicion_variable",
"definicion : definicion_struct",
"definicion : definicion_funcion",
"definicion : definicion_procedimiento",
"definiciones_variable_opc :",
"definiciones_variable_opc : definiciones_variable",
"definiciones_variable : definiciones_variable definicion_variable",
"definiciones_variable : definicion_variable",
"definicion_variable : DIM IDENT array_opc AS tipo ';'",
"array_opc :",
"array_opc : lista_dimensiones",
"lista_dimensiones : '[' CTE_ENTERA ']'",
"lista_dimensiones : lista_dimensiones '[' CTE_ENTERA ']'",
"definicion_struct : TYPE IDENT lista_campos END TYPE ';'",
"definicion_funcion : FUNCTION IDENT '(' listaParametrosOpcional ')' AS tipo definiciones_variable_opc sentencias_opc END FUNCTION ';'",
"definicion_procedimiento : PROC IDENT '(' listaParametrosOpcional ')' definiciones_variable_opc sentencias_opc END PROC ';'",
"listaParametrosOpcional :",
"listaParametrosOpcional : listaParametros",
"listaParametros : listaParametros ',' IDENT AS tipo",
"listaParametros : IDENT AS tipo",
"sentencias_opc :",
"sentencias_opc : sentencias",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : PRINT expresion ';'",
"sentencia : READ expresion ';'",
"sentencia : expresion '=' expresion ';'",
"sentencia : RETURN expresion ';'",
"sentencia : RETURN ';'",
"sentencia : WHILE expresion DO sentencias_opc END WHILE ';'",
"sentencia : IF expresion THEN sentencias_opc END IF ';'",
"sentencia : IF expresion THEN sentencias_opc ELSE sentencias_opc END IF ';'",
"sentencia : IDENT '(' expresiones_opc ')' ';'",
"expresiones_opc :",
"expresiones_opc : expresiones",
"expresiones : expresiones ',' expresion",
"expresiones : expresion",
"expresion : IDENT",
"expresion : CTE_ENTERA",
"expresion : CHARACTER",
"expresion : REAL",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion '>' expresion",
"expresion : '(' expresion ')'",
"expresion : expresion MENORIGUAL expresion",
"expresion : expresion MAYORIGUAL expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : NOT expresion",
"expresion : expresion IGUALDAD expresion",
"expresion : expresion DISTINTO expresion",
"expresion : IDENT '(' expresiones_opc ')'",
"expresion : CTYPE '(' tipo ',' expresion ')'",
"tipo : INTEGER",
"tipo : REAL",
"tipo : CHARACTER",
"tipo : IDENT",
"lista_campos : campo",
"lista_campos : lista_campos campo",
"campo : IDENT array_opc AS tipo ';'",
};

//#line 236 "../src/sintactico/sintactico.y"

private Lexico lexico;

//*Árbol AST
public NodoAST ast;


// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	   System.err.println ("Error Léxico en línea " + lexico.line()+
		" y columna "+lexico.column()+":\n\t"+e);
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    gestor.error("Error sintáctico en la línea " + lexico.line() + " y columna " + lexico.column() + ".");
}

// * El yylval no es un atributo público
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

private GestorErrores gestor;

// * Constructor del Sintáctico
public Parser(Lexico lexico, GestorErrores gestor) {
	this.lexico = lexico;
	lexico.setParser(this);
	this.gestor = gestor;
}
//#line 566 "Parser.java"
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
//#line 59 "../src/sintactico/sintactico.y"
{this.ast = new Programa((List<Definicion>)val_peek(0), lexico.line(), lexico.column());}
break;
case 2:
//#line 63 "../src/sintactico/sintactico.y"
{List<Definicion> lista = new ArrayList<Definicion>();
												lista.add((Definicion)val_peek(0));
												yyval = lista;}
break;
case 3:
//#line 66 "../src/sintactico/sintactico.y"
{List<Definicion> lista = (List<Definicion>) val_peek(1);
												lista.add((Definicion) val_peek(0));
												yyval = lista;}
break;
case 4:
//#line 72 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 73 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 74 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 75 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 8:
//#line 79 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<DefinicionVariable>();}
break;
case 9:
//#line 80 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 83 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = (List<DefinicionVariable>) val_peek(1);
																lista.add((DefinicionVariable)val_peek(0));
																yyval = lista;}
break;
case 11:
//#line 86 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
																lista.add((DefinicionVariable)val_peek(0));
																yyval = lista;}
break;
case 12:
//#line 92 "../src/sintactico/sintactico.y"
{DefinicionVariable defVar = null; 
												if (((List<Integer>)val_peek(3)).size() == 0){
													defVar = new DefinicionVariable((Tipo)val_peek(1),(String)val_peek(4), lexico.line(), lexico.column());
												}
												else{
													defVar = new DefinicionVariable((Tipo)TipoArray.crearArray((Tipo)val_peek(1), (List<Integer>)val_peek(3)),(String)val_peek(4), lexico.line(), lexico.column());
												}
												yyval = defVar;
												}
break;
case 13:
//#line 104 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<Integer>();}
break;
case 14:
//#line 105 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 15:
//#line 109 "../src/sintactico/sintactico.y"
{List<Integer> lista = new ArrayList<Integer>();
												lista.add((Integer)val_peek(1));
												yyval = lista;}
break;
case 16:
//#line 112 "../src/sintactico/sintactico.y"
{List<Integer> lista = (List<Integer>) val_peek(3);
												lista.add((Integer)val_peek(1));
												yyval = lista;}
break;
case 17:
//#line 118 "../src/sintactico/sintactico.y"
{yyval = new DefinicionStruct((String)val_peek(4), (List<Campo>)val_peek(3), lexico.line(), lexico.column());}
break;
case 18:
//#line 122 "../src/sintactico/sintactico.y"
{yyval = new DefinicionFuncion((Tipo)val_peek(5), (String)val_peek(10), (List<DefinicionVariable>)val_peek(8), (List<DefinicionVariable>)val_peek(4), (List<Sentencia>)val_peek(3), lexico.line(), lexico.column());}
break;
case 19:
//#line 126 "../src/sintactico/sintactico.y"
{yyval = new DefinicionFuncion(null,(String)val_peek(8), (List<DefinicionVariable>)val_peek(6), (List<DefinicionVariable>)val_peek(4), (List<Sentencia>)val_peek(3), lexico.line(), lexico.column());}
break;
case 20:
//#line 129 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<DefinicionVariable>();}
break;
case 21:
//#line 130 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 22:
//#line 134 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = (List<DefinicionVariable>) val_peek(4);
												lista.add(new DefinicionVariable((Tipo)val_peek(0), (String)val_peek(2), lexico.line(), lexico.column()));
												yyval = lista;}
break;
case 23:
//#line 137 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
												lista.add(new DefinicionVariable((Tipo)val_peek(0), (String)val_peek(2), lexico.line(), lexico.column()));
												yyval = lista;}
break;
case 24:
//#line 143 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<Sentencia>();}
break;
case 25:
//#line 144 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 148 "../src/sintactico/sintactico.y"
{List<Sentencia> lista = (List<Sentencia>) val_peek(1);
												lista.add((Sentencia)val_peek(0));
												yyval = lista;}
break;
case 27:
//#line 151 "../src/sintactico/sintactico.y"
{List<Sentencia> lista = new ArrayList<Sentencia>();
												lista.add((Sentencia)val_peek(0));
												yyval = lista;}
break;
case 28:
//#line 157 "../src/sintactico/sintactico.y"
{yyval = new Print((Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 29:
//#line 158 "../src/sintactico/sintactico.y"
{yyval = new Read((Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 30:
//#line 159 "../src/sintactico/sintactico.y"
{yyval = new Asignacion((Expresion)val_peek(3),(Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 31:
//#line 160 "../src/sintactico/sintactico.y"
{yyval = new Return((Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 32:
//#line 161 "../src/sintactico/sintactico.y"
{yyval = new Return(null, lexico.line(), lexico.column());}
break;
case 33:
//#line 162 "../src/sintactico/sintactico.y"
{yyval = new While((Expresion)val_peek(5), (List<Sentencia>)val_peek(3), lexico.line(), lexico.column());}
break;
case 34:
//#line 163 "../src/sintactico/sintactico.y"
{yyval = new IF((Expresion)val_peek(5), (List<Sentencia>)val_peek(3), lexico.line(), lexico.column());}
break;
case 35:
//#line 164 "../src/sintactico/sintactico.y"
{yyval = new IF((Expresion)val_peek(7), (List<Sentencia>)val_peek(5), (List<Sentencia>)val_peek(3), lexico.line(), lexico.column());}
break;
case 36:
//#line 165 "../src/sintactico/sintactico.y"
{yyval = new InvocacionProcedimiento((String)val_peek(4), (List<Expresion>)val_peek(2), lexico.line(), lexico.column());}
break;
case 37:
//#line 169 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<Expresion>();}
break;
case 38:
//#line 170 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 39:
//#line 174 "../src/sintactico/sintactico.y"
{List<Expresion> lista = (List<Expresion>) val_peek(2);
												lista.add((Expresion)val_peek(0));
												yyval = lista;}
break;
case 40:
//#line 177 "../src/sintactico/sintactico.y"
{List<Expresion> lista = new ArrayList<Expresion>();
												lista.add((Expresion)val_peek(0));
												yyval = lista;}
break;
case 41:
//#line 183 "../src/sintactico/sintactico.y"
{yyval = new Variable((String)val_peek(0), lexico.line(), lexico.column());}
break;
case 42:
//#line 184 "../src/sintactico/sintactico.y"
{yyval = new LiteralEntero((Integer)val_peek(0), lexico.line(), lexico.column());}
break;
case 43:
//#line 185 "../src/sintactico/sintactico.y"
{yyval = new LiteralCaracter((Character)val_peek(0), lexico.line(), lexico.column());}
break;
case 44:
//#line 186 "../src/sintactico/sintactico.y"
{yyval = new LiteralReal((Double)val_peek(0), lexico.line(), lexico.column());}
break;
case 45:
//#line 187 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "+", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 46:
//#line 188 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "-", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 47:
//#line 189 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "*", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 48:
//#line 190 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "/", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 49:
//#line 191 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "%", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 50:
//#line 192 "../src/sintactico/sintactico.y"
{yyval = new AccesoArray((Expresion)val_peek(3), (Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 51:
//#line 193 "../src/sintactico/sintactico.y"
{yyval = new AccesoCampo((Expresion)val_peek(2), (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 52:
//#line 194 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 53:
//#line 195 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), ">", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 54:
//#line 196 "../src/sintactico/sintactico.y"
{yyval = val_peek(1); }
break;
case 55:
//#line 197 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<=", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 56:
//#line 198 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), ">=", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 57:
//#line 199 "../src/sintactico/sintactico.y"
{yyval = new Logica((Expresion)val_peek(2), "and", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 58:
//#line 200 "../src/sintactico/sintactico.y"
{yyval = new Logica((Expresion)val_peek(2), "or", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 59:
//#line 201 "../src/sintactico/sintactico.y"
{yyval = new Negacion((Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 60:
//#line 202 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "==", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 61:
//#line 203 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<>", (Expresion)val_peek(0), lexico.line(), lexico.column());}
break;
case 62:
//#line 204 "../src/sintactico/sintactico.y"
{yyval = new InvocacionFuncion((String)val_peek(3), (List<Expresion>)val_peek(1), lexico.line(), lexico.column());}
break;
case 63:
//#line 205 "../src/sintactico/sintactico.y"
{yyval = new Cast((Tipo)val_peek(3), (Expresion)val_peek(1), lexico.line(), lexico.column());}
break;
case 64:
//#line 209 "../src/sintactico/sintactico.y"
{yyval = TipoEntero.getInstancia();}
break;
case 65:
//#line 210 "../src/sintactico/sintactico.y"
{yyval = TipoReal.getInstancia();}
break;
case 66:
//#line 211 "../src/sintactico/sintactico.y"
{yyval = TipoChar.getInstancia();}
break;
case 67:
//#line 212 "../src/sintactico/sintactico.y"
{yyval = new TipoStruct((String)val_peek(0));}
break;
case 68:
//#line 216 "../src/sintactico/sintactico.y"
{List<Campo> lista = new ArrayList<Campo>();
												lista.add((Campo)val_peek(0));
												yyval = lista;}
break;
case 69:
//#line 219 "../src/sintactico/sintactico.y"
{List<Campo> lista = (List<Campo>) val_peek(1);
												lista.add((Campo)val_peek(0));
												yyval = lista;}
break;
case 70:
//#line 224 "../src/sintactico/sintactico.y"
{Campo campo = null; 
												if (((List<Integer>)val_peek(3)).size() == 0){
													campo = new Campo((Tipo)val_peek(1),(String)val_peek(4), lexico.line(), lexico.column());
												}
												else{
													campo = new Campo((Tipo)TipoArray.crearArray((Tipo)val_peek(1), (List<Integer>)val_peek(3)),(String)val_peek(4), lexico.line(), lexico.column());
												}
												yyval = campo;
												}
break;
//#line 1039 "Parser.java"
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
