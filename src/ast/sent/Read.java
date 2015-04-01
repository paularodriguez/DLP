package ast.sent;

import visitor.Visitor;
import ast.expr.Expresion;

public class Read extends AbstractSentencia {

	private Expresion expresion;

	public Read(Expresion expresion) {
		this.setExpresion(expresion);
	}

	@Override
	public String toString() {
		return "Lectura [expresion=" + getExpresion() + "]";
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
