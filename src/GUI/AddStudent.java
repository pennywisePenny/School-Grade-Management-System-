/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;
import Main.DBConnection;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class AddStudent extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddStudent.class.getName());
    public AddStudent(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                try
                {
                    DefaultListModel<String> model= new DefaultListModel<>();
                    Connection con=DBConnection.createConnection();
                    PreparedStatement pstmt=con.prepareStatement("select subject_name from subjects;");
                    ResultSet result=pstmt.executeQuery();
                    
                    while(result.next())
                        model.addElement(result.getString("subject_name"));
                    lstSubjects.setModel(model);

                }
                catch(Exception s)
                {
                
                    JOptionPane.showMessageDialog(rootPane, "Database Connection Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAddStudentSubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSubjects = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADD STUDENT");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("ADD STUDENT");

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtUsername.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("SUBJECT SELECTION");

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("PASSWORD");

        btnAddStudentSubmit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddStudentSubmit.setText("SUBMIT");
        btnAddStudentSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentSubmitActionPerformed(evt);
            }
        });

        lstSubjects.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(lstSubjects);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("USERNAME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(138, 138, 138)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(101, 101, 101))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(btnAddStudentSubmit)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(73, 73, 73)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddStudentSubmit)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddStudentSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentSubmitActionPerformed
        try
        {
            if( !txtUsername.getText().trim().isEmpty() && !txtPassword.getText().trim().isEmpty() )
            {
                if(lstSubjects.getModel().getSize()!=0)
                {
                    if(!lstSubjects.getSelectedValuesList().isEmpty())
                    {
                        List<String> selectedSubjects = lstSubjects.getSelectedValuesList();
                        Connection con=DBConnection.createConnection();
                        PreparedStatement pstmt=con.prepareStatement("insert into users(username,password,role) values(?,?,'student');"),
                                          pstmt2,pstmt3;
                        ResultSet result;
                        pstmt.setString(1,txtUsername.getText().trim());
                        pstmt.setString(2,txtPassword.getText().trim());
                        pstmt.executeUpdate();
                        pstmt=con.prepareStatement("insert into student_subjects(student_username,subject_name) values(?,?);");
                        
                        pstmt2=con.prepareStatement("select credit_hours,lecturer_username from subjects where subject_name=?;");
                        
                        pstmt3=con.prepareStatement("""
                                                   insert into grades(subject_name,credit_hours,student_username,lecturer_username)
                                                   values(?,?,?,?);
                                                   """
                        );
                        
                        
                        for(int i=0;i<selectedSubjects.size();i++)
                        {
                            pstmt.setString(1,txtUsername.getText().trim());
                            pstmt.setString(2,selectedSubjects.get(i));
                            pstmt.executeUpdate();
                            
                            pstmt2.setString(1,selectedSubjects.get(i));
                            result=pstmt2.executeQuery();
                            result.next();
                            
                            pstmt3.setString(1,selectedSubjects.get(i));
                            pstmt3.setString(2,result.getString("credit_hours"));
                            pstmt3.setString(3,txtUsername.getText().trim());
                            pstmt3.setString(4,result.getString("lecturer_username"));
                            pstmt3.executeUpdate();
                            
                        }

                        
                        
                        JOptionPane.showMessageDialog(rootPane, "User Successfully Added", "STUDENT REGISTRATION SUCCESSFULL", JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(rootPane, "Select Subjects", "STUDENT REGISTRATION FAILED", JOptionPane.WARNING_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Please Add Subjects first", "STUDENT REGISTRATION FAILED", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Please Input Both a Valid Password And Username", "STUDENT REGISTRATION FAILED", JOptionPane.WARNING_MESSAGE);
                txtUsername.setText("");
                txtPassword.setText("");
            }
        }
        catch(SQLIntegrityConstraintViolationException e)
        {

            JOptionPane.showMessageDialog(rootPane, "User Already Exists", "STUDENT REGISTRATION FAILED", JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Database Connection Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddStudentSubmitActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AddStudent dialog = new AddStudent(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudentSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstSubjects;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
