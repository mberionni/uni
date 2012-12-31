package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */

 import javax.swing.tree.DefaultMutableTreeNode;
 import org.omg.CORBA.*;

public class emsSessionClient extends RequestResponseManagement
{ int requestId = 0;
  OggettoNodo managerNameArg;
  emsSession.EmsSession_I emsS;

  public emsSessionClient(InteractionManagementInterface _actions)
  {   setActions(_actions);
  }

  public void request(OggettoNodo oggNodoSel, org.omg.CORBA.Object _rif, String mode)
  {   println("richiesta per emsSessionClient");
      emsS = emsSession.EmsSession_IHelper.narrow(_rif);
      actionName = oggNodoSel.toString();
      if ( (oggNodoSel.getName()).compareTo("getSupportedManagers") == 0 )
         {    requestId = 1;
              getSupportedManagers();
         }
      else if  ( (oggNodoSel.getName()).compareTo("getManager") == 0 )
         {    requestId = 2;
              getManager();
         }
      else if  ( (oggNodoSel.getName()).compareTo("ping") == 0 )
         {    requestId = 3;
              ping();
         }
      else if  ( (oggNodoSel.getName()).compareTo("endSession") == 0 )
         {    requestId = 4;
              endSession();
         }
      else if  ( (oggNodoSel.getName()).compareTo("associatedSession") == 0 )
         {    requestId = 5;
              associatedSession(mode);
         }
      else println("richiesta sconosciuta !!");
  }

  public void associatedSession(String mode)
  {   try
      {   if (mode.compareTo("read") == 0)
          {     println("richiesta read attribute asssociatedSession");
                setRequestLog(null);
                session.Session_I session = emsS.associatedSession();
                String textualResponse = "session.Session_I";
                OggettoNodo rootResponseObject = new OggettoNodo(tipoStringa, "response", "", "", null);
                DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
                // DefaultMutableTreeNode sessionNode = actions.getIDLBrowser().browse(session, new DefaultMutableTreeNode("root", true), null, false);
                DefaultMutableTreeNode sessionNode = browseObject(session);
                rootResponseNode.add((DefaultMutableTreeNode)sessionNode.getChildAt(0));
                setResult(rootResponseNode, textualResponse);

          }
          else  System.out.println("caso di scrittura di attributo non ancora implementato");
      }   catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                     showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  e.printStackTrace();
                                  println(e.toString());
                              }
  }

  public void getSupportedManagers()
  {  println("costruzione richiesta getsupportedmanagers");
     emsSession.EmsSession_IPackage.managerNames_THolder holder = new emsSession.EmsSession_IPackage.managerNames_THolder();
     try
     {    setRequestLog(null);
          emsS.getSupportedManagers(holder);  // chiamata remota
          DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(new DefaultMutableTreeNode("root", true), true);
          ArrayDef tipoArray = Client.getRepository().create_array(0, tipoStringa);
          OggettoNodo ogg = new OggettoNodo(tipoArray, "sequence <string> ", "supportedManagerList", "", null);
          DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(ogg, true);

          rootResponseNode.add(node2);
          String[] array = holder.value;
          String textualOut ="string [] supportedManagerList { \n";
          DefaultMutableTreeNode app;
          for (int k = 0; k < array.length; k++)
          {  textualOut = textualOut + "\"" + array[k].toString() + "\" \n";
             ogg = new OggettoNodo(tipoStringa, array[k].toString(), "", "", null);
             app = new DefaultMutableTreeNode(ogg, true);
             node2.add(app);
          }
          textualOut = textualOut + "}";
          setResult(rootResponseNode, textualOut);
     }    catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  e.printStackTrace();
                                  println(e.toString());
                              }
  }

  public void ping()
  {  try
     {    setRequestLog(null);
          System.out.println("prima ping");
          println("prima ping");
          emsS.ping();  // chiamata remota
          System.out.println("dopo ping");
          println("dopo ping");

          String textualOut = "ping succeded!";
          setResult(null, textualOut);
          showMessageDialog("ping succeded!", INFORMATION_MESSAGE);
     }    catch (Exception e) {   e.printStackTrace();
                                  println(e.toString());
                                  showMessageDialog("Warning: Server crashed!", WARNING_MESSAGE);
                              }
    // in teoria possono essere generati due tipi di eccezioni: una dal server in corrispondenza dell'esecuzione
    // del metodo (ping in questo caso), una dal runtime per il fatto che il server è caduto !
  }

  public void endSession()
  {  println("costruzione richiesta endSession");
     try
     {    setRequestLog(null);
          System.out.println("prima endSession");
          println("prima endSession");
          emsS.endSession();
          System.out.println("dopo endSession");
          println("dopo endSession");
          getActions().getMainWindow().DisconnectButton.setEnabled(false);
          getActions().getMainWindow().ConnectButton.setEnabled(true);
          String textualOut = "session terminated!";
          setResult(null, textualOut);
          showMessageDialog("Session termimnated!", INFORMATION_MESSAGE);
     }    catch (Exception e) {   e.printStackTrace();
                                  println(e.toString());
                                  showMessageDialog("Server not found!", WARNING_MESSAGE);
                              }
  }

  public void getManager()
  {  println("costruzione richiesta getManager");
     DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode("root", true);
     managerNameArg = new OggettoNodo(tipoStringa, "string ", "managerName", "", null);
     DefaultMutableTreeNode app = new DefaultMutableTreeNode(managerNameArg, true);
     rootRequestNode.add(app);
     setRequest(rootRequestNode, this);
  }

  public void execGetManager(String managerName)
  {  //costruzione parametro di tipo out
     common.Common_IHolder holder = new common.Common_IHolder();
     //fine parametro out
     try
     {   emsS.getManager(managerName, holder);  // richiesta remota
         //String textualResponse = ("interface ::common::Common_I");
         String textualResponse = ("interface Common_I"); //tutti i manager estendono l'interfaccia Common_I
         OggettoNodo rootResponseObject = new OggettoNodo(tipoStringa, "response", "", "", null);
         DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
         DefaultMutableTreeNode managerNode = browseObject(holder.value);
         Contained contained = Client.getRepository().lookup_id("IDL:mtnm.tmforum.org/common/Common_I:1.0");
         controlla(contained);
         InterfaceDef common = InterfaceDefHelper.narrow(contained);
         DefaultMutableTreeNode commonNode = getActions().getIDLBrowser().browse(contained, new DefaultMutableTreeNode("root", true), holder.value, false);

         rootResponseNode.add((DefaultMutableTreeNode)commonNode.getChildAt(0));
         rootResponseNode.add((DefaultMutableTreeNode)managerNode.getChildAt(0));

         setResult(rootResponseNode, textualResponse);
     }   catch (Exception e) {    if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  e.printStackTrace();
                                  println(e.toString());
                              }
  }

  public void returnFromIns()
  {   switch (requestId)
          {   case 0:    println("Sob! Nessuna richiesta in corso");
                         break;
              case 2:    println("richiesta get manager name = " + managerNameArg.getValue());
                         execGetManager(managerNameArg.getValue());
          }
  }

   public static void controlla(Contained contained) throws java.lang.Exception
  {   if (contained == null)
      {  System.out.println("la ricerca nell'interface repository non ha trovato quanto richiesto!");
         throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
      }
  }

}

