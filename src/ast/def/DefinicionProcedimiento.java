package ast.def;

import java.util.List;

import ast.sent.Sentencia;

public class DefinicionProcedimiento implements Definicion {
	
	private String nombre;
	private List<DefinicionVariable> parametros;
	private List<DefinicionVariable> definicionesVariable;
	private List<Sentencia>	sentencias;
	
	public DefinicionProcedimiento(String nombre, List<DefinicionVariable> parametros, List<DefinicionVariable> definicionesVariable, List<Sentencia> sentencias){
		this.nombre = nombre;
		this.parametros = parametros;
		this.definicionesVariable = definicionesVariable;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "DefinicionProcedimiento [nombre=" + nombre + ", parametros="
				+ parametros + ", definicionesVariable=" + definicionesVariable
				+ ", sentencias=" + sentencias + "]";
	}

	
}
