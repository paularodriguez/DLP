package ast.def;

import java.util.List;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Definicion extends NodoAST {
	String getNombre();
	Tipo getTipo();
	List<DefinicionVariable> getParametros();
}
