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

public class registraEquipment extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/equipment:1.0" };
  private static String interfaceVersion = "1.0";

  public registraEquipment()
  {  println("@ - avvio registrazione modulo equipment");
     registrazioneRepository();
     println("@ - fine registrazione equipment");
  }

  public static void main(String[] args)
  {  RegistrationClass rC = new RegistrationClass();
     rC.init(args);
     println("@ - avvio registrazione modulo equipment");
     registrazioneRepository();
     println("@ - fine registrazione equipment");
  }

  private static void registrazioneRepository()
  {   try {   ModuleDef Eqm = ir.create_module(idRep[0], "equipment", "1.0");
              println("- creato modulo equipment con id=" + idRep[0]);

              String ident = idRep[0].substring(0, idRep[0].lastIndexOf(':')) + '/' + "ServiceState_T" + ':' + "1.0";
              EnumDef ServiceState_T = Eqm.create_enum(ident, "ServiceState_T", "1.0", new String[] { "IN_SERVICE", "OUT_OF_SERVICE", "OUT_OF_SERVICE_BY_MAINTENANCE", "SERV_NA" } );
              println("- creata enum con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':')) + '/' + "EquipmentHolderType_T" + ':' + "1.0";
              AliasDef EquipmentHolderType_T = Eqm.create_alias(ident, "EquipmentHolderType_T", "1.0", tipoStringa);
              EquipmentHolderType_T.original_type_def(tipoStringa);
              println("- creata alias con id = " + ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':')) + '/' + "HolderState_T" + ':' + "1.0";
              EnumDef HolderState_T = Eqm.create_enum(ident, "HolderState_T", "1.0", new String[] {"EMPTY", "INSTALLED_AND_EXPECTED", "EXPECTED_AND_NOT_INSTALLED", "INSTALLED_AND_NOT_EXPECTED", "MISMATCH_OF_INSTALLED_AND_EXPECTED", "UNAVAILABLE", "UNKNOWN"} );

              Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/NamingAttributes_T:1.0");
              controlla(contained);
              AliasDef NamingAttributes_T = AliasDefHelper.narrow(contained);


              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentObjectType_T"+':'+"1.0";
              AliasDef EquipmentObjectType_T = Eqm.create_alias(ident, "EquipmentObjectType_T", "1.0", tipoStringa);
              EquipmentObjectType_T.original_type_def(tipoStringa);
              println("- creata alias con id = "+ident);

              SequenceDef EquipmentObjectType_TSequence = ir.create_sequence(0, EquipmentObjectType_T);
              EquipmentObjectType_TSequence.element_type_def(EquipmentObjectType_T);
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentObjectTypeList_T"+':'+"1.0";
              AliasDef EquipmentObjectTypeList_T = Eqm.create_alias(ident, "EquipmentObjectTypeList_T", "1.0",  EquipmentObjectType_TSequence);
              EquipmentObjectTypeList_T.original_type_def(EquipmentObjectType_TSequence);
              println("- creata alias con id = " + ident);

              contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/NVSList_T:1.0");
              controlla(contained);
              AliasDef NVSList_T = AliasDefHelper.narrow(contained);


              StructMember sm1 = new StructMember("name", tipoVoid.type(), NamingAttributes_T);
              StructMember sm2 = new StructMember("userLabel", tipoVoid.type(), tipoStringa);
              StructMember sm3 = new StructMember("NativeEMSName", tipoVoid.type(), tipoStringa);
              StructMember sm4 = new StructMember("owner", tipoVoid.type(), tipoStringa);
              StructMember sm5 = new StructMember("alarmReportingIndicator", tipoVoid.type(), tipoBoolean);
              StructMember sm6 = new StructMember("serviceState", tipoVoid.type(), ServiceState_T);
              StructMember sm7 = new StructMember("expectedEquipmentObjectType", tipoVoid.type(), EquipmentObjectType_T);
              StructMember sm8 = new StructMember("installedEquipmentObjectType", tipoVoid.type(), EquipmentObjectType_T);
              StructMember sm9 = new StructMember("installedPartNumber", tipoVoid.type(), tipoStringa);
              StructMember sm10 = new StructMember("installedVersion", tipoVoid.type(), tipoStringa);
              StructMember sm11 = new StructMember("installedSerialNumber", tipoVoid.type(), tipoStringa);
              StructMember sm12 = new StructMember("additionalInfo", tipoVoid.type(), NVSList_T);

              StructMember[] sms =  new StructMember[] { sm1, sm2, sm3, sm4, sm5, sm6, sm7, sm8, sm9, sm10, sm11, sm12  };

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"Equipment_T"+':'+"1.0";
              StructDef structEquipment = Eqm.create_struct(ident, "Equipment_T", "1.0", sms);
              println("- creata struct con id = " + ident);

              sm6 = new StructMember("forceUniqueness", tipoVoid.type(), tipoBoolean);
              sm8 = new StructMember("equipmentHolderName", tipoVoid.type(), NamingAttributes_T);
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EQTCreateData_T"+':'+"1.0";
              StructMember[] sms1 = new StructMember[] { sm2, sm6, sm4, sm7, sm8 , sm12 };
              StructDef structEQT = Eqm.create_struct(ident, "EQTCreateData_T", "1.0", sms1 );
              println("- creata struct con id = "+ident);

              sm6 = new StructMember("holderType", tipoVoid.type(), EquipmentHolderType_T);
              sm7 = new StructMember("expectedOrInstalledEquipment", tipoVoid.type(), NamingAttributes_T);
              sm8 = new StructMember("acceptableEquipmentTypeList", tipoVoid.type(), EquipmentObjectTypeList_T);
              sm9 = new StructMember("holderState", tipoVoid.type(), HolderState_T);
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentHolder_T"+':'+"1.0";
              StructMember[] sms2 = new StructMember[] { sm1, sm2, sm3, sm4, sm5, sm6, sm7, sm8, sm9, sm12 };
              StructDef structEQTHolder = Eqm.create_struct(ident, "EquipmentHolder_T", "1.0", sms2 );
              println("- creata struct con id = "+ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentTypeQualifier_T"+':'+"1.0";
              EnumDef EquipmentTypeQualifier_T = Eqm.create_enum(ident, "EquipmentTypeQualifier_T", "1.0", new String[] { "EQT", "EQT_HOLDER" } );
              println("- creata enum con id = "+ident);

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentOrHolder_T"+':'+"1.0";
              Any any = orb.create_any();
              any.insert_string("EQT");
              UnionMember u1 = new UnionMember ("equip", any, tipoVoid.type(), structEquipment);

              Any any2 = orb.create_any();
              any2.insert_string("EQT_HOLDER");
              UnionMember u2 = new UnionMember ("holder", any2, tipoVoid.type(), structEQTHolder);
              UnionMember [] union = { u1, u2 };

              UnionDef EquipmentOrHolder_T = Eqm.create_union(ident, "EquipmentOrHolder_T", "1.0", EquipmentTypeQualifier_T, union);
              EquipmentOrHolder_T.discriminator_type_def(EquipmentTypeQualifier_T);

              println("- creata union con id = "+ident);

              SequenceDef EquipmentOrHolder_TSequence = ir.create_sequence(0, EquipmentOrHolder_T);
              EquipmentOrHolder_TSequence.element_type_def(EquipmentOrHolder_T);


              // se invece che tipo stringa metto EquipmentOrHolder viene generato un errore di marshalling !!
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentOrHolderList_T"+':'+"1.0";
              AliasDef EquipmentOrHolderList_T = Eqm.create_alias(ident, "EquipmentOrHolderList_T", "1.0", EquipmentOrHolder_TSequence);
              EquipmentOrHolderList_T.original_type_def(EquipmentOrHolder_TSequence);
              println("- creata alias con id = "+ident);

              InterfaceDef[] base_interfaces = {};
              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentOrHolderIterator_I"+':'+interfaceVersion;
              InterfaceDef EquipOrHolderIterator = Eqm.create_interface(ident, "EquipmentOrHolderIterator_I", interfaceVersion, base_interfaces);
              println("- creata interfaccia con id = " + ident);

              contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/ProcessingFailureException:1.0");
              controlla(contained);
              ExceptionDef [] exc = new ExceptionDef[] { ExceptionDefHelper.narrow(contained) };

              ParameterDescription parametro1 = new ParameterDescription ("how_many",
                                                                          tipoLong.type(),
                                                                          tipoLong,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro2 = new ParameterDescription ("equipmentOrHolderList",
                                                                          tipoVoid.type(),
                                                                          EquipmentOrHolderList_T,
                                                                          ParameterMode.PARAM_OUT);

              ParameterDescription parametri[] = new ParameterDescription[] { parametro1, parametro2 };
              ident = EquipOrHolderIterator.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "next_n" + ':' + "1.0";
              EquipOrHolderIterator.create_operation(  ident, "next_n", "1.0",
                                                       tipoBoolean,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       exc,
                                                       new String[]{}
                                                    );
              println("- creata operazione con id="+ident);

              parametri = new ParameterDescription[] { };
              ident = EquipOrHolderIterator.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "getLength" + ':' + "1.0";
              EquipOrHolderIterator.create_operation(  ident, "getLength", "1.0",
                                                       tipoULong,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       exc,
                                                       new String[]{}
                                                    );
              println("- creata operazione con id="+ident);

              parametri = new ParameterDescription[] { };
              ident = EquipOrHolderIterator.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "destroy" + ':' + "1.0";
              EquipOrHolderIterator.create_operation(  ident, "destroy", "1.0",
                                                       tipoVoid,
                                                       OperationMode.OP_NORMAL,
                                                       parametri,
                                                       exc,
                                                       new String[]{}
                                                     );
              println("- creata operazione con id="+ident);

              contained = ir.lookup_id("IDL:mtnm.tmforum.org/common/Common_I:1.0");
              controlla(contained);
              InterfaceDef common = InterfaceDefHelper.narrow(contained);

              InterfaceDef [] base_interfaces2 = { common };

              ident = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+"EquipmentInventoryMgr_I"+':'+interfaceVersion;
              InterfaceDef EquipInventory = Eqm.create_interface(ident, "EquipmentInventoryMgr_I", "1.0", base_interfaces2);
              println("- creata interfaccia con id="+ident);

              parametro1 = new ParameterDescription                       ("equipmentCreateData",
                                                                          structEQT.type(),
                                                                          structEQT,
                                                                          ParameterMode.PARAM_IN);
              parametro2 = new ParameterDescription                       ("createdEquipment",
                                                                          structEquipment.type(),
                                                                          structEquipment,
                                                                          ParameterMode.PARAM_OUT);
              parametri = new ParameterDescription[] { parametro1, parametro2 };

              ident = EquipInventory.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "provisionEquipment" + ':' + "1.0";
              EquipInventory.create_operation(  ident, "provisionEquipment", "1.0",
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           exc,
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);

              parametro1 = new ParameterDescription                      ("meOrHolderName",
                                                                          NamingAttributes_T.type(),
                                                                          NamingAttributes_T,
                                                                          ParameterMode.PARAM_IN);
              parametro2 = new ParameterDescription                       ("how_many",
                                                                          tipoLong.type(),
                                                                          tipoLong,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro3 = new ParameterDescription  ("eqList",
                                                                          tipoVoid.type(),  //???
                                                                          EquipmentOrHolderList_T,
                                                                          ParameterMode.PARAM_OUT);
              ParameterDescription parametro4 = new ParameterDescription  ("eqIt",
                                                                          EquipOrHolderIterator.type(),
                                                                          EquipOrHolderIterator,
                                                                          ParameterMode.PARAM_OUT);
              parametri = new ParameterDescription[] { parametro1, parametro2, parametro3, parametro4};

              ident = EquipInventory.id();
              ident = ident.substring(0, ident.lastIndexOf(':')) + '/' + "getAllEquipment" + ':' + "1.0";
              EquipInventory.create_operation( ident, "getAllEquipment", "1.0",
                                               tipoVoid,
                                               OperationMode.OP_NORMAL,
                                               parametri,
                                               exc,
                                               new String[]{}
                                             );
              println("- creata operazione con id="+ident);
          }   catch (Exception e) {   e.printStackTrace();
                                      println("errore "+e);
                                  }
  }

  public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}
