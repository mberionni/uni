package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import TreeTablePackage.TableEditor;
import java.awt.*;
import java.util.EventObject;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFrame;
import TreeTablePackage.JTreeTable;

public class MyTableEditor extends TableEditor
{   protected JTreeTable treeTable;
    protected JFrame frame;

      public MyTableEditor(JFrame _frame, JTreeTable _treeTable)
      {   super(_frame, _treeTable);
          treeTable = _treeTable;
          frame = _frame;
      }

      public boolean isCellEditable(EventObject e)
      { if (e instanceof MouseEvent)
            {   if ( ((MouseEvent)e).getClickCount() == 2 )
                {     Point click_point = ((MouseEvent)e).getPoint();
                      int row = treeTable.rowAtPoint(click_point);
                      DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)treeTable.getValueAt(row, 0);
                      if (nodo.isRoot())   {   return false;
                                           }
                      OggettoNodo nodoOgg = (OggettoNodo)nodo.getUserObject();
                      DefaultMutableTreeNode parent = (DefaultMutableTreeNode)nodo.getParent();
                      String title = parent.toString();
                      if (nodoOgg.isList())
                      {   InsertListWindow ilw = new InsertListWindow((MyInsertFrameInterface)frame, title, parent);
                          Point location = getLocation(ilw.getSize());
                          ilw.setLocation(location.x, location.y);
                          ilw.setTree(nodo);
                          ilw.setVisible(true);
                          System.out.println("is list! nodo="+nodo.getUserObject().toString() );
                          return false;
                      }
                      else
                      {   if (nodo.getChildCount() == 0)
                          {   return true;
                          }
                      }

                 }
            }
            //System.out.println("non mouse event o non 2 clicks --> false");
            return false;
        }
}