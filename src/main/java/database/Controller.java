package database;

import dummyPackage.Ticket;
import dummyPackage.User;

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

    public static void insertIntoPayments(User user, Ticket ticket, Integer newID) throws SQLException {
        Connection c = Controller.getConnection();
        String sql = "INSERT INTO payment(paymentID, amount, userID, ticketID) " +
                String.format("Values (%o, %f, %o, %o)", newID, ticket.getPrice(), user.getId(), ticket.getId());
        System.out.println(sql);
        Connection conn = c;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public static void insertIntoCredits(Integer userId, double credit, String expiryDate) throws SQLException {
        Connection conn = Controller.getConnection();
        String sql = "INSERT INTO credits(UserID, Credit, expiryDate) " +
                String.format("Values (%o, %f, \"%s\");", userId, credit, expiryDate);
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
}