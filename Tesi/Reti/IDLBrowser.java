package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import org.omg.CORBA.*;
import javax.swing.tree.DefaultMutableTreeNode;

 public class IDLBrowser implements IDLBrowserInterface
 {  private int indent;
    private static DefaultMutableTreeNode app;
    private boolean absoluteNames = false;

    public void viewAbsoluteNames(boolean flag)
    {   absoluteNames = flag;
    }

    public DefaultMutableTreeNode browse (Container container, DefaultMutableTreeNode node)
    {   Contained[] contained = container.contents(DefinitionKind.dk_all, true);
        for (int i = 0; i < contained.length; i++)
        {  browse("", getName(contained[i]), contained[i], node, null, false);
        }
        return node;
    }

    public DefaultMutableTreeNode browse(IRObject irObj, DefaultMutableTreeNode node, org.omg.CORBA.Object ior, boolean isList)
    {   return browse("", "", irObj, node, ior, isList);
    }

    private DefaultMutableTreeNode browse(String mode, String def, IRObject irObj, DefaultMutableTreeNode node, org.omg.CORBA.Object ior, boolean isList)
    { OggettoNodo ogg;
      switch (irObj.def_kind().value())
           {    case DefinitionKind._dk_Module:    ModuleDef module = ModuleDefHelper.narrow(irObj);
                                                   ogg = new OggettoNodo(module, "module ", module.name(), "", null);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertModule(module, app);
                                                   break;
               case DefinitionKind._dk_Interface:  InterfaceDef Interface = InterfaceDefHelper.narrow(irObj);
                                                   String descr = "interface ";
                                                   if (mode.compareTo("") != 0)
                                                   {    descr = mode;
                                                   }
                                                   if (def.compareTo("") != 0)
                                                   {    def = " " + def + ";";
                                                   }
                                                   ogg = new OggettoNodo(Interface, descr, getName(Interface), def, ior);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   if (mode.compareTo("") == 0)
                                                   {   insertInterface(Interface, app);
                                                   }
                                                   break;
               case DefinitionKind._dk_Operation:  OperationDef operation = OperationDefHelper.narrow(irObj);
                                                   String signature = "(" + signatureOperation(operation) + ")";
                                                   ogg = new OggettoNodo(irObj, describeOperation(operation), operation.name(), signature, null);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertOperation(operation, app);
                                                   break;
               case DefinitionKind._dk_Attribute:  AttributeDef attribute = AttributeDefHelper.narrow(AttributeDefHelper.narrow(irObj));
                                                   ogg = new OggettoNodo(attribute, describeAttribute(attribute), attribute.name(), "", null);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertAttribute(attribute);
                                                   break;
               case DefinitionKind._dk_Constant:   app = new DefaultMutableTreeNode("", true);
                                                   node.add(app);
                                                   insertConstant(ConstantDefHelper.narrow(irObj));
                                                   break;
               case DefinitionKind._dk_Exception:  ExceptionDef exception = ExceptionDefHelper.narrow(irObj);
                                                   ogg = new OggettoNodo(exception, "exception ", def, "", null);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertException(exception, app);
                                                   break;
               case DefinitionKind._dk_Struct:     StructDef struct = StructDefHelper.narrow(irObj);
                                                   ogg = new OggettoNodo(struct, mode + "struct ", getName(struct), "", null, isList);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertStruct(struct, app);
                                                   break;
               case DefinitionKind._dk_Alias:      AliasDef alias = AliasDefHelper.narrow(irObj);
                                                   IDLType originalType = alias.original_type_def();
                                                   if (mode.compareTo("") == 0)
                                                   {  ogg = new OggettoNodo(alias, toIdl(originalType), " "+ getName(alias)+ ";", "", null);
                                                   }
                                                   else
                                                   {    ogg = new OggettoNodo(alias, mode + getName(alias) + " ", def + ";" , "", null);
                                                   }
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   if (mode.compareTo("") != 0)
                                                   {    originalType = alias;
                                                   }
                                                   if (! (originalType.def_kind() == DefinitionKind.dk_Primitive) )
                                                   {    browse("", getName(alias), originalType, app, null, isList);
                                                   }
                                                   break;
               case DefinitionKind._dk_Union:      UnionDef union = UnionDefHelper.narrow(irObj);
                                                   ogg = new OggettoNodo(union, mode + describeUnion(union), "", "", null, isList);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertUnion(union, app);
                                                   break;
               case DefinitionKind._dk_Enum:       EnumDef enum = EnumDefHelper.narrow(irObj);
                                                   ogg = new OggettoNodo(enum, mode + "enum ", enum.name(), " " + def + ";", null, isList);
                                                   app = new DefaultMutableTreeNode(ogg, true);
                                                   node.add(app);
                                                   insertEnum(enum, app);
                                                   break;
              case DefinitionKind._dk_Primitive:   PrimitiveDef primitive = PrimitiveDefHelper.narrow(irObj);
                                                   if (def.compareTo("") != 0)
                                                   {    ogg = new OggettoNodo(irObj, mode + toIdl(primitive) + " ", def, ";", null);
                                                        app = new DefaultMutableTreeNode(ogg, true);
                                                        node.add(app);
                                                        break;
                                                   }
                                                   break;
               case DefinitionKind._dk_Sequence:   SequenceDef sequence = SequenceDefHelper.narrow(irObj);
                                                   if (! (sequence.element_type_def().def_kind() == DefinitionKind.dk_Primitive) )
                                                   {    browse("", toIdl(sequence.element_type_def()),sequence.element_type_def(), node, null, true);
                                                   }
                                                   break;

               case DefinitionKind._dk_Array:      println("tipo array - non supportato");
                                                   break;
               default:                            println("attenzione sono nel caso di default di navigaRep - 17");
                                                   if (def.compareTo("") != 0)
                                                   {    ogg = new OggettoNodo(irObj, "string ", def, ";", null);
                                                        app = new DefaultMutableTreeNode(ogg, true);
                                                        node.add(app);
                                                   }
                                                   break;
          }
          return node;
    }

    private void insertModule(ModuleDef def, DefaultMutableTreeNode node)
    { println("module " + def.name() + "{");
      indent++;
      browse(def, node);
      indent--;
      println("};");
    }

    private void insertInterface(InterfaceDef idef, DefaultMutableTreeNode node)
    {  InterfaceDef[] id = idef.base_interfaces();
       String base_interfaces = "";
       //println("len = "+id.length);  //errore ritorna sempre zero, anche se ci sono base_interfaces !!
       for (int k = 0; k < id.length; k++)
       {    base_interfaces = base_interfaces + id[k].name();
       }
       println("interface " + idef.name() + " ::" + base_interfaces + "{");
       indent++;
       browse(idef, node);
       indent--;
       println("};");
    }

     private String describeOperation(OperationDef def)
     {  String g;
        String oneway = def.mode() == OperationMode.OP_ONEWAY ? "oneway " : "";
        String metodo = oneway + toIdl(def.result_def()) + " ";
        String metodo1 = metodo + def.name()+"(";
        print(metodo1);
        return metodo;
     }

     private String signatureOperation(OperationDef def)
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

    private void insertOperation(OperationDef def, DefaultMutableTreeNode node)
    {  String comma="", parametro="";
       indent++;
       ParameterDescription[] parameters = def.params();
       for(int k = 0; k < parameters.length; k++)
       {   String[] mode = { "in ", "out ", "inout " };
           comma = k == parameters.length - 1 ? "" : ", ";
           String tipo = toIdl(parameters[k].type_def);
           parametro = mode[parameters[k].mode.value()] + tipo + " " + parameters[k].name;
           print(parametro+comma);
           browse(mode[parameters[k].mode.value()], parameters[k].name, parameters[k].type_def, node, null, false);
       }
       indent--;
       org.omg.CORBA.ExceptionDef[] exceptions = def.exceptions();
       if (exceptions.length > 0)
       {  println(") raises (");
          indent++;
          for(int k = 0; k < exceptions.length; k++)
          {  comma = k == exceptions.length - 1 ? "" : ",";
             println(getName(exceptions[k]) + comma);
          }
         indent--;
       }
       println(");");
    }


  private String describeAttribute(AttributeDef def)
  {  String readonly = def.mode() == AttributeMode.ATTR_READONLY ? "readonly " : "";
     return readonly + "attribute " + toIdl(def.type_def()) + " ";
  }

  private void insertAttribute(AttributeDef def)
  { String readonly = def.mode() == AttributeMode.ATTR_READONLY ? "readonly " : "";
    println(readonly + "attribute " + toIdl(def.type_def()) + " " + def.name() + ";");
  }

  private void insertConstant(ConstantDef def)
  { println("const " + toIdl(def.type_def()) + " " + def.name() + " = " + def.value() + ";");
  }

  private void insertException(ExceptionDef def, DefaultMutableTreeNode node)
  {   println("exception " + def.name() + " {");
      indent++;
      StructMember[] members = def.members();
      for(int j = 0; j < members.length; j++)
      {   println(toIdl(members[j].type_def) + " " + members[j].name + ";");
          OggettoNodo ogg = new OggettoNodo(members[j].type_def, toIdl(members[j].type_def) + " ", members[j].name, "", null);  //costruzione dei nodi che rappresentano i parametri
          browse("", members[j].name, members[j].type_def, node, null, false);
      }
      indent--;
      println("};");
  }
  private String describeAlias(AliasDef alias)
  {   IDLType idlType = alias.original_type_def();
      String descr = "typedef " + toIdl(idlType) + " " + getName(alias) +  ";";
      println(descr);
      return descr;
  }

  private void insertStruct(StructDef def, DefaultMutableTreeNode node)
  { println("struct " + getName(def) + " {");
    indent++;

    StructMember[] members = def.members();
    for(int j = 0; j < members.length; j++)
    {    String tipo = toIdl(members[j].type_def);
         String descr = tipo + " " + members[j].name + ";";
         println(descr);
         if (members[j].type_def.def_kind().value() == DefinitionKind._dk_Alias)
         {    //println("il membro "+members[j].name+" con value = "+members[j].type_def.def_kind().value()+" e' alias original type "+ (AliasDefHelper.narrow(members[j].type_def)).original_type_def());
              AliasDef ad = (AliasDefHelper.narrow(members[j].type_def));
              OggettoNodo ogg = new OggettoNodo(members[j].type_def, tipo + " ", members[j].name, ";", null);  //costruzione dei nodi che rappresentano i parametri
              app = new DefaultMutableTreeNode(ogg, true);
              node.add(app);
              browse("", members[j].name, members[j].type_def, app, null, false); //(new Contained[] {ad}, app, null);
         }
         else
        browse("", members[j].name, members[j].type_def, node, null, false);
    }
    indent--;
    println("};");
  }

  private String describeUnion(UnionDef def)
  {   String descr = "union " + def.name() + " switch(" + toIdl(def.discriminator_type_def()) + ")";
      println(descr + "{");
      return descr;
  }

  private void insertUnion(UnionDef def, DefaultMutableTreeNode node)
  {   DefaultMutableTreeNode metodoNode = node;
      try
      {   String descr = "";
          UnionMember[] members = def.members();
          int default_index = members.length;
          indent++;
          for(int j = 0; j < members.length; j++)
          {   if (j == default_index)
              {   println("default:");
                  descr = "default:";
              }
              else
              {   println("case " + members[j].label + ":");
                  descr = "case " + members[j].label + ":";
              }
              indent++;
              OggettoNodo ogg = new OggettoNodo(members[j].type_def, descr, toIdl(members[j].type_def) + " " + members[j].name, ";", null);  //costruzione dei nodi che rappresentano i parametri
              app = new DefaultMutableTreeNode(ogg, true);
              metodoNode.add(app);
              println(toIdl(members[j].type_def) + " " + members[j].name + ";");
              indent--;
          }
          indent--;
          println("};");
      } catch (Exception e)
              {   System.err.println("System Exception");
                  System.err.println(e);
                  e.printStackTrace();
              }
  }

  private void insertEnum(EnumDef def, DefaultMutableTreeNode node)
  {   DefaultMutableTreeNode metodoNode = node;
      try
      {   TypeCode type = def.type();
          println("enum " + type.name() + " {");
          indent++;
          IDLType tipoStringa = ConfigDataAccessClient.tipoStringa;
          int count = type.member_count();
          for(int j = 0; j < count; j++)
          {    println(type.member_name(j) + ((j == count - 1) ? "" : ","));
               OggettoNodo ogg = new OggettoNodo( tipoStringa, type.member_name(j), "", ";", null);  //costruzione dei nodi che rappresentano i parametri
               app = new DefaultMutableTreeNode(ogg, true);
               metodoNode.add(app);
          }
          indent--;
          println("};");
      }   catch (Exception e)
              {   System.err.println("System Exception");
                  System.err.println(e);
                  e.printStackTrace();
              }
  }

   private String toIdl(IDLType idlType)
   {   Contained contained = null;
       String ret;
       if (idlType.def_kind() == DefinitionKind.dk_Sequence)
          {   SequenceDef sd = SequenceDefHelper.narrow(idlType);
              return "sequence <" + toIdl(sd.element_type_def()) + "> ";
          }
      if (idlType.def_kind() == DefinitionKind.dk_Alias)
          {  AliasDef ad = AliasDefHelper.narrow(idlType);
             return getName(ad);
          }
      if (idlType.def_kind() == DefinitionKind.dk_Union)
          {  UnionDef ad = UnionDefHelper.narrow(idlType);
             return getName(ad);
          }
      try  {   if (idlType.type().name().compareTo("Object")==0)
                  return "object";
               contained = ContainedHelper.narrow(idlType);
            } catch (Exception e) {}
       if (contained == null)
          ret = idlType.type().toString();
       else
          ret = getName(contained);
       if (ret.equals("string[0]") )
          return "string";
       else return ret;
    }

    private void println(java.lang.String text)
    {   for (int i=0; i<indent; i++)
        {   //System.out.print("  ");
        }
        //System.out.println(text);
   }

    private void print(java.lang.String text)
    {   for (int i=0; i<indent; i++)
        {   //System.out.print(" ");
        }
        //System.out.print(text);
    }

    private String getName(Contained obj)
    {   if (absoluteNames)
        {   return obj.absolute_name();
        }
        else
        {   return obj.name();
        }
    }

}
