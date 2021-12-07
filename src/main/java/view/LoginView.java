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
public class LoginView extends JPanel {
	
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton;
	JButton guestButton;
	JButton registerButton;
	JButton backButton;
	
	public LoginView() {
		setLayout(new BorderLayout());
		setAlignmentX(CENTER_ALIGNMENT);
		JLabel loginLabel = new JLabel("Sign In");
		loginLabel.setFont(new Font(loginLabel.getFont().getFontName(),Font.PLAIN,36));
		JPanel topPanel = new JPanel();
		topPanel.add(loginLabel);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridLayout(2,2));
		centerPanel.setBorder(new EmptyBorder(new Insets(265,270,265,270)));
		JLabel usernameLabel = new JLabel("Email: ");
		JLabel passwordLabel = new JLabel("Password: ");
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		backButton = new JButton("Back");
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		guestButton = new JButton("Continue as Guest");
		bottomPanel.add(backButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(250,0)));
		bottomPanel.add(loginButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(100,0)));
		bottomPanel.add(registerButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(182,0)));
		bottomPanel.add(guestButton);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public String getUsernameText() {
		return usernameField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPasswordText() {
		return passwordField.getText();
	}
	
	public void addButtonsListener(ActionListener l) {
		loginButton.addActionListener(l);
		guestButton.addActionListener(l);
		registerButton.addActionListener(l);
		backButton.addActionListener(l);
	}
	
	
}
