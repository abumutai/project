/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.frames.admin;

import classes.frames.admin.AdminGuide;
import classes.frames.admin.Home;
import classes.frames.admin.Files;
import classes.frames.admin.Issues;
import classes.frames.admin.Notifications;
import classes.frames.admin.Settings;
import classes.frames.user.myConnection;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MUTAI
 */
public class AdminPanel extends javax.swing.JFrame {
GridBagLayout grid= new GridBagLayout();
Home home;
Files files;
AdminGuide admin;
Settings set;
Users users;
    /**
     * Creates new form TestAdmin
     */
    public AdminPanel() {
       
        initComponents();
        String username= jTextField1.getText();
       
        home= new Home();
        files= new Files();
        admin= new AdminGuide();
        set= new Settings();
        users= new Users();
       
        
        
        
        
        
        DynamicPanel.setLayout(grid);
        GridBagConstraints c= new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        DynamicPanel.add(home,c);
        c.gridx=0;
        c.gridy=0;
        DynamicPanel.add(files,c);
        c.gridx=0;
        c.gridy=0;
        DynamicPanel.add(admin,c);
        c.gridx=0;
        c.gridy=0;
        DynamicPanel.add(set,c);
        c.gridx=0;
        c.gridy=0;
        DynamicPanel.add(users,c);
         c.gridx=0;
        c.gridy=0;
     
        
        home.setVisible(true);
         Display();
        files.setVisible(false);
        admin.setVisible(false);
        set.setVisible(false);
        users.setVisible(false);
         
        btnHome.setBackground(Color.white);
        btnHome.setForeground(Color.blue);
        
        
    }
   
void username(String username){
   set.settings(username);
    jTextField1.setText(username);
      
    
}

public void Display(){
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Timestamp Bdate = new java.sql.Timestamp(new java.util.Date().getTime());
        
         String date= format.format(Bdate);
        
           
          PreparedStatement ps;
          ResultSet rs;
          String newUsers=null;
          String encrypted= null;
          String sent= null;
          String decrypted= null;
          String notifications=null;
          String issues=null;
       String query= "SELECT COUNT(ID) FROM users WHERE dateofreg=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 newUsers= rs.getString("count(ID)");
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT COUNT(img_id) FROM encrypted WHERE enc_date=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 encrypted= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT COUNT(img_id) FROM sent WHERE sent_date=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 sent= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT COUNT(img_id) FROM decrypted WHERE dec_date=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 decrypted= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "SELECT COUNT(ID) FROM notifications WHERE date=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 notifications= rs.getString("count(ID)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT COUNT(ID) FROM issues WHERE date=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,date);
            rs=ps.executeQuery();
            if(rs.next()){
                 issues= rs.getString("count(ID)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          home.Content(newUsers,encrypted,sent,decrypted,notifications,issues);
          
          
           encrypted= null;
           sent= null;
           decrypted= null;
          String notSent=null;
          String success= "sent";
          String failed= "fail";
         query = "SELECT COUNT(img_id) FROM encrypted" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next()){
                 encrypted= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         query = "SELECT COUNT(img_id) FROM decrypted" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next()){
                 decrypted= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        query = "SELECT COUNT(img_id) FROM sent WHERE status=?" ; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,success);
            rs=ps.executeQuery();
            if(rs.next()){
                 sent= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        query = "SELECT COUNT(img_id) FROM sent WHERE status=?"; 
        try {
            ps= myConnection.getConnection().prepareStatement(query);
            ps.setString(1,failed);
            rs=ps.executeQuery();
            if(rs.next()){
                 notSent= rs.getString("count(img_id)");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
          files.Content(encrypted,decrypted,sent,notSent);
          
      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PanelButton = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnFiles = new javax.swing.JButton();
        btnAdminGuide = new javax.swing.JButton();
        DynamicPanel = new javax.swing.JPanel();
        PanelButton1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classes/images/encyptor.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));

        PanelButton.setBackground(new java.awt.Color(0, 0, 255));

        btnHome.setBackground(new java.awt.Color(0, 0, 255));
        btnHome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnUsers.setBackground(new java.awt.Color(0, 0, 255));
        btnUsers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers.setText("Users");
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });

