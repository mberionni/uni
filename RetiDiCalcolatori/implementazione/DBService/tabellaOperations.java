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
public interface tabellaOperations {
  /**
   * <pre>
   *   string leggi (in string chiave);
   * </pre>
   */
  public java.lang.String leggi (java.lang.String chiave);

  /**
   * <pre>
   *   void update (in string chiave, in string valore);
   * </pre>
   */
  public void update (java.lang.String chiave, 
                      java.lang.String valore);

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public boolean faseRicerca ();

  /**
   * <pre>
   *   attribute boolean faseRicerca;
   * </pre>
   */
  public void faseRicerca (boolean faseRicerca);

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public boolean faseUpdate ();

  /**
   * <pre>
   *   attribute boolean faseUpdate;
   * </pre>
   */
  public void faseUpdate (boolean faseUpdate);

}
