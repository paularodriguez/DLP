package ast.sent;

import ast.NodoAST;
import ast.def.Definicion;

public interface Sentencia extends NodoAST{
	
	Definicion getDefinicionFuncion();
	
	void setDefinicionFuncion(Definicion definicion);

}
