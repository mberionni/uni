package Reti;

import java.awt.*;
import java.awt.print.*;
import java.io.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class printService extends org.omg.PortableServer.DynamicImplementation
{ private ORB orb;
  private POA poa;
  public static final String[] idRep =  new String[] { "IDL:Print:1.0" };

  public printService(ORB _orb, POA _poa)
  {  orb = _orb;
     poa = _poa;
  }

   public printService()
   {
   }

   public String[] _all_interfaces(POA poa, byte[] objectId)
   {   return idRep;
   }

   public boolean stampa(String fileName)
   {  boolean goal=true;

     try
     {    File file = new File(fileName);
          System.out.println("Opening: " + fileName + "." + "\n");
          int size = (int)file.length();
          int chars_read = 0;
          FileReader in = new FileReader(file);
          char [] data = new char[size];
          while(in.ready())
          {   chars_read += in.read(data, chars_read, size - chars_read);
          }
          String tot = new String(data, 0, chars_read);
          PrinterJob job = PrinterJob.getPrinterJob();
          Book bk = new Book();
          PageFormat pf = job.defaultPage();
          bk.append(new toPrint(tot), pf, 1);
          job.setPageable(bk);
          if (job.printDialog())
          {  try { job.print();
                 }
             catch (Exception ex) {  System.out.println("ERRORE: "+ex);
                                     return false;
                                  }
          }
    } catch(Exception e) {System.out.println("ERRORE:"+e);
                          return false;}
    return goal;
  }


 public void invoke(org.omg.CORBA.ServerRequest request)
  {   boolean goal = true;
      System.out.println("invocato metodo "+request.operation()+" in printService");
      if (!request.operation().equals("stampa"))
      {   System.out.println("l'oggetto puo' accettare solo richieste di nome 'stampa'");
          goal = false;
          throw new org.omg.CORBA.BAD_OPERATION();
       }
      String path = null;
      try {   org.omg.CORBA.NVList params = orb.create_list(1);
              org.omg.CORBA.Any any = orb.create_any();
              any.insert_string(new String(""));
              params.add_value("name", any, org.omg.CORBA.ARG_IN.value);
              request.arguments(params);
              path = params.item(0).value().extract_string();
          }
      catch (Exception e) { System.out.println("ERRORE:  "+e);
                            goal = false;
                            throw new org.omg.CORBA.BAD_PARAM();

                          }
      if (goal==true)
      {   // Invoke the actual implementation and fill out the result
          goal = stampa(path);
      }
      org.omg.CORBA.Any result = orb.create_any();
      result.insert_boolean(goal);
      request.set_result(result);
  }
}
