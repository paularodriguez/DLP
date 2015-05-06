package ast.sent;

import java.util.List;

import visitor.Visitor;
import ast.def.DefinicionFuncion;
import ast.expr.Expresion;

public class InvocacionProcedimiento extends AbstractSentencia {

	private DefinicionFuncion definicion;
	private String nombre;
	private List<Expresion> expresiones;

	public InvocacionProcedimiento(String nombre, List<Expresion> expresiones, int linea, int columna) {
		super(linea,columna);
		this.setNombre(nombre);
		this.setExpresiones(expresiones);
	}

	@Override
	public String toString() {
		return "InvocacionProcedimiento [nombre=" + getNombre()
				+ ", expresiones=" + getExpresiones() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	public void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = expresiones;
	}

	public DefinicionFuncion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(DefinicionFuncion definicion) {
		this.definicion = definicion;
	}

}
