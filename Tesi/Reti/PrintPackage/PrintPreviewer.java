package PrintPackage;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.Pageable;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import Reti.ConfigDataAccessClient;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class PrintPreviewer extends JPanel
{
  protected Pageable pageable;
  protected PrintComponent printComponent;
  protected int pageIndex;
  protected JScrollPane scrollPane;
  protected JButton previousButton, nextButton, firstPageButton, lastPageButton, zoomInButton, zoomOutButton, sizeButton, printButton;
  protected JTextField scaleText;
  protected ImageIcon printIcon = ConfigDataAccessClient.printIcon;
  protected ImageIcon zoomInIcon = ConfigDataAccessClient.zoomInIcon;
  protected ImageIcon zoomOutIcon = ConfigDataAccessClient.zoomOutIcon;
  protected ImageIcon firstPageIcon = ConfigDataAccessClient.firstPageIcon;
  protected ImageIcon lastPageIcon = ConfigDataAccessClient.lastPageIcon;
  protected ImageIcon nextPageIcon = ConfigDataAccessClient.nextPageIcon;
  protected ImageIcon previousPageIcon = ConfigDataAccessClient.previousPageIcon;
  protected Dimension PreferredButtonDimension = ConfigDataAccessClient.PreferredButtonDimension;
  protected Dimension MaximumButtonDimension = new Dimension(35,35);
  protected Font buttonFont = ConfigDataAccessClient.f;
  protected int zoomFactor = 20;
  protected String lastDate;
  protected SimpleDateFormat formatter;
  protected Date currentDate;

  public PrintPreviewer(Pageable p, int page)
  {  pageable = p;
     pageIndex = page;
     printComponent = new PrintComponent(null, null);
     printComponent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
     buildLayout();
     displayPage(pageIndex);
  }

  public void setZoomFactor(int _zoomFactor)
  {  zoomFactor = 20;
  }

  /**
   * Adds the appropriate components to this panel
   */
  protected void buildLayout()
  {
    setLayout(new BorderLayout());
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panel.add(printComponent);
    scrollPane = new JScrollPane(panel);
    add(scrollPane, BorderLayout.CENTER);
    add(getToolBarPanel(), BorderLayout.NORTH);
    addListeners();
  }

  /**
   * Returns a panel that contains the buttons supported by this interface
   */
  protected JPanel getToolBarPanel()
  { JPanel toolBarPanel = new JPanel(new GridLayout());

    JToolBar toolBar = new JToolBar();
    toolBar.setMargin(new Insets(2, 5, 2, 5));
    firstPageButton = new JButton(firstPageIcon);
    firstPageButton.setMaximumSize(MaximumButtonDimension);
    firstPageButton.setPreferredSize(PreferredButtonDimension);
    firstPageButton.setToolTipText("skip to first page");
    toolBar.add(firstPageButton);

    previousButton = new JButton(previousPageIcon);
    previousButton.setMaximumSize(MaximumButtonDimension);
    previousButton.setPreferredSize(PreferredButtonDimension);
    previousButton.setToolTipText("view previous page");
    toolBar.add(previousButton);

    nextButton = new JButton(nextPageIcon);
    nextButton.setMaximumSize(MaximumButtonDimension);
    nextButton.setPreferredSize(PreferredButtonDimension);
    nextButton.setToolTipText("view next page");
    toolBar.add(nextButton);

    lastPageButton = new JButton(lastPageIcon);
    lastPageButton.setMaximumSize(MaximumButtonDimension);
    lastPageButton.setPreferredSize(PreferredButtonDimension);
    lastPageButton.setToolTipText("skip to last page");
    toolBar.add(lastPageButton);

    JToolBar sizeToolBar = new JToolBar();
    sizeToolBar.setMargin(new Insets(2, 5, 2, 5));
    zoomInButton = new JButton(zoomInIcon);
    zoomInButton.setMaximumSize(MaximumButtonDimension);
    zoomInButton.setPreferredSize(PreferredButtonDimension);
    zoomInButton.setToolTipText("zoom in");
    sizeToolBar.add(zoomInButton);

    zoomOutButton = new JButton(zoomOutIcon);
    zoomOutButton.setMaximumSize(MaximumButtonDimension);
    zoomOutButton.setPreferredSize(PreferredButtonDimension);
    zoomOutButton.setToolTipText("zoom out");
    sizeToolBar.add(zoomOutButton);

    JPanel s = new JPanel(new BorderLayout());
    scaleText = new JTextField(3);
    scaleText.setMaximumSize(new Dimension(100, 30));
    scaleText.setPreferredSize(new Dimension(100, 30));
    s.add(scaleText, BorderLayout.CENTER);
    s.setSize(new Dimension(100, 30));
    sizeToolBar.add(s);

    sizeButton = new JButton("Size To Fit");
    sizeButton.setFont(buttonFont);
    sizeButton.setMaximumSize(new Dimension(100,30));
    sizeButton.setPreferredSize(new Dimension(80,30));
    sizeToolBar.add(sizeButton);

    printButton = new JButton(printIcon);
    printButton.setMaximumSize(MaximumButtonDimension);
    printButton.setPreferredSize(PreferredButtonDimension);
    printButton.setMargin(new Insets(0, 10, 0, 10));
    sizeToolBar.add(printButton);

    toolBarPanel.add(toolBar);
    toolBarPanel.add(sizeToolBar);

    return toolBarPanel;
  }

  /**
   * Adds listeners to the buttons and the text field
   */
  protected void addListeners() {
    previousButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(pageIndex - 1);
      }
    });
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(pageIndex + 1);
      }
    });
    firstPageButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(0);
      }
    });
    lastPageButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(pageable.getNumberOfPages() - 1);
      }
    });
    zoomInButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
         try { double scaleFactor = printComponent.getScaleFactor();

          printComponent.setScaleFactor(scaleFactor + zoomFactor);
        } catch (NumberFormatException nfe) {};
      }
    });
    zoomOutButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
         try { double scaleFactor = printComponent.getScaleFactor();

          printComponent.setScaleFactor(scaleFactor - zoomFactor);
        } catch (NumberFormatException nfe) {};
      }
    });
    sizeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        sizeToFit();
      }
    });
    printButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        print();
      }
    });
    scaleText.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try {
          int scale = Integer.parseInt(
              scaleText.getText());
          printComponent.setScaleFactor(scale);
        } catch (NumberFormatException nfe) {};
      }
    });
  }

  /**
   * Displays the specified page within the panel
   */
  protected void displayPage(int index)
  { pageIndex = index;
    printComponent.setPrintable(pageable.getPrintable(pageIndex));
    printComponent.setPageFormat(pageable.getPageFormat(pageIndex));
    printComponent.setDisplayPage(index);
    previousButton.setEnabled(pageIndex > 0);
    firstPageButton.setEnabled(pageIndex > 0);
    nextButton.setEnabled(pageIndex < (pageable.getNumberOfPages() - 1));
    lastPageButton.setEnabled(pageIndex < (pageable.getNumberOfPages() - 1));
    repaint();
  }

  /**
   * Determine the largest scale factor that can be used that will
   * allow the current page to be displayed completely. In other words,
   * make the page as large as possible without making it necessary for
   * the user to use scroll bars to view the entire page. This is
   * accomplished by calculating the ratios that represent the actual
   * width of the page relative to the available width, and the actual
   * height of the page to the available height. The smaller of the two
   * values will dictate how large the ratio can be set while still
   * allowing the entire page to be displayed.
   */
  protected void sizeToFit()
  { int newScaleFactor;
    Dimension compSize = printComponent.getSizeWithScale(100d);
    Dimension viewSize = scrollPane.getSize();

    int scaleX = (viewSize.width - 25) * 100 / compSize.width;
    int scaleY = (viewSize.height - 25) * 100 / compSize.height;
    newScaleFactor = Math.min(scaleX, scaleY);

    printComponent.setScaleFactor(newScaleFactor);
    scaleText.setText(Integer.toString(newScaleFactor));
    repaint();
  }

  protected void print()
  {   Thread t = new Thread(new Runnable()
      {   public void run()
          { try
              {   if (pageable instanceof TablePrinter)
                  {   ((TablePrinter)pageable).useDefaultHeader();
                  }
                  PrintMonitor pm = new PrintMonitor(pageable);
                  pm.performPrint(true);

              } catch (Exception e) {   e.printStackTrace();
                                    }
           }
     });
     t.start();
  }

}
