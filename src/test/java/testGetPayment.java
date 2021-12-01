import models.Payment;

import java.sql.*;
import java.util.HashMap;

public class testGetPayment {
    /**
     * This is an example on how to pull objects from jdbc and put it in hashmap
     * hashmap
     * https://www.w3schools.com/java/java_hashmap.asp
     * @return
     */
    public static HashMap<String, Payment> selectTest(Connection c) {
        HashMap<String, Payment> payments = new HashMap<String, Payment>();

        String sql = "SELECT paymentID, amount, userID, ticketID FROM payment";

        try (Connection conn = c;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("paymentID") + "\t" +
                        rs.getDouble("amount") + "\t" +
                        rs.getInt("userID") + "\t" +
                        rs.getInt("ticketID"));

                Payment object = new Payment(rs.getInt("paymentID"),
                        rs.getDouble("amount"),
                        rs.getInt("userID"),
                        rs.getInt("ticketID")
                        );
                System.out.println(object.toString());
                payments.put(String.valueOf(rs.getInt("paymentID")),
                        object);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return payments;
    }

    public static void main(String[] args) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        HashMap paymentHashMap = selectTest(c);
        System.out.println(paymentHashMap.toString());
    }
}
