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
import org.omg.CosNaming.*;
import com.inprise.vbroker.CosNamingExt.*;

public class registraDB
{ public static final String[] idRep =  new String[] { "IDL:DBService:1.0" };
  private static ORB orb;
  private static String interfaceName="tabella";
  private static String interfaceVersion="1.0";

  public static void main(String[] args)
  {   try
      {   System.out.println("@ - avvio registrazione DBService");
          orb = ORB.init(args,null);
          registrazioneRepository();
          registrazioneNameService();
          System.out.println("@ - fine registrazione DBService");
      } catch (Exception e) {   e.printStackTrace();
                            }
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] baseServices= {};

        try { Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository");

              ModuleDef moduleBank = ir.create_module(idRep[0],"DBService","1.0");
              System.out.println("- creato modulo DBService con id="+idRep[0]);
              String idAcc = idRep[0].substring(0,idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef accountDef= moduleBank.create_interface(idAcc,interfaceName,"1.0",baseServices);
              System.out.println("- creata interfaccia con id="+accountDef.id()+" e nome="+accountDef.name());
              IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
              //IDLType tipoStringa = ir.create_string(100); stringa di al max 100 chars
              ParameterDescription parametro =new ParameterDescription ("key",
                                                                        tipoStringa.type(),
                                                                        tipoStringa,
                                                                        ParameterMode.PARAM_IN);

              ParameterDescription parametri[]=new ParameterDescription[] {parametro};
              String ident = accountDef.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"leggi"+':'+accountDef.version();
              accountDef.create_operation( ident,"leggi",accountDef.version(),
                                           //ir.get_primitive(PrimitiveKind.pk_string),
                                           tipoStringa,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[]{},
                                           new String[]{}
                                           );
              System.out.println("- creata operazione con id="+ident);

             }
             catch (Exception e) { e.printStackTrace();
                                   System.out.println("errore "+e);}
  }

  private static void registrazioneNameService()
  {   try
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext rootCtx = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service " + idRep[0]);
          org.omg.CORBA.Object rootNS = orb.resolve_initial_references("NameService");
          NamingContextFactory factory = NamingContextFactoryHelper.bind(orb);
          ClusterManager clusterMgr = factory.get_cluster_manager();
          Cluster cluster = clusterMgr.create_cluster("RoundRobin");  // scelta della politica
          NameComponent[] nc = {new NameComponent(interfaceName, "")};
          rootCtx.bind(nc, cluster);
          System.out.println("Creato binding per il cluster nel naming service");
      }   catch (Exception e) {  e.printStackTrace();
                              }
  }


}
