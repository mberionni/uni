package Reti;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.RandomAccessFile;
import org.omg.CORBA.DefinitionKind;

/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class Frame1 extends JFrame
{ JPanel contentPane;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel statusBar = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField1 = new JTextField();
  private SimpleDateFormat formatter;
  private String lastdate;
  private Date currentDate;
  private DefaultMutableTreeNode nodoSel = null;
  private creaOggetto oggettoNodo;
  private boolean rightSelection = false, packFrame = false;
  protected RandomAccessFile logFile;
  protected Frame2 frame2;

  public DefaultMutableTreeNode albero = Client.node;

  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTree jTree1 = new JTree(albero);

  protected static ResourceBundle resources;
  private String font = resources.getString("fontFrame1");
  public Font f = new Font(font,java.awt.Font.PLAIN,10);
  public Font f1 = new Font(font,java.awt.Font.PLAIN,11);
  public Font f2 = new Font(font,java.awt.Font.BOLD,11);
  private final String frameTitle = resources.getString("frameTitle");
  private final String services = resources.getString("services");
  private final String track = resources.getString("track");
  private final String trackEn = resources.getString("trackEn");
  private final String update = resources.getString("update");
  private final String execute = resources.getString("execute");
  private final String updatement = resources.getString("updatement");
  private final String operation = resources.getString("operation");
  private final String leafFile = resources.getString("leafFile");
  private final String logFilePath = resources.getString("logFilePath");
  protected Color treeBackColor = Color.getColor(resources.getString("treeBackColor"));

  static
  {   try   {  resources = ResourceBundle.getBundle("Reti.resources/Reti", Locale.getDefault());
            } catch (MissingResourceException mre)
                    {    System.err.println("Reti.resoureces/Reti.properties not found");
                         mre.printStackTrace();
                    }
  }

  /**Construct the frame*/
  public Frame1()
  { enableEvents(AWTEvent.WINDOW_EVENT_MASK);
   try {  jbInit();
        }
    catch(Exception e) {  e.printStackTrace();
                       }
  }
  /**Component initialization*/
  private void jbInit() throws Exception
  { statusBar.setFont(f);
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(gridBagLayout3);
    this.setSize(new Dimension(520, 486));
    this.setTitle(frameTitle);
    statusBar.setBackground(Color.gray);
    statusBar.setBorder(BorderFactory.createRaisedBevelBorder());
    statusBar.setText(" ");

    jButton1.setFont(f1);
    jButton1.setText(track);
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {  public void actionPerformed(ActionEvent e)
      {  jButton1_actionPerformed(e);
      }
    });

    jButton2.setFont(f1);
    jButton2.setNextFocusableComponent(jButton1);
    jButton2.setText(execute);
    jButton2.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton2_actionPerformed(e);
        }
    });

    jButton3.setFont(f1);
    jButton3.setNextFocusableComponent(jButton2);
    jButton3.setText(update);
    jButton3.setSelected(true);
    jButton3.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton3_actionPerformed(e);
        }
    });

    jPanel1.setBackground(Color.lightGray);
    jPanel1.setLayout(gridBagLayout2);
    jPanel3.setLayout(gridBagLayout1);
    jPanel4.setLayout(borderLayout5);
    jPanel5.setBackground(Color.lightGray);
    jPanel5.setLayout(borderLayout3);
    jPanel6.setBackground(Color.lightGray);
    jPanel6.setLayout(borderLayout2);
    jPanel7.setBackground(Color.lightGray);
    jPanel7.setLayout(borderLayout1);
    jCheckBox1.setText(trackEn);
    jCheckBox1.setBackground(Color.lightGray);
    jCheckBox1.setFont(f);
    jCheckBox1.setSelected(true);
    jLabel1.setFont(f2);
    jLabel1.setText(services);
    jLabel2.setFont(f);
    jLabel2.setMaximumSize(new Dimension(107, 27));
    jLabel2.setMinimumSize(new Dimension(107, 27));
    jLabel2.setPreferredSize(new Dimension(107, 27));
    jLabel2.setText(operation);
    jLabel3.setFont(f);
    jLabel3.setMaximumSize(new Dimension(132, 27));
    jLabel3.setMinimumSize(new Dimension(132, 27));
    jLabel3.setPreferredSize(new Dimension(132, 27));
    jLabel3.setText(updatement);
    jPanel4.setMinimumSize(new Dimension(251, 30));
    jPanel4.setPreferredSize(new Dimension(251, 30));
    jTextField1.setFont(f1);
    jTextField1.setEditable(false);
    jTextField1.setBackground(Color.white);
    jTextField1.setHorizontalAlignment(jTextField2.CENTER);
    jTextField1.setMaximumSize(new Dimension(20, 21));
    jTextField1.setMinimumSize(new Dimension(4, 10));
    jTextField1.setPreferredSize(new Dimension(4, 10));
    jTextField2.setFont(f1);
    jTextField2.setEditable(false);
    jTextField2.setBackground(Color.white);
    jTextField2.setHorizontalAlignment(jTextField2.CENTER);
    borderLayout5.setHgap(3);
    jPanel3.setBackground(Color.lightGray);
    contentPane.setBackground(Color.lightGray);

    configuraTree(jTree1);

    jScrollPane1.getViewport().setBackground(Color.lightGray);
    jTree1.setBackground(Color.lightGray);
    contentPane.add(statusBar, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 513, 0));
    contentPane.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
    jPanel1.add(jPanel3, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(7, 30, 0, 15), 1, 0));
    jPanel3.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(19, 19, 0, 17), 77, 43));
    jPanel5.add(jCheckBox1, BorderLayout.CENTER);
    jPanel3.add(jPanel6, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(22, 8, 0, 8), 83, 31));
    jPanel6.add(jLabel2, BorderLayout.NORTH);
    jPanel6.add(jTextField1, BorderLayout.CENTER);
    jPanel3.add(jPanel7, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(22, 8, 13, 8), 58, 20));
    jPanel7.add(jTextField2, BorderLayout.CENTER);
    jPanel7.add(jLabel3, BorderLayout.NORTH);
    jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(18, 19, 0, 84), 45, 0));
    jPanel1.add(jPanel4, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(38, 92, 30, 104), 31, 0));
    jPanel4.add(jButton3, BorderLayout.WEST);
    jPanel4.add(jButton2, BorderLayout.CENTER);
    jPanel4.add(jButton1, BorderLayout.EAST);
    jPanel1.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(8, 18, 0, 0), 216, 299));
    jScrollPane1.getViewport().add(jTree1, null);

    try {   formatter = new SimpleDateFormat ("ddMMMyyyyHHmm", Locale.getDefault());
            currentDate = new Date();
            lastdate = formatter.format(currentDate);
            String logFileName = logFilePath+lastdate+".txt";
            logFile = new RandomAccessFile(logFileName,"rw");
        }   catch(Exception e) {   showMessageDialog("Errore accesso file di LOG");
                                   jCheckBox1.setEnabled(false);
                              }

    frame2 = new Frame2(this);
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
    {   frame2.pack();
    }
    else {    frame2.validate();
         }
    frame2.setVisible(false);

  }

  public Frame2 getFrame2()
  { return frame2;
  }

  public boolean esiste(DefaultMutableTreeNode nodo)
  {  if (nodoSel == null) return true;
     if (nodoSel.isRoot()) return true;
     creaOggetto oggetto = (creaOggetto)nodo.getUserObject();
     try {    DefinitionKind dk = oggetto.tipo.def_kind();
         }  catch (Exception e) {  System.out.println("Oggetto non ancora esistente");
                                   return false;
                                }
     return true;
  }

  private void configuraTree(JTree jTree1)
  { TreeSelectionModel model = jTree1.getSelectionModel();
    model.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    MyCellRenderer renderer = new MyCellRenderer();
    jTree1.setCellRenderer(renderer);
    jTree1.addTreeSelectionListener( new TreeSelectionListener()
        {    public void valueChanged (TreeSelectionEvent event)
             {   TreePath path = event.getPath();
                 Object nodo = path.getLastPathComponent();
                 nodoSel = (DefaultMutableTreeNode)nodo;
                 System.out.println("selezionato nodo " + nodo + " class=" + nodo.getClass());
                 if (!esiste(nodoSel))      {   System.out.println("Eccezione in configurazione poiche' repository modificato");
                                                statusBar.setText("Selezione non più disponibile");
                                                showMessageDialog("Contenuto repository modificato - Update automatico");
                                                bottone();
                                            }
                 else  {   if (!nodoSel.isRoot())
                           {    oggettoNodo = (creaOggetto)nodoSel.getUserObject();
                                DefinitionKind dk = oggettoNodo.tipo.def_kind();
                                if (dk == DefinitionKind.dk_Operation)
                                {   jTextField1.setText(oggettoNodo.description+oggettoNodo.name);
                                    rightSelection = true;
                                    statusBar.setText("Selezionato metodo");
                                }
                                else {    jTextField1.setText("");
                                          statusBar.setText("Selezione non eseguibile");
                                          rightSelection = false;
                                }

                           }  else rightSelection = false;
                      }
             }
        }
                                    );
    jTree1.putClientProperty("JTree.lineStyle","Angled");
    jTree1.setFont(f);
    jTree1.setBorder(BorderFactory.createLoweredBevelBorder());
    jTree1.setBackground(treeBackColor);

  }

  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  { super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {   System.exit(0);
    }
  }

  void jButton3_actionPerformed(ActionEvent e)      // evento premuto bottone update
  {  bottone();
  }

  public void bottone()      // evento premuto bottone update
  {  statusBar.setText("Aggiornamento repository in corso");
     System.out.println("Premuto update - Aggiornamento repository in corso");
     navigaRepository nR = new navigaRepository();
     albero = new DefaultMutableTreeNode("RootRep",true);
     albero=nR.cruise(Client.ir,albero);
     jScrollPane1.remove(jTree1);
     jScrollPane1.repaint();
     jTree1 = new JTree(albero);
     configuraTree(jTree1);
     jScrollPane1.getViewport().add(jTree1, null);
     statusBar.setText("Aggiornamento repository effettuato");
     formatter = new SimpleDateFormat ("EEE dd MMM yyyy - HH:mm:ss", Locale.getDefault());
     currentDate = new Date();
     lastdate = formatter.format(currentDate);
     jTextField2.setText(lastdate);
  }

  void jButton2_actionPerformed(ActionEvent e)    // evento premuto bottone esecuzione remota
  {   if (!esiste(nodoSel))  {   System.out.println("Eccezione in configurazione poiche' repository modificato");
                                 statusBar.setText("Selezione non più disponibile");
                                 showMessageDialog("Contenuto repository modificato - Update automatico");
                                 bottone();
                             }
      else
      {   if (!rightSelection)
          {   statusBar.setText("Selezione errata");
              showMessageDialog("Devi selezionare un metodo da eseguire");
          }
          else
          {   //System.out.println("nodo sel: "+nodoSel.toString());
              frame2.configurazione(nodoSel, false, null);
              frame2.setVisible(true);
          }
      }
  }


  protected  void showMessageDialog(String msg)
  { JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
  }

  void jButton1_actionPerformed(ActionEvent e) // premuto tasto showtrack
  {  jButton1.setEnabled(false);
     new Track(this);

     String line="";
     System.out.println("finesra costruita - leggo log file");
     try{   long fpPos = logFile.getFilePointer();
            logFile.seek(0);
             while ( (line=logFile.readLine())!=null)
             {    System.out.println("ho letto dal file la linea"+line);
                  Frame3.stampa(line);
             }
            logFile.seek(fpPos);
        }
     catch (Exception ex)  {  showMessageDialog("ERRORE lettura file di log");
                           }

  }

  public void enableButton1()
  {   jButton1.setEnabled(true);
  }

  public boolean Button1isEnabled()
  {   return jButton1.isEnabled();
  }

  public boolean CheckBox1isSelected()
  {   return jCheckBox1.isSelected();
  }

  public Color getTreeBackColor()
  {   return treeBackColor;
  }

  public RandomAccessFile getLogFile()
  {   return logFile;
  }
}