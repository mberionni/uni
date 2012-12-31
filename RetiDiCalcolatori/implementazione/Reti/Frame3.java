package Reti;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class Frame3 extends JFrame
{ JPanel contentPane;
  JLabel statusBar = new JLabel();
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel2 = new JPanel();
  private static DefaultListModel listModel = new DefaultListModel();
  JList jList1 = new JList(listModel);
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  Frame1 frame;
  /**Construct the frame*/
  public Frame3(JFrame frm)
  {   frame = (Frame1)frm;
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {   jbInit();
          }
      catch(Exception e) {    e.printStackTrace();
                         }
  }

  /**Component initialization*/
  private void jbInit() throws Exception
  { //setIconImage(Toolkit.getDefaultToolkit().createImage(Frame3.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(gridBagLayout2);
    this.setSize(new Dimension(414, 407));
    this.setTitle("Log file");
    statusBar.setBorder(BorderFactory.createRaisedBevelBorder());
    statusBar.setText(" ");
    jPanel1.setBackground(Color.gray);
    jPanel1.setLayout(borderLayout1);
    jPanel2.setLayout(gridBagLayout1);
    jButton1.setText("jButton1");
    jButton2.setText("jButton2");
    contentPane.add(statusBar, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
    contentPane.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(7, 9, 0, 8), 15, 168));
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jList1, null);
    jPanel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jTextField1, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 6, 0, 5), 367, 11));
    jPanel2.add(jButton2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(6, 21, 4, 98), 0, 0));
    jPanel2.add(jButton1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(6, 105, 4, 0), 0, 0));
  }

  protected static void stampa(String line)
  {   System.out.println("metodo stampa line="+line);
      listModel.addElement(line);
  }

  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  {  super.processWindowEvent(e);
     if (e.getID() == WindowEvent.WINDOW_CLOSING)
     {   frame.enableButton1(); //jButton1.setEnabled(true);
        // System.exit(0);
     }
  }
}