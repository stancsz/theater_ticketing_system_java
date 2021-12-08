package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import bookingSystem.models.Movie;
import bookingSystem.models.Seat;
import bookingSystem.models.Showtime;
import bookingSystem.models.Theater;

@SuppressWarnings("serial")
public class BookingView extends JPanel {
	
	private JComboBox<Movie> movieBox;
	private JComboBox<Theater> theaterBox;
	private JComboBox<Showtime> showtimeBox;
	private JButton movieButton;
	private JButton theaterButton;
	private JButton showtimeButton;
	private JButton bookButton;
	private JPanel seatPanel;
	private ArrayList<JButton> seatButtons;
	private int selectedSeatIndex;
	private SeatButtonListener seatButtonListener;
	private JButton backButton;
	
	public BookingView() {
		setLayout(new BorderLayout());
		
		//Build top panel with dropdown menus to select movie, theater, and showtime.
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		movieBox = new JComboBox<Movie>();
		theaterBox = new JComboBox<Theater>();
		showtimeBox = new JComboBox<Showtime>();
		movieButton = new JButton("Choose Movie");
		theaterButton = new JButton("Choose Theater");
		showtimeButton = new JButton("Choose Showtime");
		topPanel.add(movieBox);
		topPanel.add(movieButton);
		topPanel.add(Box.createRigidArea(new Dimension(15,0)));
		topPanel.add(theaterBox);
		topPanel.add(theaterButton);
		topPanel.add(Box.createRigidArea(new Dimension(15,0)));
		topPanel.add(showtimeBox);
		topPanel.add(showtimeButton);
		movieBox.setEnabled(false);
		theaterBox.setEnabled(false);
		showtimeBox.setEnabled(false);
		add(topPanel, BorderLayout.NORTH);
		
		//Build bottom panel with legend for seat selection and "book seat" button
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		JLabel availableLabel = new JLabel("Available: ");
		JLabel selectedLabel = new JLabel("Selected: ");
		JLabel reservedLabel = new JLabel("Reserved: ");
		JButton availableSquare = new JButton("");
		availableSquare.setPreferredSize(new Dimension(20,20));
		availableSquare.setEnabled(false);
		JButton selectedSquare = new JButton("");
		selectedSquare.setPreferredSize(new Dimension(20,20));
		selectedSquare.setBackground(Color.GREEN);
		selectedSquare.setBorderPainted(false);
		selectedSquare.setEnabled(false);
		JButton reservedSquare = new JButton("");
		reservedSquare.setPreferredSize(new Dimension(20,20));
		reservedSquare.setBackground(Color.RED);
		reservedSquare.setBorderPainted(false);
		reservedSquare.setEnabled(false);		
		bookButton = new JButton("Book Seat");
		bookButton.setEnabled(false);
		backButton = new JButton("Back");
		JPanel legendPanel = new JPanel();
		legendPanel.add(availableLabel);
		legendPanel.add(Box.createRigidArea(new Dimension(5,0)));
		legendPanel.add(availableSquare);
		legendPanel.add(Box.createRigidArea(new Dimension(10,0)));
		legendPanel.add(selectedLabel);
		legendPanel.add(Box.createRigidArea(new Dimension(5,0)));
		legendPanel.add(selectedSquare);
		legendPanel.add(Box.createRigidArea(new Dimension(10,0)));
		legendPanel.add(reservedLabel);
		legendPanel.add(Box.createRigidArea(new Dimension(5,0)));
		legendPanel.add(reservedSquare);
		bottomPanel.add(Box.createRigidArea(new Dimension(5,0)));
		bottomPanel.add(backButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(50,0)));
		bottomPanel.add(legendPanel);
		bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
		bottomPanel.add(bookButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(5,0)));
		add(bottomPanel, BorderLayout.SOUTH);
		
		
		seatPanel = new JPanel(new GridLayout(0,10));
		add(seatPanel, BorderLayout.CENTER);
		seatButtonListener = new SeatButtonListener();
		selectedSeatIndex = -1;
	}
	
	public void populateMovies(ArrayList<Movie> movies) {
		movieBox.removeAllItems();
		depopulateTheaters();
		for (Movie movie : movies) {
			movieBox.addItem(movie);
		}
		movieBox.setEnabled(true);
	}
	
	public void populateTheaters(ArrayList<Theater> theaters) {
		theaterBox.removeAllItems();
		depopulateShowtimes();
		for (Theater theater : theaters) {
			theaterBox.addItem(theater);
		}
		theaterBox.setEnabled(true);
		theaterButton.setEnabled(true);
	}
	
	public void depopulateTheaters() {
		depopulateShowtimes();
		theaterBox.removeAllItems();
		theaterBox.setEnabled(false);
		theaterButton.setEnabled(false);
	}
	public void populateShowtimes(ArrayList<Showtime> showtimes) {
		showtimeBox.removeAllItems();
		depopulateSeats();
		for (Showtime showtime : showtimes) {
			showtimeBox.addItem(showtime);
		}
		showtimeBox.setEnabled(true);
		showtimeButton.setEnabled(true);
	}
	
	private void depopulateShowtimes() {
		depopulateSeats();
		showtimeBox.removeAllItems();
		showtimeBox.setEnabled(false);
		showtimeButton.setEnabled(false);
	}
	
	public void populateSeats(ArrayList<Seat> seats) {
		seatButtons = new ArrayList<JButton>();
		seatPanel.removeAll();
		selectedSeatIndex = -1;
		bookButton.setEnabled(false);
		for (Seat seat : seats) {
			JButton seatButton = new JButton("" + seat.getSeatNumber());
			if (!seat.isAvailable()) {
				seatButton.setEnabled(false);
				seatButton.setBackground(Color.RED);
				seatButton.setBorderPainted(false);
			}
			seatButton.addActionListener(seatButtonListener);
			seatButtons.add(seatButton);
			seatPanel.add(seatButton);
		}
	}
	
	private void depopulateSeats() {
		seatPanel.removeAll();
		bookButton.setEnabled(false);
	}
	
	public Movie getSelectedMovie() {
		return (Movie)movieBox.getSelectedItem();
	}
	
	public Theater getSelectedTheater() {
		return (Theater)theaterBox.getSelectedItem();
	}
	
	public Showtime getSelectedShowtime() {
		return (Showtime)showtimeBox.getSelectedItem();
	}
	
	public int getSelectedSeatNumber() {
		return Integer.parseInt(seatButtons.get(selectedSeatIndex).getText());
	}
	
	public void addButtonsListener(ActionListener l) {
		movieButton.addActionListener(l);
		theaterButton.addActionListener(l);
		showtimeButton.addActionListener(l);
		bookButton.addActionListener(l);
		backButton.addActionListener(l);
	}
	
	class SeatButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton seatButton = (JButton)e.getSource();
			int i = seatButtons.indexOf(seatButton);
			if (i == selectedSeatIndex) {
				seatButton.setBackground(bookButton.getBackground());
				seatButton.setBorderPainted(true);
				bookButton.setEnabled(false);
				selectedSeatIndex = -1;
			} else {
				if (selectedSeatIndex >= 0) {
					JButton oldSelection = seatButtons.get(selectedSeatIndex);
					oldSelection.setOpaque(true);
					oldSelection.setBackground(bookButton.getBackground());
					oldSelection.setBorderPainted(true);
				} else bookButton.setEnabled(true);
				
				seatButton.setBackground(Color.GREEN);
				seatButton.setBorderPainted(false);
				selectedSeatIndex = i;
			}
		}
	}
}
