package Reti;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;


public class ApplicationGUI extends MyMainFrame
{ protected static boolean packFrame = false;
  protected JPanel contentPane;
  protected JMenuBar jMenuBar1 = new JMenuBar();
  protected JMenu jMenuFile = new JMenu();
  protected JMenuItem jMenuFileExit = new JMenuItem();
  protected JMenu jMenuView = new JMenu();
  protected JMenuItem jMenuViewIREP = new JMenuItem();
  protected JMenuItem jMenuViewDEBUG = new JMenuItem();
  protected JMenuItem jMenuViewTXTOUTPUT = new JMenuItem();
  protected JMenuItem jMenuViewInfoPanel = new JMenuItem();
  protected JCheckBoxMenuItem jMenuViewNames = new JCheckBoxMenuItem("View complete names");
  protected JMenu jMenuHelp = new JMenu();
  protected JMenuItem jMenuHelpAbout = new JMenuItem();
  protected JMenu jMenuSessionRec = new JMenu();
  protected JMenuItem jMenuOpenSession = new JMenuItem();
  protected JMenuItem jMenuStartRec = new JMenuItem();
  protected JMenuItem jMenuStopRec = new JMenuItem();
  protected JMenuItem jMenuPlay = new JMenuItem();

  protected JPanel jPanel1 = new JPanel();
  protected JPanel jPanel2 = new JPanel();
  protected JSplitPane jSplitPane1 = new JSplitPane();
  protected JDialog dialog ;
  protected TitledBorder titledBorder1;
  protected emsSessionFactoryClient emsSF;

  protected Font f = ConfigDataAccessClient.f;
  protected Color treeBackColor = ConfigDataAccessClient.treeBackColor;
  protected ImageIcon openFileIcon = ConfigDataAccessClient.openFileIcon;
  protected ImageIcon saveFileIcon = ConfigDataAccessClient.saveFileIcon;
  protected ImageIcon connectIcon = ConfigDataAccessClient.connectIcon;
  protected ImageIcon disconnectIcon = ConfigDataAccessClient.disconnectIcon;
  protected ImageIcon aboutIcon = ConfigDataAccessClient.aboutIcon;
  protected ImageIcon executeIcon = ConfigDataAccessClient.executeIcon;
  protected ImageIcon stopIcon = ConfigDataAccessClient.stopIcon;
  protected ImageIcon playIcon = ConfigDataAccessClient.playIcon;
  protected ImageIcon recIcon = ConfigDataAccessClient.recIcon;
  protected String iconPath = ConfigDataAccessClient.iconPath;
  protected String logHome = ConfigDataAccessClient.logHome;
  protected ImageIcon irepIcon = ConfigDataAccessClient.irepIcon;
  protected ImageIcon txtIcon = ConfigDataAccessClient.txtLogIcon;
  protected ImageIcon infoPanelActivateIcon = ConfigDataAccessClient.infoPanelActivateIcon;
  protected ImageIcon infoPanelDeactivateIcon = ConfigDataAccessClient.infoPanelDeactivateIcon;
  protected ImageIcon writeAttributeIcon = ConfigDataAccessClient.writeAttributeIcon;
  protected ImageIcon readAttributeIcon = ConfigDataAccessClient.readAttributeIcon;
  protected String ConnectButtonText = ConfigDataAccessClient.ConnectButtonText;
  protected String DisconnectButtonText = ConfigDataAccessClient.DisconnectButtonText;
  protected String ExecuteButtonText = ConfigDataAccessClient.ExecuteButtonText;
  protected String PlayButtonText = ConfigDataAccessClient.PlayButtonText;
  protected String OpenButtonText = ConfigDataAccessClient.OpenButtonText;
  protected String StopButtonText = ConfigDataAccessClient.StopButtonText;
  protected String RecButtonText = ConfigDataAccessClient.RecButtonText;
  protected String RecButtonText2 = ConfigDataAccessClient.RecButtonText2;
  protected String LogPathButtonText = ConfigDataAccessClient.LogPathButtonText;
  protected Dimension PreferredButtonDimension = ConfigDataAccessClient.PreferredButtonDimension;
  protected Dimension MinimumButtonDimension = new Dimension(25,25);
  protected Dimension MaximumButtonDimension = new Dimension(35,35);

