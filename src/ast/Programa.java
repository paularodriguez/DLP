package ast;

import java.util.List;

import ast.def.Definicion;
import ast.def.DefinicionVariable;

public class Programa implements NodoAST {

	//public List<Sentencia> sentencias;
	public List<Definicion> declaraciones;
	
	public Programa(List<Definicion> definiciones){
		this.declaraciones = definiciones;
		//this.sentencias = sentencias;
	}
}
