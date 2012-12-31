package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */


  public class MaintenanceManager extends maintenanceOps.MaintenanceMgr_IPOA //implements common.Common_I
  {  /**
     * <pre>
     *   void performMaintenanceOperation (in maintenanceOps.CurrentMaintenanceOperation_T maintenanceOperation,
                                           in maintenanceOps.MaintenanceOperationMode_T maintenanceOperationMode)
      raises (globaldefs.ProcessingFailureException);
     * </pre>
     */
    public void performMaintenanceOperation (maintenanceOps.CurrentMaintenanceOperation_T maintenanceOperation,
                                             maintenanceOps.MaintenanceOperationMode_T maintenanceOperationMode) throws globaldefs.ProcessingFailureException
    {   System.out.println("sto performando delle fantastiche maintenance operations");
    }

   /**
   * <pre>
   *   void getActiveMaintenanceOperations (in globaldefs.NamingAttributes_T tpOrMeName,
                                       in unsigned long how_many,
                                       out maintenanceOps.CurrentMaintenanceOperationList_T currentMaintenanceOpeationList,
                                       out maintenanceOps.CurrentMaintenanceOperationIterator_I cmoIt)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
    public void getActiveMaintenanceOperations (globaldefs.NameAndStringValue_T[] tpOrMeName,
                                              int how_many,
                                              maintenanceOps.CurrentMaintenanceOperationList_THolder currentMaintenanceOperationList,
                                              maintenanceOps.CurrentMaintenanceOperationIterator_IHolder cmoIt) throws globaldefs.ProcessingFailureException
    {   // trascuro il primo parametro d'ingresso
        String risposta[] = new String[] { "FACILITY_LOOPBACK", "TERMINAL_LOOPBACK", "FACILITY_FORCED_AIS", "TERMINAL_FORCED_AIS", "FORCE_RDI", "SET_AS_SEGMENT_END_POINT", "END_TO_END_LOOPBACK_OAM_CELL", "SEGMENT_LOOPBACK_OAM_CELL" };
        int m = 0;
        maintenanceOps.CurrentMaintenanceOperation_T[] ca = new maintenanceOps.CurrentMaintenanceOperation_T[] {};
        final globaldefs.NameAndStringValue_T[] additionalInfo = new globaldefs.NameAndStringValue_T[] {};
        short k;
        for (k = 0; k < tpOrMeName.length && k < how_many; k++)
        {  if (k >= risposta.length) m = 0;
           else m = k;
           final short num =  k;
           maintenanceOps.CurrentMaintenanceOperation_T c = new maintenanceOps.CurrentMaintenanceOperation_T(tpOrMeName, risposta[m], num , additionalInfo);
           ca[k] = c;
           m++;
        }
        currentMaintenanceOperationList.value = ca;

       // maintenanceOps.CurrentMaintenanceOperationIterator_I it =  maintenanceOps.CurrentMaintenanceOperationIterator_I
        if (k == how_many && k < tpOrMeName.length)
        { cmoIt.value = (maintenanceOps.CurrentMaintenanceOperationIterator_I) new currMaintOpIt(ca);
        }
    }

    public void setNativeEMSName (globaldefs.NameAndStringValue_T[] objectName, java.lang.String nativeEMSName) throws globaldefs.ProcessingFailureException
    { throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_NOT_IMPLEMENTED, "questa funzione non è implementata!");
    }


  /**
   * <pre>
   *   void setUserLabel (in globaldefs.NamingAttributes_T objectName,
                     in string userLabel, in boolean enforceUniqueness)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setUserLabel (globaldefs.NameAndStringValue_T[] objectName, java.lang.String userLabel, boolean enforceUniqueness) throws globaldefs.ProcessingFailureException
  {  throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_NOT_IMPLEMENTED, "questa funzione non è implementata!");

  }

  /**
   * <pre>
   *   void setOwner (in globaldefs.NamingAttributes_T objectName, in string owner)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setOwner (globaldefs.NameAndStringValue_T[] objectName, java.lang.String owner) throws globaldefs.ProcessingFailureException
  {   throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_NOT_IMPLEMENTED, "questa funzione non è implementata!");

  }


  /**
   * <pre>
   *   void getCapabilities (out common.CapabilityList_T capabilities)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getCapabilities (common.CapabilityList_THolder capabilities) throws globaldefs.ProcessingFailureException
  {   globaldefs.NameAndStringValue_T[] n = new globaldefs.NameAndStringValue_T [] { new globaldefs.NameAndStringValue_T("mic", "supported"), new globaldefs.NameAndStringValue_T("ste", "unsupported"), new globaldefs.NameAndStringValue_T("fra", "supported") };
      common.CapabilityList_THolder c = new common.CapabilityList_THolder(n);
  }

  }