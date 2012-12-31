package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTree;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import org.omg.CORBA.DefinitionKind;
import org.omg.CORBA.InterfaceDef;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.util.Enumeration;
import TreeTablePackage.JTreeTable;
import TreeTablePackage.TreeTable;



public class LowLevelInteractionManagement extends InteractionManagement implements LowLevelInteractionManagementInterface
{ protected IDLBrowserInterface idlBrowser;
  protected SessionRecorderInterface sR;
  protected MyTreeFrameInterface irepW;
  protected DefaultMutableTreeNode SelectedNode, nodeObj = new DefaultMutableTreeNode("Root", true);
  protected creaOggetto oggNodoSel = new creaOggetto(null, "", "void getEmsSession", "(in string user, in string password, in nmsSession::NmsSession_I client, out emsSession::EmsSession_I emsSessionInterface)", null);
  protected TreeSelectionModel treeModel;
  protected boolean recEnable = false;

  protected Font f1 = ConfigDataAccessClient.f1;
  protected Color treeBackColor =  ConfigDataAccessClient.treeBackColor;

  public void init(MyMainFrame _mainW,  MyLogFrameInterface _logW, MyFrame _debugW, MyFrame _textualOutW, MyTreeFrameInterface _irepW, SessionRecorderInterface _sR, IDLBrowserInterface _idlBrowser)
  {   super.init(_mainW, _logW, _debugW, _textualOutW);
      sR = _sR;
      irepW = _irepW;
      idlBrowser = _idlBrowser;
  }

   public void updateRepository()
   {   try
       {  DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("RootRep", true);
          rootNode = idlBrowser.browse(Client.getRepository(), rootNode);
          JTree tree = new JTree(rootNode);
          configuraTree(tree);
          irepW.setTree(tree);
          irepW.setVisible(true);
       }  catch (Exception e) {  e.printStackTrace();
                                 println(e.toString());
                              }
   }

   public void visualizzaTree(DefaultMutableTreeNode rootNode, String textualResponse)  // e' il metodo che visualizza il risultato!
   { textualOutW.println(" ricevuto risultato response node children:");
     textualOutW.println(textualResponse);
     boolean esiste = false;
     if (nodeObj.getChildCount() == 0)  //è il primo tree che visualizzo
     {  nodeObj.add(rootNode);
     }
     else
     {  esiste = controlloEsistenza(rootNode);
        if (!esiste)
        {    nodeObj.add(rootNode);
        }
     }
     if (!esiste)
     {    println("ho ricevuto un nuovo oggetto - lo aggiungo " + rootNode.getChildAt(0).toString());
          JTree tree = new JTree(nodeObj);
          tree.setName("ApplicationGUITree");
          configuraTree(tree);
          mainW.addTree(tree);
          expand(tree, nodeObj.children());
     }
  }

  protected void configuraTree(JTree tree)
  { tree.setBackground(treeBackColor);
    treeModel = tree.getSelectionModel();
    treeModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setCellRenderer(new MyCellRenderer());
    tree.addTreeSelectionListener( new TreeSelectionListener()
    {    public void valueChanged (TreeSelectionEvent event)
         {    treeValueChanged(event);
         }
    }                            );
    tree.putClientProperty("JTree.lineStyle","Angled");
    tree.setFont(f1);
  }

   protected void treeValueChanged (TreeSelectionEvent event)
   {   TreePath path = event.getPath();
       java.lang.Object node = path.getLastPathComponent();
       SelectedNode = (DefaultMutableTreeNode)node;
       println("selezionato nodo " + node);
       if ( ( ( (JTree)(event.getSource()) ).getName()).compareTo("ApplicationGUITree") == 0  )
       {    if (!SelectedNode.isRoot())
                {    oggNodoSel = (creaOggetto)SelectedNode.getUserObject();
                     DefinitionKind dk = oggNodoSel.tipo.def_kind();
                     if (browsable(dk, SelectedNode.getChildCount(), SelectedNode.getDepth() ))
                     {   mainW.setStatusBarText("selezionato nodo " + oggNodoSel.toString());
                         JTreeTable treeTable = CreateCustomTreeTable.createTreeTable(mainW, SelectedNode);
                         treeTable.getColumnModel().getColumn(1).setCellEditor(new MyTableEditorNonEditable());
                         mainW.addTreeTable(treeTable);
                     }
                     else if (dk == DefinitionKind.dk_Attribute)
                     {   mainW.setStatusBarText(oggNodoSel.toString() + " - selezione non eseguibile -");
                         ((ApplicationGUI)mainW).EnableExecuteButton();
                     }
                     if (dk == DefinitionKind.dk_Operation)
                     {    mainW.setStatusBarText(oggNodoSel.toString());
                          ((ApplicationGUI)mainW).EnableExecuteButton();
                     }
                     else
                     {    mainW.setStatusBarText(oggNodoSel.toString() + " - selezione non eseguibile -");
                          ((ApplicationGUI)mainW).DisableExecuteButton();
                     }
                }
                else
                {    mainW.setStatusBarText(node + " - selezione non eseguibile -");
                     ((ApplicationGUI)mainW).DisableExecuteButton();
                }
            }
            else  // selezione avvenuta nella finestra dell'interface repository
            {   irepW.setStatusBarText(node.toString());
                ((ApplicationGUI)mainW).DisableExecuteButton();
            }
   }

