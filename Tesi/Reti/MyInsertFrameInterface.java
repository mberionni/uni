package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.tree.DefaultMutableTreeNode;
import TreeTablePackage.JTreeTable;
import javax.swing.JFrame;

public interface MyInsertFrameInterface extends MyTreeTableFrameInterface
{  public void resetTree(DefaultMutableTreeNode _nodeBase, DefaultMutableTreeNode _nodeToAdd);
   public void setTree(JTreeTable _treeTable, DefaultMutableTreeNode _node);
   public void setClass(RequestResponseManagementInterface _managerClass);
   public JFrame getWindow();
}