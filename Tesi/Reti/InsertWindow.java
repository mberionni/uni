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
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.table.TableModel;
import java.util.Enumeration;
import TreeTablePackage.JTreeTable;

public class InsertWindow extends MyTreeTableFrame implements MyInsertFrameInterface
{ protected JPanel contentPane;
  protected JPanel jPanel1 = new JPanel();
  protected JPanel jPanel2 = new JPanel();
  protected JButton CancelButton = new JButton();
  protected JButton SendButton = new JButton();
  protected FlowLayout flowLayout1 = new FlowLayout();
  protected BorderLayout borderLayout2 = new BorderLayout();
  protected BorderLayout borderLayout1 = new BorderLayout();
  protected RequestResponseManagementInterface managerClass;
  protected DefaultMutableTreeNode node = null;

  protected String buttonFont  = ConfigDataAccessClient.buttonFont;
  protected String treeFont = ConfigDataAccessClient.treeFont;
  protected Color treeBackColor = ConfigDataAccessClient.treeBackColor;
  protected Font bFont = new Font(buttonFont,java.awt.Font.PLAIN, 10);
  protected Font tFont = new Font(treeFont,java.awt.Font.PLAIN, 10);

  public JFrame getWindow()
  {   return this;
  }

  public InsertWindow()
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {  jbInit();
          }
      catch(Exception e)  {   e.printStackTrace();
                          }
  }

  private void jbInit() throws Exception, globaldefs.ProcessingFailureException
  { contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout2);
    this.setSize(new Dimension(500, 320));
    this.setTitle("Inserimento valori parametri di ingresso");

    jPanel1.setLayout(borderLayout1);
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setLayout(flowLayout1);
    CancelButton.setFont(bFont);
    CancelButton.setText("Clear");
    CancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CancelButton_actionPerformed(e);
      }
    });
    SendButton.setFont(bFont);
    SendButton.setText("Send Request!");
    SendButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SendButton_actionPerformed(e);
      }
    });
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(treeTableScrollPane, BorderLayout.CENTER);
    treeTableScrollPane.getViewport().setBackground(treeBackColor);
    contentPane.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(CancelButton, null);
    jPanel2.add(SendButton, null);
  }

  protected void processWindowEvent(WindowEvent e)
  {   super.processWindowEvent(e);
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {  }
  }

  protected void CancelButton_actionPerformed(ActionEvent e)
  {   clearWindow();
  }

  protected void SendButton_actionPerformed(ActionEvent e)
  {   if (allInserted())
      {   //println("inserimento args avvenuto correttamente");
          processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
          managerClass.returnFromIns();
      }
      else
      {   showMessageDialog("Inserimento argomenti incompleto!", ERROR_MESSAGE);
      }
  }

  public void setClass(RequestResponseManagementInterface _managerClass)
  {   managerClass = _managerClass;
  }

  public void setTree(JTreeTable _treeTable, DefaultMutableTreeNode _node)
  {   treeTable = _treeTable;
      node = _node;
      JTree treeToExpand = treeTable.getTree();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode) (treeToExpand.getModel().getRoot());
      expandAll(treeToExpand, root.children());
  }

  public void resetTree(DefaultMutableTreeNode _nodeBase, DefaultMutableTreeNode _nodeToAdd)
  {   println("reset tree nodeBase = " + _nodeBase + " nodeToAdd " + _nodeToAdd + " node " + node + " node child count " + node.getChildCount());
      _nodeBase.add(_nodeToAdd);
      treeTable = CreateCustomTreeTable.createTreeTable(this, node);
      this.addTreeTable(treeTable);
      JTree tree = treeTable.getTree();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
      expandAll(tree, root.children());
  }
  public void expandAll(JTree t, Enumeration enum)
  {   while (enum.hasMoreElements())
      {   DefaultMutableTreeNode child = (DefaultMutableTreeNode)enum.nextElement();
          TreePath path = new TreePath(child.getPath());
          t.expandPath(path);
          expandAll(t, child.children());
      }
  }

  public boolean allInserted()
  {   int rows = treeTable.getRowCount();
      for (int i = 0; i < rows; i++)
      {   if (isCellEditable(i))
          {   if (  ((String)treeTable.getValueAt(i, 1)).compareTo("") == 0 )
              {  return false;
              }
          }
      }
      return true;
  }

  public boolean isCellEditable(int row)
  {   DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)treeTable.getValueAt(row, 0);
      if (nodo.isRoot())   {   return false;
                           }
      if (nodo.getChildCount() != 0)
      {   return false;
      }
      else
      {   return true;
      }
  }

  public void clearWindow()
  {   int rows = treeTable.getRowCount();
      System.out.println("clearwindow  rows = "+ rows);
      for (int i = 0; i < rows; i++)
      {   if (isCellEditable(i))
          {   treeTable.setValueAt("", i, 1);
          }
      }
      this.repaint();
  }

}