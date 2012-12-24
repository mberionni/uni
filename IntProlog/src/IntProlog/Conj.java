package IntProlog;

public class Conj extends argList
{ 	// una lista di termini (Sexp) separati da virgole senza parentesi che racchiudono
	public Conj(Sexp t1, Sexp t2)
	{
		super(t1, t2);
		isAList = true;
	}

	public Conj(Sexp t1)
	{
		super(t1, new NilSexp());
		isAList = true;
	}

	public String toString()
	{
		return car().toString() + "," + cdr().toString();
	}

}
