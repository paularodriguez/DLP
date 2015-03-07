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
//#line 29 "Parser.java"




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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    7,    7,    8,
    8,    3,    9,    9,   11,   11,    4,    5,    6,   13,
   13,   15,   15,   16,   16,   14,   14,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   19,   19,   20,   20,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   10,   10,   10,   10,   12,   12,   21,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    1,    0,    1,    2,
    1,    6,    0,    1,    3,    4,    6,   12,   10,    0,
    1,    5,    3,    0,    1,    2,    1,    3,    3,    4,
    3,    2,    7,    7,    9,    5,    0,    1,    3,    1,
    1,    1,    1,    1,    3,    3,    3,    3,    3,    4,
    3,    3,    3,    3,    3,    3,    3,    2,    3,    3,
    4,    1,    1,    1,    1,    1,    2,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    4,    5,    6,
    7,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,   66,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   67,   15,   65,   62,   63,   64,    0,
    0,    0,    0,    0,    0,    0,    0,   12,   16,   23,
    0,    0,   11,    0,    0,    0,   17,    0,    0,    0,
   44,   43,   42,    0,    0,    0,    0,    0,    0,    0,
   27,    0,   10,   68,    0,   22,    0,    0,    0,    0,
    0,   32,    0,    0,    0,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,    0,
    0,   31,   29,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   51,
    0,    0,    0,    0,    0,    0,    0,   19,   30,   50,
    0,   36,    0,   61,    0,    0,    0,   18,    0,    0,
    0,   34,    0,   33,    0,   35,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   10,   11,   54,   55,   18,   40,
   19,   23,   29,  135,   30,  136,   71,   72,  106,  107,
   24,
};
final static short yysindex[] = {                      -158,
 -256, -245, -243, -227,    0, -158,    0,    0,    0,    0,
    0,  -60,   -1,   12, -200,    0, -204, -178,   18, -165,
 -165,  -60, -254,    0,   20, -111, -149, -146,   77,   84,
   83, -132, -136,    0,    0,    0,    0,    0,    0,   74,
   43, -111, -118, -102, -105, -111,   98,    0,    0,    0,
 -111,  -97,    0,  607, -105,  101,    0, -105, -111,  126,
    0,    0,    0, -231, -231, -231,    6, -231, -231,  363,
    0,   80,    0,    0,  607,    0, -231,  128,   92,  116,
   59,    0,  127,  155,  -44,  -99,    0, -231, -231, -231,
 -231, -231, -231, -231, -231, -231, -231, -231, -231, -231,
 -231, -231, -231,  400,  431,  136,  135, -231,    0,  607,
  607,    0,    0,  122,  249,  249,  571,  249,  571,  571,
  166,  249,  249,  138,  138,  -44,  -44,  -44,  282,    0,
  -83,  129, -231,  149,  607, -216,  517,    0,    0,    0,
  132,    0,  431,    0,  -76,  607,  -78,    0,  137,  -72,
  140,    0,  -65,    0,  146,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  206,    0,    0,    0,    0,
    0,  -52,    0,    0,    0,    0,    0,    0,  -46,  178,
  178,  -52,    0,    0,    0,    0,    0,    0,    0,  179,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  618,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  641,    0,    0,  618,    0,  376,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  180,  -37,    0,    0,
    0,    0,    0,    0,  -26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,    0,  181,  180,    0, -176,
    0,    0,    0,    0,  317,  456,  522,  467,  541,  560,
    0,  480,  491,  421,  443,    1,   27,   38,    0,    0,
    0,  410,    0,    0, -156,    0,    0,    0,    0,    0,
    0,    0,   49,    0,    0,  -43,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  217,  -18,    0,    0,    0,  169,    0,  208,    7,
    0,    0,  210,  -34,    0,   86,    8,  760,  133,    0,
  211,
};
final static int YYTABLESIZE=922;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   12,  103,   22,   41,   41,   41,   41,   41,   41,   41,
   58,   13,   33,   14,   58,   58,   58,   58,   58,   70,
   58,   41,   41,   41,   41,   78,   53,   61,   62,   15,
   17,   63,   58,   58,   58,   58,   73,   47,   20,   53,
  104,   47,   47,   47,   47,   47,  102,   47,   50,   69,
  145,   21,   56,   41,  146,   41,   22,   58,   25,   47,
   47,   47,   47,   48,   82,   76,   58,   48,   48,   48,
   48,   48,   40,   48,   49,   40,  137,   87,   49,   49,
   49,   49,   49,   26,   49,   48,   48,   48,   48,   39,
   24,   28,   39,   47,   24,  101,   49,   49,   49,   49,
   99,   97,    1,   98,  103,  100,    2,    3,   27,    4,
   25,   87,   35,   41,   25,   42,  101,   43,   95,   48,
   96,   99,   97,   45,   98,  103,  100,   44,  101,   46,
   49,   47,   48,   99,   97,   49,   98,  103,  100,   95,
   94,   96,   87,   51,   87,   36,   37,   38,   39,  102,
  109,   95,  101,   96,   52,    1,   57,   99,   97,   74,
   98,  103,  100,  101,   59,   77,  114,  108,   99,   97,
  102,   98,  103,  100,  101,   95,  132,   96,  133,   99,
  138,  141,  102,  103,  100,  112,   95,  142,   96,  144,
  148,  101,  149,  151,  153,  152,   99,   97,  154,   98,
  103,  100,  101,  155,  156,    1,  102,   99,   97,   13,
   98,  103,  100,  113,   95,   14,   96,  102,   20,   21,
   37,   38,   16,   24,  139,   95,   75,   96,  102,   32,
   31,  150,   41,   34,    0,   41,   41,   41,   41,    0,
  134,   41,   41,   58,   41,  102,   58,   58,   58,   58,
    0,    0,   58,   58,    0,   58,  102,    0,    0,    0,
    0,    0,   78,    0,   61,   62,    0,    0,   63,    0,
   47,    0,    0,   47,   47,   47,   47,    0,    0,   47,
   47,    0,   47,    0,    0,  101,   69,    0,    0,    0,
   99,   97,    0,   98,  103,  100,   48,    0,    0,   48,
   48,   48,   48,    0,    0,   48,   48,   49,   48,    0,
   49,   49,   49,   49,    0,    0,   49,   49,  101,   49,
    0,    0,    0,   99,   97,    0,   98,  103,  100,    0,
    0,   88,   89,   90,   91,    0,    0,   92,   93,  102,
  111,   95,    0,   96,    0,    0,    0,    0,    0,    0,
    0,    0,   88,   89,   90,   91,    0,   55,   92,   93,
   55,    0,    0,    0,   88,   89,   90,   91,    0,    0,
   92,   93,  102,    0,  140,   55,   55,   55,   55,    0,
    0,    0,    0,    0,    0,  110,    0,    0,   88,   89,
   90,   91,    0,    0,   92,   93,    0,    0,    0,   88,
   89,   90,   91,    0,    0,   92,   93,    0,    0,   55,
    0,    0,   41,    0,    0,    0,    0,   41,   41,    0,
   41,   41,   41,    0,    0,    0,    0,   88,   89,   90,
   91,    0,    0,   92,   93,   41,   41,   41,   88,   89,
   90,   91,    0,    0,   92,   93,   61,    0,    0,    0,
    0,   61,   61,    0,   61,   61,   61,    0,    0,    0,
    0,   45,    0,   45,   45,   45,   41,  101,    0,   61,
   61,   61,   99,   97,    0,   98,  103,  100,    0,   45,
   45,   45,   45,   46,    0,   46,   46,   46,    0,    0,
   95,    0,   96,    0,    0,    0,   54,    0,    0,   54,
   61,   46,   46,   46,   46,    0,    0,   59,    0,    0,
   59,    0,    0,   45,   54,   54,   54,   54,    0,    0,
   52,  102,    0,   52,    0,   59,   59,   59,   59,    0,
    0,   53,    0,    0,   53,   46,    0,    0,   52,   52,
   52,   52,    0,    0,    0,    0,    0,    0,   54,   53,
   53,   53,   53,    0,   88,   89,   90,   91,    0,   59,
   92,   93,   60,    0,    0,   60,    0,    0,    0,    0,
    0,    0,   52,    0,    0,    0,    0,    0,    0,    0,
   60,   56,   60,   53,   56,    0,   55,    0,    0,   55,
   55,   55,   55,    0,    0,   55,   55,    0,   55,   56,
   57,   56,    0,   57,    0,    0,    0,  101,    0,    0,
    0,    0,   99,   97,   60,   98,  103,  100,   57,   60,
   57,   61,   62,    0,    0,   63,   64,    0,    0,   86,
   95,   65,   96,   56,   66,    0,    0,    0,    0,   67,
   68,    0,    0,   69,    0,    0,    0,    0,   41,   41,
   41,   41,   57,    0,   41,   41,   60,    0,   61,   62,
    0,  102,   63,   64,    0,    0,  131,    0,   65,    0,
    0,   66,    0,    0,    0,    0,   67,   68,    0,    0,
   69,    0,   61,   61,   61,   61,    0,    0,   61,   61,
   45,    0,    0,   45,   45,   45,   45,    0,    0,   45,
   45,    0,   45,   88,   89,   90,   91,    0,    0,   92,
   93,    0,   46,    0,    0,   46,   46,   46,   46,    0,
    0,   46,   46,    0,   46,   54,    0,    0,   54,   54,
   54,   54,    0,    0,   54,   54,   59,   54,    0,   59,
   59,   59,   59,    0,    0,   59,   59,    0,   59,   52,
    0,    0,   52,   52,   52,   52,    0,    0,   52,   52,
   53,   52,    0,   53,   53,   53,   53,    0,    0,   53,
   53,    0,   53,   60,    0,   61,   62,    0,    0,   63,
   64,    0,    0,  147,    0,   65,    0,    0,   66,    0,
    0,   60,    0,   67,   68,    0,   60,   69,    0,    0,
   60,   60,    0,   60,    0,    0,    0,    0,    0,    0,
   56,    0,    0,    0,    0,   56,    0,    0,    0,   56,
   56,    0,   56,   79,   80,   81,   83,   84,   85,   57,
    0,    0,    0,    0,   57,    0,  105,    0,   57,   57,
    0,   57,    0,   88,   89,    0,   91,  115,  116,  117,
  118,  119,  120,  121,  122,  123,  124,  125,  126,  127,
  128,  129,  130,   60,    0,   61,   62,  105,    0,   63,
   64,    0,    0,    0,    8,   65,    8,    8,   66,    0,
    8,    8,    0,   67,   68,    0,    8,   69,    0,    8,
    0,    0,  143,    0,    8,    8,    0,    9,    8,    9,
    9,    0,    0,    9,    9,    0,    0,    0,    0,    9,
    0,    0,    9,    0,    0,    0,    0,    9,    9,    0,
    0,    9,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  257,   46,  257,   41,   42,   43,   44,   45,   46,   47,
   37,  257,  267,  257,   41,   42,   43,   44,   45,   54,
   47,   59,   60,   61,   62,  257,   45,  259,  260,  257,
   91,  263,   59,   60,   61,   62,   55,   37,   40,   58,
   75,   41,   42,   43,   44,   45,   91,   47,   42,  281,
  267,   40,   46,   91,  271,   93,  257,   51,  263,   59,
   60,   61,   62,   37,   59,   59,   93,   41,   42,   43,
   44,   45,   41,   47,   37,   44,  111,   70,   41,   42,
   43,   44,   45,  262,   47,   59,   60,   61,   62,   41,
  267,  257,   44,   93,  271,   37,   59,   60,   61,   62,
   42,   43,  261,   45,   46,   47,  265,  266,   91,  268,
  267,  104,   93,  263,  271,  262,   37,   41,   60,   93,
   62,   42,   43,   41,   45,   46,   47,   44,   37,  262,
   93,  268,   59,   42,   43,   93,   45,   46,   47,   60,
   61,   62,  135,  262,  137,  257,  258,  259,  260,   91,
   59,   60,   37,   62,  257,  261,   59,   42,   43,   59,
   45,   46,   47,   37,  262,   40,  266,   40,   42,   43,
   91,   45,   46,   47,   37,   60,   41,   62,   44,   42,
   59,  265,   91,   46,   47,   59,   60,   59,   62,   41,
   59,   37,  269,  272,  267,   59,   42,   43,   59,   45,
   46,   47,   37,  269,   59,    0,   91,   42,   43,  262,
   45,   46,   47,   59,   60,  262,   62,   91,   41,   41,
   41,   41,    6,  267,   59,   60,   58,   62,   91,   22,
   21,  146,  270,   23,   -1,  273,  274,  275,  276,   -1,
  108,  279,  280,  270,  282,   91,  273,  274,  275,  276,
   -1,   -1,  279,  280,   -1,  282,   91,   -1,   -1,   -1,
   -1,   -1,  257,   -1,  259,  260,   -1,   -1,  263,   -1,
  270,   -1,   -1,  273,  274,  275,  276,   -1,   -1,  279,
  280,   -1,  282,   -1,   -1,   37,  281,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,  270,   -1,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,  270,  282,   -1,
  273,  274,  275,  276,   -1,   -1,  279,  280,   37,  282,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  273,  274,  275,  276,   -1,   -1,  279,  280,   91,
  282,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,   41,  279,  280,
   44,   -1,   -1,   -1,  273,  274,  275,  276,   -1,   -1,
  279,  280,   91,   -1,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,  270,   -1,   -1,  273,  274,
  275,  276,   -1,   -1,  279,  280,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,   -1,   -1,   93,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,   -1,  279,  280,   60,   61,   62,  273,  274,
  275,  276,   -1,   -1,  279,  280,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   41,   -1,   43,   44,   45,   91,   37,   -1,   60,
   61,   62,   42,   43,   -1,   45,   46,   47,   -1,   59,
   60,   61,   62,   41,   -1,   43,   44,   45,   -1,   -1,
   60,   -1,   62,   -1,   -1,   -1,   41,   -1,   -1,   44,
   91,   59,   60,   61,   62,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   93,   59,   60,   61,   62,   -1,   -1,
   41,   91,   -1,   44,   -1,   59,   60,   61,   62,   -1,
   -1,   41,   -1,   -1,   44,   93,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   -1,  273,  274,  275,  276,   -1,   93,
  279,  280,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   41,   61,   93,   44,   -1,  270,   -1,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,   -1,  282,   59,
   41,   61,   -1,   44,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   93,   45,   46,   47,   59,  257,
   61,  259,  260,   -1,   -1,  263,  264,   -1,   -1,  267,
   60,  269,   62,   93,  272,   -1,   -1,   -1,   -1,  277,
  278,   -1,   -1,  281,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,   93,   -1,  279,  280,  257,   -1,  259,  260,
   -1,   91,  263,  264,   -1,   -1,  267,   -1,  269,   -1,
   -1,  272,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,
  281,   -1,  273,  274,  275,  276,   -1,   -1,  279,  280,
  270,   -1,   -1,  273,  274,  275,  276,   -1,   -1,  279,
  280,   -1,  282,  273,  274,  275,  276,   -1,   -1,  279,
  280,   -1,  270,   -1,   -1,  273,  274,  275,  276,   -1,
   -1,  279,  280,   -1,  282,  270,   -1,   -1,  273,  274,
  275,  276,   -1,   -1,  279,  280,  270,  282,   -1,  273,
  274,  275,  276,   -1,   -1,  279,  280,   -1,  282,  270,
   -1,   -1,  273,  274,  275,  276,   -1,   -1,  279,  280,
  270,  282,   -1,  273,  274,  275,  276,   -1,   -1,  279,
  280,   -1,  282,  257,   -1,  259,  260,   -1,   -1,  263,
  264,   -1,   -1,  267,   -1,  269,   -1,   -1,  272,   -1,
   -1,  270,   -1,  277,  278,   -1,  275,  281,   -1,   -1,
  279,  280,   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,
  270,   -1,   -1,   -1,   -1,  275,   -1,   -1,   -1,  279,
  280,   -1,  282,   64,   65,   66,   67,   68,   69,  270,
   -1,   -1,   -1,   -1,  275,   -1,   77,   -1,  279,  280,
   -1,  282,   -1,  273,  274,   -1,  276,   88,   89,   90,
   91,   92,   93,   94,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  257,   -1,  259,  260,  108,   -1,  263,
  264,   -1,   -1,   -1,  257,  269,  259,  260,  272,   -1,
  263,  264,   -1,  277,  278,   -1,  269,  281,   -1,  272,
   -1,   -1,  133,   -1,  277,  278,   -1,  257,  281,  259,
  260,   -1,   -1,  263,  264,   -1,   -1,   -1,   -1,  269,
   -1,   -1,  272,   -1,   -1,   -1,   -1,  277,  278,   -1,
   -1,  281,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=282;
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
"AND","OR","NOT","DO",
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
"definicion_funcion : FUNCTION IDENT '(' listaParametrosOpcional ')' AS tipo definiciones_variable_opc sentencias END FUNCTION ';'",
"definicion_procedimiento : PROC IDENT '(' listaParametrosOpcional ')' definiciones_variable_opc sentencias END PROC ';'",
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
"sentencia : WHILE expresion DO sentencias END WHILE ';'",
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
"expresion : expresion MENORIGUAL expresion",
"expresion : expresion MAYORIGUAL expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : NOT expresion",
"expresion : expresion IGUALDAD expresion",
"expresion : expresion DISTINTO expresion",
"expresion : IDENT '(' expresiones_opc ')'",
"tipo : INTEGER",
"tipo : REAL",
"tipo : CHARACTER",
"tipo : IDENT",
"lista_campos : campo",
"lista_campos : lista_campos campo",
"campo : IDENT array_opc AS tipo ';'",
};

