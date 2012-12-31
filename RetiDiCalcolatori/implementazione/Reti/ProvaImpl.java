package Reti;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class ProvaImpl extends ProvaService.sommaPOA
{
   public String sommaConcatena(int add1, int add2, String add3)
   { System.out.println("arrivata richiesta per sommaConcatena");
     int s = add1 + add2;
     String ret = (new Integer(s)).toString() + add3;
     return ret;
   }

   public String concatena(int add1, int add2, String add3)
   { System.out.println("arrivata richiesta per concatena");
     String ret = (new Integer(add1)).toString() + (new Integer(add2)).toString() + add3;
     return ret;
   }

   public int calcola(int add1, int add2, int mul, int div)
   { System.out.println("arrivata richiesta per calcola");
     int ret = ( (add1 + add2 ) * mul ) / div;
     return ret;
   }
}