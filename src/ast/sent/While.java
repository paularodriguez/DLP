package ast.sent;

import java.util.List;

import visitor.Visitor;
import ast.def.Definicion;
import ast.expr.Expresion;

public class While implements Sentencia {

	private Expresion expresion;
	private List<Sentencia> sentencias;
	private Definicion definicionFuncion;

	public While(Expresion expresion, List<Sentencia> listaSentencias) {
		this.setExpresion(expresion);
		this.setSentencias(listaSentencias);
	}

	@Override
	public String toString() {
		return "While [expresion=" + getExpresion() + ", sentencias="
				+ getSentencias() + "]";
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

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
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
