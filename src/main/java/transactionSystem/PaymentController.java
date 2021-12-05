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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static database.Controller.*;

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
        try {
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static HashMap<String, Credit> getCreditModels() {
        HashMap<String, Credit> credits = new HashMap<String, Credit>();
        Connection conn = Controller.getConnection();
        String sql = "SELECT userID, Credit, expiryDate FROM credits";
        try (
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
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credits;
    }

    public void charge(User user, Ticket ticket) throws SQLException {
        Set<Integer> ids = payments.keySet().stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet()); // getting int set
        Integer newID = Collections.max(ids) + 1; // get an new id for payment table

        Payment newPayment = new Payment(newID, ticket.getPrice(), user.getId(), ticket.getId());
        addPayment(newPayment);
    }

    public void addPayment(Payment newPayment) throws SQLException {
        Integer paymentID = newPayment.getPaymentID();
        double amount = newPayment.getAmount();
        Integer userID = newPayment.getUserID();
        Integer ticketID = newPayment.getTicketID();

        payments.put(String.valueOf(paymentID), newPayment);
        insertIntoPayment(paymentID, amount, userID, ticketID);
    }

    public void removePayment(Payment payment) {
        Integer paymentID = payment.getPaymentID();
        payments.remove(paymentID);
        try {
            deleteFromPayment(paymentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void refund(Payment payment) {
        Connection conn = Controller.getConnection();
        String sql = String.format("SELECT isRegistered FROM users where UserID = %o",
                payment.getUserID());
        double amount = 0;
        Statement stmt = null;
        boolean isRegistered = false;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            isRegistered = rs.getBoolean("isRegistered");
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isRegistered) {
            amount = payment.getAmount();
            System.out.println(String.format("Issuing $ %f refund to registered user %o", amount, payment.getUserID()));
        } else {
            amount = 0.85 * payment.getAmount();
            System.out.println(String.format("Issuing $ %f refund to non-registered user %o", amount, payment.getUserID()));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nyd = LocalDate.now().plusYears(1);
        String expiryDate = formatter.format(nyd);
//        System.out.println(expiryDate);
        try {
            insertIntoCredits(payment.getUserID(), amount, expiryDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Payment findPayment(Ticket ticket) {
        Connection conn = Controller.getConnection();
        String sql = "SELECT paymentID FROM payment " +
                String.format("WHERE ticketID = %s ;", ticket.getId());
        System.out.println(sql);
        Statement stmt = null;
        Integer paymentID = -1;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            paymentID = rs.getInt("paymentID");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Payment payment = payments.get(paymentID.toString());
        return payment;
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
