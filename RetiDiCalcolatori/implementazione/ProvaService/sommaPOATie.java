
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
public class sommaPOATie extends sommaPOA {
  private ProvaService.sommaOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public sommaPOATie (final ProvaService.sommaOperations _delegate) {
    this._delegate = _delegate;
  }

  public sommaPOATie (final ProvaService.sommaOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public ProvaService.sommaOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final ProvaService.sommaOperations delegate) {
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
   *   string sommaConcatena (in long add1, in long add2, in string concat);
   * </pre>
   */
  public java.lang.String sommaConcatena (int add1, 
                                          int add2, 
                                          java.lang.String concat) {
    return this._delegate.sommaConcatena(add1, add2, concat);
  }

  /**
   * <pre>
   *   string concatena (in long concat1, in long concat2, in string concat3);
   * </pre>
   */
  public java.lang.String concatena (int concat1, 
                                     int concat2, 
                                     java.lang.String concat3) {
    return this._delegate.concatena(concat1, concat2, concat3);
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
    return this._delegate.calcola(add1, add2, mul, div);
  }

}
