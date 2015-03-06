package ast.sent;

import ast.expr.Expresion;


public class Print implements Sentencia {

	public Expresion expresiones; 
	
	public Print(Expresion expresiones){
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Print [expresiones=" + expresiones + "]";
	}
	
}
