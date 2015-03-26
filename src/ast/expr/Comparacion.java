package ast.expr;

import ast.tipos.Tipo;
import ast.tipos.TipoEntero;
import visitor.Visitor;


public class Comparacion implements Expresion {
	
	private Expresion operando1; 
	private Expresion operando2; 
	private String operador;
	
	public Comparacion(Expresion op1, String operador, Expresion op2){
		this.setOperando1(op1);
		this.setOperador(operador);
		this.setOperando2(op2);
	}

	@Override
	public String toString() {
		return "Comparacion [operando1=" + getOperando1() + ", operando2="
				+ getOperando2() + ", operador=" + getOperador() + "]";
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
		return TipoEntero.getInstancia();
	}
	
	

}
