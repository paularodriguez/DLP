package ast.def;

import visitor.Visitor;
import ast.tipos.Tipo;

public class Campo implements Definicion,Tipo {

	private Tipo tipo;
	private String nombre;
	
	private int direccion;
	
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

	@Override
	public boolean esPrimitivo() {
		return false;
	}

	@Override
	public void setPrimitivo(boolean primitivo) {
	}

	@Override
	public int size() {
		return tipo.size();
	}

	@Override
	public int getDireccion() {
		return direccion;
	}

	@Override
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	@Override
	public String sufijo() {
		return tipo.sufijo();
	}

	@Override
	public String getMAPLName() {
		// TODO Auto-generated method stub
		return null;
	}

}
