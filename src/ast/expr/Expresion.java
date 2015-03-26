package ast.expr;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Expresion extends NodoAST{

	public boolean getLValue();
	public Tipo getTipo();
}