//#line 217 "../src/sintactico/sintactico.y"

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
//#line 541 "Parser.java"
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
//#line 57 "../src/sintactico/sintactico.y"
{this.ast = new Programa((List<Definicion>)val_peek(0));}
break;
case 2:
//#line 61 "../src/sintactico/sintactico.y"
{List<Definicion> lista = new ArrayList<Definicion>();
												lista.add((Definicion)val_peek(0));
												yyval = lista;}
break;
case 3:
//#line 64 "../src/sintactico/sintactico.y"
{List<Definicion> lista = (List<Definicion>) val_peek(1);
												lista.add((Definicion) val_peek(0));
												yyval = lista;}
break;
case 4:
//#line 70 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 71 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 72 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 73 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 8:
//#line 77 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<DefinicionVariable>();}
break;
case 9:
//#line 78 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 81 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = (List<DefinicionVariable>) val_peek(1);
																lista.add((DefinicionVariable)val_peek(0));
																yyval = lista;}
break;
case 11:
//#line 84 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
																lista.add((DefinicionVariable)val_peek(0));
																yyval = lista;}
break;
case 12:
//#line 90 "../src/sintactico/sintactico.y"
{yyval = new DefinicionVariable((Tipo)val_peek(1), (List<Integer>)val_peek(3), (String)val_peek(4));}
break;
case 13:
//#line 94 "../src/sintactico/sintactico.y"
{yyval = null;}
break;
case 14:
//#line 95 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 15:
//#line 99 "../src/sintactico/sintactico.y"
{List<Integer> lista = new ArrayList<Integer>();
												lista.add(Integer.parseInt((String)val_peek(1)));
												yyval = lista;}
