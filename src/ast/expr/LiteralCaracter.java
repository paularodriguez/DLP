package ast.expr;


public class LiteralCaracter implements Expresion {

	private Character caracter;
	
	public LiteralCaracter(Character caracter) {
		this.caracter = caracter;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [caracter=" + caracter + "]";
	}

}
