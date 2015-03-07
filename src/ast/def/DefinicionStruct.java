package ast.def;

import java.util.List;

public class DefinicionStruct implements Definicion {
	
	public String nombre;
	public List<Campo> listaCampos;
	
	public DefinicionStruct(String nombre, List<Campo> listaCampos) {
		this.nombre = nombre;
		this.listaCampos = listaCampos;
	}

	@Override
	public String toString() {
		return "DefinicionStruct [nombre=" + nombre + ", listaCampos="
				+ listaCampos + "]";
	}

}
