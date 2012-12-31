package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CORBA.DefinitionKind;



public class MyCellRenderer extends DefaultTreeCellRenderer
{   private final ImageIcon rootIcon = ConfigDataAccessClient.rootIcon;
    private final ImageIcon moduleIcon = ConfigDataAccessClient.moduleIcon;
    private final ImageIcon interfaceIcon = ConfigDataAccessClient.interfaceIcon;
    private final ImageIcon operationIcon = ConfigDataAccessClient.operationIcon;
    private final ImageIcon variableIcon = ConfigDataAccessClient.variableIcon;
    private final ImageIcon enumIcon = ConfigDataAccessClient.enumIcon;
    private final ImageIcon unionIcon = ConfigDataAccessClient.unionIcon;
    private final ImageIcon structIcon = ConfigDataAccessClient.structIcon;
    private final ImageIcon sequenceIcon = ConfigDataAccessClient.sequenceIcon;
    private final ImageIcon exceptionIcon = ConfigDataAccessClient.exceptionIcon;
    private final ImageIcon aliasIcon = ConfigDataAccessClient.aliasIcon;
    private final ImageIcon typedefIcon = ConfigDataAccessClient.typedefIcon;
    private final ImageIcon requestIcon = ConfigDataAccessClient.requestIcon;
    private final ImageIcon responseIcon = ConfigDataAccessClient.responseIcon;
    private final Color treeBackColor = ConfigDataAccessClient.treeBackColor;
    private final Font f = ConfigDataAccessClient.f;

    protected final int moduleType = ConfigDataAccessClient.moduleType;
    protected final int interfaceType = ConfigDataAccessClient.interfaceType;
    protected final int operationType = ConfigDataAccessClient.operationType;
    protected final int exceptionType = ConfigDataAccessClient.exceptionType;
    protected final int enumType = ConfigDataAccessClient.enumType;
    protected final int unionType = ConfigDataAccessClient.unionType;
    protected final int structType = ConfigDataAccessClient.structType;
    protected final int aliasType = ConfigDataAccessClient.aliasType;
    protected final int sequenceType = ConfigDataAccessClient.sequenceType;
    protected final int typedefType = ConfigDataAccessClient.typedefType;
    protected final int arrayType = ConfigDataAccessClient.arrayType;
    protected final int defaultType = ConfigDataAccessClient.defaultType;

    DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();

    public Component getTreeCellRendererComponent(JTree tree, java.lang.Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {     if (value instanceof DefaultMutableTreeNode)
          {   DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
              java.lang.Object nodeUserObject = node.getUserObject();
              if (nodeUserObject instanceof creaOggetto)
              {   creaOggetto userObject = (creaOggetto)nodeUserObject;
                  if ( userObject.getValue().compareTo("request") == 0 )
                  {   myRenderer.setOpenIcon(requestIcon);
                      myRenderer.setClosedIcon(requestIcon);
                      myRenderer.setLeafIcon(requestIcon);
                  }
                  else
                  if ( userObject.getValue().compareTo("response") == 0 )
                  {   myRenderer.setOpenIcon(responseIcon);
                      myRenderer.setClosedIcon(responseIcon);
                      myRenderer.setLeafIcon(responseIcon);
                  }
                  else
                  try
                  {   switch ( userObject.getTipoNumerico() )
                      {   case moduleType:                  //System.out.println(c.toString()+" instanceof module");
                                                            myRenderer.setOpenIcon(moduleIcon);
                                                            myRenderer.setClosedIcon(moduleIcon);
                                                            myRenderer.setLeafIcon(moduleIcon);
                                                            break;
                          case interfaceType:               //System.out.println(c.toString()+" instanceof interface");
                                                            myRenderer.setOpenIcon(interfaceIcon);
                                                            myRenderer.setClosedIcon(interfaceIcon);
                                                            myRenderer.setClosedIcon(interfaceIcon);
                                                            break;
                          case operationType:               //System.out.println(c.toString()+" instanceof operation");
                                                            myRenderer.setOpenIcon(operationIcon);
                                                            myRenderer.setClosedIcon(operationIcon);
                                                            myRenderer.setLeafIcon(operationIcon);
                                                            break;
                          case enumType:                    myRenderer.setOpenIcon(enumIcon);
                                                            myRenderer.setClosedIcon(enumIcon);
                                                            myRenderer.setLeafIcon(enumIcon);
                                                            break;
                          case structType:                  myRenderer.setOpenIcon(structIcon);
                                                            myRenderer.setClosedIcon(structIcon);
                                                            myRenderer.setLeafIcon(structIcon);
                                                            break;
                          case unionType:                   myRenderer.setOpenIcon(unionIcon);
                                                            myRenderer.setClosedIcon(unionIcon);
                                                            myRenderer.setLeafIcon(unionIcon);
                                                            break;
                          case sequenceType:                myRenderer.setOpenIcon(sequenceIcon);
                                                            myRenderer.setClosedIcon(sequenceIcon);
                                                            myRenderer.setLeafIcon(sequenceIcon);
                                                            break;
                          case exceptionType:               myRenderer.setOpenIcon(exceptionIcon);
                                                            myRenderer.setClosedIcon(exceptionIcon);
                                                            myRenderer.setLeafIcon(exceptionIcon);
                                                            break;
                          case aliasType:                   myRenderer.setOpenIcon(aliasIcon);
                                                            myRenderer.setClosedIcon(aliasIcon);
                                                            myRenderer.setLeafIcon(variableIcon);  //ok!
                                                            break;
                          case typedefType:                 myRenderer.setOpenIcon(typedefIcon);
                                                            myRenderer.setClosedIcon(typedefIcon);
                                                            myRenderer.setLeafIcon(typedefIcon);
                                                            break;
                          case arrayType:                   myRenderer.setOpenIcon(typedefIcon);
                                                            myRenderer.setClosedIcon(typedefIcon);
                                                            myRenderer.setLeafIcon(typedefIcon);
                                                            break;
                          default:                          myRenderer.setOpenIcon(variableIcon);
                                                            myRenderer.setClosedIcon(variableIcon);
                                                            myRenderer.setLeafIcon(variableIcon);
                                                            break;
                      }
                  }   catch (Exception e) {   System.out.println("eccezione in mycellrenderer poichè selezionato nodo inesistente");
                                          }
                  myRenderer.setBackgroundNonSelectionColor(treeBackColor);
                  //myRenderer.setBackground(treeBackColor);
                  //myRenderer.setFont(f);
                  return  myRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
              }
           }
           //System.out.println("ne container ne contained");
           myRenderer.setLeafIcon(rootIcon);
           myRenderer.setOpenIcon(rootIcon);
           myRenderer.setClosedIcon(rootIcon);
           myRenderer.setBackgroundNonSelectionColor(treeBackColor);
           return myRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
   }
}