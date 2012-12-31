package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.FileReader;
import PrintPackage.PFPrintFile;
import PrintPackage.PrintMonitor;
import PrintPackage.PrintPreviewer;

public class LogTextWindow extends TextWindow implements TextFrameInterface
{ protected ImageIcon printIcon = ConfigDataAccessClient.printIcon;
  protected ImageIcon printPreviewIcon = ConfigDataAccessClient.printPreviewIcon;
  protected ImageIcon pageSetupIcon = ConfigDataAccessClient.pageSetupIcon;
  protected Dimension PreferredButtonDimension = ConfigDataAccessClient.PreferredButtonDimension;
  protected Dimension MaximumButtonDimension = new Dimension(35,35);
  protected JButton PrintButton = new JButton(printIcon);
  protected JButton PrintPreviewButton = new JButton(printPreviewIcon);
  protected JButton PageSetupButton = new JButton(pageSetupIcon);
  protected JToolBar toolBar = new JToolBar();
  protected boolean documentCreated = false;
  protected String currentFile;
  protected PFPrintFile file = null;

  public LogTextWindow()
  { super();
    PrintPreviewButton.setMaximumSize(PreferredButtonDimension);
    PrintPreviewButton.setPreferredSize(PreferredButtonDimension);
    PrintPreviewButton.setToolTipText("Print preview");
    toolBar.add(PrintPreviewButton);
    PrintButton.setMaximumSize(PreferredButtonDimension);
    PrintButton.setPreferredSize(PreferredButtonDimension);
    PrintButton.setToolTipText("Print");
    toolBar.add(PrintButton);

    toolBar.setMargin(new Insets(2, 5, 2, 5));
    PrintPreviewButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onPrintPreview();
      }
    });
    PrintButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onPrint();
      }
    });
    BorderLayout borderLayout1 = new BorderLayout();
    Container contentPane = this.getContentPane();
    contentPane.setLayout(borderLayout1);
    contentPane.add(toolBar, borderLayout1.NORTH);
    contentPane.add(jScrollPane1, borderLayout1.CENTER);

  }

  public void viewFile(String file) throws java.io.IOException
  {   int ret = 0;
      String ris = "";
      currentFile = file;
      documentCreated = false;
      FileReader fr = new FileReader(file);
      while (ret != -1 && ris.length() != 12)
      {    ret = fr.read();
           ris = ris + (char)ret;
      }
      this.setTitle(ris + " - file name: " + currentFile + " -");
      ris = "";
      while (ret != -1)
      {    ret = fr.read();
           ris = ris + (char)ret;
      }
      setText(ris);
  }

  protected void onPrint()
  {   Thread t = new Thread(new Runnable()
      {   public void run()
          {   try
              {   file = new PFPrintFile(currentFile);
                  PrintMonitor pm = new PrintMonitor(file.getPageable());
                  pm.performPrint(true);
              }   catch (Exception e)   {   showMessageDialog(e.toString() + " nell'accesso al file " + currentFile, ERROR_MESSAGE);
                                         }
          }
       });
       t.start();
   }

  protected void onPrintPreview()
  {   Thread t = new Thread(new Runnable()
      {   public void run()
          {   try
              {   file = new PFPrintFile(currentFile);
                  PrintPreviewer pp = new PrintPreviewer(file.getPageable(), 0);
                  JDialog dlg = new JDialog(LogTextWindow.this,"Print Preview: " + currentFile);
                  dlg.getContentPane().add(pp);
                  dlg.setSize(500, 600);
                  Point location = getLocation(dlg.getSize());
                  dlg.setLocation(location);
                  dlg.setVisible(true);

              }   catch (java.io.IOException ex) {  showMessageDialog(ex.toString() + " nell'accesso al file " + currentFile, ERROR_MESSAGE);
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

    public String getCurrentFile()
    {   return currentFile;
    }

}