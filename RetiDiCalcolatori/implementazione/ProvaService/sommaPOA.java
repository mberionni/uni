package ProvaService;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "somma.idl"
 * <li> <b>IDL Name</b>      ::ProvaService::somma
 * <li> <b>Repository Id</b> IDL:ProvaService/somma:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface somma {
  ...
};
 * </pre>
 */
public abstract class sommaPOA extends org.omg.PortableServer.Servant implements 
  org.omg.CORBA.portable.InvokeHandler, ProvaService.sommaOperations {

  public ProvaService.somma _this () {
   return ProvaService.sommaHelper.narrow(super._this_object());
  }

  public ProvaService.somma _this (org.omg.CORBA.ORB orb) {
    return ProvaService.sommaHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:ProvaService/somma:1.0"
  };

  private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
    _methods.put("sommaConcatena", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 0));
    _methods.put("concatena", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 1));
    _methods.put("calcola", new com.inprise.vbroker.CORBA.portable.MethodPointer(0, 2));
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
        return ProvaService.sommaPOA._invoke(this, method.method_id, _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION();
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (ProvaService.sommaOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
    org.omg.CORBA.portable.OutputStream _output = null;
    {
      switch (_method_id) {
      case 0: {
        int add1;
        add1 = _input.read_long();
        int add2;
        add2 = _input.read_long();
        java.lang.String concat;
        concat = _input.read_string();
        java.lang.String _result = _self.sommaConcatena(add1, add2, concat);
        _output = _handler.createReply();
        _output.write_string((java.lang.String)_result);
        return _output;
      }
      case 1: {
        int concat1;
        concat1 = _input.read_long();
        int concat2;
        concat2 = _input.read_long();
        java.lang.String concat3;
        concat3 = _input.read_string();
        java.lang.String _result = _self.concatena(concat1, concat2, concat3);
        _output = _handler.createReply();
        _output.write_string((java.lang.String)_result);
        return _output;
      }
      case 2: {
        int add1;
        add1 = _input.read_long();
        int add2;
        add2 = _input.read_long();
        int mul;
        mul = _input.read_long();
        int div;
        div = _input.read_long();
        int _result = _self.calcola(add1, add2, mul, div);
        _output = _handler.createReply();
        _output.write_long((int)_result);
        return _output;
      }
      }
      throw new org.omg.CORBA.BAD_OPERATION();
    }
  }
}
