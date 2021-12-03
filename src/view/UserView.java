package view;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserView extends JPanel {
	
	private JButton bookingButton;
	private JButton cancelButton;
	
	public UserView() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		bookingButton = new JButton("Purchase Ticket");
		cancelButton = new JButton("Cancel Ticket");
		
		JLabel menuText = new JLabel("Main Menu");
		
		add(menuText);
		add(bookingButton);
		add(cancelButton);
	}
	
	public void addBookingListener(ActionListener l) {
		bookingButton.addActionListener(l);
	}
	
	public void addCancelListener(ActionListener l) {
		cancelButton.addActionListener(l);
	}
}
