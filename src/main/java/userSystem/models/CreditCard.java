package userSystem.models;
public class CreditCard {
    private String cardholderName;
    private String cardholderAddress;
    private String creditCardNumber;
    private String expiryDate;
    private int ccv;

    public CreditCard(String cardholderName, String cardholderAddress, String creditCardNumber, String expiryDate, int ccv) {
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

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCcv() {
        return this.ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }
    @Override
    public String toString() {
        return "name="+cardholderName
            +" CCnumber="+creditCardNumber
            +" Address="+cardholderAddress
            +" ExpiryDate="+expiryDate
            +" ccv="+ccv;
    }
}

