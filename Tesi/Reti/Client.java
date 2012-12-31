// Client.java

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

package Reti;
import org.omg.CORBA.ORB;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import com.inprise.vbroker.interceptor.ChainUntypedObjectWrapperFactory;
import com.inprise.vbroker.interceptor.ChainUntypedObjectWrapperFactoryHelper;
import com.inprise.vbroker.interceptor.Location;
import org.omg.CORBA.Repository;
import org.omg.CORBA.RepositoryHelper;

public class Client
{ protected static ORB orb;
  protected static boolean packFrame = false;
  protected static JWindow splashScreen = null;
  protected static Repository ir;

  public static void main(String[] args)
   {  initClient(args);
   }

   protected static void initClient(String[] args)
   {    try
        {   //createSplashScreen();
            orb = ORB.init(args, null);
            org.omg.CORBA.Object rootObj = Client.getOrb().resolve_initial_references("InterfaceRepository");
            ir = RepositoryHelper.narrow(rootObj);
            initWrapper(orb);
            new ClientInit();
        }   catch (Exception e) {   System.err.println("Unexpected exception caught:");
                                      e.printStackTrace();
                                  }
   }

   public static void initWrapper(org.omg.CORBA.ORB orb) throws Exception
   {    ChainUntypedObjectWrapperFactory Cfactory = ChainUntypedObjectWrapperFactoryHelper.narrow(orb.resolve_initial_references("ChainUntypedObjectWrapperFactory"));
        Cfactory.add(new UtilityObjectWrappers.TimingUntypedObjectWrapperFactory(), Location.CLIENT);
        //Cfactory.add(new UtilityObjectWrappers.TracingUntypedObjectWrapperFactory(), Location.CLIENT);
   }

   protected static void createSplashScreen()
   {    JLabel splashLabel = new JLabel(new ImageIcon("c:/1dati/universita/javaProjects/tesi/codice/classes/ClientResources/bugsFree.jpg"));
        // attenzione non posso utilizzare configDataAccessClient
        splashScreen = new JWindow();
        splashScreen.getContentPane().add(splashLabel);
        splashScreen.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	splashScreen.setLocation(screenSize.width/2 - splashScreen.getSize().width/2,screenSize.height/2 - splashScreen.getSize().height/2);
	splashScreen.show();
        splashScreen.setVisible(true);
   }

    protected static void setGUI(ConnectToServer frame1)
    {   if (packFrame) {     frame1.pack();
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
        //splashScreen.dispose();
        frame1.toFront();
    }

   public static ORB getOrb()
   {  return orb;
   }

   public static void setRepository(Repository _ir)
   {  ir = _ir;
   }

   public static Repository getRepository()
   {  return ir;
   }

}




