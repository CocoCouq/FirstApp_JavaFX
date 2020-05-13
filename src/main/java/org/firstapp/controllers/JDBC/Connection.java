package org.firstapp.controllers.JDBC;

import java.sql.DriverManager;

/**
 * Connection class
 */
public class Connection {
    // User variables
    private final String url;
    private final String user;
    private final String pwd;

    /**
     * Constructor with db name param
     * @param db : String of db name
     */
    public Connection(String db) {
        this.url = "jdbc:mysql://localhost:8889/"+db+"?serverTimezone=UTC";
        this.user = "root";
        this.pwd = "root";
    }

    /**
     * Connect to db
     * @return The connection to db
     */
    public java.sql.Connection connection() {
        try
        {
            // Connection
            java.sql.Connection con = DriverManager.getConnection(this.url, this.user, this.pwd);
            System.out.println("DB connected");
            return con;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
