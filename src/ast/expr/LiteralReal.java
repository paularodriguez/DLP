package ast.expr;

import visitor.Visitor;


public class LiteralReal extends AbstractExpresion{

	private double valor;
	
	public LiteralReal (Double d){
		this.setValor(d);
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + getVisitaValor() + "]";
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
	
}
