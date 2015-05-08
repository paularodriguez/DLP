package semantico;

import gestorErrores.GestorErrores;
import visitor.DefaultVisitor;
import ast.Programa;
import ast.def.*;
import ast.expr.*;
import ast.sent.*;
import ast.tipos.*;

public class InferenciaVisitor extends DefaultVisitor {

	GestorErrores gestorErrores;

	public InferenciaVisitor(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	public Object visit(Programa node) {

		boolean existeMain = false;
		for (Definicion d : node.definiciones) {

			// Regla sem�ntica
			if (d instanceof DefinicionVariable) {
				((DefinicionVariable) d).setEsParametro(false);
			}

			// Predicado
			if (d.getNombre().equals("main")
					&& (d instanceof DefinicionFuncion)) {
				existeMain = true;
			}
		}
		if (!existeMain) {
			gestorErrores
					.error("Error sem�ntico: No se ha definido el main del programa. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return super.visit(node);
	}

	public Object visit(DefinicionVariable node) {
		Object ret = super.visit(node);

		// Predicado: Par�metro funci�n ha de ser de tipo primitivo.
		if (node.EsParametro() && !node.getTipo().esPrimitivo()) {
			gestorErrores
					.error("Error sem�ntico: Los par�metros de la funci�n no son de tipo primitivo. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		return ret;
	}

	public Object visit(DefinicionFuncion node) {

		// Reglas sem�nticas

		for (DefinicionVariable param : node.getParametros()) {
			param.setEsParametro(true);
		}

		for (DefinicionVariable defVars : node.getDefinicionesVariable()) {
			defVars.setEsParametro(false);
		}

		for (Sentencia sent : node.getSentencias()) {
			sent.setDefinicionFuncion(node);
		}

		Object ret = super.visit(node);

		// Predicado: El retorno de la funci�n tiene que ser de tipo primitivo.
		if (node.getRetorno() != null) {
			if (!node.getRetorno().esPrimitivo()) {
				gestorErrores
						.error("Error sem�ntico: El retorno de la funci�n '"
								+ node.getNombre()
								+ "' no es un tipo primitivo. Linea: "
								+ node.getLinea() + ", Columna: "
								+ node.getColumna());
			}
		}
		return ret;
	}

	// EXPRESIONES

	@Override
	public Object visit(AccesoArray node) {

		Object ret = super.visit(node);

		// Predicados
		if (!(node.getIzquierda().getTipo() instanceof TipoArray))
			gestorErrores
					.error("Error sem�ntico: La expresi�n izquierda no es un TipoArray. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		else if (!(node.getDerecha().getTipo() instanceof TipoEntero))
			gestorErrores
					.error("Error sem�ntico: El �ndice del array no es un TipoEntero. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		else {

			// Reglas sem�nticas
			node.setLValue(true);
			node.setTipo(((TipoArray) node.getIzquierda().getTipo()).getTipo());
		}
		return ret;
	}

	@Override
	public Object visit(AccesoCampo node) {
		node.getIzquierda().acepta(this);

		if (!(node.getIzquierda().getTipo() instanceof DefinicionStruct)) {
			gestorErrores
					.error("Error sem�ntico: La expresi�n de la izquierda '"
							+ node.getIzquierda() + "' no es una estructura."
							+ " Tipo = " + node.getIzquierda().getTipo()
							+ ". Linea: " + node.getLinea() + ", Columna: "
							+ node.getColumna());
		}

		else if (!(node.getDerecha() instanceof Variable)) {
			gestorErrores
					.error("Error sem�ntico: La expresi�n de la derecha no es una variable. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		else {
			String idCampo = ((Variable) node.getDerecha()).getNombre();
			Campo c = ((DefinicionStruct) node.getIzquierda().getTipo())
					.buscarCampoNombre(idCampo);

			if (!(c == null)) {
				node.setTipo(c.getTipo());
			} else {
				gestorErrores
						.error("Error sem�ntico: El campo no existe. Linea: "
								+ node.getLinea() + ", Columna: "
								+ node.getColumna());
			}
			node.setLValue(true);
		}
		return null;
	}

	public Object visit(Aritmetica node) {
		Object ret = super.visit(node);

		if (!(node.getOp1().getTipo() == node.getOp2().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: Los tipos de la operaci�n aritm�tica no son compatibles. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		if (!((node.getOp1().getTipo() instanceof TipoEntero || node.getOp1()
				.getTipo() instanceof TipoReal) && (node.getOp2().getTipo() instanceof TipoEntero || node
				.getOp2().getTipo() instanceof TipoReal))) {
			gestorErrores
					.error("Error sem�ntico: Tipo no permitido en operaciones aritm�ticas. S�lo pueden ser tipos reales y tipos enteros. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		node.setLValue(false);
		node.setTipo(node.getOp1().getTipo());

		return ret;
	}

	@Override
	public Object visit(Cast node) {
		node.setLValue(false);
		node.setTipo(node.getTipoCast());
		Object ret = super.visit(node);

		if (!(node.getTipo() instanceof TipoEntero
				|| node.getTipo() instanceof TipoReal || node.getTipo() instanceof TipoChar)) {
			gestorErrores
					.error("Error sem�ntico: No se puede hacer un cast a un tipo no simple. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		
		if (!(node.getExpresion().getTipo() instanceof TipoEntero
				|| node.getExpresion().getTipo() instanceof TipoReal || node.getExpresion().getTipo() instanceof TipoChar)) {
			gestorErrores
					.error("Error sem�ntico: No se puede hacer un cast a un tipo no simple. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		if (node.getTipo().equals(node.getExpresion().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: No se puede hacer un cast al mismo tipo de la expresi�n. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	/**
	 * Predicados comparaci�n
	 */
	@Override
	public Object visit(Comparacion node) {
		Object ret = super.visit(node);

		if (!(node.getOperando1().getTipo() == node.getOperando2().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: Los tipos de la comparaci�n no son compatibles. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		if (!((node.getOperando1().getTipo() instanceof TipoEntero
				|| node.getOperando1().getTipo() instanceof TipoReal || node
					.getOperando1() instanceof TipoChar) && (node
				.getOperando2().getTipo() instanceof TipoEntero
				|| node.getOperando2().getTipo() instanceof TipoReal || node
					.getOperando1() instanceof TipoChar))) {
			gestorErrores
					.error("Error sem�ntico: Tipo no permitido en comparaciones. S�lo pueden ser tipos reales y tipos enteros. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		node.setLValue(false);
		node.setTipo(TipoEntero.getInstancia());
		return ret;
	}

	@Override
	public Object visit(InvocacionFuncion node) {

		Object ret = super.visit(node);

		node.setTipo(node.getDefinicion().getRetorno());
		node.setLValue(false);

		if (!(node.getListaExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error sem�ntico: N�mero de par�metros distinto en la llamada a la funci�n '"
							+ node.getIdentificador()
							+ "'. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		} else {

			boolean paramsValidos = true;
			for (int i = 0; i < node.getDefinicion().getParametros().size(); i++) {
				if (node.getDefinicion().getParametros().get(i).getTipo() != node
						.getListaExpresiones().get(i).getTipo()) {
					paramsValidos = false;
				}
			}
			if (!paramsValidos) {
				gestorErrores
						.error("Error sem�ntico: El tipo del los par�metros en la llamada a la funci�n '"
								+ node.getIdentificador()
								+ "' no coincide con el tipo definido. Linea: "
								+ node.getLinea()
								+ ", Columna: "
								+ node.getColumna());
			}
		}

		if (!(node.getTipo() != null)) {
			gestorErrores.error("Error sem�ntico: La funci�n '"
					+ node.getIdentificador()
					+ "' no tiene definido valor de retorno. Linea: "
					+ node.getLinea() + ", Columna: " + node.getColumna());
		}

		return ret;
	}

	@Override
	public Object visit(LiteralCaracter node) {
		Object ret = super.visit(node);
		node.setLValue(false);
		node.setTipo(TipoChar.getInstancia());
		return ret;
	}

	@Override
	public Object visit(LiteralEntero node) {
		Object ret = super.visit(node);
		node.setLValue(false);
		node.setTipo(TipoEntero.getInstancia());
		return ret;
	}

	@Override
	public Object visit(LiteralReal node) {
		Object ret = super.visit(node);
		node.setLValue(false);
		node.setTipo(TipoReal.getInstancia());
		return ret;
	}

	@Override
	public Object visit(Logica node) {

		Object ret = super.visit(node);

		if (!(node.getOperando1().getTipo() instanceof TipoEntero)
				|| !(node.getOperando2().getTipo() instanceof TipoEntero))
			gestorErrores
					.error("Error sem�ntico: La operaci�n l�gica tiene un operador de tipo no entero. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());

		node.setTipo(node.getOperando1().getTipo());
		return ret;
	}

	@Override
	public Object visit(Negacion node) {
		Object ret = super.visit(node);
		node.setLValue(false);
		node.setTipo(node.getExpresion().getTipo());
		return ret;
	}

	@Override
	public Object visit(Variable node) {
		Object ret = super.visit(node);
		node.setLValue(true);
		node.setTipo(node.getDefinicion().getTipo());
		return ret;
	}

	// SENTENCIAS

	public Object visit(Asignacion node) {
		Object ret = super.visit(node);

		if (!(node.getIzquierda().getLValue())) {
			gestorErrores
					.error("Error sem�ntico: La expresi�n de la izquierda de la asignaci�n no es un LValue. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		if (!(node.getIzquierda().getTipo() == node.getDerecha().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: Los tipos de la asignaci�n no son compatibles. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		if (node.getIzquierda().getTipo() != null
				&& node.getDerecha().getTipo() != null) {
			if (!node.getIzquierda().getTipo().esPrimitivo()
					|| !node.getDerecha().getTipo().esPrimitivo()) {
				gestorErrores
						.error("Error sem�ntico: No es posible asignar tipos no primitivos. Linea: "
								+ node.getLinea()
								+ ", Columna: "
								+ node.getColumna());
			}
		}
		return ret;
	}

	public Object visit(IF node) {

		for (Sentencia s : node.getSentenciasIF()) {
			s.setDefinicionFuncion(node.getDefinicionFuncion());
		}

		if (node.getSentenciasElse() != null) {
			for (Sentencia s : node.getSentenciasElse()) {
				s.setDefinicionFuncion(node.getDefinicionFuncion());
			}
		}

		Object ret = super.visit(node);
		if (node.getExpresion().getTipo() != TipoEntero.getInstancia()) {
			gestorErrores
					.error("Error sem�ntico: La condici�n de la sentencia IF no es v�lida. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	public Object visit(InvocacionProcedimiento node) {
		Object ret = super.visit(node);

		if (!(node.getExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error sem�ntico: N�mero de par�metros distinto en la llamada al procedimiento '"
							+ node.getNombre()
							+ "'. Linea: "
							+ node.getLinea()
							+ ", Columna: " + node.getColumna());

		} else {

			boolean paramsValidos = true;
			for (int i = 0; i < node.getDefinicion().getParametros().size(); i++) {
				if (node.getDefinicion().getParametros().get(i).getTipo() != node
						.getExpresiones().get(i).getTipo()) {
					paramsValidos = false;
				}
			}
			if (!paramsValidos) {
				gestorErrores
						.error("Error sem�ntico: El tipo del los par�metros en la llamada al procedimiento '"
								+ node.getNombre()
								+ "' no coincide con el tipo definido. Linea: "
								+ node.getLinea()
								+ ", Columna: "
								+ node.getColumna());
			}
		}

		if (!(node.getDefinicion().getRetorno() == null)) {
			gestorErrores
					.error("Error sem�ntico: No se est� recogiendo el retorno de la funci�n '"
							+ node.getNombre()
							+ "'. Linea: "
							+ node.getLinea()
							+ ", Columna: " + node.getColumna());
		}

		return ret;
	}

	public Object visit(Print node) {
		Object ret = super.visit(node);
		Tipo tipo = node.getExpresion().getTipo();

		if (tipo == null || !tipo.esPrimitivo()) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Print sobre la expresi�n. Tipo no primitivo. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	public Object visit(Read node) {
		Object ret = super.visit(node);
		if (node.getExpresion().getTipo() == null
				|| !(node.getExpresion().getTipo().esPrimitivo())) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Read sobre la expresi�n. Tipo no primitivo. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}
		if (!node.getExpresion().getLValue()) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Read sobre la expresi�n. La expresi�n no es un LValue. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	public Object visit(Return node) {
		Object ret = super.visit(node);

		if (node.getDefinicionFuncion().getRetorno() == null) {
			if (!(node.getExpresion() == null)) {
				gestorErrores
						.error("Error sem�ntico: La funci�n no tiene definido un valor de retorno o �ste es de tipo void. Linea: "
								+ node.getLinea()
								+ ", Columna: "
								+ node.getColumna());
			}
		} else {			
			if (node.getDefinicionFuncion().getRetorno() != null)
				if (node.getExpresion() == null || !(node.getDefinicionFuncion().getRetorno() == node
						.getExpresion().getTipo())  ) {
					gestorErrores
							.error("Error sem�ntico: El tipo de retorno de la funci�n '"
									+ node.getDefinicionFuncion().getNombre()
									+ "' es incorrecto. Linea: "
									+ node.getLinea()
									+ ", Columna: "
									+ node.getColumna());
				}
		}

		return ret;
	}

	public Object visit(While node) {
		for (Sentencia s : node.getSentencias()) {
			s.setDefinicionFuncion(node.getDefinicionFuncion());
		}

		Object ret = super.visit(node);
		if (node.getExpresion().getTipo() != TipoEntero.getInstancia()) {
			gestorErrores
					.error("Error sem�ntico: La condici�n de la sentencia While no es v�lida. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	// TIPOS
	public Object visit(TipoArray node) {
		Object ret = super.visit(node);
		if (!(node.getTama�o() > 0)) {
			gestorErrores
					.error("Error sem�ntico: El tama�o del array no es v�lido. Linea: "
							+ node.getLinea()
							+ ", Columna: "
							+ node.getColumna());
		}

		return ret;
	}

	public Object visit(DefinicionStruct node) {
		return super.visit(node);
	}

	public Object visit(Campo node) {

		return super.visit(node);
	}

}
