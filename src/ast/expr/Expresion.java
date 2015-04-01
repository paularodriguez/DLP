package ast.expr;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Expresion extends NodoAST{

	public void setLValue(boolean lvalue);
	
	public boolean getLValue();
	
	
	public Tipo getTipo();
	

	public void setTipo(Tipo tipo);
}
