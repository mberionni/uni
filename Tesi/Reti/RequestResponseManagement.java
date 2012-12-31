package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.InterfaceDef;
import org.omg.CORBA.InterfaceDefHelper;
import org.omg.CORBA.IDLType;
import javax.swing.tree.DefaultMutableTreeNode;
import TreeTablePackage.JTreeTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

 public abstract class RequestResponseManagement implements RequestResponseManagementInterface
 { protected SimpleDateFormat formatter;
   protected Date currentDate;
   protected String lastDate = "", actionName = "";
   protected InteractionManagementInterface actions;
   protected DefaultMutableTreeNode responseNode = null, requestNode = null;
   protected IDLType tipoStringa = ConfigDataAccessClient.tipoStringa;
   protected IDLType tipoLong = ConfigDataAccessClient.tipoLong;  // usato nelle sottoclassi
   protected int ERROR_MESSAGE = javax.swing.JOptionPane.ERROR_MESSAGE;
   protected int WARNING_MESSAGE = javax.swing.JOptionPane.WARNING_MESSAGE;
   protected int INFORMATION_MESSAGE = javax.swing.JOptionPane.INFORMATION_MESSAGE;

   public void setRequest(DefaultMutableTreeNode _requestNode, RequestResponseManagementInterface managerClass)
   {    setRequestLog(_requestNode);
        MyInsertFrameInterface iw = new InsertWindow();
        JTreeTable treeTable = CreateCustomTreeTable.createTreeTable(iw.getWindow(), requestNode);
        iw.addTreeTable(treeTable);
        iw.setTree(treeTable, requestNode);
        iw.setClass(managerClass);
        iw.setVisible(true);
   }

   public void setActions(InteractionManagementInterface _actions)
   {    actions = _actions;
   }

    public InteractionManagementInterface getActions()
   {    return actions;
   }

   public void setResult(DefaultMutableTreeNode _responseNode, String _textualResponse)
    {   actions.getMainWindow().setStatusBarText("Ricevuta risposta dall'agente");
        ((ApplicationGUI)actions.getMainWindow()).readAttributeButton.setEnabled(false);
        ((ApplicationGUI)actions.getMainWindow()).writeAttributeButton.setEnabled(false);
        ((ApplicationGUI)actions.getMainWindow()).infoPanel.setSemaphoreIcon("yellow");
        actions.getMainWindow().DisableExecuteButton();
        if (_responseNode != null)
        {   DefaultMutableTreeNode plainResponseNode = setResultLog(_responseNode);
            actions.visualizzaTree(plainResponseNode, _textualResponse);
        }   else responseNode = null;
        if (actions.isRecEnabled())
        {   println("sto memorizzando l'azione appena effettuata");
            String lastDate = "";
            formatter = new SimpleDateFormat ("HH:mm", Locale.getDefault());
            currentDate = new Date();
            lastDate = formatter.format(currentDate);
            String request = lastDate + " --> operazione: " + actionName;
            String response = " --> risultato = " + _textualResponse;
            try
            {   actions.getSessionRecorder().actionRecord(request, response, requestNode, responseNode);
                actions.getLogWindow().addCurrentAction(request, response, requestNode, responseNode);
            }   catch (java.io.IOException e)  {    e.printStackTrace();
                                                    println(e.toString());
                                                    showMessageDialog("ERRORE scrittura file di log", ERROR_MESSAGE);
                                                }
        }
     }

   public void setRequestLog(DefaultMutableTreeNode _requestNode)
   {   formatter = new SimpleDateFormat ("HH:mm", Locale.getDefault());
       currentDate = new Date();
       lastDate = formatter.format(currentDate);
       OggettoNodo rootRequestObject = new OggettoNodo(tipoStringa, "", lastDate, " - " + actionName + " - ", null);
       rootRequestObject.setValue("request");
       DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode(rootRequestObject, true);
       if (_requestNode != null) // nel caso di richieste tipo getSupportedManagers()
       {   int childCount = _requestNode.getChildCount();
           for(int i = 0; i < childCount ; i++)
           {   DefaultMutableTreeNode child = (DefaultMutableTreeNode)_requestNode.getChildAt(0);
               rootRequestNode.add(child);
           }
       }
       requestNode = rootRequestNode;
    }

  public DefaultMutableTreeNode setResultLog(DefaultMutableTreeNode _responseNode)
  {   formatter = new SimpleDateFormat ("HH:mm", Locale.getDefault());
      currentDate = new Date();
      lastDate = formatter.format(currentDate);
      OggettoNodo rootResponseObject = new OggettoNodo(tipoStringa, "", lastDate, " - " + actionName + " - ", null);
      rootResponseObject.setValue("response");
      DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
      OggettoNodo rootResponseObjectPlain = new OggettoNodo(tipoStringa, "", actionName, "", null);
      rootResponseObjectPlain.setValue("response");
      DefaultMutableTreeNode rootResponseNodePlain = new DefaultMutableTreeNode(rootResponseObjectPlain, true);
      if (_responseNode != null)    //caso di risposta a getLength ???
      {    int childCount =  _responseNode.getChildCount();
           for(int i = 0; i < childCount; i++)
           {   DefaultMutableTreeNode child = (DefaultMutableTreeNode)_responseNode.getChildAt(0);
               rootResponseNode.add(clone(child));
               rootResponseNodePlain.add(child);
           }
      }
      responseNode = rootResponseNode;
      return rootResponseNodePlain;
  }

    public DefaultMutableTreeNode browseObject(org.omg.CORBA.Object obj) throws globaldefs.ProcessingFailureException
    {   // attenzione root è sempre new defaultmut...
        try
        {   org.omg.CORBA.Object object = obj._get_interface_def();
            InterfaceDef idef = InterfaceDefHelper.narrow(object);
            DefaultMutableTreeNode NewNode = actions.getIDLBrowser().browse(idef, new DefaultMutableTreeNode("Root", true), obj, false);
            return NewNode;
        }   catch (Exception e) {   e.printStackTrace();
                                    println(e.toString());
                                    //showMessageDialog("Errore nel reperimento dell'interfaccia all'Interface Repository!", ERROR_MESSAGE);
                                    throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_ENTITY_NOT_FOUND, "interfaccia non presente nell'Interface Repository o IR non in esecuzione!");
                                }
    }

    public DefaultMutableTreeNode clone(DefaultMutableTreeNode node)
    {   DefaultMutableTreeNode clone;
        if (node == null)  { return null;
                            };
        Object ogg = node.getUserObject();
        if (ogg instanceof OggettoNodo)
        {   OggettoNodo ogg1 = (OggettoNodo)ogg;
            OggettoNodo nuovoOgg = new OggettoNodo(ogg1.getOggettoIR(), ogg1.getDescription(), ogg1.getName(), ogg1.getSignature(), ogg1.getIOR());
            nuovoOgg.setValue(ogg1.getValue());
            clone =  new DefaultMutableTreeNode(nuovoOgg, true);
        }   else
        {   //println("nodo senza oggetto " + node.toString());
            clone =  new DefaultMutableTreeNode(node, true);
        }

        for (int len = node.getChildCount(), i = 0; i < len; i++)
        {    clone.add(clone((DefaultMutableTreeNode)node.getChildAt(i))); // recursive call
        }
        return clone;
    }

    public void println(String text)
    {   actions.getDebugWindow().println(text);
    }

    public void showMessageDialog(String msg, int msgType)
    {    actions.getMainWindow().showMessageDialog(msg, msgType);
    }

 }