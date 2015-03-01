package ast.expr;



public class Variable implements Expresion {

	public String nombre; 
	
	public Variable(String nombre){
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Variable [nombre=" + nombre + "]";
	}
}
