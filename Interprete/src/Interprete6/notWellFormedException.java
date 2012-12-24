package Interprete6;

public class notWellFormedException extends Exception
{ /**
	 * 
	 */
  private static final long serialVersionUID = -6090895350014647012L;
  
  String explanation = "errore: ";

  public notWellFormedException( String wrongChars )
  {	explanation = explanation  + wrongChars ;
  }

  public String toString()
  { return explanation;
  }
}
