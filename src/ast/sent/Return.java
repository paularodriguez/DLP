package ast.sent;

import visitor.Visitor;
import ast.expr.Expresion;

public class Return implements Sentencia {

	private Expresion expresion;

	public Return(Expresion expresion) {
		this.setExpresion(expresion);

	}

	@Override
	public String toString() {
		return "Return [expresion=" + getExpresion() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);	
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

}
