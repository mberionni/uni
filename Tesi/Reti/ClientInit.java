package Reti;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Window;

 public class ClientInit
 {  protected boolean packFrame = false;
    protected MyMainFrame applicationWindow;
    protected MyLogFrameInterface logWindow;
    protected MyFrame debugWindow, textualOutputWindow;
    protected TextFrameInterface logTextWindow;
    protected MyTreeFrameInterface irepWindow;
    protected SessionRecorderInterface sessionRecorder;
    protected InteractionManagementInterface actions;
    protected IDLBrowserInterface idlBrowser;
    protected Window dlg;
    protected InfoPanel iP;

    public ClientInit()
    {   actions = new InteractionManagement();
        applicationWindow = new ApplicationGUI(actions);
        iP = new InfoPanel();
        dlg = new Window(applicationWindow);
        dlg.add(iP);
        ((ApplicationGUI)applicationWindow).setInfoPanelWindow(dlg);
        logWindow = new LogWindow();
        debugWindow = new TextWindow("Debug window");
        textualOutputWindow = new TextWindow("Textual output window");
        logTextWindow = new LogTextWindow();
        irepWindow = new IREPWindow();
        sessionRecorder = new SessionRecorder();
        idlBrowser = new IDLBrowser();
        actions.init(applicationWindow, logWindow, debugWindow, textualOutputWindow, irepWindow, sessionRecorder, idlBrowser);
        logWindow.setTextWindow(logTextWindow);
        setMainWindow();
        setWindows();

    }

    protected  void setMainWindow()
    {   if (packFrame) {     applicationWindow.pack();
                       }
        else {    applicationWindow.validate();
             }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = applicationWindow.getSize();
        if (frameSize.height > screenSize.height) {    frameSize.height = screenSize.height;
                                                  }
        if (frameSize.width > screenSize.width)   {      frameSize.width = screenSize.width;
                                                  }
        applicationWindow.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height - 80) / 2);
        applicationWindow.setVisible(true);
        applicationWindow.toFront();

    int width = applicationWindow.getWidth();
    int height = applicationWindow.getHeight();
    System.out.println("widht = " + width);
    Point topLeft = applicationWindow.getLocation();
    dlg.setSize(width, 80);
    iP.setSize(width, 80);
    iP.updateUI();
    iP.validate();
    dlg.setLocation((int)topLeft.getX(), (int)topLeft.getY() + height);
    dlg.setVisible(true);
    dlg.toFront();
    }

    protected  void setWindows()
    {   debugWindow.setVisible(false);
        textualOutputWindow.setVisible(false);
        logWindow.setVisible(false);
        logTextWindow.setVisible(false);
    }

}