package Interprete6;

public abstract class NumSexp extends AtomSexp
 {
 
 public   abstract Object  val();
 public   abstract int     getValInt();
 public   abstract double  getValDouble();
 public   abstract Sexp    applyOperator( NumSexp v, char op );

 public Sexp applyOpInteger( int v1, int v2, char op ){

 	switch( op )
   {
		case '+' : return new IntSexp( new Integer( v1+v2 ) );
		case '-' : return new IntSexp( new Integer( v1-v2 ) );
		case '*' : return new IntSexp( new Integer( v1*v2 ) );
		case '/' : return new IntSexp( new Integer( v1/v2 ) );
		case '<' : return (v1 < v2) ? qtrue : qfalse;
		case '>' : return (v1 > v2) ? qtrue : qfalse;
		case '=' : return (v1 == v2) ? qtrue : qfalse;
			default  : return nil;
	 }
 }

 public Sexp applyOpDouble( double v1, double v2, char op ){
	switch( op )
  {
		case '+' : return new DoubleSexp( new Double( v1+v2 ) );
		case '-' : return new DoubleSexp( new Double( v1-v2 ) );
		case '*' : return new DoubleSexp( new Double( v1*v2 ) );
		case '/' : return new DoubleSexp( new Double( v1/v2 ) );
		case '<' : return (v1 < v2) ? qtrue : qfalse;
		case '>' : return (v1 > v2) ? qtrue : qfalse;
		case '=' : return (v1 == v2) ? qtrue : qfalse;
		default  : return nil;
  }
 }

 public   boolean isNull(){ return false;		}

 public   boolean isEq( Sexp s2 )
 { if( s2 instanceof NumSexp ) return this.equals( s2 );
	 else return false;
 }

}
