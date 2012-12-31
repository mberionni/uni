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
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeModel;
import javax.swing.JFrame;

 public class TreeTable
 {
    public static JTreeTable createTreeTable(JFrame frame, DefaultMutableTreeNode node)
    {   DefaultTreeDataModel model = new DefaultTreeDataModel(node, frame);
        JTreeTable treeTable = new JTreeTable(model);
        MyTableRenderer tableRenderer = new MyTableRenderer();
        treeTable.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
        TableEditor tableEditor = new TableEditor(frame, treeTable);
        treeTable.getColumnModel().getColumn(1).setCellEditor(tableEditor);
        return treeTable;
    }

    public static JTreeTable createTreeTable(TreeTableModel MyTreeDataModel, DefaultTableCellRenderer MyTableRenderer, TableCellEditor MyTableEditor, DefaultTreeCellRenderer treeCellRenderer)
    {   JTreeTable treeTable = new JTreeTable(MyTreeDataModel, treeCellRenderer);
        treeTable.getColumnModel().getColumn(1).setCellRenderer(MyTableRenderer);
        treeTable.getColumnModel().getColumn(1).setCellEditor(MyTableEditor);
        return treeTable;
    }
}