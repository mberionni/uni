package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

/**
* There is a single instance of the EmsSessionFactory_I. It is the entry point to the Server. This is the object reference that the client uses to connect to the server.
* This interface implements the version interface and will return the server idl version.
*/

public class EmsSessionFactory extends emsSessionFactory.EmsSessionFactory_IPOA //implements emsSessionFactory.EmsSessionFactory_I
{ private String password = "michele";


/** This operation allows the NMS to obtain the EmsSession_I object from which all managers of the EMS can be obtained.
*/
  public void getEmsSession (java.lang.String user,
                             java.lang.String password,
                             nmsSession.NmsSession_I client,
                             emsSession.EmsSession_IHolder emsSessionInterface) throws globaldefs.ProcessingFailureException
  {   if (!controlloAccesso(user, password)) throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_ACCESS_DENIED, "gasp! Password errata - try again -");
      try
      {     org.omg.CORBA.Object obj = ServerData.getEmsSessionRef();
            EmsSession EmsS = ServerData.getEmsSession();
            emsSession.EmsSession_I emsS = emsSession.EmsSession_IHelper.narrow(obj);
            EmsS.associatedSession = client;
            emsSessionInterface.value = emsS;
            println("chiamata getEmsSession - metodo eseguito");
      }     catch (Exception e) { e.printStackTrace();
                                }
  }

/**Parameters:
* user: The user or application that is trying to access the server.
* password: The password of the user. Can be empty string.
* client: A handle to the NMS Session_I object, to which the returned EmsSession_I object has to be associated.
* emsSessionInterface: It is a CORBA IOR for the EmsSession_I interface.
* Raises:
* globaldefs::ProcessingFailureException
* EXCPT_INTERNAL_ERROR - Raised in case of non-specific EMS internal failure
* EXCPT_ACCESS_DENIED - Raised in case of security violation
*/

  private boolean controlloAccesso(String user, String psw)
  {  if (psw.compareTo(password) != 0)
     {    println("Accesso negato - password errata -");
          return false;
     }
     else return true;
  }

  public String getVersion()
  {  return this.getVersion();
  }

  public void println(String text)
  {   //System.out.println(text);
      StartServer.getMonitorWindow().println(text);
  }

}