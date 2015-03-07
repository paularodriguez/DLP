package ast.def;

import java.util.List;

import ast.tipos.Tipo;

public class DefinicionVariable implements Definicion{
	
	private Tipo tipo;
	private List<Integer> dimensiones;
	private String nombre;
	
	public DefinicionVariable(Tipo tipo, List<Integer> dimensiones, String nombre) {
		this.tipo = tipo;
		this.dimensiones = dimensiones;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "DefinicionVariable [tipo=" + tipo + ", dimensiones="
				+ dimensiones + ", nombre=" + nombre + "]";
	}
}
