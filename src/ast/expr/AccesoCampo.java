package ast.expr;

public class AccesoCampo implements Expresion {

	private Expresion izquierda;
	private Expresion derecha;

	public AccesoCampo(Expresion izquierda, Expresion derecha) {

		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	@Override
	public String toString() {
		return "AccesoCampo [izquierda=" + izquierda + ", derecha=" + derecha
				+ "]";
	}

}