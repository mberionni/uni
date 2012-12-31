package registraIDL;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import org.omg.CORBA.IDLType;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Repository;
import org.omg.CORBA.RepositoryHelper;
import org.omg.CORBA.PrimitiveKind;
import org.omg.CORBA.DefinitionKind;
import org.omg.CORBA.Contained;
import Reti.TextWindow;


 public class RegistrationClass
 {  static Repository ir;
    static ORB orb;
    static IDLType tipoStringa;
    static IDLType tipoLong;
    static IDLType tipoULong;
    static IDLType tipoVoid;
    static IDLType tipoBoolean;
    static TextWindow tw;

    public static void main(String[] args)
    {   RegistrationClass rC = new RegistrationClass();
        rC.init(args);
        rC.regAll();
    }

    public void init (String[] args)
    {  tw = new TextWindow();
       tw.setVisible(true);
       try
       {   orb = ORB.init(args, null);
           ir = RepositoryHelper.narrow(orb.resolve_initial_references("InterfaceRepository"));
           println("Repository localizzato correttamente");
       }   catch (Exception e)  {  e.printStackTrace();
                                }
       tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
       tipoLong = ir.get_primitive(PrimitiveKind.pk_long);
       tipoULong = ir.get_primitive(PrimitiveKind.pk_ulong);
       tipoBoolean = ir.get_primitive(PrimitiveKind.pk_boolean);
       tipoVoid = ir.get_primitive(PrimitiveKind.pk_void);

    }

    public void regAll()
    {   println(" --- inizio registrazione globale ---");
        new registraGlobaldefs();
        new registraCommon();
        new registraVersion();
        new registraSession();
        new registraNmsSession();
        new registraEmsSession();
        new registraEmsSessionFactory();
        new registraEquipment();
        println(" --- fine registrazione globale ---");

        System.exit(0);
   }

   protected static void println(String text)
   {    //System.out.println(text);
        tw.println(text);
   }
 }