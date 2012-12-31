package DBService;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "tabella.idl"
 * <li> <b>IDL Name</b>      ::DBService::tabella
 * <li> <b>Repository Id</b> IDL:DBService/tabella:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface tabella {
  ...
};
 * </pre>
 */
public abstract class tabellaPOA extends org.omg.PortableServer.Servant implements 
  org.omg.CORBA.portable.InvokeHandler, DBService.tabellaOperations {

  public DBService.tabella _this () {
   return DBService.tabellaHelper.narrow(super._this_object());
  }

  public DBService.tabella _this (org.omg.CORBA.ORB orb) {
    return DBService.tabellaHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:DBService/tabella:1.0"
  };

  private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
    _methods.put("leggi", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 0));
    _methods.put("update", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 1));
    _methods.put("_get_faseRicerca", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 2));
    _methods.put("_set_faseRicerca", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 3));
    _methods.put("_get_faseUpdate", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 4));
    _methods.put("_set_faseUpdate", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (java.lang.String opName,
                                                      org.omg.CORBA.portable.InputStream _input,
                                                      org.omg.CORBA.portable.ResponseHandler handler) {
    com.inprise.vbroker.CORBA.portable.MethodPointer method =
      (com.inprise.vbroker.CORBA.portable.MethodPointer) _methods.get(opName);
    if (method == null) {
      throw new org.omg.CORBA.BAD_OPERATION();
    }
    switch (method.interface_id) {
      case 0: {
        return DBService.tabellaPOA._invoke(this, method.method_id, _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION();
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (DBService.tabellaOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
    org.omg.CORBA.portable.OutputStream _output = null;
    {
      switch (_method_id) {
      case 0: {
        java.lang.String chiave;
        chiave = _input.read_string();
        java.lang.String _result = _self.leggi(chiave);
        _output = _handler.createReply();
        _output.write_string((java.lang.String)_result);
        return _output;
      }
      case 1: {
        java.lang.String chiave;
        chiave = _input.read_string();
        java.lang.String valore;
        valore = _input.read_string();
        _self.update(chiave, valore);
        _output = _handler.createReply();
        return _output;
      }
      case 2: {
        boolean _result = _self.faseRicerca();
        _output = _handler.createReply();
        _output.write_boolean((boolean)_result);
        return _output;
      }
      case 3: {
        boolean faseRicerca;
        faseRicerca = _input.read_boolean();
        _self.faseRicerca(faseRicerca);
        _output = _handler.createReply();
        return _output;
      }
      case 4: {
        boolean _result = _self.faseUpdate();
        _output = _handler.createReply();
        _output.write_boolean((boolean)_result);
        return _output;
      }
      case 5: {
        boolean faseUpdate;
        faseUpdate = _input.read_boolean();
        _self.faseUpdate(faseUpdate);
        _output = _handler.createReply();
        return _output;
      }
      }
      throw new org.omg.CORBA.BAD_OPERATION();
    }
  }
}
