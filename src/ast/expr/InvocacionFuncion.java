package ast.expr;

import java.util.List;

import ast.def.Definicion;
import ast.def.DefinicionFuncion;
import ast.tipos.Tipo;
import visitor.Visitor;

public class InvocacionFuncion implements Expresion {

	private Definicion definicion;
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

	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	@Override
	public boolean getLValue() {
		return false;
	}

	@Override
	public Tipo getTipo() {
		return ((DefinicionFuncion) definicion).getRetorno();
	}
	
}
