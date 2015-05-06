package ast.expr;

import visitor.Visitor;

public class Negacion extends AbstractExpresion {

	private Expresion expresion;
	

	public Negacion(Expresion expresion, int linea, int columna) {
		super(linea, columna);
		this.setExpresion(expresion);
	}

	@Override
	public String toString() {
		return "Negacion [expresion=" + getExpresion() + "]";
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
