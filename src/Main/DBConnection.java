/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.File;
import java.sql.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DBConnection {
    
    static String username, password, host, port;//, DBName;

    public static Connection createConnection() throws Exception {

        File xmlFile = new File("src/XML/DB.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        NodeList NLUser, NLPass, NLHost, NLPort;//, NLDBName;
        Element ELuser, ELPass, ELHost, ELPort;//, ELDBName;

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

        /*NLDBName = doc.getElementsByTagName("DB");
        ELDBName = (Element) NLDBName.item(0);
        DBName = ELDBName.getChildNodes().item(0).getNodeValue().trim();*/

        String server="jdbc:mysql://"+host+":"+port;
        String DB = "jdbc:mysql://" + host + ":" + port + "/" + /*DBName*/"nibmeadsgms" + "?autoReconnect=true&useSSL=false";
        Connection con = DriverManager.getConnection(server, username, password);
        
        Statement stmt=con.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS nibmeadsgms;");
        con=DriverManager.getConnection(DB,username,password);
        stmt=con.createStatement();
        stmt.executeUpdate("""
                           CREATE TABLE IF NOT EXISTS users(username varchar(50) primary key,
                                                            password varchar(50) not null,
                                                            role     varchar(20) not null,
                                                            fullname varchar(150));
                           """);
        
        return con;

    }
    
}
