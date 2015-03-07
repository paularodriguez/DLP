package ast.tipos;

public class TipoArray implements Tipo {

	private int tama�o;
	private Tipo tipo;

	public TipoArray(int tama�o, Tipo tipo) {
		this.tama�o = tama�o;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
	}

	public static Object crearArray(Tipo tipo, Integer tama�o) {

		if (tipo instanceof TipoArray) {
			TipoArray tipoFinal = (TipoArray) tipo;
			while (tipoFinal.tipo instanceof TipoArray) {
				tipoFinal = (TipoArray) (tipoFinal.tipo);
			}

			Tipo tipoArray = new TipoArray(tama�o, tipoFinal.tipo);
			tipoFinal.tipo = tipoArray;
			return tipo;
		} else {
			return new TipoArray(tama�o, tipo);
		}

	}
}
