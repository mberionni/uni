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
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import com.inprise.vbroker.naming.NamingUtil;
import com.inprise.vbroker.CosNamingExt.*;
import java.util.*;


public class DBImpl extends DBService.tabellaPOA
{ private tabImpl localTab;
  private String nome;
  private Cluster cluster;
  boolean faseUpdate = false;
  boolean faseRicerca = false;
  Frame4 frame;

  public DBImpl(String nomeTab, String nomeServer, Cluster _cluster)
  {   frame = new Frame4(nomeServer);
      frame.setVisible(true);
      nome = nomeServer;
      println("\nCreata nuova istanza DBServer di nome " + nome);
      createTab(nomeTab);
      print("La tabella ha il seguente contenuto:\n" + localTab.toString());
      cluster = _cluster;
      println("@ - Inizio fase unBind eventuali server caduti");
      unBindOldServer();  //questa fase può essere eliminata se si usa il rebind anziche bind al momento della registrazione al cluster nel naming server
      println("@ - Fine fase unBind");
      println("@ - Inizio fase update altri Server");
      aggiorna();
      println("@ - Fine fase update altri Server");
      println("Fine start-up del Server " + nome + "\n");
  }

  public DBImpl()
  {   println("creata nuova istanza DBServer - default constructor ");
  }

  public void createTab(String nome)
  {   localTab = new tabImpl(nome);
      println("creata in memoria tabella da file "+nome);
  }

  public  void aggiorna()
  { boolean notEmpty = true;
    Hashtable vector = new Hashtable();

    while (notEmpty )
      {   try
          {    org.omg.CORBA.Object obj = cluster.select();
               DBService.tabella remoteObj = DBService.tabellaHelper.narrow(obj);
               println("   Update - oggetto attualmente selezionato dal cluster "+remoteObj._object_name());
               remoteObj.faseUpdate(true);
               Thread.currentThread().sleep(3);
               if ( !(vector.containsKey(remoteObj._object_name())) )
               {   try
                   {    vector.put(remoteObj._object_name(), remoteObj);

                        for (Enumeration e = localTab.getKeys() ; e.hasMoreElements() ;)
                        {   String chiave = (String)e.nextElement();
                            String valoreLocale = localTab.getEntry(chiave);
                            String valoreRemoto = remoteObj.leggi(chiave);
                            println("   chiave="+ chiave+" valore remoto="+valoreRemoto+" valoreLocale="+valoreLocale);
                            if ( (!valoreRemoto.equals("no")) && (!valoreRemoto.equals(valoreLocale)) )
                              {   println("   il valore remoto " + valoreRemoto + " presente in " + remoteObj._object_name()+ " va aggiornato con "+valoreLocale);
                                  remoteObj.update(chiave, valoreLocale);
                              }
                        }
                    }  catch (Exception e)  {  e.printStackTrace();
                                            }
               } else notEmpty = false;
               remoteObj.faseUpdate(false);
           }  catch (Exception e)   {   println("   Il cluster e' attualmente vuoto"); //com.inprise.vbroker.CosNamingExt.Empty
                                                                     notEmpty = false;
                                                                 }
      }

   }

  public  void unBindOldServer()
  { Hashtable vector = new Hashtable();
    boolean notEmpty = true;

      while (notEmpty)
      {   try
          {    org.omg.CORBA.Object obj = cluster.select();
               DBService.tabella remoteObj = DBService.tabellaHelper.narrow(obj);
               String remN = remoteObj._object_name();
               println("   UnBind - oggetto attualmente selezionato dal cluster..."+remN);
               if ( !(vector.containsKey(remN)) )
               {   try
                   {   vector.put(remoteObj._object_name(), remoteObj);
                       if ( remoteObj._non_existent() )
                          {   NameComponent unB = new NameComponent(remN,"");
                              cluster.unbind( unB );
                              println("   il server e' caduto lo elimino dal cluster");
                          }
                       else  println("   il server e' ancora attivo");

                    }  catch (Exception e)  {  e.printStackTrace();
                                            }
               } else notEmpty = false;
           }  catch (com.inprise.vbroker.CosNamingExt.Empty e)   {   println("   Il cluster e' attualmente vuoto");
                                                                     notEmpty = false;
                                                                 }
      }

   }


  public String leggi(String key)
  {  if (faseRicerca) println ("Richiesta pervenuta da altro Server in fase di ricerca:");
     else
     if (faseUpdate) println ("Richiesta pervenuta da altro Server in fase di update:");
     else
     println("Richiesta locale:");

     print(" richiesta lettura entry con chiave "+ key+" ...");
     String ret = localTab.getEntry(key);
     if (ret == "no" && faseRicerca == false && faseUpdate == false)
            ret = cercaAltreTab(key);
     //else println(nome+": val non esistente o in faseRicerca o in faseUpdate");
     if (ret == "no")
           println(" entry non presente in tabella locale");
     else println(" entry presente con valore = "+ret);
     return ret;
  }

  public String cercaAltreTab(String key)
  {   boolean notEmpty = true;
      Hashtable vector = new Hashtable();

      println("L'entry non è presente nella mia tabella  => fase di ricerca in altre tab iniziata");
      while (notEmpty)
      {   try
          {    org.omg.CORBA.Object obj = cluster.select();
               DBService.tabella remoteObj = DBService.tabellaHelper.narrow(obj);
               String remN = remoteObj._object_name();
               println("AltreTab - oggetto attualmente selezionato dal cluster "+remN);
               Thread.currentThread().sleep(2000);
               remoteObj.faseRicerca(true);

               if ( !(vector.containsKey(remoteObj._object_name())) )
               {   try
                   {    vector.put(remN, remoteObj);
                        String remV = remoteObj.leggi(key);
                        if ( !remV.equals("no") )
                              {   println("  aggiungo alla cache locale l'entry " + key + " ---> " + remV + " presa da " + remN);
                                  localTab.setEntry(key, remV);
                                  println("  nuova tabella: " + localTab.stampaHashTable());
                                  remoteObj.faseRicerca(false);
                                  return remV;
                              } else println("  valore non presente nel server "+remoteObj._object_name());

                    }  catch (Exception e)  {  e.printStackTrace();
                                            }
               } else {   println("Fine scansione elementi cluster");
                          notEmpty = false;
                      }
               remoteObj.faseRicerca(false);
           }  catch (Exception e)   {   println(" Il cluster e' attualmente vuoto"); //com.inprise.vbroker.CosNamingExt.Empty
                                        e.printStackTrace();
                                        notEmpty = false;
                                    }
      }

      println("\n fase di ricerca in altre tab finita");
      return "no";
  }

  public void update(String key, String value)
  {  println("  sto aggiornando " + key +  " = " + localTab.getEntry(key) + " con valore remoto " + value);
     localTab.setEntry(key, value);
     println("  nuova tabella: " + localTab.stampaHashTable());

  }

  public boolean faseRicerca()
  {   return faseRicerca;
  }

  public void faseRicerca(boolean fr)
  {   faseRicerca = fr;
  }

  public boolean faseUpdate()
  {   return faseUpdate;
  }

  public void faseUpdate(boolean fr)
  {   faseUpdate = fr;
  }

  public void println(String testo)
  { frame.println(testo);
  }

  public void print(String testo)
  { frame.print(testo);
  }
}