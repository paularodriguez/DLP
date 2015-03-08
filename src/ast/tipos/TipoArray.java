package ast.tipos;

import java.util.List;

public class TipoArray implements Tipo {

	private Tipo tipo;
	private List<Integer> dimensiones;
	
	public TipoArray(Tipo tipo, List<Integer> dimensiones) {
		this.dimensiones = dimensiones;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tipo=" + tipo + ", dimensiones=" + dimensiones + "]";
	}
}
