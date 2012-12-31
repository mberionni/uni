package Reti;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public abstract class MyMainFrame extends MyTreeTableFrame
{ protected JScrollPane treeScrollPane;
  protected JButton ConnectButton = new JButton();
  protected JButton DisconnectButton = new JButton();
  protected JButton ExecuteButton = new JButton();

  protected void addTree(JTree tree)
  {   treeScrollPane.getViewport().add(tree);
      treeScrollPane.updateUI();
  }

  public void EnableExecuteButton()
  {   ExecuteButton.setEnabled(true);
  }

  public void DisableExecuteButton()
  {   ExecuteButton.setEnabled(false);
  }

  public void DisableConnectButton()
  {   ConnectButton.setEnabled(false);
      ConnectButton.setToolTipText("connection established!");
  }

  public void EnableDisconnectButton()
  {   DisconnectButton.setEnabled(true);
  }
}