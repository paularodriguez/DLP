package visitor;

import ast.*;
import ast.def.*;
import ast.expr.*;
import ast.sent.*;
import ast.tipos.*;

public interface Visitor {

	public Object visit(Programa node);

	public Object visit(DefinicionFuncion node);

	public Object visit(DefinicionProcedimiento node);

	public Object visit(DefinicionStruct node);

	public Object visit(DefinicionVariable node);

	public Object visit(Campo node);

	public Object visit(Asignacion node);

	public Object visit(IF node);

	public Object visit(InvocacionProcedimiento node);

	public Object visit(Print node);

	public Object visit(Read node);

	public Object visit(Return node);

	public Object visit(While node);

	public Object visit(AccesoArray node);

	public Object visit(AccesoCampo node);

	public Object visit(Aritmetica node);

	public Object visit(Cast node);

	public Object visit(Comparacion node);

	public Object visit(InvocacionFuncion node);

	public Object visit(LiteralCaracter node);

	public Object visit(LiteralEntero node);

	public Object visit(LiteralReal node);

	public Object visit(Logica node);

	public Object visit(Negacion node);

	public Object visit(Variable node);

	public Object visit(TipoArray node);

	public Object visit(TipoChar node);

	public Object visit(TipoEntero node);

	public Object visit(TipoReal node);

	public Object visit(TipoStruct node);
}
