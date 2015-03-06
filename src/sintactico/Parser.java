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
    4,    1,    1,    1,    1,    0,    2,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    4,    5,    6,
    7,    0,    0,    0,   66,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,   15,   65,   62,   63,   64,    0,    0,    0,
    0,    0,    0,    0,    0,   12,   16,   23,    0,    0,
   11,    0,    0,    0,   17,    0,    0,    0,   44,   43,
   42,    0,    0,    0,    0,    0,    0,    0,   27,    0,
   10,    0,    0,   22,    0,    0,    0,    0,    0,   32,
    0,    0,    0,    0,   26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   68,    0,    0,    0,    0,    0,   28,    0,    0,
   31,   29,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   19,   50,   30,    0,
   36,    0,   61,    0,    0,    0,   18,    0,    0,    0,
   34,    0,   33,    0,   35,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   10,   11,   52,   53,   18,   38,
   19,   22,   27,  134,   28,  135,   69,   70,  105,  106,
   32,
};
final static short yysindex[] = {                      -219,
 -256, -254, -244, -243,    0, -219,    0,    0,    0,    0,
    0,  -76,  -10,   -9,    0,    0, -225, -223,  -29, -193,
 -193, -255,  -19, -108, -195, -187,   49,   47,   51,  -76,
 -170,    0,    0,    0,    0,    0,    0,   42,   21, -108,
 -138, -128, -127, -126,   81,    0,    0,    0, -108, -116,
    0,  458, -127, -108,    0, -127, -108,  113,    0,    0,
    0, -231, -231, -231,  -41, -231, -231,  316,    0,   -2,
    0,   95,  458,    0, -231,  119,   24,   35,  -26,    0,
   57,   85,  282, -104,    0, -231, -231, -231, -231, -231,
 -231, -231, -231, -231, -231, -231, -231, -231, -231, -231,
 -231,    0,  332,  282,  127,  125, -231,    0,  458,  458,
    0,    0,  112,  282,  282,  282,  282,  282,  282,  164,
  164,   75,   75,   96,  118,  282,  282,  282,  282,  -93,
  114, -231,  133,  458, -234,  442,    0,    0,    0,  116,
    0,  282,    0,  -90,  458,  -91,    0,  123,  -84,  129,
    0,  -85,    0,  132,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  185,    0,    0,    0,    0,
    0,  -70,    0,    0,    0,    0,    0,    0,  -69,  145,
  145,    0,    0,    0,    0,    0,    0,  156,    0,  -70,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  505,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  516,    0,    0,  505,    0,  153,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  161,  -37,    0,    0,    0,    0,
    0,    0,  410,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   69,    0,  162,  161,    0, -216,    0,
    0,    0,    0,  411,  415,  416,  421,  446,  471,  360,
  405,   64,  341,    0,    0,  472,  476,  477,  481,    0,
  221,    0,    0, -214,    0,    0,    0,    0,    0,    0,
    0,  126,    0,    0,  -73,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  198,   20,    0,    0,    0,  149,    0,  177,   39,
    0,    0,  187,  -25,    0,   67,  -16,  562,  110,    0,
    0,
};
final static int YYTABLESIZE=797;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   12,   30,   13,   41,   41,   41,   41,   41,   41,   41,
   98,   31,   14,   15,   17,   94,   92,   80,   93,   99,
   95,   41,   41,   41,   41,   76,   68,   59,   60,   20,
   21,   61,  144,  100,   98,  101,  145,   23,   24,   94,
   92,    1,   93,   99,   95,    2,    3,  103,    4,   67,
   24,   85,   25,   41,   24,   41,   25,  100,   97,  101,
   98,   25,   51,   26,   96,   94,   92,   39,   93,   99,
   95,   98,   71,   33,   40,   51,   94,   92,   48,   93,
   99,   95,  108,  100,  136,  101,   85,   56,   96,   41,
   42,   43,   72,   98,  100,   74,  101,   45,   94,   92,
   46,   93,   99,   95,   47,   47,   47,   47,   47,   40,
   47,   98,   40,   47,   96,  111,  100,   85,  101,   85,
   99,   98,   47,   49,   47,   96,   94,   92,   50,   93,
   99,   95,   98,    1,  100,   54,  101,   94,   92,   55,
   93,   99,   95,  112,  100,   57,  101,   96,   34,   35,
   36,   37,   75,  102,   98,  100,   47,  101,  107,   94,
   92,  113,   93,   99,   95,   96,   39,  131,  132,   39,
  137,  140,  141,  143,  147,   96,  139,  100,  148,  101,
  150,  151,  152,  154,    1,   20,   96,  153,  138,   41,
  155,   13,   14,   24,   41,   41,   21,   41,   41,   41,
   98,   37,   38,   16,   73,   94,   44,   29,   96,   99,
   95,  149,   41,   41,   41,   76,  133,   59,   60,    0,
    0,   61,    0,  100,    0,  101,    0,    0,    0,    0,
    0,    0,   41,    0,    0,   41,   41,   41,   41,   67,
    0,   41,   41,   41,   41,    0,   86,   87,   88,   89,
    0,    0,   90,   91,   96,  110,    0,   61,    0,    0,
    0,    0,   61,   61,    0,   61,   61,   61,    0,    0,
   86,   87,   88,   89,    0,    0,   90,   91,    0,    0,
   61,   61,   61,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   86,   87,   88,   89,
    0,    0,   90,   91,  109,    0,    0,   86,   87,   88,
   89,   61,    0,   90,   91,    0,    0,    0,   98,    0,
    0,    0,    0,   94,   92,    0,   93,   99,   95,   86,
   87,   88,   89,   47,    0,   90,   91,    0,    0,    0,
    0,  100,    0,  101,    0,   47,    0,   86,   87,   88,
   89,    0,    0,   90,   91,    0,    0,   86,   87,   88,
   89,    0,    0,   90,   91,    0,    0,    0,   86,   87,
   88,   89,   96,    0,   90,   91,    0,    0,    0,    0,
    0,   48,   48,   48,   48,   48,    0,   48,    0,    0,
   86,   87,   88,   89,    0,    0,   90,   91,    0,   48,
   45,   48,   45,   45,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
   45,    0,    0,    0,    0,   41,   41,   41,   41,    0,
    0,   41,   41,   48,    0,    0,   86,   87,   88,   89,
    0,    0,   90,   91,    0,   46,    0,   46,   46,   46,
   58,   55,   45,   58,   55,   54,   60,    0,   54,   60,
    0,   59,    0,   46,   59,   46,    0,    0,   58,   55,
   58,   55,    0,   54,   60,   54,   60,    0,    0,   59,
    0,   59,    0,    0,    0,    0,   56,    0,    0,   56,
    0,    0,    0,   61,   61,   61,   61,   46,    0,   61,
   61,    0,   58,   55,   56,    0,   56,   54,   60,    0,
    0,   57,   49,   59,   57,   49,   51,   52,    0,   51,
   52,   53,    0,    0,   53,    0,    0,    0,    0,   57,
   49,   57,   49,    0,   51,   52,   51,   52,   56,   53,
    0,   53,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   86,   87,   88,   89,    0,    0,
   90,   91,    0,   57,   49,    0,    0,    0,   51,   52,
    0,    0,   58,   53,   59,   60,    0,    0,   61,   62,
    0,    0,   84,    0,   63,    0,    0,   64,   58,    0,
   59,   60,   65,   66,   61,   62,   67,    0,  130,    0,
   63,    0,    0,   64,    0,    0,    0,    0,   65,   66,
   48,    0,   67,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   48,   77,   78,   79,   81,   82,   83,   45,
    0,    0,    0,    0,    0,    0,  104,    0,    0,    0,
    0,   45,    0,    0,    0,    0,    0,  114,  115,  116,
  117,  118,  119,  120,  121,  122,  123,  124,  125,  126,
  127,  128,  129,    0,    0,    0,    0,    0,  104,    0,
    0,    0,    0,    0,   46,    0,    0,    0,    0,   58,
   55,    0,    0,    0,   54,   60,   46,    0,    0,    0,
   59,   58,   55,  142,    0,    0,   54,   60,   58,    0,
   59,   60,   59,    0,   61,   62,    0,    0,  146,    0,
   63,    0,    0,   64,   58,   56,   59,   60,   65,   66,
   61,   62,   67,    0,    0,    0,   63,   56,    0,   64,
    0,    0,    0,    0,   65,   66,    0,    0,   67,    0,
   57,   49,    0,    0,    0,   51,   52,    0,    0,    0,
   53,    0,   57,   49,    0,    0,    0,   51,   52,    0,
    0,    8,   53,    8,    8,    0,    0,    8,    8,    0,
    0,    0,    9,    8,    9,    9,    8,    0,    9,    9,
    0,    8,    8,    0,    9,    8,    0,    9,    0,    0,
    0,    0,    9,    9,    0,    0,    9,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  257,  257,  257,   41,   42,   43,   44,   45,   46,   47,
   37,  267,  257,  257,   91,   42,   43,   59,   45,   46,
   47,   59,   60,   61,   62,  257,   52,  259,  260,   40,
   40,  263,  267,   60,   37,   62,  271,  263,  262,   42,
   43,  261,   45,   46,   47,  265,  266,   73,  268,  281,
  267,   68,  267,   91,  271,   93,  271,   60,   61,   62,
   37,   91,   43,  257,   91,   42,   43,  263,   45,   46,
   47,   37,   53,   93,  262,   56,   42,   43,   40,   45,
   46,   47,   59,   60,  110,   62,  103,   49,   91,   41,
   44,   41,   54,   37,   60,   57,   62,  268,   42,   43,
   59,   45,   46,   47,   41,   42,   43,   44,   45,   41,
   47,   37,   44,   93,   91,   59,   60,  134,   62,  136,
   46,   37,   59,  262,   61,   91,   42,   43,  257,   45,
   46,   47,   37,  261,   60,  262,   62,   42,   43,   59,
   45,   46,   47,   59,   60,  262,   62,   91,  257,  258,
  259,  260,   40,   59,   37,   60,   93,   62,   40,   42,
   43,  266,   45,   46,   47,   91,   41,   41,   44,   44,
   59,  265,   59,   41,   59,   91,   59,   60,  269,   62,
  272,   59,  267,  269,    0,   41,   91,   59,   93,   37,
   59,  262,  262,  267,   42,   43,   41,   45,   46,   47,
   37,   41,   41,    6,   56,   42,   30,   21,   91,   46,
   47,  145,   60,   61,   62,  257,  107,  259,  260,   -1,
   -1,  263,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,  270,   -1,   -1,  273,  274,  275,  276,  281,
   -1,  279,  280,   91,  282,   -1,  273,  274,  275,  276,
   -1,   -1,  279,  280,   91,  282,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
  273,  274,  275,  276,   -1,   -1,  279,  280,   -1,   -1,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
   -1,   -1,  279,  280,  270,   -1,   -1,  273,  274,  275,
  276,   91,   -1,  279,  280,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,  273,
  274,  275,  276,  270,   -1,  279,  280,   -1,   -1,   -1,
   -1,   60,   -1,   62,   -1,  282,   -1,  273,  274,  275,
  276,   -1,   -1,  279,  280,   -1,   -1,  273,  274,  275,
  276,   -1,   -1,  279,  280,   -1,   -1,   -1,  273,  274,
  275,  276,   91,   -1,  279,  280,   -1,   -1,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
  273,  274,  275,  276,   -1,   -1,  279,  280,   -1,   59,
   41,   61,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   -1,
   61,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
   -1,  279,  280,   93,   -1,   -1,  273,  274,  275,  276,
   -1,   -1,  279,  280,   -1,   41,   -1,   43,   44,   45,
   41,   41,   93,   44,   44,   41,   41,   -1,   44,   44,
   -1,   41,   -1,   59,   44,   61,   -1,   -1,   59,   59,
   61,   61,   -1,   59,   59,   61,   61,   -1,   -1,   59,
   -1,   61,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,  273,  274,  275,  276,   93,   -1,  279,
  280,   -1,   93,   93,   59,   -1,   61,   93,   93,   -1,
   -1,   41,   41,   93,   44,   44,   41,   41,   -1,   44,
   44,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   59,
   59,   61,   61,   -1,   59,   59,   61,   61,   93,   59,
   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,   -1,
  279,  280,   -1,   93,   93,   -1,   -1,   -1,   93,   93,
   -1,   -1,  257,   93,  259,  260,   -1,   -1,  263,  264,
   -1,   -1,  267,   -1,  269,   -1,   -1,  272,  257,   -1,
  259,  260,  277,  278,  263,  264,  281,   -1,  267,   -1,
  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,  277,  278,
  270,   -1,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  282,   62,   63,   64,   65,   66,   67,  270,
   -1,   -1,   -1,   -1,   -1,   -1,   75,   -1,   -1,   -1,
   -1,  282,   -1,   -1,   -1,   -1,   -1,   86,   87,   88,
   89,   90,   91,   92,   93,   94,   95,   96,   97,   98,
   99,  100,  101,   -1,   -1,   -1,   -1,   -1,  107,   -1,
   -1,   -1,   -1,   -1,  270,   -1,   -1,   -1,   -1,  270,
  270,   -1,   -1,   -1,  270,  270,  282,   -1,   -1,   -1,
  270,  282,  282,  132,   -1,   -1,  282,  282,  257,   -1,
  259,  260,  282,   -1,  263,  264,   -1,   -1,  267,   -1,
  269,   -1,   -1,  272,  257,  270,  259,  260,  277,  278,
  263,  264,  281,   -1,   -1,   -1,  269,  282,   -1,  272,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,   -1,
  270,  270,   -1,   -1,   -1,  270,  270,   -1,   -1,   -1,
  270,   -1,  282,  282,   -1,   -1,   -1,  282,  282,   -1,
   -1,  257,  282,  259,  260,   -1,   -1,  263,  264,   -1,
   -1,   -1,  257,  269,  259,  260,  272,   -1,  263,  264,
   -1,  277,  278,   -1,  269,  281,   -1,  272,   -1,   -1,
   -1,   -1,  277,  278,   -1,   -1,  281,
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
"lista_campos :",
"lista_campos : lista_campos campo",
"campo : IDENT array_opc AS tipo ';'",
};

//#line 181 "../src/sintactico/sintactico.y"

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
//#line 515 "Parser.java"
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
case 4:
//#line 59 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 60 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 61 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 62 "../src/sintactico/sintactico.y"
{yyval = val_peek(0);}
break;
case 62:
//#line 166 "../src/sintactico/sintactico.y"
{yyval = TipoEntero.getInstancia();}
break;
case 63:
//#line 167 "../src/sintactico/sintactico.y"
{yyval = TipoReal.getInstancia();}
break;
case 64:
//#line 168 "../src/sintactico/sintactico.y"
{yyval = TipoChar.getInstancia();}
break;
//#line 692 "Parser.java"
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
