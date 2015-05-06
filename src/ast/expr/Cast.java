package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Cast extends AbstractExpresion {

	private Tipo tipoCast;
	private Expresion expresion;

	public Cast(Tipo tipo, Expresion expresion, int linea, int columna) {
		super(linea, columna);
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

	public Tipo getTipoCast() {
		return tipoCast;
	}

	public void setTipoCast(Tipo tipoCast) {
		this.tipoCast = tipoCast;
	}

}
