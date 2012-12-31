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
import org.omg.CosNaming.*;

public class deregDB
 { private static ORB orb;

  public static void main(String[] args)
  {  System.out.println("@ - avvio deregistrazione DBService");
     orb = ORB.init(args,null);
     deregistrazioneRep();
     deregistrazioneNS();
     System.out.println("@ - fine deregistrazione DBService");
  }

  private static void deregistrazioneRep()
  {    try {
              Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository ");
              IRObject[] contained = ir.lookup_name("DBService",1,DefinitionKind.dk_Module,true);
              ModuleDef m = ModuleDefHelper.narrow(contained[0]);
              contained = m.contents(DefinitionKind.dk_Interface,false);
              InterfaceDef i1 = InterfaceDefHelper.narrow(contained[0]);
              Contained[] contained1 = i1.contents(DefinitionKind.dk_Operation,true);
              contained1[0].destroy();
              i1.destroy();
              m.destroy();
              System.out.println("Eliminato modulo DBService dal repository");
            }
            catch (Exception e) { e.printStackTrace();
                                  System.out.println("ERRORE: "+e);
                                }
  }

  private static void deregistrazioneNS()
  {    try
       {  org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service");
          NameComponent []comp= {new NameComponent("tabella","")};
          nc.unbind(comp);
          System.out.println("Eliminato binding per " + comp[0].id);
       }  catch (Exception e) {   e.printStackTrace();
                              }
  }
}
