package ast.expr;


public class LiteralReal implements Expresion{

	public double valor; 
	
	public LiteralReal (Double d){
		this.valor = d;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + "]";
	}
	
}
