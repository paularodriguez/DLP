package ast.expr;


public class Comparacion implements Expresion {
	
	private Expresion operando1; 
	private Expresion operando2; 
	private String operador;
	
	public Comparacion(Expresion op1, String operador, Expresion op2){
		this.operando1 = op1;
		this.operador = operador;
		this.operando2 = op2;
	}

	@Override
	public String toString() {
		return "Comparacion [operando1=" + operando1 + ", operando2="
				+ operando2 + ", operador=" + operador + "]";
	}
	
	

}
