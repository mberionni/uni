package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.util.EventObject;
import java.awt.event.MouseEvent;
import TreeTablePackage.TableEditor;

public class MyTableEditorNonEditable extends TableEditor
{
  public boolean isCellEditable(EventObject e)
  {   //System.out.println("table editor is cell ed non ed");
      if (e instanceof MouseEvent)
      {   MouseEvent me = (MouseEvent)e;
          if (me.getClickCount() > 1)
          {   getToolkit().beep();
          }
      }
      return false;
  }

}