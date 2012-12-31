package Reti;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IREPWindow extends MyFrame implements MyTreeFrameInterface
{ protected JPanel contentPane;
  protected JLabel statusBar = new JLabel();
  protected JScrollPane jScrollPane1 = new JScrollPane();
  protected JToolBar jToolBar1 = new JToolBar();
  protected JButton UpdateButton = new JButton();
  protected JButton PrintButton = new JButton();
  protected JButton PrintPreviewButton = new JButton();
  protected BorderLayout borderLayout1 = new BorderLayout();
  protected static boolean alreadyExists = false;
  protected ImageIcon updateIcon = ConfigDataAccessClient.updateIcon;
  protected ImageIcon printIcon = ConfigDataAccessClient.printIcon;
  protected ImageIcon printPreviewIcon = ConfigDataAccessClient.printPreviewIcon;
  protected Font statusBarFont = ConfigDataAccessClient.f;
  protected Dimension PreferredButtonDimension = ConfigDataAccessClient.PreferredButtonDimension;
  protected String UpdateButtonText = ConfigDataAccessClient.UpdateButtonText;
  protected Dimension MinimumButtonDimension = new Dimension(25,25);
  protected Dimension MaximumButtonDimension = new Dimension(35,35);
  protected SimpleDateFormat formatter;
  protected Date currentDate;
  protected String lastDate;

  public IREPWindow()
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {   jbInit();
          }
      catch(Exception e)
      {   e.printStackTrace();
      }
  }

  private void jbInit() throws Exception
  { //setIconImage(Toolkit.getDefaultToolkit().createImage(IREPWindow.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(600, 520));
    this.setTitle("Interface Repository view");
    statusBar.setFont(statusBarFont);
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar.setText(" ");
    UpdateButton.setIcon(updateIcon);
    PrintButton.setIcon(printIcon);
    PrintPreviewButton.setIcon(printPreviewIcon);
    UpdateButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        UpdateButton_actionPerformed(e);
      }
    });
    UpdateButton.setMaximumSize(MaximumButtonDimension);
    UpdateButton.setMinimumSize(MinimumButtonDimension);
    UpdateButton.setPreferredSize(PreferredButtonDimension);
    PrintButton.setMaximumSize(MaximumButtonDimension);
    PrintButton.setMinimumSize(MinimumButtonDimension);
    PrintButton.setPreferredSize(PreferredButtonDimension);
    PrintPreviewButton.setMaximumSize(MaximumButtonDimension);
    PrintPreviewButton.setMinimumSize(MinimumButtonDimension);
    PrintPreviewButton.setPreferredSize(PreferredButtonDimension);
    UpdateButton.setToolTipText(UpdateButtonText);
    jToolBar1.setMargin(new Insets(2, 5, 2, 5));
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jScrollPane1, BorderLayout.CENTER);
    contentPane.add(jToolBar1, BorderLayout.NORTH);
    jToolBar1.add(UpdateButton, null);
    jToolBar1.add(PrintButton, null);
    jToolBar1.add(PrintPreviewButton, null);
    updateTime();
  }

  public void setTree(JTree tree)
  {   tree.setName("IREPWindowTree");
      alreadyExists = true;
      JViewport viewport = jScrollPane1.getViewport();
      viewport.removeAll();
      viewport.add(tree, null);
      viewport.updateUI();
  }

  protected void processWindowEvent(WindowEvent e)
  { super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    { //System.exit(0);
    }
  }

  void UpdateButton_actionPerformed(ActionEvent e)
  {   updateTime();
      actions.updateRepository();
  }

  void updateTime()
  {   formatter = new SimpleDateFormat ("HH:mm:ss", Locale.getDefault());
      currentDate = new Date();
      lastDate = formatter.format(currentDate);
      statusBar.setText("Last updated: " + lastDate);
  }

   public void setStatusBarText(String text)
  {   statusBar.setText(text);
  }

}