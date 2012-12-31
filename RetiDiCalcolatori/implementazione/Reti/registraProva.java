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

public class registraProva
{ public static final String[] idRep =  new String[] { "IDL:ProvaService:1.0" };
  private static ORB orb;
  private static String interfaceName="somma";
  private static String interfaceVersion="1.0";

  public static void main(String[] args)
  {  System.out.println("@ - avvio registrazione ProvaService");
     orb = ORB.init(args,null);
     registrazioneRepository();
     registrazioneNameService();
     System.out.println("@ - fine registrazione ProvaService");
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] baseServices= {};

        try { Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository");

              ModuleDef moduleBank = ir.create_module(idRep[0],"ProvaService","1.0");
              System.out.println("- creato modulo PrintService con id="+idRep[0]);
              String idAcc = idRep[0].substring(0,idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef accountDef= moduleBank.create_interface(idAcc,interfaceName,"1.0",baseServices);
              System.out.println("- creata interfaccia con id="+accountDef.id()+" e nome="+accountDef.name());
              IDLType tipoLong = ir.get_primitive(PrimitiveKind.pk_long);
              ParameterDescription parametro1 = new ParameterDescription ("add1",
                                                                        tipoLong.type(),
                                                                        tipoLong,
                                                                        ParameterMode.PARAM_IN);
              ParameterDescription parametro2 = new ParameterDescription ("add2",
                                                                        tipoLong.type(),
                                                                        tipoLong,
                                                                        ParameterMode.PARAM_IN);

              IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
              ParameterDescription parametro3 = new ParameterDescription ("concat",
                                                                        tipoStringa.type(),
                                                                        tipoStringa,
                                                                        ParameterMode.PARAM_IN);

              ParameterDescription parametri[] = new ParameterDescription[] { parametro1, parametro2, parametro3 };
              String ident = accountDef.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"sommaConcatena"+':'+accountDef.version();
              accountDef.create_operation( ident,"sommaConcatena",accountDef.version(),
                                           tipoStringa,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[]{},
                                           new String[]{}
                                           );
              System.out.println("- creata operazione con id="+ident);

              ident = accountDef.id();
              parametro3 = new ParameterDescription ("mul",
                                                                        tipoLong.type(),
                                                                        tipoLong,
                                                                        ParameterMode.PARAM_IN);
              ParameterDescription parametro4 = new ParameterDescription ("div",
                                                                        tipoLong.type(),
                                                                        tipoLong,
                                                                        ParameterMode.PARAM_IN);
              ParameterDescription parametri2[] = new ParameterDescription[] { parametro1, parametro2, parametro3, parametro4 };

              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"calcola"+':'+accountDef.version();
              accountDef.create_operation( ident,"calcola",accountDef.version(),
                                           tipoLong,
                                           OperationMode.OP_NORMAL,
                                           parametri2,
                                           new ExceptionDef[]{},
                                           new String[]{}
                                           );
              System.out.println("- creata operazione con id="+ident);

              ident = accountDef.id();
              parametri[0].name = "concat1";
              parametri[1].name = "concat2";
              parametri[2].name = "concat3";
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"concatena"+':'+accountDef.version();
              accountDef.create_operation( ident,"concatena",accountDef.version(),
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
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("RootPOA");
          POA rootPOA = POAHelper.narrow(rootObj);
          rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service "+idRep[0]);
          NameComponent comp= new NameComponent(interfaceName, "");
          org.omg.CORBA.Policy[] policies = { rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT)  };
          // Create myPOA with the right policies
          POA myPOA = rootPOA.create_POA("prova_agent_poa", rootPOA.the_POAManager(), policies );
          org.omg.CORBA.Object ref = myPOA.create_reference_with_id(interfaceName.getBytes(), "IDL:ProvaService/concatena:1.0");
          nc.rebind(new NameComponent[] {comp}, ref);
          System.out.println("Creato binding per ogetto di nome "+interfaceName+" nel naming service");
      }   catch (Exception e) { e.printStackTrace();
                              }
  }

}
