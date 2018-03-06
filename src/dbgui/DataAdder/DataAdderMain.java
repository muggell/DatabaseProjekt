/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgui.DataAdder;

import dbgui.DBTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krongrah
 */
public class DataAdderMain {

    public static void main(String[] args) {

        File file = new File("players.csv");
        
     String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/tilfjjea";
     String username = "tilfjjea";
     String password = "tC0sMMGx8EH6KyQ7CRBVEIUVzC82C8Zv";
        
        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();

        
        
        
        
        try (Scanner s = new Scanner(file);) {

            while (s.hasNext()) {
                String[] strings = s.nextLine().split(",");

                String query="insert into people values('"+strings[0]+"','"+strings[1]+"','"+strings[0]+"@dbmail.com')";
                
                st.executeQuery(query);
                System.out.println(query);
                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAdderMain.class.getName()).log(Level.SEVERE, null, ex);
        }
} catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
