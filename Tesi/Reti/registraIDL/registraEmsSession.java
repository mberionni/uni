package registraIDL;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.*;
import org.omg.CORBA.InterfaceDefPackage.FullInterfaceDescription;

public class registraEmsSession extends RegistrationClass
{ protected static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/emsSession:1.0" };
  protected String moduleName = "emsSession";
  protected static String interfaceName="EmsSession_I";
  protected static String interfaceVersion="1.0";

   public registraEmsSession()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione " + moduleName + "\n");
  }


  public static void main(String[] args)
  {  RegistrationClass rC = new RegistrationClass();
     rC.init(args);
     registraEmsSession regEmsSession = new registraEmsSession();
     println("@ - avvio registrazione modulo emsSession");
     regEmsSession.registrazioneRepository();
     println("@ - fine registrazione emsSession");
  }

  protected void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_int = { };
        org.omg.CORBA.StructMember[] base_struct = { };
        try { ModuleDef emsSession = ir.create_module(idRep[0],"emsSession","1.0");
              println("- creato modulo emsSession con id="+idRep[0]);

              Contained c = ir.lookup_id("IDL:mtnm.tmforum.org/session/Session_I:1.0");
              InterfaceDef session = InterfaceDefHelper.narrow(c);
              controlla(c);
              org.omg.CORBA.InterfaceDef[] bas = new org.omg.CORBA.InterfaceDef[1];

              bas[0] = session;
              c = ir.lookup_id("IDL:mtnm.tmforum.org/common/Common_I:1.0");
              InterfaceDef common = InterfaceDefHelper.narrow(c);
              controlla(c);
              String ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef emsSession_I = emsSession.create_interface(ident, interfaceName, "1.0", bas);
              emsSession_I.base_interfaces(bas);
              ident = ident.substring(0, ident.lastIndexOf(':'))+'/'+"managerNames_T"+':'+emsSession_I.version();
              SequenceDef stringSequence = ir.create_sequence(0, tipoStringa);
              stringSequence.element_type_def(tipoStringa);
              AliasDef managerNames_T = emsSession_I.create_alias(ident, "managerNames_T", "1.0", stringSequence);
              managerNames_T.original_type_def(stringSequence);
              println("- creato alias con id = " + ident);
              ParameterDescription parametro1 = new ParameterDescription ("supportedManagerList",
                                                                          managerNames_T.type(),
                                                                          managerNames_T,
                                                                          ParameterMode.PARAM_OUT);

              ParameterDescription parametri[] = new ParameterDescription[] { parametro1 };
              ident = emsSession_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"getSupportedManagers"+':'+emsSession_I.version();

              c = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/ProcessingFailureException:1.0");
              controlla(c);
              ExceptionDef [] eccezione = new ExceptionDef[] { ExceptionDefHelper.narrow(c) };
              emsSession_I.create_operation(ident, "getSupportedManagers", emsSession_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           eccezione,
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);

              ident = emsSession_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"getManager"+':'+emsSession_I.version();
              parametro1 = new ParameterDescription                       ("managerName",
                                                                          tipoStringa.type(),
                                                                          tipoStringa,
                                                                          ParameterMode.PARAM_IN
                                                                          );
              ParameterDescription parametro2 = new ParameterDescription ("managerInterface",
                                                                          common.type(),
                                                                          common,
                                                                          ParameterMode.PARAM_OUT
                                                                          );
              parametri = new ParameterDescription[] { parametro1, parametro2 };
              emsSession_I.create_operation(ident, "getManager", emsSession_I.version(),
                                            tipoVoid,
                                            OperationMode.OP_NORMAL,
                                            parametri,
                                            eccezione,
                                            new String[]{}
                                           );
              println("- creata operazione con id="+ident);

              ident = emsSession_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"getEventChannel"+':'+emsSession_I.version();
              parametri = new ParameterDescription[] { };
              emsSession_I.create_operation(ident, "getEventChannel", emsSession_I.version(),
                                            tipoVoid,
                                            OperationMode.OP_NORMAL,
                                            parametri,
                                            eccezione,
                                            new String[]{}
                                            );
              println("- creata operazione con id="+ident);
            }
            catch (Exception e) {  e.printStackTrace();
                                   println("errore "+e); }
  }

  public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}
