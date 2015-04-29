package ast.expr;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Expresion extends NodoAST{

	public void setLValue(boolean lvalue);
	
	public boolean getLValue();
	
	
	public Tipo getTipo();
	

	public void setTipo(Tipo tipo);

	public void setVisitaValor(boolean b);

	public void setVisitaDireccion(boolean b);
	
	public boolean getVisitaValor();
	
	public boolean getVisitaDireccion();
	
}
