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
public interface sommaOperations {
  /**
   * <pre>
   *   string sommaConcatena (in long add1, in long add2, in string concat);
   * </pre>
   */
  public java.lang.String sommaConcatena (int add1, 
                                          int add2, 
                                          java.lang.String concat);

  /**
   * <pre>
   *   string concatena (in long concat1, in long concat2, in string concat3);
   * </pre>
   */
  public java.lang.String concatena (int concat1, 
                                     int concat2, 
                                     java.lang.String concat3);

  /**
   * <pre>
   *   long calcola (in long add1, in long add2, in long mul, in long div);
   * </pre>
   */
  public int calcola (int add1, 
                      int add2, 
                      int mul, 
                      int div);

}
