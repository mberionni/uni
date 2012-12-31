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

public class registraCommon extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/common:1.0" };
  protected String moduleName = "common";
  private static String interfaceName="Common_I";
  private static String interfaceVersion="1.0";

  public registraCommon()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione " + moduleName + "\n");
  }

  public static void main(String[] args)
  {  println("@ - avvio registrazione modulo common");
     //orb = ORB.init(args,null);
     //registraCommon regCommon = new registraCommon();
     //regCommon.registrazioneRepository();
     registrazioneRepository();
     println("@ - fine registrazione common");
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        try { //Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              println("Localizzato correttamente repository");
              ModuleDef common = ir.create_module(idRep[0],"common","1.0");
              println("- creato modulo common con id="+idRep[0]);
              String idAcc = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;
              InterfaceDef Common_I = common.create_interface(idAcc, interfaceName, "1.0", base_interfaces);
              println("- creata interfaccia con id="+Common_I.id()+" e nome="+Common_I.name());
              String ident = Common_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':'))+'/'+"Capability_T"+':'+Common_I.version();
              Contained c = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/NameAndStringValue_T:1.0");
              controlla(c);
              StructDef NameAndStringValue_T = StructDefHelper.narrow(c);
              AliasDef Capability_T = common.create_alias(ident, "Capability_T", "1.0", NameAndStringValue_T);
              Capability_T.original_type_def(NameAndStringValue_T);
              println("- creato alias con id = " + ident);
              SequenceDef Capability_TSequence = ir.create_sequence(0, Capability_T);
              Capability_TSequence.element_type_def(Capability_T);
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"CapabilityList_T"+':'+"1.0";
              AliasDef CapabilityList_T = common.create_alias(ident, "CapabilityList_T", "1.0", Capability_TSequence);
              CapabilityList_T.original_type_def(Capability_TSequence);
              println("- creato alias con id = " + ident);
              ParameterDescription parametri[] = new ParameterDescription[] { };
              ident = Common_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "getCapabilities" + ':' + Common_I.version();
              Common_I.create_operation(  ident, "getCapabilities", Common_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] {},
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);
              ident = Common_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "setNativeEmsName" + ':' + Common_I.version();
              Common_I.create_operation(  ident, "setNativeEmsName", Common_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] {},
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);
              ident = Common_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "setOwner" + ':' + Common_I.version();
              Common_I.create_operation(  ident, "setOwner", Common_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] {},
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);
              ident = Common_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "setUserLabel" + ':' + Common_I.version();
              Common_I.create_operation(  ident, "setUserLabel", Common_I.version(),
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

  public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}