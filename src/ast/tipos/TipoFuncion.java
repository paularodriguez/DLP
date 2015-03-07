package ast.tipos;

import java.util.List;

import ast.def.DefinicionVariable;

public class TipoFuncion implements Tipo {

	private Tipo tipo;
	private String nombre;
	private List<DefinicionVariable> parametros;

	public TipoFuncion(Tipo tipo, String nombre,
			List<DefinicionVariable> parametros) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipo=" + tipo + ", nombre=" + nombre
				+ ", parametros=" + parametros + "]";
	}

}
