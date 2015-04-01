package ast.tipos;

import visitor.Visitor;


public class TipoEntero  implements Tipo {

	private static TipoEntero instancia = null;
	private boolean primitivo;
	
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
