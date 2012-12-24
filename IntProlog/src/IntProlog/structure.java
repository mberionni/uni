package IntProlog;

public class structure extends nonAtomicTerm
{
	public structure(Sexp t1, Sexp t2)
	{
		super(t1, t2);
	} // t1 Symbol

	public void accept(unifyVisitor v, Sexp that)
	{ // System.out.println("il visitor della structure - unify");
		v.unifyStructure(this, that);
	}

	public String separator()
	{
		return " ";
	}
}
