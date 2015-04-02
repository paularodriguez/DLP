package ast.sent;

import ast.def.DefinicionFuncion;

public abstract class AbstractSentencia implements Sentencia {

	private DefinicionFuncion definicionFuncion;
	
	@Override
	public DefinicionFuncion getDefinicionFuncion() {
		return definicionFuncion;
	}

	@Override
	public void setDefinicionFuncion(DefinicionFuncion definicion) {
		this.definicionFuncion = definicion;
	}

}
