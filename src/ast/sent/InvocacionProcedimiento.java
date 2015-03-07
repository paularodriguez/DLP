package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class InvocacionProcedimiento implements Sentencia {

	private String nombre;
	private List<Expresion> expresiones;

	public InvocacionProcedimiento(String nombre, List<Expresion> expresiones) {
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "InvocacionProcedimiento [nombre=" + nombre + ", expresiones="
				+ expresiones + "]";
	}

}
