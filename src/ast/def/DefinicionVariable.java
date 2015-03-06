package ast.def;

import ast.tipos.Tipo;

public class DefinicionVariable implements Definicion{
	
	private Tipo tipo; 
	private String nombre;
	
	public DefinicionVariable(Tipo tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "DefinicionVariable [tipo=" + tipo + ", nombreVariable ="
				+ nombre + "]";
	}
}
