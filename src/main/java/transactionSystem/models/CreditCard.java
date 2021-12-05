package transactionSystem.models;

import java.util.Date;

public class CreditCard {
    private String cardHolderName;
    private String getCardHolderAddress;
    private String creditCardNumber;
    private Date expiryDate;
    private Integer cvv;

    public CreditCard(String cardHolderName, String getCardHolderAddress, String creditCardNumber, Date expiryDate, Integer cvv) {
        this.cardHolderName = cardHolderName;
        this.getCardHolderAddress = getCardHolderAddress;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getGetCardHolderAddress() {
        return getCardHolderAddress;
    }

    public void setGetCardHolderAddress(String getCardHolderAddress) {
        this.getCardHolderAddress = getCardHolderAddress;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
}
