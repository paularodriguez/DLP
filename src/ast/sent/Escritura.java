package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class Escritura implements Sentencia {

	public List<Expresion> expresiones; 
	
	public Escritura(List<Expresion> expresiones){
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + "]";
	}
	
}
