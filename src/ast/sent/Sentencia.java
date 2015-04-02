package ast.sent;

import ast.NodoAST;
import ast.def.DefinicionFuncion;

public interface Sentencia extends NodoAST{
	
	DefinicionFuncion getDefinicionFuncion();
	
	void setDefinicionFuncion(DefinicionFuncion definicion);

}