   protected boolean browsable(DefinitionKind dk, int children, int depth)
   {    if (depth == 1) return true;
        else
        if (children == 0) return false;
        else
        return ( (! ( (dk == DefinitionKind.dk_Attribute || dk == DefinitionKind.dk_Constant || dk == DefinitionKind.dk_Primitive || dk == DefinitionKind.dk_String)) && (children != 0) )
                || ( dk == DefinitionKind.dk_Operation || dk == DefinitionKind.dk_Module  || dk == DefinitionKind.dk_Interface)  );
   }


  protected void expand(JTree tree, Enumeration enum)
  {   int i = 0;
      while (enum.hasMoreElements())
      {   DefaultMutableTreeNode child = (DefaultMutableTreeNode)enum.nextElement();
          //println("sto espandendo " + child.toString());
          TreePath path = new TreePath(child.getPath());
          tree.expandPath(path);
          i++;
      }
  }

   protected boolean controlloEsistenza(DefaultMutableTreeNode newTree)
    {  boolean esiste = false;
       DefaultMutableTreeNode root = (DefaultMutableTreeNode)nodeObj.getRoot();
       DefaultMutableTreeNode r = (DefaultMutableTreeNode)newTree.getChildAt(0);
       Enumeration enum = root.children();
       while (enum.hasMoreElements())
       {   DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)(enum.nextElement());
           DefaultMutableTreeNode nodoConfronto = (DefaultMutableTreeNode)nodo.getChildAt(0);
           String nextEl = nodoConfronto.toString();
           //println("confronto "+ nextEl +" e " + r.toString());
           if (  nextEl.compareTo(r.toString()) == 0)
           {   if (((creaOggetto)r.getUserObject()).tipo instanceof InterfaceDef)
               {    esiste = true;
                    treeModel.setSelectionPath(new TreePath(nodoConfronto.getPath()));
                    //println("confronto"+nextEl + " e " + r.toString()+" - l'oggetto e' gia' esistente!");
               }
               break;
           }
       }
       return esiste;
    }

  public void esegui()
  {   DefaultMutableTreeNode ParentNode = (DefaultMutableTreeNode)SelectedNode.getParent();
      creaOggetto SelectedObject = (creaOggetto)ParentNode.getUserObject();
      String SelectedObjectName = SelectedObject.getName();
      org.omg.CORBA.Object SelectedObjectIOR = SelectedObject.getIOR();
      mainW.setStatusBarText("Invocato metodo remoto " + SelectedNode.toString());
      println("nome = " + SelectedObjectName);
      try
      {   if ( SelectedObjectName.endsWith("EmsSession_I"))
          {   emsSessionClient EmsS = new emsSessionClient(this);
              EmsS.richiesta(oggNodoSel, SelectedObjectIOR);
          }
          else
          if ( SelectedObjectName.endsWith("EquipmentInventoryMgr_I") )
          {   EquipmentInventory_MgrClient EqpM = new EquipmentInventory_MgrClient(this);
              EqpM.richiesta(oggNodoSel, SelectedObjectIOR);
          }
          else
          if ( SelectedObjectName.endsWith("EquipmentOrHolderIterator_I") )
          {   EquipmentOrHolderIteratorClient EqpHI = new EquipmentOrHolderIteratorClient(this);
              EqpHI.richiesta(oggNodoSel, SelectedObjectIOR);
          }
          // tutte le classi "client" possono essere precreate una sola volta!!!!
       }  catch (Exception e) {  e.printStackTrace();
                                 mainW.showMessageDialog(e.toString(), mainW.ERROR_MESSAGE);
                              }

  }

  public MyTreeFrameInterface getRepositoryWindow()
  {   return irepW;
  }

  public IDLBrowserInterface getIDLBrowser()
  {   return idlBrowser;
  }

  public SessionRecorderInterface getSessionRecorder()
  {   return sR;
  }

   public void recEnable()
   {    println("inizio sessione di registrazione");
        recEnable = true;
   }

   public void recDisable()
   {    println("fine sessione di registrazione");
        recEnable = false;
   }

   public boolean isRecEnabled()
   {    return recEnable;
   }

 /* public RequestResponseRecordManagement getRequestResponseRecordManagement()
  {   return RRRM;
  }*/

}