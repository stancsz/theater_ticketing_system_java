package dummyPackage;

import java.util.Date;


// TODO: integrate this dummy class
public class CreditDummy2 {
    private double credit;
    private Date expiryDate;

    public CreditDummy2(double credit, Date expiryDate) {
        this.credit = credit;
        this.expiryDate = expiryDate;
    }

    public double getCredit() {
        return this.credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public String toString() {
        return "Credit: " +credit+"expiryDate: "+expiryDate;
    }
    
}
