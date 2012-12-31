package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public interface MyFrameInterface
{
   public void showMessageDialog(String text, int type);

   //public void setStatusBarText(String text);
   public void setVisible(boolean flag);
   public boolean isVisible();
   public void setTitle(String title);
   public String getTitle();
   public void println(String text);

}