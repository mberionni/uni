package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

 public interface SessionRecorderInterface
 {
     public String initRecordingSession(String logHome) throws java.lang.Exception;
     public void actionRecord(String request, String response, DefaultMutableTreeNode requestNode, DefaultMutableTreeNode responseNode) throws java.io.IOException;
     public DefaultMutableTreeNode getRecordedSession(File file) throws java.lang.Exception;
 }