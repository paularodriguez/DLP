package visitor;

import ast.Programa;
import ast.def.Campo;
import ast.def.Definicion;
import ast.def.DefinicionFuncion;
import ast.def.DefinicionStruct;
import ast.def.DefinicionVariable;
import ast.expr.AccesoArray;
import ast.expr.AccesoCampo;
import ast.expr.Aritmetica;
import ast.expr.Cast;
import ast.expr.Comparacion;
import ast.expr.Expresion;
import ast.expr.InvocacionFuncion;
import ast.expr.LiteralCaracter;
import ast.expr.LiteralEntero;
import ast.expr.LiteralReal;
import ast.expr.Logica;
import ast.expr.Negacion;
import ast.expr.Variable;
import ast.sent.Asignacion;
import ast.sent.IF;
import ast.sent.InvocacionProcedimiento;
import ast.sent.Print;
import ast.sent.Read;
import ast.sent.Return;
import ast.sent.Sentencia;
import ast.sent.While;
import ast.tipos.TipoArray;
import ast.tipos.TipoChar;
import ast.tipos.TipoEntero;
import ast.tipos.TipoReal;
import ast.tipos.TipoStruct;

public class DefaultVisitor implements Visitor {

	@Override
	public Object visit(Programa node) {
		for (Definicion d : node.definiciones) {
			d.acepta(this);
		}
		return null;
	}

	@Override
	public Object visit(DefinicionFuncion node) {
		if (node.getRetorno() != null) {
			node.getRetorno().acepta(this);
		}

		for (DefinicionVariable param : node.getParametros()) {
			param.acepta(this);
		}

		for (DefinicionVariable defVar : node.getDefinicionesVariable()) {
			defVar.acepta(this);
		}

		for (Sentencia s : node.getSentencias()) {
			s.acepta(this);
		}

		return null;
	}

	@Override
	public Object visit(DefinicionStruct node) {
		for (Campo c : node.getListaCampos()) {
			c.acepta(this);
		}
		return null;
	}

	@Override
	public Object visit(DefinicionVariable node) {
		node.getTipo().acepta(this);
		return null;
	}

	@Override
	public Object visit(Campo node) {
		node.getTipo().acepta(this);
		return null;
	}

	@Override
	public Object visit(Asignacion node) {
		node.getIzquierda().acepta(this);
		node.getDerecha().acepta(this);

		return null;
	}

	@Override
	public Object visit(IF node) {
		node.getExpresion().acepta(this);

		if (node.getSentenciasIF() != null) {
			for (Sentencia s : node.getSentenciasIF()) {
				s.acepta(this);
			}
		}

		if (node.getSentenciasElse() != null) {
			for (Sentencia s : node.getSentenciasElse()) {
				s.acepta(this);
			}
		}

		return null;
	}

	@Override
	public Object visit(InvocacionProcedimiento node) {
		for (Expresion e : node.getExpresiones()) {
			e.acepta(this);
		}
		return null;
	}

	@Override
	public Object visit(Print node) {
		node.getExpresion().acepta(this);
		return null;
	}

	@Override
	public Object visit(Read node) {
		node.getExpresion().acepta(this);
		return null;
	}

	@Override
	public Object visit(Return node) {
		if (node.getExpresion() != null)
			node.getExpresion().acepta(this);
		return null;
	}

	@Override
	public Object visit(While node) {
		node.getExpresion().acepta(this);

		for (Sentencia s : node.getSentencias()) {
			s.acepta(this);
		}

		return null;
	}

	@Override
	public Object visit(AccesoArray node) {
		node.getIzquierda().acepta(this);
		node.getDerecha().acepta(this);
		return null;
	}

	@Override
	public Object visit(AccesoCampo node) {
		node.getIzquierda().acepta(this);
		node.getDerecha().acepta(this);
		return null;
	}

	@Override
	public Object visit(Aritmetica node) {
		node.getOp1().acepta(this);
		node.getOp2().acepta(this);
		return null;
	}

	@Override
	public Object visit(Cast node) {
		node.getTipoCast().acepta(this);
		node.getExpresion().acepta(this);
		return null;
	}

	@Override
	public Object visit(Comparacion node) {
		node.getOperando1().acepta(this);
		node.getOperando2().acepta(this);
		return null;
	}

	@Override
	public Object visit(InvocacionFuncion node) {
		for (Expresion e : node.getListaExpresiones()) {
			e.acepta(this);
		}
		return null;
	}

	@Override
	public Object visit(LiteralCaracter node) {
		return null;
	}

	@Override
	public Object visit(LiteralEntero node) {
		return null;
	}

	@Override
	public Object visit(LiteralReal node) {
		return null;
	}

	@Override
	public Object visit(Logica node) {
		node.getOperando1().acepta(this);
		node.getOperando2().acepta(this);
		return null;
	}

	@Override
	public Object visit(Negacion node) {
		node.getExpresion().acepta(this);
		return null;
	}

	@Override
	public Object visit(Variable node) {
		return null;
	}

	@Override
	public Object visit(TipoArray node) {
		node.getTipo().acepta(this);
		return null;
	}

	@Override
	public Object visit(TipoChar node) {
		return null;
	}

	@Override
	public Object visit(TipoEntero node) {
		return null;
	}

	@Override
	public Object visit(TipoReal node) {
		return null;
	}

	@Override
	public Object visit(TipoStruct node) {
		return null;
	}

	// acceso campo:String nombre campo no se visita
	// Expresion -> hay que visitarla

	// Función
	// lista de argumentos: recorrerlos
	// tipo de retorno
	// variables locales
	// sentencias

	// Expresion aritmética
	// operador - no se visita
	// Expresion 1 - visitarla
	// Expresion 2 - visitarla

}
