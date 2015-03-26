package ast.tipos;

import java.util.List;

import visitor.Visitor;

public class TipoArray implements Tipo {

	private Tipo tipo;
	private int tamaño;

	public TipoArray(Tipo tipo, List<Integer> dimensiones) {
		
	}

	public static TipoArray crearArray(Tipo tipo, List<Integer> dimensiones) {
		if (dimensiones.size() == 1){
			return new TipoArray(tipo, dimensiones.get(0));
		}
		else{
			TipoArray tA = new TipoArray();
			tA.setTamaño(dimensiones.get(0));
			dimensiones.remove(0);
			tA.setTipo(crearArray(tipo, dimensiones));
			return tA;
		}
	}

	public TipoArray(Tipo tipo, Integer tamaño) {
		this.setTipo(tipo);
		this.setTamaño(tamaño);
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


	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	@Override
	public boolean esPrimitivo() {
		return false;
	}

	
}
