package Interprete6;
import java.io.*;

class exprTokenizer extends StreamTokenizer
{ exprTokenizer( Reader R ) throws Exception
  {	super( R );
	parseNumbers();
	eolIsSignificant(true);
	ordinaryChar('.');
	ordinaryChar('\'');
	ordinaryChar('/');
	ordinaryChar('-');
	quoteChar('"');
 	wordChar('$');
   wordChar('_');
 	slashStarComments(true);
 	commentChar('%');
   }

   void wordChar(char c)
   { wordChars(c,c);
   }

 }

public class exprLexer extends exprTokenizer
{
  exprLexer( Reader I ) throws Exception
  { super( I );
  }
	
  protected Sexp getWord() throws IOException
  { if( sval.equals("t" ) ) return new IdentSexp("true");
    if( sval.equals("nil" ) ) return new NilSexp();
    else return new IdentSexp( sval );
  }


   public Sexp next() throws Exception
   { Sexp T;

     int n=nextToken();
     switch (n)
       { case TT_WORD:
         T = getWord( );
         break;
         case TT_NUMBER:
            if(Math.floor(nval)==nval)
               T = new IntSexp( new Integer( (int)nval) );
            else
               T = new DoubleSexp( nval );
            break;
         case TT_EOF:
            T= new eofToken();
            break;
         case TT_EOL:
            T=next();
            break;
         case '\'':
            T=new quoteToken();
            break;
         case '-':
            T=new addToken("-");
            break;
         case '.':
            T=new dotToken();
            break;
         case '(':
            T=new lparToken();
            break;
         case ')':
            T=new rparToken();
            break;
         case '[':
            T=new lbraToken();
            break;
         case ']':
            T=new rbraToken();
            break;
         case ',':
            T=new commaToken();
            break;
         case ';':
            T=new semicommaToken();
            break;
         case '=':
            T=new relToken("=");
            break;
         case '>':
            T=new relToken(">");
            break;
         case '<':
            T=new relToken("<");
            break;
         case '+':
            T=new addToken("+");
            break;
         case '*':
            T=new mulToken("*");
            break;
         case '/':
            T=new mulToken("/");
            break;
         default:
            T = new IdentSexp( "default"+n );
       }
 	 parserLisp.curToken=T;
    return T;
    }

}

/** ========================================================
*   costruttori 
============================================================= */
class eofToken extends IdentSexp 
{  eofToken()		
   {super("eof");	
   }
}

class lparToken extends IdentSexp
{  lparToken()	
   {super("(");			
   }
}

class rparToken extends IdentSexp
{  rparToken()
   {super(")");
   }
}

class lbraToken extends IdentSexp
{  lbraToken()
   {super("[");
   }
}

class rbraToken extends IdentSexp
{  rbraToken()
   {super("]");
   }
}

class quoteToken extends IdentSexp
{  quoteToken()
   {super("quote");
   }
}

class dotToken extends IdentSexp
{  dotToken()
   {super(".");
   }
}

class condToken extends IdentSexp
{  condToken()
   {super("cond");
   }
}

class commaToken extends IdentSexp
{  commaToken()
   {super(",");
   }
}

class semicommaToken extends IdentSexp
{  semicommaToken()
   {super(";");
   }
}

class opToken extends IdentSexp
{  opToken(String s)
   {super(s);
   }
}

class addToken extends IdentSexp
{  addToken(String s)
   {super(s);
   }
}

class mulToken extends IdentSexp
{  mulToken(String s)
   {super(s);
   }
}

class ifToken extends opToken
{  ifToken(String s)
   {super(s);
   }
}

class signToken extends opToken
{  signToken(String s)
   {super(s);
   }
}

class glueToken extends opToken
{  glueToken(String s)
   {super(s);
   }
}

class relToken extends opToken
{  relToken(String s)
   {super(s);
   }
}


