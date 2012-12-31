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
import java.awt.Component;
import java.awt.Color;
import javax.swing.*;
import javax.swing.tree.*;
import org.omg.CORBA.DefinitionKind;


public class MyCellRenderer extends DefaultTreeCellRenderer
{   private final ImageIcon moduleFile = new ImageIcon(Frame1.resources.getString("moduleIcon"));
    private final ImageIcon  interfaceFile = new ImageIcon(Frame1.resources.getString("interfaceIcon"));
    private final ImageIcon  operationFile = new ImageIcon(Frame1.resources.getString("operationIcon"));
    private final ImageIcon  variableFile = new ImageIcon(Frame1.resources.getString("variableIcon"));
    private final Color treeBackColor = Color.getColor(Frame1.resources.getString("treeBackColor"));
    protected DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
    Frame1 frame1;

    public MyCellRenderer()
   {
   }
        public Component getTreeCellRendererComponent(JTree tree, java.lang.Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {  if (value instanceof DefaultMutableTreeNode)
          {   DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
              java.lang.Object userObject = node.getUserObject();
              if (userObject instanceof creaOggetto)
              {   creaOggetto c = (creaOggetto)userObject;
                  try
                  {   DefinitionKind dk = c.tipo.def_kind();
                      switch ( dk.value() )
                      {   case DefinitionKind._dk_Module:   //System.out.println(c.toString()+" instanceof module");
                                                            myRenderer.setOpenIcon(moduleFile);
                                                            myRenderer.setClosedIcon(moduleFile);
                                                            break;
                          case DefinitionKind._dk_Interface: //System.out.println(c.toString()+" instanceof interface");
                                                            myRenderer.setOpenIcon(interfaceFile);
                                                            myRenderer.setClosedIcon(interfaceFile);
                                                            break;
                          case DefinitionKind._dk_Operation: //System.out.println(c.toString()+" instanceof operation");
                                                            myRenderer.setOpenIcon(operationFile);
                                                            myRenderer.setClosedIcon(operationFile);
                                                            myRenderer.setLeafIcon(operationFile);
                                                            //myRenderer.setIcon(operationFile);
                                                            break;
                          default:                          //System.out.println(c.toString()+" instanceof variable");
                                                            myRenderer.setOpenIcon(variableFile);
                                                            myRenderer.setClosedIcon(variableFile);
                                                            myRenderer.setLeafIcon(variableFile);
                                                            break;
                      }
                  }   catch (Exception e) {   System.out.println("eccezione in mycellrenderer poichè selezionato nodo inesistente");
                                          }
                  myRenderer.setBackgroundNonSelectionColor(treeBackColor);
                  return  myRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
              }
           }
           //System.out.println("ne container ne contained");
           myRenderer.setLeafIcon(moduleFile);
           myRenderer.setOpenIcon(moduleFile);
           myRenderer.setClosedIcon(moduleFile);
           myRenderer.setBackgroundNonSelectionColor(treeBackColor);
           return myRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
   }
}