package ast;


public abstract class AbstractNodoAST implements NodoAST {
	
	private int linea;
	private int columna;

	public AbstractNodoAST(int linea, int columna) {
		this.linea = linea;
		this.columna = columna;
	}

	public int getLinea(){
		return linea;
	}
	
	public int getColumna(){
		return columna;
	}
	
	public void setLinea(int linea) {
		this.linea = linea;
	}
	
	public void setColumna(int columna) {
		this.columna = columna;
	}	
}
