package ast.def;

import java.util.List;

import visitor.Visitor;
import ast.tipos.Tipo;

public class DefinicionVariable implements Definicion{
	
	private Tipo tipo;
	private String nombre;
	
	public DefinicionVariable(Tipo tipo, String nombre) {
		this.setTipo(tipo);
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "DefinicionVariable [tipo=" + getTipo() + ", nombre=" + getNombre() + "]";
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

	@Override
	public List<DefinicionVariable> getParametros() {
		// TODO Auto-generated method stub
		return null;
	}

}
