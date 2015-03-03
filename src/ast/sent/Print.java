package ast.sent;


public class Print implements Sentencia {

	public String expresiones; 
	
	public Print(String expresiones){
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Print [expresiones=" + expresiones + "]";
	}
	
}
