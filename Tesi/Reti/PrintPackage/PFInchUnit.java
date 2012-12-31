package PrintPackage;

/**
 * Class: PFInchUnit <p>
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

public class PFInchUnit extends PFUnit {

   //--- Private constants declarations
   private final static int POINTS_PER_INCH = 72;


   /**
    * Constructor: PFInchUnit <p>
    *
    */
   public PFInchUnit () {

   }


   /**
    * Constructor: PFInchUnit <p>
    *
    * @param parValue a value of type double
    */
   public PFInchUnit (double parValue) {

      super (parValue);

   }


   /**
    * Method: getPoints <p>
    *
    * Return the result of the conversion from
    * inches to points.
    *
    * @return a value of type double
    */
   public double getPoints () {

      return (getUnits () * POINTS_PER_INCH);

   }


   /**
    * Method: setPoints <p>
    *
    * @param parPoints a value of type double
    */
   public void setPoints (double parPoints) {

      setUnits (parPoints / POINTS_PER_INCH);

   }

}// PFInchUnit



