break;
case 16:
//#line 102 "../src/sintactico/sintactico.y"
{List<Integer> lista = (List<Integer>) val_peek(3);
												lista.add(Integer.parseInt((String)val_peek(1)));
												yyval = lista;}
break;
case 17:
//#line 108 "../src/sintactico/sintactico.y"
{yyval = new DefinicionStruct((String)val_peek(4), (List<Campo>)val_peek(3));}
break;
case 18:
//#line 112 "../src/sintactico/sintactico.y"
{TipoFuncion tipoFuncion = new TipoFuncion((Tipo)val_peek(5), (String)val_peek(10), (List<DefinicionVariable>)val_peek(8));
																																yyval = new DefinicionFuncion(tipoFuncion, (List<DefinicionVariable>)val_peek(4), (List<Sentencia>)val_peek(3));}
break;
case 19:
//#line 117 "../src/sintactico/sintactico.y"
{yyval = new DefinicionProcedimiento((String)val_peek(8), (List<DefinicionVariable>)val_peek(6), (List<DefinicionVariable>)val_peek(4), (List<Sentencia>)val_peek(3));}
break;
case 20:
//#line 120 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<DefinicionVariable>();}
break;
case 21:
//#line 121 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 22:
//#line 125 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = (List<DefinicionVariable>) val_peek(4);
												lista.add(new DefinicionVariable((Tipo)val_peek(0),null,(String)val_peek(2)));
												yyval = lista;}
