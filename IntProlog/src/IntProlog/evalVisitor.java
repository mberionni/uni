package IntProlog;

import javax.swing.*;

public class evalVisitor extends prologVisitor
{
	public evalVisitor(JTextArea a, JTextArea b, Sexp p, clause g, Sexp p2)
	{
		super(a, b, p, g, p2);
	}
}
