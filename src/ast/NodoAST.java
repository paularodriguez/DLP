package ast;

import visitor.Visitor;

public interface NodoAST {
	
	public abstract void acepta(Visitor v);
	
	public int getLinea();
	
	public int getColumna();
	
}
