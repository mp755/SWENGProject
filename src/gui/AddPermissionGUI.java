/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import client.Permission;
import client.ServerAdapter;
import client.User;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mp755
 */
public class AddPermissionGUI extends javax.swing.JFrame {
    private String[] friendsNames;
    private List<User> friendsList;
    private User user;
    private int fileID;
    private ServerAdapter serverAdapt;
    /**
     * Creates new form AddPermissionGUI
     */
    public AddPermissionGUI() {
        serverAdapt = ServerAdapter.getInstance();
        initComponents();
        refreshFriends();
        refreshFriendsList();
    }
    
    public void refreshFriends() {
        user = new User("mp755", "test123");
        friendsList = serverAdapt.getFriends(user);
        friendsNames = new String[friendsList.size()];
        
        for(int i = 0; i < friendsList.size(); i++) {
            friendsNames[i] = friendsList.get(i).getUserName();
        }
    }
    
    public void refreshFriendsList() {
        friendsModel.setModel(new javax.swing.AbstractListModel() {
            String[] strings = friendsNames;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(friendsModel);
    }
    
    public void addUser(User u) {
        this.user = u;
    }
    
    public void addFileID(int fileID) {
        this.fileID = fileID;
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
        noPermissionsButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendsModel = new javax.swing.JList();
        addPermissionsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Add Permissions");

        noPermissionsButton.setText("I would not like to add permissions at this time.");
        noPermissionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noPermissionsButtonMouseClicked(evt);
            }
        });
        noPermissionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noPermissionsButtonActionPerformed(evt);
            }
        });

        friendsModel.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(friendsModel);

        addPermissionsButton.setText("Add Access");
        addPermissionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPermissionsButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addPermissionsButton))
                            .addComponent(noPermissionsButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noPermissionsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addPermissionsButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void noPermissionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPermissionsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noPermissionsButtonActionPerformed

    private void addPermissionsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPermissionsButtonMouseClicked
        List<String> friendUserNames = friendsModel.getSelectedValuesList();
        
        for(String friendName : friendUserNames) {
            Permission p = new Permission(friendName, fileID, 1);
            
            if(serverAdapt.addPermission(p)) {
                JOptionPane.showMessageDialog(this, "Permission successfully added to the file.");
            } else {
                JOptionPane.showMessageDialog(this, "Permission has not been added to the file. Please try again.");
            }
        }
        
        this.dispose();
    }//GEN-LAST:event_addPermissionsButtonMouseClicked

    private void noPermissionsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noPermissionsButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_noPermissionsButtonMouseClicked

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
            java.util.logging.Logger.getLogger(AddPermissionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPermissionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPermissionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPermissionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPermissionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPermissionsButton;
    private javax.swing.JList friendsModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton noPermissionsButton;
    // End of variables declaration//GEN-END:variables
}