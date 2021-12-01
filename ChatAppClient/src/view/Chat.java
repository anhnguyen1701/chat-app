/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Anh Nguyen
 */
public class Chat extends javax.swing.JFrame {

    /**
     * Creates new form ChatView
     */
    private Map<String, JScrollPane> listOnlineUsers = new HashMap<>();
    private Map<String, JScrollPane> listGroups = new HashMap<>();
    private Map<String, JScrollPane> All = new HashMap<>();

    public Chat() {
        initComponents();
        updateChatAllViewPanel();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOnlineUsers = new javax.swing.JTable();
        panelChatView = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSend = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        lbSendTo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCreateGroup = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGroup = new javax.swing.JTable();
        panelChatViewGroup = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaGroup = new javax.swing.JTextArea();
        btnSendGroup = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbSendToGroup = new javax.swing.JLabel();
        txtGroupname = new javax.swing.JTextField();
        btnRefesh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnSendAll = new javax.swing.JButton();
        panelChatViewAll = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaAll = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblOnlineUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Online"
            }
        ));
        tblOnlineUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOnlineUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOnlineUsers);

        panelChatView.setBackground(new java.awt.Color(187, 187, 187));
        panelChatView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelChatView.setToolTipText("");

        javax.swing.GroupLayout panelChatViewLayout = new javax.swing.GroupLayout(panelChatView);
        panelChatView.setLayout(panelChatViewLayout);
        panelChatViewLayout.setHorizontalGroup(
            panelChatViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        panelChatViewLayout.setVerticalGroup(
            panelChatViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtAreaSend.setColumns(20);
        txtAreaSend.setRows(5);
        jScrollPane2.setViewportView(txtAreaSend);

        btnSend.setText("Send");

        lbSendTo.setText("...");

        jLabel1.setText("Bạn đang nói chuyện với");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChatView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSendTo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSendTo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChatView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Private", jPanel1);

        btnCreateGroup.setText("Create");
        btnCreateGroup.setActionCommand("Create group");

        tblGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Danh sách phòng"
            }
        ));
        tblGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGroupMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblGroup);

        panelChatViewGroup.setBackground(new java.awt.Color(187, 187, 187));
        panelChatViewGroup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelChatViewGroup.setToolTipText("");

        javax.swing.GroupLayout panelChatViewGroupLayout = new javax.swing.GroupLayout(panelChatViewGroup);
        panelChatViewGroup.setLayout(panelChatViewGroupLayout);
        panelChatViewGroupLayout.setHorizontalGroup(
            panelChatViewGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelChatViewGroupLayout.setVerticalGroup(
            panelChatViewGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        txtAreaGroup.setColumns(20);
        txtAreaGroup.setRows(5);
        jScrollPane4.setViewportView(txtAreaGroup);

        btnSendGroup.setText("Send");
        btnSendGroup.setActionCommand("Send Group");

        jLabel2.setText("Bạn đang trong nhóm:");

        lbSendToGroup.setText("...");

        btnRefesh.setText("Find");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChatViewGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSendGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtGroupname, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCreateGroup)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefesh))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSendToGroup)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateGroup)
                    .addComponent(txtGroupname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefesh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbSendToGroup))
                .addGap(12, 12, 12)
                .addComponent(panelChatViewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSendGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );

        jTabbedPane1.addTab("Group", jPanel2);

        btnSendAll.setText("Send");
        btnSendAll.setActionCommand("Send All");

        panelChatViewAll.setBackground(new java.awt.Color(187, 187, 187));
        panelChatViewAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelChatViewAll.setToolTipText("");
        panelChatViewAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelChatViewAllMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChatViewAllLayout = new javax.swing.GroupLayout(panelChatViewAll);
        panelChatViewAll.setLayout(panelChatViewAllLayout);
        panelChatViewAllLayout.setHorizontalGroup(
            panelChatViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelChatViewAllLayout.setVerticalGroup(
            panelChatViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        txtAreaAll.setColumns(20);
        txtAreaAll.setRows(5);
        jScrollPane5.setViewportView(txtAreaAll);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendAll, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                    .addComponent(panelChatViewAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChatViewAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSendAll, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("All", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblOnlineUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOnlineUsersMouseClicked
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String username = source.getModel().getValueAt(row, column).toString();
        updateChatViewPanel(username);
    }//GEN-LAST:event_tblOnlineUsersMouseClicked

    private void tblGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGroupMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String groupname = source.getModel().getValueAt(row, column).toString();
        updatePanelChatViewGroup(groupname);
    }//GEN-LAST:event_tblGroupMouseClicked

    private void panelChatViewAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelChatViewAllMouseClicked
        // TODO add your handling code here:
        System.out.println("helo");
        updateChatAllViewPanel();
    }//GEN-LAST:event_panelChatViewAllMouseClicked

    public void addButtonListener(ActionListener actionListener) {
        btnSend.addActionListener(actionListener);
        btnCreateGroup.addActionListener(actionListener);
        btnSendGroup.addActionListener(actionListener);
        btnRefesh.addActionListener(actionListener);
        btnSendAll.addActionListener(actionListener);
    }

//private chat ui
    private void updateChatViewPanel(String username) {
        panelChatView.removeAll();

        JScrollPane jScrollPane = this.listOnlineUsers.get(username);
        panelChatView.add(jScrollPane);
        jScrollPane.setSize(panelChatView.getWidth(), panelChatView.getHeight());
        jScrollPane.setVisible(true);

        panelChatView.revalidate();

        lbSendTo.setText(username);
    }

    private void appendToTextPane(JTextPane jTextPane, String message) {
        HTMLDocument doc = (HTMLDocument) jTextPane.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) jTextPane.getEditorKit();

        try {
            editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
            jTextPane.setCaretPosition(doc.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatus(String usernameFrom, int status) {
        if (!this.listOnlineUsers.containsKey(usernameFrom) && status == 1) {
            JTextPane jTextPane = new JTextPane();
            JScrollPane jScrollPane = new JScrollPane(jTextPane);
            jScrollPane.setName(usernameFrom);
            jTextPane.setEditable(false);
            jTextPane.setContentType("text/html");
            jTextPane.setBackground(Color.WHITE);
            jTextPane.setForeground(Color.WHITE);
            jTextPane.setFont(new Font("Courier New", Font.PLAIN, 18));
            updateStatusTable(usernameFrom, status);
            this.listOnlineUsers.put(usernameFrom, jScrollPane);
        } else if (this.listOnlineUsers.containsKey(usernameFrom) && status == 0) {
            updateStatusTable(usernameFrom, status);
            updateReceiveMessage(usernameFrom, "is offline");
            this.listOnlineUsers.remove(usernameFrom);
        }
    }

    private void updateStatusTable(String usernameFrom, int status) {
        DefaultTableModel model = (DefaultTableModel) tblOnlineUsers.getModel();
        if (status == 1) {
            model.addRow(new Object[]{usernameFrom});
        } else if (status == 0) {
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).toString().equals(usernameFrom)) {
                    model.removeRow(i);
                    System.out.println("remove: " + usernameFrom);
                    break;
                }
            }
        }
    }

    public void updateReceiveMessage(String usernameFrom, String message) {
        if (this.listOnlineUsers.containsKey(usernameFrom)) {
            appendToTextPane((JTextPane) this.listOnlineUsers.get(usernameFrom).getViewport().getComponents()[0],
                    "<div style='width: 40%; background-color: white; color: black; margin-top: 4px; float: left'>"
                    + usernameFrom + ": "
                    + message + "</div>");
        }
    }

    public void updateSendMessage(String usernameTo, String message) {
        if (this.listOnlineUsers.containsKey(usernameTo)) {
            appendToTextPane((JTextPane) this.listOnlineUsers.get(usernameTo).getViewport().getComponents()[0],
                    "<div style='width: 40%; background-color: white; color: black; margin-top: 4px; float: right'>"
                    + "me: " + message + "</div>");
        }
    }

