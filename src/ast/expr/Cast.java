package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Cast implements Expresion {

	private Tipo tipo; 
	private Expresion expresion; 
	
	public Cast(Tipo tipo, Expresion expresion){
		this.setTipo(tipo); 
		this.setExpresion(expresion);
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + getTipo() + ", expresion=" + getExpresion() + "]";
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
