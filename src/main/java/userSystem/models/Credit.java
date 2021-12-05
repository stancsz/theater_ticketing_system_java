package main.java.userSystem.models;

import java.util.Date;

public class Credit {
    private double credit;
    private Date expiryDate;

    public Credit(double credit, Date expiryDate) {
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
    
}
