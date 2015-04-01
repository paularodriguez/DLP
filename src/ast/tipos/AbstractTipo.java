package ast.tipos;

public abstract class AbstractTipo implements Tipo{

	private boolean primitivo;

	@Override
	public boolean esPrimitivo() {
		return primitivo;
	}

	@Override
	public void setPrimitivo(boolean primitivo) {
		this.primitivo = primitivo;
	}
}
