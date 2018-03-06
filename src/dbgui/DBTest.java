package dbgui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
 */
public class DBTest {

    private String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/tilfjjea";
    private String username = "tilfjjea";
    private String password = "tC0sMMGx8EH6KyQ7CRBVEIUVzC82C8Zv";

//    public void setUp() {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//        } catch (java.lang.ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

    private Statement connectToDB() {

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();
            return st;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List executeQuery(String query) {

        Statement st = connectToDB();
        List<String> l = new ArrayList();
        try {

            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            StringBuilder sb=new StringBuilder();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i < columnsNumber; i++) {
                    sb.append(rs.getString(i)).append(" ");
                }
                l.add(sb.toString());
                sb.setLength(0);
            }
            rs.close();
            st.close();
            System.out.println(sb.toString());
            return l;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
