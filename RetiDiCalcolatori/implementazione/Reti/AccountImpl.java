// AccountImpl.java
package Reti;

import java.util.*;
import org.omg.PortableServer.*;
import java.text.SimpleDateFormat;

public class AccountImpl extends DynamicImplementation
{  private static final String[] ids =  new String[] { "IDL:BankService/Account:1.0" };
   float _balance;
   private org.omg.CORBA.ORB _orb;
   private String _name, _created;
   private Date currentDate;
   private static SimpleDateFormat formatter = new SimpleDateFormat ("dd MMM yyyy - HH:mm", Locale.getDefault());


  public AccountImpl (float balance, org.omg.CORBA.ORB orb, String name)
  {    System.out.println("creato nuovo oggetto account con balance ="+balance);
       _balance = balance;
       _orb = orb;
       _name = name;
       currentDate = new Date();
       _created = formatter.format(currentDate);
  }

  public AccountImpl ()
  {   System.out.println("creato nuovo oggetto account con balance senza arg");

  }

  public float balance ()
  {   return _balance;
  }

  public synchronized void balance (float balance)
  {   _balance = balance;
  }

  public String[] _all_interfaces(POA poa, byte[] objectId)
  {  return ids;
  }

  public void invoke(org.omg.CORBA.ServerRequest request)
  {   if (request.operation().equals("balance"))
      {   org.omg.CORBA.NVList params = _orb.create_list(0);
          request.arguments(params);
          org.omg.CORBA.Any result = _orb.create_any();
          float res = balance();
          result.insert_float(res);
          request.set_result(result);
          System.out.println("Checked balance: " + _balance);
      }
      else
      if (request.operation().equals("describe"))
      {     org.omg.CORBA.NVList params = _orb.create_list(0);
            request.arguments(params);
            String desc = describe();
            org.omg.CORBA.Any result = _orb.create_any();
            result.insert_string(desc);
            request.set_result(result);
      }
      else throw new org.omg.CORBA.BAD_PARAM();
 }

  public String describe ()
  { String ret = "Account name:" + this._name + "\n" +
                 "Balance     :" + _balance + "\n" +
                 "Created     :" + _created + "\n" ;
    return ret;
  }

}
