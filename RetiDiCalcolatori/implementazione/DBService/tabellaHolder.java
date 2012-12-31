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
public final class tabellaHolder implements org.omg.CORBA.portable.Streamable {
  public DBService.tabella value;

  public tabellaHolder () {
  }

  public tabellaHolder (final DBService.tabella _vis_value) {
    this.value = _vis_value;
  }

  public void _read (final org.omg.CORBA.portable.InputStream input) {
    value = DBService.tabellaHelper.read(input);
  }

  public void _write (final org.omg.CORBA.portable.OutputStream output) {
    DBService.tabellaHelper.write(output, value);
  }

  public org.omg.CORBA.TypeCode _type () {
    return DBService.tabellaHelper.type();
  }
}
