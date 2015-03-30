package ast.sent;

import visitor.Visitor;
import ast.def.Definicion;
import ast.expr.Expresion;

public class Read implements Sentencia {

	private Expresion expresion;
	private Definicion definicionFuncion; 
		
	public Read(Expresion expresion){
		this.setExpresion(expresion);
	}

	@Override
	public String toString() {
		return "Lectura [expresion=" + getExpresion()+ "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);	
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}
	
	@Override
	public Definicion getDefinicionFuncion() {
		return definicionFuncion;
	}

	@Override
	public void setDefinicionFuncion(Definicion definicion) {
		this.definicionFuncion = definicion;
	}
	
	
}
