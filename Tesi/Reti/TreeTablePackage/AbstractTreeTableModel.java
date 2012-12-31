package TreeTablePackage;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;

 public abstract class AbstractTreeTableModel implements TreeTableModel
 {  protected EventListenerList listenerList = new EventListenerList();

    public boolean isLeaf(Object node)
    {    return getChildCount(node) == 0;
    }

   public void valueForPathChanged(TreePath path, Object newValue)
   {  }

    // This is not called in the JTree's default mode: use a naive implementation.
   public int getIndexOfChild(Object parent, Object child) {
       for (int i = 0; i < getChildCount(parent); i++) {
	    if (getChild(parent, i).equals(child)) {
	        return i;
	    }
        }
	return -1;
    }

    public int getChildCount(java.lang.Object node)
    {	java.lang.Object[] children = getChildren(node);
	return (children == null) ? 0 : children.length;
    }

    public java.lang.Object getChild(java.lang.Object node, int i)
    {	return getChildren(node)[i];
    }

   protected java.lang.Object[] getChildren(java.lang.Object node)
    {   DefaultMutableTreeNode fileNode;
        fileNode = (DefaultMutableTreeNode)node;
        int k = 0;
        java.util.Enumeration e = fileNode.children();
        while (e.hasMoreElements())
        {  e.nextElement();
           k++;
        }
        java.lang.Object[] uscita = new java.lang.Object [k];
        k = 0;
        e = fileNode.children();
        while (e.hasMoreElements())
        {  uscita[k] = e.nextElement();
           k++;
       }
	return uscita;
    }

    public void addTreeModelListener(TreeModelListener l)
    {  listenerList.add(TreeModelListener.class, l);
    }

    public void removeTreeModelListener(TreeModelListener l)
    {  listenerList.remove(TreeModelListener.class, l);
    }

    public Class getColumnClass(int column)
    {  return Object.class;
    }


    // Left to be implemented in the subclass:
    /*
     *   public int getColumnCount()
     *   public String getColumnName(Object node, int column)
     *   public Object getValueAt(Object node, int column)
     *   public Object setValueAt(Object aValue, Object node, int column)
     */
}
