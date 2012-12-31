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

public class creaOggetto
{ public org.omg.CORBA.IRObject tipo;
  public String description, name, signature;
  public org.omg.CORBA.Object rif;


  public creaOggetto(org.omg.CORBA.IRObject _tipo, String _description, String _name, String _signature, org.omg.CORBA.Object _rif)
  {  tipo = _tipo;  //moduleDef, interfaceDef, ....
     description = _description;
     name = _name;
     signature= _signature;
     rif = _rif;
  }

  public String toString()
  {   //System.out.println("metodo toString="+nome  );
      return description + name + signature;
  }

   public org.omg.CORBA.Object getRif()
  {   return rif;
  }

}