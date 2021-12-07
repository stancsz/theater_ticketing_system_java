package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import bookingSystem.*;
import bookingSystem.models.Movie;
import bookingSystem.models.Showtime;
import bookingSystem.models.Theater;
import view.GUI;

public class ModelsController {
	
	private TestBookingController tbc;
	private GUI gui;
	private int userId = -1;
	private String userEmail = "";
	private int selectedSeat = -1;
	private double paymentAmount = 0;
	public static void main(String[] args) {
		ModelsController app = new ModelsController();
		app.run();
	}
	
	private void run() {
		gui = new GUI();
		tbc = new TestBookingController();
		UserButtonListener ubl = new UserButtonListener();
		gui.getUserView().addButtonsListener(ubl);
		
		BookingButtonListener bbl = new BookingButtonListener();
		gui.getBookingView().populateMovies(tbc.getAllMovies());
		gui.getBookingView().addButtonsListener(bbl);
		
		CancellationButtonListener cbl = new CancellationButtonListener();
		gui.getCancellationView().addButtonsListener(cbl);
		
		LoginButtonListener lbl = new LoginButtonListener();
		gui.getLoginView().addButtonsListener(lbl);
		
		RegisterButtonListener rbl = new RegisterButtonListener();
		gui.getRegisterView().addButtonsListener(rbl);
		
	}
	
	
	
	
	private void handleMovieEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		gui.getBookingView().populateTheaters(tbc.getAllTheaters(m));
	}
	
	private void handleTheaterEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		Theater t = gui.getBookingView().getSelectedTheater();
		gui.getBookingView().populateShowtimes(tbc.getAllShowtimes(m, t));
	}
	
	private void handleShowtimeEvent() {
		Movie m = gui.getBookingView().getSelectedMovie();
		Theater t = gui.getBookingView().getSelectedTheater();
		Showtime s = gui.getBookingView().getSelectedShowtime();
		gui.getBookingView().populateSeats(tbc.getAllSeats(m, t, s));
	}
	
	private boolean login() {
		String username = gui.getLoginView().getUsernameText();
		String password = gui.getLoginView().getPasswordText();
		userId = checkCredentials(username, password);
		if (userId >= 0) return true;
		return false;
	}
	
	private int checkCredentials(String username, String password) {
		//credential checking not implemented, but return User Id if correct, return -1 if incorrect
		return 1;
	}
	
	private void handleRegisterEvent() {
		String email = gui.getRegisterView().getEmailText();
		String password = gui.getRegisterView().getPasswordText();
		String name = gui.getRegisterView().getNameText();
		String address = gui.getRegisterView().getAddressText();
		//register user
		//if successful registration
		userEmail = email;
		System.out.println("Registered User: " + name);
		paymentAmount += 20.00;
		gui.setCard(6);
	}
	
	
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
				selectedSeat = gui.getBookingView().getSelectedSeatNumber();
				System.out.println("Selected Seat: " + selectedSeat);
				//add 
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
				//For some reason, the seat buttons don't update their visibility unless the card is swapped in and out
				gui.setCard(0);
				gui.setCard(1);
				break;
			case "Choose Showtime":
				handleShowtimeEvent();
				//For some reason, the seat buttons don't update their visibility unless the card is swapped in and out
				gui.setCard(0);
				gui.setCard(1);
				break;
			}	
			
		}
	}
	
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
				//cancel ticket
				break;
			}
		}
		
	}
	
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
					JOptionPane.showMessageDialog(gui, "Invalid credentials");
				}
			case "Continue as Guest":
				userEmail = gui.getLoginView().getUsernameText();
				if (userEmail.equals("")) {
					JOptionPane.showMessageDialog(gui, "Please Enter an Email");
				} else {
					gui.setCard(5);
				}
			}
		}
		
	}
	
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
}
