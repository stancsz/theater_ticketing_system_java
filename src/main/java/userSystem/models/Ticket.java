package userSystem.models;
//temp ticket 
public class Ticket {
    private int ticketId; 
    private double price; 
    public Ticket(int ticketId){
        setTicketId(ticketId);
    }

    public int getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
