package testsrv;



import db.Entities;
import java.io.File;
import javax.swing.JFileChooser;
import org.apache.log4j.Logger;
import db.SrvConfig;
import db.HibernateUtil;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.lf5.LF5Appender;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pasha
 */
@SuppressWarnings("serial")
public class TestSrv extends javax.swing.JFrame {
    
    

    /**
     * Creates new form TestSrv
     */
    public TestSrv() {
        this.logTable = new LogTable(textArea);
        logList=new ArrayList<LogLevel>();
        logList.add(LogLevel.WARN);
        logList.add(LogLevel.INFO);
        this.lMonitor=new LogBrokerMonitor(logList);//TODO пустой список уровней!
        initComponents();
        bStopper = true;
        bConfig=false;
        entList=new ArrayDeque();
        tasks=new LinkedList();
        //servConfig=(SrvConfig) sFactory.openSession().load(SrvConfig.class, 1L);
        TestSrv.servConfig=setConfigUI(null);
        TestSrv.sFiles=SourcePath.getInstance(servConfig.getSource());
        
        
        
    }
    static Logger logger = Logger.getLogger(TestSrv.class);
    private static List<LogLevel>logList;
    private static SrvConfig servConfig;
    private static final Logger log = Logger.getLogger(TestSrv.class);
    private static boolean bStopper, bConfig;
    private static final SAXParserFactory factory = SAXParserFactory.newInstance();
    ForkJoinPool fjp=new ForkJoinPool();
    private static ArrayDeque<Entities> entList;
    private static List<ForkJoinTask<ConcurrentLinkedDeque<Entities>>>  tasks;
    private static SessionFactory sFactory;
    LogBrokerMonitor lMonitor;
    private static SourcePath sFiles; 
    protected final  LogTable logTable;
    WriterAppender wAppender;
    LF5Appender  lf5Appender;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        dialogDB = new javax.swing.JDialog();
        workLabel = new javax.swing.JLabel();
        destLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        workTextDial = new javax.swing.JTextField();
        destTextDial = new javax.swing.JTextField();
        wrongTextDial = new javax.swing.JTextField();
        try {
            comboDial =(javax.swing.JComboBox)java.beans.Beans.instantiate(getClass().getClassLoader(), "testsrv.TestSrv_comboDial");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        workBtnDial = new javax.swing.JButton();
        destBtnDial = new javax.swing.JButton();
        wrongBtnDial = new javax.swing.JButton();
        saveBtnDial = new javax.swing.JButton();
        messagePane = new javax.swing.JOptionPane();
        intFrame = new javax.swing.JInternalFrame();
        workDirLab = new javax.swing.JLabel();
        workDirText = new javax.swing.JTextField();
        wDirBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        destDirText = new javax.swing.JTextField();
        destDirBtn = new javax.swing.JButton();
        delayLbl = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<Integer>();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        wrongDirText = new javax.swing.JTextField();
        wrongDirBtn = new javax.swing.JButton();
        storeConfigBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        servControl = new javax.swing.JMenu();
        startServer = new javax.swing.JMenuItem();
        stopServer = new javax.swing.JMenuItem();
        config = new javax.swing.JMenu();
        workDirMenu = new javax.swing.JMenuItem();
        destDirMenu = new javax.swing.JMenuItem();
        wrongDirMenu = new javax.swing.JMenuItem();
        delaySecMenu = new javax.swing.JMenuItem();

        fileChooser.setApproveButtonText("Выбрать");
        fileChooser.setApproveButtonToolTipText("Выберите директорию");
        fileChooser.setDialogTitle("Выбор директории");
        fileChooser.setFileHidingEnabled(false);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        dialogDB.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                dialogDBComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dialogDBComponentShown(evt);
            }
        });

        workLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        workLabel.setText("Рабочая директория");

        destLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        destLabel.setText("Целевая директория");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Директория сброшеных");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Интервал обработки, сек");

        workTextDial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        destTextDial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        workBtnDial.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        workBtnDial.setText("Изменить");
        workBtnDial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workBtnDialActionPerformed(evt);
            }
        });

        destBtnDial.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        destBtnDial.setText("Изменить");
        destBtnDial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destBtnDialActionPerformed(evt);
            }
        });

        wrongBtnDial.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        wrongBtnDial.setText("Изменить");

        saveBtnDial.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        saveBtnDial.setForeground(java.awt.Color.red);
        saveBtnDial.setText("Записать конфигурацию");
        saveBtnDial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnDialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogDBLayout = new javax.swing.GroupLayout(dialogDB.getContentPane());
        dialogDB.getContentPane().setLayout(dialogDBLayout);
        dialogDBLayout.setHorizontalGroup(
            dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogDBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workLabel)
                    .addComponent(destLabel)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogDBLayout.createSequentialGroup()
                        .addComponent(comboDial, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveBtnDial))
                    .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(wrongTextDial, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(destTextDial)
                        .addComponent(workTextDial)))
                .addGap(18, 18, 18)
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workBtnDial)
                    .addComponent(destBtnDial)
                    .addComponent(wrongBtnDial))
                .addGap(38, 38, 38))
        );
        dialogDBLayout.setVerticalGroup(
            dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogDBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workLabel)
                    .addComponent(workTextDial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workBtnDial))
                .addGap(40, 40, 40)
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destLabel)
                    .addComponent(destTextDial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destBtnDial))
                .addGap(46, 46, 46)
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(wrongTextDial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongBtnDial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(dialogDBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboDial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtnDial))
                .addGap(44, 44, 44))
        );

        intFrame.setVisible(true);
        intFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                intFrameInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        javax.swing.GroupLayout intFrameLayout = new javax.swing.GroupLayout(intFrame.getContentPane());
        intFrame.getContentPane().setLayout(intFrameLayout);
        intFrameLayout.setHorizontalGroup(
            intFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        intFrameLayout.setVerticalGroup(
            intFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        workDirLab.setText("Рабочая директория");

        workDirText.setEditable(false);
        workDirText.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        workDirText.setForeground(javax.swing.UIManager.getDefaults().getColor("ToolBar.dockingForeground"));
        workDirText.setText("задать...");
        workDirText.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                workDirTextComponentShown(evt);
            }
        });

        wDirBtn.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        wDirBtn.setText("Изменить");
        wDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wDirBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Целевая директория");

        destDirText.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        destDirText.setForeground(javax.swing.UIManager.getDefaults().getColor("ToolBar.dockingForeground"));
        destDirText.setText("задать...");

        destDirBtn.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        destDirBtn.setText("Изменить");
        destDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destDirBtnActionPerformed(evt);
            }
        });

        delayLbl.setText("Период обработки, с");

        comboBox.setEditable(true);
        comboBox.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "100", "200", "500", "1000" }));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Директория пропущеных");

        wrongDirText.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        wrongDirText.setText("задать...");

        wrongDirBtn.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        wrongDirBtn.setText("Изменить");
        wrongDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrongDirBtnActionPerformed(evt);
            }
        });

        storeConfigBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        storeConfigBtn.setText("Записать конфигурацию");
        storeConfigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeConfigBtnActionPerformed(evt);
            }
        });

        servControl.setText("Сервер");

        startServer.setText("Старт");
        startServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerActionPerformed(evt);
            }
        });
        servControl.add(startServer);

        stopServer.setText("Останов");
        stopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServerActionPerformed(evt);
            }
        });
        servControl.add(stopServer);

        jMenuBar1.add(servControl);

        config.setText("Конфигурация");

        workDirMenu.setText("Рабочая директория");
        workDirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workDirMenuActionPerformed(evt);
            }
        });
        config.add(workDirMenu);

        destDirMenu.setText("Целевая директория");
        destDirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destDirMenuActionPerformed(evt);
            }
        });
        config.add(destDirMenu);

        wrongDirMenu.setText("Директория отброшеных");
        wrongDirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrongDirMenuActionPerformed(evt);
            }
        });
        config.add(wrongDirMenu);

        delaySecMenu.setText("Период обработки, с");
        delaySecMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delaySecMenuActionPerformed(evt);
            }
        });
        config.add(delaySecMenu);

        jMenuBar1.add(config);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workDirLab, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(delayLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(storeConfigBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(wrongDirText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(workDirText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                    .addComponent(destDirText, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wDirBtn)
                                    .addComponent(destDirBtn)
                                    .addComponent(wrongDirBtn)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workDirLab)
                    .addComponent(workDirText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wDirBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(destDirText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destDirBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(wrongDirText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongDirBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delayLbl)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeConfigBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void workDirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workDirMenuActionPerformed
      Session sess=sFactory.openSession();
       String result=(String)sess.createSQLQuery("SELECT source FROM srvconfig WHERE id=1").list().get(0);
       workDirText.setText(result);
       workDirText.setBackground(Color.white);
       sess.close();
    }//GEN-LAST:event_workDirMenuActionPerformed

    private void destDirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destDirMenuActionPerformed
       Session sess=sFactory.openSession();
       String result=(String)sess.createSQLQuery("SELECT destination FROM srvconfig WHERE id=1").list().get(0);
       sess.close();
       destDirText.setText(result);
       destDirText.setBackground(Color.white);
       logger.info("Директория для обработаных файлов " + result);
    }//GEN-LAST:event_destDirMenuActionPerformed

    private void delaySecMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delaySecMenuActionPerformed
        Session sess=sFactory.openSession();
        Integer result = (Integer) sess.createSQLQuery("SELECT delay FROM srvconfig WHERE id=1").list().get(0);
        comboBox.addItem(result);
        comboBox.setSelectedItem(result);
        comboBox.setBackground(Color.white);
        logger.info("Временная задержка для обработки файлов " + result);
        sess.close();
    }//GEN-LAST:event_delaySecMenuActionPerformed

    private void stopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopServerActionPerformed
        bStopper=true;
        logger.info("Останов сервера...");
    }//GEN-LAST:event_stopServerActionPerformed

    private void startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerActionPerformed
        bStopper=false;
        logger.info("Старт сервера...");
    }//GEN-LAST:event_startServerActionPerformed

    private void wDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wDirBtnActionPerformed
      int returnVal = fileChooser.showOpenDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        //servConfig.setSource();
        workDirText.setText(file.getAbsolutePath());
        workDirText.setBackground(Color.lightGray);
       } else {
        logger.info("File access cancelled by user.");
       }
    }//GEN-LAST:event_wDirBtnActionPerformed

    private void destDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destDirBtnActionPerformed
       int returnVal = fileChooser.showOpenDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        //servConfig.setDestination(file.getAbsolutePath());
        destDirText.setText(file.getAbsolutePath());
        destDirText.setBackground(Color.lightGray);
       } else {
        logger.info("File access cancelled by user.");
       }
    }//GEN-LAST:event_destDirBtnActionPerformed

    private void wrongDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrongDirBtnActionPerformed
         int returnVal = fileChooser.showOpenDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        //servConfig.setWrong(file.getAbsolutePath());
        wrongDirText.setText(file.getAbsolutePath());
        wrongDirText.setBackground(Color.lightGray);
        // log("Определена директория необработанных файлов: " + servConfig.getWrong() + "\n");
       } else {
        logger.info("File access cancelled by user.");
       }
    }//GEN-LAST:event_wrongDirBtnActionPerformed

    private void storeConfigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeConfigBtnActionPerformed
        SrvConfig srvConfig=new SrvConfig();
     
        int i=Integer.parseInt( comboBox.getSelectedItem().toString());
        srvConfig.setParameters(1L,workDirText.getText() , destDirText.getText(),i, wrongDirText.getText() );
        logger.info("Сохранение: " + srvConfig.getSource());
        setConfigUI(srvConfig);
        
       
    }//GEN-LAST:event_storeConfigBtnActionPerformed

    private void wrongDirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrongDirMenuActionPerformed
       Session sess=sFactory.openSession();
       String result=(String)sess.createSQLQuery("SELECT wrong FROM srvconfig WHERE id=1").list().get(0);
       wrongDirText.setText(result);
       wrongDirText.setBackground(Color.white);
       sess.close();
    }//GEN-LAST:event_wrongDirMenuActionPerformed

    private void intFrameInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_intFrameInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_intFrameInternalFrameActivated

    private void workBtnDialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workBtnDialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workBtnDialActionPerformed

    private void destBtnDialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destBtnDialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destBtnDialActionPerformed

    private void saveBtnDialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnDialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBtnDialActionPerformed

    private void dialogDBComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dialogDBComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_dialogDBComponentShown

    private void dialogDBComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dialogDBComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_dialogDBComponentHidden

    private void workDirTextComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_workDirTextComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_workDirTextComponentShown
    protected final  SrvConfig setConfigUI(SrvConfig srvConfig){
     
              Session sess=sFactory.openSession();
              Transaction tr=sess.beginTransaction();
              //sess.beginTransaction();
              if (srvConfig !=null && !srvConfig.notSet()) {
                  sess.merge(srvConfig);
                  //sess.saveOrUpdate(srvConfig);
                  logger.info("Сохранение");
                  
              }
              List lres=sess.createSQLQuery("SELECT * FROM srvconfig WHERE id=1").addEntity(SrvConfig.class).list();
              if(lres.size()>0){
                  servConfig=(SrvConfig)lres.get(0);
                  logger.info("Рабочая директория: "+ servConfig.getSource());
              } else {
                  servConfig=null;
                  logger.info ("Задайте конфигурацию");
              }
              tr.commit();
              //sess.close();
              
              
              if (servConfig != null) { 
                  bConfig=false;
                  workDirText.setText(servConfig.getSource());
                  destDirText.setText(servConfig.getDestination());
                  comboBox.setSelectedItem(servConfig.getDelay());
                  wrongDirText.setText(servConfig.getWrong());
                  workDirText.setBackground(Color.white);
                  destDirText.setBackground(Color.white);
                  comboBox.setBackground(Color.white);
                  wrongDirText.setBackground(Color.white);
                  bStopper=false;
                  logger.info("Конфигурация загружена");
              }
                      else {
                  
                  workDirText.setBackground(Color.red);
                  destDirText.setBackground(Color.red);
                  comboBox.setBackground(Color.red);
                  wrongDirText.setBackground(Color.red);
                  bConfig=true;
                  bStopper=true;
              };
              return servConfig;
               
        
    }
           
   
    private static class SourcePath extends ArrayDeque<Path>{
        private String source;
        private static Path dir;
        static CopyOnWriteArrayList<Path> myDec;
        private static SourcePath instance;
        private SourcePath(String source) {
            this.source=source;
            dir=Paths.get(source);
            
        }
        public static CopyOnWriteArrayList<Path> getPath() throws IOException{
            DirectoryStream<Path> stream=Files.newDirectoryStream(dir, "*.xml");
            myDec=new CopyOnWriteArrayList(); 
            for (Path entry: stream) {
              myDec.add(entry);
           }
           return  myDec;
            
        }
        public void newConfig(String source) throws IOException{
            myDec.clear();
            this.source=source;
            SourcePath.dir=Paths.get(this.source);
            
        }
        public static synchronized SourcePath getInstance(String source) {
      if (instance==null) {
          instance = new SourcePath(source);
      }
     return instance;
   }
    }
    
    private static void initLogger(){
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException  {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            log.error("Не нахожу класс", ex);
            
        } catch (InstantiationException ex) {
            log.error("Ошибка загрузки", ex);
            
        } catch (IllegalAccessException ex) {
            log.error("Ошибка доступа", ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            log.error("Не поддерживается", ex);
            
        }
        //</editor-fold>
   try{ 
       sFactory=HibernateUtil.getSessionFactory();
   
   } catch (java.lang.ExceptionInInitializerError e){
       logger.error("Проверьте настройку и соединение с БД!");
       
   }
        ForkJoinPool pool= new ForkJoinPool(19);
        Collection<ForkJoinTask<List<Entities>>> lft=new LinkedList<>();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestSrv().setVisible(true);
            }
        });
       

        
            
      while (true){
          if(bStopper || servConfig==null || null==sFiles ){
              
              
              
              
                   if(pool.isTerminated()) {
                       logger.info("Сервер остановлен.");
                   } else {
                   }
             
          } else {
              
              
                  //List<Path> lp1=listSourceFiles(servConfig.getSource(), lp2);
                  
                 //ArrayDeque<Path> sourcePath=sFiles.getPath();
                 EntTask eT=new EntTask(sFiles.getPath()  , factory, servConfig);
                 //logger.info("Входящий список"+ Integer.toString(sourcePath.size()));
                 tasks.add(pool.submit(eT));
                 for(ForkJoinTask<ConcurrentLinkedDeque<Entities>> fjT:tasks){
                     if(fjT.isDone()) {
                         entList.addAll(fjT.join());
                         tasks.remove(fjT);
                     }
                 }
                 
                  logger.info("Список результатов"+ Integer.toString(entList.size()));
                
                  
           try {       if(entList.size()> 0){
                            StoreAction sA=new StoreAction(sFactory,entList);
                            entList.clear();
                            logger.info("Сохранение");
                            pool.execute(sA);
                            }
                  TimeUnit.SECONDS.sleep(servConfig.getDelay().longValue());
                       
                  
              } catch (NullPointerException e) {
                  logger.error (MessageFormat.format("NullPointerException {0}", e.getCause()));
              
              } catch (InterruptedException ex) {
                  logger.error("Цикл Маин Ошибка прерывания", ex);
              }
          }
                
      }
      
        
     
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Integer> comboBox;
    private javax.swing.JComboBox<Integer> comboDial;
    private javax.swing.JMenu config;
    private javax.swing.JLabel delayLbl;
    private javax.swing.JMenuItem delaySecMenu;
    private javax.swing.JButton destBtnDial;
    private javax.swing.JButton destDirBtn;
    private javax.swing.JMenuItem destDirMenu;
    private javax.swing.JTextField destDirText;
    private javax.swing.JLabel destLabel;
    private javax.swing.JTextField destTextDial;
    private javax.swing.JDialog dialogDB;
    private javax.swing.JFileChooser fileChooser;
    public javax.swing.JInternalFrame intFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JOptionPane messagePane;
    private javax.swing.JButton saveBtnDial;
    private javax.swing.JMenu servControl;
    private javax.swing.JMenuItem startServer;
    private javax.swing.JMenuItem stopServer;
    private javax.swing.JButton storeConfigBtn;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton wDirBtn;
    private javax.swing.JButton workBtnDial;
    private javax.swing.JLabel workDirLab;
    private javax.swing.JMenuItem workDirMenu;
    private javax.swing.JTextField workDirText;
    private javax.swing.JLabel workLabel;
    private javax.swing.JTextField workTextDial;
    private javax.swing.JButton wrongBtnDial;
    private javax.swing.JButton wrongDirBtn;
    private javax.swing.JMenuItem wrongDirMenu;
    private javax.swing.JTextField wrongDirText;
    private javax.swing.JTextField wrongTextDial;
    // End of variables declaration//GEN-END:variables
}
