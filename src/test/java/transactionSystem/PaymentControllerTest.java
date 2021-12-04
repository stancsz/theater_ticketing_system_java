package transactionSystem;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {
    @Test
    void testContstructor(){
        PaymentController paymentControllerTest = new PaymentController();
        System.out.println(paymentControllerTest.toString());
    }

}