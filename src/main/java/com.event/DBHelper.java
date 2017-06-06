package com.event;

import java.sql.*;


public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/dbevent";
    private static final String USENAME = "root";
    private static final String PASSWORD = "#OOP25571project";

    private static Connection connection;

    public DBHelper(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection= DriverManager.getConnection(URL,USENAME,PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public   Connection getConnection() {
        return connection;
    }

    public   ResultSet login( String login, String pass) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT  * FROM  user WHERE login = ? AND  pass = ?";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, login);
        statement.setString(2, pass);
        return statement.executeQuery();
    }

}
