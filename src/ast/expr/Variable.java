package ast.expr;

import visitor.Visitor;



public class Variable implements Expresion {

	private String nombre; 
	
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
}
