package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

public class currMaintOpIt extends maintenanceOps.CurrentMaintenanceOperationIterator_IPOA
{ maintenanceOps.CurrentMaintenanceOperation_T[] list;
  public int k;

  public currMaintOpIt(maintenanceOps.CurrentMaintenanceOperation_T[] _list)
  {   list = _list;
      k = 0;
  }

   public boolean next_n (int how_many,
                         maintenanceOps.CurrentMaintenanceOperationList_THolder cMOList) throws globaldefs.ProcessingFailureException
   {  maintenanceOps.CurrentMaintenanceOperation_T[] listTemp = new  maintenanceOps.CurrentMaintenanceOperation_T[] {};
      for (int ix=0; k < list.length && k < how_many; k++, ix++)
      {  listTemp[ix] = list[k];
      }
      cMOList = new maintenanceOps.CurrentMaintenanceOperationList_THolder(listTemp);
      if (k < list.length) return true;
      else return false;
   }

  /**
   * <pre>
   *   unsigned long getLength ()
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public int getLength () throws globaldefs.ProcessingFailureException
  { return list.length;
  }

  /**
   * <pre>
   *   void destroy ()
    raises (globaldefs.ProcessingFailureException);
   * </pre>
   */
  public void destroy () throws globaldefs.ProcessingFailureException
  {   System.out.println("sto distruggendo l'oggetto iteratore. WOW!");
  }
}