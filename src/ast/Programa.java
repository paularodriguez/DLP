package ast;

import java.util.List;

import visitor.Visitor;
import ast.def.Definicion;

public class Programa extends AbstractNodoAST {

	public List<Definicion> definiciones;
	
	public Programa(List<Definicion> definiciones, int linea, int columna){
		super(linea,columna);
		this.definiciones = definiciones;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}
}
