package PrintPackage;
/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */
import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.table.*;
import Reti.ConfigDataAccessClient;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TablePrinter implements Printable, Pageable
{ protected JTable table;
  protected PageFormat pageFormat;
  protected int headerStatus = ALL_PAGES;
  protected boolean header = false, footer = false;
  protected String headerText = "", footerText = "", addToHeader = "";
  Font f = ConfigDataAccessClient.f;
  /** These constants indicate which pages should include column headers
   */
  public final static int ALL_PAGES = 0;
  public final static int FIRST_PAGE_ONLY = 1;
  public final static int NO_PAGES = 2;

  public TablePrinter(JTable tbl, PageFormat pf)
  { this(tbl, pf, true, false);
  }

  public TablePrinter(JTable tbl, PageFormat pf, boolean _header, boolean _footer)
  {   table = tbl;
      pageFormat = pf;
      header = _header;
      footer = _footer;
  }

  public TablePrinter(String _addToHeader, JTable tbl, PageFormat pf)
  {   this(tbl, pf, true, false);
      addToHeader = _addToHeader;
  }

  /**
   * Perform the printing here
   */
  public int print(Graphics g, PageFormat pf, int index)
  { Dimension size = null;
    //  Get the table's preferred size
    if ((table.getWidth() == 0) || (table.getHeight() == 0))
    {   table.setSize(table.getPreferredSize());
    }
    int tableWidth = table.getWidth();
    int tableHeight = table.getHeight();
    int positionX = 0;
    int positionY = 0;

    //  Loop until we have printed the entire table
    int pageIndex = 0;
    while (positionY < tableHeight)
    { positionX = 0;
      while (positionX < tableWidth)
      { size = getPrintSize(positionX, positionY);
        if (pageIndex == index)
        { //  Paint as much of the table as will fit on a page
          paintTable(g, positionX, positionY, size, pageIndex);
          return Printable.PAGE_EXISTS;
        }
        pageIndex++;
        positionX += size.width;
      }
      positionY += size.height;
    }
    return Printable.NO_SUCH_PAGE;
  }

  /**
   * Calculate how much of the table will fit on a page without
   * causing a row or column to be split across two pages
   */
  protected Dimension getPrintSize(int positionX, int positionY)
  { Rectangle rect;
    int printWidth;
    int printHeight;
    int firstCol = table.columnAtPoint(new Point(positionX, positionY));
    int firstRow = table.rowAtPoint(new Point(positionX, positionY));
    int maxWidth = (int)(pageFormat.getImageableWidth());
    int maxHeight;
    if (header)
    {   maxHeight = (int)(pageFormat.getImageableHeight()) - f.getSize() - 22;
    }
    else
    {   maxHeight = (int)(pageFormat.getImageableHeight());
    }
    if (displayHeaderOnPage(positionY))
    {   maxHeight -= table.getTableHeader().getHeight();
    }

    int lastCol = table.columnAtPoint(new Point(positionX + maxWidth, positionY));
    if (lastCol == -1)
    {   printWidth = table.getWidth() - positionX;
    }
    else
    {   rect = table.getCellRect(0, lastCol - 1, true);
        printWidth = rect.x + rect.width - positionX;
    }

    int lastRow = table.rowAtPoint(new Point(positionX, positionY + maxHeight));
    if (lastRow == -1)
    {   printHeight = table.getHeight() - positionY;
    }
    else
    {   rect = table.getCellRect(lastRow - 1, 0, true);
        printHeight = rect.y + rect.height - positionY;
    }
    return new Dimension(printWidth, printHeight);
  }

  /**
   * Paint / print a portion of the table
   */
  protected void paintTable(Graphics g, int positionX, int positionY, Dimension size, int pageIndex)
  { int offsetX = (int)(pageFormat.getImageableX());
    int offsetY = (int)(pageFormat.getImageableY());
    if (header)
    {   int width = (int) (offsetX + pageFormat.getWidth());
        int height = offsetY + f.getSize() + 2;
        g.drawLine(offsetX, offsetY + 1, width, offsetY + 1);
        g.drawString(headerText + " pag. " + (pageIndex+1) + "/" + getNumberOfPages(), offsetX, height);
        g.drawLine(offsetX, height + 5, width, height + 5);
        offsetY = height + 20;
    }

    if (displayHeaderOnPage(positionY)) {
      JTableHeader header = table.getTableHeader();
      if ((header.getWidth() == 0) ||
          (header.getHeight() == 0)) {
        header.setSize(header.getPreferredSize());
      }
      int headerHeight = header.getHeight();
      g.translate(offsetX - positionX, offsetY);
      g.clipRect(positionX, 0, size.width, size.height + headerHeight);
      header.paint(g);
      g.translate(0, headerHeight - positionY);
      g.clipRect(positionX, positionY, size.width, size.height);
    }
    else {
      g.translate(offsetX - positionX, offsetY - positionY);
      g.clipRect(positionX, positionY, size.width, size.height);
    }
    table.paint(g);

  }

 /*public void printHeaderFooter(Graphics g, String text, int offsetX, int offsetY, int width, int height)
  {   System.out.println("dentro printheaderfooter");
      g.drawLine(offsetX, offsetY, width, offsetY);
      g.drawString(text, offsetX, offsetY + 2);
      g.drawLine(offsetX, height, width, height);
  }
*/

  /**
   * Determine whether or not to paint the headers on the current page
   */
  protected boolean displayHeaderOnPage(int positionY) {
    return ((headerStatus == ALL_PAGES) ||
        ((headerStatus == FIRST_PAGE_ONLY) &&
        positionY == 0));
  }

  /**
   * Calculate the number of pages it will take to print the entire table
   */
  public int getNumberOfPages() {
    Dimension size = null;
    int tableWidth = table.getWidth();
    int tableHeight = table.getHeight();
    int positionX = 0;
    int positionY = 0;

    int pageIndex = 0;
    while (positionY < tableHeight) {
      positionX = 0;
      while (positionX < tableWidth) {
        size = getPrintSize(positionX, positionY);
        positionX += size.width;
        pageIndex++;
      }
      positionY += size.height;
    }
    return pageIndex;
  }

  public Printable getPrintable(int index) {
    return this;
  }

  public PageFormat getPageFormat(int index) {
    return pageFormat;
  }

  protected void setFooter(String text)
  {   footerText = text;
  }

  public void setHeader(String text)
  {   System.out.println("setheader " + text);
      headerText = text;
  }

  public void useDefaultHeader()
  {   SimpleDateFormat formatter = new SimpleDateFormat ("dd MMM yyyy - HH:mm", Locale.getDefault());
      Date currentDate = new Date();
      String lastDate = formatter.format(currentDate);
      if (addToHeader.compareTo("") == 0)
      {   headerText = lastDate;
      }
      else
      {   headerText = lastDate + " - " + addToHeader + " - ";
      }
  }
}
