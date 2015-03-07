package ast.sent;

import java.util.List;

import ast.expr.Expresion;

public class IF implements Sentencia {

	private Expresion expresion; 
	private List<Sentencia> sentenciasIF;
	private List<Sentencia> sentenciasElse;
	
	public IF(Expresion expresion, List<Sentencia> sentenciasIf){
		this.expresion = expresion; 
		this.sentenciasIF = sentenciasIf;
	}
	
	public IF(Expresion expresion, List<Sentencia> sentenciasIf, List<Sentencia> sentenciasElse){
		this.expresion = expresion; 
		this.sentenciasIF = sentenciasIf;
		this.sentenciasElse = sentenciasElse;
	}

	@Override
	public String toString() {
		return "IF [expresion=" + expresion + ", sentenciasIF=" + sentenciasIF
				+ ", sentenciasElse=" + sentenciasElse + "]";
	}

	
}
