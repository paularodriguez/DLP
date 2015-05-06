package ast.tipos;

import ast.NodoAST;

public interface Tipo extends NodoAST {
	
	public boolean esPrimitivo();
		
	int size();
	
	String sufijo();
	
	String getMAPLName();

}
