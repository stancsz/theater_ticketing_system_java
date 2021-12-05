package transactionSystem;

import dummyPackage.Ticket;
import dummyPackage.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPaymentModels() {
        PaymentController paymentControllerTest = new PaymentController();
        System.out.println(paymentControllerTest);
    }

    @Test
    void testGetPaymentModels() {
    }

    @Test
    void charge() {
        PaymentController paymentControllerTest = new PaymentController();
        paymentControllerTest.charge(new User(), new Ticket());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void setPayments() {
    }

    @Test
    void getPayments() {
    }

    @Test
    void testToString() {
    }
}