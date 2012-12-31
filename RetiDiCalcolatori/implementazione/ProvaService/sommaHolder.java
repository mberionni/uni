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
public final class sommaHolder implements org.omg.CORBA.portable.Streamable {
  public ProvaService.somma value;

  public sommaHolder () {
  }

  public sommaHolder (final ProvaService.somma _vis_value) {
    this.value = _vis_value;
  }

  public void _read (final org.omg.CORBA.portable.InputStream input) {
    value = ProvaService.sommaHelper.read(input);
  }

  public void _write (final org.omg.CORBA.portable.OutputStream output) {
    ProvaService.sommaHelper.write(output, value);
  }

  public org.omg.CORBA.TypeCode _type () {
    return ProvaService.sommaHelper.type();
  }
}
