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

 public class PrintServer
 {
    public static void main(String[] args)
    {     try
          {   org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
              org.omg.CORBA.Object rootObj = orb.resolve_initial_references("RootPOA");
              POA rootPOA = POAHelper.narrow(rootObj);
              org.omg.CORBA.Policy[] policies = {  rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
                                                   rootPOA.create_request_processing_policy(RequestProcessingPolicyValue.USE_SERVANT_MANAGER),
	                                        };
              POA myPOA = rootPOA.create_POA("print_servant_activator_poa", rootPOA.the_POAManager(),policies );
              // Create the servant activator servant and get its reference
              ServantActivator sa = new PrintManagerActivator()._this(orb);
              System.out.println("creato print servant activator");
              // Set the servant activator on our POA
              myPOA.set_servant_manager(sa);
              // Activate the POA manager
              rootPOA.the_POAManager().activate();
              System.out.println("--- Print Server waiting for incoming request ---");
              orb.run();
          }   catch (Exception e) {   e.printStackTrace();
                                  }
    }
}