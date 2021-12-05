package transactionSystem.models;

public class Credit {
    private Integer UserID;
    private Double Credit;
    private String expiryDate;

    public Integer getUserID() {
        return UserID;
    }

    public Credit(Integer userID, Double credit, String expiryDate) {
        UserID = userID;
        Credit = credit;
        this.expiryDate = expiryDate;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Double getCredit() {
        return Credit;
    }

    public void setCredit(Double credit) {
        Credit = credit;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
