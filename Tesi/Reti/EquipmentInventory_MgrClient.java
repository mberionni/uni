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
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

public class EquipmentInventory_MgrClient extends RequestResponseRecordManagement
{ protected int requestId = 0;
  protected equipment.EquipmentInventoryMgr_I equipmentMgr;
  protected Repository ir = Client.ir;
  protected creaOggetto how_manyArg;
  protected DefaultMutableTreeNode meOrHolderNameArg, EQTCreateDataArg;
  protected IDLBrowserInterface idlBrowser;

  public EquipmentInventory_MgrClient(LowLevelInteractionManagement _actions)
  {   actions = _actions;
      idlBrowser = actions.getIDLBrowser();
  }

  public void richiesta (creaOggetto oggNodoSel, org.omg.CORBA.Object rif) throws globaldefs.ProcessingFailureException
  {   equipmentMgr = equipment.EquipmentInventoryMgr_IHelper.narrow(rif);
      actionName = oggNodoSel.toString();
      if ( (oggNodoSel.name).compareTo("getAllEquipment") == 0 )
          {   requestId = 1;
              getAllEquipment(oggNodoSel);
          }
          else if ( (oggNodoSel.name).compareTo("provisionEquipment") == 0 )
          {   requestId = 2;
              provisionEquipment();
          }
          else
          {   println("sigh! metodo non ancora supportato");
          }
  }

  protected void getAllEquipment(creaOggetto _oggNodoSel)
  {  println("gestione richiesta getAllEquipment");
     try
      {    OperationDef operazione = OperationDefHelper.narrow(_oggNodoSel.tipo);
           ParameterDescription[] parameters = operazione.params();
           AliasDef ad = null;
           try  //attenzione codice pericoloso! si va toccare l'IR! rivedere!
           {    ad = ir.create_alias("IDL:My_mtnm.tmforum.org/temp:1.0", parameters[0].name, "1.0", parameters[0].type_def);
           }    catch (Exception e)   {   println("ho catturato una fantastica eccezione!");
                                          ir.lookup_id("IDL:My_mtnm.tmforum.org/temp:1.0").destroy();
                                          ad = ir.create_alias("IDL:My_mtnm.tmforum.org/temp:1.0", "meOrHolderName", "1.0", parameters[0].type_def);
                                      }
           DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode("root", true);
           DefaultMutableTreeNode app = idlBrowser.browse(ad, rootRequestNode, equipmentMgr, true);
           meOrHolderNameArg = (DefaultMutableTreeNode)app.getChildAt(0);
           rootRequestNode.add(meOrHolderNameArg);
           IDLType tipoLong = ConfigDataAccessClient.tipoLong;
           how_manyArg = new creaOggetto(tipoLong, "int ", "how_many", "", null);  //costruzione dei nodi che rappresentano i parametri
           app = new DefaultMutableTreeNode(how_manyArg, true);
           rootRequestNode.add(app);
           setRequest(rootRequestNode, this);
      }    catch (Exception e) {  e.printStackTrace(); }
  }

  protected void execGetAllEquipment(globaldefs.NameAndStringValue_T[] ing, int how_many)
  {  //costruzione parametri tipo out
     equipment.EquipmentOrHolder_T[] eh = { new equipment.EquipmentOrHolder_T() };
     equipment.EquipmentOrHolderList_THolder eqList = new equipment.EquipmentOrHolderList_THolder(eh);
     equipment.EquipmentOrHolderIterator_IHolder eqIt = new equipment.EquipmentOrHolderIterator_IHolder();
     //fine costruzione parametri
     try
     {   equipmentMgr.getAllEquipment(ing, how_many, eqList, eqIt);
         println("richiesta remota effettuata correttamente");
         equipment.EquipmentOrHolder_T[] eq = eqList.value;
         String textualResponse = "";
         //creaOggetto rootResponseObject = new creaOggetto(tipoStringa, "response", "", "", null);
         DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode("root", true);
         for (int k = 0; k < eq.length; k++)
         {    if (eq[k].discriminator() == equipment.EquipmentTypeQualifier_T.EQT)
              {   equipment.Equipment_T eqp =  eq[k].equip();
                  DefaultMutableTreeNode structNode = eqp.makeTree(new DefaultMutableTreeNode("Root", true));
                  println("wow! e' un equipment "+structNode.getChildCount());
                  textualResponse = textualResponse + eq[k].toString();
                  rootResponseNode.add((DefaultMutableTreeNode)structNode.getChildAt(0));
              }
              else
              {   println("urp! per ora non è implementato il caso degli holder !");
              }
          }

          if (eqIt.value != null)
          {    //creaOggetto rootResponseObject = new creaOggetto(tipoStringa, "response", "", "", null);
               //DefaultMutableTreeNode rootResponseNodeIterator = new DefaultMutableTreeNode(rootResponseObject, true);
               DefaultMutableTreeNode responseNode = browseObject(eqIt.value, new DefaultMutableTreeNode("Root", true));
               rootResponseNode.add((DefaultMutableTreeNode)responseNode.getChildAt(0));
               textualResponse = textualResponse + "\n interface ::equipment::EquipmentOrHolderIterator_I";
          }
          setResult(rootResponseNode, textualResponse);
     }    catch (globaldefs.ProcessingFailureException e) {   e.printStackTrace();
                                                              showMessageDialog(e.errorReason, ERROR_MESSAGE);
                                                          }
  }

