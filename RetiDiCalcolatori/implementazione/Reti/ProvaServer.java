package Reti;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */
import org.omg.PortableServer.*;

public class ProvaServer
{
  public static void main(String[] args)
  { try
          {   org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
              org.omg.CORBA.Object rootObj = orb.resolve_initial_references("RootPOA");
              System.out.println("Individuato correttamente RootPOA");
              POA rootPOA = POAHelper.narrow(rootObj);
              org.omg.CORBA.Policy[] policies = { rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT)  };
              // Create myPOA with the right policies
              POA myPOA = rootPOA.create_POA("prova_agent_poa", rootPOA.the_POAManager(), policies );
              ProvaImpl servant = new ProvaImpl();
              myPOA.activate_object_with_id("somma".getBytes(), servant);
              // Activate the POA manager
              rootPOA.the_POAManager().activate();
              System.out.println("--- Prova Server waiting for incoming request ---");
              orb.run();
          }  catch (Exception e)  {  e.printStackTrace();
                                  }
  }
}