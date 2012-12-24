package IntProlog;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class SexpLexer extends StreamTokenizer
{
	Sexp curItem;

	SexpLexer(Reader I) throws Exception
	{
		super(I);
		parseNumbers();
		eolIsSignificant(true);
		ordinaryChar('.');
		ordinaryChar('-');
		ordinaryChar('/');
		quoteChar('\'');
		quoteChar('"');
		wordChar('$');
		wordChar('_');
		slashStarComments(true);
		commentChar('%');
	}

	void whitespaceChar(char c)
	{
		whitespaceChars(c, c);
	}

	void wordChar(char c)
	{
		wordChars(c, c);
	}
}

class lexProlog extends SexpLexer
{
	JTextArea msgOut;
	static String anonymous = "_".intern();
	Hashtable<String, Var> dict;
	boolean inClause = false;

	lexProlog(Reader I) throws Exception
	{
		super(I);
		dict = new Hashtable<String, Var>();
		// System.out.println("ho creato la hash table");
	}

	lexProlog(String s, JTextArea msgOut) throws Exception
	{
		this(new StringReader(s));
		this.msgOut = msgOut;
		// msgOut.append("Costruttore del Lexer: \n " + s +
		// "\n fine costruttore Lexer \n" );
	}

	Sexp make_var(String s)
	{
		Var X;
		if (s == anonymous)
		{
			X = new Var(s);
		}
		else
		{
			X = (Var) dict.get(s);
			if (X == null)
			{
				X = new Var(s);
				dict.put(s, X); // se non è presente la inserisco
			}
		}
		return X;
	}

	protected Sexp getWord(boolean quoted) throws IOException
	{
		Sexp T;
		if (quoted && sval.length() == 0) return new IdentSexp("");
		char c = sval.charAt(0);
		if (!quoted && (Character.isUpperCase(c) || '_' == c)) T = make_var(sval);
		else if (sval.intern() == "is".intern())
		{
			T = new relToken("is");
		}
		else
		{
			String s = sval;
			int nt = nextToken();
			pushBack();
			if ('(' == nt) T = new funToken(s); // funtore
			else T = new Symbol(s);
		}
		return T;
	}

	public Sexp next() throws Exception
	{
		int n = nextToken();
		Sexp T;
		switch (n)
		{
			case TT_WORD:
				T = getWord(false);
				break;
			case TT_NUMBER:
				if (Math.floor(nval) == nval) T = new Symbol((int) nval + "");// new
																				// Int(
																				// new
																				// Integer(
																				// (int)nval)
																				// );
				else T = new Symbol("");// T=make_real(nval);
				break;
			case TT_EOF:
				T = new eofToken();
				break;
			case TT_EOL:
				T = next();
				break;
			case ':':
				if ('-' == nextToken())
				{
					T = new iffToken(":-");
				}
				else
				{
					pushBack();
					T = new glueToken(":");
				}
				break;
			case '-':
				if ('>' == nextToken())
				{
					T = new ifToken("->");
				}
				else
				{
					pushBack();
					T = new addToken("-");
				}
				break;

			case '.':
				int c = nextToken();
				// msgOut.append("point followed by " + c + "\n" );
				if (TT_EOL == c || TT_EOF == c)
				{
					inClause = false;
					dict.clear();
					T = new eocToken();
				}
				else
				{
					T = new dotToken();
					pushBack();
				}
				break;
			case '!':
				T = new cutToken();
				break; // T=new constToken(new Cut()); break;
			case '(':
				T = new lparToken();
				break;
			case ')':
				T = new rparToken();
				break;
			case '[':
				T = new lbraToken();
				break;
			case ']':
				T = new rbraToken();
				break;
			case '|':
				T = new barToken();
				break;
			case ',':
				T = new commaToken();
				break;
			case ';':
				T = new semicommaToken();
				break;
			case '=':
				T = new relToken("=");
				break;
			case '>':
				T = new relToken(">");
				break;
			case '<':
				T = new relToken("<");
				break;
			case '+':
				T = new addToken("+");
				break;
			case '*':
				T = new mulToken("*");
				break;
			case '/':
				T = new mulToken("/");
				break;

			default:
				T = new IdentSexp("default" + n);

		}
		// msgOut.append( "il lexer estrae: " + T + "\n" );
		return T;
	}

}

/**
 * ======================================================== costruttori
 * =============================================================
 */

class eofToken extends IdentSexp
{
	eofToken()
	{
		super("eof");
	}
}

class eocToken extends IdentSexp
{
	eocToken()
	{
		super("eoc");
	}
}

class lparToken extends IdentSexp
{
	lparToken()
	{
		super("(");
	}
}

class rparToken extends IdentSexp
{
	rparToken()
	{
		super(")");
	}
}

class lbraToken extends IdentSexp
{
	lbraToken()
	{
		super("[");
	}
}

class rbraToken extends IdentSexp
{
	rbraToken()
	{
		super("]");
	}
}

class barToken extends IdentSexp
{
	barToken()
	{
		super("|");
	}
}

class cutToken extends IdentSexp
{
	Sexp head;

	cutToken()
	{
		super("!");
	}

	public void setHead(Sexp h)
	{
		head = h;
	}

	public Sexp getHead()
	{
		return head;
	}
}

class dotToken extends IdentSexp
{
	dotToken()
	{
		super(".");
	}
}

class commaToken extends IdentSexp
{
	commaToken()
	{
		super(",");
	}
}

class semicommaToken extends IdentSexp
{
	semicommaToken()
	{
		super(";");
	}
}

class funToken extends IdentSexp
{
	funToken(String s)
	{
		super(s);
	}
}

class opToken extends IdentSexp
{
	opToken(String s)
	{
		super(s);
	}
}

class addToken extends IdentSexp
{
	addToken(String s)
	{
		super(s);
	}
}

class mulToken extends IdentSexp
{
	mulToken(String s)
	{
		super(s);
	}
}

class iffToken extends opToken
{
	iffToken(String s)
	{
		super(s);
	}
}

class ifToken extends opToken
{
	ifToken(String s)
	{
		super(s);
	}
}

class glueToken extends opToken
{
	glueToken(String s)
	{
		super(s);
	}
}

class relToken extends opToken
{
	relToken(String s)
	{
		super(s);
	}
}
