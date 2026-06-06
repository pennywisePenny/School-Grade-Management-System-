/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Main.DBConnection;
import java.io.*;
import java.io.FileWriter;

public class StudentDashboard extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentDashboard.class.getName());
    private static ResultSet userInfo;
    
    public static void getUserInfo(ResultSet usr)
    {
        userInfo=usr;
    }
    
    private DefaultTableModel model=new DefaultTableModel() 
    {
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
        
    };
    
    public StudentDashboard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                UserData.getUserInfo(userInfo);
                try
                {
                    Connection con=DBConnection.createConnection();
                    PreparedStatement pstmt;
                    ResultSet result;
                    if(userInfo.getString("fullname")==null)
                        new UserData(StudentDashboard.this, rootPaneCheckingEnabled).setVisible(true);
                    
                    lblUserFullname.setText(userInfo.getString("fullname"));
                    
                    con=DBConnection.createConnection();
                    pstmt=con.prepareStatement("select count(*) as total_subjects from student_subjects where student_username=?;");
                    pstmt.setString(1,userInfo.getString("username"));
                    result=pstmt.executeQuery();
                    result.next();
                    txtTotalSubjects.setText(Integer.toString(result.getInt("total_subjects")));
                    
                    
                    model.setRowCount(0);
                    model.setColumnIdentifiers(new String[]{
                        "SUBJECT",
                        "CREDIT HOURS",
                        "LECTURER",
                        "MARKS",
                        "GRADE LETTER",
                        "GPA"
                    });
                    

                    pstmt=con.prepareStatement("select * from grades where student_username=?;");
                    pstmt.setString(1,userInfo.getString("username"));
                    result=pstmt.executeQuery();
                    
                    tblGrades.setModel(model);
                    
                    double totalQualityPoints=0,
                           totalCredits=0;
                    
                    while(result.next())
                    {
                        model.addRow(new Object[]{
                            result.getString("subject_name"),
                            result.getInt("credit_hours"),
                            result.getString("lecturer_username"),
                            result.getDouble("marks"),
                            result.getString("grade_letter"),
                            result.getDouble("GPA")
                        });
                        totalQualityPoints+=(result.getInt("credit_hours")*result.getDouble("GPA"));
                        totalCredits+=result.getInt("credit_hours");
                    }
                    
                    txtOverallGpa.setText(String.format("%.2f",totalQualityPoints/totalCredits));
                    
                    tblGrades.getTableHeader().setResizingAllowed(false);
                    tblGrades.getColumnModel().getColumn(0).setPreferredWidth(100);
                    tblGrades.getColumnModel().getColumn(1).setPreferredWidth(80);
                    tblGrades.getColumnModel().getColumn(2).setPreferredWidth(100);
                    tblGrades.getColumnModel().getColumn(3).setPreferredWidth(30);
                    tblGrades.getColumnModel().getColumn(5).setPreferredWidth(30);
                    tblGrades.getTableHeader().setFont(
                            new java.awt.Font(
                                    "Segoe UI",
                                    java.awt.Font.BOLD,
                                    16
                            )
                    );
                    
                    tblGrades.setFont(
                            new java.awt.Font(
                                    "Segoe UI",
                                    java.awt.Font.PLAIN,
                                    13
                            )
                    );
                    
                    tblGrades.setRowHeight(25);
                    con.close();
                }
               catch(Exception s)
               {
                   System.out.println(s);
                   s.printStackTrace();
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
        
        ImageIcon SubjectImg = new ImageIcon("src/assets/subject.png");
        Image scaledSubjectImage = SubjectImg.getImage().getScaledInstance(
        lblSubjectImg.getWidth(), 
        lblSubjectImg.getHeight(), 
        Image.SCALE_SMOOTH 
        );
        lblSubjectImg.setIcon(new ImageIcon(scaledSubjectImage));
        
        
        ImageIcon gradeImg = new ImageIcon("src/assets/recorded.png");
        Image scaledGradeImage = gradeImg.getImage().getScaledInstance(
        lblGradeImg.getWidth(), 
        lblGradeImg.getHeight(), 
        Image.SCALE_SMOOTH 
        );
        lblGradeImg.setIcon(new ImageIcon(scaledGradeImage));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblUserFullname = new javax.swing.JLabel();
        lblUserImg = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblSubjectImg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalSubjects = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblGradeImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOverallGpa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        btnExportMarks = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("STUDENT DASHBOARD");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("TOTAL SUBJECTS");

        txtTotalSubjects.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalSubjects.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTotalSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblSubjectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubjectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalSubjects)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("MY GPA");

        txtOverallGpa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtOverallGpa.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtOverallGpa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblGradeImg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGradeImg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOverallGpa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "SUBJECT", "CREDIT HOURS", "LECTURER", "MARKS", "GRADE LETTER", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGrades.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblGrades);
        if (tblGrades.getColumnModel().getColumnCount() > 0) {
            tblGrades.getColumnModel().getColumn(0).setResizable(false);
            tblGrades.getColumnModel().getColumn(1).setResizable(false);
            tblGrades.getColumnModel().getColumn(2).setResizable(false);
            tblGrades.getColumnModel().getColumn(3).setResizable(false);
            tblGrades.getColumnModel().getColumn(4).setResizable(false);
            tblGrades.getColumnModel().getColumn(5).setResizable(false);
        }

        btnExportMarks.setBackground(new java.awt.Color(204, 204, 204));
        btnExportMarks.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExportMarks.setText("EXPORT GRADES AS A CSV FILE");
        btnExportMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportMarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblUserImg, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblUserFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(btnExportMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserImg, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout))
                        .addGap(3, 3, 3)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void exportToCSV(String filePath)
    {
        System.out.println(model.getRowCount());
        System.out.println(model.getColumnCount());
        try
        {
            FileWriter writer=new FileWriter(filePath);
            for(int col=0;col<model.getColumnCount();col++)
            {
                writer.append(model.getColumnName(col));
                if(col<model.getColumnCount()-1)
                    writer.append(",");
            }
            writer.append("\n");
            
            for(int row=0; row<model.getRowCount();row++)
            {
                for(int col=0;col<model.getColumnCount();col++)
                {
                    Object value=model.getValueAt(row, col);
                    writer.append(value==null?"":value.toString());
                    
                    if(col<model.getColumnCount()-1)
                        writer.append(",");

                }
                writer.append("\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExportMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportMarksActionPerformed
        try{
        JFileChooser chooser=new JFileChooser();
        if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            String path=chooser.getSelectedFile().getAbsolutePath();
            if( !path.toLowerCase().endsWith(".csv") )
            {
                path+=".csv";
            }
            exportToCSV(path);
            JOptionPane.showMessageDialog(rootPane, "Export Completed!");
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnExportMarksActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentDashboard dialog = new StudentDashboard(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExportMarks;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGradeImg;
    private javax.swing.JLabel lblSubjectImg;
    private javax.swing.JLabel lblUserFullname;
    private javax.swing.JLabel lblUserImg;
    private javax.swing.JTable tblGrades;
    private javax.swing.JLabel txtOverallGpa;
    private javax.swing.JLabel txtTotalSubjects;
    // End of variables declaration//GEN-END:variables
}
