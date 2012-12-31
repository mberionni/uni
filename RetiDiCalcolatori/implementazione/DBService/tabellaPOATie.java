
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
public class tabellaPOATie extends tabellaPOA {
  private DBService.tabellaOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public tabellaPOATie (final DBService.tabellaOperations _delegate) {
    this._delegate = _delegate;
  }

  public tabellaPOATie (final DBService.tabellaOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public DBService.tabellaOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final DBService.tabellaOperations delegate) {
    this._delegate = delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   string leggi (in string chiave);
   * </pre>
   */
  public java.lang.String leggi (java.lang.String chiave) {
    return this._delegate.leggi(chiave);
  }

  /**
   * <pre>
   *   void update (in string chiave, in string valore);
   * </pre>
   */
  public void update (java.lang.String chiave, 
                      java.lang.String valore) {
    this._delegate.update(chiave, valore);
  }

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public boolean faseRicerca () {
    return this._delegate.faseRicerca();
  }

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public void faseRicerca (boolean faseRicerca) {
    this._delegate.faseRicerca(faseRicerca);
  }

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public boolean faseUpdate () {
    return this._delegate.faseUpdate();
  }

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public void faseUpdate (boolean faseUpdate) {
    this._delegate.faseUpdate(faseUpdate);
  }

}
