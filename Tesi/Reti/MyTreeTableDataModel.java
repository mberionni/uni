package Reti;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CORBA.IDLType;
import org.omg.CORBA.IDLTypeHelper;
import org.omg.CORBA.TCKind;
import TreeTablePackage.AbstractTreeTableModel;
import TreeTablePackage.TreeTableModel;


 public class MyTreeTableDataModel extends AbstractTreeTableModel
 {  protected JFrame frame1;
    protected Object root;
    static protected String[]  cNames = {"Name", "Value"};
    static protected Class[]   cTypes = { TreeTableModel.class, String.class };


    public MyTreeTableDataModel(DefaultMutableTreeNode node, JFrame _frame)
    {   super();
	root = node;
        frame1 = _frame;

    }

    public Object getRoot()
    {    return root;
    }

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
            OggettoNodo oggNodo = (OggettoNodo)fn.getUserObject();
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
    {  System.out.println("setvalueat -MyTreeTableDataModel - value " + aValue);
       if (column == 1)
       {   if ( ((DefaultMutableTreeNode)node).isRoot())
           {    System.out.println("Si sta tentando di impostare il valore di un nodo di radice!");
           }
           else
           {   OggettoNodo oggNodo = (OggettoNodo)(((DefaultMutableTreeNode)node).getUserObject());
               org.omg.CORBA.Any retValue = Client.getOrb().create_any();
               String val = (String)aValue;
               if ( val.compareTo("") == 0 )
               {    oggNodo.setValue("");
               }
               else
               {    try
                   {   IDLType idlType = IDLTypeHelper.narrow(oggNodo.getOggettoIR());
                       switch (idlType.type().kind().value())
                       {    case TCKind._tk_string:   retValue.insert_string(val);
                                                      break;
                            case TCKind._tk_long:     retValue.insert_long( new Integer(val).intValue() );
                                                      break;
                            case TCKind._tk_longlong: retValue.insert_longlong( new Long(val).longValue() );
                                                      break;
                            case TCKind._tk_float:    retValue.insert_float( new Float(val).floatValue() );
                                                      break;
                            case TCKind._tk_boolean:  if ( val.compareToIgnoreCase("true") == 0 || val.compareToIgnoreCase("false") == 0 || val.compareTo("") == 0)
                                                      {   retValue.insert_boolean( new Boolean(val).booleanValue() );
                                                      } else
                                                      {   throw new java.lang.Exception();
                                                      }
                                                      break;
                            default:                  System.out.println("Attenzione: MyTreeTableDataModel tipo default !!!");
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

    /** By default, make the column with the Tree in it the only editable one.
    *  Making this column editable causes the JTable to forward mouse
    *  and keyboard events in the Tree column to the underlying JTree.
    */
    public boolean isCellEditable(Object node, int column)
    {   if (column == 0)  {   System.out.print("my tree table model is celled return true " );
                              return true;
                          }
        else
        {    DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)node;
             if (nodo.isRoot())   {   System.out.print("my tree table model iscelled return false " );
                                      return false;
                                  }
            else
            {     System.out.print("my tree table model iscelled return true " );
            return true;
            }
        }
    }
}
