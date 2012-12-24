package Interprete6;

public abstract class Sexp
{ protected boolean isAList;

 /*  costanti di uso comune */
 public  static Sexp qnull	= new IdentSexp("null");
 public  static Sexp nil	= new NilSexp();
 public  static Sexp qtrue	= new IdentSexp("true");
 public  static Sexp qfalse	= new NilSexp();
 public  static Sexp qset		= new IdentSexp("setq");
 public  static Sexp qeval	= new IdentSexp("eval");
 public  static Sexp qprint	= new IdentSexp("print");
 public  static Sexp qread	= new IdentSexp("read");
 public  static Sexp qand	= new IdentSexp("and");
 public  static Sexp qor	= new IdentSexp("or");
 public  static Sexp qnot	= new IdentSexp("not");
 public  static Sexp qeq	= new IdentSexp("eq");
 public  static Sexp qeof	= new IdentSexp("eof");
 //public  static Sexp qset	= new IdentSexp("setq");
 public  static Sexp qprimitive	= new IdentSexp("primitive");
 public  static Sexp qquote	= new IdentSexp("quote");
 public  static Sexp qcond	= new IdentSexp("cond");
 public  static Sexp qdefine	= new IdentSexp("define");
 public  static Sexp qlambda	= new IdentSexp("lambda");
 public  static Sexp qclosure	= new IdentSexp("closure");
 public  static Sexp qcons	= new IdentSexp("cons");
 public  static Sexp qcar	= new IdentSexp("car");
 public  static Sexp qcdr	= new IdentSexp("cdr");
 public  static Sexp failSexp   = new IdentSexp("failure in primitive");

 /* operazioni abstract
 	  che devono essere realizzate dalle sottoclassi */

 public  abstract boolean isNull();
 public  abstract boolean isEq( Sexp s2 );
 public  abstract boolean isAtom();
 public  abstract boolean isList();
 public  abstract Sexp car();
 public  abstract Sexp cdr();

 public  abstract String toSimpleString();

 /*  operazioni di costruzione */
 public static Sexp eq(Sexp s1,Sexp s2)
 {return s1.isEq(s2)?qtrue:qfalse;}

 public static Sexp cons( Sexp t1, Sexp t2 )
 { return new ConsSexp( t1,t2 ); 	}

 public static Sexp coppia( Sexp t1, Sexp t2 )
 { return new CoppiaSexp( t1,t2 ); 	}

 public static Sexp atom( String id )
 {return new IdentSexp( id );		}

 public static Sexp number( int v )
  {return new IntSexp( new Integer(v) );	}

 public static Sexp list1( Sexp t1 )
 	{return new ConsSexp( t1, nil );	}

 public static Sexp list2( Sexp t1, Sexp t2 )
 {return new ConsSexp(t1, list1( t2 ) );		}

 public static Sexp list3( Sexp t1, Sexp t2, Sexp t3 )
 { return new ConsSexp( t1,list2(t2,t3) );		}

 public static Sexp list4( Sexp t1, Sexp t2, Sexp t3, Sexp t4 )
 { return new ConsSexp(t1,list3(t2,t3,t4));	}

 public static Sexp list5( Sexp t1, Sexp t2, Sexp t3, Sexp t4 ,Sexp t5)
 { return new ConsSexp(t1,list4(t2,t3,t4,t5));	}

public static Sexp list6( Sexp t1, Sexp t2, Sexp t3, Sexp t4 ,Sexp t5, Sexp t6)
 { return new ConsSexp(t1,list5(t2,t3,t4,t5,t6));	}
 
public static Sexp list7( Sexp t1, Sexp t2, Sexp t3, Sexp t4 ,Sexp t5, Sexp t6 ,Sexp t7)
 { return new ConsSexp(t1,list6(t2,t3,t4,t5,t6,t7));	}

 
 public static Sexp list10( Sexp t1, Sexp t2, Sexp t3, Sexp t4, Sexp t5, Sexp t6, Sexp t7, Sexp t8 ,Sexp t9,Sexp t10)
 { return Sexp.append(list5(t1,t2,t3,t4,t5),list5(t6,t7,t8,t9,t10));}

