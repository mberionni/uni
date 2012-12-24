package IntProlog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class prologApplet extends JApplet implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8212423392585649099L;

	public static JTextArea code = new JTextArea("", 12, 28);
	JTextArea intmsg = new JTextArea("", 12, 28);
	JTextArea outp = new JTextArea("", 12, 28);
	JScrollPane scrollPaneC = new JScrollPane(code);
	JScrollPane scrollPaneM = new JScrollPane(intmsg);
	JScrollPane scrollPaneO = new JScrollPane(outp);
	JTextField gl = new JTextField("", 28);
	JButton compile = new JButton("Compile Code");
	JButton compileG = new JButton("Compile Goal");
	JButton run = new JButton("Run");
	int ch = 0;
	String ss = "";
	Sexp prog = new NilSexp();
	Sexp prog2 = new NilSexp();
	Sexp goal = new NilSexp();
	Sexp curSexp = Sexp.nil;
	JPanel input = new JPanel();
	JPanel output = new JPanel();
	evalVisitor ev;

	@Override
	public void init()
	{
		getContentPane().setLayout(new BorderLayout());
				
		try
		{
			setInputPanel();
		}
		catch (Exception e)
		{  	
		    outp.append("errore input panel"+e);
		};
		setOutputPanel();
		getContentPane().add(input, BorderLayout.WEST);
		getContentPane().add(output, BorderLayout.EAST);
	}

	public void setInputPanel() throws IOException
	{
		final String newline = "\n";
		final JTextArea log = new JTextArea(2, 28);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		input.setLayout(gridbag);

		// Create the log first, because the action listeners
		// need to refer to it.

		log.setMargin(new Insets(1, 1, 1, 1));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		final JFileChooser fc = new JFileChooser();

		// Create the open button
		ImageIcon openIcon = new ImageIcon("Images/open.gif");
		JButton openButton = new JButton("Open a File...", openIcon);
		openButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int returnVal = fc.showOpenDialog(prologApplet.this);

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					String name = file.getName();
					String path = file.getAbsolutePath();
					try
					{
						FileInputStream inps = new FileInputStream(path);
						ch = 0;
						ss = "";
						while (ch != -1)
						{
							ch = inps.read();
							ss = ss + (char) ch;
						}
						inps.close();
					}
					catch (Exception exc)
					{
						System.out.println("" + e);
					}
					code.selectAll();
					code.cut();
					code.append(ss);

					log.append("Opening: " + name + "." + newline);
				}
				else
				{
					log.append("Open command cancelled by user." + newline);
				}
			}
		});

		// Create the save button
		ImageIcon saveIcon = new ImageIcon("Images/save.gif");
		JButton saveButton = new JButton("Save a File...", saveIcon);
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int returnVal = fc.showSaveDialog(prologApplet.this);

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					// this is where a real application would save the file.
					log.append("Saving: " + file.getName() + "." + newline);
				}
				else
				{
					log.append("Save command cancelled by user." + newline);
				}
			}
		});

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);

		// Explicitly set the focus sequence.
		// rimossi poichè deprecati
		// openButton.setNextFocusableComponent(saveButton);
		// saveButton.setNextFocusableComponent(openButton);

		// Add the buttons and the log to the frame
		// Container contentPane = getContentPane();
		// contentPane.add(buttonPanel, BorderLayout.NORTH);
		// contentPane.add(logScrollPane, BorderLayout.CENTER);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;		
		gridbag.setConstraints(buttonPanel, c);
		input.add(buttonPanel);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(logScrollPane, c);
		input.add(logScrollPane);

		JLabel label1 = new JLabel("Codice:");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(label1, c);
		input.add(label1);

		c.gridx = 0;
		c.gridy = 3;
	    c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(scrollPaneC, c);
		input.add(scrollPaneC);

		JLabel label2 = new JLabel("GOAL clause:");
		c.gridx = 0;
		c.gridy = 4;
		// c.weightx=1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(label2, c);
		input.add(label2);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(gl, c);
		input.add(gl);

		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.3;
		c.gridwidth = 1;
		gridbag.setConstraints(compile, c);
		input.add(compile);
		compile.addActionListener(this);

		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0.3;
		c.gridwidth = 1;
		gridbag.setConstraints(compileG, c);
		input.add(compileG);
		compileG.addActionListener(this);

		c.gridx = 2;
		c.gridy = 6;
		c.weightx = 0.3;
		c.gridwidth = 1;
		gridbag.setConstraints(run, c);
		input.add(run);
		run.addActionListener(this);
	
		Color c1 = new Color(166, 209, 241);
		code.setBackground(c1);
		code.setAutoscrolls(true);
		code.setEditable(true);

		/* mb1 
		  input.add(load);
		  input.add(scrollPaneC); input.add(gl);
		 */

		input.setBorder(BorderFactory.createEtchedBorder());
	}

	public void setOutputPanel()
	{
		BoxLayout box = new BoxLayout(output, BoxLayout.Y_AXIS);
		output.setLayout(box);
		Color c1 = new Color(166, 209, 241);
		Color c2 = new Color(204, 210, 211);
		JLabel label3 = new JLabel("Messaggi interni:");
		output.add(label3);
		outp.setBackground(c1);
		outp.setAutoscrolls(true);
		outp.setEditable(false);
		intmsg.setBackground(c2);
		intmsg.setAutoscrolls(true);
		intmsg.setEditable(false);
		output.add(scrollPaneM);
		JLabel label4 = new JLabel("Output:");
		output.add(label4);
		output.add(scrollPaneO);
		output.setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Object source = evt.getSource();
		if (source == run) run();
		else if (source == compile) compile();
		else if (source == compileG) compileGoal(false);
	}

	protected void run()
	{
		if (!goal.isNull()) eval(prog);
		else compileGoal(true);
	}

	protected void compileGoal(boolean andRun)
	{
		String sgoal = gl.getText();
		if (!(sgoal.length() == 0))
		{
			try
			{
				ProParser p = new ProParser(sgoal, intmsg);
				goal = p.getClause();
				intmsg.append("compiled goal: " + goal + "\n");
				if (andRun) run();
			}
			catch (Exception e)
			{
				outp.append("errore" + e + " \n");
			}
		}
		else outp.append("inserire il goal" + " \n");
	}

	protected void showProg(Sexp prog, JTextArea intmsg)
	{
		if (!prog.isNull())
		{
			intmsg.append(prog.car() + "\n");
			showProg(prog.cdr(), intmsg);
		}
	}

	protected void compile()
	{
		Sexp curClause;
		goal = new NilSexp();
		prog = new NilSexp();
		prog2 = new NilSexp();
		String codice = code.getText();

		try
		{
			ProParser p = new ProParser(codice, intmsg);
			for (;;)
			{
				curClause = p.getClause(); // seleziono una singola clausola
				intmsg.append("compiled: " + curClause + "\n");
				if (!(curClause instanceof eofToken))
				{
					prog = Sexp.append(prog, Sexp.list1(curClause));
				}
				if (p.atEOF())
				{
					break;
				}
			}
			String codice2 = code.getText();
			ProParser p2 = new ProParser(codice2, intmsg);
			for (;;)
			{
				curClause = p2.getClause(); // seleziono una singola clausola
				// intmsg.append( "compiled: " + curClause + "\n" );
				if (!(curClause instanceof eofToken))
				{
					prog2 = Sexp.append(prog2, Sexp.list1(curClause));
				}
				if (p2.atEOF())
				{
					break;
				}
			}

			showProg(prog, outp);
			// showProg( prog2, outp );
		}
		catch (Exception e)
		{
			outp.append("error" + e + " \n");
		}
	}

	public void eval(Sexp prog)
	{
		ev = new evalVisitor(intmsg, outp, prog, (clause) goal, prog2);
		if (goal instanceof clause)
		{
			// deve lanciare un nuovo thread e riattivarlo con il more
			// ev.demo( (clause)e, this );
			ev.start();
		}
		else outp.setText(goal + " is not  a goal clause ");
	}

}
