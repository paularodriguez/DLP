package ast.sent;

import ast.expr.Expresion;

public class Read implements Sentencia {

	public Expresion expresion; 
		
	public Read(Expresion expresion){
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Lectura [expresion=" + expresion+ "]";
	}
	
	
}
