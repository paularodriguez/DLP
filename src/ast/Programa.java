package ast;

import java.util.List;

import ast.def.Definicion;

public class Programa implements NodoAST {

	public List<Definicion> definiciones;
	
	public Programa(List<Definicion> definiciones){
		this.definiciones = definiciones;
	}
}
