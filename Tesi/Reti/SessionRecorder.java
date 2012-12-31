package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.io.*;
import TreeTablePackage.JTreeTable;
import javax.swing.tree.DefaultMutableTreeNode;

 public class SessionRecorder implements SessionRecorderInterface
 { protected SimpleDateFormat formatter, simpleFormatter;
   protected Date currentDate;
   protected String logFileName = "", serFileName = "";;
   protected RandomAccessFile logFile;
   protected ObjectOutputStream oos;

   public String initRecordingSession(String logHome) throws java.lang.Exception
   {      String lastDate = "", simpleDate = "";
          formatter = new SimpleDateFormat ("ddMMMyyyy_HH_mm", Locale.getDefault());
          simpleFormatter = new SimpleDateFormat (" dd MMM yyyy ", Locale.getDefault());
          currentDate = new Date();
          simpleDate = simpleFormatter.format(currentDate);
          lastDate = formatter.format(currentDate);
          if (logHome.indexOf(".") < 0)    // permette che il nome sia determinato a default o dall'utente!
          {   logFileName = logHome + "/log" + lastDate + ".txt";
              serFileName = logHome + "/log" + lastDate + ".ser";
          }
          else
          {   logFileName = logHome;
              serFileName = logHome.substring(0, logHome.length() - 4) + ".ser";
          }
          boolean exists = true;
          try
          {   logFile = new RandomAccessFile(logFileName, "r");
          }   catch (Exception ex)  {   logFile = new RandomAccessFile(logFileName, "rw");
                                        exists = false;
                                        println("file creato adesso eccezione controllata = " + ex.toString());
                                    }
          if (exists)
          {   //LogW.println("azz! file gia' esistente");
              formatter = new SimpleDateFormat ("ddMMMyyyy_HH_mm_ss", Locale.getDefault());
              currentDate = new Date();
              lastDate = formatter.format(currentDate);
              logFileName = logHome + "/log" + lastDate + ".txt";
              serFileName = logHome + "/log" + lastDate + ".ser";
              logFile = new RandomAccessFile(logFileName, "rw");
          }
          logFile.writeBytes(simpleDate);
          FileOutputStream fos = new FileOutputStream(serFileName);
          oos = new ObjectOutputStream (fos);
          return logFileName;
  }

  public void actionRecord(String request, String response, DefaultMutableTreeNode requestNode, DefaultMutableTreeNode responseNode) throws java.io.IOException
  {  logFile.writeBytes(request + "\n" + response + "\n\n");
     if (requestNode != null) oos.writeObject(requestNode);
     if (responseNode != null) oos.writeObject(responseNode);
  }

  public DefaultMutableTreeNode getRecordedSession(File file) throws java.lang.Exception
  {   FileInputStream fis = new FileInputStream(file.getAbsolutePath());
      ObjectInputStream ois = new ObjectInputStream(fis);
      DefaultMutableTreeNode node = new DefaultMutableTreeNode("Root", true);
      try
      {   while(true)
          {    DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)ois.readObject();
               node.add(currentNode);
          }
      }   catch (EOFException IOExc)  {    println("Eccezione controllata di fine file");
                                      }
      return node;
  }

  protected void println(String text)
  {   System.out.println(text);
  }
}