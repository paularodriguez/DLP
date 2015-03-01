package ast.tipos;


public class TipoArray  implements Tipo {

	private int tamaño;
	private Tipo tipo;


	public TipoArray(int tamaño, Tipo tipo){
		this.tamaño = tamaño;
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}



	public static Object crearArray(Tipo tipo, Integer tamaño){
		return new TipoArray(tamaño, tipo);
	}

}
