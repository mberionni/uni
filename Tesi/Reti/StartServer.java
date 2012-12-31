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
import java.io.FileWriter;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.CORBA.ORB;
import com.inprise.vbroker.interceptor.ChainUntypedObjectWrapperFactory;
import com.inprise.vbroker.interceptor.ChainUntypedObjectWrapperFactoryHelper;
import com.inprise.vbroker.interceptor.Location;
import com.inprise.vbroker.PortableServerExt.BindSupportPolicyValue;
import com.inprise.vbroker.PortableServerExt.BindSupportPolicyValueHelper;
import com.inprise.vbroker.PortableServerExt.BIND_SUPPORT_POLICY_TYPE;

public class StartServer extends JFrame
{ protected JPanel contentPane;
  protected JPanel jPanel1 = new JPanel();
  protected JPanel jPanel2 = new JPanel();
  protected JPanel jPanel3 = new JPanel();
  protected JTextField iorPath = new JTextField();
  protected JRadioButton IORRadioButton = new JRadioButton();
  protected JRadioButton NameServiceRadioButton = new JRadioButton();
  protected JButton RegistraButton = new JButton();
  protected ORB orb;
  protected org.omg.CORBA.Object rif;
  protected POA rootPOA;
  protected ButtonGroup group = new ButtonGroup();
  protected boolean exit = true;
  protected GridBagLayout gridBagLayout1 = new GridBagLayout();
  protected FlowLayout flowLayout1 = new FlowLayout();
  protected GridBagLayout gridBagLayout2 = new GridBagLayout();
  protected GridBagLayout gridBagLayout3 = new GridBagLayout();
  protected static ServerMonitorWindow mw;

  protected Font f1 = ConfigDataAccessServer.f1;
  protected String IORHome =  ConfigDataAccessServer.IORHome;
  protected ImageIcon openFileIcon =  ConfigDataAccessServer.openFileIcon;
  protected String EntryPointName =  ConfigDataAccessServer.EntryPointName;

  protected JButton jButton1 = new JButton("Sfoglia...");
  protected final JFileChooser fc = new JFileChooser(IORHome);

  public StartServer(org.omg.CORBA.ORB _orb)
  {   enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      orb = _orb;
      jbInit();
  }

