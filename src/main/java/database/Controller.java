package database;

import java.sql.*;

/**
 * binding object to jdbc src
 * https://docs.oracle.com/cd/E19182-01/821-1069/6nm3256a2/index.html
 */

public class Controller {
    public static void main( String args[] ) {
        createSQLiteDB();
    }
    private static void createSQLiteDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }
}