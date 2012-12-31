package Reti;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.text.SimpleDateFormat;
import javax.swing.tree.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.*;
import javax.swing.border.*;
import java.io.RandomAccessFile;


/**
 * Title:        Progetto di reti di calcolatori
 * Description:  Progetti di reti di calcolatori
 * uso funzionalità avanzate CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      unibo
 * @author michele berionni
 * @version 1.0
 */

public class Frame2 extends JFrame
{ JPanel contentPane;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel111 = new JPanel();
  JPanel jPanel112 = new JPanel();
  JLabel jLabel12 = new JLabel();
  JPanel jPanel113 = new JPanel();
  JPanel jPanel114 = new JPanel();
  JLabel jLabel13 = new JLabel();
  JPanel jPanel115 = new JPanel();
  JPanel jPanel14 = new JPanel();
  JScrollPane jScrollPane3 = new JScrollPane();
  JScrollPane jScrollPane4 = new JScrollPane();
  JTextArea jTextArea2 = new JTextArea();
  JTextField jTextField4 = new JTextField();
  JButton jButton6 = new JButton();
  JButton jButton12 = new JButton();
  JButton jButton13 = new JButton();
  JButton jButton14 = new JButton();
  JButton jButton15 = new JButton();
  JButton jButton16 = new JButton();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel statusBar = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  creaOggetto oggettoNodo;
  ParameterDescription[] parameters;
  String risultato;
  Vector v = new Vector(5,2);
  org.omg.CORBA.Object rif = null;
  private String font = Frame1.resources.getString("fontFrame2");
  public Font f = new Font(font, java.awt.Font.PLAIN,10);
  public Font f1 = new Font(font, java.awt.Font.PLAIN,11);
  public Font f2 = new Font(font, java.awt.Font.BOLD,11);
  private SimpleDateFormat formatter;
  private String lastdate;
  private Date currentDate;
  private static DefaultMutableTreeNode nodoSel;
  private TitledBorder titledBorder1;
  private  Border border1;
  private boolean terminated = false, inserted= false, message=false, firstTime=true, bool = false, rightSelection = false;
  int k;
  String parIngresso="";
  Frame1 frame1;
  DefaultMutableTreeNode nodeObj = new DefaultMutableTreeNode("Root",true);
  JTree jTree3 = new JTree(nodeObj);
  FlowLayout flowLayout1 = new FlowLayout();


  public Frame2(Frame1 _frame1)
  {   frame1 = _frame1;
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {  jbInit();
          }
      catch(Exception e) {    e.printStackTrace();
                         }
  }

