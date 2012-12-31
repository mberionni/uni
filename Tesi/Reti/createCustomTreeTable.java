package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import TreeTablePackage.JTreeTable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFrame;

public class CreateCustomTreeTable
{   public static JTreeTable createTreeTable(JFrame frame, DefaultMutableTreeNode node)
    {   MyTreeTableDataModel model = new MyTreeTableDataModel(node, frame);
        MyTreeCellRenderer cellRenderer = new MyTreeCellRenderer();
        JTreeTable treeTable = new JTreeTable(model, cellRenderer);
        MyTableRenderer tableRenderer = new MyTableRenderer();
        treeTable.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
        MyTableEditor tableEditor = new MyTableEditor(frame, treeTable);
        treeTable.getColumnModel().getColumn(1).setCellEditor(tableEditor);
        return treeTable;
    }
}