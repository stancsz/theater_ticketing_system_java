package view;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CancellationView extends JPanel {
	
	private JTextField ticketNumField;
	private JLabel resultText;
	private JButton cancelTicketButton;
	
	public CancellationView() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JLabel cancelText = new JLabel("Cancel Ticket");
		JLabel enterTicketText = new JLabel("Enter Ticket Number: ");
		cancelTicketButton = new JButton("Cancel Ticket");
		resultText = new JLabel("");
		add(cancelText);
		add(enterTicketText);
		add(ticketNumField);
		add(cancelTicketButton);
		add(resultText);
	}
	
	public int getTicketNumber() {
		return Integer.parseInt(ticketNumField.getText());
	}
	
	public void confirmCancel(boolean result) {
		if (result) {
			resultText.setText("Ticket cancelled.");
		} else {
			resultText.setText("Ticket not found.");
		}
	}
	
	public void addCancelButtonListener(ActionListener l) {
		cancelTicketButton.addActionListener(l);
	}
}
