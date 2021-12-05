package main.java.userSystem.models;
import java.util.Date;
public class CreditCard {
    private String cardholderName;
    private String cardholderAddress;
    private String creditCardNumber;
    private Date expiryDate;
    private int ccv;

    public CreditCard(String cardholderName, String cardholderAddress, String creditCardNumber, Date expiryDate, int ccv) {
        this.cardholderName = cardholderName;
        this.cardholderAddress = cardholderAddress;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
    }

    public String getCardholderName() {
        return this.cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderAddress() {
        return this.cardholderAddress;
    }

    public void setCardholderAddress(String cardholderAddress) {
        this.cardholderAddress = cardholderAddress;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCcv() {
        return this.ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

}

