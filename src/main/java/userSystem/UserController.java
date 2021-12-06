package userSystem;
import userSystem.models.*;
import java.util.ArrayList;
import java.sql.*;

public class UserController{
    private ArrayList<RegisteredUser> listOfRegisteredUsers;
    private ArrayList<User> listOfUsers;
     
    public UserController(){
        listOfRegisteredUsers = new ArrayList<RegisteredUser>();
        listOfUsers = new ArrayList<User>();
    }    

    public static void main(String[] args) {
        System.out.println("It works");
        UserController uc = new UserController();
        try {
            uc.loadUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() throws SQLException{
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        String sql = "SELECT FROM Users" +
                String.format("WHERE userID = %s ;", 1);

        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("UserID") +  "\t" + 
                               rs.getString("email") + "\t" +
                               rs.getString("name") + "\t" +
                               rs.getString("addres") + "\t" +
                               rs.getInt("isRegisterd"));
        }
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
