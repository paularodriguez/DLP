package ast.expr;

import visitor.Visitor;

public class AccesoArray extends AbstractExpresion {

	private Expresion izquierda;
	private Expresion derecha;

	public AccesoArray(Expresion izquierda, Expresion derecha) {
		this.setIzquierda(izquierda);
		this.setDerecha(derecha);
	}

	@Override
	public String toString() {
		return "AccesoArray [izquierda=" + getIzquierda() + ", derecha="
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
