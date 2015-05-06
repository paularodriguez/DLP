package gestionMemoria;

import visitor.DefaultVisitor;
import ast.Programa;
import ast.def.Definicion;
import ast.def.DefinicionFuncion;
import ast.def.DefinicionStruct;
import ast.def.DefinicionVariable;

/*
 * IP: INSTRUCTION POINTER: Apunta a la l�nea de la instrucci�n que se est� ejecutando.
 * SP: STACK POINTER: �ltima posici�n ocupada en la pila. Permite saber en qu� posicion hay que guardar aquello que se almacene en la pila.
 * BP: BASE POINTER: Apunta a d�nde empieza la pila en la funci�n actual. Contiene la direcci�n base a partir de la cual se van a calcular vbles locales y par�metros de una funci�n.
 * 
 * 
 * Para que el programa MAPL pinte las vbles locales se escribir�a as�:
 * 
 * 	#func f
 * 	f:
 * 	#local a:int
 * 	#local b:int
 * 	enter 4    --- permite dejar espacio libre para poder guardar las vbles locales.
 * 	pusha 2
 * 	push 1
 * 	store
 * 	ret 0, 4, 0
 */
public class GestionMemoria extends DefaultVisitor {


	public Object visit(Programa node) {

		int direccionLibre = 0;

		// VARIABLES GLOBALES
		for (Definicion def : node.definiciones) {
			if (def instanceof DefinicionVariable) {
				def.setDireccion(direccionLibre);
				direccionLibre += def.getTipo().size();
				
				//System.out.println("La variable " + def.getNombre() +" tiene asignada la direcci�n " + def.getDireccion()); 

			}
		}
		
		
		return super.visit(node);
	}

	@Override
	public Object visit(DefinicionFuncion node) {
		int offset_parametros = 4;
		for (int i = node.getParametros().size() - 1; i >= 0; i--) {
			node.getParametros().get(i).setDireccion(offset_parametros);
			offset_parametros += node.getParametros().get(i).getTipo().size();
			//node.setAmbitoVariable("LOCAL"); -- Para distinguir en el futuro qu� tipo de variable es.
		}

		int offset_locales = 0;
		for (int i = 0; i < node.getDefinicionesVariable().size(); i++) {
			offset_locales -= node.getDefinicionesVariable().get(i).getTipo()
					.size();
			node.getDefinicionesVariable().get(i).setDireccion(offset_locales);
			//node.setAmbitoVariable("LOCAL"); -- Para distinguir en el futuro qu� tipo de variable es.
		}

		/*for (int i = 0; i < node.getParametros().size(); i++) {
			System.out.println("Parametro: "
					+ node.getParametros().get(i).getNombre() + " - Offset: "
					+ node.getParametros().get(i).getDireccion());
		}
		for (int i = 0; i < node.getDefinicionesVariable().size(); i++) {
			System.out.println("Variable Local: "
					+ node.getDefinicionesVariable().get(i).getNombre()
					+ " - Offset: "
					+ node.getDefinicionesVariable().get(i).getDireccion());
		}*/

		return null;
	}

	/*
	 * // OJO: direcciones de campos son relativas a la posici�n de inicio del
	 * struct 1. Posici�n primer campo: BP+4 -> node.campo.get(0).direccion= 0;
	 * 2. Resto campos, su direcci�n se ir� incrementando seg�n el tama�o del
	 * campo anterior
	 */
	@Override
	public Object visit(DefinicionStruct node) {
		int offset = 0;

		for (int i = 0; i < node.getListaCampos().size(); i++) {
			node.getListaCampos().get(i).setDireccion(offset);
			offset += node.getListaCampos().get(i).size();
		}

		/*for (int i = 0; i < node.getListaCampos().size(); i++) {
			System.out.println("Campo: "
					+ node.getListaCampos().get(i).getNombre()
					+ " - Direcci�n: "
					+ node.getListaCampos().get(i).getDireccion());
		}*/

		return null;
	}
}