break;
case 23:
//#line 128 "../src/sintactico/sintactico.y"
{List<DefinicionVariable> lista = new ArrayList<DefinicionVariable>();
												lista.add(new DefinicionVariable((Tipo)val_peek(0),null,(String)val_peek(2)));
												yyval = lista;}
break;
case 24:
//#line 134 "../src/sintactico/sintactico.y"
{yyval = new ArrayList<Sentencia>();}
break;
case 25:
//#line 135 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 139 "../src/sintactico/sintactico.y"
{List<Sentencia> lista = (List<Sentencia>) val_peek(1);
												lista.add((Sentencia)val_peek(0));
												yyval = lista;}
break;
case 27:
//#line 142 "../src/sintactico/sintactico.y"
{List<Sentencia> lista = new ArrayList<Sentencia>();
												lista.add((Sentencia)val_peek(0));
												yyval = lista;}
break;
case 28:
//#line 148 "../src/sintactico/sintactico.y"
{yyval = new Print((Expresion)val_peek(1));}
break;
case 29:
//#line 149 "../src/sintactico/sintactico.y"
{yyval = new Read((Expresion)val_peek(1));}
break;
case 30:
//#line 150 "../src/sintactico/sintactico.y"
{yyval = new Asignacion((Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 31:
//#line 151 "../src/sintactico/sintactico.y"
{yyval = new Return((Expresion)val_peek(1));}
break;
case 32:
//#line 152 "../src/sintactico/sintactico.y"
{yyval = new Return(null);}
break;
case 33:
//#line 153 "../src/sintactico/sintactico.y"
{yyval = new While((Expresion)val_peek(5), (List<Sentencia>)val_peek(3));}
break;
case 34:
//#line 154 "../src/sintactico/sintactico.y"
{yyval = new IF((Expresion)val_peek(5), (List<Sentencia>)val_peek(3));}
break;
case 35:
//#line 155 "../src/sintactico/sintactico.y"
{yyval = new IF((Expresion)val_peek(7), (List<Sentencia>)val_peek(5), (List<Sentencia>)val_peek(3));}
break;
case 36:
//#line 156 "../src/sintactico/sintactico.y"
{yyval = new InvocacionProcedimiento((String)val_peek(4), (List<Expresion>)val_peek(2));}
break;
case 39:
//#line 165 "../src/sintactico/sintactico.y"
{List<Expresion> lista = (List<Expresion>) val_peek(2);
												lista.add((Expresion)val_peek(0));
												yyval = lista;}
break;
case 40:
//#line 168 "../src/sintactico/sintactico.y"
{List<Expresion> lista = new ArrayList<Expresion>();
												lista.add((Expresion)val_peek(0));
												yyval = lista;}
break;
case 41:
//#line 174 "../src/sintactico/sintactico.y"
{yyval = new Variable((String)val_peek(0));}
break;
case 42:
//#line 175 "../src/sintactico/sintactico.y"
{yyval = new LiteralEntero(Integer.parseInt((String)val_peek(0)));}
break;
case 43:
//#line 176 "../src/sintactico/sintactico.y"
{yyval = new LiteralCaracter((Character)val_peek(0));}
break;
case 44:
//#line 177 "../src/sintactico/sintactico.y"
{yyval = new LiteralReal(Double.parseDouble((String)val_peek(0)));}
break;
case 45:
//#line 178 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "+", (Expresion)val_peek(0));}
break;
case 46:
//#line 179 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "-", (Expresion)val_peek(0));}
break;
case 47:
//#line 180 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "*", (Expresion)val_peek(0));}
break;
case 48:
//#line 181 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "/", (Expresion)val_peek(0));}
break;
case 49:
//#line 182 "../src/sintactico/sintactico.y"
{yyval = new Aritmetica((Expresion)val_peek(2), "%", (Expresion)val_peek(0));}
break;
case 50:
//#line 183 "../src/sintactico/sintactico.y"
{yyval = new AccesoArray((Expresion)val_peek(3), (Expresion)val_peek(1));}
break;
case 51:
//#line 184 "../src/sintactico/sintactico.y"
{yyval = new AccesoCampo((Expresion)val_peek(2), (Expresion)val_peek(0));}
break;
case 52:
//#line 185 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<", (Expresion)val_peek(0));}
break;
case 53:
//#line 186 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), ">", (Expresion)val_peek(0));}
break;
case 54:
//#line 187 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<=", (Expresion)val_peek(0));}
break;
case 55:
//#line 188 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), ">=", (Expresion)val_peek(0));}
break;
case 56:
//#line 189 "../src/sintactico/sintactico.y"
{yyval = new Logica((Expresion)val_peek(2), "and", (Expresion)val_peek(0));}
break;
case 57:
//#line 190 "../src/sintactico/sintactico.y"
{yyval = new Logica((Expresion)val_peek(2), "or", (Expresion)val_peek(0));}
break;
case 58:
//#line 191 "../src/sintactico/sintactico.y"
{yyval = new Negacion((Expresion)val_peek(0));}
break;
case 59:
//#line 192 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "==", (Expresion)val_peek(0));}
break;
case 60:
//#line 193 "../src/sintactico/sintactico.y"
{yyval = new Comparacion((Expresion)val_peek(2), "<>", (Expresion)val_peek(0));}
break;
case 61:
//#line 194 "../src/sintactico/sintactico.y"
{yyval = new InvocacionFuncion((String)val_peek(3), (List<Expresion>)val_peek(1));}
break;
case 62:
//#line 198 "../src/sintactico/sintactico.y"
{yyval = TipoEntero.getInstancia();}
break;
case 63:
//#line 199 "../src/sintactico/sintactico.y"
{yyval = TipoReal.getInstancia();}
break;
case 64:
//#line 200 "../src/sintactico/sintactico.y"
{yyval = TipoChar.getInstancia();}
break;
case 65:
//#line 201 "../src/sintactico/sintactico.y"
{yyval = new TipoStruct((String)val_peek(0));}
break;
case 66:
//#line 205 "../src/sintactico/sintactico.y"
{List<Campo> lista = new ArrayList<Campo>();
												lista.add((Campo)val_peek(0));
												yyval = lista;}
break;
case 67:
//#line 208 "../src/sintactico/sintactico.y"
{List<Campo> lista = (List<Campo>) val_peek(1);
												lista.add((Campo)val_peek(0));
												yyval = lista;}
break;
case 68:
//#line 213 "../src/sintactico/sintactico.y"
{yyval = new Campo((String)val_peek(4), (Tipo)val_peek(1));}
break;
//#line 983 "Parser.java"
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
