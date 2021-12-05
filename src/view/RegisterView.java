package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class RegisterView extends JPanel {
	
	private JTextField emailField;
	private JTextField nameField;
	private JTextField addressField;
	private JButton registerButton;
	
	public RegisterView() {
		setLayout(new BorderLayout());
		
		JLabel registerLabel = new JLabel("Register for Subscription");
		add(registerLabel, BorderLayout.NORTH);
		
		JPanel bodyPanel = new JPanel(new GridLayout(0,2));
		JLabel emailLabel = new JLabel("Email: ");
		JLabel nameLabel = new JLabel("Name: ");
		JLabel addressLabel = new JLabel("Address: ");
		bodyPanel.add(emailLabel);
		bodyPanel.add(emailField);
		bodyPanel.add(nameLabel);
		bodyPanel.add(nameField);
		bodyPanel.add(addressLabel);
		bodyPanel.add(addressField);
		add(bodyPanel, BorderLayout.CENTER);
		
		registerButton = new JButton("Register");
		add(registerButton, BorderLayout.SOUTH);
	}
	
	public void addRegisterButtonListener(ActionListener l) {
		registerButton.addActionListener(l);
	}
	
}