   protected void provisionEquipment() throws globaldefs.ProcessingFailureException
   {  try
      {   println("gestione richiesta provisionEquipment");
          Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/equipment/EQTCreateData_T:1.0");
          controlla(contained);
          creaOggetto rootRequestObject = new creaOggetto(tipoStringa, "request", "", "", null);
          DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode(rootRequestObject, true);

          DefaultMutableTreeNode app = idlBrowser.browse(contained, rootRequestNode, equipmentMgr, false);
          EQTCreateDataArg = (DefaultMutableTreeNode)app.getChildAt(0);
          setRequest(app, this);
      } catch (java.lang.Exception e) {   e.printStackTrace();
                                      }

   }

   protected void execProvisionEquipment(equipment.EQTCreateData_T ingEQT)
   {   try
       {   equipment.Equipment_THolder createdEquipment = new equipment.Equipment_THolder();
           // remote request
           equipmentMgr.provisionEquipment(ingEQT, createdEquipment);
           // end request
           equipment.Equipment_T equipment = createdEquipment.value;
           creaOggetto rootResponseObject = new creaOggetto(tipoStringa, "response", "", "", null);
           DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
           DefaultMutableTreeNode structNode = equipment.makeTree(new DefaultMutableTreeNode("Root", true));
           rootResponseNode.add((DefaultMutableTreeNode)structNode.getChildAt(0));
           setResult(rootResponseNode, equipment.toString());
       }   catch (globaldefs.ProcessingFailureException e) {   e.printStackTrace();
                                                               showMessageDialog(e.errorReason, ERROR_MESSAGE);
                                                           }
   }

  public void returnType()
  {   try
      {   switch (requestId)
          {   case 0:    println("Sob! Nessuna richiesta in corso");
                         break;
              case 1:    while ( !  ( ((creaOggetto)meOrHolderNameArg.getUserObject()).tipo instanceof StructDef) )
                         {    meOrHolderNameArg = (DefaultMutableTreeNode)meOrHolderNameArg.getFirstChild();
                         }
                         DefaultMutableTreeNode sequence = (DefaultMutableTreeNode)meOrHolderNameArg.getParent();
                         Enumeration enum = sequence.children();
                         globaldefs.NameAndStringValue_T[] ing = new globaldefs.NameAndStringValue_T[sequence.getChildCount()];
                         createNameAndStringValueList(enum, ing);
                         int how_many = new Integer(how_manyArg.getValue()).intValue();
                         execGetAllEquipment(ing, how_many);
                         break;
              case 2:    equipment.EQTCreateData_T ingEQT = new equipment.EQTCreateData_T();
                         ingEQT.userLabel = ((creaOggetto)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(0)).getUserObject()).getValue();
                         ingEQT.forceUniqueness = new Boolean(((creaOggetto)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(1)).getUserObject()).getValue()).booleanValue();
                         ingEQT.owner = ((creaOggetto)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(2)).getUserObject()).getValue();
                         DefaultMutableTreeNode expectedEquipmentObjectType = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(3);
                         ingEQT.expectedEquipmentObjectType = ((creaOggetto)((DefaultMutableTreeNode)expectedEquipmentObjectType.getChildAt(0)).getUserObject()).getValue();
                         DefaultMutableTreeNode equipmentHolderNameArg = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(4);
                         while ( !  ( ((creaOggetto)equipmentHolderNameArg.getUserObject()).tipo instanceof StructDef) )
                         {    equipmentHolderNameArg = (DefaultMutableTreeNode)equipmentHolderNameArg.getFirstChild();
                         }
                         sequence = (DefaultMutableTreeNode)equipmentHolderNameArg.getParent();
                         enum = sequence.children();
                         ingEQT.equipmentHolderName = new globaldefs.NameAndStringValue_T[sequence.getChildCount()];
                         createNameAndStringValueList(enum, ingEQT.equipmentHolderName);
                         DefaultMutableTreeNode additionalInfoArg = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(5);
                         while ( !  ( ((creaOggetto)additionalInfoArg.getUserObject()).tipo instanceof StructDef) )
                         {    additionalInfoArg = (DefaultMutableTreeNode)additionalInfoArg.getFirstChild();
                         }
                         sequence = (DefaultMutableTreeNode)additionalInfoArg.getParent();
                         enum = sequence.children();
                         ingEQT.additionalInfo = new globaldefs.NameAndStringValue_T[sequence.getChildCount()];
                         createNameAndStringValueList(enum, ingEQT.additionalInfo);
                         execProvisionEquipment(ingEQT);
                         break;
              default:   println("visualizzazione valore di ritorno non implementata!");
          }
      }   catch (Exception e)
          {   e.printStackTrace();
              println(e.toString());
          }
  }

  protected void createNameAndStringValueList(Enumeration enum, globaldefs.NameAndStringValue_T[] ing)
  {   int i = 0;
      while (enum.hasMoreElements())
      {    DefaultMutableTreeNode struct = (DefaultMutableTreeNode)enum.nextElement();
           String name = ((creaOggetto)(((DefaultMutableTreeNode)struct.getChildAt(0)).getUserObject())).getValue();
           String value = ((creaOggetto)(((DefaultMutableTreeNode)struct.getChildAt(1)).getUserObject())).getValue();
           ing[i] = new globaldefs.NameAndStringValue_T(name, value);
           i++;
      }
  }

   protected void controlla(Contained contained) throws java.lang.Exception
   {   if (contained == null)
       {  System.out.println("la ricerca nell'interface repository non ha trovato quanto richiesto: " + contained.name());
          throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
       }
   }

}