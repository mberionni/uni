package Reti;
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


 public class MyTreeDataModel extends AbstractTreeTableModel
 {  protected JFrame frame1;
    static protected String[]  cNames = {"Name", "Value"};
    static protected Class[]   cTypes = { TreeTableModel.class, String.class };

    public MyTreeDataModel(DefaultMutableTreeNode node, JFrame _frame)
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
        {   DefaultMutableTreeNode fn = (DefaultMutableTreeNode)node;
            if (fn.isRoot())
            {   switch(column)
                {   case 0:   return fn;
                    case 1:   return "";
                }
            }
            creaOggetto oggNodo = (creaOggetto)fn.getUserObject();
            switch(column)
            {   case 0:   return fn;
                case 1:	  return oggNodo.getValue();
            }
        }
	catch  (Exception e) {   e.printStackTrace();
                             }
        return null;
    }

    public void setValueAt(java.lang.Object aValue, java.lang.Object node, int column)
    {  // System.out.println("setvalueat - filesystemmodel - value "+aValue);
       if (column == 1)
       {   if ( ((DefaultMutableTreeNode)node).isRoot())
           {    System.out.println("Si sta tentando di impostare il valore di un nodo di radice!");
           }
           else
           {   creaOggetto oggNodo = (creaOggetto)(((DefaultMutableTreeNode)node).getUserObject());
               org.omg.CORBA.Any retValue = Client.getOrb().create_any();
               String val = (String)aValue;
               if ( val.compareTo("") == 0 )
               {    oggNodo.setValue("");
               }
               else
               {    try
                   {   IDLType idlType = IDLTypeHelper.narrow(oggNodo.tipo);
                       switch (idlType.type().kind().value())
                       {    case TCKind._tk_string:   System.out.println("tipo string");
                                                      retValue.insert_string(val);
                                                      break;
                            case TCKind._tk_long:     System.out.println("tipo int");
                                                      retValue.insert_long( new Integer(val).intValue() );
                                                      break;
                            case TCKind._tk_longlong: System.out.println("tipo long");
                                                      retValue.insert_longlong( new Long(val).longValue() );
                                                      break;
                            case TCKind._tk_float:    System.out.println("tipo float");
                                                      retValue.insert_float( new Float(val).floatValue() );
                                                      break;
                            case TCKind._tk_boolean:  System.out.println("tipo boolean");
                                                      if ( val.compareToIgnoreCase("true") == 0 || val.compareToIgnoreCase("false") == 0 || val.compareTo("") == 0)
                                                      {   retValue.insert_boolean( new Boolean(val).booleanValue() );
                                                      } else
                                                      {   throw new java.lang.Exception();
                                                      }
                                                      break;
                            default:                  System.out.println("tipo default");
                                                      break;
                       }
                       oggNodo.setValue(aValue.toString());
                       //System.out.println("sto impostando il valore di "+ oggNodo.name+" al valore "+aValue.toString());
                    }  catch (Exception ex)  {    JOptionPane.showMessageDialog(frame1, "argomento di tipo errato", "ERRORE", JOptionPane.ERROR_MESSAGE);
                                             }
                  }
               }
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
