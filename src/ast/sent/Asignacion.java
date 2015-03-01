package ast.sent;

import ast.expr.Expresion;

public class Asignacion implements Sentencia  {
	
	public Expresion derecha; 
	public Expresion izquierda;
	
	public Asignacion(Expresion derecha, Expresion izquierda){
		this.derecha = derecha;
		this.izquierda = izquierda;
	}

	@Override
	public String toString() {
		return "Asignacion [derecha=" + derecha + ", izquierda=" + izquierda
				+ "]";
	}
	
	

}
