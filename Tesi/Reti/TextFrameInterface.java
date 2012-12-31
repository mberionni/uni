package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import java.awt.Dimension;
import java.awt.Point;

 public interface TextFrameInterface extends MyFrameInterface
 {    //public void setCurrentFile(String text);
      public void setText(String text);
      public void viewFile(String file) throws java.io.IOException;
      public String getCurrentFile();
      public Dimension getSize();
      public void setLocation(Point p);

 }