package TreeTablePackage;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;

  public class MyTableRenderer extends DefaultTableCellRenderer    // Renderer della colonna Value
  {
        MyTableRenderer()
        {   setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }

	public void setValue(Object value)
        {   System.out.println("setValue in MyTableRenderer");
            setFont(new Font("Verdana", Font.PLAIN, 11));
            setText((value == null) ? "---" : value.toString());
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column)
        {   super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    return this;
	}
  }
