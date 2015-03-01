package ast.expr;


public class Logica implements Expresion {

	private Expresion operando1;
	private Expresion operando2;
	private String operador;
	
	public Logica (Expresion op1, String operador, Expresion op2){
		this.operando1 = op1; 
		this.operando2 = op2;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "Logica [operando1=" + operando1 + ", operando2=" + operando2
				+ ", operador=" + operador + "]";
	}
}
