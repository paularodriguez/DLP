package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class While implements Sentencia{

	private Expresion expresion; 
	private List<Sentencia> sentencias;
	
	public While(Expresion expresion, List<Sentencia> listaSentencias){
		this.expresion = expresion;
		this.sentencias = listaSentencias;
	}

	@Override
	public String toString() {
		return "While [expresion=" + expresion + ", sentencias=" + sentencias
				+ "]";
	}
		
}
