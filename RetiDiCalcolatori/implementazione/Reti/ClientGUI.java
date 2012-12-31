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

public class ClientGUI
 {  boolean packFrame = false;
    Frame1 frame1;

    public ClientGUI()
    {   frame1 = new Frame1();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {     frame1.pack();
                       }
        else {    frame1.validate();
             }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame1.getSize();
        if (frameSize.height > screenSize.height) {    frameSize.height = screenSize.height;
                                                  }
        if (frameSize.width > screenSize.width)   {      frameSize.width = screenSize.width;
                                                  }
        frame1.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame1.setVisible(true);

    }

    public Frame1 getFrame1()
    { return frame1;
    }

     public Frame2 getFrame2()
    { return frame1.getFrame2();
    }

}