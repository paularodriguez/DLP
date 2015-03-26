package ast.def;

import java.util.List;

import ast.NodoAST;

public interface Definicion extends NodoAST {
	String getNombre();

	List<DefinicionVariable> getParametros();
}
