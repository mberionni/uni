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
import org.omg.CosNaming.*;


public class deregBank
 { private static ORB orb;

  public static void main(String[] args)
  {  System.out.println("@ - avvio deregistrazione BankService");
     orb = ORB.init(args,null);
     deregistrazioneRep();
     deregistrazioneNS();
     System.out.println("@ - fine deregistrazione BankService");
  }

  private static void deregistrazioneRep()
  {    try {
              Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository ");
              IRObject[] contained = ir.lookup_name("BankService",1,DefinitionKind.dk_Module,true);
              ModuleDef m = ModuleDefHelper.narrow(contained[0]);
              contained = m.contents(DefinitionKind.dk_Interface,false);
              InterfaceDef i1 = InterfaceDefHelper.narrow(contained[0]);
              InterfaceDef i2 = InterfaceDefHelper.narrow(contained[1]);

              Contained[] contained1 = i1.contents(DefinitionKind.dk_Operation,true);
              Contained[] contained2 = i2.contents(DefinitionKind.dk_Operation,true);

              contained1[0].destroy();
              contained2[0].destroy();
              contained2[1].destroy();
              i1.destroy();
              i2.destroy();
              m.destroy();
              System.out.println("Eliminato modulo BankService dal repository");
            }
            catch (Exception e) { e.printStackTrace();
                                  System.out.println("ERRORE: " + e);}
  }

  private static void deregistrazioneNS()
  {    try
       {  org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service");
          NameComponent []comp= {new NameComponent("AccountManager","")};
          nc.unbind(comp);
          System.out.println("binding per " + comp[0].id + " eliminato");
       }  catch (Exception e) {e.printStackTrace();}
  }
}
