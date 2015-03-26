package semantico;

import gestorErrores.GestorErrores;

import java.util.HashMap;
import java.util.Map;

import visitor.DefaultVisitor;
import ast.def.Campo;
import ast.def.Definicion;
import ast.def.DefinicionFuncion;
import ast.def.DefinicionProcedimiento;
import ast.def.DefinicionStruct;
import ast.def.DefinicionVariable;
import ast.expr.InvocacionFuncion;
import ast.expr.Variable;
import ast.sent.InvocacionProcedimiento;
import ast.tipos.TipoStruct;

public class IdentificacionVisitor extends DefaultVisitor {

	GestorErrores gestorErrores;

	TablaSimbolos<String, DefinicionVariable> variables = new TablaSimbolos<String, DefinicionVariable>();

	// Admite DefinicionProcedimiento y DefinicionFuncion
	Map<String, Definicion> funciones = new HashMap<String, Definicion>();

	Map<String, DefinicionStruct> estructuras = new HashMap<String, DefinicionStruct>();
	Map<String, Campo> campos = new HashMap<String, Campo>();

	public IdentificacionVisitor(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	public Object visit(DefinicionVariable node) {

		if (variables.getVariable(node.getNombre()) != null) {
			gestorErrores.error("La variable " + node.getNombre()
					+ " ya está definida.");
		} else {
			variables.put(node.getNombre(), node);
		}
		/* Cuando es una estructura */
		if (node.getTipo() instanceof TipoStruct){
			node.setTipo(estructuras.get(((TipoStruct) node.getTipo())
					.getNombre()));
			return null;/*arreglarlo*/
		}

		return super.visit(node);
	}

	public Object visit(DefinicionFuncion node) {

		if (funciones.get(node.getNombre()) != null) {
			gestorErrores.error("La función " + node.getNombre()
					+ " ya está definida.");
		} else {
			funciones.put(node.getNombre(), node);
		}

		variables.set();
		Object ret = super.visit(node);
		variables.reset();
		return ret;
	}

	public Object visit(DefinicionProcedimiento node) {

		if (funciones.get(node.getNombre()) != null) {
			gestorErrores.error("La función " + node.getNombre()
					+ " ya está definida.");
		} else {
			funciones.put(node.getNombre(), node);
		}

		variables.set();
		Object ret = super.visit(node);
		variables.reset();
		return ret;
	}

	public Object visit(DefinicionStruct node) {

		if (estructuras.get(node.getNombre()) != null) {
			gestorErrores.error("El tipo estructura " + node.getNombre()
					+ " ya está definido.");
		} else {
			estructuras.put(node.getNombre(), node);
		}
		campos.clear();
		Object ret = super.visit(node);
		return ret;
	}

	public Object visit(Campo node) {

		if (campos.get(node.getNombre()) != null) {
			gestorErrores.error("El campo " + node.getNombre()
					+ " ya está definido.");
		} else {
			campos.put(node.getNombre(), node);
		}
		return super.visit(node);
	}

	public Object visit(InvocacionProcedimiento node) {
		if (funciones.get(node.getNombre()) != null) {
			node.setDefinicion(funciones.get(node.getNombre()));
		} else {
			gestorErrores.error("La función " + node.getNombre()
					+ " no ha sido declarada.");
		}
		return super.visit(node);
	}

	public Object visit(InvocacionFuncion node) {
		if (funciones.get(node.getIdentificador()) != null) {
			node.setDefinicion(funciones.get(node.getIdentificador()));
		} else {
			gestorErrores.error("La función " + node.getIdentificador()
					+ " no ha sido declarada.");
		}
		return super.visit(node);
	}

	public Object visit(Variable node) {
		if (variables.getFromAny(node.getNombre()) != null) {
			node.setDefinicion(variables.getFromAny(node.getNombre()));
		} else {
			gestorErrores.error("La variable " + node.getNombre()
					+ " no ha sido declarada.");
		}
		return super.visit(node);
	}

}
