package multiplechoiceserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    static Connection conn = null;

    public static Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/gamedb";
            String usr = "root";
            String password = "";
            conn = DriverManager.getConnection(url, usr, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return conn;
    }
}
