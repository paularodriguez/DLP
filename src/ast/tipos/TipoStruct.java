package ast.tipos;

import visitor.Visitor;

public class TipoStruct implements Tipo {

	private String nombre;
	private boolean primitivo;

	public TipoStruct(String nombre) {
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "TipoStruct [nombre=" + getNombre() + "]";
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

	@Override
	public boolean esPrimitivo() {
		return primitivo;
	}

	@Override
	public void setPrimitivo(boolean primitivo) {
		this.primitivo = primitivo;
	}

}
