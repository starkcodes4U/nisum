package com.nisum;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        // Set connection properties including timeouts
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Raghu@123");
        props.setProperty("autoReconnect", "true");
        props.setProperty("useSSL", "false");
        props.setProperty("allowPublicKeyRetrieval", "true");

        // Set timeout parameters to avoid connection issues
        props.setProperty("connectTimeout", "5000"); // 5 seconds to connect
        props.setProperty("socketTimeout", "30000"); // 30 seconds socket timeout

        String url = "jdbc:mysql://localhost:3306/ecomerce";
        Connection conn = DriverManager.getConnection(url, props);

        // Set auto-commit to true to avoid transaction locks
        conn.setAutoCommit(true);

        return conn;
    }

    // Close resources safely
    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    public static void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    public static void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }
}
