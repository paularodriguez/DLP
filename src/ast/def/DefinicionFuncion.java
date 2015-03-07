package ast.def;

import java.util.List;

import ast.sent.Sentencia;
import ast.tipos.Tipo;

public class DefinicionFuncion implements Definicion {

	private Tipo tipoFuncion;
	private List<DefinicionVariable> definicionesVariable;
	private List<Sentencia> sentencias;

	public DefinicionFuncion(Tipo tipoFuncion,
			List<DefinicionVariable> defVariables, List<Sentencia> sentencias) {
		this.tipoFuncion = tipoFuncion;
		this.definicionesVariable = defVariables;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "DefinicionFuncion [tipoFuncion=" + tipoFuncion
				+ ", definicionesVariable=" + definicionesVariable
				+ ", sentencias=" + sentencias + "]";
	}

}
