package ast.expr;

import ast.tipos.Tipo;
import ast.tipos.TipoChar;
import visitor.Visitor;


public class LiteralCaracter implements Expresion {

	private Character caracter;
	
	public LiteralCaracter(Character caracter) {
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

	@Override
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return TipoChar.getInstancia();
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
