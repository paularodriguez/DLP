package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class Read implements Sentencia {

	public List<Expresion> expresiones; 
		
	public Read(List<Expresion> expresiones){
		this.expresiones = expresiones;
	}

	public Read(Expresion val_peek) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + "]";
	}
	
	
}
