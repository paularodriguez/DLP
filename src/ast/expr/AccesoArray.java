package ast.expr;



public class AccesoArray implements Expresion {

	private Expresion izquierda; 
	private Expresion derecha;
	
	public AccesoArray(Expresion izquierda, Expresion derecha){
		this.izquierda = izquierda; 
		this.derecha = derecha; 
	}

	@Override
	public String toString() {
		return "AccesoArray [izquierda=" + izquierda + ", derecha=" + derecha
				+ "]";
	}
	
	

}
