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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krongrah
 */
public class PlayerAdder {

    public static String clean(String val) {
        String word = "";
        for (char ch : val.toCharArray()) {
            if (ch != ' ') {
                word += ch;
            }
        }
        return word;
    }

    public static void main(String[] args) {

        File file = new File("teams_participating.csv");

        String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/tilfjjea";
        String username = "tilfjjea";
        String password = "tC0sMMGx8EH6KyQ7CRBVEIUVzC82C8Zv";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();

            try (Scanner s = new Scanner(file);) {

                while (s.hasNext()) {
                    String[] words = s.nextLine().split(",");
                    String query = "";
                    
                    //Insert into participatesin (team name, tournament):
                    //query = "INSERT INTO participatesin VALUES ('"+words[0]+"','"+words[1]+"')";
                    
                    //Insert into Tournaments (name,prize,date, winner):
                    //query = "INSERT INTO tournaments VALUES ('"+words[0]+"','"+words[1]+"','"+words[2]+"','"+words[3]+"')";
                    
                    //Insert into playsOn (Nickname, team name):
                    //query = "INSERT INTO playson VALUES ('"+words[0]+"','"+words[2]+"')";
                    
                    
                    //Insert into coaches (nickname,team name):
//                    if (words.length > 2) {
//                        query = "INSERT INTO coaches VALUES ('" + words[2] + "','" + words[0] + "')";
//                    }

                    //For inserting all Teams (Name, Country) into Teams:
                    //query = "INSERT INTO teams VALUES('" + words[0] + "','" + words[1] + "')";
                    
                    //For inserting coaches into people( nickname, name, email):
//                    String query = "";
//                    if (words.length > 2) {
//                        query = "INSERT INTO people VALUES ('" + words[2] + "','" + words[3] + "','Coach@" + clean(words[0]) + ".com')";
//                    }


                    //For inserting all players into people (Nickname, name, email):
                    //String query = "insert into people values('" + words[0] + "','" + words[1] + "','" + words[0].substring(0, 2) + "@" + clean(words[2]) + ".com')";
                    try {
                        st.execute(query);
                        System.out.println(query);

                    } catch (org.postgresql.util.PSQLException ex) {
                        System.out.println("PSQL ERROR");
                    }

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlayerAdder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
