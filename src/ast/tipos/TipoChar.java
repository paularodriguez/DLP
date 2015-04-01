package ast.tipos;

import visitor.Visitor;

public class TipoChar extends AbstractTipo {

	private static TipoChar instancia = null;

	public TipoChar() {

	}

	@Override
	public String toString() {
		return "Tipo Char";
	}

	public static Tipo getInstancia() {
		if (instancia == null)
			instancia = new TipoChar();
		return instancia;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

}
