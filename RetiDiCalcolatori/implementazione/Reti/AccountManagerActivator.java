package Reti;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalitą avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

import org.omg.PortableServer.*;

public class AccountManagerActivator extends ServantActivatorPOA
{
  public Servant incarnate (byte[] oid, POA adapter) throws ForwardRequest
  { Servant servant;
    String accountType = new String(oid);
    System.out.println("\nAccountManagerActivator.incarnate called with ID = " +accountType + "\n");
    // Create Savings or Checking Servant based on AccountType
    servant = (Servant)new AccountManagerImpl(this._orb(), adapter);
    new DeactivateThread(oid, adapter).start();
    return servant;
  }

  public void etherealize (byte[] oid, POA adapter, Servant serv, boolean cleanup_in_progress, boolean remaining_activations)
  {  System.out.println("\nAccountManagerActivator.etherealize called with ID = " + new String(oid) + "\n");
     System.gc();
  }

}



