package ast.expr;

import ast.tipos.Tipo;
import visitor.Visitor;


public class Aritmetica implements Expresion {

	private Expresion op1;
	private Expresion op2;
	private String operador; 
	
	public Aritmetica(Expresion op1, String operador, Expresion op2){
		this.setOp1(op1);
		this.setOp2(op2);
		this.setOperador(operador);
	}

	@Override
	public String toString() {
		return "Aritmetica [op1=" + getOp1() + ", op2=" + getOp2() + ", operador="
				+ getOperador() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Expresion getOp1() {
		return op1;
	}

	public void setOp1(Expresion op1) {
		this.op1 = op1;
	}

	public Expresion getOp2() {
		return op2;
	}

	public void setOp2(Expresion op2) {
		this.op2 = op2;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return op1.getTipo();
	}
}
