package generacionCodigo;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import visitor.DefaultVisitor;
import ast.Programa;
import ast.def.DefinicionFuncion;
import ast.def.DefinicionStruct;
import ast.def.DefinicionVariable;
import ast.expr.AccesoArray;
import ast.expr.AccesoCampo;
import ast.expr.Aritmetica;
import ast.expr.Cast;
import ast.expr.Comparacion;
import ast.expr.LiteralCaracter;
import ast.expr.LiteralEntero;
import ast.expr.LiteralReal;
import ast.expr.Logica;
import ast.expr.Negacion;
import ast.expr.Variable;
import ast.sent.Asignacion;
import ast.sent.IF;
import ast.sent.InvocacionProcedimiento;
import ast.sent.Print;
import ast.sent.Read;
import ast.sent.Return;
import ast.sent.Sentencia;
import ast.sent.While;
import ast.tipos.Tipo;
import ast.tipos.TipoChar;
import ast.tipos.TipoEntero;
import ast.tipos.TipoReal;

public class GeneracionCodigo extends DefaultVisitor {

	public PrintStream out;
	private Map<String, String> instruccionAritmetica = new HashMap<String, String>();
	private Map<String, String> instruccionComparacion = new HashMap<String, String>();

	// Contador para crear las etiquetas de salto de estas estructuras
	private int contadorWhile;
	private int contadorIf;

