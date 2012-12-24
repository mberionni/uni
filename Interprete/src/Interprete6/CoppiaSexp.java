package Interprete6;

public class CoppiaSexp extends Sexp
{ protected  Sexp left ;
  protected  Sexp right ;

  public CoppiaSexp( Sexp t1, Sexp t2 )
  { left    = t1;
	  right   = t2;
	  isAList = false ;
  }

  public   boolean isAtom()
  { return false;		}
  public   boolean isNull()
  { return false;		}
  public   boolean isList()
  { return false;		}

  public   String  separator()
  { return " . ";
  }

  public   boolean isEq( Sexp s2 )
  { //if (!( s2 instanceof CoppiaSexp) ) return false;
    //System.out.println("is eq fra coppiaSexp");
    if (s2.isNull()) return this.isNull();
    Sexp el1=this.left;
    Sexp el2=this.right;
    Sexp ele1=((CoppiaSexp)s2).left;
    Sexp ele2=((CoppiaSexp)s2).right;
    if ( (el1.isEq(ele1)) && (el2.isEq(ele2)))
    return true;
     else
    return false;
  }  

  public  Sexp car()
  { return left;	}
  public  Sexp cdr()
  { return right;	}

  public String toSimpleString()
  { String prefix = car().toString();
    String rest;
  	if ( isAList ) rest = cdr().toSimpleString();
	  else rest= cdr().toString();
    return prefix + separator() + rest;
  }

 public String toString()
 {return "(" + toSimpleString() + ")";
 }

}
