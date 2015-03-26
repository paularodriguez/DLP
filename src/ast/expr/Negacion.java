package ast.expr;

import ast.tipos.Tipo;
import visitor.Visitor;


public class Negacion implements Expresion {

	private Expresion expresion; 
		
	public Negacion (Expresion expresion){
		this.setExpresion(expresion);		
	}

	@Override
	public String toString() {
		return "Negacion [expresion=" + getExpresion() + "]";
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
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return expresion.getTipo();
	}

	@Override
	public void setLValue(boolean lvalue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTipo(Tipo tipo) {
		// TODO Auto-generated method stub
		
	}
	
	
}
