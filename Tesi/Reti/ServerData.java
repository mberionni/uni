package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public class ServerData
{ static EmsSession emsSession;
  static EquipmentInventoryMgr eqpIM;
  static MaintenanceManager mantMgr;
  static org.omg.CORBA.Object eqpIMRef, emsSessionRef, mantMgrRef;
  static String[] managerList = new String [] { "EMS", "ManagedElement", "MultiLayerSubnetwork", "TrafficDescriptor", "PerformanceManagement", "Protection", "EquipmentInventory", "Maintenance", "GuiCutThrough" };
  static equipment.EquipmentOrHolder_T[] equipmentList;
  static int numEquipm = 7;
  static int remainingEquipm = 0;

  public ServerData()
  {
  }

  public static void startup(org.omg.PortableServer.POA myPOA)
  {   try
      {   println("@ - Inzio fase startup del server");
          emsSession = new EmsSession();
          myPOA.activate_object(emsSession);
          emsSessionRef = myPOA.servant_to_reference(emsSession);
          println("creato oggetto EmsSession e relativo riferimento CORBA");

          eqpIM = new EquipmentInventoryMgr();
          myPOA.activate_object(eqpIM);
          eqpIMRef = myPOA.servant_to_reference(eqpIM);
          println("creato oggetto Equipment Inventory Manager e relativo riferiemnto CORBA");

          mantMgr = new MaintenanceManager();
          myPOA.activate_object(mantMgr);
          mantMgrRef = myPOA.servant_to_reference(mantMgr);
          println("creato oggetto Maintenance Manager e relativo riferiemnto CORBA");

          createEquipment();
          println("creati equipments - scelgo per ora di non creare holders");

          println("@ - Fine fase startup del server");

      }   catch (Exception e) {  e.printStackTrace();
                              }

  }

  public static EmsSession getEmsSession()
  {   return emsSession;
  }

  public static org.omg.CORBA.Object getEmsSessionRef()
  {   return emsSessionRef;
  }

 /* public static NmsSession getNmsSession()
  {   return nmsSession;
  }

  public static org.omg.CORBA.Object getNmsSessionRef()
  {   return nmsSessionRef;
  }*/

   public static MaintenanceManager getMaintenanceMgr()
  {   return mantMgr;
  }

  public static org.omg.CORBA.Object getMaintenanceMgrRef()
  {   return mantMgrRef;
  }

  public static EquipmentInventoryMgr getEquipmentInventoryMgr()
  {   return eqpIM;
  }

  public static org.omg.CORBA.Object getEquipmentInventoryMgrRef()
  {   return eqpIMRef;
  }

  public static String[] getSupportedManagerList()
  {   return managerList;
  }

  public static equipment.EquipmentOrHolder_T[] getAllEquipment(globaldefs.NameAndStringValue_T[] meOrHolderName, int how_many) throws globaldefs.ProcessingFailureException
  {   println("sob! sto trascurando l'ingresso");
      if (how_many > numEquipm)
      {   how_many = numEquipm;
          println("urgh! specificato valore troppo grande per how_many - correggo!");
      }
      else if (how_many < 0)
      {   println("urgh! specificato un valore minore di zero per how_many");
          throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_INVALID_INPUT, "how_many deve essere >= 0 !");
      }
      equipment.EquipmentOrHolder_T[] eqList = new equipment.EquipmentOrHolder_T[how_many];
      for (int k = 0; k < how_many; k++)
      {   eqList[k] = equipmentList[k];
      }
      remainingEquipm = numEquipm - how_many;
      return eqList;
  }

  public static equipment.EquipmentOrHolder_T[] getRemainingEquipmentIterator(int how_many)
  {   equipment.EquipmentOrHolder_T[]  eqL;
      if ( remainingEquipm > 0)
      {   eqL = new equipment.EquipmentOrHolder_T[remainingEquipm];
          int i = 0;
          for (int k = 0; k < remainingEquipm; k++)
          {   i = how_many + k;
              eqL[k] = equipmentList[i];
          }
          remainingEquipm = numEquipm - i;
       }
       else
       {    eqL = new equipment.EquipmentOrHolder_T[] {};
            println("sob! errore non previsto - iterator vuoto non ho piu' elementi da ritornare");
       }
       return eqL;
  }

  public static equipment.Equipment_THolder provisionEquipment(equipment.EQTCreateData_T equipmentCreateData) throws globaldefs.ProcessingFailureException
  {   try
      {   String userLabel = equipmentCreateData.userLabel;
          String owner = equipmentCreateData.owner;
          boolean uniqueness = equipmentCreateData.forceUniqueness;
          String expectedEquipmentObjectType = equipmentCreateData.expectedEquipmentObjectType;
          globaldefs.NameAndStringValue_T[] additionalInfo = equipmentCreateData.additionalInfo;
          globaldefs.NameAndStringValue_T[] name = equipmentCreateData.equipmentHolderName;
          String PartNumber = "5";
          String VersionNumber = "1.0";
          String SerialNumber = "9.3";
          equipment.EquipmentOrHolder_T eqH = createEqOrHolder(userLabel, owner, expectedEquipmentObjectType, name, additionalInfo, PartNumber, VersionNumber, SerialNumber);
          return  new equipment.Equipment_THolder(eqH.equip());
      }   catch (Exception e) {   e.printStackTrace();
                                  throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_INTERNAL_ERROR, "Errore incomprensibile: dare fuoco al PC oppure riavviare");
                              }
  }

  public static void createEquipment()
  {   int numCampi = 9;   // numero di campi
      String[][] stringArray = new String[numEquipm][numCampi];
      stringArray[0] = new String[] { "sony","h21","casse acustiche","ESMName","michele","cassa","2.3","4", "05465377892" };
      stringArray[1] = new String[] { "philips","v1","casse acustiche","ESMName","michele","cassa","1.1","2", "0565683489" };
      stringArray[2] = new String[] { "michele3","value3","userLabel3","ESMName3","owner3","type3","partNumber3","versionNumber3", "serialNumber3" };
      stringArray[3] = new String[] { "michele4","value4","userLabel4","ESMName4","owner4","type4","partNumber4","versionNumber4", "serialNumber4" };
      stringArray[4] = new String[] { "michele5","value5","userLabel5","ESMName5","owner5","type5","partNumber5","versionNumber5", "serialNumber5" };
      stringArray[5] = new String[] { "michele6","value6","userLabel6","ESMName6","owner6","type6","partNumber6","versionNumber6", "serialNumber6" };
      stringArray[6] = new String[] { "michele7","value7","userLabel7","ESMName7","owner7","type7","partNumber7","versionNumber7", "serialNumber7" };
      equipmentList = new equipment.EquipmentOrHolder_T[numEquipm];
      for (int k = 0; k < numEquipm; k++)
      {   equipmentList[k] = createEqOrHolder(stringArray[k]);
      }
  }

  public static equipment.EquipmentOrHolder_T createEqOrHolder(String userLabel, String owner, String expectedEquipmentObjectType, globaldefs.NameAndStringValue_T[] name, globaldefs.NameAndStringValue_T[] additionalInfo, String partNumber, String versionNumber, String serialNumber)
  {   equipment.ServiceState_T serviceState = equipment.ServiceState_T.IN_SERVICE;
      boolean alarmReportingIndicator = false;
      String ESMName = "ESMName";
      String installedEquipmentObjectType = expectedEquipmentObjectType;
      equipment.Equipment_T equipment = new equipment.Equipment_T(name, userLabel, ESMName, owner, alarmReportingIndicator, serviceState, expectedEquipmentObjectType, installedEquipmentObjectType, partNumber, versionNumber, serialNumber, additionalInfo);
      equipment.EquipmentOrHolder_T EquipmentOrHolder = new equipment.EquipmentOrHolder_T();
      EquipmentOrHolder.equip(equipment);
      return EquipmentOrHolder;
  }

  public static equipment.EquipmentOrHolder_T createEqOrHolder(String[] array)
  {   String name = array[0];
      String value = array[1];
      String userLabel = array[2];
      String ESMName = array[3];
      String owner = array[4];
      String type = array[5];
      String partNumber = array[6];
      String versionNumber = array[7];
      String serialNumber = array[8];
      // vengono creati solamente equipment e non holder
      println("creazione nuovo eq or holder " + array[0] +"...");
      globaldefs.NameAndStringValue_T[] nameValue = { new globaldefs.NameAndStringValue_T(name, value), new globaldefs.NameAndStringValue_T(name + "bis", value + "bis") };
      equipment.ServiceState_T serviceState = equipment.ServiceState_T.IN_SERVICE;
      globaldefs.NameAndStringValue_T[] additionalInfo = { new globaldefs.NameAndStringValue_T("manufacturer year", "1999"), new globaldefs.NameAndStringValue_T("manufacturer next year", "2000") };
      equipment.Equipment_T eq = new equipment.Equipment_T(nameValue, userLabel,ESMName, owner, false, serviceState, type, type, partNumber, versionNumber, serialNumber, additionalInfo);
      equipment.EquipmentOrHolder_T eqH = new equipment.EquipmentOrHolder_T();
      eqH.equip(eq);
      println("...completata correttamente");
      return eqH;
  }

  public static void println(String text)
  {   StartServer.getMonitorWindow().println(text);
      System.out.println(text);
  }
}