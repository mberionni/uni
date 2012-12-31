package Reti;

/**
 * Title:        Tesi di laurea - ingegneria informatica
 * Description:  Sistema di testing di apparati di telecomunicazione remoti - Java & CORBA
 * Copyright:    Copyright (c) 2001
 * Company:      Finsoft - università di Bologna
 * @author michele berionni - prof. A.Corradi - ing. G.Maccaferri
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CosNaming.*;
import javax.swing.border.TitledBorder;
/*import org.omg.CORBA.Repository;
import org.omg.CORBA.RepositoryHelper;*/

public class ConnectToServer extends JFrame
{ protected JPanel contentPane;
  protected JPanel jPanel1 = new JPanel();
  protected JPanel jPanel2 = new JPanel();
  protected JPanel jPanel3 = new JPanel();
  protected JTextField iorPath = new JTextField();
  protected JRadioButton IORRadioButton = new JRadioButton();
  protected JRadioButton NameServiceRadioButton = new JRadioButton();
  protected JButton BindingButton = new JButton();
  protected FlowLayout flowLayout1 = new FlowLayout();
  protected  org.omg.CORBA.ORB orb;
  protected boolean exit = true;
  protected ButtonGroup group = new ButtonGroup();
  protected GridBagLayout gridBagLayout1 = new GridBagLayout();
  protected GridBagLayout gridBagLayout2 = new GridBagLayout();
  protected GridBagLayout gridBagLayout3 = new GridBagLayout();
  protected TitledBorder titledBorder1;
  protected MyMainFrame returnClass;
//  protected Repository ir;


  protected Font f1 = ConfigDataAccessClient.f1;
  protected String IORHome = ConfigDataAccessClient.IORHome;
  protected String EntryPointName = ConfigDataAccessClient.EntryPointName;
  protected ImageIcon openIcon = ConfigDataAccessClient.openFileIcon;

  protected final JFileChooser fc = new JFileChooser(IORHome);
  protected JButton jButton1 = new JButton("Sfoglia...", openIcon);

