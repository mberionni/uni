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

public class registraEmsSessionFactory extends RegistrationClass
{ public static final String[] idRep =  new String[] { "IDL:mtnm.tmforum.org/emsSessionFactory:1.0" };
  protected String moduleName = "emsSessionFactory";
  private static String interfaceName="EmsSessionFactory_I";
  private static String interfaceVersion="1.0";
  //private static Repository ir;

  public registraEmsSessionFactory()
  {   println("@ - avvio registrazione modulo " + moduleName);
      registrazioneRepository();
      println("@ - fine registrazione " + moduleName+ "\n");
  }

  public static void main(String[] args)
  {  println("@ - avvio registrazione modulo emsSessionFactory");
     registraEmsSessionFactory regEmsSessionFactory = new registraEmsSessionFactory();
     regEmsSessionFactory.registrazioneRepository();//orb = ORB.init(args,null);
     //registrazioneRepository();
     println("@ - fine registrazione emsSessionFactory");
  }

  private void registrazioneRepository()
  {     org.omg.CORBA.InterfaceDef[] base_interfaces = { };
        try { //ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              println("Localizzato correttamente repository");
              ModuleDef emsSessionFactory = ir.create_module(idRep[0],"emsSessionFactory","1.0");
              println("- creato modulo emsSessionFactory con id="+idRep[0]);
              String idAcc = idRep[0].substring(0, idRep[0].lastIndexOf(':'))+'/'+interfaceName+':'+interfaceVersion;

              Contained c = ir.lookup_id("IDL:mtnm.tmforum.org/session/Session_I:1.0");
              controlla(c);
              InterfaceDef Session_I = InterfaceDefHelper.narrow(c);
              TypeCode tcs = orb.create_interface_tc("IDL:mtnm.tmforum.org/session/Session_I:1.0", "Session_I");

              c = ir.lookup_id("IDL:mtnm.tmforum.org/emsSession/EmsSession_I:1.0");
              controlla(c);
              InterfaceDef emsSession_I = InterfaceDefHelper.narrow(c);
              TypeCode emsSession_I_TC = orb.create_interface_tc("IDL:mtnm.tmforum.org/emsSession/EmsSession_I:1.0", "EmsSession_I");

              c = ir.lookup_id("IDL:mtnm.tmforum.org/nmsSession/NmsSession_I:1.0");
              controlla(c);
              InterfaceDef nmsSession_I = InterfaceDefHelper.narrow(c);
              TypeCode nmsSession_I_TC = orb.create_interface_tc("IDL:mtnm.tmforum.org/nmsSession/NmsSession_I:1.0", "NmsSession_I");


              c = ir.lookup_id("IDL:mtnm.tmforum.org/version/Version_I:1.0");
              controlla(c);
              InterfaceDef Version_I = InterfaceDefHelper.narrow(c);
              TypeCode tcv = orb.create_interface_tc("IDL:mtnm.tmforum.org/version/Version_I:1.0", "Version_I");

              org.omg.CORBA.InterfaceDef[] base_interfaces2 = { Version_I };

              IDLType sss = InterfaceDefHelper.narrow(Session_I);
              IDLType emssss = InterfaceDefHelper.narrow(emsSession_I);

              InterfaceDef Factory_I = emsSessionFactory.create_interface(idAcc, interfaceName, "1.0", base_interfaces2);
              println("- creata interfaccia con id="+Factory_I.id());
              IDLType tipoObj = ir.get_primitive(PrimitiveKind.pk_objref);
              ParameterDescription parametro1 = new ParameterDescription ("user",
                                                                          tipoStringa.type(),
                                                                          tipoStringa,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro2 = new ParameterDescription ("password",
                                                                          tipoStringa.type(),
                                                                          tipoStringa,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro3 = new ParameterDescription ("client",
                                                                          nmsSession_I_TC,
                                                                          nmsSession_I,
                                                                          ParameterMode.PARAM_IN);
              ParameterDescription parametro4 = new ParameterDescription ("emsSessionInterface",
                                                                          emsSession_I_TC,
                                                                          emsSession_I,
                                                                          ParameterMode.PARAM_OUT);
              ParameterDescription parametri[] = new ParameterDescription[] { parametro1, parametro2, parametro3, parametro4 };
              String ident = Factory_I.id();
              ident = ident.substring(0,ident.lastIndexOf(':'))+'/'+"getEmsSession"+':'+Factory_I.version();

              c = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/ProcessingFailureException:1.0");
              controlla(c);
              ExceptionDef ex = ExceptionDefHelper.narrow(c);


              Factory_I.create_operation(  ident, "getEmsSession", Factory_I.version(),
                                           tipoVoid,
                                           OperationMode.OP_NORMAL,
                                           parametri,
                                           new ExceptionDef[] { ex },
                                           new String[]{}
                                         );
              println("- creata operazione con id="+ident);

             }
             catch (Exception e) { e.printStackTrace();
                                   println("errore "+e);}
  }

  public static void controlla(Contained contained) throws org.omg.CORBA.SystemException
  {   if (contained == null)
      {  println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }



}
