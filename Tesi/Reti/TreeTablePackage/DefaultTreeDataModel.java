package TreeTablePackage;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.util.Stack;
import javax.swing.*;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CORBA.*;
import TreeTablePackage.*;


 public class DefaultTreeDataModel extends AbstractTreeTableModel
 {  protected JFrame frame1;
    static protected String[]  cNames = {"Name", "Value"};
    static protected Class[]   cTypes = { TreeTableModel.class, String.class };

    public DefaultTreeDataModel(DefaultMutableTreeNode node, JFrame _frame)
    {   super(null);
	root = node;
        frame1 = _frame;
    }

    // The TreeModel interface
    public int getChildCount(java.lang.Object node)
    {	java.lang.Object[] children = getChildren(node);
	return (children == null) ? 0 : children.length;
    }

    public java.lang.Object getChild(java.lang.Object node, int i)
    {	return getChildren(node)[i];
    }

    public boolean isLeaf(java.lang.Object node)
    {   return ((DefaultMutableTreeNode)node).isLeaf();
    }

    //  interfaccia TreeTableNode

    public int getColumnCount()
    {	return cNames.length;
    }

    public String getColumnName(int column)
    {	return cNames[column];
    }

    public Class getColumnClass(int column)
    {	return cTypes[column];
    }


    public java.lang.Object getValueAt(java.lang.Object node, int column)
    {	//System.out.println("getvalueat - filesystemmodel - colonna = "+column);
        try
        {   switch(column)
            {   case 0:   return "";
                case 1:	  return node.toString();
            }
        }
	catch  (Exception e) {   e.printStackTrace();
                             }
        return null;
    }

    public void setValueAt(java.lang.Object aValue, java.lang.Object node, int column)
    {  // System.out.println("setvalueat - filesystemmodel - value "+aValue);
       if (column == 1)
       {  aValue = node.toString();
       }
       else
       {   System.out.println("non si puo' impostare il valore di una colonna con il tree");
       }
    }

    //
    // Some convenience methods.
    //
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

}
