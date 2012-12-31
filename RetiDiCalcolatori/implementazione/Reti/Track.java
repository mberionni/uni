package Reti;

import javax.swing.UIManager;
import java.awt.*;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */
import javax.swing.JFrame;

public class Track
{ boolean packFrame = false;

  /**Construct the application*/
  public Track(JFrame frm)
  {  Frame3 frame = new Frame3(frm);
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
    {  frame.pack();
    }
    else
    {  frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
    {  frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {  frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

}