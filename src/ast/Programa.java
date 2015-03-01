package ast;

import java.util.List;

import ast.sent.DefinicionVariable;

public class Programa implements NodoAST {

	//public List<Sentencia> sentencias;
	public List<DefinicionVariable> declaraciones;
	
	public Programa(List<DefinicionVariable> declaraciones){
		this.declaraciones = declaraciones;
		//this.sentencias = sentencias;
	}
}
