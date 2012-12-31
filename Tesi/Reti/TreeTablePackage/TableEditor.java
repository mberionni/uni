package TreeTablePackage;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import javax.swing.JFrame;


  public class TableEditor extends JTextField implements TableCellEditor   // Editor della colonna Value
  {   JFrame frame;
      JTreeTable treeTable;
      Font f1 = new Font("Verdana", Font.PLAIN, 11);

        public TableEditor()
        {   super();
            addActionListener(new ActionListener()
            {    public void actionPerformed(ActionEvent event)
                 {  //System.out.println("lancio ev editing stopped");
                    fireEditingStopped();
                 }
            });
        }

        public TableEditor(JFrame _frame, JTreeTable _treeTable)
        {   super();
            treeTable = _treeTable;
            frame = _frame;
            this.setFont(f1);
            addActionListener(new ActionListener()
            {    public void actionPerformed(ActionEvent event)
                 {  //System.out.println("lancio ev editing stopped");
                    fireEditingStopped();
                 }
            });
        }

        public Component getTableCellEditorComponent(JTable table, java.lang.Object value, boolean isSelected, int r, int c)
        {   //System.out.println("getTableCellEditorComponent value = "+value.toString()+" row="+r+" col="+c);
            this.setText("");
            this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            return this;
        }

        public java.lang.Object getCellEditorValue()
        {    //System.out.println("getcellEdvaL return "+this.getText());
             return this.getText();
        }

        public boolean shouldSelectCell(EventObject anEvent)
        {   return false;
        }

        public boolean stopCellEditing()
        {   fireEditingStopped();
            return true;
        }

        public void cancelCellEditing()
        {   fireEditingCanceled();
        }

        public void addCellEditorListener(CellEditorListener l)
        {   listenerList.add(CellEditorListener.class, l);
        }

        public void removeCellEditorListener(CellEditorListener l)
        {   listenerList.remove(CellEditorListener.class, l);
        }

        protected void fireEditingStopped()
        {   java.lang.Object[] listeners = listenerList.getListenerList();
            for (int i = listeners.length-2; i>=0; i-=2)
            {    if (listeners[i]==CellEditorListener.class)
                {   ((CellEditorListener)listeners[i+1]).editingStopped(new ChangeEvent(this));
                }
            }
        }

        protected void fireEditingCanceled()
        {   java.lang.Object[] listeners = listenerList.getListenerList();
            for (int i = listeners.length-2; i>=0; i-=2)
            {   if (listeners[i]==CellEditorListener.class)
                {   ((CellEditorListener)listeners[i+1]).editingCanceled(new ChangeEvent(this));
                }
            }
        }

	public boolean isCellEditable(EventObject e)
        {   if (e instanceof MouseEvent)
            {   return true;
            }   else
            //System.out.println("non mouse event o non 2 clicks --> false");
            return false;
	}

   protected Point getLocation(Dimension newWindowDimension)
   {    Point ret;
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int XScreenSize = ScreenSize.width;
        Point TopLeftLocation = frame.getLocation();
        Dimension Size = frame.getSize();
        int YPos =  TopLeftLocation.y;
        int XPos =  TopLeftLocation.x + Size.width;
        int XRemainsRight = XScreenSize - XPos;
        int XRemainsLeft = TopLeftLocation.x;
        if (XRemainsRight > XRemainsLeft)
        {   ret = new Point(XPos, YPos);
        }
        else
        {   ret = new Point(TopLeftLocation.x - newWindowDimension.width, YPos);
        }
        return ret;
    }

}

