package database;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static database.Controller.deleteFromPayment;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void deleteFromPaymentTest() {
        try {
            int paymentID = 10;
            deleteFromPayment(paymentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}