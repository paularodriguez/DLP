package ast.expr;

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
	
	
}
