package transactionSystem;

import database.Controller;
import dummyPackage.Ticket;
import dummyPackage.User;
import transactionSystem.models.Credit;
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

import static database.Controller.insertIntoPayments;

public class PaymentController {
    private HashMap<String, Payment> payments;
    private HashMap<String, Credit> credits;

    public PaymentController() {
        this.payments = getPaymentModels();
        this.credits = getCreditModels();
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

    public static HashMap<String, Credit> getCreditModels() {
        Connection c = Controller.getConnection();
        HashMap<String, Credit> credits = new HashMap<String, Credit>();

        String sql = "SELECT userID, Credit, expiryDate FROM credits";

        try (Connection conn = c;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("userID") + "\t" +
                        rs.getDouble("Credit") + "\t" +
                        rs.getString("expiryDate"));

                Credit object = new Credit(rs.getInt("userID"),
                        rs.getDouble("Credit"),
                        rs.getString("expiryDate")
                );
//                System.out.println(object.toString());
                credits.put(String.valueOf(rs.getInt("userID")),
                        object);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return credits;
    }

    public void charge(User user, Ticket ticket) throws SQLException {
        Set<Integer> ids = payments.keySet().stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet()); // getting int set
        Integer newID = Collections.max(ids)+1; // get an new id for payment table
        payments.put(newID.toString(), new Payment(newID, ticket.getPrice(), user.getId(), ticket.getId()));
        insertIntoPayments(user, ticket, newID);
    }

    public void refund(Payment payment){
//        if (user.isRegistered() == true) {
//        } else {
//            payments.put()
//            insertIntoCredits();
//        }
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
                "\n, credits=" + credits +
                '}';
    }
}
