package visitor;

import ast.Programa;
import ast.def.Campo;
import ast.def.DefinicionFuncion;
import ast.def.DefinicionStruct;
import ast.def.DefinicionVariable;
import ast.expr.AccesoArray;
import ast.expr.AccesoCampo;
import ast.expr.Aritmetica;
import ast.expr.Cast;
import ast.expr.Comparacion;
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
import ast.sent.While;
import ast.tipos.TipoArray;
import ast.tipos.TipoChar;
import ast.tipos.TipoEntero;
import ast.tipos.TipoReal;
import ast.tipos.TipoStruct;

public class XMLVisitor extends DefaultVisitor {

	public Object visit(Programa node) {
		System.out.println("<Programa>");
		Object ret = super.visit(node);
		System.out.println("</Programa>");
		return ret;
	}

	public Object visit(DefinicionVariable node) {
		System.out.println("<DefinicionVariable name='" + node.getNombre()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</DefinicionVariable>");
		return ret;
	}

	public Object visit(DefinicionFuncion node) {
		System.out.println("<DefinicionFuncion" + "'>");
		Object ret = super.visit(node);
		System.out.println("</DefinicionFuncion>");
		return ret;
	}

	public Object visit(DefinicionStruct node) {
		System.out.println("<DefinicionStruct name='" + node.getNombre() + ">");
		Object ret = super.visit(node);
		System.out.println("</DefinicionStruct>");
		return ret;
	}

	public Object visit(Campo node) {
		System.out.println("<Campo name='" + node.getNombre() + ">");
		Object ret = super.visit(node);
		System.out.println("</Campo>");
		return ret;
	}

	public Object visit(Asignacion node) {
		System.out.println("<Asignacion>");
		Object ret = super.visit(node);
		System.out.println("</Asignacion>");
		return ret;
	}

	public Object visit(IF node) {
		System.out.println("<SentenciaIF>");
		Object ret = super.visit(node);
		System.out.println("</SentenciaIF>");
		return ret;
	}

	public Object visit(InvocacionProcedimiento node) {
		System.out.println("<InvocacionProcedimiento name='" + node.getNombre()
				+ ">");
		Object ret = super.visit(node);
		System.out.println("</InvocacionProcedimiento>");
		return ret;
	}

	public Object visit(Print node) {
		System.out.println("<Print>");
		Object ret = super.visit(node);
		System.out.println("</Print>");
		return ret;
	}

	public Object visit(Read node) {
		System.out.println("<Read>");
		Object ret = super.visit(node);
		System.out.println("</Read>");
		return ret;
	}

	public Object visit(Return node) {
		System.out.println("<Return>");
		Object ret = super.visit(node);
		System.out.println("</Return>");
		return ret;
	}

	public Object visit(While node) {
		System.out.println("<While>");
		Object ret = super.visit(node);
		System.out.println("</While>");
		return ret;
	}

	public Object visit(AccesoArray node) {
		System.out.println("<AccesoArray>");
		Object ret = super.visit(node);
		System.out.println("</AccesoArray>");
		return ret;
	}

	public Object visit(AccesoCampo node) {
		System.out.println("<AccesoCampo>");
		Object ret = super.visit(node);
		System.out.println("</AccesoCampo>");
		return ret;
	}

	public Object visit(Aritmetica node) {
		System.out
				.println("<Aritmetica operador='" + node.getOperador() + "'>");
		Object ret = super.visit(node);
		System.out.println("</Aritmetica>");
		return ret;
	}

	public Object visit(Cast node) {
		System.out.println("<Cast>");
		Object ret = super.visit(node);
		System.out.println("</Cast>");
		return ret;
	}

	public Object visit(Comparacion node) {
		System.out.println("<Comparacion operador='" + node.getOperador()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</Comparacion>");
		return ret;
	}

	public Object visit(InvocacionFuncion node) {
		System.out.println("<InvocacionFuncion identificador='"
				+ node.getIdentificador() + "'>");
		Object ret = super.visit(node);
		System.out.println("</InvocacionFuncion>");
		return ret;
	}

	public Object visit(LiteralCaracter node) {
		System.out.println("<LiteralCaracter value='" + node.getCaracter()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</LiteralCaracter>");
		return ret;
	}

	public Object visit(LiteralEntero node) {
		System.out.println("<LiteralEntero value='" + node.getVisitaValor() + "'>");
		Object ret = super.visit(node);
		System.out.println("</LiteralEntero>");
		return ret;
	}

	public Object visit(LiteralReal node) {
		System.out.println("<LiteralReal value='" + node.getVisitaValor() + "'>");
		Object ret = super.visit(node);
		System.out.println("</LiteralReal>");
		return ret;
	}

	public Object visit(Logica node) {
		System.out.println("<OperacionLogica operador='" + node.getOperador()
				+ "'>");
		Object ret = super.visit(node);
		System.out.println("</OperacionLogica>");
		return ret;
	}

	public Object visit(Negacion node) {
		System.out.println("<Negacion>");
		Object ret = super.visit(node);
		System.out.println("</Negacion>");
		return ret;
	}

	public Object visit(Variable node) {
		System.out.println("<Variable name='" + node.getNombre() + "'>");
		Object ret = super.visit(node);
		System.out.println("</Variable>");
		return ret;
	}

	public Object visit(TipoArray node) {
		System.out.println("<TipoArray>");
		Object ret = super.visit(node);
		System.out.println("</TipoArray>");
		return ret;
	}

	public Object visit(TipoChar node) {
		System.out.println("<TipoChar>");
		Object ret = super.visit(node);
		System.out.println("</TipoChar>");
		return ret;
	}

	public Object visit(TipoEntero node) {
		System.out.println("<TipoEntero>");
		Object ret = super.visit(node);
		System.out.println("</TipoEntero>");
		return ret;
	}

	public Object visit(TipoReal node) {
		System.out.println("<TipoReal>");
		Object ret = super.visit(node);
		System.out.println("</TipoReal>");
		return ret;
	}

	public Object visit(TipoStruct node) {
		System.out.println("<TipoStruct name='" + node.getNombre() + "'>");
		Object ret = super.visit(node);
		System.out.println("</TipoStruct>");
		return ret;
	}

}
