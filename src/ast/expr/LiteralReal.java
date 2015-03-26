package ast.expr;

import ast.tipos.Tipo;
import ast.tipos.TipoReal;
import visitor.Visitor;


public class LiteralReal implements Expresion{

	private double valor;
	
	public LiteralReal (Double d){
		this.setValor(d);
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + getValor() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return TipoReal.getInstancia();
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
