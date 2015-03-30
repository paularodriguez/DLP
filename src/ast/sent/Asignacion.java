package ast.sent;

import visitor.Visitor;
import ast.def.Definicion;
import ast.expr.Expresion;

public class Asignacion implements Sentencia {

	private Expresion izquierda;
	private Expresion derecha;
	private Definicion definicionFuncion;

	public Asignacion(Expresion izquierda, Expresion derecha) {
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

	@Override
	public Definicion getDefinicionFuncion() {
		return definicionFuncion;
	}

	@Override
	public void setDefinicionFuncion(Definicion definicion) {
		this.definicionFuncion = definicion;
	}

}
