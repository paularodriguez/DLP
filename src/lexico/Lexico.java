/* The following code was generated by JFlex 1.4.1 on 9/05/15 0:09 */

// ************  C�digo a incluir ********************

package lexico;
import sintactico.Parser;
import gestorErrores.GestorErrores;
import java.io.Reader;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 9/05/15 0:09 from the specification file
 * <tt>lexico/lexico.l</tt>
 */
public class Lexico {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\42\1\4\2\0\1\42\22\0\1\42\1\35\3\0\1\35"+
    "\1\0\1\36\1\35\1\35\1\5\1\10\1\35\1\11\1\6\1\3"+
    "\12\1\1\0\1\35\1\37\1\40\1\41\2\0\4\2\1\7\25\2"+
    "\1\35\1\0\1\35\1\0\1\2\1\0\1\22\1\2\1\24\1\12"+
    "\1\17\1\33\1\20\1\25\1\13\2\2\1\23\1\14\1\15\1\30"+
    "\1\27\1\2\1\21\1\26\1\16\1\34\1\2\1\32\1\2\1\31"+
    "\1\2\1\35\1\0\1\35\103\0\1\2\7\0\1\2\3\0\1\2"+
    "\3\0\1\2\1\0\1\2\6\0\1\2\6\0\1\2\7\0\1\2"+
    "\3\0\1\2\3\0\1\2\1\0\1\2\6\0\1\2\uff05\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\2\4\14\3"+
    "\1\1\3\4\1\2\4\0\1\6\1\3\1\7\1\3"+
    "\1\10\7\3\1\11\3\3\1\12\2\3\1\0\1\13"+
    "\1\14\1\15\1\16\1\6\3\0\1\17\1\3\1\20"+
    "\2\3\1\21\3\3\1\22\6\3\1\23\1\3\1\24"+
    "\1\25\1\26\1\3\1\27\1\30\3\3\1\31\4\3"+
    "\1\32\1\3\1\33\1\34\2\3\1\35\2\3\1\36"+
    "\3\3\1\37\1\40";

