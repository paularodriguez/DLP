package ast.sent;

import visitor.Visitor;
import ast.expr.Expresion;

public class Print extends AbstractSentencia {

	private Expresion expresion;

	public Print(Expresion expresiones, int linea, int columna) {
		super(linea,columna);
		this.setExpresion(expresiones);
	}

	@Override
	public String toString() {
		return "Print [expresiones=" + getExpresion() + "]";
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
