package ast.tipos;


public class TipoEntero  implements Tipo {

	private static TipoEntero instancia = null;
	
	public TipoEntero(){
		
	}
	
	@Override
	public String toString() {
		return "Tipo Entero";
	}

	public static Tipo getInstancia() {
		if(instancia == null)
			instancia = new TipoEntero();
		return instancia;
	}
}
