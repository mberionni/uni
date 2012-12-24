package Interprete6;
import java.lang.Exception;
import javax.swing.*;

public class parserLisp
{ static Sexp curToken;
  exprLexer lex;
  JTextArea msgOut;

  public parserLisp (exprLexer p0, JTextArea p1) throws Exception
  { lex=p0;
    msgOut=p1;
  }

   public void println( String s)
   {	msgOut.append( s + "\n" );
 	}
  
       
   public Sexp begin() throws Exception
   { if  ((appletInt.SintassiJ).isSelected())
	   { // msgOut.append("sintassi Java");
	     return exp();
   	} else
        { //msgOut.append("sintassi Lisp");
          return read( );
        }
    }

    
  public Sexp read() throws Exception
  { Sexp t;
    Sexp s=curToken;
    if( curToken instanceof eofToken )	 throw new notWellFormedException( "reached eof " );
    if( curToken instanceof NumSexp )		 {lex.next();return s;}
    if( curToken instanceof relToken )	 {lex.next();return s;}
    if( Sexp.selfEvaluating(curToken) )	 {lex.next();return s;}
    if( curToken instanceof quoteToken )
		  { lex.next();
          return Sexp.list2( Sexp.qquote, readQuote());
        }
	if (curToken instanceof lparToken)
	    { lex.next();
      Sexp r = readPairOrList();
      if (curToken instanceof rparToken)
        { lex.next();
          return r;
        }
      else { throw new notWellFormedException("frase non ben formata");
           }
    }
  else
  if( curToken instanceof addToken )
   { boolean negative = curToken.toString() == "-";
		 t=lex.next();
		 if( t instanceof IntSexp )
        {	if( negative ) t = new IntSexp( - ((IntSexp)t).getValInt() );
          lex.next();
	  			return t;
		   	}else if( t instanceof DoubleSexp )
        {
					if( negative ) t = new DoubleSexp(- ((DoubleSexp)t).getValDouble() );
          lex.next();
					return t;
			  }else
				return s;
	 }
  else
	if( curToken instanceof IdentSexp )	 {lex.next();return s;}
  else{	throw new notWellFormedException( "expected atom or (" );
	    }
	}

  public Sexp readQuote() throws Exception
  { Sexp s=curToken;
    if( curToken instanceof lparToken )
       { lex.next();
         Sexp r= readPairOrList();
         if (curToken instanceof rparToken)
         { lex.next();
           return r;
         }
      else { throw new notWellFormedException("frase non ben formata");
           }
       }
  	if( s instanceof eofToken )	 throw new notWellFormedException( "expected atom or(" );
  	if( s instanceof NumSexp )		 {lex.next();return s;}
  	if( s instanceof relToken )	 {lex.next();return s;}
    if( s instanceof quoteToken )
      { lex.next();
        return Sexp.list2( Sexp.qquote, readQuote());
      }
   	if( Sexp.selfEvaluating(s) )	 {lex.next();return s;}
	  if( s instanceof IdentSexp )	 {lex.next();return s;}
  	else{
			throw new notWellFormedException( "expected atom or (" );
      	}
	}

  public Sexp readPairOrList() throws Exception
  { Sexp first=curToken;
    if (curToken instanceof rparToken) return new NilSexp();
    if (curToken instanceof lparToken) {lex.next();
                                        first=readPairOrList();
                                        if (curToken  instanceof dotToken)
                                         { lex.next();
                                           return  new CoppiaSexp(first,readCdrPair());
                                         }
                                               }

   if (!(curToken instanceof eofToken)&&!(curToken instanceof lparToken))
   lex.next();
   if (curToken instanceof eofToken) return Sexp.list1(first);
   if (curToken  instanceof dotToken)
       { lex.next();
         return  new CoppiaSexp(first,readCdrPair());
       }
   if (curToken instanceof rparToken)
       return Sexp.list1(first);
   Sexp rest=readList();
   return Sexp.cons( first, rest );
  }

  public Sexp readList() throws Exception
  {  Sexp first = Sexp.list1(read());
     while (!(curToken instanceof rparToken))
     { Sexp s=read();
       first=Sexp.append(first,Sexp.list1(s) );
     }
     return first;
  }

  public Sexp readCdrPair() throws Exception
  {	Sexp rest;
		if( curToken instanceof  rparToken ) return Sexp.nil;
   	rest = read();
		if( curToken instanceof  rparToken )
       return rest;
		else throw new Exception( "wrong pair " );
	}


