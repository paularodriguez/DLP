package ast.sent;

import visitor.Visitor;
import ast.expr.Expresion;

public class Return extends AbstractSentencia {

	private Expresion expresion;

	public Return(Expresion expresion, int linea, int columna) {
		super(linea, columna);
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
