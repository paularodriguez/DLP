package ast.tipos;

import java.util.List;

public class TipoStruct {

	String nombre;
	List campos;
	
	public TipoStruct(String nombre, List listaCampos) {
		this.nombre = nombre;
		this.campos = listaCampos;
	}

}
