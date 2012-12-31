package PrintPackage;

/**
 * Class: PFPrinFile <p>
 */
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.Font;
import java.awt.Color;
import java.io.FileReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import javax.swing.JFrame;
import javax.swing.JDialog;

public class PFPrintFile
{ protected String headerText = "";
  protected String currentFile;
  protected String lastDate;
  protected SimpleDateFormat formatter;
  protected Date currentDate;
  protected PageFormat pageFormat;
  protected PFPoint Base, NewBase;
  protected double textH = 0;
  protected PFDocument document = null;
  protected Font f = new Font("Verdana", Font.PLAIN, 10);
  protected boolean externHeader = false;

  public PFPrintFile(String file) throws java.io.IOException
  {   createDocument(file, false, true, true);
  }

  public PFPrintFile(String file, boolean showPageDialog, boolean showPrintDialog) throws java.io.IOException
  {   createDocument(file, showPageDialog, showPrintDialog, true);
  }

  public PFPrintFile(String file, boolean showPageDialog, boolean showPrintDialog, boolean makeHeader) throws java.io.IOException
  {   createDocument(file, showPageDialog, showPrintDialog, makeHeader);
  }

  public void setHeader(String _headerText)
  {   headerText = _headerText;
      externHeader = true;
  }

  public void setHeaderFont(Font _f)
  {   f = _f;
  }

  public PFDocument createDocument(String file, boolean showPageDialog, boolean showPrintDialog, boolean makeHeader) throws java.io.IOException
  {   currentFile = file;
      String textLine = "";
      formatter = new SimpleDateFormat ("ddMMMyyyy_HH:mm", Locale.getDefault());
      currentDate = new Date();
      lastDate = formatter.format(currentDate);
      int i = 0, numPag = 0;
      textH = 0;
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      PFSize PSize;
      Base = new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0));
      NewBase = (PFPoint)Base.clone();

      document = new PFDocument ();

      document.setDocumentName(currentFile);
      document.showPrintDialog(showPrintDialog);
      document.showPageDialog(showPageDialog);

      PFPage page = new PFPage ();
      if (makeHeader)
      {   createHeader(page, numPag + 1);
      }

      PFUnit pageWidth = page.getPrintableAreaSize().getWidth();
      PFUnit pageHeight = page.getPrintableAreaSize().getHeight();
      PFParagraph paragraph;

      document.addPage (page, numPag);

      System.out.println("page height = " + page.getPrintableAreaSize().getHeight().getUnits());

      while ( (textLine = br.readLine()) != null)
      {    if (textLine.compareTo("") == 0)
           {   textLine = "     ";
           }
            paragraph = new PFParagraph ();
            page.add(paragraph);
            paragraph.setText (textLine);
            PSize = new PFSize(pageWidth, paragraph.getNextParagraphPosition().getY());
            paragraph.setSize (PSize);
            textH = textH + paragraph.getNextParagraphPosition().getY().getUnits();
            if (textH < pageHeight.getUnits())
            {   paragraph.setPosition (Base);
                Base = (PFPoint)Base.clone();
                NewBase.add(paragraph.getNextParagraphPosition());
                Base = (PFPoint)NewBase.clone();
            }
            else
            {   numPag++;
                System.out.println("\n nuova pagina numpag = " + numPag);

                page.remove(paragraph);
                Base = new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0));
                NewBase = (PFPoint)Base.clone();
                page = new PFPage();
                textH = 0;
                if (makeHeader)
                {   createHeader(page, numPag + 1);
                }
                document.addPage(page, numPag);
                page.add(paragraph);
                textH = textH + paragraph.getNextParagraphPosition().getY().getUnits();
                paragraph.setPosition (Base);
                Base = (PFPoint)Base.clone();
                NewBase.add(paragraph.getNextParagraphPosition());
                Base = (PFPoint)NewBase.clone();
            }
       }
       return document;

 }

  protected void createHeader(PFPage page, int pageNum)
  {     PFUnit pageWidth = page.getPrintableAreaSize().getWidth();
        PFParagraph header = new PFParagraph();
        header.setFont(f);
        header.setTextColor(Color.blue);
        page.add(header);
        if (!externHeader)
        {   headerText = "--- " + lastDate + " - printing: " + currentFile + " - pag " + pageNum;
        }
        header.setText(headerText);
        PFSize PSize = new PFSize(pageWidth, header.getNextParagraphPosition().getY());
        header.setSize (PSize);
        textH = textH + header.getNextParagraphPosition().getY().getUnits();
        header.setPosition(Base);
        header.setHorizontalAlignment(PFParagraph.CENTER_JUSTIFIED);
        Base = (PFPoint)Base.clone();
        NewBase.add(header.getNextParagraphPosition());
        Base = (PFPoint)NewBase.clone();
        PFLine pl = new PFLine();
        pl.setTickness(2);
        pl.setWidth(pageWidth);
        pl.setEndPoint(new PFPoint(pageWidth, Base.getY()));
        pl.setPosition(Base);
        //textH = textH + 0.2;
        PFParagraph nullLine = new PFParagraph();
        page.add(nullLine);
        nullLine.setText("    ");
        PSize = new PFSize(pageWidth, nullLine.getNextParagraphPosition().getY());
        nullLine.setSize (PSize);
        textH = textH + nullLine.getNextParagraphPosition().getY().getUnits();
        nullLine.setPosition(Base);
        Base = (PFPoint)Base.clone();
        NewBase.add(nullLine.getNextParagraphPosition());
        Base = (PFPoint)NewBase.clone();
  }

  public void print()
  {   document.print();
  }

  public Pageable getPageable()
  {   return document.getPageable();
  }

  /*public void printPreview(JFrame frame)
  {   JDialog dlg = new JDialog(frame,"Print Preview: " + currentFile);
      Thread t = new Thread(new Runnable()
      {   public void run()
          { PrintPreviewer pp = new PrintPreviewer(document.getPageable(), 0);
get            dlg.getContentPane().add(pp);
            dlg.setSize(500, 600);
            dlg.setVisible(true);
          }
      });
      t.start();
  }*/
}
