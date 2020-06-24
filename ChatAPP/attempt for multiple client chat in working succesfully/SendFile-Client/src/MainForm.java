
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class ClientsDisplay
{
     javax.swing.JButton jButton;
   String to,from;
   
     javax.swing.JInternalFrame jInternalFrame;
       javax.swing.JTextPane jTextPane;
        javax.swing.JTextField jTextField;
     javax.swing.JScrollPane jScrollPane;
     MainForm MF;
    ClientsDisplay(String F,String T,MainForm MF)
    {
        this.from=F;
        this.MF=MF;
        this.to=T;
         jInternalFrame = new javax.swing.JInternalFrame();
        jScrollPane = new javax.swing.JScrollPane();
        jTextPane = new javax.swing.JTextPane();
        jButton = new javax.swing.JButton();
         jTextField = new javax.swing.JTextField();
         jInternalFrame.setVisible(false);
        jInternalFrame.getContentPane().setLayout(null);

        jTextPane.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jScrollPane.setViewportView(jTextPane);
        
        jInternalFrame.getContentPane().add(jScrollPane);
        jScrollPane.setBounds(20, 10, 860, 400);

        jButton.setBackground(new java.awt.Color(255, 255, 255));
        jButton.setForeground(new java.awt.Color(255, 255, 255));
        jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Send_Button1.png"))); // NOI18N
        jButton.setEnabled(true);
         jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });
        jInternalFrame.getContentPane().add(jButton);
        jButton.setBounds(760, 440, 90, 90);

         jTextField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionPerformed(evt);
            }
        });
        jInternalFrame.getContentPane().add(jTextField);
        jTextField.setBounds(10, 420, 876, 130);

       MF.getContentPane().add(jInternalFrame);
        jInternalFrame.setBounds(350, 0, 910, 600);

    }
    
    private void jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        System.out.println("text field working.");
        try {
            String content = from+" "+to+" "+ evt.getActionCommand();
           MF. dos.writeUTF("CMD_CHAT "+ content);
           
            this.appendMyMessage(" "+evt.getActionCommand(), from);
            jTextField.setText("");
        } catch (IOException e) {
           MF.appendMessage(" Unable to send messages now, cannot connect to Server at this time, please try again later or restart this application.! "," Error ", Color.RED, Color.RED);
        }
    }                                           

    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("buttton working good.");
        try {
            String content = from+" "+to+" "+ jTextField.getText();
           MF. dos.writeUTF("CMD_CHAT "+ content);
            this.appendMyMessage(" "+jTextField.getText(), from);
            jTextField.setText("");
        } catch (IOException e) {
            MF.appendMessage(" Unable to send messages now, cannot connect to Server at this time, please try again later or restart this application.! "," Error", Color.RED, Color.RED);
        }
    }
    public void appendMyMessage(String msg, String header){
        jTextPane.setEditable(true);
         getMsgHeader(header, Color.ORANGE);
        getMsgContent(msg, Color.LIGHT_GRAY);
       
        jTextPane.setEditable(false);
    }
    
   
    public void getMsgHeader(String header, Color color){
        int len = jTextPane.getDocument().getLength();
        jTextPane.setCaretPosition(len);
        jTextPane.setCharacterAttributes(MessageStyle.styleMessageContent(color, "Impact", 13), false);
        jTextPane.replaceSelection(header+":");
    }
    
    public void getMsgContent(String msg, Color color){
        int len = jTextPane.getDocument().getLength();
        jTextPane.setCaretPosition(len);
        jTextPane.setCharacterAttributes(MessageStyle.styleMessageContent(color, "Arial", 12), false);
        jTextPane.replaceSelection(msg +"\n\n");
    }
   
}
public class MainForm extends javax.swing.JFrame implements ActionListener{
    String username;
    String host;
    int port;
    Socket socket;
    DataOutputStream dos;
    public boolean attachmentOpen = false;
    private boolean isConnected = false;
    private String mydownloadfolder = "D:\\";
    Vector online;
 int OnlineClientCounts=0;
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }
        public void initFrame(String username, String host, int port){
        this.username = username;
        this.host = host;
        this.port = port;
        
        setTitle("You are being logged in with the name: " + username);
        
        connect();
    }
    
    public void connect(){
        appendMessage(" Connecting ... "," Status", Color.GREEN, Color.GREEN);
        try {
            socket = new Socket(host, port);
            dos = new DataOutputStream(socket.getOutputStream());
       
            dos.writeUTF("CMD_JOIN "+ username);
            appendMessage("Connected "," Status", Color.GREEN, Color.GREEN);
            appendMessage(" send message now.! "," Status", Color.GREEN, Color.GREEN);
            
           
            new Thread(new ClientThread(socket, this)).start();
            jButton1.setEnabled(true);
           
            isConnected = true;
            
        }
        catch(IOException e) {
            isConnected = false;
            JOptionPane.showMessageDialog(this, "Unable to connect to the server, please try again later.! "," Connection failed",JOptionPane.ERROR_MESSAGE);
            appendMessage("[IOException]: "+ e.getMessage(), "Error", Color.RED, Color.RED);
        }
    }
    
    
    public boolean isConnected(){
        return this.isConnected;
    }
    
     
    public void appendMessage(String msg, String header, Color headerColor, Color contentColor){
        jTextPane1.setEditable(true);
        getMsgHeader(header, headerColor);
        getMsgContent(msg, contentColor);
        jTextPane1.setEditable(false);
    }
    
 
    public void appendMyMessage(String msg, String header){
        jTextPane1.setEditable(true);
         getMsgHeader(header, Color.ORANGE);
        getMsgContent(msg, Color.LIGHT_GRAY);
       
        jTextPane1.setEditable(false);
    }
    
   
    public void getMsgHeader(String header, Color color){
        int len = jTextPane1.getDocument().getLength();
        jTextPane1.setCaretPosition(len);
        jTextPane1.setCharacterAttributes(MessageStyle.styleMessageContent(color, "Impact", 13), false);
        jTextPane1.replaceSelection(header+":");
    }
    
    public void getMsgContent(String msg, Color color){
        int len = jTextPane1.getDocument().getLength();
        jTextPane1.setCaretPosition(len);
        jTextPane1.setCharacterAttributes(MessageStyle.styleMessageContent(color, "Arial", 12), false);
        jTextPane1.replaceSelection(msg +"\n\n");
    }
    
    public void appendOnlineList(Vector list){
        this.online=list;
        sampleOnlineList(list); 
    }
    

    public void showOnLineList(Vector list){
        try {
            txtpane2.setEditable(true);
            txtpane2.setContentType("text/html");
            StringBuilder sb = new StringBuilder();
            Iterator it = list.iterator();
            sb.append("<html><table>");
            while(it.hasNext()){
                Object e = it.next();
                URL url = getImageFile();
                Icon icon = new ImageIcon(this.getClass().getResource("/images/online.png"));
                sb.append("<tr><td><b>></b></td><td>").append(e).append("</td></tr>");
                System.out.println("Online: "+ e);
            }
            sb.append("</table></body></html>");
            txtpane2.removeAll();
            txtpane2.setText(sb.toString());
            txtpane2.setEditable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    private void sampleOnlineList(Vector list){
        txtpane2.setEditable(true);
        txtpane2.removeAll();
        txtpane2.setText("");
        Iterator i = list.iterator();
        while(i.hasNext()){
            Object e = i.next();
            
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.LIGHT_GRAY);
            
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/online1.jpeg"));
////                  ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
            Image image = icon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
            icon = new ImageIcon(newimg);  
//            ImageIcon icon = new ImageIcon(new ImageIcon("/images/online1.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/online1.jpeg"));
             
//            JLabel label = new JLabel(icon);
            JButton JB=new JButton(icon);
            
//            label.addMouseListener(null);
//            label.setText(" "+ e);
            JB.setText(" "+ e);
            JB.setBounds(0,0, 150, 80);
//            panel.add(label);
//            TC.add(new ClientsDisplay(this.username," "+e,this));
           
            panel.add(JB);
            JB.addActionListener(this);
            
            int len = txtpane2.getDocument().getLength();
            txtpane2.setCaretPosition(len);
            txtpane2.insertComponent(panel);
            /*  Append Next Line   */
            sampleAppend();
        }
        txtpane2.setEditable(false);
    }
    private void sampleAppend(){
        int len = txtpane2.getDocument().getLength();
        txtpane2.setCaretPosition(len);
        txtpane2.replaceSelection("\n");
    }
    /*
      ************************************  Show Online Sample  *********************************************
    */
    
    
    
    
    /*
        Get image file path
    */
    public URL getImageFile(){
        URL url = this.getClass().getResource("/images/online.png");
        return url;
    }
    
    
    /*
        Set myTitle
    */
    public void setMyTitle(String s){
        setTitle(s);
    }
    
   
    public String getMyDownloadFolder(){
        return this.mydownloadfolder;
    }
    
    
    public String getMyHost(){
        return this.host;
    }
    
   
    public int getMyPort(){
        return this.port;
    }
    public Vector getonline()
    {
        return this.online;
    }
    
    public String getMyUsername(){
        return this.username;
    }
    
    
    public void updateAttachment(boolean b){
        this.attachmentOpen = b;
    }
    
    
    public void openFolder(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int open = chooser.showDialog(this, "open the folder");
        if(open == chooser.APPROVE_OPTION){
            mydownloadfolder = chooser.getSelectedFile().toString()+"\\";
        } else {
            mydownloadfolder = "D:\\";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        txtpane2 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        sendFileMenu = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        LogoutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(1271, 632));
        setMinimumSize(new java.awt.Dimension(1271, 632));
        setPreferredSize(new java.awt.Dimension(1271, 632));
        getContentPane().setLayout(null);

        txtpane2.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        txtpane2.setForeground(new java.awt.Color(120, 14, 3));
        txtpane2.setAutoscrolls(false);
        txtpane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtpane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpane2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(txtpane2);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(6, 81, 330, 510);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Friends List");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 50, 120, 20);

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setText("Group chat");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(200, 20, 130, 40);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(52, 4, 70, 0);

        jInternalFrame1.setVisible(false);
        jInternalFrame1.getContentPane().setLayout(null);

        jTextPane1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jTextPane1);

        jInternalFrame1.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 10, 860, 400);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Send_Button1.png"))); // NOI18N
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(jButton1);
        jButton1.setBounds(760, 440, 90, 90);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(jTextField1);
        jTextField1.setBounds(10, 420, 876, 130);

        getContentPane().add(jInternalFrame1);
        jInternalFrame1.setBounds(350, 0, 910, 600);

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(110, 20, 90, 40);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sharing.png"))); // NOI18N
        jMenu3.setText("File Sharing");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        sendFileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sendfile.png"))); // NOI18N
        sendFileMenu.setText("Send File");
        sendFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileMenuActionPerformed(evt);
            }
        });
        jMenu3.add(sendFileMenu);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/process.png"))); // NOI18N
        jMenuItem3.setText("Download");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jMenu2.setText("Account");

        LogoutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loggoff.png"))); // NOI18N
        LogoutMenu.setText("Log out");
        LogoutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutMenuActionPerformed(evt);
            }
        });
        jMenu2.add(LogoutMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileMenuActionPerformed
        // TODO add your handling code here:
        if(!attachmentOpen){
            SendFile s = new SendFile();
            if(s.prepare(username, host, port, this)){
                s.setLocationRelativeTo(null);
                s.setVisible(true);
                attachmentOpen = true;
            } else {
                JOptionPane.showMessageDialog(this, "Unable to set File Sharing at this time, please try again later.! "," Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_sendFileMenuActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int browse = chooser.showOpenDialog(this);
            if(browse == chooser.APPROVE_OPTION){
                this.mydownloadfolder = chooser.getSelectedFile().toString() +"\\";
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void LogoutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutMenuActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you log out ?");
        if(confirm == 0){
            try {
                socket.close();
                setVisible(false);
                /** Login Form **/
                new LoginForm().setVisible(true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_LogoutMenuActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        try {
            String content = username+" "+ evt.getActionCommand();
            dos.writeUTF("CMD_CHATALL "+ content);
            appendMyMessage(" "+evt.getActionCommand(), username);
            jTextField1.setText("");
        } catch (IOException e) {
            appendMessage(" Unable to send messages now, cannot connect to Server at this time, please try again later or restart this application.! "," Error ", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String content = username+" "+ jTextField1.getText();
            dos.writeUTF("CMD_CHATALL "+ content);
            appendMyMessage("                    "+jTextField1.getText(), username);
            jTextField1.setText("");
        } catch (IOException e) {
            appendMessage(" Unable to send messages now, cannot connect to Server at this time, please try again later or restart this application.! "," Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtpane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpane2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jInternalFrame1.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Vector online=this.getonline();
         Iterator i = online.iterator();
        while(i.hasNext()){
            Object e = i.next();
        
         this.OnlineClientCounts++;
            System.out.println("clients:"+this.OnlineClientCounts);
            System.out.println("online clients:"+" "+e);
            TC.add(new ClientsDisplay(this.username," "+e,this));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LogoutMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JMenuItem sendFileMenu;
    private javax.swing.JTextPane txtpane2;
    // End of variables declaration//GEN-END:variables

//    main.appendMessage(msg1, frm1,sendTO, Color.MAGENTA, Color.BLUE);
       ArrayList<ClientsDisplay> TC=new ArrayList<ClientsDisplay>();
    public void appendMessage(String msg, String header,String sendto, Color headerColor, Color contentColor){
        System.out.println("send to person name in main client :"+sendto);
        System.out.println("no of clients:"+TC.size());
      for(int i=0;i<TC.size();i++)
    { 
        System.out.println("list of client:"+TC.get(i).to);
        if(TC.get(i).to.equals(" "+sendto))
        {
            System.out.println("send to person text field found it");
            TC.get(i).jTextPane.setEditable(true);
       TC.get(i). getMsgHeader(sendto, headerColor);
       TC.get(i). getMsgContent(msg, contentColor);
       TC.get(i). jTextPane.setEditable(false);
        }
        else{
            System.out.println("send to person text field not found");
        }
         
    }
       
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    String TO = e.getActionCommand();
        
    this.jInternalFrame1.setVisible(false);
     
        System.out.println("in main client to:"+TO);
    for(int i=0;i<TC.size();i++)
    {
        System.out.println("button.clients:"+TC.get(i));
        System.out.println("button.Send To:"+TO);
        if(TC.get(i).to.equals(TO))
        {
            TC.get(i).jInternalFrame.setVisible(true);
            
        }
        else
        {
            TC.get(i).jInternalFrame.setVisible(false);
        }
         
    }
//    TC.add(new ClientsDisplay(this.username,TO,this));
    }
    

  
}
