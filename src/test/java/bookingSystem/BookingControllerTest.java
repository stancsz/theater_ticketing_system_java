package bookingSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
    @Test
    void BookingControllerConstructorTest(){
        BookingController bookingController = new BookingController();
        System.out.println(bookingController);
    }

}