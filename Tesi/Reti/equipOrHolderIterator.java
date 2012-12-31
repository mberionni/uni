package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public class equipOrHolderIterator extends equipment.EquipmentOrHolderIterator_IPOA
{ protected equipment.EquipmentOrHolder_T[] list;
  protected int k;

   public equipOrHolderIterator(equipment.EquipmentOrHolder_T[] _list)
  {   list = _list;
      k = 0;
      println("creazione eq or holder iterator - list.length = "+ list.length);
  }

   /**
   * <pre>
   *   boolean next_n (in unsigned long how_many,
                  out equipment.EquipmentOrHolderList_T equipmentOrHolderList)
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public boolean next_n (int how_many, equipment.EquipmentOrHolderList_THolder equipmentOrHolderList) throws globaldefs.ProcessingFailureException
  {   boolean ret;
      int remains = list.length - k;
      println("Richiamato metodo next_n con how_many = " + how_many);
      if (how_many > remains) throw new globaldefs.ProcessingFailureException(globaldefs.ExceptionType_T.EXCPT_CAPACITY_EXCEEDED, "hey! hai specificato un valore troppo alto per il parametro how_many: max = " + remains);
      try {    remains = list.length - k;
               println("elementi ancora da leggere = " + remains);
               int minimo = 0;
               if (remains < how_many) minimo = remains;
               else minimo = how_many;
               equipment.EquipmentOrHolder_T[] listTemp = new  equipment.EquipmentOrHolder_T[minimo];
               for (int ix=0; ix < minimo; k++, ix++)
               {  listTemp[ix] = list[k];
               }
               equipmentOrHolderList.value = listTemp;
               if (k < remains) ret = true;
                  else ret = false;
               println("metodo eseguito correttamente - ritorno " + ret);
               return ret;
           }   catch (Exception e) {   println(e.toString());
                                       e.printStackTrace();
                                       return false;
                                   }
  }


  /**
   * <pre>
   *   unsigned long getLength ()
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public int getLength () throws globaldefs.ProcessingFailureException
  {   println("Richiamato metodo getLength");
      return list.length - k;
  }

  /**
   * <pre>
   *   void destroy ()
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void destroy () throws globaldefs.ProcessingFailureException
  {   println("Richiamato metodo destroy - implementazione nulla");
  }

  public void println(String text)
  {   StartServer.getMonitorWindow().println(text);
      //System.out.println(text);
  }
}