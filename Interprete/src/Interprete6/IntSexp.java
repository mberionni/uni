package Interprete6;

 public class IntSexp extends NumSexp
 { Integer numVal;

   public IntSexp( Integer v )
   { numVal = v; }

   public IntSexp( int v )
   { numVal = new Integer(v); }

   public   boolean isEq( Sexp s2 )
   { if( s2 instanceof IntSexp ) return (this.getValInt()== ((IntSexp)s2).getValInt() );
	   else return false;
   }
   
   public    int     getValInt()
   {	return numVal.intValue();	}

   public    double  getValDouble()
   { 	return numVal.intValue();	}

   public Sexp applyOperator( NumSexp v, char op )
   { if( v instanceof IntSexp )
       return applyOpInteger( numVal.intValue(), v.getValInt(), op );
     else
     if( v instanceof DoubleSexp )
       return applyOpDouble( numVal.intValue(),v.getValDouble(), op );
  	 else return nil;
   }

   public   int     getVal()
   { 	return numVal.intValue();	}
   public   Object  val()
   {    	return numVal;				}
   public String toString()
   {			return numVal.toString();	}
   public String toSimpleString()
   {	return numVal.toString();	}

}
