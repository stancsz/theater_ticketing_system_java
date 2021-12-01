package models;

/**
 * example:
 * https://www.javacodegeeks.com/2019/10/spring-mvc-binding-w-o-setters.html
 */
public class Payment {
    private final Integer paymentID;
    private final double amount;
    private final Integer userID;
    private final Integer ticketID;

    public Payment(Integer paymentID, double amount, Integer userID, Integer ticketID) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.userID = userID;
        this.ticketID = ticketID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", amount=" + amount +
                ", userID=" + userID +
                ", ticketID=" + ticketID +
                '}';
    }
}


