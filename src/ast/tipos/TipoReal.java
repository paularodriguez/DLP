package ast.tipos;

import visitor.Visitor;

public class TipoReal implements Tipo {

	private static TipoReal instancia = null;

	public TipoReal() {
		
	}

	@Override
	public String toString() {
		return "Tipo Real";
	}

	public static Tipo getInstancia() {
		if (instancia == null)
			instancia = new TipoReal();
		return instancia;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public String sufijo() {
		return "f";
	}

	@Override
	public String getMAPLName() {
		return "real";
	}
	
	@Override
	public boolean esPrimitivo() {
		return true;
	}

	@Override
	public int getLinea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return 0;
	}

}
