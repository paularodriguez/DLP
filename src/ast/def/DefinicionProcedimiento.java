package ast.def;

import java.util.List;

import visitor.Visitor;
import ast.sent.Sentencia;

public class DefinicionProcedimiento implements Definicion {
	
	private String nombre;
	private List<DefinicionVariable> parametros;
	private List<DefinicionVariable> definicionesVariable;
	private List<Sentencia>	sentencias;
	
	public DefinicionProcedimiento(String nombre, List<DefinicionVariable> parametros, List<DefinicionVariable> definicionesVariable, List<Sentencia> sentencias){
		this.setNombre(nombre);
		this.setParametros(parametros);
		this.setDefinicionesVariable(definicionesVariable);
		this.setSentencias(sentencias);
	}

	@Override
	public String toString() {
		return "DefinicionProcedimiento [nombre=" + getNombre() + ", parametros="
				+ getParametros() + ", definicionesVariable=" + getDefinicionesVariable()
				+ ", sentencias=" + getSentencias() + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public List<DefinicionVariable> getParametros() {
		return parametros;
	}

	public void setParametros(List<DefinicionVariable> parametros) {
		this.parametros = parametros;
	}

	public List<DefinicionVariable> getDefinicionesVariable() {
		return definicionesVariable;
	}

	public void setDefinicionesVariable(List<DefinicionVariable> definicionesVariable) {
		this.definicionesVariable = definicionesVariable;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
