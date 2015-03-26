package ast.def;

import java.util.List;

import visitor.Visitor;
import ast.tipos.Tipo;

public class DefinicionStruct implements Definicion,Tipo {
	
	private String nombre;
	private List<Campo> listaCampos;
	
	public DefinicionStruct(String nombre, List<Campo> listaCampos) {
		this.setNombre(nombre);
		this.setListaCampos(listaCampos);
	}

	@Override
	public String toString() {
		return "DefinicionStruct [nombre=" + getNombre() + ", listaCampos="
				+ getListaCampos() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public List<Campo> getListaCampos() {
		return listaCampos;
	}

	public void setListaCampos(List<Campo> listaCampos) {
		this.listaCampos = listaCampos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean esPrimitivo() {
		return false;
	}

}
