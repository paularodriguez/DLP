package ast.expr;

import java.util.List;

import visitor.Visitor;

public class InvocacionFuncion implements Expresion {

	private String identificador; 
	private List<Expresion> listaExpresiones;
	
	public InvocacionFuncion(String identificador, List<Expresion> expresiones){
		this.setIdentificador(identificador); 
		this.setListaExpresiones(expresiones);
	}

	@Override
	public String toString() {
		return "InvocacionFuncion [identificador=" + getIdentificador()
				+ ", listaExpresiones=" + getListaExpresiones() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public List<Expresion> getListaExpresiones() {
		return listaExpresiones;
	}

	public void setListaExpresiones(List<Expresion> listaExpresiones) {
		this.listaExpresiones = listaExpresiones;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
}
