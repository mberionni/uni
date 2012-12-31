package equipment;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "equipment.idl"
 * <li> <b>IDL Name</b>      ::equipment::Equipment_T
 * <li> <b>Repository Id</b> IDL:mtnm.tmforum.org/equipment/Equipment_T:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * struct Equipment_T {
  ...
};
 * </pre>
 */
 import javax.swing.tree.*;
 import org.omg.CORBA.*;
 import Reti.creaOggetto;

public final class Equipment_T implements org.omg.CORBA.portable.IDLEntity {
  public globaldefs.NameAndStringValue_T[] name;
  public java.lang.String userLabel;
  public java.lang.String nativeEMSName;
  public java.lang.String owner;
  public boolean alarmReportingIndicator;
  public equipment.ServiceState_T serviceState;
  public java.lang.String expectedEquipmentObjectType;
  public java.lang.String installedEquipmentObjectType;
  public java.lang.String installedPartNumber;
  public java.lang.String installedVersion;
  public java.lang.String installedSerialNumber;
  public globaldefs.NameAndStringValue_T[] additionalInfo;

  public Equipment_T ()
  {  }

  public Equipment_T (final globaldefs.NameAndStringValue_T[] name,
                      final java.lang.String userLabel,
                      final java.lang.String nativeEMSName,
                      final java.lang.String owner,
                      final boolean alarmReportingIndicator,
                      final equipment.ServiceState_T serviceState,
                      final java.lang.String expectedEquipmentObjectType,
                      final java.lang.String installedEquipmentObjectType,
                      final java.lang.String installedPartNumber,
                      final java.lang.String installedVersion,
                      final java.lang.String installedSerialNumber,
                      final globaldefs.NameAndStringValue_T[] additionalInfo) {
    this.name = name;
    this.userLabel = userLabel;
    this.nativeEMSName = nativeEMSName;
    this.owner = owner;
    this.alarmReportingIndicator = alarmReportingIndicator;
    this.serviceState = serviceState;
    this.expectedEquipmentObjectType = expectedEquipmentObjectType;
    this.installedEquipmentObjectType = installedEquipmentObjectType;
    this.installedPartNumber = installedPartNumber;
    this.installedVersion = installedVersion;
    this.installedSerialNumber = installedSerialNumber;
    this.additionalInfo = additionalInfo;
  }

