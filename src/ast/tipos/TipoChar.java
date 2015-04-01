package ast.tipos;

import visitor.Visitor;


public class TipoChar  implements Tipo {

	private static TipoChar instancia = null;
	private boolean primitivo;
	
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

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
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
