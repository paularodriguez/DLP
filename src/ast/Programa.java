package ast;

import java.util.List;

import visitor.Visitor;
import ast.def.Definicion;

public class Programa implements NodoAST {

	public List<Definicion> definiciones;
	
	public Programa(List<Definicion> definiciones){
		this.definiciones = definiciones;
	}

	@Override
	public void acepta(Visitor v) {
		v.visit(this);
	}
}
