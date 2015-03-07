package ast.tipos;


public class TipoStruct implements Tipo{

	String nombre;

	public TipoStruct(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoStruct [nombre=" + nombre + "]";
	}

	
}