  private static int [] zzUnpackAction() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\106\0\151\0\214\0\43\0\43\0\257"+
    "\0\322\0\365\0\u0118\0\u013b\0\u015e\0\u0181\0\u01a4\0\u01c7"+
    "\0\u01ea\0\u020d\0\u0230\0\u0253\0\u0276\0\u0299\0\u02bc\0\u02df"+
    "\0\u0302\0\u0325\0\u0348\0\u036b\0\u038e\0\257\0\u03b1\0\151"+
    "\0\u03d4\0\151\0\u03f7\0\u041a\0\u043d\0\u0460\0\u0483\0\u04a6"+
    "\0\u04c9\0\151\0\u04ec\0\u050f\0\u0532\0\151\0\u0555\0\u0578"+
    "\0\u059b\0\43\0\43\0\43\0\43\0\u0325\0\u05be\0\257"+
    "\0\u05e1\0\151\0\u0604\0\151\0\u0627\0\u064a\0\151\0\u066d"+
    "\0\u0690\0\u06b3\0\151\0\u06d6\0\u06f9\0\u071c\0\u073f\0\u0762"+
    "\0\u0785\0\43\0\u07a8\0\151\0\151\0\151\0\u07cb\0\151"+
    "\0\151\0\u07ee\0\u0811\0\u0834\0\151\0\u0857\0\u087a\0\u089d"+
    "\0\u08c0\0\151\0\u08e3\0\151\0\151\0\u0906\0\u0929\0\151"+
    "\0\u094c\0\u096f\0\151\0\u0992\0\u09b5\0\u09d8\0\151\0\151";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\4"+
    "\2\7\1\11\1\12\1\4\1\13\1\14\1\15\1\4"+
    "\1\16\1\17\1\4\1\20\2\4\1\21\1\22\1\4"+
    "\1\23\1\24\1\4\1\7\1\25\1\26\1\27\1\30"+
    "\1\6\44\0\1\31\4\0\1\32\1\33\7\0\1\33"+
    "\24\0\2\4\4\0\1\4\2\0\23\4\11\0\1\34"+
    "\1\0\1\35\36\0\1\36\42\0\2\4\4\0\1\4"+
    "\2\0\1\4\1\37\14\4\1\40\4\4\7\0\2\4"+
    "\4\0\1\4\2\0\3\4\1\41\15\4\1\42\1\4"+
    "\7\0\2\4\4\0\1\4\2\0\16\4\1\43\4\4"+
    "\7\0\2\4\4\0\1\4\2\0\13\4\1\44\3\4"+
    "\1\45\3\4\7\0\2\4\4\0\1\4\2\0\3\4"+
    "\1\46\5\4\1\47\11\4\7\0\2\4\4\0\1\4"+
    "\2\0\5\4\1\50\15\4\7\0\2\4\4\0\1\4"+
    "\2\0\3\4\1\51\10\4\1\52\6\4\7\0\2\4"+
    "\4\0\1\4\2\0\4\4\1\53\6\4\1\54\7\4"+
    "\7\0\2\4\4\0\1\4\2\0\7\4\1\55\13\4"+
    "\7\0\2\4\4\0\1\4\2\0\7\4\1\56\13\4"+
    "\7\0\2\4\4\0\1\4\2\0\13\4\1\57\7\4"+
    "\7\0\2\4\4\0\1\4\2\0\22\4\1\60\6\0"+
    "\36\61\1\0\4\61\40\0\1\62\1\63\41\0\1\64"+
    "\42\0\1\65\3\0\1\31\4\0\1\32\35\0\1\66"+
    "\5\0\1\67\7\0\1\67\24\0\1\36\6\0\2\70"+
    "\31\0\4\34\1\6\36\34\5\35\1\71\35\35\1\0"+
    "\2\4\4\0\1\4\2\0\2\4\1\72\20\4\7\0"+
    "\2\4\4\0\1\4\2\0\4\4\1\73\16\4\7\0"+
    "\2\4\4\0\1\4\2\0\4\4\1\74\16\4\7\0"+
    "\2\4\4\0\1\4\2\0\5\4\1\75\15\4\7\0"+
    "\2\4\4\0\1\4\2\0\15\4\1\76\5\4\7\0"+
    "\2\4\4\0\1\4\2\0\1\77\22\4\7\0\2\4"+
    "\4\0\1\4\2\0\14\4\1\100\6\4\7\0\2\4"+
    "\4\0\1\4\2\0\4\4\1\101\3\4\1\102\12\4"+
    "\7\0\2\4\4\0\1\4\2\0\1\103\22\4\7\0"+
    "\2\4\4\0\1\4\2\0\17\4\1\104\3\4\7\0"+
    "\2\4\4\0\1\4\2\0\10\4\1\105\12\4\7\0"+
    "\2\4\4\0\1\4\2\0\1\4\1\106\14\4\1\107"+
    "\4\4\7\0\2\4\4\0\1\4\2\0\1\4\1\110"+
    "\21\4\7\0\2\4\4\0\1\4\2\0\3\4\1\111"+
    "\17\4\44\0\1\112\5\0\1\36\7\0\1\70\31\0"+
    "\3\35\1\6\1\35\1\71\35\35\1\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\113\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\3\4\1\114\17\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\115\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\116\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\22\4\1\117\7\0\2\4\4\0\1\4"+
    "\2\0\1\120\10\4\1\121\11\4\7\0\2\4\4\0"+
    "\1\4\2\0\15\4\1\122\5\4\7\0\2\4\4\0"+
    "\1\4\2\0\7\4\1\123\13\4\7\0\2\4\4\0"+
    "\1\4\2\0\3\4\1\124\17\4\7\0\2\4\4\0"+
    "\1\4\2\0\12\4\1\125\10\4\7\0\2\4\4\0"+
    "\1\4\2\0\11\4\1\126\11\4\7\0\2\4\4\0"+
    "\1\4\2\0\12\4\1\127\10\4\7\0\2\4\4\0"+
    "\1\4\2\0\6\4\1\130\14\4\7\0\2\4\4\0"+
    "\1\4\2\0\7\4\1\131\13\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\132\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\10\4\1\133\12\4\7\0\2\4\4\0"+
    "\1\4\2\0\4\4\1\134\16\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\135\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\4\4\1\136\16\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\137\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\3\4\1\140\17\4\7\0\2\4\4\0"+
    "\1\4\2\0\12\4\1\141\10\4\7\0\2\4\4\0"+
    "\1\4\2\0\1\4\1\142\21\4\7\0\2\4\4\0"+
    "\1\4\2\0\7\4\1\143\13\4\7\0\2\4\4\0"+
    "\1\4\2\0\4\4\1\144\16\4\7\0\2\4\4\0"+
    "\1\4\2\0\16\4\1\145\4\4\7\0\2\4\4\0"+
    "\1\4\2\0\5\4\1\146\15\4\7\0\2\4\4\0"+
    "\1\4\2\0\3\4\1\147\17\4\7\0\2\4\4\0"+
    "\1\4\2\0\7\4\1\150\13\4\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2555];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\2\11\22\1\4\0\23\1\1\0"+
    "\4\11\1\1\3\0\20\1\1\11\36\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
// ************  Atributos y m�todos ********************
// * El analizador sint�ctico
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



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexico(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 178) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return Parser.DIM;
          }
        case 33: break;
        case 24: 
          { parser.setYylval(yytext()); return Parser.REAL;
          }
        case 34: break;
        case 12: 
          { parser.setYylval(yytext()); return Parser.DISTINTO;
          }
        case 35: break;
        case 8: 
          { return Parser.IF;
          }
        case 36: break;
        case 9: 
          { return Parser.AS;
          }
        case 37: break;
        case 32: 
          { parser.setYylval(yytext()); return Parser.CHARACTER;
          }
        case 38: break;
        case 30: 
          { parser.setYylval(yytext()); return Parser.INTEGER;
          }
        case 39: break;
        case 23: 
          { return Parser.READ;
          }
        case 40: break;
        case 25: 
          { return Parser.PROC;
          }
        case 41: break;
        case 16: 
          { return Parser.NOT;
          }
        case 42: break;
        case 26: 
          { return Parser.CTYPE;
          }
        case 43: break;
        case 14: 
          { parser.setYylval(yytext()); return Parser.MAYORIGUAL;
          }
        case 44: break;
        case 18: 
          { return Parser.AND;
          }
        case 45: break;
        case 27: 
          { return Parser.PRINT;
          }
        case 46: break;
        case 29: 
          { return Parser.RETURN;
          }
        case 47: break;
        case 7: 
          { return Parser.DO;
          }
        case 48: break;
        case 20: 
          { return Parser.THEN;
          }
        case 49: break;
        case 22: 
          { return Parser.ELSE;
          }
        case 50: break;
        case 31: 
          { return Parser.FUNCTION;
          }
        case 51: break;
        case 2: 
          { parser.setYylval(new Integer(yytext())); return Parser.CTE_ENTERA;
          }
        case 52: break;
        case 10: 
          { return Parser.OR;
          }
        case 53: break;
        case 11: 
          { parser.setYylval(yytext()); return Parser.MENORIGUAL;
          }
        case 54: break;
        case 4: 
          { parser.setYylval(yytext()); return (int)yycharat(0);
          }
        case 55: break;
        case 1: 
          { gestor.error("Error l�xico en la l�nea " + line() + " y columna " + column() + ".");
          }
        case 56: break;
        case 3: 
          { parser.setYylval(yytext()); return Parser.IDENT;
          }
        case 57: break;
        case 19: 
          { parser.setYylval(yytext().charAt(1)); return Parser.CHARACTER;
          }
        case 58: break;
        case 28: 
          { return Parser.WHILE;
          }
        case 59: break;
        case 17: 
          { return Parser.END;
          }
        case 60: break;
        case 6: 
          { parser.setYylval(new Double(yytext())); return Parser.REAL;
          }
        case 61: break;
        case 21: 
          { return Parser.TYPE;
          }
        case 62: break;
        case 5: 
          { 
          }
        case 63: break;
        case 13: 
          { parser.setYylval(yytext()); return Parser.IGUALDAD;
          }
        case 64: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
