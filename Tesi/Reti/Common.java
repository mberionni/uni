package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public class Common extends common.Common_IPOA
{ /**
   * <pre>
   *   void setNativeEMSName (in globaldefs.NamingAttributes_T objectName,
                         in string nativeEMSName)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setNativeEMSName (globaldefs.NameAndStringValue_T[] objectName, java.lang.String nativeEMSName) throws globaldefs.ProcessingFailureException
  {  this.setNativeEMSName(objectName, nativeEMSName);
  }


  /**
   * <pre>
   *   void setUserLabel (in globaldefs.NamingAttributes_T objectName,
                     in string userLabel, in boolean enforceUniqueness)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setUserLabel (globaldefs.NameAndStringValue_T[] objectName, java.lang.String userLabel, boolean enforceUniqueness) throws globaldefs.ProcessingFailureException
  {   this.setUserLabel(objectName, userLabel, enforceUniqueness);
  }

  /**
   * <pre>
   *   void setOwner (in globaldefs.NamingAttributes_T objectName, in string owner)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setOwner (globaldefs.NameAndStringValue_T[] objectName, java.lang.String owner) throws globaldefs.ProcessingFailureException
  {   this.setOwner(objectName, owner);
  }

  /**
   * <pre>
   *   void getCapabilities (out common.CapabilityList_T capabilities)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getCapabilities (common.CapabilityList_THolder capabilities) throws globaldefs.ProcessingFailureException
  {   this.getCapabilities(capabilities);
  }



}