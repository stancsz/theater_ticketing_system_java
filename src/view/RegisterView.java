package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RegisterView extends JPanel {
	
	private JTextField emailField;
	private JTextField nameField;
	private JTextField addressField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JButton backButton;
	
	public RegisterView() {
		setLayout(new BorderLayout());
		
		JLabel registerLabel = new JLabel("Register for Subscription");
		registerLabel.setFont(new Font(registerLabel.getFont().getFontName(),Font.PLAIN,36));
		JPanel registerLabelPanel = new JPanel();
		registerLabelPanel.add(registerLabel);
		add(registerLabelPanel, BorderLayout.NORTH);
		
		JPanel bodyPanel = new JPanel(new GridLayout(0,2));
		JLabel emailLabel = new JLabel("Email: ");
		emailField = new JTextField(20);
		JLabel passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField(20);
		JLabel nameLabel = new JLabel("Name: ");
		nameField = new JTextField(20);
		JLabel addressLabel = new JLabel("Address: ");
		addressField = new JTextField(20);
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.add(emailLabel);
		JPanel emailFieldPanel = new JPanel();
		emailFieldPanel.add(emailField);
		JPanel passwordLabelPanel = new JPanel();
		passwordLabelPanel.add(passwordLabel);
		JPanel passwordFieldPanel = new JPanel();
		passwordFieldPanel.add(passwordField);
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.add(nameLabel);
		JPanel nameFieldPanel = new JPanel();
		nameFieldPanel.add(nameField);
		JPanel addressLabelPanel = new JPanel();
		addressLabelPanel.add(addressLabel);
		JPanel addressFieldPanel = new JPanel();
		addressFieldPanel.add(addressField);
		bodyPanel.setBorder(new EmptyBorder(new Insets(220,250,220,220)));
		bodyPanel.add(emailLabelPanel);
		bodyPanel.add(emailFieldPanel);
		bodyPanel.add(passwordLabelPanel);
		bodyPanel.add(passwordFieldPanel);
		bodyPanel.add(nameLabelPanel);
		bodyPanel.add(nameFieldPanel);
		bodyPanel.add(addressLabelPanel);
		bodyPanel.add(addressFieldPanel);
		add(bodyPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		backButton = new JButton("Back");
		registerButton = new JButton("Register");
		bottomPanel.add(backButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(100,0)));
		bottomPanel.add(registerButton, BorderLayout.SOUTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void addButtonsListener(ActionListener l) {
		registerButton.addActionListener(l);
		backButton.addActionListener(l);
	}
	
	public String getEmailText() {
		return emailField.getText();
	}
	
	public String getNameText() {
		return nameField.getText();
	}
	
	public String getAddressText() {
		return addressField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPasswordText() {
		return passwordField.getText();
	}
	
}
