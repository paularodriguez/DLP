package ast.sent;

import java.util.List;

import visitor.Visitor;
import ast.def.Definicion;
import ast.expr.Expresion;

public class IF implements Sentencia {

	private Expresion expresion; 
	private List<Sentencia> sentenciasIF;
	private List<Sentencia> sentenciasElse;
	private Definicion definicionFuncion;
	
	public IF(Expresion expresion, List<Sentencia> sentenciasIf){
		this.setExpresion(expresion); 
		this.setSentenciasIF(sentenciasIf);
	}
	
	public IF(Expresion expresion, List<Sentencia> sentenciasIf, List<Sentencia> sentenciasElse){
		this.setExpresion(expresion); 
		this.setSentenciasIF(sentenciasIf);
		this.setSentenciasElse(sentenciasElse);
	}

	@Override
	public String toString() {
		return "IF [expresion=" + getExpresion() + ", sentenciasIF=" + getSentenciasIF()
				+ ", sentenciasElse=" + getSentenciasElse() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);	
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public List<Sentencia> getSentenciasIF() {
		return sentenciasIF;
	}

	public void setSentenciasIF(List<Sentencia> sentenciasIF) {
		this.sentenciasIF = sentenciasIF;
	}

	public List<Sentencia> getSentenciasElse() {
		return sentenciasElse;
	}

	public void setSentenciasElse(List<Sentencia> sentenciasElse) {
		this.sentenciasElse = sentenciasElse;
	}

	@Override
	public Definicion getDefinicionFuncion() {
		return definicionFuncion;
	}

	@Override
	public void setDefinicionFuncion(Definicion definicion) {
		this.definicionFuncion = definicion;
	}
	
}
