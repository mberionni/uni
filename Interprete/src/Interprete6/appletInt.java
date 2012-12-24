package Interprete6;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class appletInt extends JApplet implements ActionListener
{ /**
	 * 
	 */
	private static final long serialVersionUID = -5088660610010808131L;
  
  JPanel ioPanel = new JPanel();
  JPanel cmdPanel 			= new JPanel();
  JButton info 			= new JButton("info");
  JButton valuta 			= new JButton("valuta");
  JTextArea msgIn    = new JTextArea(3,50); 
  JTextArea msgOut 	= new JTextArea(2,50);
  JTextArea Utility = new JTextArea(9,50);
  JTextArea Error = new JTextArea(3,50);
  JScrollPane scrollPane4 = new JScrollPane(msgIn);
  JScrollPane scrollPane = new JScrollPane(Utility);
  JScrollPane scrollPane2 = new JScrollPane(msgOut);
  JScrollPane scrollPane3 = new JScrollPane(Error);
  String text;
  esegui e;
  public static JRadioButton SintassiJ=new JRadioButton("Sintassi Java");
  JRadioButton SintassiL=new JRadioButton("Sintassi Lisp");


  public void init()
  { super.init();
    getContentPane().setLayout( new BorderLayout(  ) );
	 	setCommandPanel();
    setIoPanel();
   	getContentPane().add( "Center", ioPanel  );
	 	getContentPane().add( "North",  cmdPanel  );
    e=new esegui(msgOut,Utility,Error);
 	}

  	protected void setCommandPanel()
    { GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      cmdPanel.setLayout( gridbag);
      SintassiJ.setSelected(true);
      ButtonGroup group=new ButtonGroup();
      group.add(SintassiJ);
      group.add(SintassiL);
      Color c1=new Color(166,209,241);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 0;
      //c.insets = new Insets(2,12,2,12);
      c.weightx = 0.5;
      gridbag.setConstraints(info, c);
      cmdPanel.add( info );
      c.gridx = 1;
      c.gridy = 0;
      c.weightx = 0.25;
      gridbag.setConstraints(SintassiJ, c);
      cmdPanel.add(SintassiJ);
      c.gridx = 2;
      c.gridy = 0;
      c.weightx = 0.25;
      gridbag.setConstraints(SintassiL, c);
      cmdPanel.add(SintassiL);

      c.ipady = 10;
      c.gridx = 0;
      c.gridy = 1;
      c.weightx=0.0;
      c.gridwidth=3;
      msgIn.setBackground(c1);
      gridbag.setConstraints(scrollPane4, c);
      cmdPanel.add( scrollPane4 );
      cmdPanel.setBorder(BorderFactory.createEtchedBorder());
	  	info.addActionListener( this );
  	}

    public boolean SintJava()
    { System.out.println("è selezionata sintassi Java "+SintassiJ.isSelected());
      return (SintassiJ.isSelected());
    }
    
    public void printOut()
    { println3("Programma realizzato da Michele Berionni");
      println3("dicembre 1999 / gennaio 2000");
    }

    public void println( String s)
    {	msgOut.append( s + "\n" );
  	}

    public void println2( String s)
    {	Utility.append( s + "\n" );
  	}
    public void println3( String s)
    {	Error.append( s + "\n" );
  	}

   	public void setIoPanel()
    {	ioPanel.setLayout( new BorderLayout());
	  	Color c1=new Color(166,209,241);
      Color c2=new Color(204,210,211);
      msgIn.setBackground(c1);
      msgOut.setAutoscrolls(true);
      msgOut.setEditable(true);
      msgOut.setBackground(c2);
      msgOut.setAutoscrolls(true);
      msgOut.setEditable(false);
      Utility.setBackground(c1);
      Utility.setAutoscrolls(true);
      Utility.setEditable(false);
      Error.setBackground(c2);
      Error.setAutoscrolls(true);
      Error.setEditable(false);
      ioPanel.setBorder(BorderFactory.createEtchedBorder());
  		ioPanel.add( "North", scrollPane2 );
      ioPanel.add( "Center", scrollPane );
      ioPanel.add( "West" ,valuta );
      ioPanel.add( "South", scrollPane3 );
      cmdPanel.setBorder(BorderFactory.createEtchedBorder());
  		valuta.addActionListener( this );
    }


	  public void actionPerformed(ActionEvent evt)
    {  Object source = evt.getSource();
       if ( source == info )  			printOut();
       else {
              text = msgIn.getText();
              e.setT(text);
              if (text.length()!=0)
               e.interpreta();
               else
                println3("Inserire l'espressione da valutare");
              msgIn.selectAll();
              msgIn.cut();
            }
    }
 
 }


