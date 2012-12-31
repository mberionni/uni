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
public class _tabellaStub extends com.inprise.vbroker.CORBA.portable.ObjectImpl implements DBService.tabella {
  final public static java.lang.Class _opsClass = DBService.tabellaOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:DBService/tabella:1.0"
  };

  /**
   * <pre>
   *   string leggi (in string chiave);
   * </pre>
   */
  public java.lang.String leggi (java.lang.String chiave) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        java.lang.String _result;
        try {
          _output = this._request("leggi", true);
          _output.write_string((java.lang.String)chiave);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("leggi", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          return _self.leggi(chiave);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

  /**
   * <pre>
   *   void update (in string chiave, in string valore);
   * </pre>
   */
  public void update (java.lang.String chiave, 
                      java.lang.String valore) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("update", true);
          _output.write_string((java.lang.String)chiave);
          _output.write_string((java.lang.String)valore);
          _input = this._invoke(_output);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("update", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          _self.update(chiave, valore);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
      break;
    }
  }

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public boolean faseRicerca () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("_get_faseRicerca", true);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_get_faseRicerca", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          return _self.faseRicerca();
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public void faseRicerca (boolean faseRicerca) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("_set_faseRicerca", true);
          _output.write_boolean((boolean)faseRicerca);
          _input = this._invoke(_output);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_set_faseRicerca", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          _self.faseRicerca(faseRicerca);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
      break;
    }
  }

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public boolean faseUpdate () {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        boolean _result;
        try {
          _output = this._request("_get_faseUpdate", true);
          _input = this._invoke(_output);
          _result = _input.read_boolean();
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_get_faseUpdate", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          return _self.faseUpdate();
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public void faseUpdate (boolean faseUpdate) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        try {
          _output = this._request("_set_faseUpdate", true);
          _output.write_boolean((boolean)faseUpdate);
          _input = this._invoke(_output);
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
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("_set_faseUpdate", _opsClass);
        if (_so == null) {
          continue;
        }
        final DBService.tabellaOperations _self = (DBService.tabellaOperations)_so.servant;
        try {
          _self.faseUpdate(faseUpdate);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
      break;
    }
  }

}
