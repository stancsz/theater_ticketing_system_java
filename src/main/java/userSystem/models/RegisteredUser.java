package userSystem.models;

import java.util.ArrayList;
public class RegisteredUser extends User{
    private String name;
    private String address;
    private ArrayList<CreditCard> listOfCreditCards;
   

    public RegisteredUser(String name, String address, int userId, String eMail) {
        super(userId, eMail);
        this.name = name;
        this.address = address;
        this.listOfCreditCards = new ArrayList<CreditCard>();
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addCreditCard(CreditCard cc){
        this.listOfCreditCards.add(cc);
    }

    public ArrayList<CreditCard> getListOfCreditCards() {
        return this.listOfCreditCards;
    }

    public void setListOfCreditCards(ArrayList<CreditCard> listOfCreditCards) {
        this.listOfCreditCards = listOfCreditCards;
    }

}
