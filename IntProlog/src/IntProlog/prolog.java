package IntProlog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class myFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1286140762826761256L;

	public myFrame(String s)
	{
		super(s);
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.print("WindowAdapter.windowClosing with event: \n"
						+ e + "\n");
				((Frame) e.getSource()).dispose();
				System.exit(0);
			}
		});
	}
}

class prolog
{
	public static void main(String[] args)
	{
		myFrame f = new myFrame(
				"Interprete Prolog - una prima soluzione - Michele Berionni");
		prologApplet c1 = new prologApplet();
		c1.init();
		c1.start();
		f.getContentPane().add("Center", c1);
		f.setSize(700, 500);
		// f.pack();
		// f.setVisible();
		f.setVisible(true);
		// show();
	}
}
