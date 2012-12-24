package IntProlog;

public class Var extends atomicTerm
{
	Sexp value;

	public Var(String id)
	{
		super(id);
		value = this; // unbound
	}

	public void accept(unifyVisitor v, Sexp that)
	{ 	// System.out.println("il visitor della var - unify");
		v.unifyVar(this, that);
	}

	public String toString()
	{
		String rest = unbound() ? id + '/' + id : value.toString();
		return rest;
	}

	public boolean unbound()
	{
		return value == this;
	}

	public void setVal(Sexp v)
	{ 	// (prologVisitor.msgOut).append("assegno a "+this+" "+v+" ");
		value = v;
	}

	public Sexp value()
	{
		return value;
	}
}
