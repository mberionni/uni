package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import TreeTablePackage.JTreeTable;

 public abstract class MyTreeTableFrame extends MyFrame implements MyTreeTableFrameInterface
 {  protected JScrollPane treeTableScrollPane = new JScrollPane();
    protected JLabel statusBar = new JLabel();
    protected JTreeTable treeTable = null;

    public void addTreeTable(JTreeTable _treeTable)
    {   treeTableScrollPane.getViewport().removeAll();
        treeTableScrollPane.getViewport().add(_treeTable, null);
        treeTableScrollPane.updateUI();
    }

    public void setStatusBarText(String text)
    {   statusBar.setText(text);
    }
 }