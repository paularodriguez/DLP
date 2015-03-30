package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;


public class LiteralEntero implements Expresion {

	private int valor;
	private Tipo tipo;
	private boolean lvalue;
	
	public LiteralEntero (int valor){
		this.setValor(valor);
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + getValor() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
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
