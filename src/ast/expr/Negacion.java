package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Negacion implements Expresion {

	private Expresion expresion;
	private boolean lvalue;
	private Tipo tipo;

	public Negacion(Expresion expresion) {
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

	@Override
	public boolean getLValue() {
		return lvalue;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setLValue(boolean lvalue) {
		this.lvalue = lvalue;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
