package ast.tipos;

import visitor.Visitor;

/**
 * Clase que no se utiliza a partir de la fase de identificación.
 * @author uo224071
 *
 */
public class TipoStruct extends AbstractTipo {

	private String nombre;

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
	public int size() {
		return 0;
	}

	@Override
	public String sufijo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMAPLName() {
		// TODO Auto-generated method stub
		return null;
	}

}
