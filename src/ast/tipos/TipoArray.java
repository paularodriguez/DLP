package ast.tipos;

public class TipoArray implements Tipo {

	private int tamaño;
	private Tipo tipo;

	public TipoArray(int tamaño, Tipo tipo) {
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}

	public static Object crearArray(Tipo tipo, Integer tamaño) {

		if (tipo instanceof TipoArray) {
			TipoArray tipoFinal = (TipoArray) tipo;
			while (tipoFinal.tipo instanceof TipoArray) {
				tipoFinal = (TipoArray) (tipoFinal.tipo);
			}

			Tipo tipoArray = new TipoArray(tamaño, tipoFinal.tipo);
			tipoFinal.tipo = tipoArray;
			return tipo;
		} else {
			return new TipoArray(tamaño, tipo);
		}

	}
}
