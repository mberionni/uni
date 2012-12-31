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

public class DeactivateThread extends Thread
{
  byte[] _oid;
  POA _adapter;

  public DeactivateThread(byte[] oid, POA adapter)
  { _oid = oid;
    _adapter = adapter;
  }

  public void run()
  {   try {   Thread.currentThread().sleep(10000);
              System.out.println("\nDeactivating the object with ID = " + new String(_oid) + "\n");
              _adapter.deactivate_object(_oid);
          }
      catch (Exception e)  {  e.printStackTrace();
                           }
  }
}