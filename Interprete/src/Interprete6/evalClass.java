package Interprete6;
import javax.swing.*;

public class evalClass
{ protected Sexp globalEnv;
   Sexp ris,s;
  JTextArea msg,er;


	public evalClass( JTextArea ta, JTextArea e)
  {	msg = ta;
    er=e;
		initEnv();
	}

  public void println( String s)
  {	msg.append( s + "\n" );
  }

  public void printlnE( String s)
  {	er.append( s + "\n" );
  }

Sexp globalEnvR=Sexp.list1( Sexp.cons( Sexp.atom("Env"), Sexp.atom("dummy") ) );

 	protected void initEnv()
  { println( "initEnv " );
		globalEnv = Sexp.pairLis(
					Sexp.primitiveSetNames(),
					Sexp.createPrimitiveSet(Sexp.primitiveSetNames()), Sexp.nil );
      println("ambiente globale iniziale: "+globalEnv);
	}

	public Sexp eval( Sexp e )
  {   globalEnvR=Sexp.list1( Sexp.cons( Sexp.atom("Env"),Sexp.atom("dummy") ) );
     // println("inizio frase corrente: eval "+e+" in env "+globalEnvR+"\n");
      
    	return eval(e,globalEnvR);
	}

 	protected Sexp evalAtom( Sexp e, Sexp env  )
  { //println("eval Atom "+e+" in env "+env);
   	Sexp value = Sexp.assoc( e,env );
    if (value.isEq(Sexp.nil))
      { value=Sexp.assoc(e,globalEnv);
        println("non è nell'env corrente => lo cerco nel globale e trovo: "+value);
        if (value.isEq(Sexp.nil)) return Sexp.nil;
         else {//System.out.println("sto ritornando "+value.cdr());
               return value.cdr();}
      }
    else return value.cdr();  
	}

	public  Sexp eval( Sexp e, Sexp env ) 
  { println( "eval "+ e + "  ENV= " + env);
    //System.out.println( "eval "+ e + "  ENV= " + env);
    if( e instanceof NumSexp ) return e;
    if( Sexp.selfEvaluating(e)    ) return e;
	 if( e.isAtom() ) return evalAtom(e,env);
	 if( e.car().isAtom() )
       { if( e.car().isEq( Sexp.qquote )  )	return e.cdr().car();
         if( e.car().isEq( Sexp.qdefine ) )return evalDefine( e,env );
         if( e.car().isEq( Sexp.qcond  ) )	return evalCond( e.cdr().car(), e.cdr().cdr(),env );
         if( e.car().isEq( Sexp.qset  ) )	return evalSetq( e,env );
         if( e.car().isEq( Sexp.qlambda  ) ) return evalLambda( e.cdr().car(), e.cdr().cdr(),env );
         Sexp n = eval(e.car(),env);
         //println("è un atomo: dovrò applicare " +n);
    		return apply( n, evalArgLis(e.cdr(),env)  );
       }
    s = eval(e.car(),env);
    //println("non è un atomo dovrò applicare "+s);
    return apply(  eval(e.car(),env), evalArgLis(e.cdr(),env)  );
   
  }

protected Sexp apply(  Sexp s, Sexp argList )  
  { println( "apply "+ s + " to " + argList );
    //System.out.println( "apply "+ s + " to " + argList );
    if ( s.isEq(Sexp.nil) ) {//println("è nullo");return Sexp.nil;
                              return Sexp.nil;
                             }
	 if ( s.car().isEq( Sexp.qprimitive ) )
   	   return applyPrimitive( s.cdr(), argList );
    if( s.car().isEq( Sexp.qclosure ) )
      { Sexp body =  closureBody(s);
		  Sexp args =  closureArgs(s);
	     return evalSequence(body,Sexp.pairLis( args, argList, closureEnv(s) ) );
 		}
   	printlnE("funzione sconosciuta");
	   return new IdentSexp("funzione non nota");
  }

 protected static Sexp closureEnv( Sexp ce )	
   {return ce.cdr().cdr().cdr().car(); }
 protected static Sexp closureBody( Sexp ce )	{return ce.cdr().cdr().car();		}
 protected static Sexp closureArgs( Sexp ce )	{return ce.cdr().car();				}

  protected Sexp evalDefine (Sexp list,Sexp env)
  {Sexp first=list.cdr().car();
   Sexp rest=list.cdr().cdr();
   if (!(rest.cdr()).isNull())
        printlnE("numero errato di argomenti");
   if (!(first instanceof IdentSexp))
     printlnE("la define richiede come primo arg un identificatore");
   Sexp v=Sexp.assoc(first,globalEnv);
     if (!v.isEq(Sexp.nil)) 
        printlnE("l'identificatore "+first+" è già presente nell'environment globale");
     else
        { Sexp ris=eval(rest.car());
          addGlobalEnv(first,ris);
          return first;
        }
     return Sexp.nil;
  }

  public void addGlobalEnv( Sexp symbol, Sexp value)
  { Sexp v= Sexp.list1(Sexp.cons(symbol,value));
    globalEnv=Sexp.append(v,globalEnv);
    //println("environment corrente globale "+globalEnvR);
  }

  public static void extendLocalEnv(Sexp env, Sexp symbol, Sexp value)
  { Sexp v= Sexp.list1(Sexp.cons(symbol,value));
    env= Sexp.append(v,env);
    //System.out.println("environment corrente locale "+env);
    
  }

  public static Sexp evalLambda( Sexp argList, Sexp bodyList, Sexp env )
  {	return Sexp.list4( Sexp.qclosure, argList, bodyList, env ) ;
  }

