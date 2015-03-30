package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Cast implements Expresion {

	private Tipo tipoCast;
	private Expresion expresion;
	private Tipo tipo;
	private boolean lvalue;

	public Cast(Tipo tipo, Expresion expresion) {
		this.setTipoCast(tipo);
		this.setExpresion(expresion);
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + getTipo() + ", expresion=" + getExpresion()
				+ "]";
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
	
	public Tipo getTipoCast() {
		return tipoCast;
	}

	public void setTipoCast(Tipo tipoCast) {
		this.tipoCast = tipoCast;
	}

}
