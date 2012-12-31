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
public class _sommaStub extends com.inprise.vbroker.CORBA.portable.ObjectImpl implements ProvaService.somma {
  final public static java.lang.Class _opsClass = ProvaService.sommaOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:ProvaService/somma:1.0"
  };

  /**
   * <pre>
   *   string sommaConcatena (in long add1, in long add2, in string concat);
   * </pre>
   */
  public java.lang.String sommaConcatena (int add1, 
                                          int add2, 
                                          java.lang.String concat) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("sommaConcatena", true);
          _output.write_long((int)add1);
          _output.write_long((int)add2);
          _output.write_string((java.lang.String)concat);
          _input = this._invoke(_output);
          _result = _input.read_string();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          final org.omg.CORBA.portable.InputStream in = _exception.getInputStream();
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException _exception) {
          continue;
        }
        finally {
          this._releaseReply(_input);
        }
      } else {
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("sommaConcatena", _opsClass);
        if (_so == null) {
          continue;
        }
        final ProvaService.sommaOperations _self = (ProvaService.sommaOperations)_so.servant;
        try {
          return _self.sommaConcatena(add1, add2, concat);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

  /**
   * <pre>
   *   string concatena (in long concat1, in long concat2, in string concat3);
   * </pre>
   */
  public java.lang.String concatena (int concat1, 
                                     int concat2, 
                                     java.lang.String concat3) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("concatena", true);
          _output.write_long((int)concat1);
          _output.write_long((int)concat2);
          _output.write_string((java.lang.String)concat3);
          _input = this._invoke(_output);
          _result = _input.read_string();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          final org.omg.CORBA.portable.InputStream in = _exception.getInputStream();
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException _exception) {
          continue;
        }
        finally {
          this._releaseReply(_input);
        }
      } else {
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("concatena", _opsClass);
        if (_so == null) {
          continue;
        }
        final ProvaService.sommaOperations _self = (ProvaService.sommaOperations)_so.servant;
        try {
          return _self.concatena(concat1, concat2, concat3);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

  /**
   * <pre>
   *   long calcola (in long add1, in long add2, in long mul, in long div);
   * </pre>
   */
  public int calcola (int add1, 
                      int add2, 
                      int mul, 
                      int div) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        int _result;
        try {
          _output = this._request("calcola", true);
          _output.write_long((int)add1);
          _output.write_long((int)add2);
          _output.write_long((int)mul);
          _output.write_long((int)div);
          _input = this._invoke(_output);
          _result = _input.read_long();
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          final org.omg.CORBA.portable.InputStream in = _exception.getInputStream();
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException _exception) {
          continue;
        }
        finally {
          this._releaseReply(_input);
        }
      } else {
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("calcola", _opsClass);
        if (_so == null) {
          continue;
        }
        final ProvaService.sommaOperations _self = (ProvaService.sommaOperations)_so.servant;
        try {
          return _self.calcola(add1, add2, mul, div);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

}
