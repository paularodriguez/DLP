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
		
		for (Definicion def : node.definiciones){
			if (def instanceof DefinicionVariable){
				((DefinicionVariable) def).setEsParametro(false);
			}
		}
		
		Object ret = super.visit(node);
		boolean existeMain = false;
		for (Definicion d : node.definiciones) {
			if (d.getNombre().equals("main")) {
				existeMain = true;
			}
		}
		if (!existeMain) {
			gestorErrores
					.error("Error sem�ntico: No se ha definido el main del programa.");
		}

		return ret;
	}

	public Object visit(DefinicionVariable node) {
		Object ret = super.visit(node);

		if (node.EsParametro() && !node.getTipo().esPrimitivo()) {
			gestorErrores
					.error("Error sem�ntico: Los par�metros de la funci�n no son de tipo primitivo.");
		}

		return ret;
	}

	public Object visit(DefinicionFuncion node) {

		for (DefinicionVariable param : node.getParametros()) {
			param.setEsParametro(true);
		}

		for (DefinicionVariable defVars : node.getDefinicionesVariable()) {
			defVars.setEsParametro(false);
		}

		/*
		 * for (Sentencia sent: node.getSentencias()){
		 * sent.setDefinicionFuncion(node); }
		 */

		Object ret = super.visit(node);

		if (!node.getRetorno().esPrimitivo()) {
			gestorErrores.error("Error sem�ntico: El retorno de la funci�n '"
					+ node.getNombre() + "' no es un tipo primitivo.");
		}

		return ret;
	}

	public Object visit(DefinicionProcedimiento node) {

		for (DefinicionVariable param : node.getParametros()) {
			param.setEsParametro(true);
		}

		for (DefinicionVariable defVars : node.getDefinicionesVariable()) {
			defVars.setEsParametro(false);
		}

		/*
		 * for (Sentencia sent: node.getSentencias()){
		 * sent.setDefinicionFuncion(node); }
		 */

		Object ret = super.visit(node);
		return ret;
	}

	public Object visit(Asignacion node) {
		Object ret = super.visit(node);

		if (!(node.getIzquierda().getLValue())) {
			gestorErrores
					.error("Error sem�ntico: La expresi�n de la izquierda de la asignaci�n no es un LValue.");
		}

		if (!(node.getIzquierda().getTipo() == node.getDerecha().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: Los tipos de la asignaci�n no son compatibles.");
		}
		if (!node.getIzquierda().getTipo().esPrimitivo()
				|| !node.getDerecha().getTipo().esPrimitivo()) {
			gestorErrores
					.error("Error sem�ntico: No es posible asignar tipos no primitivos.");
		}

		/*
		 * IMPLEMENTAR PREDICADOS: - �ExprIzq es un l-value? -
		 * �ExprIzq.Tipo==ExprDer.Tipo? - �ExprIzq (o ExprDer) son un tipo
		 * primitivo?
		 */
		return ret;
	}

	public Object visit(Aritmetica node) {
		Object ret = super.visit(node);

		if (!(node.getOp1().getTipo() == node.getOp2().getTipo())) {
			gestorErrores
					.error("Error sem�ntico: Los tipos de la operaci�n aritm�tica no son compatibles.");
		}
		if (!((node.getOp1().getTipo() instanceof TipoEntero || node.getOp1()
				.getTipo() instanceof TipoReal) && (node.getOp2().getTipo() instanceof TipoEntero || node
				.getOp2().getTipo() instanceof TipoReal))) {
			gestorErrores
					.error("Error sem�ntico: Tipo no permitido en operaciones aritm�ticas. S�lo se pueden sumar tipos reales y tipos enteros.");
		}
		node.setTipo(node.getOp1().getTipo());
		node.setLValue(false);

		/*
		 * IMPLEMENTAR PREDICADOS: - �exprIzq.tipo==exprDer.tipo? - �exprIzq (o
		 * exprDer) son valores aceptados para esta operaci�n aritm�tica?
		 * [integer y real] C�LCULO ATRIBUTOS: - node.tipo= exprIzq.tipo (o
		 * exprDer.tipo -> ambas deben ser de igual tipo) - node.lvalue= false;
		 * // el resultado de una op aritm�tica nunca ser� un l-value en nuestro
		 * lenguaje
		 */
		return ret;
	}

	public Object visit(InvocacionFuncion node) {
		Object ret = super.visit(node);

		if (!(node.getListaExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error sem�ntico: N�mero de par�metros distinto en la llamada a la funci�n '"
							+ node.getIdentificador() + "'.");
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
								+ "' no coincide con el tipo definido.");
			}
		}
		return ret;
	}

	public Object visit(IF node) {
		Object ret = super.visit(node);
		if (node.getExpresion().getTipo() != TipoEntero.getInstancia()) {
			gestorErrores
					.error("Error sem�ntico: La condici�n de la sentencia IF no es v�lida.");
		}

		return ret;
	}

	public Object visit(InvocacionProcedimiento node) {
		Object ret = super.visit(node);

		if (!(node.getExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error sem�ntico: N�mero de par�metros distinto en la llamada al procedimiento '"
							+ node.getNombre() + "'.");

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
								+ "' no coincide con el tipo definido.");
			}
		}

		return ret;
	}

	public Object visit(Print node) {
		Object ret = super.visit(node);
		if (!(node.getExpresion().getTipo().esPrimitivo())) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Print sobre la expresi�n. Tipo no primitivo.");
		}

		return ret;
	}

	public Object visit(Read node) {
		Object ret = super.visit(node);
		if (!(node.getExpresion().getTipo().esPrimitivo())) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Read sobre la expresi�n. Tipo no primitivo.");
		}
		if (!node.getExpresion().getLValue()) {
			gestorErrores
					.error("Error sem�ntico: No puede realizarse un Read sobre la expresi�n. La expresi�n no es un LValue.");
		}

		return ret;
	}

	public Object visit(While node) {
		Object ret = super.visit(node);
		if (node.getExpresion().getTipo() != TipoEntero.getInstancia()) {
			gestorErrores
					.error("Error sem�ntico: La condici�n de la sentencia While no es v�lida.");
		}

		return ret;
	}

	public Object visit(TipoArray node) {
		Object ret = super.visit(node);
		if (!(node.getTama�o() > 0)) {
			gestorErrores
					.error("Error sem�ntico: El tama�o del array no es v�lido.");
		}

		return ret;
	}

}