package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

 import java.awt.Font;
 import java.awt.Color;
 import javax.swing.ImageIcon;
 import java.util.*;

  public class ConfigDataAccessServer
  { static ResourceBundle resources;

    static
    {   try   {  resources = ResourceBundle.getBundle("Reti.ServerResources/Reti", Locale.getDefault());
              } catch (Exception mre)
                      {    System.out.println("Reti.ServerResoureces/Reti.properties not found");
                           mre.printStackTrace();
                      }
    }
    static String font = resources.getString("BaseFont");
    static Font f = new Font(font,java.awt.Font.PLAIN, 10);
    static Font f1 = new Font(font,java.awt.Font.PLAIN, 11);
    static Font f2 = new Font(font,java.awt.Font.BOLD, 11);
    static String IORHome = resources.getString("IORHome");
    static String EntryPointName = resources.getString("EntryPointName");
    static String serverIconPath = resources.getString("serverIconPath");
    static ImageIcon openFileIcon = new ImageIcon(resources.getString("openFileIcon"));

  }