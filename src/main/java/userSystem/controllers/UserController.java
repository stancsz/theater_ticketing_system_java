package main.java.userSystem.controllers;
import main.java.userSystem.models.*;
import java.util.ArrayList;

public class UserController{
    private ArrayList<RegisteredUser> listOfRegisteredUsers;
    private ArrayList<User> listOfUsers;
     
    public UserController(){
        listOfRegisteredUsers = new ArrayList<RegisteredUser>();
        listOfUsers = new ArrayList<User>();
    }    

    public void registerUser(String name, String address, int userId, String eMail){
        RegisteredUser ru = new RegisteredUser(name, address, userId, eMail);
        listOfRegisteredUsers.add(ru);
    }
    public void addUser(int userId, String eMail){
        User u = new User(userId, eMail);
        listOfUsers.add(u);
    }
    public void addTicket(User user, Ticket ticket){
        user.getListOfTickets().add(ticket);
    }

    public void removeTicket(User user, Ticket ticket){
        user.getListOfTickets().remove(ticket);
    }
    // public void addRefundCredit(User user, Ticket ticket){
    //     // we dont have price of a ticket 
    //     Credit credit = new Credit(ticket.getPrice(), ticket.getShowtime().getStartTime());
    //     user.getListOfCredits().add(credit);
    // }

    public ArrayList<RegisteredUser> getListOfRegisteredUsers() {
        return this.listOfRegisteredUsers;
    }

    public void setListOfRegisteredUsers(ArrayList<RegisteredUser> listOfRegisteredUsers) {
        this.listOfRegisteredUsers = listOfRegisteredUsers;
    }

    public ArrayList<User> getListOfUsers() {
        return this.listOfUsers;
    }

    public void setListOfUsers(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

}
