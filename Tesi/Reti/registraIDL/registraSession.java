package registraIDL;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.*;

public class registraSession extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/session:1.0" };
  protected String moduleName = "session";
  private static String interfaceName="Session_I";
  private static String interfaceVersion="1.0";

  public registraSession()
  {  println("@ - avvio registrazione modulo " + moduleName);
     registrazioneRepository();
     println("@ - fine registrazione nodule " + moduleName + "\n");
  }

  public static void main(String[] args)
  {  RegistrationClass rC = new RegistrationClass();
     rC.init(args);
     println("@ - avvio registrazione modulo Session");
     registraSession regSession = new registraSession();
     regSession.registrazioneRepository();
     println("@ - fine registrazione Session");
  }

  private void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        try { ModuleDef Session = ir.create_module(idRep[0],"session","1.0");
              println("- creato modulo session con id="+idRep[0]);
              String idAcc = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef Session_I = Session.create_interface(idAcc, interfaceName, "1.0", base_interfaces);
              println("- creata interfaccia con id="+Session_I.id()+" e nome="+Session_I.name());
              String ident = Session_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"associatedSession"+':'+Session_I.version();
              Session_I.create_attribute(ident, "associatedSession", "1.0", Session_I, AttributeMode.ATTR_READONLY);
              ParameterDescription parametri[] = new ParameterDescription[] { };
              ident = Session_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "ping" + ':' + Session_I.version();
              Session_I.create_operation(  ident, "ping", Session_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] {},
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);
              ident = Session_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "endSession" + ':' + Session_I.version();
              Session_I.create_operation(  ident, "endSession", Session_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] {},
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);

             }
             catch (Exception e) { e.printStackTrace();
                                   println("errore "+e);}
  }

}
