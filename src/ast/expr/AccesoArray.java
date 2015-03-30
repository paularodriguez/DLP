package ast.expr;

import visitor.Visitor;
import ast.tipos.Tipo;

public class AccesoArray implements Expresion {

	private Expresion izquierda; 
	private Expresion derecha;
	private boolean lvalue;
	private Tipo tipo;
	
	public AccesoArray(Expresion izquierda, Expresion derecha){
		this.setIzquierda(izquierda); 
		this.setDerecha(derecha); 
	}

	@Override
	public String toString() {
		return "AccesoArray [izquierda=" + getIzquierda() + ", derecha=" + getDerecha()
				+ "]";
	}

	@Override
	public void acepta(Visitor v) {
		 v.visit(this);
	}

	public Expresion getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Expresion izquierda) {
		this.izquierda = izquierda;
	}

	public Expresion getDerecha() {
		return derecha;
	}

	public void setDerecha(Expresion derecha) {
		this.derecha = derecha;
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
