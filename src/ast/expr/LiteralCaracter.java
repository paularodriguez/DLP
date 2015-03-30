package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;


public class LiteralCaracter implements Expresion {

	private Character caracter;
	private boolean lvalue;
	private Tipo tipo;
	
	public LiteralCaracter(Character caracter) {
		this.setCaracter(caracter);
	}

	@Override
	public String toString() {
		return "LiteralCaracter [caracter=" + getCaracter() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Character getCaracter() {
		return caracter;
	}

	public void setCaracter(Character caracter) {
		this.caracter = caracter;
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
