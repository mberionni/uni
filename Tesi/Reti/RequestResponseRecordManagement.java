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
import javax.swing.JTree;
import TreeTablePackage.JTreeTable;
import TreeTablePackage.TreeTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

 public abstract class RequestResponseRecordManagement extends RequestResponseManagement
 { protected SessionRecorderInterface sR;
   protected SimpleDateFormat formatter;
   protected Date currentDate;
   protected String lastDate = "", actionName = "";
   protected IDLType tipoStringa = ConfigDataAccessClient.tipoStringa;
   protected IDLType tipoLong = ConfigDataAccessClient.tipoLong;
   protected int ERROR_MESSAGE = javax.swing.JOptionPane.ERROR_MESSAGE;
   protected int WARNING_MESSAGE = javax.swing.JOptionPane.WARNING_MESSAGE;

   public void setRequest(DefaultMutableTreeNode _requestNode, RequestResponseManagement managerClass)
   {    setRequestLog(_requestNode);
        super.setRequest(requestNode, managerClass);
   }

   public void setRequest()   //se non ci sono argomenti di ingresso si skippa la fase di inserimento
   {    setRequestLog(null);
   }

   public void setResult(DefaultMutableTreeNode _responseNode, String _textualResponse)
    {   setResultLog(_responseNode);
        super.setResult(responseNode, _textualResponse);
        if (actions.isRecEnabled())
        {   println("sto memorizzando l'azione appena effettuara");
            String lastDate = "";
            formatter = new SimpleDateFormat ("ddMMMyyyy_HH:mm", Locale.getDefault());
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
   {   formatter = new SimpleDateFormat ("ddMMMyyyy_HH:mm", Locale.getDefault());
       currentDate = new Date();
       lastDate = formatter.format(currentDate);
       creaOggetto rootRequestObject = new creaOggetto(tipoStringa, "", lastDate, " - " + actionName + " - ", null);
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

  public void setResultLog(DefaultMutableTreeNode _responseNode)
  {   formatter = new SimpleDateFormat ("ddMMMyyyy_HH:mm", Locale.getDefault());
      currentDate = new Date();
      lastDate = formatter.format(currentDate);
      creaOggetto rootResponseObject = new creaOggetto(tipoStringa, "", lastDate, " - " + actionName + " - ", null);
      rootResponseObject.setValue("response");
      DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
      if (_responseNode != null)    //caso di risposta a getLengtt ???
      {    int childCount =  _responseNode.getChildCount();
           for(int i = 0; i < childCount; i++)
           {   DefaultMutableTreeNode child = (DefaultMutableTreeNode)_responseNode.getChildAt(0);
               rootResponseNode.add(child);
           }
      }
      responseNode = rootResponseNode;
  }

    public DefaultMutableTreeNode browseObject(org.omg.CORBA.Object obj, DefaultMutableTreeNode root)
    {   // attenzione root è sempre new defaultmut...
        try
        {   org.omg.CORBA.Object object = obj._get_interface_def();
            InterfaceDef idef = InterfaceDefHelper.narrow(object);
            DefaultMutableTreeNode NewNode = actions.getIDLBrowser().browse(idef, root, obj, false);
            return NewNode;
        }   catch (Exception e) {   e.printStackTrace();
                                    println(e.toString());
                                    return null;
                                }
    }

   public void println(String text)
   {   actions.getDebugWindow().println(text);
   }

   public void showMessageDialog(String msg, int msgType)
   {    actions.getMainWindow().showMessageDialog(msg, msgType);
   }

   /*public void returnType()
   {    System.out.println("mai chiamato: implementato nelle sottoclassi!");
   }*/

 }