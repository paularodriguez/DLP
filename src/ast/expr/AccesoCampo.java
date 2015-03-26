package ast.expr;

import ast.tipos.Tipo;
import visitor.Visitor;

public class AccesoCampo implements Expresion {

	private Expresion izquierda;
	private Expresion derecha;

	public AccesoCampo(Expresion izquierda, Expresion derecha) {

		this.setIzquierda(izquierda);
		this.setDerecha(derecha);
	}

	@Override
	public String toString() {
		return "AccesoCampo [izquierda=" + getIzquierda() + ", derecha=" + getDerecha()
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
		return true;
	}

	@Override
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLValue(boolean lvalue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTipo(Tipo tipo) {
		// TODO Auto-generated method stub
		
	}

}