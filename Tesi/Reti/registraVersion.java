package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.*;

public class registraVersion extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/version:1.0" };
  protected String moduleName = "version";
  private static String interfaceName="Version_I";
  private static String interfaceVersion="1.0";

  public registraVersion()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione modulo " + moduleName + "\n");
  }

  public static void main(String[] args)
  {  println("@ - avvio registrazione modulo Version");
     registraVersion regVersion = new registraVersion();
     regVersion.registrazioneRepository();//orb = ORB.init(args,null);
     //registrazioneRepository();
     println("@ - fine registrazione Version");
  }

  private void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        try { //Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              println("Localizzato correttamente repository");
              ModuleDef Session = ir.create_module(idRep[0],"version","1.0");
              println("- creato modulo session con id="+idRep[0]);
              String idAcc = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef Version_I = Session.create_interface(idAcc, interfaceName, "1.0", base_interfaces);
              println("- creata interfaccia con id="+Version_I.id()+" e nome="+Version_I.name());
              String ident = Version_I.id();
              ParameterDescription parametri[] = new ParameterDescription[] { };
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "getVersion" + ':' + Version_I.version();
              Version_I.create_operation(  ident, "getVersion", Version_I.version(),
                                           tipoStringa,
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
