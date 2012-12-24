package Interprete6;

public class Interprete
{	public static void main(String[] args )
  {	myFrame f =	new myFrame("Interprete Lisp");
  	appletInt c1 = new appletInt();
		c1.init();
		c1.start();
		f.add("Center", c1 );
		f.setSize( 635,385 );
		f.setVisible(true);
	}
}
