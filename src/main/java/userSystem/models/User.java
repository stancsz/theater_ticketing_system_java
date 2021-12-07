package userSystem.models;

import dummyPackage.CreditDummy2;
import dummyPackage.TicketDummy2;

import java.util.ArrayList;
public class User {
    protected int userId;
    protected String eMail;
    protected ArrayList<CreditDummy2> listOfCredits;
    protected ArrayList<TicketDummy2> listOfTickets;

    public User(int userId, String eMail) {
        setUserId(userId);
        setEMail(eMail);
        this.listOfCredits = new ArrayList<CreditDummy2>();
        this.listOfTickets = new ArrayList<TicketDummy2>();
    }

    public User(int userId, String eMail, ArrayList<CreditDummy2> listOfCredits, ArrayList<TicketDummy2> listOfTickets) {
        setUserId(userId);
        setEMail(eMail);
        setListOfCredits(listOfCredits);
        setListOfTickets(listOfTickets);
    }

    public double getTotalCreditAmount(){
        double totalCredits =0;
        for (CreditDummy2 c: listOfCredits){
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

    public void addCredit(CreditDummy2 credit){
        this.listOfCredits.add(credit);
    }

    public ArrayList<CreditDummy2> getListOfCredits() {
        return this.listOfCredits;
    }

    public void setListOfCredits(ArrayList<CreditDummy2> listOfCredits) {
        this.listOfCredits = listOfCredits;
    }

    public void addTicket(TicketDummy2 ticket){
        this.listOfTickets.add(ticket);
    }

    public ArrayList<TicketDummy2> getListOfTickets() {
        return this.listOfTickets;
    }

    public void setListOfTickets(ArrayList<TicketDummy2> listOfTickets) {
        this.listOfTickets = listOfTickets;
    }
    @Override
    public String toString(){
        return "userID="+getUserId()+"email="+getEMail();
    }
    
}
