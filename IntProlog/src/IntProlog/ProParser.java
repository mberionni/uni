package IntProlog;

import java.io.StreamTokenizer;

import javax.swing.*;

public class ProParser
{
	Sexp curItem;
	lexProlog lex;
	JTextArea msgOut;

	ProParser(String s, JTextArea msgOut) throws Exception
	{
		lex = new lexProlog(s, msgOut);
		this.msgOut = msgOut;
		// msgOut.append("\n Costruttore parser \n" );
	}

	public boolean atEOF()
	{
		return lex.ttype == StreamTokenizer.TT_EOF;
	}

	public Sexp getClause() throws Exception
	{
		Sexp h, b, t1;
		h = getTerm();
		t1 = lex.next();
		if (t1 instanceof eofToken) return h;
		if (t1 instanceof iffToken)
		{
			b = getBody(h);
			return new clause(h, b);
		}
		else return new clause(h, new True());
	}

	protected Sexp getBody(Sexp head) throws Exception
	{
		Sexp t1;
		t1 = getTerm();
		// msgOut.append("body term" + t1 + "\n" );
		if (t1 instanceof cutToken) ((cutToken) t1).setHead(head);
		return getConjCont(t1, head);
	}

	protected Sexp getConjCont(Sexp curr, Sexp head) throws Exception
	{
		Sexp t1 = lex.next();
		Sexp t;
		// msgOut.append("getConjCont " + curr + "\n" );
		if (t1 instanceof eocToken) t = curr;
		else if (t1 instanceof commaToken)
		{
			Sexp other = getTerm();
			if (other instanceof cutToken) ((cutToken) other).setHead(head);
			t = new Conj(curr, getConjCont(other, head));
		}
		else if (t1 instanceof dotToken) t = curr;
		else
		{
			throw new Exception("mi aspettavo un punto o una virgola");
		}
		return t;
	}

	public Sexp getTerm() throws Exception
	{
		Sexp t1 = lex.next();
		if (t1 instanceof funToken) return new structure(new Symbol(
				t1.toString()), getArgList());
		else return t1; // t1 is a Symbol
	}

	public Sexp getArgList() throws Exception
	{
		Sexp t1 = lex.next();
		if (t1 instanceof lparToken)
		{
			t1 = getTerm();
			return new argList(t1, getArgListCont());
		}
		else
		{ // internal error
			msgOut.append("parser internal error at " + lex.lineno() + "\n");
			throw new Exception("parser internal error");
		}
	}

	public Sexp getArgListCont() throws Exception
	{
		Sexp t1 = lex.next();
		if (t1 instanceof commaToken)
		{
			t1 = getTerm();
			return new argList(t1, getArgListCont());
		}
		if (t1 instanceof rparToken) return Sexp.nil;
		else throw new Exception("argument list is wrong");
	}

}
