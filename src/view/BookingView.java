package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import BookingSystemModel.Movie;
import BookingSystemModel.Showtime;
import BookingSystemModel.Theater;

@SuppressWarnings("serial")
public class BookingView extends JPanel {
	
	private JComboBox<Movie> movieBox;
	private JComboBox<Theater> theaterBox;
	private JComboBox<Showtime> showtimeBox;
	
	public BookingView() {
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		movieBox = new JComboBox<Movie>();
		theaterBox = new JComboBox<Theater>();
		showtimeBox = new JComboBox<Showtime>();
	}
	
	public void populateMovies(Movie[] movies) {
		for (Movie movie : movies) {
			movieBox.addItem(movie);
		}
	}
	
	public void populateTheaters(Theater[] theaters) {
		for (Theater theater : theaters) {
			theaterBox.addItem(theater);
		}
	}
	
	public void populateShowtimes(Showtime[] showtimes) {
		for (Showtime showtime : showtimes) {
			showtimeBox.addItem(showtime);
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
	
	public void addMovieBoxListener(ActionListener l) {
		movieBox.addActionListener(l);
	}
	
	public void addTheaterBoxListener(ActionListener l) {
		theaterBox.addActionListener(l);
	}
	
	public void addShowtimeBoxListener(ActionListener l) {
		showtimeBox.addActionListener(l);
	}
}
