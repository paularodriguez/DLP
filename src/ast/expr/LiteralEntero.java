package ast.expr;


public class LiteralEntero implements Expresion {

	public int valor;
	
	public LiteralEntero (int valor){
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + "]";
	}
	
}
