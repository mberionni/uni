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

public interface InteractionManagementInterface
{
   public void init(MyMainFrame _mainW, MyLogFrameInterface _logW, MyFrame _debugW, MyFrame _txtW, MyTreeFrameInterface _irepW, SessionRecorderInterface _sR, IDLBrowserInterface _idlBrowser);
   public void esegui(String mode);
   public void updateRepository();
   public MyTreeFrameInterface getRepositoryWindow();
   public SessionRecorderInterface getSessionRecorder();
   public IDLBrowserInterface getIDLBrowser();
   public MyMainFrame getMainWindow();
   public MyFrame getDebugWindow();
   public MyLogFrameInterface getLogWindow();
   public MyFrame getTxtWindow();
   public void recEnable();
   public void recDisable();
   public boolean isRecEnabled();
   public void visualizzaTree(DefaultMutableTreeNode rootNode, String textualResponse);
   // manca visualizza tree e tutti i metodi di rec enable



}