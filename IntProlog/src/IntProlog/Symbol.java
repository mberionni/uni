package IntProlog;

public class Symbol extends atomicTerm
{
	public Symbol(String id)
	{
		super(id);
	}

	public void accept(unifyVisitor v, Sexp that)
	{ // System.out.println("il visitor del symbol - unify");
		v.unifySymbol(this, that);
	}

	public boolean isEq(Sexp s2)
	{ // System.out.println("symbol - confronto "+this+ " e "+
		// s2+" "+id.equals(s2.toString()));
		return id.equals(s2.toString());
	}

	public String getId()
	{
		return id;
	}

}
