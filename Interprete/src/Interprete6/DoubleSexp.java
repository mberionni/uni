package Interprete6;

public class DoubleSexp extends NumSexp
{ Double numVal;
  public DoubleSexp( Double v ){ numVal = v;	}
  public DoubleSexp( double v ){ numVal = new Double(v);	}
  public   double  getVal(){ return numVal.doubleValue();	}

  public    int     getValInt(){
	 return numVal.intValue();	}

  public    double     getValDouble(){
  	return numVal.doubleValue();	}

  public Sexp applyOperator( NumSexp v, char op )
  { if( v instanceof IntSexp )  return applyOpDouble( numVal.doubleValue(), v.getValInt(), op );
    else if( v instanceof DoubleSexp )  return applyOpDouble( numVal.doubleValue(),v.getValDouble(), op );
	  else return nil;
  }

 public   Object  val(){    return numVal;				}
 public String toString()		 {	return numVal.toString();	}
 public String toSimpleString() {	return numVal.toString();	}

}

