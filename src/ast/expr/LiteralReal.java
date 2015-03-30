package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;


public class LiteralReal implements Expresion{

	private double valor;
	private boolean lvalue;
	private Tipo tipo;
	
	public LiteralReal (Double d){
		this.setValor(d);
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + getValor() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
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
