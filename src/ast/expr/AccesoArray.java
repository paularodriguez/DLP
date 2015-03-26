package ast.expr;

import ast.tipos.Tipo;
import visitor.Visitor;



public class AccesoArray implements Expresion {

	private Expresion izquierda; 
	private Expresion derecha;
	
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
		return false;
	}

	@Override
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
