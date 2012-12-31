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


public interface MyLogFrameInterface extends MyTreeTableFrameInterface
{
  public void openSavedSession();
  public void beginSession(String logFileName);
  public void addCurrentAction(String request, String response, DefaultMutableTreeNode requestNode, DefaultMutableTreeNode responseNode);
  public void setTextWindow(TextFrameInterface _textWin);
  public void setStatusBarText(String text);

}