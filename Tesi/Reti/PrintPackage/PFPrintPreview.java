package PrintPackage;


/**
 * Class: PFPrintPreview <p>
 *
 * Provide a print preview window for the print framework.
 * This class will take a <code>PFDocument</code> object
 * and display all the pages in the document on screen.<p>
 *
 * Here is a list of the functionnality offer to the user:
 *
 * <ul>
 * <li>Navigation between pages, first, previous, next and last page</li>
 * <li>Zoom-in/out, zoom the page in and out (NOT IMPLEMENTED YET)</li>
 * <li>Print the document directly with the print button</li>
 * </ul>
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import Reti.ConfigDataAccessClient;

public class PFPrintPreview extends JFrame
{  protected ImageIcon printIcon = ConfigDataAccessClient.printIcon;
   protected ImageIcon zoomInIcon = ConfigDataAccessClient.zoomInIcon;
   protected ImageIcon zoomOutIcon = ConfigDataAccessClient.zoomOutIcon;
   protected ImageIcon firstPageIcon = ConfigDataAccessClient.firstPageIcon;
   protected ImageIcon lastPageIcon = ConfigDataAccessClient.lastPageIcon;
   protected ImageIcon nextPageIcon = ConfigDataAccessClient.nextPageIcon;
   protected ImageIcon previousPageIcon = ConfigDataAccessClient.previousPageIcon;


   //--- Private instances declarations
   //----------------------------------

   private JPanel viewPanel = new JPanel ();
   private PagePanel pagePanel = new PagePanel ();

   private BorderLayout mainLayout = new BorderLayout ();
   private BorderLayout pageLayout = new BorderLayout ();

   private JScrollBar verticalScrollBar = new JScrollBar (JScrollBar.VERTICAL);
   private JScrollBar horizontalScrollBar = new JScrollBar (JScrollBar.HORIZONTAL);

   private PFPrintPreviewToolBar toolbar = new PFPrintPreviewToolBar (this);

   private Dimension preferredSize = new Dimension (500, 700);

   private PFPage currentPage = null;
   private PFDocument document = null;
   private int pageIndex = 0;
   private int zoom = 1;


   /**
    * Constructor: PFPrintPreview <p>
    *
    */
   public PFPrintPreview (PFDocument parDocument) {

      super ();

      document = parDocument;
      init ();

   }


   /**
    * Method: setDocument <p>
    *
    * Set the document to preview
    *
    * @param parDocument a value of type PFDocument
    */
   public void setDocument () {



   }


   /**
    * Method: zoom <p>
    *
    * Not implemented yet
    *
    * @param parZoomFactor a value of type int
    */
   public void setZoomIn (int parZoom) {

      zoom = parZoom;

   }


   /**
    * Method: setZoomOut <p>
    *
    * Not implemented yet
    *
    * @param parZoom a value of type int
    */
      public void setZoomOut (int parZoom) {

      zoom = parZoom;

   }


   /**
    * Method: getZoom <p>
    *
    * @return a value of type int
    */
      public int getZoom () {

      return (zoom);

   }


   /**
    * Method: nextPage <p>
    *
    * Preview the next page in the document
    *
    */
   public void nextPage () {

      pageIndex++;

      if (pageIndex > document.getPageCount () - 1)
         pageIndex--;

      renderPage ();

   }


   /**
    * Method: previousPage <p>
    *
    * Preview the previous page in the document
    *
    */
   public void previousPage () {

      pageIndex--;

      if (pageIndex < 0)
         pageIndex = 0;

      renderPage ();

   }


   /**
    * Method: firstPage <p>
    *
    * Preview the first page in the document
    *
    */
   public void firstPage () {

      pageIndex = 0;

      renderPage ();
   }


   /**
    * Method: lastPage <p>
    *
    * Preview the last page in the document
    *
    */
   public void lastPage () {

      pageIndex = document.getPageCount () - 1;

      renderPage ();
   }


   /**
    * Method: print <p>
    *
    * Print the document to the printer
    *
    */
   public void print () {

      document.print ();

   }


   /**
    * Method: renderPage <p>
    *
    * Ask the <code>pagePanel</code> to
    * render the current page
    *
    */
   private void renderPage () {

      pagePanel.renderPage (document.getPage (pageIndex));

   }


   /**
    * Method: getPreferredSize <p>
    *
    * @return a value of type Dimension
    */
   public Dimension getPreferredSize () {

      return (preferredSize);
   }


   /**
    * Method: init <p>
    *
    * Init this class and all the Swing controls
    *
    */
   private void init () {


      //--- Init this frame
      this.getContentPane ().setLayout (mainLayout);
      this.getContentPane ().add (toolbar, BorderLayout.NORTH);
      this.getContentPane ().add (pagePanel, BorderLayout.CENTER);
      this.getContentPane ().add (verticalScrollBar, BorderLayout.EAST);
      this.getContentPane ().add (horizontalScrollBar, BorderLayout.SOUTH);

      this.setTitle ("Print preview: " + document.getDocumentName ());
      this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
      this.pack ();
      this.show ();

      //--- Init the pagePanel
      pagePanel.setBackground (Color.white);
      pagePanel.setBorder (BorderFactory.createLineBorder (Color.lightGray, 10));
      pagePanel.setPreferredSize (new Dimension (500, 900));

      renderPage ();
   }


   /**
    * Class: PagePanel <p>
    *
    * Represent a page in the preview window
    *
    * @author Jean-Pierre Dube <jpdube@videotron.ca>
    * @version 1.0
    * @since 1.0
    * @see JPanel
    */
   private class PagePanel extends JPanel {

      //--- Private instances declarations
      //----------------------------------
      private PFPage page;
      private int zoom = 1;


      /**
       * Constructor: PagePanel <p>
       *
       */
      public PagePanel () {

         super ();

      }


      /**
       * Method: setZoomIn <p>
       *
       * @param parZoom a value of type int
       */
      public void setZoomIn (int parZoom) {

         zoom = parZoom;
         this.setSize (this.getSize ().width * zoom, this.getSize ().height * zoom);
         repaint ();

      }


      /**
       * Method: setZoomOut <p>
       *
       * @param parZoom a value of type int
       */
      public void setZoomOut (int parZoom) {

         zoom = parZoom;
         this.setSize (this.getSize ().width / zoom, this.getSize ().height / zoom);
         repaint ();
      }


      /**
       * Method: renderPage <p>
       *
       * @param parPage a value of type PFPage
       */
      public void renderPage (PFPage parPage) {

         page = parPage;
         repaint ();
      }


      /**
       * Method: paint <p>
       *
       * @param parG a value of type Graphics
       */
      public void paint (Graphics parG) {

         super.paint (parG);

         Dimension size = this.getSize ();
         double scaleFactor = 0.77;

         BufferedImage doubleBuffer = new BufferedImage (size.width, size.height, BufferedImage.TYPE_INT_RGB);
         Graphics2D g2d = (Graphics2D) doubleBuffer.getGraphics ();
         g2d.setColor (Color.white);
         g2d.fillRect (0, 0, size.width, size.height);
         g2d.scale (scaleFactor, scaleFactor);
         g2d.setColor (Color.red);
         g2d.drawRect (0, 0, size.width, size.height);

         if (page != null)
            page.print (g2d, new PageFormat (), 0);

         if (doubleBuffer != null)
            parG.drawImage (doubleBuffer, 0, 0, this);

      }

   }


   /**
    * Class: PFPrintPreviewToolBar <p>
    *
    * This class responsability is to display the
    * toolbar and handle the user requests
    *
    * @author Jean-Pierre Dube <jpdube@videotron.ca>
    * @version 1.0
    * @since 1.0
    * @see JToolBar
    * @see ActionListener
    */
   public class PFPrintPreviewToolBar extends JToolBar implements ActionListener {

      //--- Private instances declarations
      //----------------------------------

      //--- Buttons
      private JButton firstPage = new JButton ();
      private JButton lastPage = new JButton ();
      private JButton nextPage = new JButton ();
      private JButton previousPage = new JButton ();
      private JButton zoomIn = new JButton ();
      private JButton zoomOut = new JButton ();
      private JButton print = new JButton ();

      PFPrintPreview preview;


      /**
       * Constructor: PFPrintPreviewToolBar <p>
       *
       * @param parPreview a value of type PFPrintPreview
       */
      public PFPrintPreviewToolBar (PFPrintPreview parPreview) {

         super ();
         preview = parPreview;
         init ();
      }


      /**
       * Method: init <p>
       *
       * Init this class and all the Swing controls
       *
       */
      private void init () {

         //--- Init the buttons
         //firstPage.setIcon (new ImageIcon (getClass ().getResource ("images/FirstPage.gif")));
         firstPage.setIcon (firstPageIcon);
         firstPage.setActionCommand ("firstPage");
         firstPage.addActionListener (this);

         //previousPage.setIcon (new ImageIcon (getClass ().getResource ("images/PreviousPage.gif")));
         previousPage.setIcon (previousPageIcon);
         previousPage.setActionCommand ("previousPage");
         previousPage.addActionListener (this);

         //nextPage.setIcon (new ImageIcon (getClass ().getResource ("images/NextPage.gif")));
         nextPage.setIcon (nextPageIcon);
         nextPage.setActionCommand ("nextPage");
         nextPage.addActionListener (this);

         lastPage.setIcon (lastPageIcon);
         lastPage.setActionCommand ("lastPage");
         lastPage.addActionListener (this);

         //zoomIn.setIcon (new ImageIcon (getClass ().getResource ("images/ZoomIn.gif")));
         zoomIn.setIcon (zoomInIcon);
         zoomIn.setActionCommand ("zoomIn");
         zoomIn.addActionListener (this);
         zoomIn.setEnabled (false);

         //zoomOut.setIcon (new ImageIcon (getClass ().getResource ("images/ZoomOut.gif")));
         zoomOut.setIcon (zoomOutIcon);
         zoomOut.setActionCommand ("zoomOut");
         zoomOut.addActionListener (this);
         zoomOut.setEnabled (false);

         //print.setIcon (new ImageIcon (getClass ().getResource ("images/Print.gif")));
         print.setIcon (printIcon);
         print.setActionCommand ("print");
         print.addActionListener (this);


         //--- Init the toolbar
         this.add (firstPage);
         this.add (previousPage);
         this.add (nextPage);
         this.add (lastPage);
         this.add (zoomIn);
         this.add (zoomOut);
         this.add (print);
      }


      /**
       * Method: actionPerformed <p>
       *
       * @param parEvent a value of type ActionEvent
       */
      public void actionPerformed (ActionEvent parEvent) {

         String command = parEvent.getActionCommand ();

         if (command.equals ("nextPage"))
            preview.nextPage ();

         else if (command.equals ("previousPage"))
            preview.previousPage ();

         else if (command.equals ("firstPage"))
            preview.firstPage ();

         else if (command.equals ("lastPage"))
            preview.lastPage ();

         else if (command.equals ("print"))
            preview.print ();

      }

   }
}// PFPrintPreview





















