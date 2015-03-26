package semantico;

import gestorErrores.GestorErrores;
import visitor.DefaultVisitor;
import ast.sent.Asignacion;

public class InferenciaVisitor extends DefaultVisitor {

	GestorErrores gestorErrores;

	public InferenciaVisitor(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	public Object visit(Asignacion node) {
		Object ret = super.visit(node);
		
		if (!(node.getIzquierda().getLValue())){
			gestorErrores.error("Error semántico: La expresión de la izquierda no es un LValue");
		}
		
		if (!(node.getIzquierda().getTipo() == node.getDerecha().getTipo())){
			gestorErrores.error("Error semántico: Los tipos no son compatibles");
		}
		if(!node.getIzquierda().getTipo().esPrimitivo() || !node.getDerecha().getTipo().esPrimitivo()){
			gestorErrores.error("Error semántico: No es posible asignar tipos no primitivos");
		}
		
		/*
		 * IMPLEMENTAR PREDICADOS: - ¿ExprIzq es un l-value? -
		 * ¿ExprIzq.Tipo==ExprDer.Tipo? - ¿ExprIzq (o ExprDer) son un tipo
		 * primitivo?
		 */
		return ret;
	}
}