  private transient java.util.Hashtable _printMap = null;
  public java.lang.String toString() {
    final java.lang.StringBuffer _ret = new java.lang.StringBuffer("struct equipment.Equipment_T {");
    final java.lang.Thread _currentThread = java.lang.Thread.currentThread();
    boolean justCreated = false;
    if (_printMap == null) {
      synchronized (this) {
        if (_printMap == null) {
          justCreated = true;
          _printMap = new java.util.Hashtable();
        }
      }
    }
    if (!justCreated) {
      if (_printMap.get(_currentThread) != null) {
        _ret.append("...}");
        return _ret.toString();
      }
    }
    _printMap.put(_currentThread, this);
    _ret.append("\n");
    _ret.append("globaldefs.NameAndStringValue_T[] name=");
    _ret.append("{");
    if (name == null) {
      _ret.append(name);
    } else {
      for (int $counter3 = 0; $counter3 < name.length; $counter3++) {
        _ret.append(name[$counter3]);
        if ($counter3 < name.length - 1) {
          _ret.append(",");
        }
      }
    }
    _ret.append("}");
    _ret.append(",\n");
    _ret.append("java.lang.String userLabel=");
    _ret.append(userLabel != null?'\"' + userLabel + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String nativeEMSName=");
    _ret.append(nativeEMSName != null?'\"' + nativeEMSName + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String owner=");
    _ret.append(owner != null?'\"' + owner + '\"':null);
    _ret.append(",\n");
    _ret.append("boolean alarmReportingIndicator=");
    _ret.append(alarmReportingIndicator);
    _ret.append(",\n");
    _ret.append("equipment.ServiceState_T serviceState=");
    _ret.append(serviceState);
    _ret.append(",\n");
    _ret.append("java.lang.String expectedEquipmentObjectType=");
    _ret.append(expectedEquipmentObjectType != null?'\"' + expectedEquipmentObjectType + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String installedEquipmentObjectType=");
    _ret.append(installedEquipmentObjectType != null?'\"' + installedEquipmentObjectType + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String installedPartNumber=");
    _ret.append(installedPartNumber != null?'\"' + installedPartNumber + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String installedVersion=");
    _ret.append(installedVersion != null?'\"' + installedVersion + '\"':null);
    _ret.append(",\n");
    _ret.append("java.lang.String installedSerialNumber=");
    _ret.append(installedSerialNumber != null?'\"' + installedSerialNumber + '\"':null);
    _ret.append(",\n");
    _ret.append("globaldefs.NameAndStringValue_T[] additionalInfo=");
    _ret.append("{");
    if (additionalInfo == null) {
      _ret.append(additionalInfo);
    } else {
      for (int $counter4 = 0; $counter4 < additionalInfo.length; $counter4++) {
        _ret.append(additionalInfo[$counter4]);
        if ($counter4 < additionalInfo.length - 1) {
          _ret.append(",");
        }
      }
    }
    _ret.append("}");
    _ret.append("\n");
    _printMap.remove(_currentThread);
    _ret.append("}");
    return _ret.toString();
  }

  public DefaultMutableTreeNode makeTree(DefaultMutableTreeNode node)
  { DefaultMutableTreeNode app;
    DefaultMutableTreeNode NameAndStringValue;
    Repository ir = Reti.Client.getRepository();
    IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
    IDLType tipoBoolean = ir.get_primitive(PrimitiveKind.pk_boolean);
    IDLType tipoArray = ir.create_array(0, tipoStringa);
    Contained contained = ir.lookup_id("IDL:mtnm.tmforum.org/equipment/Equipment_T:1.0");
    try
    {   controlla(contained);
    }   catch (java.lang.Exception e) {   e.printStackTrace();
                                      }
    StructDef TipoStruct = StructDefHelper.narrow(contained);

    creaOggetto ogg = new creaOggetto(TipoStruct, "struct equipment.Equipment_T", "", "", null, false);
    DefaultMutableTreeNode struct = new DefaultMutableTreeNode(ogg, true);
    node.add(struct);
    ogg = new creaOggetto(tipoArray, "globaldefs.NameAndStringValue[]", " name", "", null, false);
    DefaultMutableTreeNode NamingAttributesNode = new DefaultMutableTreeNode(ogg, true);
    struct.add(NamingAttributesNode);
    /*contained = ir.lookup_id("IDL:mtnm.tmforum.org/globaldefs/NameAndStringValue_T:1.0");
    try
    {   controlla(contained);
    }   catch (java.lang.Exception e) {   e.printStackTrace();
                                      }
    TipoStruct = StructDefHelper.narrow(contained);*/

    if (name == null) {
    } else {
        for (int counter3 = 0; counter3 < name.length; counter3++)
        {  ogg = new creaOggetto(TipoStruct, "globaldefs.NameAndStringValue", "", "", null, false);
           NameAndStringValue = new DefaultMutableTreeNode(ogg, true);
           NamingAttributesNode.add(NameAndStringValue);
           ogg = new creaOggetto(tipoStringa, "string", " name", "", null, false);
           ogg.setValue(name[counter3].name);
           app = new DefaultMutableTreeNode(ogg, true);
           NameAndStringValue.add(app);
           ogg = new creaOggetto(tipoStringa, "string", " value", "", null, false);
           ogg.setValue(name[counter3].value);
           app = new DefaultMutableTreeNode(ogg, true);
           NameAndStringValue.add(app);
         }
      }

    ogg = new creaOggetto(tipoStringa, "string", " userLabel", "", null, false);
    ogg.setValue(userLabel);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " nativeEMSName", "", null, false);
    ogg.setValue(nativeEMSName);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " owner", "", null, false);
    ogg.setValue(owner);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoBoolean, "boolean", " alarmReportingIndicator", "", null, false);
    ogg.setValue(new Boolean(alarmReportingIndicator).toString());
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    contained = ir.lookup_id("IDL:mtnm.tmforum.org/equipment/ServiceState_T:1.0");
    try
    {   controlla(contained);
    }   catch (java.lang.Exception e) {   e.printStackTrace();
                                      }
    EnumDef enum = EnumDefHelper.narrow(contained);
    ogg = new creaOggetto(enum, "enum", " ServiceState", "", null, false);
    ogg.setValue(serviceState.toString());
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " expectedEquipmentObjectType", "", null, false);
    ogg.setValue(expectedEquipmentObjectType.toString());
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " installedEquipmentObjectType", "", null, false);
    ogg.setValue(installedEquipmentObjectType.toString());
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " installedEquipmentObjectType", "", null, false);
    ogg.setValue(installedPartNumber);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " installedVersion", "", null, false);
    ogg.setValue(installedVersion);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoStringa, "string", " installedSerialNumber", "", null, false);
    ogg.setValue(installedSerialNumber);
    app = new DefaultMutableTreeNode(ogg, true);
    struct.add(app);
    ogg = new creaOggetto(tipoArray, "globaldefs.NameAndStringValue_T[]", " additionalInfo", "", null, false);
    DefaultMutableTreeNode additionalInfoNode = new DefaultMutableTreeNode(ogg, true);
    struct.add(additionalInfoNode);
    if (additionalInfoNode == null) {
    } else {
        for (int counter4 = 0; counter4 < additionalInfo.length; counter4++)
        {   ogg = new creaOggetto(TipoStruct, "globaldefs.NameAndStringValue", "", "", null, false);
            NameAndStringValue = new DefaultMutableTreeNode(ogg, true);
            additionalInfoNode.add(NameAndStringValue);
            ogg = new creaOggetto(tipoStringa, "string", " name", "", null, false);
            ogg.setValue(additionalInfo[counter4].name);
            app = new DefaultMutableTreeNode(ogg, true);
            NameAndStringValue.add(app);
            ogg = new creaOggetto(tipoStringa, "string", " value", "", null, false);
            ogg.setValue(additionalInfo[counter4].value);
            app = new DefaultMutableTreeNode(ogg, true);
            NameAndStringValue.add(app);
        }

    }
    return node;
  }

  private transient java.util.Hashtable _cmpMap = null;
  public boolean equals (java.lang.Object o) {
    if (this == o) return true;
    if (o == null) return false;

    final java.lang.Thread _currentThread = java.lang.Thread.currentThread();
    boolean justCreated = false;
    if (_cmpMap == null) {
      synchronized (this) {
        if (_cmpMap == null) {
          justCreated = true;
          _cmpMap = new java.util.Hashtable();
        }
      }
    }
    if (!justCreated) {
      final java.lang.Object _cmpObj;
      _cmpObj= _cmpMap.get(_currentThread);
      if (_cmpObj != null) return o == _cmpObj;
    }
    if (o instanceof equipment.Equipment_T) {
      _cmpMap.put(_currentThread, o);
      final equipment.Equipment_T obj = (equipment.Equipment_T)o;
      boolean res = true;
      do {
            if (res = (this.name.length == obj.name.length)) {
              for (int $counter5 = 0; res && $counter5 < this.name.length; $counter5++) {
                res = this.name[$counter5] == obj.name[$counter5] ||
                 (this.name[$counter5] != null && obj.name[$counter5] != null && this.name[$counter5].equals(obj.name[$counter5]));
              }
            }
        if (!res) break;
        res = this.userLabel == obj.userLabel ||
         (this.userLabel != null && obj.userLabel != null && this.userLabel.equals(obj.userLabel));
        if (!res) break;
        res = this.nativeEMSName == obj.nativeEMSName ||
         (this.nativeEMSName != null && obj.nativeEMSName != null && this.nativeEMSName.equals(obj.nativeEMSName));
        if (!res) break;
        res = this.owner == obj.owner ||
         (this.owner != null && obj.owner != null && this.owner.equals(obj.owner));
        if (!res) break;
        res = this.alarmReportingIndicator == obj.alarmReportingIndicator;
        if (!res) break;
        res = this.serviceState == obj.serviceState ||
         (this.serviceState != null && obj.serviceState != null && this.serviceState.equals(obj.serviceState));
        if (!res) break;
          res = this.expectedEquipmentObjectType == obj.expectedEquipmentObjectType ||
           (this.expectedEquipmentObjectType != null && obj.expectedEquipmentObjectType != null && this.expectedEquipmentObjectType.equals(obj.expectedEquipmentObjectType));
        if (!res) break;
          res = this.installedEquipmentObjectType == obj.installedEquipmentObjectType ||
           (this.installedEquipmentObjectType != null && obj.installedEquipmentObjectType != null && this.installedEquipmentObjectType.equals(obj.installedEquipmentObjectType));
        if (!res) break;
        res = this.installedPartNumber == obj.installedPartNumber ||
         (this.installedPartNumber != null && obj.installedPartNumber != null && this.installedPartNumber.equals(obj.installedPartNumber));
        if (!res) break;
        res = this.installedVersion == obj.installedVersion ||
         (this.installedVersion != null && obj.installedVersion != null && this.installedVersion.equals(obj.installedVersion));
        if (!res) break;
        res = this.installedSerialNumber == obj.installedSerialNumber ||
         (this.installedSerialNumber != null && obj.installedSerialNumber != null && this.installedSerialNumber.equals(obj.installedSerialNumber));
        if (!res) break;
          if (res = (this.additionalInfo.length == obj.additionalInfo.length)) {
            for (int $counter6 = 0; res && $counter6 < this.additionalInfo.length; $counter6++) {
              res = this.additionalInfo[$counter6] == obj.additionalInfo[$counter6] ||
               (this.additionalInfo[$counter6] != null && obj.additionalInfo[$counter6] != null && this.additionalInfo[$counter6].equals(obj.additionalInfo[$counter6]));
            }
          }
      } while (false);
      _cmpMap.remove(_currentThread);
      return res;
    }
    else {
      return false;
    }
  }

   public void controlla(Contained contained) throws java.lang.Exception
   {   if (contained == null)
       {  System.out.println("la ricerca nell'interface repository non ha trovato quanto richiesto ");
          throw new org.omg.CORBA.INTERNAL("elemento non trovato nel repository");
       }
   }

}
