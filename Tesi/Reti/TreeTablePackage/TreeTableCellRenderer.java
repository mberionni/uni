package TreeTablePackage;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.tree.*;

    // Renderer della colonna Name (del tree)
    public class TreeTableCellRenderer extends JTree implements TableCellRenderer
    {	/** Last table/tree row asked to renderer. */
	protected int visibleRow;
        JTreeTable JTreeTableClass;

	public TreeTableCellRenderer(TreeModel model)
        {   super(model);
	}

        public void setTreeTable(JTreeTable _JTreeTableClass)
        { JTreeTableClass = _JTreeTableClass;
        }

	/**
	 * updateUI is overridden to set the colors of the Tree's renderer
	 * to match that of the table.
	 */
	public void updateUI()
        {   super.updateUI();
	    TreeCellRenderer tcr = getCellRenderer();
	    if (tcr instanceof DefaultTreeCellRenderer) {
                DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer)tcr);
		dtcr.setTextSelectionColor(UIManager.getColor("Table.selectionForeground"));
		dtcr.setBackgroundSelectionColor(UIManager.getColor("Table.selectionBackground"));
	    }

         }

	/* Sets the row height of the tree, and forwards the row height to
	 * the table.*/
	public void setRowHeight(int rowHeight)
        {   if (rowHeight > 0)
            {   super.setRowHeight(rowHeight);
		if (JTreeTableClass != null && JTreeTableClass.getRowHeight() != rowHeight)
                {   JTreeTableClass.setRowHeight(getRowHeight());
		}
	    }
	}

	/* This is overridden to set the height to match that of the JTable.*/
	public void setBounds(int x, int y, int w, int h)
        {    super.setBounds(x, 0, w, JTreeTableClass.getHeight());
	}

	/* Sublcassed to translate the graphics such that the last visible
	   row will be drawn at 0,0. */

	public void paint(Graphics g)
        {   //System.out.println("metodo paint !!!");
            g.translate(0, -visibleRow * getRowHeight());
	    super.paint(g);
	}


	// TreeCellRenderer method. Overridden to update the visible row.

	public Component getTableCellRendererComponent(JTable table,
						       java.lang.Object value,
						       boolean isSelected,
						       boolean hasFocus,
						       int row, int column)
        {   if(isSelected)
		setBackground(table.getSelectionBackground());
	    else
		setBackground(table.getBackground());
	    visibleRow = row;
            //System.out.println("tree table cell renderer - getTableCellRendererComponent");
	    return this;
	}
    } //renderer
