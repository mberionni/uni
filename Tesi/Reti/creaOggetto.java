package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.io.Serializable;
import org.omg.CORBA.IRObject;
import org.omg.CORBA.DefinitionKind;

public class creaOggetto extends java.lang.Object implements Serializable
{ protected transient IRObject tipo;
  protected transient org.omg.CORBA.Object IOR;
  protected String description, name, signature, value;
  protected boolean IsList;
  protected int tipoNumerico;

  protected int moduleType = ConfigDataAccessClient.moduleType;
  protected int interfaceType = ConfigDataAccessClient.interfaceType;
  protected int operationType = ConfigDataAccessClient.operationType;
  protected int exceptionType = ConfigDataAccessClient.exceptionType;
  protected int enumType = ConfigDataAccessClient.enumType;
  protected int unionType = ConfigDataAccessClient.unionType;
  protected int structType = ConfigDataAccessClient.structType;
  protected int aliasType = ConfigDataAccessClient.aliasType;
  protected int sequenceType = ConfigDataAccessClient.sequenceType;
  protected int typedefType = ConfigDataAccessClient.typedefType;
  protected int arrayType = ConfigDataAccessClient.arrayType;
  protected int defaultType = ConfigDataAccessClient.defaultType;

  public creaOggetto(IRObject _tipo, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR)
  {   initOggetto(_tipo, _description, _name, _signature, _IOR, false);
  }

  public creaOggetto(IRObject _tipo, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR, boolean _IsList)
  {   initOggetto(_tipo, _description, _name, _signature, _IOR, _IsList);
  }

  public void initOggetto(IRObject _tipo, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR, boolean _IsList)
  {  tipo = _tipo;
     description = _description;
     name = _name;
     signature = _signature;
     IOR = _IOR;
     IsList = _IsList;
     impostaParametroValue(IsList);
     impostaParametroTipoNumerico(tipo);
  }

  protected void impostaParametroValue(boolean _IsList)
  {  if (_IsList)
     {    value = "elemento di lista";
     }
     else
     {    value = "";
     }
  }

  protected void impostaParametroTipoNumerico(IRObject tipo)
  {   if (tipo == null)
      {   tipoNumerico = defaultType;
      }
      else
      switch(tipo.def_kind().value())
      {   case DefinitionKind._dk_Module:   tipoNumerico = moduleType;
                                            break;
          case DefinitionKind._dk_Interface: tipoNumerico = interfaceType;
                                            break;
          case DefinitionKind._dk_Operation:tipoNumerico = operationType;
                                            break;
          case DefinitionKind._dk_Exception:tipoNumerico = exceptionType;
                                            break;
          case DefinitionKind._dk_Enum:     tipoNumerico = enumType;
                                            break;
          case DefinitionKind._dk_Struct:   tipoNumerico = structType;
                                            break;
          case DefinitionKind._dk_Sequence: tipoNumerico = sequenceType;
                                            break;
          case DefinitionKind._dk_Union:    tipoNumerico = unionType;
                                            break;
          case DefinitionKind._dk_Alias:    tipoNumerico = aliasType;
                                            break;
          case DefinitionKind._dk_Typedef:  tipoNumerico = typedefType;
                                            break;
          case DefinitionKind._dk_Array:    tipoNumerico = arrayType;
                                            break;
          default:                          tipoNumerico = defaultType;
      }
  }

  public String toString()
  {   return description + name + signature;
  }

  public int getTipoNumerico()
  {   return tipoNumerico;
  }

   public org.omg.CORBA.Object getIOR()
  {   return IOR;
  }

  public String getValue()
  {   return value;
  }

  public String getName()
  {   return name;
  }

  public boolean isList()
  {   return IsList;
  }

  public void setValue(String _value)
  {   if ( (this.tipo instanceof org.omg.CORBA.Container) && (!this.IsList) )
      {   //System.out.println("attenzione. stai tentando di impostare il valore di un nodo di tipo Container");
      }
      else
      {  value = _value;

      }
  }

}