package IntProlog;

abstract class nonAtomicTerm extends ConsSexp
{
	public nonAtomicTerm(Sexp t1, Sexp t2)
	{
		super(t1, t2);
	}

	public Sexp ref()
	{
		return this;
	}

	public String toString()
	{
		return car() + separator() + cdr().toString();
	}
}
