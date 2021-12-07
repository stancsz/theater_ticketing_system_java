package userSystem.models;

import java.util.ArrayList;
public class User {
    protected int userId;
    protected String eMail;
    protected ArrayList<Credit> listOfCredits;    
    protected ArrayList<Ticket> listOfTickets;    

    public User(int userId, String eMail) {
        setUserId(userId);
        setEMail(eMail);
        this.listOfCredits = new ArrayList<Credit>();
        this.listOfTickets = new ArrayList<Ticket>();
    }

    public User(int userId, String eMail, ArrayList<Credit> listOfCredits, ArrayList<Ticket> listOfTickets) {
        setUserId(userId);
        setEMail(eMail);
        setListOfCredits(listOfCredits);
        setListOfTickets(listOfTickets);
    }

    public double getTotalCreditAmount(){
        double totalCredits =0;
        for (Credit c: listOfCredits){
            //TODO check if expired
            //
            totalCredits=+c.getCredit();
        }
        return totalCredits;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEMail() {
        return this.eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void addCredit(Credit credit){
        this.listOfCredits.add(credit);
    }

    public ArrayList<Credit> getListOfCredits() {
        return this.listOfCredits;
    }

    public void setListOfCredits(ArrayList<Credit> listOfCredits) {
        this.listOfCredits = listOfCredits;
    }

    public void addTicket(Ticket ticket){
        this.listOfTickets.add(ticket);
    }

    public ArrayList<Ticket> getListOfTickets() {
        return this.listOfTickets;
    }

    public void setListOfTickets(ArrayList<Ticket> listOfTickets) {
        this.listOfTickets = listOfTickets;
    }
    @Override
    public String toString(){
        return "userID="+getUserId()+"email="+getEMail();
    }
    
}
