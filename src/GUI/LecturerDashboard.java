/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import Main.DBConnection;
import java.lang.classfile.instruction.ConvertInstruction;
import javax.swing.table.DefaultTableModel;
        
public class LecturerDashboard extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LecturerDashboard.class.getName());
    
    private static ResultSet userInfo;
    
    
    public static void getUserInfo(ResultSet usr)
    {
        userInfo=usr;
    }
    
    private DefaultTableModel model=new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column)
        {
            if(column==3)
                return true;
            return false;
        }
        
    };
    
    public LecturerDashboard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                UserData.getUserInfo(userInfo);
                try
                {
                    while(userInfo.getString("fullname")==null)
                    {
                        UserData userName = new UserData(LecturerDashboard.this, rootPaneCheckingEnabled);
                        userName.setVisible(true);
                    }
                    lblUserFullname.setText(userInfo.getString("fullname"));
                    Connection con=DBConnection.createConnection();
                    PreparedStatement pstmt=con.prepareStatement("select count(*) as total_subjects from subjects where lecturer_username=?;");
                    pstmt.setString(1,userInfo.getString("username"));
                    ResultSet result=pstmt.executeQuery();
                    result.next();
                    txtTotalSubjects.setText(Integer.toString(result.getInt("total_subjects")));
                    
                    pstmt=con.prepareStatement("select count(distinct student_username) as total_students from grades where lecturer_username=?;");
                    pstmt.setString(1,userInfo.getString("username"));
                    result=pstmt.executeQuery();
                    result.next();
                    txtTotalStudents.setText(Integer.toString(result.getInt("total_students")));
                    
                    pstmt=con.prepareStatement("select * from subjects where lecturer_username =?;");
                    pstmt.setString(1,userInfo.getString("username"));
                    result=pstmt.executeQuery();
                    
                    while(result.next())
                        cmbSubjects.addItem(result.getString("subject_name"));
                    
                    
                    model.setRowCount(0);
                    model.setColumnIdentifiers(new String[]{
                        "SUBJECT",
                        "CREDIT HOURS",
                        "STUDENT",
                        "MARKS",
                        "GRADE LETTER",
                        "GPA"
                    });
      

                    
                    switch(cmbSubjects.getSelectedItem().toString())
                    {
                        case "ALL":
                            pstmt=con.prepareStatement("select * from grades where lecturer_username=?;");
                            pstmt.setString(1,userInfo.getString("username"));
                        break;
                        
                        default:
                            pstmt=con.prepareStatement("select * from grades where subject_name=?;");
                            //pstmt.setString(1,userInfo.getString("username"));
                            pstmt.setString(1,cmbSubjects.getSelectedItem().toString());
                    }
                    
                    result=pstmt.executeQuery();
                    tblMarks.setModel(model);
                    
                    while(result.next())
                        model.addRow(new Object[]{
                            result.getString("subject_name"),
                            result.getInt("credit_hours"),
                            result.getString("student_username"),
                            result.getDouble("marks"),
                            result.getString("grade_letter"),
                            result.getDouble("GPA")
                        });
                    
                    
                    
                    tblMarks.getTableHeader().setResizingAllowed(false);
                    tblMarks.getColumnModel().getColumn(0).setPreferredWidth(100);
                    tblMarks.getColumnModel().getColumn(1).setPreferredWidth(80);
                    tblMarks.getColumnModel().getColumn(2).setPreferredWidth(100);
                    tblMarks.getColumnModel().getColumn(3).setPreferredWidth(30);
                    tblMarks.getColumnModel().getColumn(5).setPreferredWidth(30);
                    tblMarks.getTableHeader().setFont(
                            new java.awt.Font(
                                    "Segoe UI",
                                    java.awt.Font.BOLD,
                                    16
                            )
                    );
                    
                    tblMarks.setFont(
                            new java.awt.Font(
                                    "Segoe UI",
                                    java.awt.Font.PLAIN,
                                    13
                            )
                    );
                    
                    tblMarks.setRowHeight(25);
                }
               catch(Exception s)
               {
                   System.out.println(s);
               }
            }
        });
        
        ImageIcon userImg = new ImageIcon("src/assets/logo6.jpg");
        Image scaledUserImage = userImg.getImage().getScaledInstance(
        lblUserImg.getWidth(), 
        lblUserImg.getHeight(), 
        Image.SCALE_SMOOTH 
        );
        lblUserImg.setIcon(new ImageIcon(scaledUserImage));
        
        ImageIcon subjectImg = new ImageIcon("src/assets/subject.png");
        Image scaledSubjectImage = subjectImg.getImage().getScaledInstance(
        lblSubjectImg.getWidth(), 
        lblSubjectImg.getHeight(), 
        Image.SCALE_SMOOTH 
        );
        lblSubjectImg.setIcon(new ImageIcon(scaledSubjectImage));
        
        ImageIcon studentImg = new ImageIcon("src/assets/student.png");
        Image scaledStudentImage = studentImg.getImage().getScaledInstance(
        lblStudentImg.getWidth(), 
        lblStudentImg.getHeight(), 
        Image.SCALE_SMOOTH 
        );
        lblStudentImg.setIcon(new ImageIcon(scaledStudentImage));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUserFullname = new javax.swing.JLabel();
        lblUserImg = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblSubjectImg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalSubjects = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblStudentImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotalStudents = new javax.swing.JLabel();
        btnLoadStudents = new javax.swing.JButton();
        cmbSubjects = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMarks = new javax.swing.JTable();
        btnSubmitGrade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LECTURER DASHBOARD");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblUserFullname.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        btnLogout.setBackground(new java.awt.Color(255, 0, 0));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.setToolTipText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("MY SUBJECTS");

        txtTotalSubjects.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalSubjects.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTotalSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblSubjectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubjectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalSubjects)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("MY TOTAL STUDENTS");

        txtTotalStudents.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalStudents.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTotalStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblStudentImg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStudentImg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalStudents)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLoadStudents.setBackground(new java.awt.Color(204, 255, 204));
        btnLoadStudents.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLoadStudents.setText("LOAD STUDENTS");
        btnLoadStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadStudentsActionPerformed(evt);
            }
        });

        cmbSubjects.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbSubjects.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL" }));

        tblMarks.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "SUBJECT", "CREDIT HOURS", "STUDENT", "MARKS", "GRADE LETTER", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarks.getTableHeader().setResizingAllowed(false);
        tblMarks.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblMarks);
        if (tblMarks.getColumnModel().getColumnCount() > 0) {
            tblMarks.getColumnModel().getColumn(0).setResizable(false);
            tblMarks.getColumnModel().getColumn(1).setResizable(false);
            tblMarks.getColumnModel().getColumn(2).setResizable(false);
            tblMarks.getColumnModel().getColumn(3).setResizable(false);
            tblMarks.getColumnModel().getColumn(4).setResizable(false);
            tblMarks.getColumnModel().getColumn(5).setResizable(false);
        }

        btnSubmitGrade.setBackground(new java.awt.Color(204, 204, 204));
        btnSubmitGrade.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSubmitGrade.setText("SUBMIT GRADES");
        btnSubmitGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitGradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblUserImg, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblUserFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLoadStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSubjects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(btnSubmitGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserImg, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout))
                        .addGap(3, 3, 3)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLoadStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(cmbSubjects))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmitGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLoadStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadStudentsActionPerformed
        
        try
        {
            ResultSet result;
            Connection con=DBConnection.createConnection();
            PreparedStatement pstmt;
        
            switch(cmbSubjects.getSelectedItem().toString())
            {
                case "ALL":
                    pstmt=con.prepareStatement("select * from grades where lecturer_username=?;");
                    pstmt.setString(1,userInfo.getString("username"));
                break;  
                        
                default:
                    pstmt=con.prepareStatement("select * from grades where subject_name=?;");
                    //pstmt.setString(1,userInfo.getString("username"));
                    pstmt.setString(1,cmbSubjects.getSelectedItem().toString());
            }
            
            result=pstmt.executeQuery();
            model.setRowCount(0);
            
            while(result.next())
            model.addRow(new Object[]{
                result.getString("subject_name"),
                result.getInt("credit_hours"),
                result.getString("student_username"),
                result.getDouble("marks"),
                result.getString("grade_letter"),
                result.getDouble("GPA")
            });
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnLoadStudentsActionPerformed

    private String gradeLetter(double m)
    {
        String g="";
        if(m>=85&&m<=100)
            g="A+";
        else if(m>=70&&m<=84)
            g="A";
        else if(m>=65&&m<=69)
            g="A-";
        else if(m>=60&&m<=64)
            g="B+";
        else if(m>=55&&m<=59)
            g="B";
        else if(m>=50&&m<=54)
            g="B-";
        else if(m>=45&&m<=49)
            g="C+";
        else if(m>=40&&m<=44)
            g="C";
        else if(m>=35&&m<=39)
            g="C-";
        else if(m>=30&&m<=34)
            g="D+";
        else if(m>=25&&m<=29)
            g="D";
        else if(m>=0&&m<=24)
            g="E";
        
        return g;
    }
    
    private double gradePoints(double m)
    {
        double p=0.0;
        if(m>=85&&m<=100)
            p=4.0;
        else if(m>=70&&m<=84)
            p=4.0;
        else if(m>=65&&m<=69)
            p=3.7;
        else if(m>=60&&m<=64)
            p=3.3;
        else if(m>=55&&m<=59)
            p=3.0;
        else if(m>=50&&m<=54)
            p=2.7;
        else if(m>=45&&m<=49)
            p=2.3;
        else if(m>=40&&m<=44)
            p=2.0;
        else if(m>=35&&m<=39)
            p=1.7;
        else if(m>=30&&m<=34)
            p=1.3;
        else if(m>=25&&m<=29)
            p=1.0;
        else if(m>=0&&m<=24)
            p=0.0;
        
        return p;
    }
    
    private void btnSubmitGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitGradeActionPerformed
        for(int row=0;row<model.getRowCount();row++)
        {  
            double marks,gpa;
            String gradeLetter,subjectName="",studentName="";
            try
            {
                if( !model.getValueAt(row,3).toString().trim().isEmpty() )
                {   
                    if( (Double.parseDouble(model.getValueAt(row, 3).toString().trim()) >= 0) && (Double.parseDouble(model.getValueAt(row, 3).toString().trim()) <= 100) )
                    {
                        Connection con=DBConnection.createConnection();
                        PreparedStatement pstmt;
                        marks=Double.parseDouble(model.getValueAt(row,3).toString().trim());
                        gradeLetter=gradeLetter(marks);
                        gpa=gradePoints(marks);
                    
                        subjectName=model.getValueAt(row,0).toString();
                        studentName=model.getValueAt(row,2).toString();
                        model.setValueAt(gradeLetter, row, 4);
                        model.setValueAt(gpa, row, 5);
                        pstmt = con.prepareStatement("update grades set marks = ? where subject_name=? and student_username =?;");
                        pstmt.setDouble(1,marks);
                        pstmt.setString(2,subjectName);
                        pstmt.setString(3,studentName);
                        pstmt.executeUpdate();
                    
                        pstmt=con.prepareStatement("update grades set grade_letter = ? where subject_name= ? and student_username =?;");
                        pstmt.setString(1,gradeLetter);
                        pstmt.setString(2,subjectName);
                        pstmt.setString(3,studentName);
                        pstmt.executeUpdate();
                    
                        pstmt=con.prepareStatement("update grades set gpa = ? where subject_name= ? and student_username =?;");
                        pstmt.setDouble(1,gpa);
                        pstmt.setString(2,subjectName);
                        pstmt.setString(3,studentName);
                        pstmt.executeUpdate();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Please Input a valid number between 0 and 100", "INVALID RANGE", JOptionPane.WARNING_MESSAGE);
                        model.setValueAt(0.0,row,3);
                    }
                }
                else
                    model.setValueAt(0.0,row,3);
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Input Valid Marks", "MARK ALLOCATION FAILED", JOptionPane.WARNING_MESSAGE);
                model.setValueAt(0.0,row,3);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnSubmitGradeActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LecturerDashboard dialog = new LecturerDashboard(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLoadStudents;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSubmitGrade;
    private javax.swing.JComboBox<String> cmbSubjects;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStudentImg;
    private javax.swing.JLabel lblSubjectImg;
    private javax.swing.JLabel lblUserFullname;
    private javax.swing.JLabel lblUserImg;
    private javax.swing.JTable tblMarks;
    private javax.swing.JLabel txtTotalStudents;
    private javax.swing.JLabel txtTotalSubjects;
    // End of variables declaration//GEN-END:variables
}
