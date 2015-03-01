package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class Lectura implements Sentencia {

	public List<Expresion> expresiones; 
		
	public Lectura(List<Expresion> expresiones){
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + "]";
	}
	
	
}
