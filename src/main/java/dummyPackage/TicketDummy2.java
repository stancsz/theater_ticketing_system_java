package dummyPackage;

// TODO: integrate this dummy class with real ticket class
//temp ticket 
public class TicketDummy2 {
    private int ticketId; 
    private double price; 
    public TicketDummy2(int ticketId){
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
