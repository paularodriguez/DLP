package ast.tipos;

import visitor.Visitor;


public class TipoReal implements Tipo {

	private static TipoReal instancia = null;
	
	public TipoReal(){
		
	}

	@Override
	public String toString() {
		return "Tipo Real";
	}
	
	public static Tipo getInstancia() {
		if(instancia == null)
			instancia = new TipoReal();
		return instancia;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}
}
