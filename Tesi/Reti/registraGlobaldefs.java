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

public class registraGlobaldefs extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/globaldefs:1.0" };
  String moduleName = "globaldefs";
  private static String version="1.0";

  public registraGlobaldefs()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione modulo " + moduleName + "\n");
  }

  public static void main(String[] args)
  {  println("@ - avvio registrazione modulo globaldefs");
     //orb = ORB.init(args, null);
     //ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
     registrazioneRepository();
     println("@ - fine registrazione modulo globaldefs\n");
  }

  private static void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        try { println("Localizzato correttamente repository");
              ModuleDef Globaldefs = ir.create_module(idRep[0], "globaldefs", "1.0");
              println("- creato modulo equipment con id = " + idRep[0]);

              String ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"NameAndStringValue_T"+':'+"1.0";
              StructMember sm1 = new StructMember("name", tipoVoid.type(), tipoStringa);
              StructMember sm2 = new StructMember("value", tipoVoid.type(), tipoStringa);
              StructMember[] sms = new StructMember[] { sm1, sm2 };
              StructDef NameAndStringValue_T = Globaldefs.create_struct(ident, "NameAndStringValue_T", "1.0", sms);
              println("- creata struct con id = "+ ident +" con campi di tipo void");

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"NVSList_T"+':'+"1.0";
              SequenceDef NameAndStringValue_TSequence = ir.create_sequence(0, NameAndStringValue_T);
              NameAndStringValue_TSequence.element_type_def(NameAndStringValue_T);
              AliasDef NVSList = Globaldefs.create_alias(ident, "NVSList", "1.0", NameAndStringValue_TSequence);
              NVSList.original_type_def(NameAndStringValue_TSequence);
              println("- creato alias con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"NamingAttributes_T"+':'+"1.0";
              AliasDef NamingAttributes_T = Globaldefs.create_alias(ident, "NamingAttributes_T", "1.0", NVSList);
              NamingAttributes_T.original_type_def(NVSList);
              println("- creato alias con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"NamingAttributesList_T"+':'+"1.0";
              SequenceDef NamingAttributes_TSequence = ir.create_sequence(0, NamingAttributes_T);
              NamingAttributes_TSequence.element_type_def(NamingAttributes_T);
              AliasDef NamingAttributesList_T = Globaldefs.create_alias(ident, "NamingAttributesList_T", "1.0", NamingAttributes_TSequence);
              NamingAttributesList_T.original_type_def(NamingAttributes_TSequence);
              println("- creato alias con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"Time_T"+':'+"1.0";
              Globaldefs.create_alias(ident, "Time_T", "1.0", tipoStringa);
              println("- creato alias con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"ConnectionDirection_T"+':'+"1.0";
              String [] connectionTypes = new String [] { "CD_UNI", "CD_BD" };
              EnumDef ConnectionDirection_T = Globaldefs.create_enum(ident, "ConnectionDirection_T", "1.0", connectionTypes);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"ExceptionType_T"+':'+"1.0";
              String [] exceptionTypes = new String [] { "EXCPT_NOT_IMPLEMENTED", "EXCPT_INTERNAL_ERROR", "EXCPT_INVALID_INPUT", "EXCPT_OBJECT_IN_USE", "EXCPT_TP_INVALID_ENDPOINT", "EXCPT_ENTITY_NOT_FOUND", "EXCPT_TIMESLOT_IN_USE", "EXCPT_PROTECTION_EFFORT_NOT_MET", "EXCPT_NOT_IN_VALID_STATE", "EXCPT_UNABLE_TO_COMPLY", "EXCPT_NE_COMM_LOSS", "EXCPT_CAPACITY_EXCEEDED", "EXCPT_ACCESS_DENIED", "EXCPT_TOO_MANY_OPEN_ITERATORS", "EXCPT_UNSUPPORTED_ROUTING_CONSTRAINTS", "EXCPT_USERLABEL_IN_USE" };
              EnumDef ExceptionType_T = Globaldefs.create_enum(ident, "ExceptionType_T", "1.0", exceptionTypes);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"ProcessingFailureException"+':'+"1.0";
              sm1 = new StructMember("exceptionType", tipoVoid.type(), ExceptionType_T);
              sm2 = new StructMember("errorReason", tipoVoid.type(), tipoStringa);
              sms = new StructMember [] { sm1, sm2 };
              sms = new StructMember [] { sm1, sm2 };
              ExceptionDef excDef = Globaldefs.create_exception(ident, "ProcessingFailureException", "1.0", sms);
              ExceptionDef[] ex = { excDef };
              println("- creata eccezione con id = " + ident);
              // manca interface NamingAttributesIterator
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"NamingAttributesIterator_I"+':' + version;
              InterfaceDef NamingAttributesIterator_I = Globaldefs.create_interface(ident, "NamingAttributesIterator_I", version, base_interfaces);
              println("- creata interfaccia con id = " + ident);

              ParameterDescription parametro1 = new ParameterDescription ("how_many",
                                                                          tipoULong.type(),
                                                                          tipoULong,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro2 = new ParameterDescription ("nameList",
                                                                          NamingAttributesList_T.type(),
                                                                          NamingAttributesList_T,
                                                                          ParameterMode.PARAM_OUT);

              ParameterDescription parametri[] = new ParameterDescription[] { parametro1, parametro2 };
              ident = NamingAttributesIterator_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "next_n" + ':' + "1.0";
              NamingAttributesIterator_I.create_operation(  ident, "next_n", "1.0",
                                                       tipoBoolean,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       ex,
                                                       new String[]{}
                                                    );
              println("- creata operazione con id = " + ident);

              parametri = new ParameterDescription[] { };
              ident = NamingAttributesIterator_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "getLength" + ':' + "1.0";
              NamingAttributesIterator_I.create_operation(  ident, "getLength", "1.0",
                                                       tipoULong,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       ex,
                                                       new String[]{}
                                                    );
              println("- creata operazione con id="+ident);

              parametri = new ParameterDescription[] { };
              ident = NamingAttributesIterator_I.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "destroy" + ':' + "1.0";
              NamingAttributesIterator_I.create_operation(  ident, "destroy", "1.0",
                                                       tipoVoid,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       ex,
                                                       new String[]{}
                                                     );
              println("- creata operazione con id="+ident);
            }
             catch (Exception e) {   e.printStackTrace();
                                     println("errore "+e);
                                 }
  }

}
