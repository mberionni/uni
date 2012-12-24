package Interprete6;

public abstract class AtomSexp extends Sexp
{
 public   boolean isAtom()
 { return true;
 }

 public   boolean isList()
 { return false;
 }

 public  Sexp car()
 { return nil;
 }

 public  Sexp cdr()
 { return nil;
 }

}
