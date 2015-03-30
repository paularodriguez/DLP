package ast.def;

import java.util.List;

import visitor.Visitor;
import ast.sent.Sentencia;
import ast.tipos.Tipo;

public class DefinicionFuncion implements Definicion {

	private Tipo retorno;
	private String nombre;
	private List<DefinicionVariable> parametros;
	private List<DefinicionVariable> definicionesVariable;
	private List<Sentencia> sentencias;

	public DefinicionFuncion(Tipo retorno, String nombre,
			List<DefinicionVariable> parametros,
			List<DefinicionVariable> defVariables, List<Sentencia> sentencias) {
		this.setRetorno(retorno);
		this.setNombre(nombre);
		this.setParametros(parametros);
		this.setDefinicionesVariable(defVariables);
		this.setSentencias(sentencias);
	}

	@Override
	public String toString() {
		return "DefinicionFuncion [retorno=" + retorno + ", nombre=" + nombre
				+ ", parametros=" + parametros + ", definicionesVariable="
				+ definicionesVariable + ", sentencias=" + sentencias + "]";
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}

	public List<DefinicionVariable> getDefinicionesVariable() {
		return definicionesVariable;
	}

	public void setDefinicionesVariable(
			List<DefinicionVariable> definicionesVariable) {
		this.definicionesVariable = definicionesVariable;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	public Tipo getRetorno() {
		return retorno;
	}

	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DefinicionVariable> getParametros() {
		return parametros;
	}

	public void setParametros(List<DefinicionVariable> parametros) {
		this.parametros = parametros;
	}

	@Override
	public Tipo getTipo() {
		return retorno;
	}

}
