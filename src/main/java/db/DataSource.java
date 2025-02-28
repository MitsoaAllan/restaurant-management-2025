package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static String url;
    private final String username = System.getenv("USERNAME");
    private final String password = System.getenv("PASSWORD");
    private final String host = System.getenv("HOST");
    private final String dbname = System.getenv("DB_NAME");
    private final int port = Integer.parseInt(System.getenv("PORT"));

    public DataSource() {
        url = "jdbc:postgresql://"+host+":"+port+"/"+dbname;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
