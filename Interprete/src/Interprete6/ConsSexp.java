package Interprete6;

public class ConsSexp extends Sexp
{ protected  Sexp left ;
  protected  Sexp right ;

  public ConsSexp( Sexp t1, Sexp t2 )
  { left    = t1;
	  right   = t2;
	  isAList = t2.isNull() || t2.isList() ;
  }

  public   boolean isAtom()
  { return false;		} 
  public   boolean isNull()
  { return false;		}
  public   boolean isList()
  { return isAList;		}

  public   String  separator()
  { return isAList?" ":" . ";
  }

  /*public   boolean isE( Sexp x )
  {  if (!(x instanceof IdentSexp))
       return false;
     else
    {
      if (this.isList())
      {if ((this.car()).isEq(x)) return true;
       else return ((this.cdr()).isEq(x));
      }
      else
      {System.out.println(x+" = "+this.car());
      if ((this.car()).isEq(x)) return true;
      else return false;
      }
    }
  }      */

  public boolean isEq (Sexp x)
  { Sexp l2=this;
    //System.out.println("is eq fra consSexp");
    if (!this.isList()) return false;
    while (!x.isNull())
    { Sexp el=x.car();
      if ( !  (l2.car()).isEq(el))
      return false;
      x=x.cdr();
      l2=l2.cdr();
    }
    if (l2.isNull())
    return true;
     else return false;
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
 {return "(" + toSimpleString() + ")";	}


 public void changeVal( Sexp v )
 { //System.out.println("sono arrivato qui");
    right = v; }
 
 public void insertVal( Sexp name, Sexp val )
 { if( isAList )
   { if (right.isNull()) right = list1( new ConsSexp(name,val) );
  	 else ((ConsSexp)right).insertVal( name,val );
   }
 }

}