  protected final JFileChooser fc = new JFileChooser(logHome);
  protected JToolBar MainToolbar = new JToolBar();
  protected JButton OpenButton = new JButton();
  protected JButton RecButton = new JButton();
  protected JToolBar LogToolbar = new JToolBar();
  protected JButton PlayButton = new JButton();
  protected JButton StopButton = new JButton();
  protected JButton LogPathButton = new JButton("Sfoglia...", openFileIcon);
  protected JLabel LogPathLabel = new JLabel();
  protected GridLayout gridLayout1 = new GridLayout();
  protected BorderLayout borderLayout1 = new BorderLayout();
  protected BorderLayout borderLayout2 = new BorderLayout();
  protected Window infoPanelWindow;
  protected static InfoPanel infoPanel;
  protected JButton ViewInfoPanelButton = new JButton();
  private boolean infoPanelOn = true;
  JButton writeAttributeButton = new JButton();
  JButton readAttributeButton = new JButton();

  public ApplicationGUI(InteractionManagementInterface _actions)
  {   actions = _actions;
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try
      {   jbInit();
      }
      catch(Exception e)
      {   e.printStackTrace();
      }
  }

  protected void jbInit() throws Exception
  { treeScrollPane = new JScrollPane();
    setIconImage( Toolkit.getDefaultToolkit().createImage(iconPath) );
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(900, 600));
    this.setTitle("Tesi di Laurea di Laurea in Ingegneria Informatica - Michele Berionni -");

    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    jMenuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });

    this.addComponentListener(new ComponentListener() {
      public void componentHidden(ComponentEvent e) {
        //jMenuFileExit_actionPerformed(e);
      }
      public void componentShown(ComponentEvent e) {
       // jMenuFileExit_actionPerformed(e);
      }
      public void componentMoved(ComponentEvent e) {
       resizeInfoPanel();
      }
      public void componentResized(ComponentEvent e) {
        resizeInfoPanel();
      }
    });
    jMenuOpenSession.setText("Open an old session");
    jMenuOpenSession.setIcon(openFileIcon);
    jMenuOpenSession.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileOpenSession_actionPerformed(e);
      }
    });
    jMenuSessionRec.setText("Session");
    jMenuOpenSession.setText("Open saved session");
    jMenuOpenSession.setIcon(openFileIcon);
    jMenuOpenSession.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
       OpenButton_actionPerformed(e);
      }
    });
    jMenuStartRec.setText("Begin recording");
    jMenuStartRec.setIcon(recIcon);
    jMenuStartRec.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        RecButton_actionPerformed(e);
      }
    });
    jMenuStopRec.setText("Stop recording");
    jMenuStopRec.setIcon(stopIcon);
    jMenuStopRec.setEnabled(false);
    jMenuStopRec.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
       StopButton_actionPerformed(e);
      }
    });
    jMenuPlay.setText("View current Session");
    jMenuPlay.setIcon(playIcon);
    jMenuPlay.setEnabled(false);
    jMenuPlay.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
       PlayButton_actionPerformed(e);
      }
    });

    jMenuView.setText("View");
    jMenuViewIREP.setText("Interface Repository");
    jMenuViewIREP.setIcon(irepIcon);
    jMenuViewIREP.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuViewIREP_actionPerformed(e);
      }
    });
    jMenuViewTXTOUTPUT.setText("Textual output");
    jMenuViewTXTOUTPUT.setIcon(null);
    jMenuViewTXTOUTPUT.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuViewTXTOUTPUT_actionPerformed(e);
      }
    });
    jMenuViewInfoPanel.setText("Open/Close Info Panel");
    jMenuViewInfoPanel.setIcon(null);
    jMenuViewInfoPanel.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        ViewInfoPanelButton_actionPerformed(null);
      }
    });
    jMenuViewDEBUG.setText("Debug window");
    jMenuViewDEBUG.setIcon(null);
    jMenuViewDEBUG.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuViewDEBUG_actionPerformed(e);
      }
    });
    jMenuViewNames.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuViewNames_actionPerformed();
      }
    });
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.setIcon(aboutIcon);
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jPanel1.setLayout(borderLayout2);
    treeTableScrollPane.getViewport().setBackground(Color.white);
    jSplitPane1.setBackground(Color.white);
    jSplitPane1.setOneTouchExpandable(true);
    jPanel2.setLayout(gridLayout1);
    ConnectButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ConnectButton_actionPerformed(e);
      }
    });
    ConnectButton.setToolTipText(ConnectButtonText);
    ConnectButton.setPreferredSize(PreferredButtonDimension);
    ConnectButton.setMinimumSize(MinimumButtonDimension);
    ConnectButton.setMaximumSize(MaximumButtonDimension);
    ConnectButton.setIcon(connectIcon);
        DisconnectButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DisconnectButton_actionPerformed(e);
      }
    });
    DisconnectButton.setToolTipText(DisconnectButtonText);
    DisconnectButton.setPreferredSize(PreferredButtonDimension);
    DisconnectButton.setMinimumSize(MinimumButtonDimension);
    DisconnectButton.setMaximumSize(MaximumButtonDimension);
    DisconnectButton.setIcon(disconnectIcon);
    DisconnectButton.setEnabled(false);
    MainToolbar.setMargin(new Insets(2, 5, 2, 5));
    OpenButton.setMaximumSize(MaximumButtonDimension);
    OpenButton.setMinimumSize(MinimumButtonDimension);
    OpenButton.setNextFocusableComponent(RecButton);
    OpenButton.setPreferredSize(PreferredButtonDimension);
    OpenButton.setToolTipText(OpenButtonText);
    OpenButton.setIcon(openFileIcon);
    OpenButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OpenButton_actionPerformed(e);
      }
    });ExecuteButton.setIcon(executeIcon);
    ExecuteButton.setEnabled(false);
    ExecuteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ExecuteButton_actionPerformed(e);
      }
    });
    ExecuteButton.setMaximumSize(MaximumButtonDimension);
    ExecuteButton.setMinimumSize(MinimumButtonDimension);
    ExecuteButton.setPreferredSize(PreferredButtonDimension);
    ExecuteButton.setToolTipText(ExecuteButtonText);
    RecButton.setIcon(recIcon);
    RecButton.setEnabled(true);
    RecButton.setToolTipText(RecButtonText);
    RecButton.setPreferredSize(PreferredButtonDimension);
    RecButton.setMinimumSize(MinimumButtonDimension);
    RecButton.setMaximumSize(MaximumButtonDimension);
    RecButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RecButton_actionPerformed(e);
      }
    });

    PlayButton.setIcon(playIcon);
    PlayButton.setEnabled(false);
    PlayButton.setToolTipText(PlayButtonText);
    PlayButton.setMaximumSize(MaximumButtonDimension);
    PlayButton.setMinimumSize(MinimumButtonDimension);
    PlayButton.setPreferredSize(PreferredButtonDimension);
    PlayButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        PlayButton_actionPerformed(e);
      }
    });
    StopButton.setIcon(stopIcon);
    StopButton.setEnabled(false);
    StopButton.setToolTipText(StopButtonText);
    StopButton.setMaximumSize(MaximumButtonDimension);
    StopButton.setMinimumSize(MinimumButtonDimension);
    StopButton.setPreferredSize(PreferredButtonDimension);
    StopButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StopButton_actionPerformed(e);
      }
    });
    LogPathLabel.setFont(f);
    setLogPathLabel(logHome);
    LogPathLabel.setMaximumSize(new Dimension(270, 40));
    LogPathLabel.setMinimumSize(new Dimension(100, 40));
    LogPathLabel.setPreferredSize(new Dimension(240, 40));
    LogPathButton.setToolTipText(LogPathButtonText);
    LogPathButton.setFont(f);
    LogPathButton.setMaximumSize(new Dimension(100, 30));
    LogPathButton.setMinimumSize(new Dimension(50, 20));
    LogPathButton.setPreferredSize(new Dimension(90, 25));
    LogPathButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LogPathButton_actionPerformed(e);
      }
    });
    jPanel2.setMinimumSize(new Dimension(458, 40));
    jPanel2.setPreferredSize(new Dimension(878, 40));
    ViewInfoPanelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ViewInfoPanelButton_actionPerformed(e);
      }
    });
    ViewInfoPanelButton.setIcon(infoPanelDeactivateIcon);
    ViewInfoPanelButton.setToolTipText("close info Panel");
    ViewInfoPanelButton.setPreferredSize(PreferredButtonDimension);
    ViewInfoPanelButton.setNextFocusableComponent(RecButton);
    ViewInfoPanelButton.setMinimumSize(MinimumButtonDimension);
    ViewInfoPanelButton.setMaximumSize(MaximumButtonDimension);
    writeAttributeButton.setEnabled(false);
    writeAttributeButton.setMaximumSize(MaximumButtonDimension);
    writeAttributeButton.setMinimumSize(MinimumButtonDimension);
    writeAttributeButton.setNextFocusableComponent(RecButton);
    writeAttributeButton.setPreferredSize(PreferredButtonDimension);
    writeAttributeButton.setToolTipText("set attribute value");
    writeAttributeButton.setIcon(writeAttributeIcon);
    writeAttributeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        writeAttributeButton_actionPerformed(e);
      }
    });
    readAttributeButton.setEnabled(false);
    readAttributeButton.setMaximumSize(MaximumButtonDimension);
    readAttributeButton.setMinimumSize(MinimumButtonDimension);
    readAttributeButton.setNextFocusableComponent(RecButton);
    readAttributeButton.setPreferredSize(PreferredButtonDimension);
    readAttributeButton.setToolTipText("read attribute value");
    readAttributeButton.setIcon(readAttributeIcon);
    readAttributeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        readAttributeButton_actionPerformed(e);
      }
    });
    jMenuFile.add(jMenuFileExit);
    jMenuSessionRec.add(jMenuOpenSession);
    jMenuSessionRec.addSeparator();
    jMenuSessionRec.add(jMenuStartRec);
    jMenuSessionRec.add(jMenuStopRec);
    jMenuSessionRec.add(jMenuPlay);
    jMenuView.add(jMenuViewIREP);
    jMenuView.add(jMenuViewDEBUG);
    jMenuView.add(jMenuViewTXTOUTPUT);
    jMenuView.add(jMenuViewInfoPanel);
    jMenuView.add(jMenuViewNames);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuSessionRec);
    jMenuBar1.add(jMenuView);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    treeScrollPane.getViewport().setBackground(Color.white);
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(treeScrollPane, JSplitPane.LEFT);
    jSplitPane1.add(treeTableScrollPane, JSplitPane.RIGHT);
    contentPane.add(jPanel2, BorderLayout.NORTH);
    jPanel2.add(MainToolbar, null);
    MainToolbar.add(ConnectButton);
    MainToolbar.add(DisconnectButton);
    MainToolbar.add(ExecuteButton);
    MainToolbar.add(readAttributeButton);
    MainToolbar.add(writeAttributeButton);
    MainToolbar.add(OpenButton);
    MainToolbar.add(ViewInfoPanelButton);

    LogToolbar.setMargin(new Insets(2, 5, 2, 5));
    jPanel2.add(LogToolbar, null);
    LogToolbar.add(RecButton, null);
    LogToolbar.add(StopButton, null);
    LogToolbar.add(PlayButton, null);
    LogToolbar.add(LogPathLabel, null);
    LogToolbar.add(LogPathButton, null);
    jSplitPane1.setDividerLocation(255);

    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    fc.setMultiSelectionEnabled(false);
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("txt");
    filter.setDescription("txt Files");
    fc.setFileFilter(filter);
  }

  public void setInfoPanelWindow(Window infoP)
  { infoPanelWindow = infoP;
    infoPanel = (InfoPanel)(infoPanelWindow.getComponents())[0];
  }

  protected void resizeInfoPanel()
  {   int width = this.getWidth();
      int height = this.getHeight();
      Point topLeft = this.getLocation();
      infoPanelWindow.setSize(width, 80);
      infoPanel.setSize(width, 80);
      infoPanel.updateUI();
      infoPanel.validate();
      infoPanelWindow.setLocation((int)topLeft.getX(), (int)topLeft.getY() + height);
  }

  protected void jMenuFileExit_actionPerformed(ActionEvent e)
  {   final JOptionPane optionPane = new JOptionPane("     Are you sure ?", JOptionPane.QUESTION_MESSAGE, JOptionPane.CANCEL_OPTION);
      dialog = new JDialog(this, "Exit", true);
      dialog.setContentPane(optionPane);
      dialog.setSize(235,110);
      Dimension dialogSize = dialog.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dialog.setLocation((frmSize.width - dialogSize.width) / 2 + loc.x, (frmSize.height - dialogSize.height) / 2 + loc.y);
      optionPane.addPropertyChangeListener(
      new PropertyChangeListener()
      {   public void propertyChange(PropertyChangeEvent p)
          {  String prop = p.getPropertyName();
             if (dialog.isVisible() && (p.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY) || prop.equals(JOptionPane.INPUT_VALUE_PROPERTY)))
              {   int value = new Integer(p.getNewValue().toString()).intValue();
                  if (value == 0)
                  {   System.exit(0);
                  }
                  else
                  {   dialog.dispose();
                  }
              }
          }
      });
      dialog.setVisible(true);

  }

  protected void jMenuHelpAbout_actionPerformed(ActionEvent e)
  { AboutBox dlg = new AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  protected void jMenuFileOpenSession_actionPerformed(ActionEvent e)
  {   actions.getLogWindow().openSavedSession();
  }

  protected void jMenuViewDEBUG_actionPerformed(ActionEvent e)
  {   actions.getDebugWindow().setVisible(true);
  }

  protected void jMenuViewIREP_actionPerformed(ActionEvent e)
  {   if (IREPWindow.alreadyExists)
      {   actions.getRepositoryWindow().setVisible(true);
      }
      else
      {   actions.updateRepository();
      }
  }

  protected void jMenuViewTXTOUTPUT_actionPerformed(ActionEvent e)
  {   actions.getTxtWindow().setVisible(true);
  }

  protected void jMenuViewNames_actionPerformed()
  {   if (jMenuViewNames.isSelected())
      {   actions.getIDLBrowser().viewAbsoluteNames(true);
      }
      else
      {   actions.getIDLBrowser().viewAbsoluteNames(false);
      }
  }

  protected void processWindowEvent(WindowEvent e)
  {   super.processWindowEvent(e);
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {   jMenuFileExit_actionPerformed(null);
      }
  }

  protected void ExecuteButton_actionPerformed(ActionEvent e)
  {   actions.esegui("");
  }

  protected void ConnectButton_actionPerformed(ActionEvent e)
  {   ConnectToServer cts = new ConnectToServer(Client.getOrb());
      setGUI(cts);
      cts.setReturnClass(this);
  }

  public void startSession(org.omg.CORBA.Object object)
  {     emsSF = new emsSessionFactoryClient(actions);
        emsSF.request(null, object, "");
  }

  protected void DisconnectButton_actionPerformed(ActionEvent e)
  {   // ancora non implementato
      // showMessageDialog("still unimplemented", ERROR_MESSAGE);
      ConnectButton.setEnabled(true);
      DisconnectButton.setEnabled(false);

  }

  protected void RecButton_actionPerformed(ActionEvent e)
  {   try
      {   String logFileName = actions.getSessionRecorder().initRecordingSession(logHome);
          actions.getLogWindow().beginSession(logFileName);
          actions.recEnable();
          LogPathButton.setEnabled(false);
          RecButton.setEnabled(false);
          RecButton.setToolTipText(RecButtonText2);
          jMenuStartRec.setEnabled(false);
          PlayButton.setEnabled(true);
          jMenuPlay.setEnabled(true);
          StopButton.setEnabled(true);
          jMenuStopRec.setEnabled(true);
      }   catch(Exception ex) {   showMessageDialog("Errore accesso file di LOG", ERROR_MESSAGE );
                                  ex.printStackTrace();
                              }
  }

  protected void StopButton_actionPerformed(ActionEvent e)
  {   actions.recDisable();
      actions.getLogWindow().setStatusBarText("status: VIEW MODE");
      RecButton.setEnabled(true);
      jMenuStartRec.setEnabled(true);
      LogPathButton.setEnabled(true);
      StopButton.setEnabled(false);
      jMenuStopRec.setEnabled(false);
      PlayButton.setEnabled(false);
      jMenuPlay.setEnabled(false);
  }

  protected void PlayButton_actionPerformed(ActionEvent e)
  {   MyLogFrameInterface logW = actions.getLogWindow();
      logW.setVisible(true);
      logW.setStatusBarText("status: REC MODE");
      //actions.lG.setVisible(true);
  }

  protected void OpenButton_actionPerformed(ActionEvent e)
  {   actions.getLogWindow().openSavedSession();

  }

  protected void LogPathButton_actionPerformed(ActionEvent e)
  {   int returnVal = fc.showDialog(this, "ok");
      fc.setDialogTitle("choose or create log file to use");
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {    File file = fc.getSelectedFile();
           String path = file.getAbsolutePath();
           println("path selected = " + path);
           if ( !file.isDirectory() && path.indexOf(".") < 0 )
           {  path = path + ".txt";  // aggiungo automaticamente l'estensione se l'utente non la mette
           }
           setLogPathLabel(path);

      }    else {   System.out.println("snif! command cancelled by user.");
                }
  }

  protected void setLogPathLabel(String text)
  {   logHome = text;
      LogPathLabel.setText("    " + text +  "    ");
      LogPathLabel.setToolTipText(LogPathLabel.getText());
  }

  public static void setRequestTime(String text)
  {   infoPanel.timeTextField1.setText(text);
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

  public void setStatusBarText(String text)
  {   infoPanel.setjLabel2Text(text);
  }

  protected void ViewInfoPanelButton_actionPerformed(ActionEvent e)
  {   if (infoPanelOn)
      {   infoPanelOn = false;
          infoPanelWindow.setVisible(false);
          ViewInfoPanelButton.setIcon(infoPanelActivateIcon);
          ViewInfoPanelButton.setToolTipText("open info panel");
      }
      else
      {   infoPanelOn = true;
          infoPanelWindow.setVisible(true);
          ViewInfoPanelButton.setIcon(infoPanelDeactivateIcon);
          ViewInfoPanelButton.setToolTipText("close info panel");
      }
  }

  protected void writeAttributeButton_actionPerformed(ActionEvent e)
  {   actions.esegui("write");
  }

  protected void readAttributeButton_actionPerformed(ActionEvent e)
  {   actions.esegui("read");
  }
}