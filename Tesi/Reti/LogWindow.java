package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.io.File;
import java.io.FileReader;
import java.awt.*;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;
import TreeTablePackage.JTreeTable;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import PrintPackage.TablePrinter;
import PrintPackage.PrintMonitor;
import PrintPackage.PrintPreviewer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogWindow extends MyTreeTableFrame implements MyLogFrameInterface
{ protected JPanel contentPane;
  protected JPanel mainPanel = new JPanel();
  protected JToolBar toolBar = new JToolBar();
  protected JMenuBar menuBar = new JMenuBar();
  protected BorderLayout borderLayout4 = new BorderLayout();

  protected Font f = ConfigDataAccessClient.f;
  protected ImageIcon openIcon = ConfigDataAccessClient.openFileIcon;
  protected ImageIcon printIcon = ConfigDataAccessClient.printIcon;
  protected ImageIcon txtLogIcon = ConfigDataAccessClient.txtLogIcon;
  protected ImageIcon printPreviewIcon = ConfigDataAccessClient.printPreviewIcon;
  protected ImageIcon pageSetupIcon = ConfigDataAccessClient.pageSetupIcon;
  protected Dimension PreferredButtonDimension = ConfigDataAccessClient.PreferredButtonDimension;
  protected Dimension MinimumButtonDimension = new Dimension(25,25);
  protected Dimension MaximumButtonDimension = new Dimension(35,35);

  protected String logHome = ConfigDataAccessClient.logHome;
  protected final JFileChooser fc = new JFileChooser(logHome);
  protected JButton OpenButton = new JButton();
  protected JButton PrintButton = new JButton();
  protected JButton TxtLogButton = new JButton();
  protected BorderLayout borderLayout1 = new BorderLayout();
  protected JPanel CommandPanel = new JPanel();
  protected BorderLayout borderLayout2 = new BorderLayout();
  protected String lastDate;
  protected SimpleDateFormat formatter;
  protected Date currentDate;
  protected String currentFile;
  protected TextFrameInterface textWin;
  protected PageFormat pageFormat;

  public LogWindow()
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {   jbInit("");
          }
      catch(Exception e)
      {  e.printStackTrace();
      }
  }

  public LogWindow(String title)
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {   jbInit(title);
          }
      catch(Exception e)
      {  e.printStackTrace();
      }
  }

  private void jbInit(String title) throws Exception
  { pageFormat = new PageFormat();
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout2);
    this.setSize(new Dimension(500, 400));
    this.setTitle(title);
    mainPanel.setLayout(borderLayout1);
    CommandPanel.setLayout(borderLayout4);
    OpenButton.setIcon(openIcon);
    OpenButton.setMaximumSize(MaximumButtonDimension);
    OpenButton.setMinimumSize(MinimumButtonDimension);
    OpenButton.setPreferredSize(PreferredButtonDimension);
    OpenButton.setToolTipText("Open an old session");
    OpenButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OpenButton_actionPerformed(e);
      }
    });
    PrintButton.setIcon(printIcon);
    PrintButton.setMaximumSize(MaximumButtonDimension);
    PrintButton.setMinimumSize(MinimumButtonDimension);
    PrintButton.setPreferredSize(PreferredButtonDimension);
    PrintButton.setToolTipText("Print session displayed");
    PrintButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        PrintButton_actionPerformed(e);
      }
    });
    TxtLogButton.setIcon(txtLogIcon);
    TxtLogButton.setMaximumSize(MaximumButtonDimension);
    TxtLogButton.setMinimumSize(MinimumButtonDimension);
    TxtLogButton.setPreferredSize(PreferredButtonDimension);
    TxtLogButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        TxtLogButton_actionPerformed(e);
      }
    });

    JMenu menu = new JMenu("File");
    menu.add(new AbstractAction("Open", openIcon) {
      public void actionPerformed(ActionEvent event) {
        openSavedSession();
      }
    });
    menu.add(new AbstractAction("Page Setup", pageSetupIcon) {
      public void actionPerformed(ActionEvent event) {
        onPageSetup();
      }
    });
    menu.add(new AbstractAction("Print Preview", printPreviewIcon) {
      public void actionPerformed(ActionEvent event) {
        onPrintPreview();
      }
    });
    menu.add(new AbstractAction("Print", printIcon) {
      public void actionPerformed(ActionEvent event) {
        onPrint();
      }
    });
    menu.addSeparator();
    menu.add(new AbstractAction("Exit") {
      public void actionPerformed(ActionEvent event) {
        processWindowEvent(new WindowEvent(LogWindow.this, WindowEvent.WINDOW_CLOSING));

      }
    });
    menuBar.add(menu);
    JMenu view = new JMenu("View");
    view.add(new AbstractAction("View textual version") {
      public void actionPerformed(ActionEvent event) {
        TxtLogButton_actionPerformed(null);
      }
    });
    menuBar.add(view);

    statusBar.setText("");
    statusBar.setHorizontalTextPosition(SwingConstants.CENTER);
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar.setMaximumSize(new Dimension(300, 25));
    statusBar.setMinimumSize(new Dimension(300, 1));
    statusBar.setPreferredSize(new Dimension(100, 23));
    statusBar.setFont(f);

    toolBar.add(OpenButton, null);
    toolBar.add(PrintButton, null);
    toolBar.add(TxtLogButton, null);
    toolBar.setMargin(new Insets(2, 5, 2, 5));

    CommandPanel.add(menuBar, BorderLayout.NORTH);
    CommandPanel.add(toolBar, BorderLayout.SOUTH);
    treeTableScrollPane.getViewport().setBackground(Color.white);
    mainPanel.add(treeTableScrollPane, BorderLayout.CENTER);
    mainPanel.add(CommandPanel, BorderLayout.NORTH);
    contentPane.add(mainPanel, BorderLayout.CENTER);
    contentPane.add(statusBar, BorderLayout.SOUTH);

    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setMultiSelectionEnabled(false);
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("ser");
    filter.setDescription("ser Files");
    fc.setFileFilter(filter);

  }

  protected void processWindowEvent(WindowEvent e)
  { super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {
    }
  }

  public void setTextWindow(TextFrameInterface _textWin)
  {   textWin = _textWin;
  }

  public void beginSession(String logFileName)
  {   currentFile = logFileName;
      formatter = new SimpleDateFormat (" dd MMM yyyy ", Locale.getDefault());
      currentDate = new Date();
      lastDate = formatter.format(currentDate);
      treeTableScrollPane.getViewport().removeAll();
      String fileName = currentFile.substring(0, currentFile.length() - 4) + ".ser";
      setTitle(lastDate + "- file name: " + fileName + " -");
      treeTable = null;
      println("logFileName " + fileName);

  }

  public void addCurrentAction(String request, String response, DefaultMutableTreeNode requestNode, DefaultMutableTreeNode responseNode)
  {  if (textWin.isVisible() && currentFile.compareTo(textWin.getCurrentFile()) == 0)
     {  textWin.println(request + "\n" + response + "\n\n");
     }
     JTreeTable requestTreeTable = CreateCustomTreeTable.createTreeTable(this, requestNode);
     addTreeTable(requestTreeTable);
     if (responseNode != null)
     {  JTreeTable responseTreeTable = CreateCustomTreeTable.createTreeTable(this, responseNode);
        addTreeTable(responseTreeTable);
     }

  }

  public void addTreeTable(JTreeTable _treeTable)
  {   //println("dentro logW addtreeTable");
      if (treeTable == null)
      {   //println("treeTable null");
          DefaultMutableTreeNode oldTree = (DefaultMutableTreeNode)_treeTable.getTree().getModel().getRoot();
          oldTree = clone(oldTree);
          DefaultMutableTreeNode root = new DefaultMutableTreeNode("root", true);
          root.add(oldTree);
          treeTable = CreateCustomTreeTable.createTreeTable(this, root);
          treeTable.getColumnModel().getColumn(1).setCellEditor(new MyTableEditorNonEditable());
          super.addTreeTable(treeTable);
      }
      else
      {   //println("treeTable non null");
          DefaultMutableTreeNode oldTree = (DefaultMutableTreeNode)treeTable.getTree().getModel().getRoot();
          DefaultMutableTreeNode newTree = (DefaultMutableTreeNode)_treeTable.getTree().getModel().getRoot();
          oldTree = clone(oldTree);
          oldTree.add(newTree);
          treeTable = CreateCustomTreeTable.createTreeTable(this, oldTree);
          treeTable.getColumnModel().getColumn(1).setCellEditor(new MyTableEditorNonEditable());
          super.addTreeTable(treeTable);
      }
  }

   protected DefaultMutableTreeNode clone(DefaultMutableTreeNode node)
    {   DefaultMutableTreeNode clone;
        if (node == null)  { return null;
                           };
        Object ogg = node.getUserObject();
        if (ogg instanceof OggettoNodo)
        {   OggettoNodo ogg1 = (OggettoNodo)ogg;
            OggettoNodo nuovoOgg = new OggettoNodo(ogg1.getOggettoIR(), ogg1.getDescription(), ogg1.getName(), ogg1.getSignature(), ogg1.getIOR());
            nuovoOgg.setValue(ogg1.getValue());
            clone =  new DefaultMutableTreeNode(nuovoOgg, true);
        }
        else
        {    //println("nodo senza oggetto "+node.toString());
             clone =  new DefaultMutableTreeNode(node, true);
        }

        for (int len = node.getChildCount(), i = 0; i < len; i++)
        {    clone.add(clone((DefaultMutableTreeNode)node.getChildAt(i))); // recursive call
        }

        return clone;

  }

  protected void PrintButton_actionPerformed(ActionEvent e)
  {   onPrint();
  }

  protected void TxtLogButton_actionPerformed(ActionEvent e)
  {   //textWin.setTitle(currentFile);
      try
      {  textWin.viewFile(currentFile);
         Point location = getLocation(textWin.getSize());
         textWin.setLocation(location);
         textWin.setVisible(true);
      }  catch (java.io.IOException ex)    {   showMessageDialog(ex.toString() + " nell'accesso al file " + currentFile, ERROR_MESSAGE);
                                               ex.printStackTrace();
                                          }
  }

  protected void OpenButton_actionPerformed(ActionEvent e)
  {   openSavedSession();
  }

  public void openSavedSession()
  {   int ret = 0;
      String ris = "";
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {   File file = fc.getSelectedFile();
          treeTable = null;
          treeTableScrollPane.getViewport().removeAll();
          try
          {   String path = file.getPath();
              currentFile = path.substring(0, path.length() - 4) + ".txt";
              FileReader fr = new FileReader(currentFile);
              while (ret != -1 && ris.length() != 12)
              {    ret = fr.read();
                   ris = ris + (char)ret;
              }
              this.setTitle(ris + "- file name: " + path + " -");
              fr.close();
              DefaultMutableTreeNode node = actions.getSessionRecorder().getRecordedSession(file);
              treeTable = CreateCustomTreeTable.createTreeTable(this, node);
              this.setVisible(true);
              super.addTreeTable(treeTable);
              setStatusBarText("status: VIEW MODE");
          }   catch (Exception e) {   e.printStackTrace();
                                      showMessageDialog("ERRORE nell'accesso al file " + file.getName() + " " + e.toString(), ERROR_MESSAGE);
                                  }
      }   else {   System.out.println("Open command cancelled by user.");
               }
  }

   protected void onPageSetup()
   {  Thread t = new Thread(new Runnable()
      {   public void run()
          {   PrinterJob pj = PrinterJob.getPrinterJob();
              pageFormat = pj.pageDialog(pageFormat);
          }
      });
      t.start();
   }

  protected void onPrintPreview()
  {   Thread t = new Thread(new Runnable()
      {   public void run()
          {   String fileName = currentFile.substring(0, currentFile.length() - 4) + ".ser";
              TablePrinter tp = new TablePrinter("Printing File: " + fileName, treeTable, pageFormat);
              tp.useDefaultHeader();
              PrintPreviewer pp = new PrintPreviewer(tp, 0);
              JDialog dlg = new JDialog(LogWindow.this,"Print Preview: " + fileName);
              dlg.getContentPane().add(pp);
              dlg.setSize(500, 600);
              Point location = getLocation(dlg.getSize());
              dlg.setLocation(location);
              dlg.setVisible(true);
          }
      });
      t.start();
  }

  protected void onPrint()
  {   Thread t = new Thread(new Runnable()
      {   public void run()
          {   String fileName = currentFile.substring(0, currentFile.length() - 4) + ".ser";
              TablePrinter tp = new TablePrinter("Printing file: " + fileName, treeTable, pageFormat);
              tp.useDefaultHeader();
              tp.setHeader(fileName + " - " + lastDate + " - ");
              PrintMonitor pm = new PrintMonitor(tp);
              try {   pm.performPrint(true);
                  } catch (PrinterException pe) {   showMessageDialog("Printing error:" + pe.getMessage(), ERROR_MESSAGE);
                                                }
          }
       });
       t.start();
  }

   protected Point getLocation(Dimension newWindowDimension)
   {    Point ret;
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int XScreenSize = ScreenSize.width;
        Point TopLeftLocation = this.getLocation();
        Dimension Size = this.getSize();
        int YPos =  TopLeftLocation.y;
        int XPos =  TopLeftLocation.x + Size.width;
        int XRemainsRight = XScreenSize - XPos;
        int XRemainsLeft = TopLeftLocation.x;
        if (XRemainsRight > XRemainsLeft)
        {   ret = new Point(XPos, YPos);
        }
        else
        {   ret = new Point(TopLeftLocation.x - newWindowDimension.width, YPos);
        }
        return ret;
    }

}