 public static Sexp list15( Sexp t1, Sexp t2, Sexp t3, Sexp t4, Sexp t5, Sexp t6, Sexp t7, Sexp t8 ,Sexp t9,Sexp t10,Sexp t11,Sexp t12,Sexp t13,Sexp t14,Sexp t15)
 { return Sexp.append(list10(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10),list5(t11,t12,t13,t14,t15));}
 /*  operazioni primitive nello stile funzionale */

 public static boolean isAtom( Sexp s1 )
 {return s1.isAtom();		}

 public static boolean isEq( Sexp s1, Sexp s2 )
 {return s1.isEq(s2);		}

 public static boolean isNull( Sexp s1 )
 {return s1.isNull();			}

 public static boolean isList( Sexp s1 )
 {return s1.isList( );			}

 public static Sexp car( Sexp s1 )
 {return s1.car();		}

 public static Sexp caar( Sexp s1 )
 {return s1.car().car();		}

 public static Sexp cdr( Sexp s1 )
 {return s1.cdr();		}

 public static Sexp cadr( Sexp s1 )
 {return s1.cdr().car();		}

 public static Sexp cddr( Sexp s1 )
 {return s1.cdr().cdr();		}

 public static Sexp cdar( Sexp s1 )
 {return s1.car().cdr();		}

 public static Sexp cadar( Sexp s1 )
 {return s1.car().cdr().car();	}

 public static Sexp caddr( Sexp s1 )
 {return s1.cdr().cdr().car();	}

 public static Sexp isAnAtom( Sexp s1 )
 {return s1.isAtom()?qtrue:qfalse;}

 

 /*  operazioni non primitive nello stile funzionale */

 public static Sexp pairLis( Sexp vars, Sexp vals, Sexp a )
 { return ( isNull(vars) )  ? a :
	      cons( cons( car( vars), car(vals) ),
				pairLis( cdr(vars),cdr(vals),a ));
 }

 public static int length( Sexp se )
 { if ( isNull(se) )  return 0 ;
	 else return 1 + length( se.cdr() );
 }

 public static Sexp assoc( Sexp el, Sexp plis )
 {  return( isNull( plis ) )  ? nil :
		(isEq(caar(plis),el))? car(plis) : assoc(el,cdr(plis));
    /*
	  if( isNull( plis ) ) return nil;
	  else
	  return(isEq(caar(plis),el))?car(plis):assoc(el,cdr(plis));
	  */
 }

 public static String toString( Sexp s1 )
 {return s1.toString();
 }

  /*  operazioni utili per l'interprete  */

 public static Sexp append( Sexp l1, Sexp l2 )
 { if( l1.isNull() ) return l2;
	 if( l2.isNull() ) return l1;
	 return cons( l1.car(), append( l1.cdr(), l2 ) );
 }

 public static boolean member( Sexp x, Sexp l1 )
 { if( l1.isNull() ) return false;
	 if( (l1.car()).isEq( x ) ) return true;
	 else return member( x, l1.cdr() );
 }

 public static boolean isPresent (IdentSexp x, Sexp l1)  // funzione attualmente non usata
 { // verifica la  presenza di un identificatore in una lista
   //System.out.println("esiste "+x+" in "+l1);
   if( l1.isNull() ) return false;
	 if( (l1.car()).isEq( x ) ) return true;
	 else return member( x, l1.cdr() );
 }

 public static Sexp primitiveSetNames()
 { 	return append (list6( qread,qeval,qprint,qnull,qand,qor),
			             append ( list6(qnot,qcar,qcdr,nil,qeq,qcons),operatorSetNames() )
			            );
 }


 public static Sexp operatorSetNames()
 { return list7( atom("<"),atom(">"),atom("="),atom("+"),atom("-"),atom("*"),atom("/") );
 }

 public static Sexp createPrimitiveSet( Sexp pNameList)
 { return( pNameList.isEq( nil ) ) ?  nil :
	    cons( cons( qprimitive, pNameList.car() ), createPrimitiveSet( pNameList.cdr() ));
 }

 public static boolean selfEvaluating( Sexp e )
 {   if (e instanceof AtomSexp)
			return ( e.isEq( qtrue ) || e.isEq( qfalse )||
    					 e.isEq( qeof ) ||  e.isEq( nil ) );
		else return false;
 }

}
