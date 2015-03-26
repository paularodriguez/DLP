package ast.def;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Campo implements Definicion {

	private Tipo tipo;
	private String nombre;
	
	public Campo(Tipo tipo, String nombre) {
		this.setTipo(tipo);
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "Campo [tipo=" + getTipo() + ", nombreVariable ="
				+ getNombre() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
