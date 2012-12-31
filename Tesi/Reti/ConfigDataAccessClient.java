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
 import java.awt.Image;
 import java.awt.Dimension;
 import javax.swing.ImageIcon;
 import java.util.*;
 import org.omg.CORBA.IDLType;
 import org.omg.CORBA.Repository;
 import org.omg.CORBA.PrimitiveKind;

  public class ConfigDataAccessClient
  { static Repository ir = Client.getRepository();
    static ResourceBundle resources;

    static
    {   try   {  resources = ResourceBundle.getBundle("Reti.ClientResources/Reti", Locale.getDefault());
               } catch (Exception mre)
                      {    System.out.println("Reti.ClientResoureces/Reti.properties not found");
                           mre.printStackTrace();
                      }
    }

    public static final int moduleType = 1;
    public static final int interfaceType = 2;
    public static final int operationType = 3;
    public static final int exceptionType = 4;
    public static final int enumType = 5;
    public static final int structType = 6;
    public static final int sequenceType = 7;
    public static final int unionType = 8;
    public static final int aliasType = 9;
    public static final int typedefType = 10;
    public static final int arrayType = 11;
    public static final int attributeType = 13;
    public static final int defaultType = 12;

    public static IDLType tipoStringa = ir.get_primitive(PrimitiveKind.pk_string);
    public static IDLType tipoLong = ir.get_primitive(PrimitiveKind.pk_long);
    public static IDLType tipoULong = ir.get_primitive(PrimitiveKind.pk_ulong);
    public static IDLType tipoVoid = ir.get_primitive(PrimitiveKind.pk_void);
    public static IDLType tipoBoolean = ir.get_primitive(PrimitiveKind.pk_boolean);

    public static Dimension PreferredButtonDimension = new Dimension(Integer.parseInt(resources.getString("PreferredButtonDimensionX")), Integer.parseInt(resources.getString("PreferredButtonDimensionY")));
    public static String font = resources.getString("BaseFont");
    public static String ConnectButtonText = resources.getString("ConnectButtonText");
    public static String DisconnectButtonText = resources.getString("DisconnectButtonText");
    public static String ExecuteButtonText = resources.getString("ExecuteButtonText");
    public static String PlayButtonText = resources.getString("PlayButtonText");
    public static String RecButtonText = resources.getString("RecButtonText");
    public static String RecButtonText2 = resources.getString("RecButtonText2");
    public static String StopButtonText = resources.getString("StopButtonText");
    public static String LogPathButtonText = resources.getString("LogPathButtonText");
    public static String OpenButtonText = resources.getString("OpenButtonText");
    public static String UpdateButtonText = resources.getString("UpdateButtonText");
    public static Font f = new Font(font, java.awt.Font.PLAIN,10);
    public static Font f1 = new Font(font, java.awt.Font.PLAIN,11);
    public static Font f2 = new Font(font, java.awt.Font.BOLD,11);
    public static String buttonFont  = resources.getString("InsertArgsButtonFont");
    public static String treeFont = resources.getString("InsertArgsTreeFont");
    public static String logHome = resources.getString("logHome");
    public static String IORHome = resources.getString("IORHome");
    public static String EntryPointName = resources.getString("EntryPointName");
    public static String iconPath = resources.getString("iconPath");
    public static String monitorWinIconPath = resources.getString("monitorWinIconPath");
    public static Color treeBackColor = parseColor(resources.getString("treeBackColor"));
    public static ImageIcon aboutFileImage = new ImageIcon(resources.getString("aboutFileImage"));
    public static ImageIcon openFileIcon = new ImageIcon(resources.getString("openFileIcon"));
    public static ImageIcon saveFileIcon = new ImageIcon(resources.getString("saveFileIcon"));
    public static ImageIcon image3 = new ImageIcon(resources.getString("saveFileIcon"));
    public static ImageIcon rootIcon = new ImageIcon(resources.getString("rootIcon"));
    public static ImageIcon moduleIcon = new ImageIcon(resources.getString("moduleIcon"));
    public static ImageIcon interfaceIcon = new ImageIcon(resources.getString("interfaceIcon"));
    public static ImageIcon operationIcon = new ImageIcon(resources.getString("operationIcon"));
    public static ImageIcon variableIcon = new ImageIcon(resources.getString("variableIcon"));
    public static ImageIcon attributeIcon = new ImageIcon(resources.getString("attributeIcon"));
    public static ImageIcon enumIcon = new ImageIcon(resources.getString("enumIcon"));
    public static ImageIcon unionIcon = new ImageIcon(resources.getString("unionIcon"));
    public static ImageIcon structIcon = new ImageIcon(resources.getString("structIcon"));
    public static ImageIcon sequenceIcon = new ImageIcon(resources.getString("sequenceIcon"));
    public static ImageIcon exceptionIcon = new ImageIcon(resources.getString("exceptionIcon"));
    public static ImageIcon aliasIcon = new ImageIcon(resources.getString("aliasIcon"));
    public static ImageIcon typedefIcon = new ImageIcon(resources.getString("typedefIcon"));
    public static ImageIcon connectIcon = new ImageIcon(resources.getString("connectIcon"));
    public static ImageIcon disconnectIcon = new ImageIcon(resources.getString("disconnectIcon"));
    public static ImageIcon aboutIcon = new ImageIcon(resources.getString("aboutIcon"));
    public static ImageIcon executeIcon = new ImageIcon(resources.getString("executeIcon"));
    public static ImageIcon updateIcon = new ImageIcon(resources.getString("updateIcon"));
    public static ImageIcon startIcon = new ImageIcon(resources.getString("startIcon"));
    public static ImageIcon requestIcon = new ImageIcon(resources.getString("requestIcon"));
    public static ImageIcon responseIcon = new ImageIcon(resources.getString("responseIcon"));
    public static ImageIcon playIcon = new ImageIcon(resources.getString("playIcon"));
    public static ImageIcon stopIcon = new ImageIcon(resources.getString("stopIcon"));
    public static ImageIcon recIcon = new ImageIcon(resources.getString("recIcon"));
    public static ImageIcon printIcon = new ImageIcon(resources.getString("printIcon"));
    public static ImageIcon txtLogIcon = new ImageIcon(resources.getString("txtLogIcon"));
    public static ImageIcon printPreviewIcon = new ImageIcon(resources.getString("printPreviewIcon"));
    public static ImageIcon pageSetupIcon = new ImageIcon(resources.getString("pageSetupIcon"));
    public static ImageIcon zoomInIcon =  new ImageIcon(resources.getString("zoomInIcon"));
    public static ImageIcon zoomOutIcon =  new ImageIcon(resources.getString("zoomOutIcon"));
    public static ImageIcon firstPageIcon =  new ImageIcon(resources.getString("firstPageIcon"));
    public static ImageIcon lastPageIcon =  new ImageIcon(resources.getString("lastPageIcon"));
    public static ImageIcon nextPageIcon =  new ImageIcon(resources.getString("nextPageIcon"));
    public static ImageIcon previousPageIcon =  new ImageIcon(resources.getString("previousPageIcon"));
    public static ImageIcon irepIcon =  new ImageIcon(resources.getString("irepIcon"));
    public static ImageIcon semaphoreRedIcon =  new ImageIcon(resources.getString("semaphoreRedIcon"));
    public static ImageIcon semaphoreGreenIcon =  new ImageIcon(resources.getString("semaphoreGreenIcon"));
    public static ImageIcon semaphoreYellowIcon =  new ImageIcon(resources.getString("semaphoreYellowIcon"));
    public static ImageIcon infoPanelActivateIcon =  new ImageIcon(resources.getString("infoPanelActivateIcon"));
    public static ImageIcon infoPanelDeactivateIcon =  new ImageIcon(resources.getString("infoPanelDeactivateIcon"));
    public static ImageIcon writeAttributeIcon =  new ImageIcon(resources.getString("writeAttributeIcon"));
    public static ImageIcon readAttributeIcon =  new ImageIcon(resources.getString("readAttributeIcon"));


    public static Color parseColor(String s)
    {   int red = 0;
        int green = 0;
        int blue = 0;
        try {    StringTokenizer st = new StringTokenizer(s, ",");
                 red = Integer.parseInt(st.nextToken());
	         green = Integer.parseInt(st.nextToken());
                 blue = Integer.parseInt(st.nextToken());
            } catch (Exception e)
                    {   System.out.println(e);
                        System.out.println("Couldn't parse color :" + s);
                    }
        return new Color(red, green, blue);
    }

  }