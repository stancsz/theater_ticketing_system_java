package bookingSystem;

import bookingSystem.models.Movie;
import bookingSystem.models.Seat;
import bookingSystem.models.Showtime;
import bookingSystem.models.Theater;
import bookingSystem.models.Ticket;
import transactionSystem.PaymentController;
import userSystem.UserController;
import view.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Central controller class for movie booking system.
 * Contains main method. Run this to run app.
 * @author David Cooksley
 *
 */
public class ModelsController {
	
	private BookingController bookingController;
	private UserController userController;
	private PaymentController paymentController;
	private GUI gui;
	private int userId = -1;
	private int ticketId = -1;
	private String userEmail = "";
	private int selectedSeat = -1;
	private double paymentAmount = 0;
	
	
	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		ModelsController app = new ModelsController();
		app.run();
	}
	
	/**
	 * Initializes booking, user, and payment controllers, and GUI.
	 * Initializes and Registers ActionListeners.
	 */
	private void run() {
		gui = new GUI();
		bookingController = new BookingController();
		userController = new UserController();
		paymentController = new PaymentController();
		UserButtonListener ubl = new UserButtonListener();
		gui.getUserView().addButtonsListener(ubl);
		
		BookingButtonListener bbl = new BookingButtonListener();
		gui.getBookingView().populateMovies(bookingController.getAllMovies());
		gui.getBookingView().addButtonsListener(bbl);
		
		CancellationButtonListener cbl = new CancellationButtonListener();
		gui.getCancellationView().addButtonsListener(cbl);
		
		LoginButtonListener lbl = new LoginButtonListener();
		gui.getLoginView().addButtonsListener(lbl);
		
		RegisterButtonListener rbl = new RegisterButtonListener();
		gui.getRegisterView().addButtonsListener(rbl);
		
		PaymentButtonListener pbl = new PaymentButtonListener();
		gui.getPaymentView().addButtonsListener(pbl);
		
	}
	
	/**
	 * Private ActionListener class for buttons in UserView page.
	 */
	private class UserButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			case "Purchase Ticket":
				gui.setCard(1);
				break;
			case "Cancel Ticket":
				gui.setCard(2);
				break;
			}
		}
	}
	
	
	
	/**
	 * Set of helper methods for handling events from BookingView page.
	 */
	private void handleMovieEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		gui.getBookingView().populateTheaters(bookingController.getAllTheaters(m));
	}
	private void handleTheaterEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		Theater t = gui.getBookingView().getSelectedTheater();
		gui.getBookingView().populateShowtimes(bookingController.getAllShowtimes(m, t));
	}
	private void handleShowtimeEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		Theater t = gui.getBookingView().getSelectedTheater();
		Showtime s = gui.getBookingView().getSelectedShowtime();
		gui.getBookingView().populateSeats(bookingController.getAllSeats(m, t, s));
	}
	private void handleBookEvent() {
		selectedSeat = gui.getBookingView().getSelectedSeatNumber();
		System.out.println("Selected Seat: " + selectedSeat);
		paymentAmount += Ticket.getPrice();
		gui.getPaymentView().setPaymentAmount(paymentAmount);
	}

	/**
	 * Private ActionListener class for buttons in BookingView page.
	 */
	private class BookingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			switch (command) {
			case "Back":
				gui.setCard(0);
				break;
			case "Book Seat":
				handleBookEvent();
				gui.setCard(4);
				break;
			case "Choose Movie":
				handleMovieEvent();
				//For some reason, the seat buttons don't update their visibility unless the card is swapped in and out
				gui.setCard(0);
				gui.setCard(1);
				break;
			case "Choose Theater":
				handleTheaterEvent();
				gui.setCard(0);
				gui.setCard(1);
				break;
			case "Choose Showtime":
				handleShowtimeEvent();
				gui.setCard(0);
				gui.setCard(1);
				break;
			}	
			
		}
	}
	
	/**
	 * Private ActionListener class for buttons on CancellationView page.
	 */
	private class CancellationButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			switch (command) {
			case "Back":
				gui.setCard(0);
				break;
			case "Cancel Ticket":
				try {
					int ticketNumber = gui.getCancellationView().getTicketNumber();
					Ticket ticket = bookingController.findTicket(ticketNumber);
					if ((ticket == null)||(ticket.getSeat().isAvailable())) {
						gui.getCancellationView().setResultText("Ticket not found.");
					} else {
						bookingController.cancelTicket(ticket);
						gui.getCancellationView().setResultText("Ticket " + ticketNumber + " cancelled.");
					}
				} catch (NumberFormatException ex) {
					gui.getCancellationView().setResultText("Invalid Ticket Number");
				}
				break;
			}
		}
		
	}
	
	/**
	 * Set of helper methods for handling events on LoginView page.
	 */
	private boolean login() {
		String username = gui.getLoginView().getUsernameText();
		String password = gui.getLoginView().getPasswordText();
		userId = checkCredentials(username, password);
		if (userId >= 0) {
			gui.getPaymentView().populateCreditCards(userController.getCreditCards(userId));
			return true;
		}
		return false;
	}
	private int checkCredentials(String username, String password) {
		//NOT IMPLEMENTED: credential checking 
		// return User Id if correct, return -1 if incorrect
		return 1;
	}
	
	/**
	 * Private ActionListener class for buttons on LoginView page.
	 */
	private class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			switch (command) {
			case "Back":
				gui.setCard(1);
				break;
			case "Register":
				gui.setCard(3);
				break;
			case "Login":
				if (login()) {
					userEmail = gui.getLoginView().getUsernameText();
					gui.setCard(5);
				} else {
					JOptionPane.showMessageDialog(gui, "Invalid credentials.");
				}
				break;
			case "Continue as Guest":
				userEmail = gui.getLoginView().getUsernameText();
				if (userEmail.equals("")) {
					JOptionPane.showMessageDialog(gui, "Please Enter an Email.");
				} else {
					userId = userController.addUser(userEmail);
					gui.getPaymentView().populateCreditCards(userController.getCreditCards(userId));
					gui.setCard(5);
				}
				break;
			}
		}
		
	}
	
	/**
	 * Helper method for events on RegisterView page.
	 */
	private void handleRegisterEvent() {
		String email = gui.getRegisterView().getEmailText();
		String password = gui.getRegisterView().getPasswordText();
		String name = gui.getRegisterView().getNameText();
		String address = gui.getRegisterView().getAddressText();
		userId = userController.addRegisteredUser(email, name, address);
		gui.getPaymentView().populateCreditCards(userController.getCreditCards(userId));
		if (userId >= 0) {
			userEmail = email;
			System.out.println("Registered User: " + name);
			paymentAmount += 20.00;
			gui.getPaymentView().setPaymentAmount(paymentAmount);
			gui.setCard(5);
		}
	}
	
	/**
	 * private ActionListener class for buttons on RegisterView page.
	 */
	private class RegisterButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			switch (command) {
			case "Back":
				gui.setCard(4);
				break;
			case "Register":
				handleRegisterEvent();
				break;
			}
		}
		
	}
	
	/**
	 * Helper method for events on PaymentView page.
	 */
	private void bookTicket() {
		Movie m = gui.getBookingView().getSelectedMovie();
		Theater t = gui.getBookingView().getSelectedTheater();
		Showtime s = gui.getBookingView().getSelectedShowtime();
		Ticket tic = bookingController.findTicket(m, t, s, new Seat(selectedSeat));
		bookingController.bookTicket(tic, userId);
		ticketId = tic.getTicketNumber();
	}
	
	/**
	 * Private ActionListener class for buttons on PaymentView page.
	 */
	private class PaymentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			switch (command) {
			case "Back":
				gui.setCard(4);
				break;
			case "Pay":
				String creditCardNumber = gui.getPaymentView().getCreditCardNumber();
				System.out.println(creditCardNumber);
				if ((creditCardNumber == null) || (creditCardNumber.length() < 16)) {
					JOptionPane.showMessageDialog(gui, "Please enter a valid credit card number.");
				} else {
					//NOT IMPLEMENTED: actually charging credit cards
					bookTicket();
					paymentController.charge(userId, ticketId);
					gui.getBookingView().depopulateTheaters();
					//NOT IMPLEMENTED: send email with ticket and receipt
					gui.setCard(0);
					JOptionPane.showMessageDialog(gui, "Ticket number " + ticketId + " Booked.");
				}
				break;
			}
			
		}
		
	}
}
