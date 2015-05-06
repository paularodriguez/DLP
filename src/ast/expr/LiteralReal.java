package ast.expr;

import visitor.Visitor;


public class LiteralReal extends AbstractExpresion{

	private double valor;
	
	public LiteralReal (Double d, int linea, int columna){
		super(linea, columna);
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
	
}
