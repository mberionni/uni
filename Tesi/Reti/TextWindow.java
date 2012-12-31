package Reti;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.awt.Font;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextWindow extends MyFrame
{ protected JPanel contentPane;
  protected BorderLayout borderLayout1 = new BorderLayout();
  protected JScrollPane jScrollPane1 = new JScrollPane();
  protected JTextArea jTextArea1 = new JTextArea();
  protected Font f1 = new Font("Verdane", Font.PLAIN, 11);

  public TextWindow()
  { enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try   {  jbInit("");
          }
    catch(Exception e)
    {    e.printStackTrace();
    }
  }

   public TextWindow( String nome )
   {    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
         try  {  jbInit(nome);

              }
        catch(Exception e)  {   e.printStackTrace();
                            }
   }

  private void jbInit(String nome) throws Exception
  { //setIconImage ( Toolkit.getDefaultToolkit().createImage(clientIconPath) );
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    jTextArea1.setFont(f1);
    this.setSize(new Dimension(450, 400));
    this.setTitle(nome);
    jTextArea1.setEditable(false);
    contentPane.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextArea1);

  }

  protected void processWindowEvent(WindowEvent e)
  {  super.processWindowEvent(e);
     if (e.getID() == WindowEvent.WINDOW_CLOSING)
     {
     }
  }

  public void println(String text)
  {   System.out.println(text);
      jTextArea1.append(text + "\n");
  }

  public void print(String text)
  {   System.out.print(text);
      jTextArea1.append(text);
  }

  public void setText(String text)
  {   jTextArea1.setText(text);
  }
}