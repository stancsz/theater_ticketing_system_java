package transactionSystem;

import database.Controller;
import dummyPackage.Ticket;
import dummyPackage.User;
import transactionSystem.models.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
//                System.out.println(object.toString());
                payments.put(String.valueOf(rs.getInt("paymentID")),
                        object);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return payments;
    }

    public void charge(User user, Ticket ticket) {
        Set<Integer> ids = payments.keySet().stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet()); // getting int set
        Integer newID = Collections.max(ids)+1;
        System.out.println(newID);
        if (user.isRegistered() == true) {
//            payments.put();
        } else {
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentController that = (PaymentController) o;
        return Objects.equals(payments, that.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payments);
    }

    public void setPayments(HashMap<String, Payment> payments) {
        this.payments = payments;
    }

    public HashMap<String, Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return "PaymentController{" +
                "payments=" + payments +
                '}';
    }
}
