package ast.expr;

import ast.tipos.Tipo;
import visitor.Visitor;


public class Logica implements Expresion {

	private Expresion operando1;
	private Expresion operando2;
	private String operador;
	
	private Tipo tipo;
	
	public Logica (Expresion op1, String operador, Expresion op2){
		this.setOperando1(op1); 
		this.setOperando2(op2);
		this.setOperador(operador);
	}

	@Override
	public String toString() {
		return "Logica [operando1=" + getOperando1() + ", operando2=" + getOperando2()
				+ ", operador=" + getOperador() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Expresion getOperando1() {
		return operando1;
	}

	public void setOperando1(Expresion operando1) {
		this.operando1 = operando1;
	}

	public Expresion getOperando2() {
		return operando2;
	}

	public void setOperando2(Expresion operando2) {
		this.operando2 = operando2;
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
		return tipo;
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
