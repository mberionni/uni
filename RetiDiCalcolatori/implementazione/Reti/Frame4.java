package Reti;
/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Frame4 extends JFrame
{ String nome = "";
  JPanel contentPane;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  private static ResourceBundle resources;

  private String font = resources.getString("fontDBDebug");
  public Font f = new Font(font, java.awt.Font.PLAIN, 12);
  public Font f1 = new Font(font, java.awt.Font.PLAIN, 10);

  static
  {   try   {  resources = ResourceBundle.getBundle("Reti.resources/Reti", Locale.getDefault());
            } catch (MissingResourceException mre)
                    {    System.err.println("Reti.resoureces/Reti.properties not found");
                         mre.printStackTrace();
                    }
  }
  /**Construct the frame*/
  public Frame4()
  { enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try   {  jbInit();
          }
    catch(Exception e)
    {    e.printStackTrace();
    }
  }

   public Frame4( String _nome )
   {    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        nome = _nome;
        try  {  jbInit();
             }
        catch(Exception e)  {   e.printStackTrace();
                            }
   }

  /**Component initialization*/
  private void jbInit() throws Exception
  { //setIconImage(Toolkit.getDefaultToolkit().createImage(Frame4.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    statusBar.setFont(f1);
    jTextArea1.setFont(f1);
    this.setSize(new Dimension(450, 400));
    this.setTitle("DBServer " + nome + " - Debug Window -");
    jTextArea1.setEditable(false);
    statusBar.setText(" debug mode ");
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextArea1, null);

  }
  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  {  super.processWindowEvent(e);
     if (e.getID() == WindowEvent.WINDOW_CLOSING)
     {   System.exit(0);
     }
  }

  public void println(String testo)
  { jTextArea1.append(testo+"\n");
  }

  public void print(String testo)
  { jTextArea1.append(testo);
  }
}