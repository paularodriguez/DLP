package ast.def;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Definicion extends NodoAST {
	String getNombre();

	Tipo getTipo();
	
	int getDireccion();
	
	void setDireccion(int direccion);
}