  public ConnectToServer(org.omg.CORBA.ORB _orb)
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      orb = _orb;
      try {  jbInit();
          }
      catch(Exception e)  {    e.printStackTrace();
                          }
  }

  public ConnectToServer()
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try
      {   jbInit();
      }
      catch(Exception e) {    e.printStackTrace();
                         }
  }

  private void jbInit() throws Exception
  { this.setResizable(false);
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    contentPane.setLayout(gridBagLayout3);
    this.setSize(new Dimension(390, 250));
    this.setTitle("Modalità recupero IOR server");
    this.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        this_keyPressed(e);
      }
    });
    jPanel1.setLayout(gridBagLayout1);
    jPanel2.setLayout(gridBagLayout2);
    jPanel3.setLayout(flowLayout1);
    BindingButton.setFont(f1);
    BindingButton.setName("Binding");
    BindingButton.setMaximumSize(new Dimension(90, 30));
    BindingButton.setNextFocusableComponent(IORRadioButton);
    BindingButton.setPreferredSize(new Dimension(90, 27));
    BindingButton.setToolTipText("tenta recupero IOR del server");
    BindingButton.setText("Get  IOR");

    BindingButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        BindingButton_actionPerformed(e);
      }
    });
    iorPath.setEnabled(false);
    iorPath.setFont(f1);
    iorPath.setBorder(BorderFactory.createEtchedBorder());
    iorPath.setPreferredSize(new Dimension(250, 19));
    iorPath.setEditable(false);
    iorPath.setMargin(new Insets(0, 20, 0, 10));
    iorPath.setText(IORHome);
    iorPath.setColumns(2);
    iorPath.setHorizontalAlignment(SwingConstants.CENTER);
    NameServiceRadioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        NameServiceRadioButton_actionPerformed(e);
      }
    });
    NameServiceRadioButton.setNextFocusableComponent(jButton1);
    NameServiceRadioButton.setSelected(true);
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
    jButton1.setBorder(BorderFactory.createEtchedBorder());
    jButton1.setMaximumSize(new Dimension(95, 50));
    jButton1.setMinimumSize(new Dimension(90, 30));
    jButton1.setNextFocusableComponent(BindingButton);
    jButton1.setPreferredSize(new Dimension(90, 50));

    IORRadioButton.setNextFocusableComponent(NameServiceRadioButton);
    jPanel3.setBackground(Color.lightGray);
    contentPane.setBackground(Color.lightGray);
    jPanel2.setBackground(Color.lightGray);
    group.add(IORRadioButton);
    group.add(NameServiceRadioButton);
    IORRadioButton.setText("da file system locale");
    IORRadioButton.setFont(f1);
    IORRadioButton.setName("IOR");
    IORRadioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        IORRadioButton_actionPerformed(e);
      }
    });
    NameServiceRadioButton.setText("da Naming Service");
    NameServiceRadioButton.setFont(f1);
    NameServiceRadioButton.setName("NameService");
    NameServiceRadioButton.setFocusPainted(true);
    jButton1.setEnabled(false);
    jButton1.setFont(f1);
    jButton1.setName("Sfoglia");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    contentPane.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(19, 5, 0, 5), -1, -2));
    jPanel1.add(IORRadioButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 43, 0, 52), 125, 0));
    jPanel1.add(NameServiceRadioButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 43, 24, 52), 140, 0));
    contentPane.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 5, 0, 5), 0, 0));
    jPanel2.add(iorPath, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 0, 9, 0), 248, 14));
    jPanel2.add(jButton1, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 7, 0, 0), 0, 4));
    contentPane.add(jPanel3, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 4, 5), 271, 11));
    jPanel3.add(BindingButton, null);
  }

  protected void jButton1_actionPerformed(ActionEvent e)
  {   fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fc.setMultiSelectionEnabled(false);
      ExampleFileFilter filter = new ExampleFileFilter();
      filter.addExtension("ior");
      filter.setDescription("ior Files");
      fc.setFileFilter(filter);

      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {   File file = fc.getSelectedFile();
          String path = file.getAbsolutePath();
          iorPath.setText(path);
      }   else {   System.out.println("Open command cancelled by user.");
               }
  }

  protected void IORRadioButton_actionPerformed(ActionEvent e)
  {    iorPath.setEnabled(true);
       jButton1.setEnabled(true);
  }

  protected void BindingButton_actionPerformed(ActionEvent e)
  {   exit = false;
      try
      {   /*org.omg.CORBA.Object rootObj = Client.getOrb().resolve_initial_references("InterfaceRepository");
          ir = RepositoryHelper.narrow(rootObj);
          Client.setRepository(ir);*/
      }   catch (Exception ex)   {  ex.printStackTrace();
                                    System.out.println("Problemi nella localizzazione dell'Interface Repository. Assicurarsi che il servizio sia attivo");
                                 }
      System.out.println("Localizzato correttamente repository");

      if (IORRadioButton.isSelected())
      {    String FilePath = iorPath.getText();
           if ( iorPath.getText().compareTo("") == 0)
           {   showMessageDialog("Selezionare il file contenente lo IOR");
           }
           else
           {   FileBinding(FilePath);
           }
       }
       else //uso Naming Service
       {    NameServerBinding();
       }
  }

  protected  void FileBinding(String FilePath)
  {   try
      {   LineNumberReader input = new LineNumberReader(new FileReader(FilePath));
          org.omg.CORBA.Object object = orb.string_to_object(input.readLine());
          if (object._non_existent()) {   System.out.println("Server non attivo");
                                          throw new org.omg.CORBA.TRANSIENT("Server non attivo");
                                      }
          processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
          ((ApplicationGUI)returnClass).startSession(object);
          this.dispose();
      }   catch (Exception e) {   if (e instanceof org.omg.CORBA.TRANSIENT)
                                  {   showMessageDialog("Ops! Server attualmente non attivo - impossibile stabilire il collegamaneto");
                                  }
                                  else
                                  {   showMessageDialog("Gasp! Il file indicato non contiene uno IOR o lo IOR non è valido");
                                  }
                                  this.dispose();

                              };

  }

  protected  void NameServerBinding()
  {   try
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext root = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente Naming service");
          NameComponent interfaceName = new NameComponent(EntryPointName, "");
          org.omg.CORBA.Object object = root.resolve(new NameComponent[] { interfaceName });
          System.out.println("Individuato correttamente oggetto " + EntryPointName + " nel naming service");
          if (object._non_existent()) {   System.out.println("Server non attivo");
                                          throw new org.omg.CORBA.TRANSIENT("Server non attivo");
                                      }
          processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
          ((ApplicationGUI)returnClass).startSession(object);
          this.dispose();
      }
      catch (Exception e) {   e.printStackTrace();
                              if (e instanceof org.omg.CORBA.OBJECT_NOT_EXIST)
                              {   showMessageDialog("Ops! Naming Server non attivo - impossibile stabilire il collegamaneto");
                              }
                              else
                              if (e instanceof org.omg.CORBA.TRANSIENT)
                              {   showMessageDialog("Ops! Server attualmente non attivo - impossibile stabilire il collegamaneto");
                              }
                              else
                              {   showMessageDialog("Ops! Errore durante la ricerca nel Naming Service - impossibile stabilire il collegamento");
                              }
                              this.dispose();

                          }
  }

  protected void processWindowEvent(WindowEvent e)
  {   super.processWindowEvent(e);
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {   //if (exit) System.exit(0);
      }
  }

  protected void NameServiceRadioButton_actionPerformed(ActionEvent e)
  {   iorPath.setEnabled(false);
      jButton1.setEnabled(false);

  }

  protected  void showMessageDialog(String msg)
  {   JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  protected void this_keyPressed(KeyEvent e)
  {
      if (e.getKeyCode() == KeyEvent.VK_ENTER)
      {   System.out.println("Ho premuto enter key pressed");
          Component componentHasFocus = this.getFocusOwner();
          String componentName = componentHasFocus.getName();
          if (componentName.compareTo("Binding") == 0)
          {   //System.out.println("name="+focus.toString());
              BindingButton_actionPerformed(new ActionEvent(this.BindingButton, ActionEvent.ACTION_PERFORMED, this.BindingButton.getName()));
          }
          else
          if (componentName.compareTo("IOR") == 0)
          {   IORRadioButton.setSelected(true);
              iorPath.setEnabled(true);
              jButton1.setEnabled(true);
          }
          else
          if (componentName.compareTo("NameService") == 0)
          {   NameServiceRadioButton.setSelected(true);
              iorPath.setEnabled(false);
              jButton1.setEnabled(false);
          }
          else
          if (componentName.compareTo("Sfoglia") == 0)
          {   jButton1_actionPerformed(new ActionEvent(this.jButton1, ActionEvent.ACTION_PERFORMED, this.jButton1.getName()));
          }
      }

  }

   protected void setReturnClass(MyMainFrame aG)
   {  returnClass = aG;
   }

}