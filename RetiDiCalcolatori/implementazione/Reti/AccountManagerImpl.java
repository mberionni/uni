// AccountManagerImpl.java
package Reti;

import org.omg.CORBA.*;
import org.omg.CORBA.InterfaceDef.*;
import org.omg.CORBA.InterfaceDefOperations.*;
import java.util.Hashtable;
import java.util.Random;
import org.omg.PortableServer.*;


public class AccountManagerImpl extends org.omg.PortableServer.DynamicImplementation
 {  private Random _random = new Random();
    private ORB orb;
    private POA poa;
    private static Hashtable _registry = new Hashtable();
    public static final String[] ids =  new String[] { "IDL:BankService/AccountManager:1.0" };
    public static final String[] idsss =  new String[] { "IDL:BankService/Account:1.0" };



    public AccountManagerImpl(ORB _orb, POA _poa)
    {   // System.out.println("Attivazione dinamica");
        orb = _orb;
        poa = _poa;
    }

    public String[] _all_interfaces(POA poa, byte[] objectId)
    {   return ids;
    }

    public synchronized org.omg.CORBA.Object open(String name)
    {
        org.omg.CORBA.Object obj =  (org.omg.CORBA.Object) _registry.get(name);
        if (obj == null)
        {   try {   System.out.println("simulate the delay while creating the new account");
                    Thread.currentThread().sleep(1200);
                    float balance = new Float(Math.abs(_random.nextInt()) % 100000 / 100f).floatValue();
                    AccountImpl account = new AccountImpl(balance, orb, name);
                    byte[] accountId = name.getBytes();
                    obj = _default_POA().servant_to_reference(account);
                    System.out.println("Created " + name + "'s account: " + balance);
                    _registry.put(name, obj); //registra l'account come esistente nella tabella
                }
           catch (Exception e)  {   e.printStackTrace();
                                }
        }
        else
            System.out.println("Esiste già un account con il nome "+name);

        return obj; // Return object reference
  }


  public void invoke(org.omg.CORBA.ServerRequest request)
  {   // Ensure that the operation name is correct
      System.out.println("invocato metodo "+request.operation()+" in AccounManagerImpl");
      Float balance;
      String name = new String(_object_id());

      if (request.operation().equals("open"))
      {     org.omg.CORBA.NVList params = orb.create_list(1);
            org.omg.CORBA.Any any = orb.create_any();
            any.insert_string(new String(""));
            params.add_value("nomeFile", any, org.omg.CORBA.ARG_IN.value);
            request.arguments(params);
            try
            {   name = params.item(0).value().extract_string();
            }  catch (Exception e) {    System.out.println("ERRORE:");
                                        e.printStackTrace();
                                   }
            // Invoke the actual implementation and fill out the result
            org.omg.CORBA.Object account = open(name);
            org.omg.CORBA.Any result = orb.create_any();
            result.insert_Object(account);
            request.set_result(result);
      }
      else
      {   System.out.println("Errore nell'ivocazione dinamica del metodo open");
          throw new org.omg.CORBA.BAD_PARAM();
      }
  }

}
