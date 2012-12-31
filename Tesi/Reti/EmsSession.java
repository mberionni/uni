package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

/**A handle to an instance of this interface is gained via the getemsSession operation in EmsSessionFactory_I
*/


public class EmsSession extends emsSession.EmsSession_IPOA
{ public session.Session_I associatedSession;
  String [] managerList;
  ServerMonitorWindow mw;
/**This allows an NMS to gain access to the specified manager interface.
 * <pre>
 * void getSupportedManagers (out emsSession.EmsSession_IPackage.managerNames_T supportedManagerList)
  raises (globaldefs.ProcessingFailureException);
 * </pre>
 */

  public void getSupportedManagers (emsSession.EmsSession_IPackage.managerNames_THolder supportedManagerList) throws globaldefs.ProcessingFailureException
  {     println("Ricevuta richiesta getSupportedManagers");
        managerList = ServerData.getSupportedManagerList();
        supportedManagerList.value = managerList;
        println("metodo getSupportedManagers eseguito correttamente");
  }


/** Parameters:
* managerName: The class or type of manager object that the client wants (see getSupportedManagers).
* managerInterface: The actual object returned will implement the specified manager interface. However it is returned as a Common_I object so that this operation can be generic. The client should narrow the returned object to the correct object type.
* Attempting to gain access to the following manager interfaces may not raise EXCPT_NOT_IMPLEMENTED:
* EMS
* ManagedElement
* MultiLayerSubnetwork
* GuiCutThrough
*
* Raises:
* globaldefs::ProcessingFailureException
* EXCPT_NOT_IMPLEMENTED - Raised if the EMS does not support the manager
* EXCPT_INTERNAL_ERROR - Raised in case of non-specific EMS internal failure
* EXCPT_ACCESS_DENIED - Raised in case of security violation
*/

/**
 * <pre>
 * void getManager (in string managerName, out common.Common_I managerInterface)
  raises (globaldefs.ProcessingFailureException);
 * </pre>
 */

  public void getManager (java.lang.String managerName, common.Common_IHolder managerInterface) throws globaldefs.ProcessingFailureException
  {    if ( (managerName.compareTo("Maintenance")) == 0)
       {    println("chiamato metodo getManager - con arg maintenance");
            org.omg.CORBA.Object obj = ServerData.getMaintenanceMgrRef();
            managerInterface.value = common.Common_IHelper.narrow(obj);
            println("metodo eseguito correttamente");
       }
       else
       if ( (managerName.compareTo("EquipmentInventory")) == 0)
       {    println("chiamato metodo getManager - con arg equipment inventory -");
            org.omg.CORBA.Object obj = ServerData.getEquipmentInventoryMgrRef();
            managerInterface.value = common.Common_IHelper.narrow(obj);
            println("metodo eseguito correttamente");
       }
       else
       {    println("purtroppo non conosco questo manager. sigh!");
            throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_ENTITY_NOT_FOUND, "manager non trovato nel server");
       }

  }


/**
 * <pre>
 * void getEventChannel (out CosNotifyChannelAdmin.EventChannel eventChannel)
  raises (globaldefs.ProcessingFailureException);
 * </pre>
 */
    public void getEventChannel (CosNotifyChannelAdmin.EventChannelHolder eventChannel) throws globaldefs.ProcessingFailureException
    {   println("chiamto metodo hetEventChannel - metodo non implementato");
    }

    public session.Session_I associatedSession()
    {   println("EmsSession - chiamato metodo associatedSession");
        return associatedSession;
    }

    public void ping()
    {   println("EmsSession - chiamato metodo ping - implementazione nulla pausa");
     try {       Thread.currentThread().sleep(6000);
            } catch (Exception e){}
      println("EmsSession - cdopo pausa");
    }

    public void endSession()
    {   println("EmsSession - chiamato metodo endSession - implementazione nulla - pausa");
        try {       Thread.currentThread().sleep(6000);
            } catch (Exception e){}
      println("EmsSession - cdopo pausa");

    }

    public void println(String text)
    {   StartServer.getMonitorWindow().println(text);
        //System.out.println(text);
    }
}