package ast.def;

import java.util.List;

import visitor.Visitor;
import ast.AbstractNodoAST;
import ast.tipos.Tipo;

public class DefinicionStruct extends AbstractNodoAST implements Definicion, Tipo {

	private String nombre;
	private List<Campo> listaCampos;

	private int direccion;

	public DefinicionStruct(String nombre, List<Campo> listaCampos, int linea, int columna) {
		super(linea, columna);
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
	public Tipo getTipo() {
		return this;
	}


	@Override
	public int size() {
		int size = 0;
		for (Campo c : getListaCampos()) {
			size += c.size();
		}
		return size;
	}

	@Override
	public int getDireccion() {
		return direccion;
	}

	@Override
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public Campo buscarCampoNombre(String id) {
		for (Campo c: listaCampos){
			if (c.getNombre().equals(id))
				return c;
		}
		return null;
	}

	@Override
	public String sufijo() {
		return nombre;
	}

	@Override
	public String getMAPLName() {
		return getNombre();
	}
	
	@Override
	public boolean esPrimitivo() {
		return false;
	}

}
