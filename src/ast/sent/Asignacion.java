package ast.sent;

import ast.expr.Expresion;

public class Asignacion implements Sentencia  {
	
	public Expresion izquierda;
	public Expresion derecha; 
	
	
	public Asignacion(Expresion izquierda, Expresion derecha){
		this.izquierda = izquierda;
		this.derecha = derecha;
	}


	@Override
	public String toString() {
		return "Asignacion [izquierda=" + izquierda + ", derecha=" + derecha
				+ "]";
	}
	
}
