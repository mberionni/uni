package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CORBA.IRObject;
import org.omg.CORBA.Container;

public interface IDLBrowserInterface
{
  public DefaultMutableTreeNode browse(IRObject irObj, DefaultMutableTreeNode node, org.omg.CORBA.Object ior, boolean isList);

  public DefaultMutableTreeNode browse(Container container, DefaultMutableTreeNode node);

  public void viewAbsoluteNames(boolean flag);

}