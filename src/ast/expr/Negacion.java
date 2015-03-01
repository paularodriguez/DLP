package ast.expr;


public class Negacion implements Expresion {

	private Expresion expresion; 
	
	public Negacion (Expresion expresion){
		this.expresion = expresion;		
	}

	@Override
	public String toString() {
		return "Negacion [expresion=" + expresion + "]";
	}
	
	
}
