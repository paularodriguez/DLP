package semantico;

import gestorErrores.GestorErrores;
import visitor.DefaultVisitor;
import ast.Programa;
import ast.def.Definicion;
import ast.def.DefinicionFuncion;
import ast.expr.Aritmetica;
import ast.expr.InvocacionFuncion;
import ast.sent.Asignacion;
import ast.sent.InvocacionProcedimiento;
import ast.sent.Print;
import ast.sent.Read;
import ast.tipos.TipoArray;
import ast.tipos.TipoEntero;
import ast.tipos.TipoReal;

public class InferenciaVisitor extends DefaultVisitor {

	GestorErrores gestorErrores;

	public InferenciaVisitor(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	public Object visit(Programa node) {
		Object ret = super.visit(node);
		boolean existeMain = false;
		for (Definicion d : node.definiciones) {
			if (d.getNombre().equals("main")) {
				existeMain = true;
			}
		}
		if (!existeMain) {
			gestorErrores
					.error("Error semántico: No se ha definido el main del programa.");
		}

		return ret;
	}

	public Object visit(DefinicionFuncion node) {
		Object ret = super.visit(node);
		if (!node.getRetorno().esPrimitivo()) {
			gestorErrores.error("Error semántico: El retorno de la función '"
					+ node.getNombre() + "' no es un tipo primitivo.");
		}
		return ret;
	}

	public Object visit(Asignacion node) {
		Object ret = super.visit(node);

		if (!(node.getIzquierda().getLValue())) {
			gestorErrores
					.error("Error semántico: La expresión de la izquierda de la asignación no es un LValue.");
		}

		if (!(node.getIzquierda().getTipo() == node.getDerecha().getTipo())) {
			gestorErrores
					.error("Error semántico: Los tipos de la asignación no son compatibles.");
		}
		if (!node.getIzquierda().getTipo().esPrimitivo()
				|| !node.getDerecha().getTipo().esPrimitivo()) {
			gestorErrores
					.error("Error semántico: No es posible asignar tipos no primitivos.");
		}

		/*
		 * IMPLEMENTAR PREDICADOS: - ¿ExprIzq es un l-value? -
		 * ¿ExprIzq.Tipo==ExprDer.Tipo? - ¿ExprIzq (o ExprDer) son un tipo
		 * primitivo?
		 */
		return ret;
	}

	public Object visit(Aritmetica node) {
		Object ret = super.visit(node);

		if (!(node.getOp1().getTipo() == node.getOp2().getTipo())) {
			gestorErrores
					.error("Error semántico: Los tipos de la operación aritmética no son compatibles.");
		}
		if (!((node.getOp1().getTipo() instanceof TipoEntero || node.getOp1()
				.getTipo() instanceof TipoReal) && (node.getOp2().getTipo() instanceof TipoEntero || node
				.getOp2().getTipo() instanceof TipoReal))) {
			gestorErrores
					.error("Error semántico: Tipo no permitido en operaciones aritméticas. Sólo se pueden sumar tipos reales y tipos enteros.");
		}
		node.setTipo(node.getOp1().getTipo());
		node.setLValue(false);

		/*
		 * IMPLEMENTAR PREDICADOS: - ¿exprIzq.tipo==exprDer.tipo? - ¿exprIzq (o
		 * exprDer) son valores aceptados para esta operación aritmética?
		 * [integer y real] CÁLCULO ATRIBUTOS: - node.tipo= exprIzq.tipo (o
		 * exprDer.tipo -> ambas deben ser de igual tipo) - node.lvalue= false;
		 * // el resultado de una op aritmética nunca será un l-value en nuestro
		 * lenguaje
		 */
		return ret;
	}

	public Object visit(InvocacionFuncion node) {
		Object ret = super.visit(node);

		if (!(node.getListaExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error semántico: Número de parámetros distinto en la llamada a la función '"
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
						.error("Error semántico: El tipo del los parámetros en la llamada a la función '"
								+ node.getIdentificador()
								+ "' no coincide con el tipo definido.");
			}
		}
		return ret;
	}

	public Object visit(InvocacionProcedimiento node) {
		Object ret = super.visit(node);

		if (!(node.getExpresiones().size() == node.getDefinicion()
				.getParametros().size())) {
			gestorErrores
					.error("Error semántico: Número de parámetros distinto en la llamada al procedimiento '"
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
						.error("Error semántico: El tipo del los parámetros en la llamada al procedimiento '"
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
					.error("Error semántico: No puede realizarse un Print sobre la expresión. Tipo no primitivo.");
		}

		return ret;
	}

	public Object visit(Read node) {
		Object ret = super.visit(node);
		if (!(node.getExpresion().getTipo().esPrimitivo())) {
			gestorErrores
					.error("Error semántico: No puede realizarse un Read sobre la expresión. Tipo no primitivo.");
		}
		if (!node.getExpresion().getLValue()) {
			gestorErrores
					.error("Error semántico: No puede realizarse un Read sobre la expresión. La expresión no es un LValue.");
		}

		return ret;
	}

	public Object visit(TipoArray node) {
		Object ret = super.visit(node);
		if (!(node.getTamaño() > 0)) {
			gestorErrores
					.error("Error semántico: El tamaño del array no es válido.");
		}

		return ret;
	}

}