//group ui
    private void updatePanelChatViewGroup(String groupname) {
        panelChatViewGroup.removeAll();

        JScrollPane jScrollPane = this.listGroups.get(groupname);
        panelChatViewGroup.add(jScrollPane);
        jScrollPane.setSize(panelChatViewGroup.getWidth(), panelChatViewGroup.getHeight());
        jScrollPane.setVisible(true);

        lbSendToGroup.setText(groupname);

        panelChatViewGroup.revalidate();
    }

    public void updateGroupStatus(String groupname) {
        if ((!this.listGroups.containsKey(groupname))) {
            JTextPane jTextPane = new JTextPane();
            JScrollPane jScrollPane = new JScrollPane(jTextPane);
            jScrollPane.setName(groupname);
            jTextPane.setEditable(false);
            jTextPane.setContentType("text/html");
            jTextPane.setBackground(Color.WHITE);
            jTextPane.setForeground(Color.WHITE);
            jTextPane.setFont(new Font("Courier New", Font.PLAIN, 18));
//            appendToTextPane(jTextPane, "<div class='clear' style='background-color:white; color:red; position: fixed'>" + groupname + "</div>");
            udateStatusGroupTable(groupname);
            this.listGroups.put(groupname, jScrollPane);
        }
    }

    private void udateStatusGroupTable(String groupname) {
        DefaultTableModel model = (DefaultTableModel) tblGroup.getModel();
        model.addRow(new Object[]{groupname});
    }

    public void updateReceiveGroupMessage(String usernameFrom, String roomname, String message) {
        if (this.listGroups.containsKey(roomname)) {
            appendToTextPane((JTextPane) this.listGroups.get(roomname).getViewport().getComponents()[0],
                    "<div style='width: 40%; background-color: white; margin-top: 4px; float: left'>"
                    + usernameFrom + ": " + message
                    + "</div>");
        }
    }

    public void updateSendGroupMessage(String groupname, String message) {
        if (this.listGroups.containsKey(groupname)) {
            appendToTextPane((JTextPane) this.listGroups.get(groupname).getViewport().getComponents()[0],
                    "<div style='width: 40%; background-color: white; margin-top: 4px; float: right'>"
                    + "me: " + message
                    + "</div>");
        }
    }

