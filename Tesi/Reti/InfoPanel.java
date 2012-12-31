package Reti;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public class InfoPanel extends JPanel
{
  protected JPanel jPanel1 = new JPanel();
  protected JLabel semaphoreLabel = new JLabel();
  protected Font f = ConfigDataAccessClient.f;
  protected Icon semaphoreRedIcon = ConfigDataAccessClient.semaphoreRedIcon;
  protected Icon semaphoreGreenIcon = ConfigDataAccessClient.semaphoreGreenIcon;
  protected Icon semaphoreYellowIcon = ConfigDataAccessClient.semaphoreYellowIcon;
  protected JPanel jPanel2 = new JPanel();
  protected Border border1;
  protected JPanel jPanel3 = new JPanel();
  protected JLabel jLabel8 = new JLabel();
  protected JLabel jLabel9 = new JLabel();
  protected JLabel jLabel11 = new JLabel();
  protected static JTextField timeTextField1 = new JTextField();
  protected JLabel jLabel12 = new JLabel();
  protected JLabel jLabel13 = new JLabel();
  protected JPanel jPanel4 = new JPanel();
  protected GridBagLayout gridBagLayout2 = new GridBagLayout();
  protected GridLayout gridLayout1 = new GridLayout();
  protected GridBagLayout gridBagLayout3 = new GridBagLayout();
  protected GridBagLayout gridBagLayout4 = new GridBagLayout();
  protected JLabel jLabel1 = new JLabel();
  protected GridBagLayout gridBagLayout1 = new GridBagLayout();


  /**Construct the frame*/
  public InfoPanel()
  {  try
     {  jbInit();
     }
     catch(Exception e) {  e.printStackTrace();
                        }
  }

  /**Overridden so we can exit when window is closed*/
 /* protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }*/
  private void jbInit() throws Exception
  { border1 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(2,2,3,3));
    this.setLayout(gridBagLayout4);
    jPanel1.setLayout(gridBagLayout3);
    semaphoreLabel.setIcon(semaphoreRedIcon);
    jPanel1.setBackground(Color.white);
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel1.setMaximumSize(new Dimension(1400, 80));
    jPanel1.setMinimumSize(new Dimension(100, 80));
    jPanel1.setPreferredSize(new Dimension(453, 80));
    this.setBorder(border1);
    this.setMaximumSize(new Dimension(1600, 80));
    this.setMinimumSize(new Dimension(118, 80));
    this.setPreferredSize(new Dimension(471, 80));
    jPanel2.setBackground(Color.white);
    jPanel2.setMaximumSize(new Dimension(1600, 10));
    jPanel2.setMinimumSize(new Dimension(6, 8));
    jPanel2.setPreferredSize(new Dimension(6, 8));
    jPanel2.setLayout(gridLayout1);
    jPanel3.setLayout(gridBagLayout2);
    jLabel8.setText("ms.");
    jLabel8.setFont(f);
    jLabel9.setText(" Time Call:");
    jLabel9.setFont(f);
    jLabel11.setText("Method Invocation:");
    jLabel11.setFont(f);
    timeTextField1.setFont(f);
    timeTextField1.setEditable(false);
    jLabel12.setPreferredSize(new Dimension(5, 15));
    jLabel12.setMinimumSize(new Dimension(5, 15));
    jLabel12.setFont(f);
    jLabel13.setText("Current Selection:");
    jLabel13.setFont(f);
    jLabel13.setMaximumSize(new Dimension(101, 17));
    jLabel13.setMinimumSize(new Dimension(101, 17));
    jLabel13.setPreferredSize(new Dimension(101, 17));
    jPanel4.setLayout(gridBagLayout1);
    jPanel3.setBackground(Color.white);
    jPanel3.setMaximumSize(new Dimension(1600, 50));
    jPanel3.setPreferredSize(new Dimension(412, 50));
    jPanel4.setMaximumSize(new Dimension(1600, 20));
    jPanel4.setMinimumSize(new Dimension(371, 20));
    jPanel4.setOpaque(false);
    jPanel4.setPreferredSize(new Dimension(412, 20));
    jLabel1.setFont(f);
    jLabel1.setToolTipText("");
    jLabel1.setText(" ");
    this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 11, 5));
    jPanel1.add(jPanel2, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 35, 58));
    jPanel2.add(semaphoreLabel, null);

    jPanel1.add(jPanel3, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 0, 0), 0, 0));

    jPanel3.add(jLabel11, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 4, 0, 0), 0, 3));
    jPanel3.add(jLabel12, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(4, 19, 0, 4), 0, 0));
    jPanel3.add(jLabel13, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(4, 3, 0, 0), 0, 0));
    jPanel3.add(jLabel1, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 19, 0, 4), 240, 0));

    jPanel1.add(jPanel4, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 0), 0, 0));

    jPanel4.add(timeTextField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 27, 0, 0), 144, 0));
    jPanel4.add(jLabel8, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 85, 2));
    jPanel4.add(jLabel9, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 0), 38, 0));
  }

  protected void setMethodSelectedLabelText(String text)
  {   jLabel1.setText(text);
      jLabel1.setToolTipText(text);
  }

  protected void setjLabel2Text(String text)
  {   jLabel12.setText(text);
      jLabel12.setToolTipText(text);
  }

  protected void setSemaphoreIcon(String color)
  {   if (color.compareTo("green") == 0)
          semaphoreLabel.setIcon(semaphoreGreenIcon);
      else
      if (color.compareTo("red") == 0)
          semaphoreLabel.setIcon(semaphoreRedIcon);
      else
      if (color.compareTo("yellow") == 0)
          semaphoreLabel.setIcon(semaphoreYellowIcon);

  }

}