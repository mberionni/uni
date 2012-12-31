package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.*;

public class registraNmsSession extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/nmsSession:1.0" };
  protected String moduleName = "nmsSession";
  private static String interfaceName="NmsSession_I";
  private static String interfaceVersion="1.0";

  public registraNmsSession()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione nmsSession " + moduleName + "\n");
  }

  public static void main(String[] args)
  {  println("@ - avvio registrazione modulo nmsSession");
     registraNmsSession regNmsSession = new registraNmsSession();
     regNmsSession.registrazioneRepository();//orb = ORB.init(args, null);
     //registrazioneRepository();
     println("@ - fine registrazione nmsSession");
  }

  private void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        ExceptionDef[] eccezioni = { };
      //  org.omg.CORBA.StructMember[] base_struct = { };
        try { //Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              println("Localizzato correttamente repository");
              ModuleDef nmsSession = ir.create_module(idRep[0],"nmsSession","1.0");
              println("- creato modulo ensSession con id="+idRep[0]);

              Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/session/Session_I:1.0");
              InterfaceDef session = InterfaceDefHelper.narrow(contained);
              controlla(contained);
              org.omg.CORBA.InterfaceDef[] base_interfaces2 = { session };

              contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/Time_T:1.0");
              controlla(contained);
              AliasDef Time = AliasDefHelper.narrow(contained);

              String ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef nmsSession_I = nmsSession.create_interface(ident, interfaceName, "1.0", base_interfaces2);
              println("- creata interfaccia con id = " + ident);

              ParameterDescription parametro1 = new ParameterDescription ("endTime",
                                                                          Time.type(),
                                                                          Time,
                                                                          ParameterMode.PARAM_IN);

              ParameterDescription parametri[] = new ParameterDescription[] { parametro1 };
              ident = nmsSession_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"eventLossCleared"+':'+nmsSession_I.version();

              nmsSession_I.create_operation(ident, "eventLossCleared", nmsSession_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           eccezioni,
                                           new String[]{}
                                         );
              println("- creata operazione con id = " + ident);

              ident = nmsSession_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':'))+'/'+"eventLossOccurred"+':'+nmsSession_I.version();
              parametro1 = new ParameterDescription                       ("startTime",
                                                                          tipoStringa.type(),
                                                                          tipoStringa,
                                                                          ParameterMode.PARAM_IN
                                                                          );
              ParameterDescription parametro2 = new ParameterDescription ("notificationId",
                                                                          Time.type(),
                                                                          Time,
                                                                          ParameterMode.PARAM_IN
                                                                          );
              parametri = new ParameterDescription[] { parametro1, parametro2 };
              nmsSession_I.create_operation(ident, "getManager", nmsSession_I.version(),
                                            tipoVoid,
                                            OperationMode.OP_NORMAL,
                                            parametri,
                                            eccezioni,
                                            new String[]{}
                                           );
              println("- creata operazione con id="+ident);

             }
             catch (Exception e) { e.printStackTrace();
                                   println("errore "+e);}
  }

  public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}
