package ast.tipos;

import visitor.Visitor;

public class TipoEntero  implements Tipo {

	private static TipoEntero instancia = null;

	public TipoEntero() {

	}

	@Override
	public String toString() {
		return "Tipo Entero";
	}

	public static Tipo getInstancia() {
		if (instancia == null)
			instancia = new TipoEntero();
		return instancia;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public String sufijo() {
		return "i";
	}

	@Override
	public String getMAPLName() {
		return "int";
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
