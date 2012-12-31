package Reti;

import javax.swing.UIManager;
import javax.swing.tree.*;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class ArgsWindow
{ boolean packFrame = false;


  public ArgsWindow()
  {   Frame2 frame = new Frame2();
      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame)
      {   frame.pack();
      }
      else {    frame.validate();
           }
      frame.setVisible(true);
  }

  /**Main method*/
  public static void main(String[] args)
  { try {   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
    catch(Exception e)
        {   e.printStackTrace();
        }
    new ArgsWindow();
  }
}