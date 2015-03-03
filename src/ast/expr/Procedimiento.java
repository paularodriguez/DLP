package ast.expr;


public class Procedimiento {
	private String nombre;
	private Object parametros;
	private Object sentencias;

	
	public Procedimiento(String nombre, Object parametros, Object sentencias) {
		this.nombre = nombre;
		this.parametros = parametros;
		this.sentencias = sentencias;
	}

	

	@Override
	public String toString() {
		return "Procedimiento [nombre=" + nombre + ", parametros=" + parametros
				+ ", sentencias=" + sentencias + "]";
	}

	

}
