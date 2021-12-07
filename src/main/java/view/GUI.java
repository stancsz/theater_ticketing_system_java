package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Insets;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	private UserView userView;
	private BookingView bookingView;
	private CancellationView cancellationView;
	private RegisterView registerView;
	private LoginView loginView;
	private PaymentView paymentView;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private static final String USER_STRING = "Main Menu";
	private static final String BOOKING_STRING = "Purchase Ticket";
	private static final String CANCEL_STRING = "Cancel Ticket";
	private static final String REGISTER_STRING = "Register";
	private static final String LOGIN_STRING = "Login";
	private static final String PAYMENT_STRING = "Payment";
	
	public GUI() {
		super("Movie Booking System");
		
		userView = new UserView();
		bookingView = new BookingView();
		cancellationView = new CancellationView();
		registerView = new RegisterView();
		loginView = new LoginView();
		paymentView = new PaymentView();
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBorder(new EmptyBorder(new Insets(3,3,3,3)));
		cardPanel.add(userView,USER_STRING);
		cardPanel.add(bookingView,BOOKING_STRING);
		cardPanel.add(cancellationView, CANCEL_STRING);
		cardPanel.add(registerView,REGISTER_STRING);
		cardPanel.add(loginView, LOGIN_STRING);
		cardPanel.add(paymentView,PAYMENT_STRING);
		this.add(cardPanel);
		setCard(0);
		setSize(900,700);
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
		case 4:
			cardLayout.show(cardPanel, LOGIN_STRING);
			break;
		case 5:
			cardLayout.show(cardPanel, PAYMENT_STRING);
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
	
	public LoginView getLoginView() {
		return loginView;
	}
	
	public PaymentView getPaymentView() {
		return paymentView;
	}
}
