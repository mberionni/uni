package IntProlog;

abstract class AtomSexp extends Sexp
{
	public boolean isAtom()
	{
		return true;
	}

	public boolean isList()
	{
		return false;
	}

	public Sexp car()
	{
		return nil;
	}

	public Sexp cdr()
	{
		return nil;
	}

	public int length()
	{
		return 1;
	}
}
