package Interprete6;
import java.awt.*;
import java.awt.event.*;

public class myFrame extends Frame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2509095551477790910L;

	public myFrame( String s )
  {	super(s);
		addWindowListener
      ( new java.awt.event.WindowAdapter()
       { public void windowClosing( WindowEvent e )
        {	System.out.print("WindowAdapter.windowClosing with event: \n" + e + "\n");
				((Frame)e.getSource()).dispose();
				System.exit(0);
        }
       }
    	);

	}
}

