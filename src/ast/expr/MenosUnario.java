package ast.expr;


public class MenosUnario implements Expresion {

	public Expresion operando; 
	public String operador;
	
	public MenosUnario(Expresion operando){
		this.operando = operando; 
		this.operador = "-";
	}

	@Override
	public String toString() {
		return "MenosUnario [operando=" + operando + ", operador=" + operador
				+ "]";
	}
}
