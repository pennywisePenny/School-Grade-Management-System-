/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.File;
import java.sql.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DBConnection 
{
    
    static String username, password, host, port, DBName;

    public static Connection createConnection() throws Exception 
    {
        
        String server="jdbc:mysql://"+host+":"+port;
        Connection con=DriverManager.getConnection(server+"/"+DBName+"?autoReconnect=true&useSSL=false",username,password);

        return con;

    }
    
    public static void createDB() throws Exception
    {
        File xmlFile = new File("src/XML/DB.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        NodeList NLUser, NLPass, NLHost, NLPort, NLDBName;
        Element ELuser, ELPass, ELHost, ELPort, ELDBName;

        NLUser = doc.getElementsByTagName("user");
        ELuser = (Element) NLUser.item(0);
        username = ELuser.getChildNodes().item(0).getNodeValue().trim();
     
        NLPass = doc.getElementsByTagName("password");
        ELPass = (Element) NLPass.item(0);
        password = ELPass.getChildNodes().item(0).getNodeValue().trim();

        NLHost = doc.getElementsByTagName("host");
        ELHost = (Element) NLHost.item(0);
        host = ELHost.getChildNodes().item(0).getNodeValue().trim();

        NLPort = doc.getElementsByTagName("port");
        ELPort = (Element) NLPort.item(0);
        port = ELPort.getChildNodes().item(0).getNodeValue().trim();
        
        NLDBName = doc.getElementsByTagName("DB");
        ELDBName = (Element) NLDBName.item(0);
        DBName = ELDBName.getChildNodes().item(0).getNodeValue().trim();
        
        String server="jdbc:mysql://"+host+":"+port;
        Connection con = DriverManager.getConnection(server, username, password);
        Statement stmt=con.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS "+DBName+";");
        con=createConnection();
        stmt=con.createStatement();
        
        stmt.executeUpdate("""
                           CREATE TABLE IF NOT EXISTS users(
                                username VARCHAR(50) PRIMARY KEY,
                                password VARCHAR(50) NOT NULL,
                                role     VARCHAR(20) NOT NULL,
                                fullname VARCHAR(150)
                           );
                           """);
        stmt.executeUpdate("""
                           INSERT INTO users(username, password, role, fullname)
                                select 'admin','admin','admin','admin'
                           WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='admin');
                           """);
        
        stmt.executeUpdate("""
                           CREATE TABLE IF NOT EXISTS subjects(
                                subject_id          INT PRIMARY KEY AUTO_INCREMENT,
                                subject_name        VARCHAR(50) NOT NULL UNIQUE,
                                credit_hours        INT NOT NULL,
                                lecturer_username   VARCHAR(50),
                           
                                FOREIGN KEY (lecturer_username) REFERENCES users(username)
                                ON DELETE SET NULL
                           );
                           """);
        
        stmt.executeUpdate("""
                           CREATE TABLE IF NOT EXISTS student_subjects(
                                id                INT PRIMARY KEY AUTO_INCREMENT,
                                student_username  VARCHAR(50) NOT NULL,
                                subject_name      VARCHAR(50) NOT NULL,
                           
                                FOREIGN KEY (student_username) REFERENCES users(username)
                                ON DELETE CASCADE,
                           
                                FOREIGN KEY (subject_name) REFERENCES subjects(subject_name)
                                ON DELETE CASCADE
                           );
                           
                           """);
        
        stmt.executeUpdate("""
                           CREATE TABLE IF NOT EXISTS grades(
                                grade_id            INT PRIMARY KEY AUTO_INCREMENT,
                                subject_name        VARCHAR(50) NOT NULL,                                
                                credit_hours        INT NOT NULL,
                                student_username    VARCHAR(50) NOT NULL,
                                marks               DOUBLE DEFAULT 0.0,
                                grade_letter        VARCHAR(3) DEFAULT "E",
                                GPA                 DOUBLE DEFAULT 0.0,
                                lecturer_username   VARCHAR(50) NOT NULL
                           );
                           """);
                
    }
    
}
