package ast.sent;

import ast.expr.Expresion;

public class Return implements Sentencia {

	private Expresion expresion;

	public Return(Expresion expresion) {
		this.expresion = expresion;

	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

}
