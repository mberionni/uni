package IntProlog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class prologVisitor extends Thread implements unifyVisitor, ActionListener
{
	public static JTextArea msgOut;
	JTextArea stdOut;
	JFrame panelB = new JFrame();
	ImageIcon buttonIcon = new ImageIcon("images/middle.gif");
	JButton more = new JButton("MORE", buttonIcon);
	Sexp prog, prog2;
	clause curGoal;
	Sexp cutHead = null; // not null when there is no cut active in backtracking

	public static java.util.Stack<Var> trail = new java.util.Stack<Var>();
	boolean unified = false;

	public prologVisitor(JTextArea a, JTextArea b, Sexp prog, clause g,	Sexp prog2)
	{
		// println("Costruttore prolog vis prog2 "+prog2);
		msgOut = a;
		stdOut = b;
		this.prog = prog;
		this.prog2 = prog2;
		// println("Costruttore prolog vis prog2 "+this.prog2);
		curGoal = g;
	}

	public void run()
	{
		printlnO("prologVisitor starts" + "\n");
		println("GOAL: " + curGoal/* +" prog "+prog+" prog2 "+prog2 */);
		demo(curGoal);
		// prog2=new NilSexp();
		// println("ho finito la dim. prog2 "+prog2);

	}

	public void printlnO(String s)
	{
		msgOut.append(s + "\n");
	}

	public void println(String s)
	{
		stdOut.append(s + "\n");
	}

	/*
	 * Viene inizializzato con una sequenza (lista) di clause. Questa lista
	 * rappresenta il programma. Un termine visitato viene considerato come il
	 * goal corrente Il visitore cerca di dedurre il goal da visitare dal
	 * programma
	 */
	protected void unifyList(Sexp a1, Sexp a2)
	{// System.out.print("unifyList of prologVisitor " + a1 + " " + a2 +"\n" );
		// if( a1==null || a2 == null ) unified = false; //controllare
		if (a1.isNull()) unified = a2.isNull();
		else if (a2.isNull()) unified = a1.isNull();
		else
		{ // System.out.println("a1.car "+a1.car()+" a2.car: "+a2.car());
			a1.car().accept(this, a2.car());
			// System.out.println("dopo unified = "+unified);
			if (unified) unifyList(a1.cdr(), a2.cdr());
			else
			{
				a2.car().accept(this, a1.car());
				if (unified) unifyList(a1.cdr(), a2.cdr());
			}
		}
	}

	public void unifyVar(Var e, Sexp that)
	{
		if (e.unbound())
		{
			printlnO("unificazione variabili " + e + " con " + that);
			e.setVal(that);
			unified = true;
			trail.push(e);
			printlnO("aggiungo sullo stack: " + e + " legata con " + that);
		}
		else
		{ // printlnO(e+" è bound, controllo se ha lo stesso riferimento di "+that);
			e.value().accept(this, that);
		}
	}

	public void unifySymbol(Symbol e, Sexp that)
	{ // System.out.println("unifySymbol (prolog vis)= " + e + " con " +that);
		// unified = ( (e.getId()).equals(that.toString()) );
		unified = e.isEq(that);
		printlnO("unificazione simboli " + e + " con " + that);
	}

	public void unifyStructure(structure e, Sexp that)
	{
		if (that instanceof structure)
		{
			printlnO("unificazione struttura " + e + " con " + that);
			e.car().accept(this, that.car());
			if (unified) unifyList(e.cdr(), that.cdr());
		}
		else unified = false;
	}

	public void unifyArgList(argList e, Sexp that)
	{
		unifyList(e, that);
	}

	public void unifyClause(clause e, Sexp that)
	{
		if (that instanceof structure) e.car().accept(this, that.car());
		if (unified) unifyList(e.cdr(), that.cdr());
		else unified = false;
	}

	public void unwindTrail(java.util.Stack<Var> trail, int to)
	{
		printlnO("stack prima unwind " + trail);
		for (int i = trail.size() - to; i > 0; i--)
		{
			Var V = (Var) trail.pop();
			// printlnO(" tolgo "+V+" cui assegno "+V);
			V.setVal(V);
		}
		printlnO("stack dopo unwind " + trail);
	}

	protected final Sexp appendConj(Sexp x, Sexp y)
	{
		if (x instanceof True) return y;
		if (y instanceof True) return x;
		if (x instanceof Conj)
		{
			Sexp curr = ((Conj) x).car();
			Sexp cont = appendConj(((Conj) x).cdr(), y);
			return new Conj(curr, cont);
		}
		else return new Conj(x, y);
	}

	public clause unfold(clause e, clause that, Sexp pg2)
	{ // e è il goal --- that è la prima clausola del programma
		clause result = null;
		Sexp first = e.getFirst();
		Sexp rest = e.getRest();
		pg2 = new NilSexp();
		String codice = prologApplet.code.getText();
		try
		{
			ProParser p = new ProParser(codice, msgOut);
			for (;;)
			{
				Sexp curClause = p.getClause(); // seleziono una singola
												// clausola
				if (!(curClause instanceof eofToken)) pg2 = Sexp.append(pg2,
						Sexp.list1(curClause));
				if (p.atEOF()) break;
			}
		}
		catch (Exception err)
		{
			msgOut.append("error" + err + " \n");
		}

		Sexp nuovo = new ConsSexp(pg2, Sexp.nil);
		printlnO("unifica  " + first.toString() + " con " + that.getHead());
		/*
		 * printlnO("first= " + first + " rest= " + rest + "\n" );
		 * printlnO("head=  " + e.getHead() + " body= " + e.getBody() + "\n" );
		 * printlnO( "that.head " + that.getHead() + "  that.body " +
		 * that.getBody() + "\n" );
		 */
		if (!first.isNull()) first.accept(this, that.getHead());
		if (unified)
		{ // ritira(that, those);
			prog = nuovo.car();
			// printlnO("\n prima 2 op "+prog);
			Sexp body = that.getBody();
			Sexp cont = appendConj(body, rest);
			// printlnO("continuation=  body "+body+" rest "+rest +" = "+cont);
			// printlnO( " prog "+prog);
			result = new clause(e.getHead(), cont);
		}
		return result;
	}

	protected void more()
	{
		panelB.setSize(150, 100);
		more = new JButton("MORE", buttonIcon);
		more.setVerticalTextPosition(AbstractButton.BOTTOM);
		more.setHorizontalTextPosition(AbstractButton.CENTER);
		panelB.getContentPane().add(more, BorderLayout.CENTER);
		more.addActionListener(this);
		panelB.setBounds(10, 10, 200, 150);
		panelB.validate();
		panelB.pack();
		panelB.setVisible(true);
		// introdotta la wait invece di suspend() che è deprecated;
		try
		{
			wait();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelB.remove(more);
		panelB.validate();
		panelB.setVisible(false);
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object source = evt.getSource();
		if (source == more) this.notify();
	}

	public void demo(clause goal)
	{ // unifico con le clausole di prog, una alla volta
		printlnO("demo " + goal + "\n");
		if (goal.isNull()) println("I have no goal \n");
		else if (goal.getBody().isEq(new True()))
		{
			println("> " + goal + "\n");
			more();
		}
		else step(goal, trail.size(), prog, prog2);
	}

	protected void step(clause goal, int oldTop, Sexp prog, Sexp pg)
	{
		clause newgoal;
		printlnO("step with prog: \n " + prog /* +" prog2 "+pg */);
		if (goal.getFirst() instanceof cutToken)
		{ // printlnO("cut with head: " + ((cutToken)goal.getFirst()).getHead()
			// + "\n" );
			newgoal = new clause(goal.getHead(), goal.getRest());
			demo(newgoal);
			// qui c'e' il backtracking del cut
			// unwindTrail( trail, oldTop );
			// non dovrebbe fare nulla avendo "saltato" il cut in fase di demo
			cutHead = ((cutToken) goal.getFirst()).getHead();
			println("backtrack cut with head: " + cutHead + "\n");
		}
		else
		{// not c'è il cut
			if (((prog.car()).toString()).length() > 1)
			{
				newgoal = unfold(goal, (clause) prog.car(), pg);// unificazione
				printlnO("newgoal " + newgoal + "\n");
				if (newgoal != null)
				{
					demo(newgoal);
				}
				unwindTrail(trail, oldTop);
				printlnO("try another solution for--> " + goal + "\n");
				if (cutHead != null)
				{ // cut attivo nel backtracking
					printlnO("             cut is on  --> "
							+ " current clause head = "
							+ ((clause) prog.car()).getHead()
							+ " head of cut = " + cutHead + "\n");
					if (cutHead != ((clause) prog.car()).getHead()) ; // noop
					else cutHead = null;
				}
				else // no cut active
				if (!prog.cdr().isNull()) step(goal, oldTop, prog.cdr(), pg);
				else
				{// no more to do
					printlnO("no more to do for--> " + goal + "\n");
				}
			}
			else
			{ // prog isNull
				printlnO("ho finito di scandire il programma");
			}
		}// not a cut
	}// step
}
