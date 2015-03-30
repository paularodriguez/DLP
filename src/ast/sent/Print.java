package ast.sent;

import visitor.Visitor;
import ast.def.Definicion;
import ast.expr.Expresion;


public class Print implements Sentencia {

	private Expresion expresion;
	private Definicion definicionFuncion; 
	
	public Print(Expresion expresiones){
		this.setExpresion(expresiones);
	}

	@Override
	public String toString() {
		return "Print [expresiones=" + getExpresion() + "]";
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
