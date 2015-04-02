package ast.def;

import visitor.Visitor;
import ast.tipos.Tipo;

public class DefinicionVariable implements Definicion {

	private Tipo tipo;
	private String nombre;
	private boolean esParametro;

	public DefinicionVariable(Tipo tipo, String nombre) {
		this.setTipo(tipo);
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "DefinicionVariable [tipo=" + getTipo() + ", nombre="
				+ getNombre() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean EsParametro() {
		return esParametro;
	}

	public void setEsParametro(boolean esParametro) {
		this.esParametro = esParametro;
	}

}
