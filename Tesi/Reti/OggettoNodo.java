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

public class OggettoNodo extends java.lang.Object implements Serializable
{ private transient IRObject oggettoIR;
  private transient org.omg.CORBA.Object IOR;
  private String description, name, signature, value;
  private boolean isList;
  private int tipoNumerico;

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
  protected int attributeType = ConfigDataAccessClient.attributeType;
  protected int defaultType = ConfigDataAccessClient.defaultType;

  public OggettoNodo(IRObject _oggettoIR, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR)
  {   initOggetto(_oggettoIR, _description, _name, _signature, _IOR, false);
  }

  public OggettoNodo(IRObject _oggettoIR, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR, boolean _isList)
  {   initOggetto(_oggettoIR, _description, _name, _signature, _IOR, _isList);
  }

  public void initOggetto(IRObject _oggettoIR, String _description, String _name, String _signature, org.omg.CORBA.Object _IOR, boolean _isList)
  {  oggettoIR = _oggettoIR;
     IOR = _IOR;
     isList = _isList;
     description = _description;
     name = _name;
     signature = _signature;
     impostaParametroValue(isList);
     impostaParametroTipoNumerico(oggettoIR);
  }

  protected void impostaParametroValue(boolean _isList)
  {  if (_isList)
     {    value = "elemento di lista";
     }
     else
     {    value = "";
     }
  }

  protected void impostaParametroTipoNumerico(IRObject oggettoIR)
  {   if (oggettoIR == null)
      {   tipoNumerico = defaultType;
      }
      else
      switch(oggettoIR.def_kind().value())
      {   case DefinitionKind._dk_Module:   tipoNumerico = moduleType;
                                            break;
          case DefinitionKind._dk_Interface:tipoNumerico = interfaceType;
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
          case DefinitionKind._dk_Attribute:tipoNumerico = attributeType;
                                            break;
          default:                          tipoNumerico = defaultType;
      }
  }

  public String toString()
  {   return description + name + signature;
  }

  protected int getTipoNumerico()
  {   return tipoNumerico;
  }

  protected IRObject getOggettoIR()
  {   return oggettoIR;
  }

  public org.omg.CORBA.Object getIOR()
  {   return IOR;
  }

  protected String getValue()
  {   return value;
  }

  protected String getName()
  {   return name;
  }

  protected String getDescription()
  {   return description;
  }

  protected String getSignature()
  {   return signature;
  }

  protected boolean isList()
  {   return isList;
  }

  public void setValue(String _value)
  {   //System.out.println("setValue in oggettoNodo " + value);
      if ( (this.oggettoIR instanceof org.omg.CORBA.Container) && (!this.isList) )
      {   //System.out.println("attenzione. stai tentando di impostare il valore di un nodo di tipo Container");
      }
      else
      {  value = _value;
      }
  }

}