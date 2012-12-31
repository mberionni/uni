package PrintPackage;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

public class PFCmUnit extends PFUnit {

   //--- Private constants declarations
   private final static double POINTS_PER_CM = 28.3465;


   /**
    * Constructor: PFCmUnit <p>
    *
    */
   public PFCmUnit () {

   }


   /**
    * Constructor: PFCmUnit <p>
    *
    * @param parValue a value of type double
    */
   public PFCmUnit (double parValue) {

      super (parValue);

   }


   /**
    * Method: getPoints <p>
    *
    * Return the result of the conversion from
    * centimeters to points.
    *
    * @return a value of type double
    */
   public double getPoints () {

      return (getUnits () * POINTS_PER_CM);

   }


   /**
    * Method: setPoints <p>
    *
    * @param parPoints a value of type double
    */
   public void setPoints (double parPoints) {

      setUnits (parPoints / POINTS_PER_CM);

   }

}// PFCmUnit



















