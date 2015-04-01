package ast.sent;

import ast.def.Definicion;

public abstract class AbstractSentencia implements Sentencia {

	private Definicion definicionFuncion;
	
	@Override
	public Definicion getDefinicionFuncion() {
		return definicionFuncion;
	}

	@Override
	public void setDefinicionFuncion(Definicion definicion) {
		this.definicionFuncion = definicion;
	}

}
