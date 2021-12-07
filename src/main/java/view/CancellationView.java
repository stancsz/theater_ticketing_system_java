package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CancellationView extends JPanel {
	
	private JTextField ticketNumField;
	private JLabel resultText;
	private JButton cancelTicketButton;
	private JButton backButton;
	
	public CancellationView() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.setBorder(new EmptyBorder(new Insets(20,200,20,200)));
		JLabel cancelText = new JLabel("Cancel Ticket");
		cancelText.setFont(new Font(cancelText.getFont().getName(), Font.PLAIN, 36));
		JLabel enterTicketText = new JLabel("Enter Ticket Number: ");
		ticketNumField = new JTextField(20);
		ticketNumField.setMaximumSize(new Dimension(600,25));
		cancelTicketButton = new JButton("Cancel Ticket");
		resultText = new JLabel("");
		backButton = new JButton("Back");
		JPanel cancelTextPanel = new JPanel();
		cancelTextPanel.add(cancelText);
		JPanel enterTicketTextPanel = new JPanel();
		enterTicketTextPanel.add(enterTicketText);
		JPanel ticketNumFieldPanel = new JPanel();
		ticketNumFieldPanel.add(ticketNumField);
		JPanel cancelTicketButtonPanel = new JPanel();
		cancelTicketButtonPanel.add(cancelTicketButton);
		JPanel resultTextPanel = new JPanel();
		resultTextPanel.add(resultText);
		JPanel backButtonPanel = new JPanel();
		backButtonPanel.add(backButton);
		add(cancelTextPanel);
		add(Box.createRigidArea(new Dimension(0,200)));
		add(enterTicketTextPanel);
		add(ticketNumFieldPanel);
		add(cancelTicketButtonPanel);
		add(resultTextPanel);
		add(Box.createRigidArea(new Dimension(0,150)));
		add(backButtonPanel);
	}
	
	public int getTicketNumber() {
		return Integer.parseInt(ticketNumField.getText());
	}
	
	public void setResultText(String text) {
			resultText.setText(text);
	}
	
	public void addButtonsListener(ActionListener l) {
		cancelTicketButton.addActionListener(l);
		backButton.addActionListener(l);
	}
}
