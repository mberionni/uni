package IntProlog;

public class IdentSexp extends AtomSexp
{
	protected String id;

	public IdentSexp(String id)
	{
		this.id = id;
	}

	public boolean isNull()
	{
		return false;
	}

	public boolean isEq(Sexp s2)
	{
		System.out.println("ident - confronto " + id + " e " + s2 + " "
				+ id.equals(s2.toString()));
		return id.equals(s2.toString());
	}

	public String toString()
	{
		return id;
	}

	// public String toStringAsList() { return id; }
	public String toSimpleString()
	{
		return id;
	}
}// IdentSexp

