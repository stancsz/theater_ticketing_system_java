package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserView extends JPanel {
	
	private JButton bookingButton;
	private JButton cancelButton;
	
	public UserView() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setAlignmentX(CENTER_ALIGNMENT);
		
		bookingButton = new JButton("Purchase Ticket");
		bookingButton.setFont(new Font(bookingButton.getFont().getFontName(), Font.PLAIN, 36));
		cancelButton = new JButton("Cancel Ticket");
		cancelButton.setFont(new Font(cancelButton.getFont().getFontName(), Font.PLAIN, 36));
		
		JLabel menuText = new JLabel("Main Menu");
		menuText.setFont(new Font(menuText.getFont().getFontName(), Font.PLAIN, 36));
		JPanel menuTextPanel = new JPanel();
		menuTextPanel.add(menuText);
		JPanel bookingButtonPanel = new JPanel();
		bookingButtonPanel.add(bookingButton);
		JPanel cancelButtonPanel = new JPanel();
		cancelButtonPanel.add(cancelButton);
		add(menuTextPanel);
		add(bookingButtonPanel);
		add(cancelButtonPanel);
	}
	
	public void addButtonsListener(ActionListener l) {
		bookingButton.addActionListener(l);
		cancelButton.addActionListener(l);
	}
	
}
