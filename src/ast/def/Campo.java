package ast.def;

import ast.tipos.Tipo;

public class Campo {

	Tipo tipo;
	String nombre;
	
	public Campo(String nombre, Tipo tipo) {
		this.tipo = tipo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Campo [tipo=" + tipo + ", nombreVariable ="
				+ nombre + "]";
	}

}
