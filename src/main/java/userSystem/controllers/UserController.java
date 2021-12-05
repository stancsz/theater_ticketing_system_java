package main.java.userSystem.controllers;
import main.java.userSystem.models.*;
import java.util.ArrayList;

public class UserController{
    private ArrayList<RegisteredUser> listOfRegisteredUsers;
    private ArrayList<User> listOfUsers;
     
    public UserController(){

    }    
    public void registerUser(User user, String name, String address){
        RegisteredUser ru = new RegisteredUser(name, address, user.getListOfCredits(), user.getUserId(), user.getEMail(), user.getListOfCredits(), user.getListOfTickets()) 
    }
    public void addUser(String eMail){
        //TODO assign random unique user id
        User u = new User(userId, eMail);
        listOfUsers.add(u);
    }
    public void addTicket(User user, Ticket ticket){
        user.getListOfTickets().add(ticket);
    }

    public void removeTicket(User user, Ticket ticket){
        user.getListOfTickets().remove(ticket);
    }
    public void addRefundCredit(User user, Ticket ticket){
        // we dont have price of a ticket 
        Credit credit = new Credit(ticket.price, ticket.showtime);
        user.getListOfCredits().add(credit);
    }
    public void removeRefundCredit(User user, Ticket ticket){

        user.getListOfCredits().remove(credit);

    }
}
