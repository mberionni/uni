package Reti;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ServerMonitorWindow extends MyFrame
{ JPanel contentPane;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  static JTextArea jTextArea1 = new JTextArea();
  Font f1 = ConfigDataAccessServer.f1;
  String serverIconPath = ConfigDataAccessServer.serverIconPath;

  public ServerMonitorWindow()
  { enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try   {  jbInit("");
          }
    catch(Exception e)
    {    e.printStackTrace();
    }
  }

   public ServerMonitorWindow( String nome )
   {    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
         try  {  jbInit(nome);

              }
        catch(Exception e)  {   e.printStackTrace();
                            }
   }

  private void jbInit(String nome) throws Exception
  { if (nome.compareToIgnoreCase("Server status") == 0)
    setIconImage ( Toolkit.getDefaultToolkit().createImage(serverIconPath) );
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    statusBar.setFont(f1);
    jTextArea1.setFont(f1);
    this.setSize(new Dimension(450, 400));
    this.setTitle(nome);
    jTextArea1.setEditable(false);
    statusBar.setText("");
    contentPane.add(statusBar, BorderLayout.SOUTH);
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
  {  //System.out.println(text);
     jTextArea1.append(text + "\n");
  }

  public void print(String text)
  {  //System.out.print(text);
     jTextArea1.append(text);
  }

  public void setText(String text)
  {   jTextArea1.setText(text);
  }

  public static void appendText(String text)
  {   jTextArea1.append(text + "\n");
  }
}