package PrintPackage;


/**
 * Class: PFPage <p>
 *
 * The page class act as a container of <code>PFPrintObject</code>.
 * Each page in a <code>PFDocument</code> has to be an instance
 * of this class. This class implement the <code>Printable</code>
 * interface
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */


import java.awt.print.Printable;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;


public class PFPage implements Printable {

   //--- Private instances declarations
   //----------------------------------

   //--- Page format
   private PFPageFormat pageFormat = new PFPageFormat ();

   //--- Page object container
   private Vector pageObjectCollection = new Vector ();

   //--- Header and footer
   private PFPrintObject header = null;
   private PFPrintObject footer = null;

   //--- Reference to parent document
   private PFDocument document = null;


   /**
    * Constructor: PFPage <p>
    *
    * Default constructor
    */
   public PFPage () {

   }


   /**
    * Method: setPageFormat <p>
    *
    * @param parPageFormat a value of type PFPageFormat
    */
   public void setPageFormat (PFPageFormat parPageFormat) {

      if (parPageFormat != null)
         pageFormat = parPageFormat;
   }


   /**
    * Method: getPageFormat <p>
    *
    * @return a value of type PFPageFormat
    */
   public PFPageFormat getPageFormat () {

      return ((PFPageFormat) pageFormat.clone ());
   }


   /**
    * Method: print <p>
    *
    * Print header/footer and the page content. This method
    * implement the <code>Printable</code> interface.
    *
    * @param parG a value of type Graphics
    * @param parPageFormat a value of type PageFormat
    * @param parPage a value of type int
    */
   public int print (Graphics parG, PageFormat parPageFormat, int parPage) {

      int i;
      PFPrintObject printObject;
      Graphics2D g2d;
      PFPrintObject printHeader;
      PFPrintObject printFooter;


      //--- Convert to Graphics 2D
      g2d = (Graphics2D) parG;


      //--- Render the header
      printHeader = getHeader ();
      if (printHeader != null) {
         printHeader.setRelativeMode (false);
         printHeader.setPosition (new PFPoint (pageFormat.getLeftMargin (), pageFormat.getTopMargin ()));
         printHeader.print (g2d);
      }

      //--- Render the footer
      printFooter = getFooter ();
      if (printFooter != null) {
         printFooter.setRelativeMode (false);
         PFSize tempSize = pageFormat.getPageAreaSize ();
         PFPoint tempPoint = new PFPoint (pageFormat.getLeftMargin (),
                                          pageFormat.getPageAreaSize ().getHeight ().substract (printFooter.getSize ().getHeight ()).add (pageFormat.getBottomMargin ()));

         printFooter.setPosition (tempPoint);

         printFooter.print (g2d);
      }

      //--- Draw page content
      //System.out.println("pageobj coll size = " + pageObjectCollection.size());
      for (i = 0; i < pageObjectCollection.size (); i++) {
         printObject = (PFPrintObject) pageObjectCollection.get (i);
         //System.out.println("i="+i+" printObj size="+printObject.getSize().toString()+" toString="+printObject.toString());
         printObject.print (g2d);

      }

      return (PAGE_EXISTS);
   }




   /**
    * Method: add <p>
    *
    * Add a print object (<code>PFPrintObject</code>) in
    * this page.
    *
    * @param parPrintObject a value of type PFPrintObject
    */
   public void add (PFPrintObject parPrintObject) {

      pageObjectCollection.add (parPrintObject);
      parPrintObject.setPage (this);

   }


   /**
    * Method: remove <p>
    *
    * Remove a print object from this page
    *
    * @param parPrintObject a value of type PFPrintObject
    */
   public void remove (PFPrintObject parPrintObject) {

      pageObjectCollection.remove (parPrintObject);
   }


   /**
    * Method: setDocument <p>
    *
    * Set the parent document object.
    *
    * @param parDocument a value of type PFDocument
    */
   public void setDocument (PFDocument parDocument) {

      document = parDocument;
   }


   /**
    * Method: getPrintableAreaOrigin <p>
    *
    * Return the printable area origin. The print
    * area origin is defined as the top left corner
    * of the page including margins and gutters but
    * excluding the header and footers.
    *
    * @return a value of type PFPoint
    */
   public PFPoint getPrintableAreaOrigin () {

      PFPoint origin;

      if (getHeader () != null)
         origin = new PFPoint (pageFormat.getLeftMargin ().add (pageFormat.getGutter ()), pageFormat.getTopMargin ().add (getHeader ().getSize ().getHeight ()));
      else
         origin = new PFPoint (pageFormat.getLeftMargin ().add (pageFormat.getGutter ()), pageFormat.getTopMargin ());

      return (origin);

   }


   /**
    * Method: getPrintableAreaSize <p>
    *
    * Return the printable area size including the
    * margins and gutter including the header and
    * footer heights.
    *
    * @return a value of type PFSize
    */
   public PFSize getPrintableAreaSize () {

      PFSize areaSize = new PFSize (new PFInchUnit (0.0), new PFInchUnit (0.0));


      areaSize.setWidth ((PFInchUnit) pageFormat.getPageSize ().getWidth ().substract (pageFormat.getLeftMargin ()).substract (pageFormat.getGutter ()).substract (pageFormat.getRightMargin ()));
      areaSize.setHeight (pageFormat.getPageSize ().getHeight ().substract (pageFormat.getTopMargin ()).substract (pageFormat.getBottomMargin ()));

      if (header != null)
         areaSize.setHeight (areaSize.getHeight ().substract (header.getSize ().getHeight ()));

      if (footer != null)
         areaSize.setHeight (areaSize.getHeight ().substract (footer.getSize ().getHeight ()));

      return (areaSize);
   }



   /**
    * Method: setHeader <p>
    *
    * Set the header for this page. This
    * header will override the header specified in
    * the document
    *
    * @param parHeader a value of type PFPrintObject
    */
   public void setHeader (PFPrintObject parHeader) {

      header = parHeader;
      header.setPage (this);

   }


   /**
    * Method: getHeader <p>
    *
    * Return the header for this page
    *
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getHeader () {

      if (document != null) {
         if (header == null && document.getHeader (this) != null)
            return (document.getHeader ());
      }

      return (header);
   }


   /**
    * Method: setFooter <p>
    *
    * Set the footer for this page. This footer setting
    * will override the footer setting in the document.
    *
    * @param parFooter a value of type PFPrintObject
    */
   public void setFooter (PFPrintObject parFooter) {

      footer = parFooter;
      footer.setPage (this);
   }


   /**
    * Method: getFooter <p>
    *
    * Return the footer for this page
    *
    * @return a value of type PFPrintObject
    */
   public PFPrintObject getFooter () {

      if (document != null) {
         if (footer == null && document.getFooter (this) != null)
            return (document.getFooter ());
      }

      return (footer);
   }


   /**
    * Method: getPageNo <p>
    *
    * Return the page number for this page.
    *
    * @return a value of type int
    */
   public int getPageNo () {

      return (document.getPageNo (this));

   }

}// PFPage





























