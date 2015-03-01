package ast.expr;

import java.util.List;

public class InvocacionFuncion implements Expresion {

	private String identificador; 
	private List<Expresion> listaExpresiones;
	
	public InvocacionFuncion(String identificador, List<Expresion> expresiones){
		this.identificador = identificador; 
		this.listaExpresiones = expresiones;
	}

	@Override
	public String toString() {
		return "InvocacionFuncion [identificador=" + identificador
				+ ", listaExpresiones=" + listaExpresiones + "]";
	}
	
}
