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
import org.omg.CosNaming.*;
import com.inprise.vbroker.CosNamingExt.*;
import com.inprise.vbroker.PortableServerExt.BIND_SUPPORT_POLICY_TYPE;
import com.inprise.vbroker.PortableServerExt.BindSupportPolicyValue;
import com.inprise.vbroker.PortableServerExt.BindSupportPolicyValueHelper;

public class TabellaServer
{ private org.omg.CORBA.ORB _orb;
  private Cluster _cluster;

  public static void main(String[] args)
  {   if (args.length < 1 || args.length > 2)
          {   System.out.println("Usage: TabellaServer [ NomeServer ] { NomeTabellaLocale }");
              System.exit(1);
          }
      TabellaServer tabS = new TabellaServer();
      tabS.inizio(args);

  }

 public void inizio(String[] args)
 {    _orb = org.omg.CORBA.ORB.init(args, null);
      _cluster = findCluster();
      DBService.tabella tabella = registrazionePOA(args[0], args[1]);
      bindCluster(tabella, args[0]);
      System.out.println("\n System waiting for incoming requests...");
      _orb.run();
 }

 public Cluster findCluster()
 {  try
      {   org.omg.CORBA.Object rootNS = _orb.resolve_initial_references("NameService");
          NamingContext rootCtx = NamingContextHelper.narrow(rootNS);
          System.out.println("Localizzato correttamente naming service");
          NamingContextFactory factory = NamingContextFactoryHelper.bind(_orb);
          ClusterManager clusterMgr = factory.get_cluster_manager();
          NameComponent[] nc = {new NameComponent("tabella", "")};
          Cluster cluster = clusterMgr.find_cluster(rootCtx, nc);
          //org.omg.CORBA.Object clusterObj = rootCtx.resolve(nc);
          //Cluster cluster = ClusterHelper.narrow(clusterObj);
          System.out.println("Localizzato correttamente cluster con nome tabella nel Naming Service");
          return cluster;
      }   catch (Exception e) {   System.out.println("ERRORE:");
                                  e.printStackTrace();
                                  return null;
                              }
 }

  public DBService.tabella registrazionePOA(String _nomeServer, String _nomeFileTabella)
  {   try
      {   org.omg.CORBA.Object rootObj = _orb.resolve_initial_references("RootPOA");
          POA rootPOA = POAHelper.narrow(rootObj);
          org.omg.CORBA.Any any = _orb.create_any();
          // Create a BindSupport Policy that makes POA register each servant with osagent
          BindSupportPolicyValueHelper.insert(any, BindSupportPolicyValue.BY_INSTANCE);
          // Create policies for our testPOA
          org.omg.CORBA.Policy[] policies = {    rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
                                                 _orb.create_policy(BIND_SUPPORT_POLICY_TYPE.value, any)
                                            };
          POA myPOA = rootPOA.create_POA("DB_agent_poa", rootPOA.the_POAManager(), policies );
          // Create the servant
          DBImpl managerServant = new DBImpl(_nomeFileTabella, _nomeServer, _cluster);
          // Decide on the ID for the servant
          byte[] managerId = _nomeServer.getBytes();
          // Activate the servant with the ID on myPOA
          myPOA.activate_object_with_id(managerId, managerServant);
          DBService.tabella tab = DBService.tabellaHelper.narrow(myPOA.servant_to_reference(managerServant));
          // Activate the POA manager
          rootPOA.the_POAManager().activate();
          return tab;
          } catch (Exception e) {   System.out.println("ERRORE:");
                                    e.printStackTrace();
                                    return null;
                                }
}

  public void bindCluster(DBService.tabella _tabella, String _nomeServer)
  {   try
      {   NameComponent nc = new NameComponent(_nomeServer, "");
          _cluster.bind(nc, _tabella);
          System.out.println("Legata istanza " + _nomeServer + " al cluster");
      }   catch (Exception e) {   System.out.println("ERRORE:");
                                  e.printStackTrace();
                              }
  }

}