package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. Corradi - ing. Maccaferri
 * @version 1.0
 */
import org.omg.CORBA.IDLType;
import org.omg.CORBA.PrimitiveKind;
import javax.swing.tree.DefaultMutableTreeNode;

public class EquipmentOrHolderIteratorClient extends RequestResponseManagement
{ int requestId = 0;
  OggettoNodo how_manyArg;
  equipment.EquipmentOrHolderIterator_I iterator;

  public EquipmentOrHolderIteratorClient(InteractionManagementInterface _actions)
  {   setActions(_actions);
  }

  public void request (OggettoNodo oggNodoSel, org.omg.CORBA.Object _rif, String mode)
  {    actionName = oggNodoSel.toString();
       iterator = equipment.EquipmentOrHolderIterator_IHelper.narrow(_rif);
       if ( (oggNodoSel.getName()).compareTo("getLength") == 0 )
          {   getLength();
          }
       else if ( (oggNodoSel.getName()).compareTo("destroy") == 0 )
          {   destroy();
          }
       else if ( (oggNodoSel.getName()).compareTo("next_n") == 0 )
          {   requestId = 1;
              next_n();
          }
       else println("INTERNAL ERROR: il metodo non esiste!");
  }

  public void next_n()
  {   println("costruzione richiesta metodo next_n");
      IDLType tipoLong = ConfigDataAccessClient.tipoLong;
      DefaultMutableTreeNode rootRequestNode = new DefaultMutableTreeNode("root", true);
      how_manyArg = new OggettoNodo(tipoLong, "int ", "how_many", "", null);  //costruzione dei nodi che rappresentano i parametri
      DefaultMutableTreeNode app = new DefaultMutableTreeNode(how_manyArg, true);
      rootRequestNode.add(app);
      setRequest(rootRequestNode, this);

  }

  public void execNext_n()
  {   try
      {   equipment.EquipmentOrHolderList_THolder holder = new equipment.EquipmentOrHolderList_THolder();
          int how_many = new Integer(how_manyArg.getValue()).intValue();
          boolean ret = iterator.next_n(how_many, holder); // chiamata remota
          equipment.EquipmentOrHolder_T[] eq = holder.value;
          String textualResponse = "";
          DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode("root", true);
          for (int k = 0; k < eq.length; k++)
          {   if (eq[k].discriminator() == equipment.EquipmentTypeQualifier_T.EQT)
              {   equipment.Equipment_T eqp =  eq[k].equip();
                  DefaultMutableTreeNode structNode = eqp.makeTree(new DefaultMutableTreeNode("Root", true));
                  textualResponse = textualResponse + eqp.toString();
                  rootResponseNode.add((DefaultMutableTreeNode)structNode.getChildAt(0));
              }
              else
              {   println("urp! per ora non è implementato il caso degli holder !");
              }
         }
         setResult(rootResponseNode, textualResponse);
         println("... restituito una holderlist e un booleano di valore = "+ret);
       }   catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  println(e.toString());
                               }
  }

  public void getLength ()
  {  try {   println("invocato metodo getLength...");
             setRequestLog(null);
             int length = iterator.getLength();    // chiamata remota
             String result = new Integer(length).toString();
             DefaultMutableTreeNode rootResponseNode = new DefaultMutableTreeNode("root", true);
             OggettoNodo ogg = new OggettoNodo(tipoLong, "", result, "", null);
             DefaultMutableTreeNode node = new DefaultMutableTreeNode(ogg, true);
             rootResponseNode.add(node);
             setResult(rootResponseNode, result);
             println("... restituito valore "+length);
         }   catch (Exception e) {   if (e instanceof globaldefs.ProcessingFailureException)
                                       showMessageDialog(((globaldefs.ProcessingFailureException)e).errorReason, ERROR_MESSAGE);
                                  else showMessageDialog("Sob! server not found!",  ERROR_MESSAGE);
                                  println(e.toString());
                                 }
  }

  public void destroy ()
  {   try
      {   println("invocato metodo destroy...");
          iterator.destroy();
          println("... azione effettuata - non e' previsto valore di ritorno");
      }   catch (globaldefs.ProcessingFailureException e) {   e.printStackTrace();
                                                          }
  }

  public void returnFromIns()
  {   switch (requestId)
          {   case 0:    println("Sob! Nessuna richiesta in corso");
                         break;
              case 1:    execNext_n();

          }
  }

}