package ast.expr;

import ast.tipos.Tipo;

public class Cast implements Expresion {

	private Tipo tipo; 
	private Expresion expresion; 
	
	public Cast(Tipo tipo, Expresion expresion){
		this.tipo = tipo; 
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", expresion=" + expresion + "]";
	}
	
}
