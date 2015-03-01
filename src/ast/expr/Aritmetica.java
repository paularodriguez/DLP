package ast.expr;


public class Aritmetica implements Expresion {

	public Expresion op1;
	public Expresion op2;
	public String operador; 
	
	public Aritmetica(Expresion op1, String operador, Expresion op2){
		this.op1 = op1;
		this.op2 = op2;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "Aritmetica [op1=" + op1 + ", op2=" + op2 + ", operador="
				+ operador + "]";
	}
}
