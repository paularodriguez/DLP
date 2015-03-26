package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Cast implements Expresion {

	private Tipo tipoCast;
	private Expresion expresion;

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
		return false;
	}

	@Override
	public Tipo getTipo() {
		return getTipoCast();
	}

	@Override
	public void setLValue(boolean lvalue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTipo(Tipo tipo) {
		// TODO Auto-generated method stub

	}
	
	public Tipo getTipoCast() {
		return tipoCast;
	}

	public void setTipoCast(Tipo tipoCast) {
		this.tipoCast = tipoCast;
	}

}
