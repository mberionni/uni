package IntProlog;

public class NilSexp extends AtomSexp
{
	public NilSexp()
	{}

	public boolean isNull()
	{
		return true;
	}

	public boolean isList()
	{
		return true;
	}

	public boolean isEq(Sexp s2)
	{
		return s2 instanceof NilSexp;
	}

	public String toString()
	{
		return "nil";
	}

	// public String toStringAsList() { return ""; }

	public String toSimpleString()
	{
		return "";
	}

}
