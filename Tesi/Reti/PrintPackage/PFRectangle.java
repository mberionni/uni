package PrintPackage;


/**
 * Class: PFRectangle <p>
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */


public class PFRectangle {

   //--- Private instances declarations
   private PFPoint location = new PFPoint (new PFInchUnit (0.0), new PFInchUnit (0.0));
   private PFSize size = new PFSize (new PFInchUnit (0.0), new PFInchUnit (0.0));


   /**
    * Constructor: PFRectangle <p>
    *
    */
   public PFRectangle () {

   }


   /**
    * Method: setX <p>
    *
    * @param parX a value of type PFUnit
    */
   public void setX (PFUnit parX) {

      location.setX (parX);
   }


   /**
    * Method: setY <p>
    *
    * @param parY a value of type PFUnit
    */
   public void setY (PFUnit parY) {

      location.setY (parY);

   }


   /**
    * Method: setLocation <p>
    *
    * @param parPoint a value of type PFPoint
    */
   public void setLocation (PFPoint parPoint) {

      location = parPoint;

   }


   /**
    * Method: setWidth <p>
    *
    * @param parWidth a value of type PFUnit
    */
   public void setWidth (PFUnit parWidth) {

      size.setWidth (parWidth);

   }


   /**
    * Method: setHeight <p>
    *
    * @param parHeight a value of type PFUnit
    */
   public void setHeight (PFUnit parHeight) {

      size.setHeight (parHeight);

   }


   /**
    * Method: setSize <p>
    *
    * @param parSize a value of type PFSize
    */
   public void setSize (PFSize parSize) {

      size = parSize;
   }

}// PFRectangle




















