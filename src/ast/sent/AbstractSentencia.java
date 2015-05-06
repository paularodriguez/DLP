package ast.sent;

import ast.AbstractNodoAST;
import ast.def.DefinicionFuncion;

public abstract class AbstractSentencia extends AbstractNodoAST implements
		Sentencia {

	public AbstractSentencia(int linea, int columna) {
		super(linea, columna);
	}

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
