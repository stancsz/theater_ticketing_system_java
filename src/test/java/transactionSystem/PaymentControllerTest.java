package transactionSystem;

import dummyPackage.Ticket;
import dummyPackage.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transactionSystem.models.Payment;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {
    @Test
    void getCreditModels() {
        PaymentController paymentControllerTest = new PaymentController();
        System.out.println(paymentControllerTest);
    }

    @Test
    void charge() throws SQLException {
        PaymentController paymentControllerTest = new PaymentController();
        paymentControllerTest.charge(new User(), new Ticket());
    }

    @Test
    void refund() {
        PaymentController refundTest = new PaymentController();
        refundTest.refund(new Payment(2, 20, 1, 6));

    }
}