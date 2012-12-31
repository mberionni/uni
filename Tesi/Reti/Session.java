package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

/**The Session_I interface provides capabilities to manage the client-server connection.
 * Its main purpose is to enable either a client or server to detect the loss of communication with the associated party.
 * For a single communication session between an NMS and an EMS, there are two Session_I objects. One is maintained on the NMS; the other one is maintained on the EMS. The Session_I object maintained on the EMS is actually an EmsSession_I (that inherits from Session_I).
 * Each Session_I object is responsible to "ping" the other Session_I object periodically to detect communication failures. Exactly when this is done is up to the implementation.
 * When a Session_I object detects a communication failure, or when the endSession operation is called on it, all resources allocated with that communication session must be freed and the Session_I object must be deleted.
 */
import org.omg.PortableServer.*;

public class Session extends session.Session_IPOA
{ /** This attribute contains a reference to the Session_I on the other side (NMS/EMS) to which the object
  * is associated.  This attribute can be checked to make sure the Session_I/EmsSession_I association is still valid
  * (in particular in case of communication failures).
  */
  public session.Session_I associatedSession;


  public session.Session_I associatedSession()
  {  return associatedSession;
  }

   /** Allows for the detection of loss of communication.
   *  It is implementation specific to differenciate intermittent problems from loss of connection.
   */
  public void ping()
  {  System.out.println("ping - devo individuare se la connessione con associated session è attiva");
  }

  /** Allows for a controlled disconnect between parties.
   *  All resources allocated for the session are deleted by operation.
   */
  public void endSession()
  {  System.out.println("La sessione è finita. andate in pace");

  }


}