package TreeTablePackage;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

 import javax.swing.JTable;
 import javax.swing.table.TableCellEditor;
 import java.awt.Component;
 import java.util.EventObject;
 import javax.swing.JTextField;
 import javax.swing.event.EventListenerList;
 import javax.swing.event.CellEditorListener;
 import javax.swing.event.ChangeEvent;


/** Editor della colonna Name. Component returned is the JTree.*/
    public class TreeTableCellEditor implements TableCellEditor
    {   protected EventListenerList listenerList = new EventListenerList();
        protected TreeTableCellRenderer tree;

        public void setTree(TreeTableCellRenderer _tree)
        {   tree = _tree;
        }

        public Component getTableCellEditorComponent(JTable table, java.lang.Object value, boolean isSelected, int r, int c)
        {   System.out.println("tree table cell editor - getCellEditorComponent");
            return tree;
	}

	public boolean isCellEditable(EventObject e)
        {   System.out.println("tree table cell editor return false");
            return true;
    	}

        public Object getCellEditorValue()
        { return null; }

        public boolean shouldSelectCell(EventObject anEvent) { return true; }
        public boolean stopCellEditing() { return true; }
        public void cancelCellEditing() {}

        public void addCellEditorListener(CellEditorListener l)
        {   listenerList.add(CellEditorListener.class, l);
        }
        public void removeCellEditorListener(CellEditorListener l)
        {   listenerList.remove(CellEditorListener.class, l);
        }
    }