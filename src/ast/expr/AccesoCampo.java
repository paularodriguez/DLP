package ast.expr;

import visitor.Visitor;

public class AccesoCampo extends AbstractExpresion {

	private Expresion izquierda;
	private Expresion derecha;

	public AccesoCampo(Expresion izquierda, Expresion derecha) {

		this.setIzquierda(izquierda);
		this.setDerecha(derecha);
	}

	@Override
	public String toString() {
		return "AccesoCampo [izquierda=" + getIzquierda() + ", derecha="
				+ getDerecha() + "]";
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

}