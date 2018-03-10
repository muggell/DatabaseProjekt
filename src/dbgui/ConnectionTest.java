package dbgui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

    private Connection db;
    private String username = null;
    private String password = null;
    private String url = null;

    ConnectionTest(String user, String pass, String url) {
        this.password = pass;
        this.url = url;
        this.username = user;
    }

    public String connectionTest() {
        if (password != null && username != null && url != null) {
            System.out.println("All values were set.");
        }
        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Connection did not succeed.");
        }
        try {
            Statement st = db.createStatement();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Statement could not be created.");
        }
        try {
            db.close();
        } catch (SQLException ex) {
            System.out.println("Connection could not be closed.");
        }
        return "Completed without errors.";
    }
}
