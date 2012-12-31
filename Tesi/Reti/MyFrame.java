package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

 public abstract class MyFrame extends JFrame implements MyFrameInterface
 {  protected static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
    protected static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
    protected static final int PLAIN_MESSAGE = JOptionPane.PLAIN_MESSAGE;
    protected static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;
    protected static InteractionManagementInterface actions;

    public void showMessageDialog(String text, int type)
    {   String msg;
        switch (type)
        {   case ERROR_MESSAGE      : msg = "ERRORE";
                                      break;
            case WARNING_MESSAGE    : msg = "WARNING";
                                      break;
            case PLAIN_MESSAGE      : msg = "INFO";
                                      break;
            case INFORMATION_MESSAGE: msg = "INFO";
                                      break;
            default                 : msg = "ERRORE";
                                      System.out.println("Attenzione: My Frame - specificato tipo non previsto. caso di default!");
        }
        JOptionPane.showMessageDialog(this, text, msg, type);
    }

    public void println(String text)
    {   actions.getDebugWindow().println(text);
    }

}