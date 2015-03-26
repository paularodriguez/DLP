package ast.expr;

import ast.tipos.Tipo;
import ast.tipos.TipoEntero;
import visitor.Visitor;


public class LiteralEntero implements Expresion {

	private int valor;
	
	public LiteralEntero (int valor){
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return TipoEntero.getInstancia();
	}
	
}
