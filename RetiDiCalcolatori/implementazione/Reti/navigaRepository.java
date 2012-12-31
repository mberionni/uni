package Reti;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */
import java.io.*;
import java.util.Vector;
import org.omg.CORBA.*;
import javax.swing.tree.*;

 public class navigaRepository
 {  private PrintStream out = System.out;
    private int indent;
    public static DefaultMutableTreeNode app; // = new DefaultMutableTreeNode();

    public DefaultMutableTreeNode cruise (Container container, DefaultMutableTreeNode node)
    {   Contained[] contained = container.contents(DefinitionKind.dk_all, true);
        return cruise2(contained, node, null);
    }

    protected DefaultMutableTreeNode cruise2(Contained[] contained, DefaultMutableTreeNode node, org.omg.CORBA.Object rif)
    {   creaOggetto ogg;
        // contained è un array di moduli
        //System.out.println("dentro cruise2 con rif= "+rif);

        for (int i=0; i<contained.length; i++)
        {  switch (contained[i].def_kind().value())
           {   case DefinitionKind._dk_Module:     ogg = new creaOggetto(contained[i],contained[i].name(), "", "", null);
                                                   app = new DefaultMutableTreeNode(ogg,true);
                                                   node.add(app);
                                                   insertModule(ModuleDefHelper.narrow(contained[i]),app);
                                                   break;
               case DefinitionKind._dk_Interface:  //System.out.println("tipo interfaccia. sto inserendo rif="+rif);
                                                   ogg=new creaOggetto(contained[i],contained[i].name(), "", "", rif);
                                                   app = new DefaultMutableTreeNode(ogg,true);
                                                   node.add(app);
                                                   insertInterface(InterfaceDefHelper.narrow(contained[i]),app);
                                                   break;
               case DefinitionKind._dk_Attribute:  //ogg=new creaOggetto(contained[i]);
                                                   app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertAttribute(AttributeDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Constant:   app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertConstant(ConstantDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Exception:  app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertException(ExceptionDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Operation:  //System.out.println("tipo operazione. sto inserendo rif="+rif);
                                                   OperationDef op = OperationDefHelper.narrow(contained[i]);
                                                   String signature = "(" + signatureOperation(op) + ")";
                                                   ogg=new creaOggetto(contained[i],describeOperation(op), op.name(), signature, null);
                                                   app = new DefaultMutableTreeNode(ogg,true);
                                                   node.add(app);
                                                   insertOperation(OperationDefHelper.narrow(contained[i]),app);
                                                   break;
               case DefinitionKind._dk_Alias:      app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertAlias(AliasDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Struct:     app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertStruct(StructDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Union:      app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertUnion(UnionDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_Enum:       app = new DefaultMutableTreeNode(contained[i].name(),true);
                                                   node.add(app);
                                                   insertEnum(EnumDefHelper.narrow(contained[i]));
                                                   break;
               case DefinitionKind._dk_none:
               case DefinitionKind._dk_all:
               case DefinitionKind._dk_Typedef:
               case DefinitionKind._dk_Primitive:
               case DefinitionKind._dk_String:
               case DefinitionKind._dk_Sequence:
               case DefinitionKind._dk_Array:
               default:                            System.out.println("attenzione sono nel caso di default di navigaRep");
                                                   break;
           }
        }
        return node;
    }

    private void insertModule(ModuleDef def, DefaultMutableTreeNode node)
    { println("module "+def.name()+"{");
      indent++;
      cruise(def,node);
      indent--;
      println("};");
    }

    private void insertInterface(InterfaceDef idef, DefaultMutableTreeNode node)
    { println("interface "+idef.name()+"{");
      indent++;
      cruise(idef, node);
      indent--;
      println("};");
    }

     private String describeOperation(org.omg.CORBA.OperationDef def)
     { String g;
       String oneway = def.mode() == org.omg.CORBA.OperationMode.OP_ONEWAY ? "oneway " : "";
       String metodo = oneway + toIdl(def.result_def()) + " ";
       String metodo1 = metodo + def.name()+"(";
       printSenzaln(metodo1);
       return metodo;
     }

     private String signatureOperation(org.omg.CORBA.OperationDef def)
     {  String parametri="", comma="", parametro="";
        ParameterDescription[] parameters = def.params();

        for(int k = 0; k < parameters.length; k++)
        {   String[] mode = { "in", "out", "inout" };
            comma = k == parameters.length - 1 ? "" : ",";
            parametro = mode[parameters[k].mode.value()] + " " + toIdl(parameters[k].type_def) + " " + parameters[k].name + comma;
            parametri = parametri + parametro;
        }
        return parametri;
     }

    private void insertOperation(org.omg.CORBA.OperationDef def, DefaultMutableTreeNode node)
    { String comma="", parametro="";
      DefaultMutableTreeNode metodoNode = node;
      indent++;
      ParameterDescription[] parameters = def.params();
      for(int k = 0; k < parameters.length; k++)
      {   String[] mode = { "in", "out", "inout" };
          comma = k == parameters.length - 1 ? "" : ",";
          parametro = mode[parameters[k].mode.value()] + " " + toIdl(parameters[k].type_def) + " " + parameters[k].name;
          printSenzaln(parametro+comma);
          creaOggetto ogg = new creaOggetto(parameters[k].type_def, parametro, "", "", null);  //costruzione dei nodi che rappresentano i parametri
          app = new DefaultMutableTreeNode(ogg,true);
          metodoNode.add(app);
      }
      indent--;
      org.omg.CORBA.ExceptionDef[] exceptions = def.exceptions();
      if (exceptions.length > 0)
      { println(") raises (");
        indent++;
        for(int k = 0; k < exceptions.length; k++)
        { comma = k == exceptions.length - 1 ? "" : ",";
          println(exceptions[k].absolute_name() + comma);
        }
        indent--;
      }
      println(");");
    }


    private void insertAttribute(org.omg.CORBA.AttributeDef def)
   { String readonly = def.mode() == org.omg.CORBA.AttributeMode.ATTR_READONLY ?
      "readonly " : "";
     println(readonly + "attribute " + toIdl(def.type_def()) +
            " " + def.name() + ";");
   }


  private void insertConstant(org.omg.CORBA.ConstantDef def)
  { println("const " + toIdl(def.type_def()) + " " + def.name() + " = " + def.value() + ";");
  }


  private void insertException(org.omg.CORBA.ExceptionDef def)
  { println("exception " + def.name() + " {");
    indent++;
    org.omg.CORBA.StructMember[] members = def.members();
    for(int j = 0; j < members.length; j++)
    {
      println(toIdl(members[j].type_def) + " " +
                    members[j].name + ";");
    }
    indent--;
    println("};");
  }

  private void insertAlias(org.omg.CORBA.AliasDef def)
  { org.omg.CORBA.IDLType idlType = def.original_type_def();
    String arrayBounds = "";
    while(true)
    {
      org.omg.CORBA.ArrayDef arrayDef = org.omg.CORBA.ArrayDefHelper.narrow(idlType);
      if (arrayDef == null)
      {
        break;
      }
      arrayBounds += "[" + arrayDef.length() + "]";
      idlType = arrayDef.element_type_def();
    }
    println("typedef " + toIdl(idlType) +
            " " + def.name() + arrayBounds + ";");
  }


  private void insertStruct(org.omg.CORBA.StructDef def)
  { println("struct " + def.name() + " {");
    indent++;
    org.omg.CORBA.StructMember[] members = def.members();
    for(int j = 0; j < members.length; j++)
    {
      println(toIdl(members[j].type_def) + " " +
               members[j].name + ";");
    }
    indent--;
    println("};");
  }


  private void insertUnion(org.omg.CORBA.UnionDef def)
  { try
    {
      println("union " + def.name() + " switch(" +
              toIdl(def.discriminator_type_def()) + ") {");
      org.omg.CORBA.UnionMember[] members = def.members();
      int default_index = def.type().default_index();
      indent++;
      for(int j = 0; j < members.length; j++)
      {
        if (j == default_index) {
          println("default:");
        }
        else {
          println("case " + members[j].label + ":");
        }
        indent++;
        println(toIdl(members[j].type_def) + " " +
                      members[j].name + ";");
        indent--;
      }
      indent--;
      println("};");
    } catch (Exception e)
    { System.err.println("System Exception");
      System.err.println(e);
    }
  }


  private void insertEnum(org.omg.CORBA.EnumDef def)
  { try
    {
      org.omg.CORBA.TypeCode type = def.type();
      println("enum " + type.name() + " {");
      indent++;
      int count = type.member_count();
      for(int j = 0; j < count; j++)
      {
        println(type.member_name(j) + ((j == count - 1) ? "" : ","));
      }
      indent--;
      println("};");
    } catch (Exception e)
    { System.err.println("System Exception");
      System.err.println(e);
    }
  }

   private String toIdl(org.omg.CORBA.IDLType idlType)
   {   org.omg.CORBA.Contained contained= null;
       String ret;

      try  {   if (idlType.type().name().compareTo("Object")==0)
                  return "object";
               contained = org.omg.CORBA.ContainedHelper.narrow(idlType);
            } catch (Exception e) {}
       if (contained == null)
          ret = idlType.type().toString();
       else
          ret =  contained.absolute_name();
       if (ret.equals("string[0]") )
          return "string";
       else return ret;
    }

    private void println(java.lang.Object o)
    { for (int i=0; i<indent; i++)
      out.print("  ");

      out.println(o);
    }

      private void printSenzaln(java.lang.Object o)
    { for (int i=0; i<indent; i++)
      out.print(" ");

      out.print(o);
    }

}
