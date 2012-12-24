package IntProlog;

public interface unifyVisitor
{
	public void unifyVar(Var e, Sexp that);

	public void unifySymbol(Symbol e, Sexp that);

	public void unifyStructure(structure e, Sexp that);

	public void unifyArgList(argList e, Sexp that);

	public void unifyClause(clause goal, Sexp that);

	public void demo(clause goal);
}
