package IntProlog;

abstract class atomicTerm extends IdentSexp
{
	public atomicTerm(String id)
	{
		super(id);
	}

	public Sexp ref()
	{
		return this;
	}
}
