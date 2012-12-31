package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

public class EquipmentInventory_Mgr extends equipment.EquipmentInventoryMgr_IPOA
{
  public EquipmentInventory_Mgr()
  {
  }

  /**
   * <pre>
   *   void provisionEquipment (in equipment.EQTCreateData_T equipmentCreateData,
                           out equipment.Equipment_T createdEquipment)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void provisionEquipment (equipment.EQTCreateData_T equipmentCreateData, equipment.Equipment_THolder createdEquipment) throws globaldefs.ProcessingFailureException
  {   try
      {   println("arf! giunta richiesta provisionEquipment");
          createdEquipment.value = (ServerData.provisionEquipment(equipmentCreateData)).value;
          println("wow! metodo eseguito correttamente ");
      }   catch (Exception e) {   e.printStackTrace();
                                  if (e instanceof globaldefs.ProcessingFailureException)
                                  {   globaldefs.ProcessingFailureException ex = (globaldefs.ProcessingFailureException)e;
                                      throw ex;
                                  }
                              }
  }

  /**
   * <pre>
   *   void unprovisionEquipment (in globaldefs.NamingAttributes_T equipmentName)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void unprovisionEquipment (globaldefs.NameAndStringValue_T[] equipmentName) throws globaldefs.ProcessingFailureException
  {   println("sto effettuando l'unprovision dell'equipment"  + equipmentName[0].name +" value=" +equipmentName[0].value);
      // metodo non implementato
  }

  /**
   * <pre>
   *   void setAlarmReportingOn (in globaldefs.NamingAttributes_T equipmentOrHolderName)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setAlarmReportingOn (globaldefs.NameAndStringValue_T[] equipmentOrHolderName) throws globaldefs.ProcessingFailureException
  {  println("set alarm reposrting on per l'equipment "+ equipmentOrHolderName[0].toString());
  }

  /**
   * <pre>
   *   void setAlarmReportingOff (in globaldefs.NamingAttributes_T equipmentOrHolderName)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void setAlarmReportingOff (globaldefs.NameAndStringValue_T[] equipmentOrHolderName) throws globaldefs.ProcessingFailureException
  {  println("set alarm reposrting off per l'equipment "+ equipmentOrHolderName[0].toString());
     // non implementato
  }

  /**
   * <pre>
   *   void getContainedEquipment (in globaldefs.NamingAttributes_T equipmentHolderName,
                              out equipment.EquipmentOrHolderList_T equipmentOrHolderList)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getContainedEquipment (globaldefs.NameAndStringValue_T[] equipmentHolderName,
                                     equipment.EquipmentOrHolderList_THolder equipmentOrHolderList) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getEquipment (in globaldefs.NamingAttributes_T equipmentOrHolderName,
                     out equipment.EquipmentOrHolder_T equip)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getEquipment (globaldefs.NameAndStringValue_T[] equipmentOrHolderName,
                            equipment.EquipmentOrHolder_THolder equip) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getAllEquipment (in globaldefs.NamingAttributes_T meOrHolderName,
                        in unsigned long how_many,
                        out equipment.EquipmentOrHolderList_T eqList,
                        out equipment.EquipmentOrHolderIterator_I eqIt)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllEquipment (globaldefs.NameAndStringValue_T[] meOrHolderName,
                               int how_many,
                               equipment.EquipmentOrHolderList_THolder eqList,
                               equipment.EquipmentOrHolderIterator_IHolder eqIt) throws globaldefs.ProcessingFailureException
  {    try
       {  println("slurp! giunta richiesta getAllEquipment");
          eqList.value = ServerData.getAllEquipment(meOrHolderName, how_many);
          if (ServerData.remainingEquipm != 0)
          {   equipment.EquipmentOrHolder_T[] eqIteratorList = ServerData.getRemainingEquipmentIterator(how_many);
              org.omg.CORBA.Object obj = _default_POA().servant_to_reference( new equipOrHolderIterator(eqIteratorList));
              equipment.EquipmentOrHolderIterator_I eqHI = equipment.EquipmentOrHolderIterator_IHelper.narrow(obj);
              eqIt.value = eqHI;
          }
          else
          {   eqIt.value = null;
          }
          println("wow! metodo eseguito correttamente");

       }  catch (org.omg.CORBA.UserException e) {  e.printStackTrace();
                                                   if (e instanceof globaldefs.ProcessingFailureException)
                                                   {   globaldefs.ProcessingFailureException ex = (globaldefs.ProcessingFailureException)e;
                                                       throw ex;
                                                   }
                                                }
  }

  /**
   * <pre>
   *   void getAllEquipmentNames (in globaldefs.NamingAttributes_T meOrHolderName,
                             in unsigned long how_many,
                             out globaldefs.NamingAttributesList_T nameList,
                             out globaldefs.NamingAttributesIterator_I nameIt)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllEquipmentNames (globaldefs.NameAndStringValue_T[] meOrHolderName,
                                    int how_many,
                                    globaldefs.NamingAttributesList_THolder nameList,
                                    globaldefs.NamingAttributesIterator_IHolder nameIt) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getAllSupportedPTPs (in globaldefs.NamingAttributes_T equipmentName,
                            in unsigned long how_many,
                            out terminationPoint.TerminationPointList_T tpList,
                            out terminationPoint.TerminationPointIterator_I tpIt)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllSupportedPTPs (globaldefs.NameAndStringValue_T[] equipmentName,
                                   int how_many,
                                   terminationPoint.TerminationPointList_THolder tpList,
                                   terminationPoint.TerminationPointIterator_IHolder tpIt) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getAllSupportedPTPNames (in globaldefs.NamingAttributes_T equipmentName,
                                in unsigned long how_many,
                                out globaldefs.NamingAttributesList_T nameList,
                                out globaldefs.NamingAttributesIterator_I nameIt)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllSupportedPTPNames (globaldefs.NameAndStringValue_T[] equipmentName,
                                       int how_many,
                                       globaldefs.NamingAttributesList_THolder nameList,
                                       globaldefs.NamingAttributesIterator_IHolder nameIt) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getAllSupportingEquipment (in globaldefs.NamingAttributes_T ptpName,
                                  out equipment.EquipmentOrHolderList_T eqList)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllSupportingEquipment (globaldefs.NameAndStringValue_T[] ptpName,
                                         equipment.EquipmentOrHolderList_THolder eqList) throws globaldefs.ProcessingFailureException
  {
  }

  /**
   * <pre>
   *   void getAllSupportingEquipmentNames (in globaldefs.NamingAttributes_T ptpName,
                                       out globaldefs.NamingAttributesList_T nameList)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void getAllSupportingEquipmentNames (globaldefs.NameAndStringValue_T[] ptpName,
                                              globaldefs.NamingAttributesList_THolder nameList) throws globaldefs.ProcessingFailureException
  {
  }

/**
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

  public void println(String text)
  {   StartServer.getMonitorWindow().println(text);
      System.out.println(text);
  }

  }