  private void jbInit()
  { initServer();
    this.setResizable(false);
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(gridBagLayout3);
    this.getContentPane().setBackground(Color.lightGray);
    this.setSize(new Dimension(390, 250));
    this.setTitle("Server start up - Registrazione IOR -");
    this.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        this_keyPressed(e);
      }
    });
    jPanel1.setLayout(gridBagLayout1);
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setLayout(gridBagLayout2);
    jPanel3.setLayout(flowLayout1);
    RegistraButton.setFont(f1);
    RegistraButton.setMaximumSize(new Dimension(110, 35));
    RegistraButton.setNextFocusableComponent(IORRadioButton);
    RegistraButton.setPreferredSize(new Dimension(110, 30));
    RegistraButton.setToolTipText("Start");
    RegistraButton.setText("Registra IOR");
    RegistraButton.setName("Registra");
    RegistraButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RegistraButton_actionPerformed(e);
      }
    });
    iorPath.setEnabled(false);
    iorPath.setFont(f1);
    iorPath.setBorder(BorderFactory.createEtchedBorder());
    iorPath.setEditable(false);
    iorPath.setText(IORHome);
    iorPath.setHorizontalAlignment(SwingConstants.CENTER);
    NameServiceRadioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        NameServiceRadioButton_actionPerformed(e);
      }
    });
    NameServiceRadioButton.setNextFocusableComponent(jButton1);
    NameServiceRadioButton.setSelected(true);
    System.out.println("open icon = " + openFileIcon.toString());
    jButton1.setIcon(openFileIcon);
    jButton1.setBorder(BorderFactory.createEtchedBorder());
    jButton1.setMaximumSize(new Dimension(70, 50));
    jButton1.setMinimumSize(new Dimension(70, 30));
    jButton1.setNextFocusableComponent(RegistraButton);
    jButton1.setPreferredSize(new Dimension(70, 48));
    jButton1.setToolTipText("scegli il file in cui registrare lo IOR");
    jButton1.setName("Sfoglia");
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
    contentPane.setForeground(Color.lightGray);
    jPanel3.setBackground(Color.lightGray);
    jPanel3.setForeground(Color.lightGray);
    IORRadioButton.setNextFocusableComponent(NameServiceRadioButton);
    group.add(IORRadioButton);
    group.add(NameServiceRadioButton);
    IORRadioButton.setText("registra IOR su file locale");
    IORRadioButton.setFont(f1);
    IORRadioButton.setName("IOR");
    IORRadioButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        IORRadioButton_actionPerformed(e);
      }
    });
    NameServiceRadioButton.setText("registra IOR al Naming Service");
    NameServiceRadioButton.setFont(f1);
    NameServiceRadioButton.setName("NameService");
    jButton1.setFont(f1);
    jButton1.setEnabled(false);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });

    contentPane.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(19, 5, 0, 5), 10, 7));
    jPanel1.add(IORRadioButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 53, 0, 33), 150, 0));
    jPanel1.add(NameServiceRadioButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 53, 23, 33), 119, 0));
    contentPane.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(8, 5, 0, 5), 37, 0));
    jPanel2.add(jButton1, new GridBagConstraints(1, 0, 1, 1, 0.5, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(3, 7, 3, 0), 7, 3));
    jPanel2.add(iorPath, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 0, 9, 0), 190, 13));
    contentPane.add(jPanel3, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 12, 5), 283, 8));
    jPanel3.add(RegistraButton, null);

  }

  void jButton1_actionPerformed(ActionEvent e)
  {   fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fc.setMultiSelectionEnabled(false);
      ExampleFileFilter filter = new ExampleFileFilter();
      filter.addExtension("ior");
      filter.setDescription("ior Files");
      fc.setFileFilter(filter);

      int returnVal = fc.showSaveDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION)
       {   File file = fc.getSelectedFile();
           String path=file.getAbsolutePath();
           iorPath.setText(path);

       } else {   System.out.println("Save command cancelled by user.");
              }
  }

  void IORRadioButton_actionPerformed(ActionEvent e)
  {    iorPath.setEnabled(true);
       jButton1.setEnabled(true);
       jButton1.setFocusPainted(true);
  }

  void RegistraButton_actionPerformed(ActionEvent e)
  {    exit = false;
       if (IORRadioButton.isSelected())
       {    String FilePath = iorPath.getText();
            if ( iorPath.getText().compareTo("") == 0)
            {   showMessageDialog("Selezionare il file contenente lo IOR");
            }
            else
            {   RegistraFile(FilePath);
            }
       }
       else     // uso Naming Service
       {    RegistraNameServer();
       }
       processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
       mw = new ServerMonitorWindow("Server status");
       mw.setVisible(true);
       ServerData.startup(rootPOA);
       System.out.println("System waiting for incoming request ...");


  }

  protected void RegistraFile(String FilePath)
  {   try
      {   FileWriter output = new FileWriter(FilePath);
          output.write(orb.object_to_string(rif));
          output.close();
      }   catch (Exception e) {   e.printStackTrace();  };

  }

  protected void RegistraNameServer()
  {   try
      {   org.omg.CORBA.Object rootObj = orb.resolve_initial_references("NameService");
          NamingContext root = NamingContextHelper.narrow(rootObj);
          System.out.println("Localizzato correttamente Naming service");
          NameComponent nc = new NameComponent(EntryPointName, "");
          ((NamingContext)root).rebind(new NameComponent[] { nc }, rif);
          System.out.println("Creato binding per oggetto "+EntryPointName+" nel name server");
      }
      catch (Exception e) {  e.printStackTrace();
                          }
  }

  protected void initServer()
  {  try
      {   ChainUntypedObjectWrapperFactory Sfactory = ChainUntypedObjectWrapperFactoryHelper.narrow(orb.resolve_initial_references("ChainUntypedObjectWrapperFactory"));
          Sfactory.add(new UtilityObjectWrappers.TracingUntypedObjectWrapperFactory(), Location.SERVER);
          Sfactory.add(new UtilityObjectWrappers.TimingUntypedObjectWrapperFactory(), Location.SERVER);
          // get a reference to the root POA
          rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
          // Create a BindSupport Policy that makes POA register each servant with osagent
          org.omg.CORBA.Any any = orb.create_any();
          BindSupportPolicyValueHelper.insert(any, BindSupportPolicyValue.BY_INSTANCE);
          org.omg.CORBA.Policy bsPolicy = orb.create_policy(BIND_SUPPORT_POLICY_TYPE.value, any);
          // Create policies for our testPOA
          org.omg.CORBA.Policy[] policies = { rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT), bsPolicy  };
          // Create myPOA with the right policies
          POA myPOA = rootPOA.create_POA("serverPOA", rootPOA.the_POAManager(), policies );

          System.out.println("POA localizzato correttamente");
          EmsSessionFactory servant = new EmsSessionFactory();
          byte[] managerId = EntryPointName.getBytes();
          myPOA.activate_object_with_id(managerId, servant);
          rootPOA.the_POAManager().activate();
          rif = myPOA.servant_to_reference(servant);
      }   catch (Exception e)
          {   e.printStackTrace();
          }
  }

  protected void showMessageDialog(String msg)
  {   JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.INFORMATION_MESSAGE);
  }

  protected void processWindowEvent(WindowEvent e)
  {   if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {   if (exit) System.exit(0);
      else this.dispose();
      }
  }

  void NameServiceRadioButton_actionPerformed(ActionEvent e)
  {   iorPath.setEnabled(false);
      jButton1.setEnabled(false);
      RegistraButton.setFocusPainted(true);
  }

  void this_keyPressed(KeyEvent e)
  {
      if (e.getKeyCode() == KeyEvent.VK_ENTER)
      {   Component componentHasFocus = this.getFocusOwner();
          String componentName = componentHasFocus.getName();
          if (componentName.compareTo("Registra") == 0)
          {   RegistraButton_actionPerformed(new ActionEvent(this.RegistraButton, ActionEvent.ACTION_PERFORMED, this.RegistraButton.getName()));
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

  public static ServerMonitorWindow getMonitorWindow()
  {   return mw;
  }
}


