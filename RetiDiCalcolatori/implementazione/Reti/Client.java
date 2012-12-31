// Client.java
package Reti;
import org.omg.CORBA.*;
import javax.swing.tree.*;

public class Client
{ private static ORB orb;
  public static DefaultMutableTreeNode node = new DefaultMutableTreeNode("RootRep",true);
  public static Repository ir;
  protected static navigaRepository nR;

  public static void main(String[] args)
   {  try
      {   orb = org.omg.CORBA.ORB.init(args,null);
          visualizzaOpzioni();
      } catch (Exception e) {   System.err.println("Unexpected exception caught:");
                                   e.printStackTrace();
                            }
   }

   public static void visualizzaOpzioni()
   {      try
          {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("InterfaceRepository");
              ir = RepositoryHelper.narrow(rootObj);
              System.out.println("Localizzato correttamente repository");
              nR = new navigaRepository();
              node = nR.cruise(ir,node);
              ClientGUI cg = new ClientGUI();
          } catch (Exception e) {   System.err.println("Unexpected exception caught:");
                                    e.printStackTrace();
                                }
   }

   protected static navigaRepository getnR()
   { return nR;
   }

   public static ORB getOrb()
   { return orb;
   }

}




