package com.ss.utopia.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class ConnectionUtil {

    /**
     * Creates a connection based on the credentials from db.properties
     *
     * @return connection from db.properties
     */
    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream input = new FileInputStream("src/main/resources/db.properties");
        Properties prop = new Properties();
        prop.load(input);

        Class.forName(prop.getProperty("driver"));
        Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        conn.setAutoCommit(false);
        return conn;
    }

}
