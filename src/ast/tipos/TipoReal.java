package ast.tipos;

import visitor.Visitor;

public class TipoReal extends AbstractTipo {

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

}