        btnSettings.setBackground(new java.awt.Color(0, 0, 255));
        btnSettings.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnSettings.setText("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        btnFiles.setBackground(new java.awt.Color(0, 0, 255));
        btnFiles.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFiles.setForeground(new java.awt.Color(255, 255, 255));
        btnFiles.setText("Files");
        btnFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilesActionPerformed(evt);
            }
        });

        btnAdminGuide.setBackground(new java.awt.Color(0, 0, 255));
        btnAdminGuide.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdminGuide.setForeground(new java.awt.Color(255, 255, 255));
        btnAdminGuide.setText("Admin Guide");
        btnAdminGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminGuideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminGuide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdminGuide, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        DynamicPanel.setBackground(new java.awt.Color(102, 255, 204));

        javax.swing.GroupLayout DynamicPanelLayout = new javax.swing.GroupLayout(DynamicPanel);
        DynamicPanel.setLayout(DynamicPanelLayout);
        DynamicPanelLayout.setHorizontalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        DynamicPanelLayout.setVerticalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        PanelButton1.setBackground(new java.awt.Color(0, 0, 255));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true)));
        jPanel7.setForeground(new java.awt.Color(0, 0, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Welcome:");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Abu");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jButton2.setBackground(new java.awt.Color(0, 0, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classes/images/logout.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classes/images/encyptor.png"))); // NOI18N

        javax.swing.GroupLayout PanelButton1Layout = new javax.swing.GroupLayout(PanelButton1);
        PanelButton1.setLayout(PanelButton1Layout);
        PanelButton1Layout.setHorizontalGroup(
            PanelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButton1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelButton1Layout.setVerticalGroup(
            PanelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButton1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(PanelButton1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DynamicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DynamicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        home.setVisible(true);
        
        files.setVisible(false);
        admin.setVisible(false);
        set.setVisible(false);
        users.setVisible(false);
        btnHome.setBackground(Color.white);
        btnHome.setForeground(Color.blue);
         btnUsers.setBackground(Color.blue);
        btnUsers.setForeground(Color.white);
         btnSettings.setBackground(Color.blue);
        btnSettings.setForeground(Color.white);
         btnFiles.setBackground(Color.blue);
        btnFiles.setForeground(Color.white);
         btnAdminGuide.setBackground(Color.blue);
        btnAdminGuide.setForeground(Color.white);
       
       
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        home.setVisible(false);
        files.setVisible(false);
        admin.setVisible(false);
        set.setVisible(false);
        users.setVisible(true);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnUsers.setBackground(Color.white);
        btnUsers.setForeground(Color.blue);
         btnSettings.setBackground(Color.blue);
        btnSettings.setForeground(Color.white);
         btnFiles.setBackground(Color.blue);
        btnFiles.setForeground(Color.white);
         btnAdminGuide.setBackground(Color.blue);
        btnAdminGuide.setForeground(Color.white);
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        home.setVisible(false);
        files.setVisible(false);
        admin.setVisible(false);
        set.setVisible(true);
        
        users.setVisible(false);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnUsers.setBackground(Color.blue);
        btnUsers.setForeground(Color.white);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnSettings.setBackground(Color.white);
        btnSettings.setForeground(Color.blue);
         btnFiles.setBackground(Color.blue);
        btnFiles.setForeground(Color.white);
         btnAdminGuide.setBackground(Color.blue);
        btnAdminGuide.setForeground(Color.white);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilesActionPerformed
     home.setVisible(false);
        files.setVisible(true);
        admin.setVisible(false);
        set.setVisible(false);
        users.setVisible(false);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnUsers.setBackground(Color.blue);
        btnUsers.setForeground(Color.white);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnSettings.setBackground(Color.blue);
        btnSettings.setForeground(Color.white);
        btnFiles.setBackground(Color.white);
        btnFiles.setForeground(Color.blue);
         btnAdminGuide.setBackground(Color.blue);
        btnAdminGuide.setForeground(Color.white);
    }//GEN-LAST:event_btnFilesActionPerformed

    private void btnAdminGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminGuideActionPerformed
        home.setVisible(false);
        files.setVisible(false);
        admin.setVisible(true);
        set.setVisible(false);
        users.setVisible(false);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnUsers.setBackground(Color.blue);
        btnUsers.setForeground(Color.white);
        btnHome.setBackground(Color.blue);
        btnHome.setForeground(Color.white);
        btnSettings.setBackground(Color.blue);
        btnSettings.setForeground(Color.white);
        btnFiles.setBackground(Color.blue);
        btnFiles.setForeground(Color.white);
         btnAdminGuide.setBackground(Color.white);
        btnAdminGuide.setForeground(Color.blue);
    }//GEN-LAST:event_btnAdminGuideActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       AdminLogin adminl= new AdminLogin();
       adminl.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DynamicPanel;
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelButton1;
    private javax.swing.JButton btnAdminGuide;
    private javax.swing.JButton btnFiles;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnUsers;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