// chat all ui
//internal function
    public void addCloseListener(WindowAdapter windowAdapter) {
        this.addWindowListener(windowAdapter);
    }

    public void show(String content) {
        JOptionPane.showMessageDialog(null, content);
    }

//private
    public String getLbSendTo() {
        return lbSendTo.getText();
    }

    public String getTxtAreaSend() {
        return txtAreaSend.getText();
    }

    public void clearTxtAreaSend() {
        txtAreaSend.setText("");
    }

//group
    public String getGroupname() {
        return txtGroupname.getText().trim();
    }

    public String getLbSendToGroup() {
        return lbSendToGroup.getText().trim();
    }

    public String getTxtAreaGroup() {
        return txtAreaGroup.getText().trim();
    }

    public void clearTxtAreaGroup() {
        txtAreaGroup.setText("");
    }

//all
    private void updateChatAllViewPanel() {
        JTextPane jTextPane = new JTextPane();
        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setName("all");
        jTextPane.setEditable(false);
        jTextPane.setContentType("text/html");
        jTextPane.setBackground(Color.WHITE);
        jTextPane.setForeground(Color.WHITE);
        jTextPane.setFont(new Font("Courier New", Font.PLAIN, 18));
        this.All.put("all", jScrollPane);

//        panelChatViewAll.removeAll();
        JScrollPane jScrollPane2 = this.All.get("all");
        panelChatViewAll.add(jScrollPane2);
        jScrollPane.setSize(panelChatViewAll.getWidth(), panelChatViewAll.getHeight());
        jScrollPane.setVisible(true);

        panelChatViewAll.revalidate();
    }

    public void updateSendAllMessage(String message) {
        appendToTextPane((JTextPane) this.All.get("all").getViewport().getComponents()[0],
                "<div style='width: 40%; background-color: white; margin-top: 4px; float: right'>"
                + "me: " + message
                + "</div>");
    }

    public void updateReceiveAllMessage(String usernameFrom, String message) {
        appendToTextPane((JTextPane) this.All.get("all").getViewport().getComponents()[0],
                "<div style='width: 40%; background-color: white; margin-top: 4px; float: left'>"
                + usernameFrom + ": " + message
                + "</div>");
    }

    public String getTxtAreaAll() {
        return txtAreaAll.getText().trim();
    }

    public void clearTxtAreaAll() {
        txtAreaAll.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateGroup;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSendAll;
    private javax.swing.JButton btnSendGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbSendTo;
    private javax.swing.JLabel lbSendToGroup;
    private javax.swing.JPanel panelChatView;
    private javax.swing.JPanel panelChatViewAll;
    private javax.swing.JPanel panelChatViewGroup;
    private javax.swing.JTable tblGroup;
    private javax.swing.JTable tblOnlineUsers;
    private javax.swing.JTextArea txtAreaAll;
    private javax.swing.JTextArea txtAreaGroup;
    private javax.swing.JTextArea txtAreaSend;
    private javax.swing.JTextField txtGroupname;
    // End of variables declaration//GEN-END:variables
}