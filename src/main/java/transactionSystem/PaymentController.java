package transactionSystem;
import database.Controller;
import transactionSystem.models.Payment;

import java.sql.*;
import java.util.HashMap;

public class PaymentController {
    private HashMap<String, Payment> payments;

    public PaymentController() {
        this.payments = getPaymentModels();
    }

    public static HashMap<String, Payment> getPaymentModels() {
        Connection c = Controller.getConnection();
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

}
