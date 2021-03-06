package dbgui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
 */
public class DBTest {

    private String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/tilfjjea";
    private String username = "tilfjjea";
    private String password = "tC0sMMGx8EH6KyQ7CRBVEIUVzC82C8Zv";
    private Statement st = connectToDB();

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
        ConnectionTest tests = new ConnectionTest(username, password, url);
        System.out.println("Connection to DB Test: " + tests.connectionTest());

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            st = db.createStatement();
            return st;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List executeQuery(String query) {
        List<String> l = new ArrayList();
        try (ResultSet rs = st.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            StringBuilder sb = new StringBuilder();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    sb.append(rs.getString(i)).append("\t");
                }
                l.add(sb.toString());
                sb.setLength(0);
            }
            QueryResultTest test = new QueryResultTest(l);
            System.out.println(test.checkQueryResult());
            rs.close();
            return l;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