  public Frame2()
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {  jbInit();
          }
      catch(Exception e) {    e.printStackTrace();
                         }
  }

  private void jbInit() throws Exception
  { //setIconImage(Toolkit.getDefaultToolkit().createImage(Frame1.resources.getString("aboutImage")));
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    border1 = BorderFactory.createCompoundBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(148, 145, 140)),BorderFactory.createEmptyBorder(0,2,0,2));
    contentPane.setLayout(gridBagLayout5);
    this.setSize(new Dimension(384, 463));
    this.setTitle("Inserimento argomenti");
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {   public void windowDeactivated(WindowEvent e)
        {   this_windowDeactivated(e);
        }
    });
    statusBar.setBackground(Color.lightGray);
    statusBar.setFont(f);
    statusBar.setForeground(Color.darkGray);
    statusBar.setBorder(BorderFactory.createLineBorder(Color.black));
    statusBar.setMaximumSize(new Dimension(800, 21));
    statusBar.setMinimumSize(new Dimension(210, 21));
    statusBar.setPreferredSize(new Dimension(210, 21));
    statusBar.setText(" ");
    contentPane.setBackground(Color.lightGray);

    jPanel1.setLayout(gridBagLayout4);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setPreferredSize(new Dimension(384, 472));
    jPanel10.setLayout(flowLayout3);
    jPanel10.setBackground(Color.lightGray);
    jPanel10.setMinimumSize(new Dimension(77, 30));
    jPanel10.setPreferredSize(new Dimension(77, 30));

    jButton12.setFont(f);
    jButton12.setForeground(Color.darkGray);
    jButton12.setBorder(border1);
    jButton12.setMaximumSize(new Dimension(60, 30));
    jButton12.setMinimumSize(new Dimension(50, 20));
    jButton12.setPreferredSize(new Dimension(50, 20));
    jButton12.setText("Info");
    jButton12.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton12_actionPerformed(e);
        }
    });
    jButton13.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton13_actionPerformed(e);
        }
    });
    jButton13.setText("Annulla");
    jButton13.setFont(f);
    jButton14.addActionListener(new Frame2_jButton14_actionAdapter(this));
    jButton14.setText("Esegui Metodo");
    jButton14.setFont(f);
    jButton6.setText("Ripeti");
    jButton6.setFont(f);
    jPanel5.setBackground(Color.lightGray);
    jPanel5.setBorder(BorderFactory.createEtchedBorder());
    jPanel5.setMinimumSize(new Dimension(136, 65));
    jPanel5.setPreferredSize(new Dimension(136, 65));
    jPanel5.setLayout(gridBagLayout2);
    jPanel6.setLayout(flowLayout4);
    jPanel111.setLayout(gridBagLayout1);
    jPanel111.setBackground(Color.lightGray);
    jPanel111.setMinimumSize(new Dimension(110, 33));
    jPanel111.setPreferredSize(new Dimension(110, 33));
    jPanel112.setLayout(gridBagLayout3);
    jPanel112.setPreferredSize(new Dimension(370, 90));
    jPanel112.setMinimumSize(new Dimension(370, 90));
    jPanel112.setBorder(BorderFactory.createEtchedBorder());
    jPanel112.setBackground(Color.lightGray);
    jLabel12.setFont(f2);
    jPanel113.setLayout(gridLayout1);
    jPanel113.setBackground(Color.lightGray);
    jPanel113.setMinimumSize(new Dimension(150, 24));
    jPanel113.setPreferredSize(new Dimension(150, 24));
    jPanel114.setPreferredSize(new Dimension(15, 25));
    jPanel114.setMinimumSize(new Dimension(15, 25));
    jPanel114.setBackground(Color.lightGray);
    jPanel114.setLayout(flowLayout1);
    jLabel13.setFont(f1);
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setText("Nome argomento:");
    jPanel115.setLayout(flowLayout2);
    jPanel115.setBackground(Color.lightGray);
    jPanel115.setMinimumSize(new Dimension(147, 25));
    jPanel115.setPreferredSize(new Dimension(157, 25));
    jPanel14.setLayout(borderLayout1);
    jScrollPane3.setPreferredSize(new Dimension(24, 24));
    jScrollPane4.getViewport().setBackground(Color.lightGray);
    jScrollPane4.setMinimumSize(new Dimension(53, 170));
    jScrollPane4.setPreferredSize(new Dimension(53, 5000));
    jTextArea2.setBackground(Color.lightGray);
    jTextArea2.setEditable(false);
    jTextArea2.setBackground(Color.white);
    jTextArea2.setFont(f);
    jLabel14.setFont(f2);
    jButton15.setBackground(Color.lightGray);
    jButton15.setFont(f);
    jButton15.setForeground(new Color(0, 0, 217));
    jButton15.setText("Esegui");
    jButton15.setVerticalAlignment(SwingConstants.TOP);
    jButton15.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton15_actionPerformed(e);
        }
    });
    jTextField4.setFont(f1);
    jTextField4.setMinimumSize(new Dimension(270, 25));
    jTextField4.setPreferredSize(new Dimension(280, 25));
    jTextField4.setDisabledTextColor(Color.lightGray);
    jTextField4.setHorizontalAlignment(SwingConstants.CENTER);
    jButton16.setFont(f);
    jButton16.setBorder(border1);
    jButton16.setMaximumSize(new Dimension(60, 30));
    jButton16.setMinimumSize(new Dimension(50, 25));
    jButton16.setPreferredSize(new Dimension(50, 25));
    jButton16.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton16.setText("Ok");
    jButton16.addActionListener(new java.awt.event.ActionListener()
    {   public void actionPerformed(ActionEvent e)
        {   jButton16_actionPerformed(e);
        }
    });
    jLabel6.setFont(f2);
    jLabel6.setText("    metodo scelto:");
    jLabel7.setBackground(Color.white);
    jLabel7.setFont(f2);
    jLabel7.setText("  Risultato: ");
    jLabel8.setBackground(Color.lightGray);
    jLabel8.setFont(f);
    jTree3.setPreferredSize(new Dimension(300, 70));
    jTree3.setMinimumSize(new Dimension(300, 70));
    jTree3.setMaximumSize(new Dimension(5000, 5000));
    jTree3.setBackground(Color.lightGray);
    gridLayout1.setColumns(3);
    jPanel14.setBackground(Color.lightGray);
    jPanel14.setMinimumSize(new Dimension(76, 60));
    jPanel14.setPreferredSize(new Dimension(362, 60));
    jPanel6.setMinimumSize(new Dimension(259, 31));
    jPanel6.setPreferredSize(new Dimension(259, 31));
    contentPane.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 0, 0, 0), 0, 0));
    jPanel1.add(statusBar, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 1));
    jPanel1.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1), 0, 0));
    jPanel5.add(jPanel113, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 10, 2, 11), 0, 0));
    jPanel113.add(jLabel6, null);
    jPanel113.add(jButton12, null);
    jPanel5.add(jPanel114, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 7, 11), 0, 0));
    jPanel114.add(jLabel8, null);
    jPanel114.add(jLabel14, null);
    jPanel1.add(jPanel6, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1), 0, 0));
    jPanel6.add(jButton13, null);
    jPanel6.add(jButton6, null);
    jPanel6.add(jButton14, null);
    jPanel1.add(jPanel112, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1), 0, 0));
    jPanel112.add(jPanel115, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 2), 0, 0));
    jPanel115.add(jLabel13, null);
    jPanel115.add(jLabel12, null);
    jPanel112.add(jPanel111, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 2), 0, 0));
    jPanel111.add(jTextField4, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
    jPanel111.add(jButton16, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel112.add(jPanel10, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 2), 0, 0));
    jPanel10.add(jButton15, null);
    jPanel1.add(jPanel14, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1), 0, 0));
    jPanel14.add(jScrollPane3, BorderLayout.CENTER);
    jPanel14.add(jLabel7, BorderLayout.WEST);
    jPanel1.add(jScrollPane4, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 0, 0));
    jScrollPane4.getViewport().add(jTree3, null);
    jScrollPane3.getViewport().add(jTextArea2, null);
    jScrollPane3.getViewport();
    jScrollPane4.getViewport();

  }
  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  { super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {    // System.exit(0);
    }
  }

  public void configurazione(DefaultMutableTreeNode nodo, boolean _bool, org.omg.CORBA.Object _rif)
  {   terminated = false;
      inserted = false;
      bool = _bool;
      rif = _rif;
      DefaultMutableTreeNode padre = (DefaultMutableTreeNode)nodo.getParent();
      creaOggetto oggInt = (creaOggetto)padre.getUserObject();
      oggettoNodo = (creaOggetto)nodo.getUserObject();

      if (bool)
          {   rif = oggInt.getRif();
              System.out.println("il rif che ho ricevuto è: "+rif);
          }
      k = 0;
      OperationDef operazione = OperationDefHelper.narrow(oggettoNodo.tipo);
      parameters = operazione.params();
      jLabel8.setText(oggettoNodo.description);
      System.out.println("descr "+oggettoNodo.description);
      jLabel14.setText(oggettoNodo.name + oggettoNodo.signature);
      System.out.println("name + sign "+oggettoNodo.name + oggettoNodo.signature);

      jTextArea2.setText("");
      if (parameters.length==0)
      {   inserted = true;
          jLabel12.setText("Nessun parametro");
          System.out.println("il metodo selezionato non ha nessun par");
          jButton16.setEnabled(false);
          jTextField4.setEditable(false);
      }
      else
      {   jTextField4.setEditable(true);
          jButton16.setEnabled(true);
          System.out.println("il metodo selezionato ha "+parameters.length+" parametri");
          jLabel12.setText(descriviPar(parameters[k]));
      }
  }


  private void jButton16_actionPerformed(ActionEvent e)  //tasto "ok" conferma inserimento arg
  {
      statusBar.setText("Inserito valore argomento");
      System.out.println("inserisco arg con valore="+jTextField4.getText()+" k="+k);
      if (k == 0) parIngresso="";
      String value = jTextField4.getText();
      v.add(k, value);
      String comma = k == parameters.length - 1 ? "" : ",";
      parIngresso = parIngresso + value + " " + comma;
      k++;
      if (k==parameters.length)
          {   jTextField4.setEditable(false);
              jTextField4.setBackground(Color.white);
              jButton16.setEnabled(false);
              inserted = true;
          }
      else
          {   jTextField4.setText("");
              jLabel12.setText(descriviPar(parameters[k]));
          }
  }

  void jButton15_actionPerformed(ActionEvent e)  //tasto "esegui" - inizio esecuzione remota
  {  if (!inserted)
        showMessageDialog("Impostazione chiamata remota incompleta");
     else
     {    inserted = false;
          System.out.println("Frame2 evento: premuto tasto invio richiesta bool="+bool+" rif class="+rif);
          statusBar.setText("Inviata invocazione remota");
          jTextField4.setEditable(false);
          risultato = eseguiMetodo(oggettoNodo, bool, rif);  // sincronizzazione
          if (frame1.CheckBox1isSelected())
              memorizzaAzione();
          jTextArea2.setText(risultato);
          jTextField4.setText("");
          parIngresso="";
          statusBar.setText("Ricevuto risultato");
          terminated = true;
    }
  }

  void jButton12_actionPerformed(ActionEvent e)  // premuto bottone "info"
  {   System.out.println("Premuto bottone info");
      message = true;
      JDialog jd = new Frame2_AboutBox(this);
      jd.setVisible(true);
      message = false;
  }

  void jButton13_actionPerformed(ActionEvent e)  // premuto bottone "annulla" di uscita
  {   jTextField4.setText("");
      jTextField4.setEditable(false);
      jTextField4.setBackground(Color.white);
      jButton16.setEnabled(false);
      jTextArea2.setText("");
      jLabel14.setText("");
      jLabel8.setText("");
      jLabel12.setText("Nessun parametro");
      terminated = true;
      inserted = false;
      System.out.println("Frame 2 - evento premuto bottone di annulla ");
      // eventualmente trasferire il fuoco
  }

  protected  void showMessageDialog(String msg)
  {   System.out.println("apro finestra di dialogo");
      message = true;
      JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
      System.out.println("chiusa finestra di dialogo");
      message = false;

  }

  void this_windowDeactivated(WindowEvent e)  //finestra frame2 perde il fuoco
  {   statusBar.setText("  ");
      System.out.println("La finestra ha perso il fuoco terminate = " + terminated);
      if (!message)
      {   if (!terminated)
             showMessageDialog("Inviare richiesta o annullare");
      }
  }

   private void configuraTree(JTree jTree1)
  { TreeSelectionModel model = jTree1.getSelectionModel();
    model.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    MyCellRenderer renderer = new MyCellRenderer();
    jTree1.setCellRenderer(renderer);
    jTree1.addTreeSelectionListener( new TreeSelectionListener()
        {    public void valueChanged (TreeSelectionEvent event)
             {   if (terminated)
                 {    TreePath path = event.getPath();
                     java.lang.Object nodo = path.getLastPathComponent();
                     nodoSel = (DefaultMutableTreeNode)nodo;
                     System.out.println("evento "+nodo+" class="+nodo.getClass());
                     if (!nodoSel.isRoot())
                     {  oggettoNodo = (creaOggetto)nodoSel.getUserObject();

                        if (oggettoNodo.tipo.def_kind()==DefinitionKind.dk_Operation)
                           {    rightSelection = true;
                                statusBar.setText("Selezionato metodo "+oggettoNodo.toString());
                           }
                        else {    //jTextField4.setText("");
                                  statusBar.setText("Selezione non eseguibile");
                                  rightSelection = false;
                             }
                     }
                 } else {   statusBar.setText("Selezione al momento non eseguibile - completare richiesta");
                            rightSelection = false;
                        }
              }
        }  );
    jTree1.putClientProperty("JTree.lineStyle","Angled");
    jTree1.setFont(f);
    jTree1.setBorder(BorderFactory.createLoweredBevelBorder());
    jTree1.setBackground(frame1.getTreeBackColor());
  }

  protected void visualizzaTree(DefaultMutableTreeNode albero)
  {  firstTime = false;
     jTree3 = new JTree(albero);
     configuraTree(jTree3);
     jScrollPane4.getViewport().add(jTree3, null);
  }

  void memorizzaAzione()
  {  formatter = new SimpleDateFormat ("ddMMMyyyy_HH:mm", Locale.getDefault());
     currentDate = new Date();
     lastdate = formatter.format(currentDate);
     String row = lastdate+ " --> operazione:"+oggettoNodo.name+"( "+parIngresso+")"+" --> risultato="+risultato;
     if (frame1.Button1isEnabled()==false)    {   Frame3.stampa(row);
                                              }
     try {    RandomAccessFile logF = frame1.getLogFile();
              logF.writeBytes(row);
              logF.writeBytes("\n");
         } catch (Exception e)  {   showMessageDialog("ERRORE scrittura file di log");
                                }
  }

  public  String eseguiMetodo(creaOggetto oggetto, boolean bool , org.omg.CORBA.Object rif)
   {    org.omg.CORBA.Object manager;
        org.omg.CORBA.ORB orb = Client.getOrb();

        try
        {   OperationDef op = OperationDefHelper.narrow((org.omg.CORBA.Object)oggetto.tipo);
            org.omg.CORBA.Container container = op.defined_in();
            InterfaceDef interface1 = InterfaceDefHelper.narrow(container);
        if (!bool)
            {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
                NamingContext root = NamingContextHelper.narrow(rootObj);
                System.out.println("Localizzato correttamente Naming service");
                NameComponent interfaceName = new NameComponent(interface1.name(), "");
                System.out.println("sto cercando ogggetto con nome "+interface1.name());
                manager = root.resolve(new NameComponent[]{interfaceName});
                System.out.println("Individuato correttamente oggetto "+interface1.name()+" nel naming service");
            }
            else
            {   manager = rif;
            }

            ParameterDescription[] parameters = op.params();
            System.out.println("Costruzione dinamica richiesta per operazione "+op.name());
            org.omg.CORBA.Request request = manager._request(op.name());
            org.omg.CORBA.NVList arguments = request.arguments();
            org.omg.CORBA.Any customer;
            for(int k = 0; k < parameters.length; k++)
            {   TCKind tipo = parameters[k].type.kind();
                // si suppone che gli argomenti siano tutti in ingresso (ARG_IN) è possibile comunque estendere
                System.out.println("k="+k+" param in ingresso di nome="+parameters[k].name+" tipo="+tipo.value());
                switch (tipo.value())
                {   case TCKind._tk_string:   System.out.println("è un tipo stringa value="+TCKind._tk_string);
                                              customer = orb.create_any();
                                              customer.insert_string((String)v.elementAt(k));
                                              arguments.add_item(parameters[k].name, org.omg.CORBA.ARG_IN.value);

                                              arguments.add_value(parameters[k].name, customer, org.omg.CORBA.ARG_IN.value);
                                              System.out.println("ho associato a "+parameters[k].name+" il valore "+customer);
                                              break;
                    case TCKind._tk_long:     System.out.println("è un tipo int value="+TCKind._tk_long);
                                              customer = orb.create_any();
                                              customer.insert_long( (new Integer( (String)(v.elementAt(k)) ) ).intValue() );
                                              arguments.add_value(parameters[k].name, customer, org.omg.CORBA.ARG_IN.value);
                                              break;
                    case TCKind._tk_longlong: System.out.println("è un tipo long value="+TCKind._tk_longlong);
                                              customer = orb.create_any();
                                              customer.insert_longlong( (new Long( (String)(v.elementAt(k)) ) ).longValue() );
                                              arguments.add_value(parameters[k].name, customer, org.omg.CORBA.ARG_IN.value);
                                              break;
                    case TCKind._tk_float:    System.out.println("è un tipo float value="+TCKind._tk_float);
                                              customer = orb.create_any();
                                              customer.insert_float( (new Float( (String)(v.elementAt(k)) ) ).floatValue() );
                                              arguments.add_value(parameters[k].name, customer, org.omg.CORBA.ARG_IN.value);
                                              break;
                    case TCKind._tk_objref:   System.out.println("è un tipo riferimento ad oggetto value="+TCKind._tk_objref);
                                              customer = orb.create_any();
                                              customer.insert_Object(  (org.omg.CORBA.Object)(v.elementAt(k))   );
                                              arguments.add_value(parameters[k].name, customer, org.omg.CORBA.ARG_IN.value);
                                              break;
                    default:                  System.out.println(" caricamento parametri - caso di default");
                                              break;

                }

            }
            // Set the result type
            TCKind retKind = op.result_def().type().kind();
            request.set_return_type(orb.get_primitive_tc(op.result_def().type().kind()));
            request.invoke();
            // Get the return value
            org.omg.CORBA.Any op_result = request.return_value();
            String retValue;
            org.omg.CORBA.Object retObj = null;
            //System.out.println("param in uscita cod "+retKind.value()+" tipo bollean="+TCKind._tk_boolean);

            switch (retKind.value())
            {   case TCKind._tk_string:   retValue=op_result.extract_string();
                                          break;
                case TCKind._tk_long:     retValue = (new Integer(op_result.extract_long())).toString();
                                          break;
                case TCKind._tk_longlong: retValue = (new Long(op_result.extract_longlong())).toString();
                                          break;
                case TCKind._tk_float:    retValue = (new Float(op_result.extract_float())).toString()                          ;
                                          break;
                case TCKind._tk_boolean:  retValue = (new Boolean(op_result.extract_boolean())).toString();
                                          break;
                case TCKind._tk_objref:   retValue = "ricevuto riferimento ad oggetto remoto";
                                          retObj = op_result.extract_Object();
                                          break;
                default:                  retValue="default";
                                          System.out.println("sono nel caso di default della cattura del valore di ritorno");
            }
            if (!(retObj==null))
               {  visualizzaOggetto(retObj);
               }
            System.out.println("Il risultato e': "+retValue);
            return retValue;
           }   catch (Exception e) {  System.out.println("ERRORE:");
                                      e.printStackTrace();
                                      return "ERRORE";
                                   }
    }

    public  void visualizzaOggetto(org.omg.CORBA.Object obj)
    {   System.out.println("Visualizzo struttura oggetto ricevuto");   //obj è il riferimento remoto
        org.omg.CORBA.Object o = obj._get_interface_def();
        InterfaceDef idef = InterfaceDefHelper.narrow(o);
        InterfaceDef[] idef2 = {idef};
        System.out.println("id="+idef.id());
        System.out.println("name="+idef.name());
        navigaRepository nR = Client.getnR();
        DefaultMutableTreeNode oldTree = nodeObj;
        nodeObj = new DefaultMutableTreeNode("Root",true); //inserire se non si vuole tenere traccia dei vecchi oggetti ricevuti
        System.out.println("sto per chiamare cruise2 con rif= "+obj);
        nodeObj = nR.cruise2(idef2, nodeObj, obj);
        bool = true;
        if (firstTime)
            visualizzaTree(nodeObj);
        else aggiungiTree(oldTree, nodeObj);
    }

    protected void aggiungiTree(DefaultMutableTreeNode oldTree, DefaultMutableTreeNode newTree)
    {  DefaultMutableTreeNode r = (DefaultMutableTreeNode)newTree.getChildAt(0);
       oldTree.add(r);
       nodeObj = oldTree;
       jTree3.updateUI();
    }

  void jButton14_actionPerformed(ActionEvent e)  // bottone "esegui metodo"
  {   if (!rightSelection)
         {   statusBar.setText("Selezione errata");
             showMessageDialog("Devi selezionare un metodo da eseguire");
         }
      else
      if (!terminated)
        {   statusBar.setText("Terminare richiesta in corso");
            showMessageDialog("Devi terminare la richiesta in corso");
        }
      else
      {   //System.out.println("nodo sel: "+nodoSel.toString());
          //jTree3.setSelectionRow(0); //.setSelectionPath(new TreePath(jTree3.getroo();
           configurazione(nodoSel, true, rif);
      }
  }

  protected String descriviPar(ParameterDescription p)
  {  String[] mode = { "in", "out", "inout" };
     String parametro = mode[p.mode.value()] + " " + toIdl(p.type_def) + " " + p.name;
     return parametro;
  }

  private String toIdl(org.omg.CORBA.IDLType idlType)
  {    org.omg.CORBA.Contained contained= null;
       String ret;

       try  {   if (idlType.type().name().compareTo("Object")==0)
                  return "object";
               contained = org.omg.CORBA.ContainedHelper.narrow(idlType);
            } catch (Exception e) {}
       if (contained == null)
          ret = idlType.type().toString();
       else
          ret =  contained.absolute_name();
       if (ret.equals("string[0]") )
          return "string";
       else return ret;
    }
} // class

  class Frame2_jButton14_actionAdapter implements java.awt.event.ActionListener
  {  Frame2 adaptee;
     Frame2_jButton14_actionAdapter(Frame2 adaptee)
     {    this.adaptee = adaptee;
     }

     public void actionPerformed(ActionEvent e)
    {    adaptee.jButton14_actionPerformed(e);
    }
  }