  protected Sexp evalCond(Sexp first,Sexp rest, Sexp env )
  { Sexp ris=eval(first.car(),env);
    if (!ris.isEq(Sexp.nil))
     return evalSequence(first.cdr(),env);
    if (!rest.isEq(Sexp.nil))
     return evalCond(rest.car(),rest.cdr(),env);
     else return Sexp.nil;
  }

   public Sexp evalSetq( Sexp e, Sexp env )
   { //println( "evalSetq "+ e + " in env= " + env + "\n");
     Sexp symbol = e.cdr().car();
     Sexp value = eval( e.cdr().cdr().car(), env );
     Sexp v = Sexp.assoc( symbol, env );
     if( !v.isNull() )
		 { println(" simbolo "+v+" già presente, cambio il valore");
      //  System.out.println("1 simbolo "+v+" già presente, cambio il valore");
		    ((ConsSexp)v).changeVal( value );
       }
     else
       { println("simbolo non presente nell'env locale: estendo l'env locale");
     	   ((ConsSexp)env).insertVal(symbol, value );
	    //  println("dopo estensione env locale "+env);  
	    }    
	   return symbol;
   }

  protected Sexp evalSequence(Sexp seq,Sexp e)
  { //println("eval Sequence " + seq);
    if ((seq.cdr()).isNull()) return eval(seq.car(),e);
     else
    {eval(seq.car(),e);
    return evalSequence(seq.cdr(),e);
    }
  }

 	protected  Sexp evalArgLis( Sexp e, Sexp env )
  { println("\tevalArgLis "+ e + " in env "+env);
    if ( e.isNull() ) return  Sexp.nil;
     else
      { ris= eval(e.car(),env);
        return  Sexp.cons(ris,evalArgLis(e.cdr(),env));
      }  
 	}

  protected  Sexp applyPrimitive( Sexp fun, Sexp argList )
  { println( "\t\tapplyPrimitive "+ fun + " argList " + argList);
   if( fun.isEq( Sexp.qnull )) return  argList.car().isNull() ? Sexp.qtrue : Sexp.qfalse;
   if(fun.isEq(Sexp.nil)) return Sexp.nil;
   if( fun.isEq( Sexp.qread )) return  applyRead();
		if( fun.isEq( Sexp.qprint ))	  return  applyPrint(  argList );
   	if( fun.isEq( Sexp.qcar ) ) return  argList.car().car();
		if( fun.isEq( Sexp.qcdr ) ) 
		{//System.out.println("applico cdr ai seguenti argomenti "+argList);
		return  argList.car().cdr();}
	   if( fun.isEq( Sexp.qcons )) return  Sexp.cons( argList.car(),argList.cdr().car() );
	 	if( fun.isEq( Sexp.qeq )  ) return  Sexp.eq( argList.car(),argList.cdr().car() );

      if( fun.isEq( Sexp.qand ) ) return and(argList.car(),argList.cdr().car());
		if( fun.isEq( Sexp.qor ) ) return  or(argList.car(),argList.cdr().car());
		if( fun.isEq( Sexp.qnot )) return  not(argList.car());
	   if( fun.isEq( Sexp.qeval )) return  eval( argList.car() );
		if( fun.isEq( Sexp.atom("+"))) return  applyOperator( argList, '+' );
		if( fun.isEq( Sexp.atom("*"))) return  applyOperator( argList, '*' );
		if( fun.isEq( Sexp.atom("/"))) return  applyOperator( argList, '/' );
		if( fun.isEq( Sexp.atom("-"))) return  applyOperator( argList, '-' );
      if( fun.isEq( Sexp.atom("<"))) return  applyOperator( argList, '<' );
		if( fun.isEq( Sexp.atom(">"))) return  applyOperator( argList, '>' );
 		if( fun.isEq( Sexp.atom("="))) return  applyOperator( argList, '=' );
		return Sexp.failSexp;
  }

  public Sexp and(Sexp s1,Sexp s2)
  { Sexp r1=eval(s1);
    Sexp r2=eval(s2);
    if (  r1.isEq(Sexp.qtrue)&& r2.isEq(Sexp.qtrue))
     return Sexp.qtrue;
    else return Sexp.qfalse;
  }

  public Sexp or(Sexp s1,Sexp s2)
  { Sexp r1=eval(s1);
    Sexp r2=eval(s2);
    if (  r1.isEq(Sexp.qtrue) || r2.isEq(Sexp.qtrue))
     return Sexp.qtrue;
    else return Sexp.qfalse;
  }

  public Sexp not(Sexp s1)
  { Sexp r1=eval(s1);
    if (  r1.isEq(Sexp.qtrue)) return Sexp.qfalse;
    else return Sexp.qtrue;
  }

protected Sexp applyPrint( Sexp arg )
{	 println( ""+ arg.car() + "\n");
	 return arg.car();
} 

 protected static Sexp applyRead()
 {
 /*Frame anchorPoint = findFrame();
  formPanel dataPanel  = new formPanel(anchorPoint, Sexp.nil, "prego inserire una forma ... ", msg,ooo );
		return dataPanel.getVal();*/
		return Sexp.nil;
 }


  protected  Sexp applyOperator( Sexp al, char op )
  { NumSexp v1;
  	 NumSexp v2;
	 println("\t\tapplyOperator " + op + " args= " + al);
    if (  al.car().isNull() ) 		return Sexp.nil;
	 if (  al.cdr().car().isNull() ) return Sexp.nil;
    v1 = (NumSexp)al.car();
    v2 = (NumSexp)al.cdr().car();
	 return v1.applyOperator( v2, op );
 	}

}
