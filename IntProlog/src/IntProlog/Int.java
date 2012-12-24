package IntProlog;

public class Int extends Symbol
{
	long val;

	Int(long i)
	{
		super("");
		val = i;
		id = "" + val;
	}

	Int(String s)
	{
		super("");
	}

	public String toString()
	{
		return id;
	}

}
