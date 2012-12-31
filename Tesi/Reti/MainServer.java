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
import java.awt.Toolkit;
import org.omg.CORBA.ORB;

public class MainServer
{ protected static boolean packFrame = false;

  public static void main(String[] args)
  {   try
      {   ORB orb = ORB.init(args, null);
          StartServer s = new StartServer(orb);
          setGUI(s);
          s.setVisible(true);
          orb.run();
      }   catch (Exception e) {   e.printStackTrace();
                              }
  }

  public static void setGUI(StartServer frame1)
    {   if (packFrame) {     frame1.pack();
                       }
        else {    frame1.validate();
             }
        //Posizionamento della finestra al centro del video
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame1.getSize();
        if (frameSize.height > screenSize.height) {    frameSize.height = screenSize.height;
                                                  }
        if (frameSize.width > screenSize.width)   {    frameSize.width = screenSize.width;
                                                  }
        frame1.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame1.setVisible(true);
        frame1.toFront();
    }


}