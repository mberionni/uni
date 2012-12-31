package PrintPackage;


/**
 * Class: PFDocument <p>
 *
 * This class is at the top of the print framework hierarchy
 * To use this framework you must create an instance of this
 * class and then add pages to it. This class is also
 * responsible for instantiating the print process as well
 * as all the export features built-in the framework.
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

import java.awt.print.PrinterJob;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.util.Vector;
import java.util.LinkedList;


public class PFDocument {

   //--- Private instances declarations
   //----------------------------------

   //--- Page data structure
   private Vector pageCollection = new Vector ();

   //--- Strings
   private String documentName = "";
   private String exportFilename = "";

   //--- Print dialogs
   private boolean showPrintDialog = false;
   private boolean showPageDialog = false;

   //--- Headers and footers
   private PFPrintObject header = null;
   private PFPrintObject footer = null;
   private PFPrintObject firstPageHeader = null;
   private PFPrintObject firstPageFooter = null;


   /**
    * Constructor: PFDocument <p>
    *
    * Default constructor
    *
    */
   public PFDocument () {

   }


   /**
    * Method: addPage <p>
    *
    * Add a page to this document, the page must be
    * an instance of the <code>PFPage</code> class.
    *
    * @param parPage a value of type PFPage
    */
   public void addPage (PFPage parPage) {

      if (parPage != null) {
         pageCollection.add (parPage);
         parPage.setDocument (this);
      }
   }


   /**
    * Method: addPage <p>
    *
    * Insert a page in this document.
    *
    * @param parPage a value of type PFPage, the page to  insert in the document
    * @param parPageNo a value of type int, insertion point in the document
    */
   public void addPage (PFPage parPage, int parPageNo) {

      if (parPage != null) {
         pageCollection.insertElementAt (parPage, parPageNo);
         parPage.setDocument (this);
      }
   }


   /**
    * Method: removePage <p>
    *
    * Remove a page using the page number
    *
    * @param parPageNo a value of type int, the page number to remove
    */
   public void removePage (int parPageNo) {

      if (parPageNo >= 0 && parPageNo < pageCollection.size ())
         pageCollection.remove (parPageNo);

   }


   /**
    * Method: removePage <p>
    *
    * Remove a page using an instance of the page
    *
    * @param parPage a value of type PFPage, the instance of the page to remove
    */
   public void removePage (PFPage parPage) {

      if (parPage != null)
         pageCollection.remove (parPage);

   }


   /**
    * Method: getPage <p>
    *
    * Get a page using the page number as the reference.
    *
    * @param parPageNo a value of type int, the page number
    *
    * @return a value of type PFPage
    */
   public PFPage getPage (int parPageNo) {

      if (parPageNo >= 0 && parPageNo < pageCollection.size ())
         return ((PFPage) pageCollection.get (parPageNo));
      else
         return (null);

   }


   /**
    * Method: getPageCount <p>
    *
    * Return the number of pages currently in the document.
    * The page count is zero based.
    *
    * @return a value of type int
    */
   public int getPageCount () {

      return (pageCollection.size ());

   }


   /**
    * Method: setDocumentName <p>
    *
    * Give a name to this document. The document name
    * is used by the operating system print queue to
    * identify this print job. The document name is also
    * used by the <code>PfPrintPreview</code> class
    * to identify the document.
    *
    * @param parName a value of type String, the name of the document
    */
   public void setDocumentName (String parName) {

      documentName = parName;
   }


   /**
    * Method: getDocumentName <p>
    *
    * Return the name of this document.
    *
    * @return a value of type String
    */
   public String getDocumentName () {

      return (documentName);

   }


   /**
    * Method: showPrintDialog <p>
    *
    * Set the showPrintDialog flag. The print dialog
    * will not be shown immediatly. It will only be shown
    * when the print process is instantiated.
    *
    * @param parShowPrintDialog a value of type boolean
    *
    */
   public void showPrintDialog (boolean parShowPrintDialog) {

      showPrintDialog = parShowPrintDialog;

   }


   /**
    * Method: showPageDialog <p>
    *
    * Set the showPage dialog flag. The page dialog that will
    * be showned is the print framework dialog, not the one
    * provided by the Java Print API.
    *
    * @param parShowPageDialog a value of type boolean
    */
   public void showPageDialog (boolean parShowPageDialog) {

      showPageDialog = parShowPageDialog;

   }


   /**
    * Method: print <p>
    *
    * Print the whole document
    *
    */
   public void print () {

      print (0, pageCollection.size ());

   }


   /**
    * Method: print <p>
    *
    * Print a page range. You must supply the start
    * and end page to be printed. Appropriate dialogs
    * will be showned if necessary.
    *
    * @param parStartPage the first page to print
    * @param parEndPage the last page
    */
   public void print (int parStartPage, int parEndPage) {

      PFPage page;
      PFPageFormat pageFormat = new PFPageFormat ();
      int i;

      //--- Require
      if (parEndPage > pageCollection.size ())
         parEndPage = pageCollection.size ();

      if (parStartPage < 0)
         parStartPage = 0;

      //--- Create a new PrinterJob object
      PrinterJob printJob = PrinterJob.getPrinterJob ();
      printJob.setJobName (documentName);

      //--- Show the page dialog
      /*if (showPageDialog) {
         PFPageSetupDialog pageSetupDialog = new PFPageSetupDialog (pageFormat);
         pageFormat = pageSetupDialog.showDialog ();
      }*/
      // attualmente il pageDialog è realizzato in altro modo: non viene utilizzata la classe PFPageSetupDialog !

      //--- Create a new book to add pages to
      Book book = new Book ();

      //--- Add the pages to the book
      for (i = parStartPage; i < parEndPage; i++) {
         page = (PFPage) pageCollection.get (i);

         if (showPageDialog)
            book.append (page, pageFormat.getPageFormat ());
         else
            book.append (page, page.getPageFormat ().getPageFormat ());
      }

      //--- Tell the printJob to use the book as the pageable object
      printJob.setPageable (book);

      //--- Show the print dialog box. If the user click the
      //--- print button we then proceed to print else we abort.
      try {
         if (showPrintDialog) {
            if (printJob.printDialog()) {
               printJob.print();
            }
         }
         else
            printJob.print ();

      } catch (Exception PrintException) {
         PrintException.printStackTrace();
      }
   }

   public Pageable getPageable()
   {  PFPage page;
      PFPageFormat pageFormat = new PFPageFormat ();
      int i;
      int parEndPage = this.getPageCount();
      int parStartPage = 0;
      //--- Require
      if (parEndPage > pageCollection.size ())
         parEndPage = pageCollection.size ();

      if (parStartPage < 0)
         parStartPage = 0;

      //--- Create a new PrinterJob object
      //PrinterJob printJob = PrinterJob.getPrinterJob ();
      //printJob.setJobName (documentName);

      //--- Create a new book to add pages to
      Book book = new Book ();

      //--- Add the pages to the book
      for (i = parStartPage; i < parEndPage; i++) {
         page = (PFPage) pageCollection.get (i);

         if (showPageDialog)
            book.append (page, pageFormat.getPageFormat ());
         else
            book.append (page, page.getPageFormat ().getPageFormat ());
      }
      return book;
   }
   /**
    * Method: printPreview <p>
    *
    * Show this document in the print preview window
    *
    */
  /* public void printPreview () {

      PFPrintPreview printPreview = new PFPrintPreview (this);

   }*/
   // attualmente si utilizza un'altra classe per il preview


   /**
    * Method: setHeader <p>
    *
    * Set the page header for this document
    *
    */
   public void setHeader (PFPrintObject parHeader) {

      header = parHeader;

   }


   /**
    * Method: setFooter <p>
    *
    * Set the page footer for this document
    *
    */
   public void setFooter (PFPrintObject parFooter) {

      footer = parFooter;

   }


   /**
    * Method: setFirstPageHeader <p>
    *
    * Set the page header for the first page only
    *
    * @param parHeader a value of type PFPrintObject
    */
   public void setFirstPageHeader (PFPrintObject parHeader) {

      firstPageHeader = parHeader;

   }


   /**
    * Method: setFirstPageFooter <p>
    *
    * Set the page footer for the first page only
    *
    * @param parFooter a value of type PFPrintObject
    */
   public void setFirstPageFooter (PFPrintObject parFooter) {

      firstPageFooter = parFooter;
   }


   /**
    * Method: getHeader <p>
    *
    * Get the page haader
    *
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getHeader () {

      return (header);
   }


   /**
    * Method: getFooter <p>
    *
    * Get the page footer
    *
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getFooter () {

      return (footer);
   }


   /**
    * Method: getHeader <p>
    *
    * @param parPage a value of type PFPage
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getHeader (PFPage parPage) {

      if (getPageNo (parPage) == 0 && firstPageHeader != null)
         return (firstPageHeader);
      else
         return (header);

   }


   /**
    * Method: getFooter <p>
    *
    * @param parPage a value of type PFPage
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getFooter (PFPage parPage) {

      if (getPageNo (parPage) == 0 && firstPageFooter != null)
         return (firstPageFooter);
      else
         return (footer);

   }


   /**
    * Method: getPageNo <p>
    *
    * Return the page number for the page passed in
    * parameters.
    *
    * @param parPage a value of type PFPage
    * @return a value of type int
    */
   public int getPageNo (PFPage parPage) {

      return (pageCollection.indexOf (parPage));
   }
}// PFDocument





















