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

public class registraBank
 { public static final String[] idRep =  new String[] {"IDL:BankService:1.0"};
   private static ORB orb;
   private static String interfaceManagerName="AccountManager";
   private static String interfaceName="Account";
   private static String interfaceVersion="1.0";

  public registraBank()
  {
  }

  public static void main(String[] args)
  {   try
      {   System.out.println("@ - avvio registrazione modulo BankService");
          orb = ORB.init(args, null);
          registrazioneRepository();
          registrazioneNameService();
          System.out.println("@ - fine registrazione");
      }
      catch (Exception e) {   e.printStackTrace();
                          }
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] baseServices= {};

        try { Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository");
              ModuleDef moduleBank = ir.create_module(idRep[0],"BankService","1.0");
              System.out.println("- creato modulo BankService con id="+idRep[0]);
              String idAccManager = idRep[0].substring(0,idRep[0].lastIndexOf(':'))+'/'+interfaceManagerName+':'+interfaceVersion;
              String idAcc = idRep[0].substring(0,idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;

              InterfaceDef accountManagerDef = moduleBank.create_interface(idAccManager,interfaceManagerName,interfaceVersion,baseServices);
              InterfaceDef accountDef = moduleBank.create_interface(idAcc,interfaceName,interfaceVersion,baseServices);
              System.out.println("- creata interfaccia con id="+accountDef.id()+" e nome="+accountDef.name());
              //IDLType tipoStringa = ir.create_string(100);
              IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
              String ident = accountDef.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "balance" + ':' + accountDef.version();
              accountDef.create_operation( ident, "balance", accountDef.version(),
                                           ir.get_primitive(PrimitiveKind.pk_float),
                                           OperationMode.OP_NORMAL,
                                           new ParameterDescription[]{},
                                           new ExceptionDef[]{},
                                           new String[]{});
              System.out.println("- creata operazione con id="+ident);

              ident = accountDef.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "describe" + ':' + accountDef.version();
              accountDef.create_operation( ident, "describe", accountDef.version(),
                                           //ir.get_primitive(PrimitiveKind.pk_string),
                                           tipoStringa,
                                           OperationMode.OP_NORMAL,
                                           new ParameterDescription[]{},
                                           new ExceptionDef[]{},
                                           new String[]{});
              System.out.println("- creata operazione con id="+ident);

              System.out.println("- creata interfaccia con id="+accountManagerDef.id()+" e nome="+accountManagerDef.name());
              ParameterDescription parametroManager = new ParameterDescription ("nomeAccount",
                                                                         tipoStringa.type(),
                                                                         tipoStringa,
                                                                         ParameterMode.PARAM_IN);
              ParameterDescription parametriManager[]=new ParameterDescription[] {parametroManager};
              ParameterDescription parametri[]=new ParameterDescription[] {};

              String identManager = accountManagerDef.id();
              ident = accountDef.id();
              identManager = identManager.substring(0,identManager.lastIndexOf(':'))+'/'+"open"+':'+accountManagerDef.version();
              accountManagerDef.create_operation( identManager,"open",accountManagerDef.version(),
                                           //IDLTypeHelper.narrow(ir.get_primitive(PrimitiveKind.pk_objref)), - analogo -
                                           //ir.get_primitive(PrimitiveKind.pk_objref)  - analogo -
                                           IDLTypeHelper.narrow(accountDef),
                                           OperationMode.OP_NORMAL,
                                           parametriManager,
                                           new ExceptionDef[]{},
                                           new String[]{}
                                           );
              System.out.println("- creata operazione con id="+identManager);
            }
             catch (Exception e) { e.printStackTrace();
                                   System.out.println("errore "+e);}
  }

  private static void registrazioneNameService()
  {   try
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service");
          NameComponent compManager= new NameComponent(interfaceManagerName, "");
          rootObj = orb.resolve_initial_references("RootPOA");
          System.out.println("Individuato correttamente RootPOA");
          POA rootPOA = POAHelper.narrow(rootObj);
          org.omg.CORBA.Policy[] policies = {   rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
                                                rootPOA.create_request_processing_policy(RequestProcessingPolicyValue.USE_SERVANT_MANAGER),
	                                    };
          POA myPOA = rootPOA.create_POA("bank_servant_activator_poa", rootPOA.the_POAManager(), policies );
          org.omg.CORBA.Object ref = myPOA.create_reference_with_id(interfaceManagerName.getBytes(), "IDL:BankService/AccountManager:1.0");
          nc.rebind(new NameComponent[] { compManager }, ref);
          System.out.println("Creato binding per oggetto "+interfaceManagerName+" nel naming service");
      }   catch (Exception e) {   e.printStackTrace();
                              }
  }

}