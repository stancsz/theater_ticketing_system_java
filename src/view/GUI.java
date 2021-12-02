package view;


import javax.swing.*;
import java.awt.CardLayout;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	private UserView userView;
	private BookingView bookingView;
	private CancellationView cancellationView;
	private RegisterView registerView;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private static final String USER_STRING = "Main Menu";
	private static final String BOOKING_STRING = "Purchase Ticket";
	private static final String CANCEL_STRING = "Cancel Ticket";
	private static final String REGISTER_STRING = "Register";
	
	public GUI() {
		super("Movie Booking System");
		
		userView = new UserView();
		bookingView = new BookingView();
		cancellationView = new CancellationView();
		registerView = new RegisterView();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(userView,USER_STRING);
		cardPanel.add(bookingView,BOOKING_STRING);
		cardPanel.add(cancellationView, CANCEL_STRING);
		cardPanel.add(registerView,REGISTER_STRING);
		this.add(cardPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setCard(int i) {
		switch (i) {
		case 0:
			cardLayout.show(cardPanel, USER_STRING);
			break;
		case 1:
			cardLayout.show(cardPanel, BOOKING_STRING);
			break;
		case 2:
			cardLayout.show(cardPanel, CANCEL_STRING);
			break;
		case 3:
			cardLayout.show(cardPanel, REGISTER_STRING);
			break;
		}
	}
	
	public UserView getUserView() {
		return userView;
	}

	public BookingView getBookingView() {
		return bookingView;
	}

	public CancellationView getCancellationView() {
		return cancellationView;
	}

	public RegisterView getRegisterView() {
		return registerView;
	}
}
