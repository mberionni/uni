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
import java.util.*;
import java.io.*;

public class tabImpl
{   private java.util.Hashtable tabDB;

    public tabImpl(String nomefile)
    {   carica(nomefile);
    }

    public void carica(String nomefile)
    {	BufferedReader fin;
	String line=null;
	tabDB=new Hashtable();
	try{    fin= new BufferedReader(new FileReader(nomefile));
        	while((line=fin.readLine())!=null)
        	{   StringTokenizer riga = new StringTokenizer(line,"##");
                    String key = riga.nextToken();
                    String val=(new String(riga.nextToken()));
                    tabDB.put(key,val);
        	}
	        fin.close();
	   }    catch(IOException e)  {   System.out.println("Errore di apertura del file: "+e);
                                      }
  }

  public synchronized String toString()
  {     String ret="";
        for (Enumeration e = tabDB.keys() ; e.hasMoreElements() ;)
        {   String s = (String)e.nextElement();
            String i =(String)(tabDB.get(s));
            ret = ret + s +" ---> "+ i +"\n";
        }
        return ret;
  }

  public Enumeration getKeys()
  {   return tabDB.keys();
  }

  public Object getObj(String k)
  {   return tabDB.get(k);
  }

  // restituisce le lunghezza della hashtable
  public int lungh()
  {   return tabDB.size();
  }

  public String getEntry(Object chiave)
  {   String ret = (String)tabDB.get(chiave);
      if (ret == null)
          ret="no";
      return ret;
  }

  public void setEntry(Object chiave, Object valore)
  {   tabDB.put(chiave, valore);
      System.out.println("Update effettuato ---> nuova tabella:\n"+tabDB.toString());
  }

  public String stampaHashTable()
  { return tabDB.toString();
  }

}

