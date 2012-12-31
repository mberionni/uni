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
import java.awt.print.*;
import java.awt.Graphics;
import java.awt.Color;


public class toPrint implements Printable
{   protected String testo;

    public toPrint (String text)
    {  testo = text;
    }

    public toPrint ()
    {
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException
    {  if (pageIndex == 0)
       {   g.drawString(testo, 100, 100);
           return Printable.PAGE_EXISTS;
       }
       g.drawString(testo, 100, 100);
       return Printable.NO_SUCH_PAGE;
   }
}