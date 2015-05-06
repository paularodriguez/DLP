package ast.expr;

import visitor.Visitor;


public class LiteralCaracter extends AbstractExpresion {

	private Character caracter;
	
	
	public LiteralCaracter(Character caracter, int linea, int columna) {
		super(linea, columna);
		this.setCaracter(caracter);
	}

	@Override
	public String toString() {
		return "LiteralCaracter [caracter=" + getCaracter() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Character getCaracter() {
		return caracter;
	}

	public void setCaracter(Character caracter) {
		this.caracter = caracter;
	}

}
