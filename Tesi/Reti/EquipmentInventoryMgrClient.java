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

public class EquipmentInventoryMgrClient extends RequestResponseManagement
{ protected int requestId = 0;
  protected equipment.EquipmentInventoryMgr_I equipmentMgr;
  protected Repository ir = Client.ir;
  protected OggettoNodo how_manyArg;
  protected DefaultMutableTreeNode meOrHolderNameArg, EQTCreateDataArg;

   public EquipmentInventoryMgrClient(InteractionManagementInterface _actions)
  {   setActions(_actions);
  }

  public void request (OggettoNodo oggNodoSel, org.omg.CORBA.Object rif, String mode)
  {   equipmentMgr = equipment.EquipmentInventoryMgr_IHelper.narrow(rif);
      actionName = oggNodoSel.toString();
      if ( (oggNodoSel.getName()).compareTo("getAllEquipment") == 0 )
          {   requestId = 1;
              getAllEquipment();
          }
          else if ( (oggNodoSel.getName()).compareTo("provisionEquipment") == 0 )
          {   requestId = 2;
              provisionEquipment();
          }
          else if ( (oggNodoSel.getName()).compareTo("getCapabilities") == 0 )
          {   requestId = 3;
              getCapabilities();
          }
          else if ( (oggNodoSel.getName()).compareTo("setNativeEMSName") == 0 )
          {   requestId = 4;
              setNativeEMSName();
          }
          else if ( (oggNodoSel.getName()).compareTo("setOwner") == 0 )
          {   requestId = 5;
              setOwner();
          }
          else if ( (oggNodoSel.getName()).compareTo("setUserLabel") == 0 )
          {   requestId = 6;
              setUserLabel();
          }
          else
          {   println("sigh! metodo non ancora supportato");
          }
  }

  protected void getAllEquipment()
  {  println("gestione richiesta getAllEquipment");
     try
      {    Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/NamingAttributes_T:1.0");
           controlla(contained);
           DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode("root", true);
           DefaultMutableTreeNode app = getActions().getIDLBrowser().browse(contained, rootRequestNode, null, true);
           meOrHolderNameArg = (DefaultMutableTreeNode)app.getChildAt(0);
           rootRequestNode.add(meOrHolderNameArg);
           IDLType tipoLong = ConfigDataAccessClient.tipoLong;
           how_manyArg = new OggettoNodo(tipoLong, "int ", "how_many", "", null);  //costruzione dei nodi che rappresentano i parametri
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
     {   println("prima di chiamata getAllEquipment");
         equipmentMgr.getAllEquipment(ing, how_many, eqList, eqIt);  // chiamata remota
         println("richiesta remota effettuata correttamente");
         equipment.EquipmentOrHolder_T[] eq = eqList.value;
         String textualResponse = "";
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
          {    DefaultMutableTreeNode responseNode = browseObject(eqIt.value);
               rootResponseNode.add((DefaultMutableTreeNode)responseNode.getChildAt(0));
               textualResponse = textualResponse + "\n interface ::equipment::EquipmentOrHolderIterator_I";
          }
          setResult(rootResponseNode, textualResponse);
      }   catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  println(e.toString());
                              }
  }

   protected void provisionEquipment()
   {  try
      {   println("gestione richiesta provisionEquipment");
          Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/equipment/EQTCreateData_T:1.0");
          controlla(contained);
          OggettoNodo rootRequestObject = new OggettoNodo(tipoStringa, "request", "", "", null);
          DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode(rootRequestObject, true);
          DefaultMutableTreeNode app = getActions().getIDLBrowser().browse(contained, rootRequestNode, null, false);
          EQTCreateDataArg = (DefaultMutableTreeNode)app.getChildAt(0);
          setRequest(app, this);
      }   catch (java.lang.Exception e) {   e.printStackTrace();
                                      }
   }

   protected void execProvisionEquipment(equipment.EQTCreateData_T ingEQT)
   {   try
       {   equipment.Equipment_THolder createdEquipment = new equipment.Equipment_THolder();
           // remote request
           equipmentMgr.provisionEquipment(ingEQT, createdEquipment);   //chiamata remota
           // end request
           equipment.Equipment_T equipment = createdEquipment.value;
           OggettoNodo rootResponseObject = new OggettoNodo(tipoStringa, "response", "", "", null);
           DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode(rootResponseObject, true);
           DefaultMutableTreeNode structNode = equipment.makeTree(new DefaultMutableTreeNode("Root", true));
           rootResponseNode.add((DefaultMutableTreeNode)structNode.getChildAt(0));
           setResult(rootResponseNode, equipment.toString());
       }   catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                   else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                   println(e.toString());
                                }
   }

   protected void getCapabilities()
   {    println("sigh! metodo non ancora supportato");
   }

   protected void setNativeEMSName()
   {    println("sigh! metodo non ancora supportato");
   }

   protected void setOwner()
   {    println("sigh! metodo non ancora supportato");
   }

   protected void setUserLabel()
   {    println("sigh! metodo non ancora supportato");
   }

   public void returnFromIns()
   {  try
      {   switch (requestId)
          {   case 0:    println("Sob! Nessuna richiesta in corso");
                         break;
              case 1:    while ( !  ( ((OggettoNodo)meOrHolderNameArg.getUserObject()).getOggettoIR() instanceof StructDef) )
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
                         ingEQT.userLabel = ((OggettoNodo)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(0)).getUserObject()).getValue();
                         ingEQT.forceUniqueness = new Boolean(((OggettoNodo)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(1)).getUserObject()).getValue()).booleanValue();
                         ingEQT.owner = ((OggettoNodo)((DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(2)).getUserObject()).getValue();
                         DefaultMutableTreeNode expectedEquipmentObjectType = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(3);
                         ingEQT.expectedEquipmentObjectType = ((OggettoNodo)((DefaultMutableTreeNode)expectedEquipmentObjectType.getChildAt(0)).getUserObject()).getValue();
                         DefaultMutableTreeNode equipmentHolderNameArg = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(4);
                         while ( !  ( ((OggettoNodo)equipmentHolderNameArg.getUserObject()).getOggettoIR() instanceof StructDef) )
                         {    equipmentHolderNameArg = (DefaultMutableTreeNode)equipmentHolderNameArg.getFirstChild();
                         }
                         sequence = (DefaultMutableTreeNode)equipmentHolderNameArg.getParent();
                         enum = sequence.children();
                         ingEQT.equipmentHolderName = new globaldefs.NameAndStringValue_T[sequence.getChildCount()];
                         createNameAndStringValueList(enum, ingEQT.equipmentHolderName);
                         DefaultMutableTreeNode additionalInfoArg = (DefaultMutableTreeNode)EQTCreateDataArg.getChildAt(5);
                         while ( !  ( ((OggettoNodo)additionalInfoArg.getUserObject()).getOggettoIR() instanceof StructDef) )
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
           String name = ((OggettoNodo)(((DefaultMutableTreeNode)struct.getChildAt(0)).getUserObject())).getValue();
           String value = ((OggettoNodo)(((DefaultMutableTreeNode)struct.getChildAt(1)).getUserObject())).getValue();
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