  public Sexp exp( ) throws Exception
  { Sexp t1 = Sexp.nil;
    Sexp t2, op;
      //println("dentro exp");
 		//msgOut.append("exp with "  + curToken + "\n" );
 		t1 = term();
		// println("t1= "+t1+" curT "+curToken);
 		while( true ) 
		  { if( curToken instanceof addToken ) 
		      { op = curToken;
		        //println("op= "+op);
              curToken = lex.next();
         	  t2 = term( );	
	           //println("t2= "+t2);
 			     t1 = Sexp.list3( op, t1, t2 );		
   			}
 			else break;
   		}
 		return t1;
 	}//exp
 
 	public Sexp term( ) throws Exception{
 	Sexp t1 = Sexp.nil;
 	Sexp t2, op;
 		//msgOut.append("term with "  + curToken + "\n" );
 		t1 = factor( );
 		while( true )
		{ if( curToken instanceof mulToken ) 
   	  {	op = curToken;
 				curToken = lex.next();
  				t2 = factor( );	
	 			t1 = Sexp.list3( op, t1, t2 );		
	 		}
	 		else break;
	 	}//while
	 	return t1;
 	}//term

 
  public Sexp factor( ) throws Exception
  {	Sexp t;
 		//msgOut.append("factor: curToken="  + curToken + "\n" );
 		if( curToken instanceof eofToken )	 throw new Exception( "reached eof " );		
 		if( curToken instanceof lparToken ){	//va in testa perche lparToken e' un IdentSxep
 			curToken = lex.next();
 			t = exp();
 			if( curToken instanceof rparToken ) {
 				curToken = lex.next();		//consumo la ) e prendo il simbolo dopo
 				//msgOut.append("dopo ) si ha"  + curToken + "\n" );
 				return t;
 			}
 			else throw new Exception( "missing )" );
 		}//lparToken
 		
 		if( curToken instanceof quoteToken ) {
 			curToken = lex.next();		//consumo quote
 			if((curToken instanceof IdentSexp) && ( curToken.toString().length()==1)) 
			 {
 				t = curToken;				//memorizzo il carattere come ident
 				curToken = lex.next();		//consumo char
 				if( curToken instanceof quoteToken ){
 						curToken = lex.next();		//consumo close quote
 						return new IdentSexp( t.toString() );
 				}else throw new Exception( "missing closing quote" );  
 			}else throw new Exception( "expected character" );	
 		}//quoteToken
 		
 		if( curToken instanceof addToken ) {
 		boolean negative = curToken.toString() == "-";
 			curToken = lex.next();
 			t = curToken;
   			if( t instanceof IntSexp )
			    { if( negative ) t = new IntSexp( - ((IntSexp)t).getValInt() );
    	 			curToken = lex.next();		//consumo
	   			return t;
	 	       }else if( curToken instanceof DoubleSexp )
		         { if( negative ) t = new DoubleSexp(- ((DoubleSexp)t).getValDouble() );
                 curToken = lex.next();	//consuma
	 				  return t;
      	 		}else throw new Exception( "expected number" );
	 	}//addToken		
	 	if( curToken instanceof NumSexp || curToken instanceof relToken || 
	 		Sexp.selfEvaluating(curToken) ) {
	 			t = curToken;
	 			curToken = lex.next();
    				return t;				//number or relop or seleval
	 	}	
	 	if( curToken instanceof IdentSexp ) {
	 			t = curToken;
	 			curToken = lex.next();
	 			if( curToken instanceof lparToken ){	//f(x) o f((x))
	 			Sexp fName = t;
	 				curToken = lex.next();	//consuma (
	 				t = argList();	
	 				t = Sexp.cons(fName,t);
	 			}//if 
    				return t;				//identifier or function call
	 	}	
	 
	 	else throw new Exception( "expected factor" );
 	}//factor

 public Sexp argList() throws Exception
 {	Sexp arg, args = Sexp.nil;
	if( curToken instanceof  rparToken )
	{ curToken = lex.next();
	  return Sexp.nil;
	}
  		//if( curToken instanceof  lparToken )throw new Exception( "expected argument" );
 	while ( ! (curToken instanceof eofToken) )
	   {	arg  = exp();
 			args = Sexp.append( args, Sexp.list1(arg) );
 			if( curToken instanceof  rparToken ){ curToken = lex.next(); break; }
 			if( curToken instanceof commaToken ) 
 				curToken = lex.next();		//consuma la virgola
 			else throw new Exception( "expected comma" );
 		}
 		return args;
 }



}