	public GeneracionCodigo() {
		try {
			out = new PrintStream("salida.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarInstrucciones();
	}

	private void cargarInstrucciones() {
		// aritmetica
		instruccionAritmetica.put("+", "add");
		instruccionAritmetica.put("-", "sub");
		instruccionAritmetica.put("*", "mul");
		instruccionAritmetica.put("/", "div");

		// comparacion
		instruccionComparacion.put(">", "gt");
		instruccionComparacion.put("<", "lt");
		instruccionComparacion.put(">=", "ge");
		instruccionComparacion.put("<=", "le");
		instruccionComparacion.put("==", "eq");
		instruccionComparacion.put("!=", "ne");
	}

	@Override
	public Object visit(Programa node) {
		String NombreFicheroFuente = "fichero.txt";
		out.println("#source " + NombreFicheroFuente + "\"");
		out.println("call main");
		out.println("halt");
		return super.visit(node);
	}

	@Override
	public Object visit(DefinicionVariable node) {
		out.println("#VAR " + node.getNombre() + ":"
				+ node.getTipo().getMAPLName());

		node.setAmbito("GLOBAL");

		// DIRECCIÓN

		// si la variable es global -- PUSHA offset

		// si la variable no es global -- PUSHA BP
		// PUSH offset
		// ADD

		// VALOR
		// load
		// storef

		return super.visit(node);
	}

	@Override
	public Object visit(DefinicionFuncion node) {

		out.println("#FUNC " + node.getNombre());

		for (DefinicionVariable dv : node.getParametros()) {
			out.println("#PARAM " + dv.getNombre() + ":"
					+ dv.getTipo().getMAPLName());
			dv.setAmbito("LOCAL");
		}

		for (DefinicionVariable loc : node.getDefinicionesVariable()) {
			out.println("#LOCAL " + loc.getNombre() + ":"
					+ loc.getTipo().getMAPLName());
			loc.setAmbito("LOCAL");
		}

		if (node.getRetorno() != null)
			out.println("#RET " + node.getRetorno().getMAPLName());

		out.println(node.getNombre() + ":");
		out.println("enter " + node.getSizeLocales());

		for (Sentencia s : node.getSentencias()) {
			s.acepta(this);
		}

		// RET
		if (node.getRetorno() != null) {
			out.println("RET 0," + node.getSizeLocales() + ","
					+ node.getSizeParametros());
		}

		return null;
	}

	@Override
	public Object visit(DefinicionStruct node) {
		out.println("#TYPE " + node.getNombre());
		return super.visit(node);
	}

	@Override
	public Object visit(LiteralCaracter node) {
		out.println("push" + node.getTipo().sufijo() + " " + node.getCaracter());
		return null;
	}

	@Override
	public Object visit(LiteralEntero node) {
		out.println("push" + node.getTipo().sufijo() + " " + node.getValor());
		return null;
	}

	@Override
	public Object visit(LiteralReal node) {
		out.println("push" + node.getTipo().sufijo() + " " + node.getValor());
		return null;
	}

	@Override
	public Object visit(Logica node) {
		node.getOperando1().setVisitaDireccion(false);
		node.getOperando1().setVisitaValor(true);
		node.getOperando1().acepta(this);

		node.getOperando2().setVisitaDireccion(false);
		node.getOperando2().setVisitaValor(true);
		node.getOperando2().acepta(this);

		if (node.getOperador().equals("and")) {
			out.println("and");
		}
		if (node.getOperador().equals("or")) {
			out.println("or");
		}
		return null;
	}

	@Override
	public Object visit(Negacion node) {
		node.getExpresion().setVisitaDireccion(false);
		node.getExpresion().setVisitaValor(true);
		node.getExpresion().acepta(this);
		out.println("not");
		return null;
	}

	@Override
	public Object visit(AccesoCampo node) {
		node.getIzquierda().setVisitaDireccion(true);
		node.getIzquierda().setVisitaValor(false);
		node.getIzquierda().acepta(this);

		// calcular desplazamiento

		// out add

		return null;
	}

	@Override
	public Object visit(Aritmetica node) {
		node.getOp1().setVisitaValor(true);
		node.getOp1().setVisitaDireccion(false);
		node.getOp1().acepta(this); // valor

		node.getOp2().setVisitaValor(true);
		node.getOp1().setVisitaDireccion(false);
		node.getOp2().acepta(this); // valor
		out.println(instruccionAritmetica.get(node.getOperador())
				+ node.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Comparacion node) {
		node.getOperando1().setVisitaValor(true);
		node.getOperando1().setVisitaDireccion(false);
		node.getOperando1().acepta(this); // valor

		node.getOperando2().setVisitaValor(true);
		node.getOperando2().setVisitaDireccion(false);
		node.getOperando2().acepta(this); // valor
		out.println(instruccionComparacion.get(node.getOperador())
				+ node.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Cast node) {
		node.getExpresion().setVisitaValor(true);
		node.getExpresion().setVisitaDireccion(false);
		node.getExpresion().acepta(this);
		cast(node.getExpresion().getTipo(), node.getTipo());

		// si es real a char es: f2i
		// i2b
		return null;
	}

	private void cast(Tipo tipoOrigen, Tipo tipoDestino) {
		if (tipoOrigen instanceof TipoChar && tipoDestino instanceof TipoReal) {
			out.println("b2i");
			out.println("i2f");
		}
		if (tipoOrigen instanceof TipoReal && tipoDestino instanceof TipoChar) {
			out.println("f2i");
			out.println("i2b");
		}
		if (tipoOrigen instanceof TipoReal && tipoDestino instanceof TipoEntero) {
			out.println("f2i");
		}
		if (tipoOrigen instanceof TipoEntero && tipoDestino instanceof TipoReal) {
			out.println("i2f");
		}
		if (tipoOrigen instanceof TipoEntero && tipoDestino instanceof TipoChar) {
			out.println("i2b");
		}
		if (tipoOrigen instanceof TipoChar && tipoDestino instanceof TipoEntero) {
			out.println("b2i");
		}
	}

	@Override
	public Object visit(Variable node) {

		if (((DefinicionVariable) node.getDefinicion()).getAmbito() == "GLOBAL") {
			out.println("pusha " + node.getDefinicion().getDireccion());
		} else {
			out.println("pusha BP");
			out.println("push " + node.getDefinicion().getDireccion());
			out.println("add");
		}

		if (node.getVisitaValor()) {
			out.println("load" + node.getTipo().sufijo());
		}

		return null;

	}

	@Override
	public Object visit(Asignacion node) {

		node.getIzquierda().setVisitaValor(false);
		node.getIzquierda().setVisitaDireccion(true);
		node.getIzquierda().acepta(this);

		node.getDerecha().setVisitaValor(true);
		node.getDerecha().setVisitaDireccion(false);
		node.getDerecha().acepta(this);

		out.println("store" + node.getIzquierda().getTipo().sufijo());

		return null;
	}

	@Override
	public Object visit(IF node) {
		int contadorIFLocal = contadorIf;
		contadorIf++;

		// CONDICION --> valor del código de la condición
		// si no se cumple saltamos al else

		// jz else_01
		out.println("jz else_" + contadorIFLocal);

		for (Sentencia s : node.getSentenciasIF()) {
			s.acepta(this);
		}

		// jmp fin_if_01
		out.println("jmp fin_if_" + contadorIFLocal);
		// else_01:
		out.println("else_" + contadorIFLocal + ":");

		if (node.getSentenciasElse() != null) {
			for (Sentencia s : node.getSentenciasElse()) {
				s.acepta(this);
			}
		}

		// fin_if_01:
		out.println("fin_if_" + contadorIFLocal + ":");

		return super.visit(node);
	}

	@Override
	public Object visit(InvocacionProcedimiento node) {

		// valor argumentos

		/*
		 * for (Expresion e: node.getExpresiones()){ e.acepta(this,valor); }
		 */

		out.println("call " + node.getNombre());

		// comprobar la definicion en INVOCACION PROCEDIMIENTO
		/*
		 * if (node.getDefinicionFuncion().getRetorno() != null){
		 * out.println("pop"); }
		 */
		// hacer un pop que retire los residuos de la pila

		return super.visit(node);
	}

	@Override
	public Object visit(Print node) {
		node.getExpresion().setVisitaDireccion(false);
		node.getExpresion().setVisitaValor(true);
		node.getExpresion().acepta(this);
		out.println("out" + node.getExpresion().getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Read node) {
		node.getExpresion().setVisitaDireccion(true);
		node.getExpresion().setVisitaValor(false);
		node.getExpresion().acepta(this);
		out.println("in" + node.getExpresion().getTipo().sufijo());
		out.println("store" + node.getExpresion().getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Return node) {
		if (node.getExpresion() != null) {
			// ret tamañoExpre, size(locales), size(params)
		}
		return super.visit(node);
	}

	@Override
	public Object visit(While node) {
		int contadorWhileLocal = contadorWhile;
		contadorWhile++;

		// iniWhile_01:
		out.println("inicioWhile_" + contadorWhileLocal + ":");

		// CONDICION -- hay que visitarla

		// jz finWhile_01
		out.println("jz finWhile_" + contadorWhileLocal);

		for (Sentencia s : node.getSentencias()) {
			s.acepta(this);
		}

		// jmp inicioWhile_01
		out.println("jmp inicioWhile_" + contadorWhileLocal);
		// finWhile_01:
		out.println("jz finWhile_" + contadorWhileLocal + ":");

		// TODO Auto-generated method stub
		return super.visit(node);
	}

	// acceso array - izq dirección , indice - valor.
	@Override
	public Object visit(AccesoArray node) { // -- ejemplo v[2]

		// DIRECCIÓN
		// generarDireccionVector -- devuelve dirección de v[]
		// generarValorIndice -- devuelve el número 2 - índice
		// push tamaño.tipoBaseArray
		// mul
		// add

		// VALOR
		// direccion(AccesoArray)
		// LOAD

		// TODO Auto-generated method stub
		return super.visit(node);
	}

	// EJEMPLO EXAMEN
	/*
	 * Si piden que puedan pasarse parámetros por referencia: añadir la palabra
	 * reservada en el léxico para identificar cómo se pasa el parámetro.
	 * 
	 * f (x as integer, byref y as integer) en el sintáctico permitir estos
	 * parámetros en el generador de código para cada parámetro distinguir si es
	 * un acceso por valor o por dirección
	 */

}
