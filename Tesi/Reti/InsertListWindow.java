package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Dimension;
import TreeTablePackage.JTreeTable;

 public class InsertListWindow extends InsertWindow
 { protected JButton NextButton = new JButton();
   protected MyInsertFrameInterface frame;
   protected JTreeTable treeTable = null;  //La treeTable visualizzata in questa finestra
   protected DefaultMutableTreeNode nodeBase = null, WindowNode = null;

   public InsertListWindow(MyInsertFrameInterface _frame, String title, DefaultMutableTreeNode _nodeBase)
   {   super();
       this.setTitle("Lista: " + title);
       this.SendButton.setText("Insert");
       this.setSize(new Dimension(380, 270));
       frame = _frame;
       nodeBase = _nodeBase;
       println("nodebase = " + nodeBase.toString());
       this.getContentPane();//??
       NextButton.setFont(bFont);
       NextButton.setText("Next");
       NextButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(ActionEvent e) {
         NextButton_actionPerformed(e);
       }
       });
       this.jPanel2.add(NextButton, null);
     }

  public void setTree( DefaultMutableTreeNode _node)
  {   println("set tree e node in insertlistwindow ok ");
      WindowNode = clean_clone(_node);
      treeTable = CreateCustomTreeTable.createTreeTable(this, WindowNode);
      this.addTreeTable(treeTable);

  }

  void NextButton_actionPerformed(ActionEvent e)
  {   if (allInserted())
      {   try
          { println("premuto bottone next ");
            frame.resetTree(nodeBase, WindowNode);
            WindowNode = clean_clone(WindowNode);
            treeTable = CreateCustomTreeTable.createTreeTable(this, WindowNode);
            addTreeTable(treeTable);
          } catch (Exception ex) {ex.printStackTrace();}
      }
      else
      {   showMessageDialog("Inserimento argomenti incompleto!", WARNING_MESSAGE);
      }
  }

  protected void CancelButton_actionPerformed(ActionEvent e)
  {   clearWindow();
  }

  protected void SendButton_actionPerformed(ActionEvent e)  // è in realtà il bottone di insert!!!
  {   if (allInserted())
      {   frame.resetTree(nodeBase, WindowNode);
          processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      }
      else
      {   showMessageDialog("Inserimento argomenti incompleto!", WARNING_MESSAGE);
      }
  }

  public boolean allInserted()
  {   int rows = treeTable.getRowCount();
      for (int i = 0; i < rows; i++)
      {   if (isCellEditable(i))
          {   if (  ((String)treeTable.getValueAt(i, 1)).compareTo("") == 0 )
              {   return false;
              }
          }
      }
      return true;
  }

  public boolean isCellEditable(int row)
  {   DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)treeTable.getValueAt(row, 0);
      if (nodo.isRoot())   {   return false;
                           }
      OggettoNodo nodoOgg = (OggettoNodo)nodo.getUserObject();
      if (nodoOgg.getOggettoIR() instanceof org.omg.CORBA.Container)
      {   return false;
      }
      else
      {   return true;
      }
  }

  public void clearWindow()
  {   int rows = treeTable.getRowCount();
      for (int i = 0; i < rows; i++)
      {   treeTable.setValueAt("", i, 1);
      }
      this.repaint();
  }

  public DefaultMutableTreeNode clean_clone(DefaultMutableTreeNode node)
    {   DefaultMutableTreeNode clone;
        if (node == null)  { return null;
                           };
        Object ogg = node.getUserObject();
        if (ogg instanceof OggettoNodo)
        {   OggettoNodo ogg1 = (OggettoNodo)ogg;
            OggettoNodo nuovoOgg = new OggettoNodo(ogg1.getOggettoIR(), ogg1.getDescription(), ogg1.getName(), ogg1.getSignature(), ogg1.getIOR());
            nuovoOgg.setValue("");
            clone =  new DefaultMutableTreeNode(nuovoOgg, true);
        }
        else
        {    println("nodo senza oggetto "+node.toString());
             clone =  new DefaultMutableTreeNode(node, true);
        }

        for (int len = node.getChildCount(), i = 0; i < len; i++)
        {    clone.add(clean_clone((DefaultMutableTreeNode)node.getChildAt(i))); // recursive call
        }

        return clone;

  }


}
