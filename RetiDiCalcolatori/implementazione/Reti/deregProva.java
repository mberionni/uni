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

public class deregProva
 { private static ORB orb;

  public static void main(String[] args)
  {  System.out.println("@ - avvio deregistrazione ProvaService");
     orb = ORB.init(args, null);
     deregistrazioneRep();
     deregistrazioneNS();
     System.out.println("@ - fine deregistrazione ProvaService");
  }

  private static void deregistrazioneRep()
  {    try {  Repository ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
              System.out.println("Localizzato correttamente repository");
              IRObject[] contained = ir.lookup_name("ProvaService",1,DefinitionKind.dk_Module,true);
              ModuleDef m = ModuleDefHelper.narrow(contained[0]);
              contained = m.contents(DefinitionKind.dk_Interface,false);
              InterfaceDef i = InterfaceDefHelper.narrow(contained[0]);
              contained = i.contents(DefinitionKind.dk_Operation,true);
              contained[0].destroy();
              contained[1].destroy();
              contained[2].destroy();
              i.destroy();
              m.destroy();
            }
            catch (Exception e) {  e.printStackTrace();
                                   System.out.println("ERRORE: "+e);
                                }

  }

  private static void deregistrazioneNS()
  {    try
       {  org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext nc = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente naming service");
          NameComponent []comp= {new NameComponent("somma","")};
          nc.unbind(comp);
          System.out.println("Eliminato binding per " + comp[0].id);
       }  catch (Exception e) {   e.printStackTrace();
                              }
  }
 }