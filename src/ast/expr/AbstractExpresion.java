package ast.expr;

import ast.AbstractNodoAST;
import ast.tipos.Tipo;

public abstract class AbstractExpresion extends AbstractNodoAST implements Expresion {

	public AbstractExpresion(int linea, int columna) {
		super(linea, columna);
	}

	private Tipo tipo;
	private boolean lvalue;
	private boolean visitaValor;
	private boolean visitaDireccion;
	
	
	@Override
	public void setLValue(boolean lvalue) {
		this.lvalue = lvalue;
	}

	@Override
	public boolean getLValue() {
		return lvalue;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public void setVisitaValor(boolean valor){
		this.visitaValor = valor;
	}

	public void setVisitaDireccion(boolean direccion){
		this.visitaDireccion = direccion;
	}
	
	public boolean getVisitaValor(){
		return visitaValor;
	}
	
	public boolean getVisitaDireccion(){
		return visitaDireccion;
	}

}
