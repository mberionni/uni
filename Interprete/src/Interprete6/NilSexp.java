package Interprete6;

public class NilSexp extends AtomSexp
{
 public NilSexp( )
 {}

 public   boolean isNull()
 { return true;	}

 public   boolean isList()
 { return true;	}

 public   Object  val()
 { return null;	}

 public   boolean isEq( Sexp s2 )
 { return s2 instanceof NilSexp;	}

 public String toString()
 { return "nil";	}

 public String toSimpleString()
 {	return "";	}

}

