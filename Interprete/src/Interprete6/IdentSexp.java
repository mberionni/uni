package Interprete6;

public class IdentSexp extends AtomSexp
{ protected String id;

  public IdentSexp( String id )
  {	 this.id = id;
  }

  public   boolean isNull()
  { return false;
  }

  public   boolean isEq( Sexp s2 )
  { return id.equals(s2.toString());
  }

  public String toString()
  {	return id;
  }

  public String toSimpleString()
  {	return id;
  }

  
 
}

