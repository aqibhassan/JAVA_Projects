
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class LoginForm extends javax.swing.JFrame {

    
    public LoginForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("chat App");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        txtUsername.setBackground(new java.awt.Color(49, 141, 202));
        txtUsername.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(153, 255, 255));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername);
        txtUsername.setBounds(860, 100, 250, 40);

        txtHost.setBackground(new java.awt.Color(49, 141, 202));
        txtHost.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtHost.setForeground(new java.awt.Color(153, 255, 255));
        txtHost.setText("127.0.0.1");
        txtHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHostActionPerformed(evt);
            }
        });
        getContentPane().add(txtHost);
        txtHost.setBounds(860, 160, 260, 40);

        txtPort.setBackground(new java.awt.Color(49, 141, 202));
        txtPort.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtPort.setForeground(new java.awt.Color(153, 255, 255));
        txtPort.setText("4444");
        getContentPane().add(txtPort);
        txtPort.setBounds(860, 220, 170, 70);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login-button.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(280, 60));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(850, 310, 240, 50);

        jLabel6.setBackground(new java.awt.Color(0, 204, 255));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 255, 255));
        jLabel6.setText("Port:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(800, 230, 60, 50);

        jLabel9.setBackground(new java.awt.Color(0, 204, 255));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 255, 255));
        jLabel9.setText("Account :");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(740, 100, 110, 40);

        jLabel7.setBackground(new java.awt.Color(0, 204, 255));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 255, 255));
        jLabel7.setText("IP address:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(730, 160, 130, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chat-app-icon-24.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 10, 540, 510);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FrailOfficialDegus-small-1542659176215.gif"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 1290, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
        connectToServer();
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         connectToServer();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHostActionPerformed
    
    private void connectToServer(){
        if(txtHost.getText().length() > 0 && txtPort.getText().length() > 0 && txtUsername.getText().length() > 0){
            if(txtUsername.getText().length() <= 15){
        
                String username = txtUsername.getText();
                String u = username.replace(" ", "_");
       
                MainForm main = new MainForm();
                main.initFrame(u, txtHost.getText(), Integer.parseInt(txtPort.getText()));
         
                if(main.isConnected()){
                    main.setLocationRelativeTo(null);
                    main.setVisible(true);
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account must be up to 15 characters including [space].! "," Error ", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incomplete Form.! "," Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
