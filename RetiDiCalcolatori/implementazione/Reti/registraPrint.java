/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */
package Reti;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class registraPrint
{ public static final String[] idRep =  new String[] { "IDL:PrintService:1.0" };
  private static ORB orb;
  private static String interfaceName="Print";
  private static String interfaceVersion="1.0";

  public static void main(String[] args)
  {  System.out.println("@ - avvio registrazione modulo PrintService");
     orb = ORB.init(args,null);
     registrazioneRepository();
     registrazioneNameService();
     System.out.println("@ - fine registrazione PrintService");
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] baseServices= {};

        try { Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository");
              ModuleDef moduleBank = ir.create_module(idRep[0],"PrintService","1.0");
              System.out.println("- creato modulo PrintService con id="+idRep[0]);
              String idAcc = idRep[0].substring(0,idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef accountDef= moduleBank.create_interface(idAcc,interfaceName,"1.0",baseServices);
              System.out.println("- creata interfaccia con id="+accountDef.id()+" e nome="+accountDef.name());
              //IDLType tipoStringa = ir.create_string(100);
              IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
              ParameterDescription parametro =new ParameterDescription ("nomeFile",
                                                                        tipoStringa.type(),
                                                                        tipoStringa,
                                                                        ParameterMode.PARAM_IN);

              ParameterDescription parametri[]=new ParameterDescription[] {parametro};
              String ident = accountDef.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"stampa"+':'+accountDef.version();
              accountDef.create_operation( ident,"stampa",accountDef.version(),
                                           ir.get_primitive(PrimitiveKind.pk_boolean),
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
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("RootPOA");
          POA rootPOA = POAHelper.narrow(rootObj);
          rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service "+idRep[0]);
          NameComponent comp= new NameComponent(interfaceName, "");

          Policy[] policies = {  rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
                                 rootPOA.create_request_processing_policy(RequestProcessingPolicyValue.USE_SERVANT_MANAGER),
	                      };
          POA myPOA = rootPOA.create_POA("print_servant_activator_poa", rootPOA.the_POAManager(),policies );
          // Create the servant activator servant and get its reference
          org.omg.CORBA.Object ref = myPOA.create_reference_with_id(interfaceName.getBytes(), "IDL:PrintService/print:1.0");
          nc.rebind(new NameComponent[] {comp}, ref);
          System.out.println("Creato binding per oggetto "+interfaceName+" nel naming service");
      }   catch (Exception e) { e.printStackTrace();
                              }
  }

}
