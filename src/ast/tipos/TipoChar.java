package ast.tipos;


public class TipoChar  implements Tipo {

	private static TipoChar instancia = null;
	
	public TipoChar() {
	
	}
	
	@Override
	public String toString() {
		return "Tipo Char";
	}
	
	public static Tipo getInstancia() {
		if(instancia == null)
			instancia = new TipoChar();
		return instancia;
	}

}
