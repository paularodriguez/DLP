package ast.sent;

import visitor.Visitor;
import ast.expr.Expresion;

public class Asignacion extends AbstractSentencia {

	private Expresion izquierda;
	private Expresion derecha;

	public Asignacion(Expresion izquierda, Expresion derecha, int linea, int columna) {
		super(linea, columna);
		this.setIzquierda(izquierda);
		this.setDerecha(derecha);
	}

	@Override
	public String toString() {
		return "Asignacion [izquierda=" + getIzquierda() + ", derecha="
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
