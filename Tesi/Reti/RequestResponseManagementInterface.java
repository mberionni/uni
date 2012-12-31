package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferii
 * @version 1.0
 */

 import javax.swing.tree.DefaultMutableTreeNode;

 public interface RequestResponseManagementInterface
 {
    public void request(OggettoNodo oggNodoSel, org.omg.CORBA.Object rif, String mode);

    public void returnFromIns();

    public void println(String text);

    public void setRequest(DefaultMutableTreeNode requestNode, RequestResponseManagementInterface managerClass);

    public void setResult(DefaultMutableTreeNode responseNode, String textualResponse);

    public DefaultMutableTreeNode browseObject(org.omg.CORBA.Object object) throws globaldefs.ProcessingFailureException;

 }