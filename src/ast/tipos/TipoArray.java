package ast.tipos;

import java.util.List;

import visitor.Visitor;

public class TipoArray implements Tipo {

	private Tipo tipo;
	private int tama�o;

	public TipoArray(Tipo tipo, List<Integer> dimensiones) {
		
	}

	public static TipoArray crearArray(Tipo tipo, List<Integer> dimensiones) {
		if (dimensiones.size() == 1){
			return new TipoArray(tipo, dimensiones.get(0));
		}
		else{
			TipoArray tA = new TipoArray();
			tA.setTama�o(dimensiones.get(0));
			dimensiones.remove(0);
			tA.setTipo(crearArray(tipo, dimensiones));
			return tA;
		}
	}

	public TipoArray(Tipo tipo, Integer tama�o) {
		this.setTipo(tipo);
		this.setTama�o(tama�o);
	}

	public TipoArray() {

	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public int getTama�o() {
		return tama�o;
	}

	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}

	@Override
	public boolean esPrimitivo() {
		return false;
	}

	
}
