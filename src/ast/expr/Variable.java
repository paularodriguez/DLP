package ast.expr;

import ast.def.Definicion;
import visitor.Visitor;



public class Variable implements Expresion {

	private String nombre;
	private Definicion definicion;
	
	public Variable(String nombre){
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "Variable [nombre=" + getNombre() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}
}
