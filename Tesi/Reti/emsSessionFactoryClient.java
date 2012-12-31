package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CORBA.PrimitiveKind;
import org.omg.CORBA.IDLType;
import org.omg.CORBA.Contained;
import org.omg.CORBA.InterfaceDef;
import org.omg.CORBA.InterfaceDefHelper;

public class emsSessionFactoryClient extends RequestResponseManagement
{ protected org.omg.CORBA.Object IOR_EntryPoint;
  protected OggettoNodo userArg, passwordArg;

  public emsSessionFactoryClient(InteractionManagementInterface _actions)
  {   setActions(_actions);
  }

  public void request(OggettoNodo oggNodoSel, org.omg.CORBA.Object rif, String mode) //throws globaldefs.ProcessingFailureException
  {   println("richiesta per emsSessionFactoryClient");
      IOR_EntryPoint = rif;
      //actionName = "void getEmsSession(in string user, in string password, in nmsSession::NmsSession_I client, out emsSession::EmsSession_I emsSessionInterface)";
      actionName = "void getEmsSession(in string user, in string password, in NmsSession_I client, out EmsSession_I emsSessionInterface)";
      DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode("root", true);
      userArg = new OggettoNodo(tipoStringa, "string ", "user", "", null);
      DefaultMutableTreeNode app = new DefaultMutableTreeNode(userArg, true);
      rootRequestNode.add(app);
      passwordArg = new OggettoNodo(tipoStringa, "string ", "password", "", null);
      app = new DefaultMutableTreeNode(passwordArg, true);
      rootRequestNode.add(app);
      setRequest(rootRequestNode, this);
  }

   public void execGetEmsSession()
  {  try
     {   org.omg.CORBA.ORB orb = Client.getOrb();
         POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
         NmsSession servant = new NmsSession();
         rootPOA.the_POAManager().activate();
         rootPOA.activate_object(servant);
         org.omg.CORBA.Object obj = rootPOA.servant_to_reference(servant);
         nmsSession.NmsSession_I client = nmsSession.NmsSession_IHelper.narrow(obj);
         emsSession.EmsSession_IHolder holder = new emsSession.EmsSession_IHolder();
         //fine parametri
         String user = userArg.getValue();
         String password = passwordArg.getValue();
         // remote request
         emsSessionFactory.EmsSessionFactory_I rif = emsSessionFactory.EmsSessionFactory_IHelper.narrow (IOR_EntryPoint);
         rif.getEmsSession(user, password, client, holder);  // chiamata remota
         // end request
         String textualResponse = "interface ::emsSession::EmsSession_I";
         DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode("root", true);
         DefaultMutableTreeNode emsSessionNode = browseObject(holder.value);

         Contained contained = Client.getRepository().lookup_id("IDL:mtnm.tmforum.org/session/Session_I:1.0");
         controlla(contained);

         InterfaceDef session = InterfaceDefHelper.narrow(contained);
         DefaultMutableTreeNode sessionNode = getActions().getIDLBrowser().browse(contained, new DefaultMutableTreeNode("root",true), holder.value, false);

         rootResponseNode.add((DefaultMutableTreeNode)sessionNode.getChildAt(0));
         rootResponseNode.add((DefaultMutableTreeNode)emsSessionNode.getChildAt(0));

         setResult(rootResponseNode, textualResponse);
         getActions().getMainWindow().DisableConnectButton();
         getActions().getMainWindow().EnableDisconnectButton();

     }   catch (Exception e)  {   if (e instanceof globaldefs.ProcessingFailureException)
                                  {   globaldefs.ProcessingFailureException ex = (globaldefs.ProcessingFailureException)e;
                                      showMessageDialog(ex.errorReason, ERROR_MESSAGE);
                                  }   else   {  e.printStackTrace();  }
                              }

  }

  public void returnFromIns()
  {   execGetEmsSession();
  }

  public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  System.out.println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}
