package Interprete6;
import java.io.*;
import javax.swing.*;

public class esegui
{ exprLexer ex;
  parserLisp ep;
  evalClass ev;
  JTextArea a,b,c;
  Reader r;
  Sexp n;
  String s;

  public esegui(JTextArea msgOut ,JTextArea Utility, JTextArea Error)
  {
    a = msgOut;
    b = Utility;
    c = Error;
    ev = new evalClass(b,c);

  }

  public void setT(String arg)
  { s=arg;
  }

   public Sexp next()
  { try{
    return ex.next();
    } catch(Exception e ) {}
    return new NilSexp();
  }

  public void interpreta()
  {  Sexp sp;
    try
    {  r = new StringReader(s);
       ex = new exprLexer(r);
       ep = new parserLisp(ex,a);
       print(" > ingress parser: "+s+"\n");
       n=ex.next();
       sp=ep.begin();
       print("  > uscita parser: " + sp.toString()  + " => valutazione ==> ");
       println( ""+ev.eval( sp ) );

     }catch( Exception e){ printlnE(""+e);
                         }
  }

  public void println( String s)
  {	a.append( s + "\n" );
  }

  public void print( String s)
  {	a.append( s  );
  }
  public void printlnE( String s)
  {	c.append( s + "\n" );
  }

}



 