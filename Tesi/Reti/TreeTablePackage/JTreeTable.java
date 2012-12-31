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
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import java.awt.Dimension;
import java.util.EventObject;
import java.awt.Font;

/**
 * This example shows how to create a simple JTreeTable component,
 * by using a JTree as a renderer (and editor) for the cells in a
 * particular column in the JTable.
 */
public class JTreeTable extends JTable
 {  // Una sottoclasse del JTree.
    protected TreeTableCellRenderer tree;
    Font f1 = new Font("Verdana", Font.PLAIN, 11);

    public JTreeTable(TreeTableModel treeTableModel, DefaultTreeCellRenderer renderer)
    {   super();
        // Create the tree. It will be used as a renderer and editor.
	tree = new TreeTableCellRenderer(treeTableModel);
        tree.setTreeTable(this);
        tree.setCellRenderer(renderer);

	// Install a tableModel representing the visible rows in the tree.
	super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

	// Force the JTable and JTree to share their row selection models.
	ListToTreeSelectionModelWrapper selectionWrapper = new ListToTreeSelectionModelWrapper();
	tree.setSelectionModel(selectionWrapper);
        selectionWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	setSelectionModel(selectionWrapper.getListSelectionModel());

	// Install the tree editor renderer and editor.
        tree.setFont(f1);
	setDefaultRenderer(TreeTableModel.class, tree);
        TreeTableCellEditor cellEditor = new TreeTableCellEditor();
        cellEditor.setTree(tree);
        setDefaultEditor(TreeTableModel.class, cellEditor);

	// No grid.
	setShowGrid(false);

	// No intercell spacing
	setIntercellSpacing(new Dimension(0, 0));

	// And update the height of the trees row to match that of
	// the table.
	if (tree.getRowHeight() < 1)
        {   // Metal looks better like this.
	    setRowHeight(18);
	}
    }


    public JTreeTable(TreeTableModel treeTableModel)
    {   super();
        // Create the tree. It will be used as a renderer and editor.
	tree = new TreeTableCellRenderer(treeTableModel);
        tree.setTreeTable(this);

	// Installazione del modello dei dati per la TreeTable, cioè per la tabella
        // l'adattatore media fra tree e table
	super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

	// Force the JTable and JTree to share their row selection models.
	ListToTreeSelectionModelWrapper selectionWrapper = new ListToTreeSelectionModelWrapper();
	tree.setSelectionModel(selectionWrapper);
        selectionWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	setSelectionModel(selectionWrapper.getListSelectionModel());

	// Install the tree editor renderer and editor.
        tree.setFont(f1);
	setDefaultRenderer(TreeTableModel.class, tree);
        TreeTableCellEditor cellEditor = new TreeTableCellEditor();
        cellEditor.setTree(tree);
        setDefaultEditor(TreeTableModel.class, cellEditor);

	// No grid.
	setShowGrid(false);

	// No intercell spacing
	setIntercellSpacing(new Dimension(0, 0));

	// And update the height of the trees row to match that of
	// the table.
	if (tree.getRowHeight() < 1)
        {   // Metal looks better like this.
	    setRowHeight(18);
	}
    }
    /**
     * Overridden to message super and forward the method to the tree.
     * Since the tree is not actually in the component hieachy it will
     * never receive this unless we forward it in this manner.
     */
    public void updateUI()
    {   super.updateUI();
	if(tree != null)
        {    tree.updateUI();
	}
	// Use the tree's default foreground and background colors in the table.
        //System.out.println("update UI - look and feel");
        LookAndFeel.installColorsAndFont(this, "Tree.background","Tree.foreground", "Tree.font");
    }

    /* Workaround for BasicTableUI anomaly. Make sure the UI never tries to
     * paint the editor. The UI currently uses different techniques to
     * paint the renderers and editors and overriding setBounds() below
     * is not the right thing to do for an editor. Returning -1 for the
     * editing row in this case, ensures the editor is never painted.
     */
    public int getEditingRow()
    {   return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 :
	    editingRow;
    }

    /**
     * Overridden to pass the new rowHeight to the tree.
     */
    public void setRowHeight(int rowHeight)
    {   super.setRowHeight(rowHeight);
	if (tree != null && tree.getRowHeight() != rowHeight) {
            tree.setRowHeight(getRowHeight());
	}
    }

    /**
     * Returns the tree that is being shared between the model.
     */
    public JTree getTree()
    {	return tree;
    }


    /**
     * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel
     * to listen for changes in the ListSelectionModel it maintains. Once
     * a change in the ListSelectionModel happens, the paths are updated
     * in the DefaultTreeSelectionModel.
     */
    class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel
    {	/** Set to true when we are updating the ListSelectionModel. */
	protected boolean         updatingListSelectionModel;

	public ListToTreeSelectionModelWrapper()
        {   super();
	    getListSelectionModel().addListSelectionListener(createListSelectionListener());
	}

	/* Returns the list selection model. ListToTreeSelectionModelWrapper
	 * listens for changes to this model and updates the selected paths
	 * accordingly. */
	ListSelectionModel getListSelectionModel()
        {   return listSelectionModel;
	}

	/**
	 * This is overridden to set <code>updatingListSelectionModel</code>
	 * and message super. This is the only place DefaultTreeSelectionModel
	 * alters the ListSelectionModel.
	 */
	public void resetRowSelection()
        {   //  System.out.println("reset row sel");
	    if(!updatingListSelectionModel)
            {	updatingListSelectionModel = true;
		try {   super.resetRowSelection();
                    }
		finally
                    {   updatingListSelectionModel = false;
                    }
	    }
	    // Notice how we don't message super if
	    // updatingListSelectionModel is true. If
	    // updatingListSelectionModel is true, it implies the
	    // ListSelectionModel has already been updated and the
	    // paths are the only thing that needs to be updated.
	}

	/**
	 * Creates and returns an instance of ListSelectionHandler.
	 */
	protected ListSelectionListener createListSelectionListener()
        {   return new ListSelectionHandler();
	}

	/**
	 * If <code>updatingListSelectionModel</code> is false, this will
	 * reset the selected paths from the selected rows in the list
	 * selection model.
	 */
	protected void updateSelectedPathsFromSelectedRows() {
	 // System.out.println("update sel path");
	 if(!updatingListSelectionModel) {
		updatingListSelectionModel = true;
		try {
		    // This is way expensive, ListSelectionModel needs an
		    // enumerator for iterating.
		    int        min = listSelectionModel.getMinSelectionIndex();
		    int        max = listSelectionModel.getMaxSelectionIndex();

		    clearSelection();
		    if(min != -1 && max != -1) {
			for(int counter = min; counter <= max; counter++) {
			    if(listSelectionModel.isSelectedIndex(counter)) {
				TreePath     selPath = tree.getPathForRow
				                            (counter);

				if(selPath != null) {
				    addSelectionPath(selPath);
				}
			    }
			}
		    }
		}
		finally {
		    updatingListSelectionModel = false;
		}
	    }
	}

	/**
	 * Class responsible for calling updateSelectedPathsFromSelectedRows
	 * when the selection of the list changse.
	 */
	class ListSelectionHandler implements ListSelectionListener
        {   public void valueChanged(ListSelectionEvent e)
            {   //  System.out.println("list sel han");
                updateSelectedPathsFromSelectedRows();
	    }
	}
   }
}
