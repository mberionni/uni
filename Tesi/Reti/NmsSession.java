package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

/**A handle to an instance of this interface is gained via the client parameter of the getemsSession operation in EmsSessionFactory_I
*/
import org.omg.PortableServer.POA;

public class NmsSession extends nmsSession.NmsSession_IPOA
{   session.Session_I associatedSession;

    public void eventLossCleared(String endTime)
    {   System.out.println("ping - non implementato");
    }

    public void eventLossOccurred(String startTime, String notificationId)
    {   System.out.println("ping - non implementato");
    }

    public session.Session_I associatedSession()
    {   System.out.println("restituisco associated session");
        return associatedSession;
    }

    public void ping()
    {   System.out.println("ping - non implementato");
    }

    public void endSession()
    {   System.out.println("endSession - non implementato");
    }
}