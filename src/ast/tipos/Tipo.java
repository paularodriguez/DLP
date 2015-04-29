package ast.tipos;

import ast.NodoAST;

public interface Tipo extends NodoAST {
	
	public boolean esPrimitivo();
	
	void setPrimitivo(boolean primitivo);
	
	int size();
	
	String sufijo();
	
	String getMAPLName();

}
