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
        try {
            loadAllUsers();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    

    public static void main(String[] args) {
        System.out.println("It works");
        UserController uc = new UserController();

        uc.deleteUser(3);
    }

    public void addRegisteredUser(int userId,String eMail, String name, String address){
        RegisteredUser ru = new RegisteredUser(name, address, userId, eMail);
        listOfRegisteredUsers.add(ru);
        try {
            addRegisteredUserToDB(userId, eMail, name, address);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void addUser(int userId, String eMail){
        User u = new User(userId, eMail);
        listOfUsers.add(u);
        try {
            addUserToDB(userId, eMail);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void deleteUser(int userId){
        //find and remove user object
        for (RegisteredUser ru: listOfRegisteredUsers){
            if(ru.getUserId()==userId){
                listOfRegisteredUsers.remove(ru);
                break;
            }
        }
        for (User u: listOfUsers){
            if(u.getUserId()==userId){
                listOfUsers.remove(u);
                break;
            }
        }
        //remove user from database
        try{
            deleteUserFromDB(userId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    //-----------------------------Methods to work with DB----------------------
    //  Connect to a sample database
    public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:sqlite.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void loadAllUsers() throws SQLException{
        Connection conn = connect();
        String sql = String.format("SELECT * FROM Users"); 

        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()) {
            int userID = rs.getInt("UserID");
            String email = rs.getString("email");
            String name = rs.getString("name");
            String address = rs.getString("name");
            Boolean isRegistered = rs.getBoolean("isRegistered");
            if(isRegistered){
                RegisteredUser ru = new RegisteredUser(name, address, userID, email);
                listOfRegisteredUsers.add(ru);
            }else{
                User u = new User(userID, email);
                listOfUsers.add(u);
            }
        }
        conn.close();
    }
    public void addUserToDB(int userId, String eMail) throws SQLException{
        Connection conn = connect();

        String sql = String.format("INSERT INTO users (UserID, email, isRegistered) VALUES (%d, '%s', 0)", userId, eMail); 
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate(sql);
        conn.close();
    }
    public void addRegisteredUserToDB(int userId, String eMail, String name, String address) throws SQLException{
        Connection conn = connect();

        String sql = String.format("INSERT INTO users (UserID, email, name, address, isRegistered) VALUES (%d, '%s', '%s', '%s', 1)", userId, eMail, name, address); 
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate(sql);
        conn.close();
    }
    //deletes both Registered and regular user from db
    public void deleteUserFromDB(int userId) throws SQLException{
        Connection conn = connect();

        String sql = String.format("DELETE FROM users WHERE UserID='%s'", userId); 
        Statement stmt = conn.createStatement();
        int rs = stmt.executeUpdate(sql);
        conn.close();
    }
}
