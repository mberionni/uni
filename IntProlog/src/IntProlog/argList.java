package IntProlog;

public class argList extends ConsSexp
{
	// una lista di termini (Sexp) separati da virgole e racchiusa tra perentesi
	public argList(Sexp t1, Sexp t2)
	{
		super(t1, t2);
		isAList = true;
	}

	public argList(Sexp t1)
	{
		super(t1, new NilSexp());
		isAList = true;
	}

	public void accept(unifyVisitor v, Sexp that)
	{
		System.out.println("il visitor della argList - unify");
		v.unifyArgList(this, that);
	}

	public String separator()
	{
		return " , ";
	}

	/*
	 * public String toSimpleString(){ String rest; if( cdr().isNull() ) return
	 * car().toString(); if( cdr() instanceof argList ) rest =
	 * cdr().toSimpleString(); else rest = cdr().toString(); return
	 * car().toString() + " , " + rest; }
	 */
}
