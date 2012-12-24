package IntProlog;

public class ConsSexp extends Sexp
{
	protected Sexp left;
	protected Sexp right;

	public ConsSexp(Sexp t1, Sexp t2)
	{
		left = t1;
		right = t2;
		isAList = t2.isNull() || t2.isList();
	}

	public void accept(unifyVisitor v, Sexp that)
	{}

	public boolean isAtom()
	{
		return false;
	}

	public boolean isNull()
	{
		return false;
	}

	public boolean isList()
	{
		return isAList;
	}

	public String separator()
	{
		if (isAList) return " ";
		else return " . ";
	}

	public boolean isEq(Sexp x)
	{
		Sexp l2 = this;
		// System.out.println("is eq fra consSexp");
		if (!this.isList()) return false;
		while (!x.isNull())
		{
			Sexp el = x.car();
			if (!(l2.car()).isEq(el)) return false;
			x = x.cdr();
			l2 = l2.cdr();
		}
		if (l2.isNull()) return true;
		else return false;
	}

	public Sexp car()
	{
		return left;
	}

	public Sexp cdr()
	{
		return right;
	}

	/*
	 * public int length() { return 1 + cdr().length(); }
	 */

	public String toSimpleString()
	{
		String prefix = car().toString();
		String rest;
		if (cdr().isNull()) return car().toString();
		/*
		 * la frase precedente gestisce il caso di una coppia con secondo
		 * elemento nil. Se non ci fosse risultarebbe un non estetico ( x . )
		 */
		if (isAList) rest = cdr().toSimpleString(); // toStringAsList();
		else rest = cdr().toString();
		return prefix + separator() + rest;
	}

	public String toString()
	{
		return "(" + toSimpleString() + ")";
	}
	// public String toStringAsList() {return toSimpleString(); }

}
