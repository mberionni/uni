package IntProlog;

public class clause extends nonAtomicTerm
{
	public clause(Sexp t1, Sexp t2)
	{
		super(t1, t2);
	}

	public void accept(unifyVisitor v, Sexp that)
	{
		System.out.println("il visitor della clausola - unify");
		v.unifyClause(this, that);
	}

	public String separator()
	{
		return " :- ";
	}

	public Sexp getHead() // la testa della clausola
	{
		return car();
	}

	public Sexp getBody() // il body della clausola
	{
		if (cdr().isNull()) return new True();
		else return cdr();
	}

	public Sexp getFirst() // il primo elemento (head) del body
	{
		Sexp body = getBody();
		if (body instanceof Conj) return ((Conj) body).car();
		else if (body instanceof True) return Sexp.nil;
		else return body;
	}

	public Sexp getRest() // il cdr del body
	{
		Sexp body = getBody();
		if (body instanceof Conj) return ((Conj) body).cdr();
		else return new True();
	}
	// public String toString() { return car() + " :- " + cdr() ; }
}
