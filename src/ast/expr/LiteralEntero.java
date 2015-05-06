package ast.expr;

import visitor.Visitor;

public class LiteralEntero extends AbstractExpresion {

	private int valor;

	public LiteralEntero(int valor, int linea, int columna) {
		super(linea, columna);
		this.setValor(valor);
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + getValor() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public int valor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getValor(){
		return valor;
	}

}
