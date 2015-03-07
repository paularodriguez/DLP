package gestorErrores;

public class GestorErrores {
	private int errores = 0;

	public void error(String msg) {
		System.out.println(msg);
		errores++;
	}

	public boolean hayErrores() {
		return errores > 0;
	}
}
