package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import BookingSystemModel.Movie;
import BookingSystemModel.Seat;
import BookingSystemModel.Showtime;
import BookingSystemModel.Theater;

@SuppressWarnings("serial")
public class BookingView extends JPanel {
	
	private JComboBox<Movie> movieBox;
	private JComboBox<Theater> theaterBox;
	private JComboBox<Showtime> showtimeBox;
	private JButton bookButton;
	private JPanel seatPanel;
	private GridLayout seatLayout;
	private ArrayList<JButton> seatButtons;
	private int selectedSeatIndex;
	private SeatButtonListener seatButtonListener;
	
	public BookingView() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		movieBox = new JComboBox<Movie>();
		theaterBox = new JComboBox<Theater>();
		showtimeBox = new JComboBox<Showtime>();
		topPanel.add(movieBox);
		topPanel.add(theaterBox);
		topPanel.add(showtimeBox);
		
		bookButton = new JButton("Book Seat");
		add(topPanel, BorderLayout.NORTH);
		add(bookButton, BorderLayout.SOUTH);
		bookButton.setEnabled(false);
		
		seatLayout = new GridLayout(0,10);
		seatPanel = new JPanel(seatLayout);
		seatButtonListener = new SeatButtonListener();
		selectedSeatIndex = -1;
	}
	
	public void populateMovies(ArrayList<Movie> movies) {
		movieBox.removeAllItems();
		for (Movie movie : movies) {
			movieBox.addItem(movie);
		}
	}
	
	public void populateTheaters(ArrayList<Theater> theaters) {
		theaterBox.removeAllItems();
		for (Theater theater : theaters) {
			theaterBox.addItem(theater);
		}
	}
	
	public void populateShowtimes(ArrayList<Showtime> showtimes) {
		showtimeBox.removeAllItems();
		for (Showtime showtime : showtimes) {
			showtimeBox.addItem(showtime);
		}
	}
	
	public void populateSeats(ArrayList<Seat> seats) {
		seatButtons = new ArrayList<JButton>();
		seatPanel.removeAll();
		for (Seat seat : seats) {
			JButton seatButton = new JButton("" + seat.getSeatNumber());
			if (!seat.isAvailable()) {
				seatButton.setEnabled(false);
				seatButton.setBackground(Color.RED);
				seatButton.setOpaque(true);
				seatButton.setBorderPainted(false);
			}
			seatButton.addActionListener(seatButtonListener);
			seatButtons.add(seatButton);
			seatPanel.add(seatButton);
		}
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
	public void addMovieBoxListener(ActionListener l) {
		movieBox.addActionListener(l);
	}
	
	public void addTheaterBoxListener(ActionListener l) {
		theaterBox.addActionListener(l);
	}
	
	public void addShowtimeBoxListener(ActionListener l) {
		showtimeBox.addActionListener(l);
	}
	
	public void addBookListener(ActionListener l) {
		bookButton.addActionListener(l);
	}
	
	class SeatButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton seatButton = (JButton)e.getSource();
			int i = seatButtons.indexOf(seatButton);
			if (i == selectedSeatIndex) {
				seatButton.setOpaque(true);
				seatButton.setBackground(bookButton.getBackground());
				seatButton.setBorderPainted(true);
				bookButton.setEnabled(false);
				selectedSeatIndex = -1;
			} else {
				if (selectedSeatIndex > 0) {
					JButton oldSelection = seatButtons.get(selectedSeatIndex);
					oldSelection.setOpaque(true);
					oldSelection.setBackground(bookButton.getBackground());
					oldSelection.setBorderPainted(true);
				} else bookButton.setEnabled(true);
				
				seatButton.setBackground(Color.GREEN);
				seatButton.setOpaque(true);
				seatButton.setBorderPainted(false);
				selectedSeatIndex = i;
			}
		}
	}
}
