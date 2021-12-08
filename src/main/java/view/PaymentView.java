package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import userSystem.models.CreditCard;

/**
 * Class representing page to make payment in movie booking system.
 * @author David Cooksley
 *
 */
@SuppressWarnings("serial")
public class PaymentView extends JPanel {
	
	private double paymentAmount;
	private JComboBox<String> creditCardBox;
	private JButton payButton;
	private JLabel amountLabel;
	private JButton backButton;
	
	
	/**
	 * Constructor. Initializes components, builds and organizes UI layout. 
	 */
	public PaymentView() {
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		JLabel payLabel = new JLabel("Make Payment");
		payLabel.setFont(new Font(payLabel.getFont().getFontName(), Font.PLAIN, 36));
		topPanel.add(payLabel);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		midPanel.setAlignmentX(CENTER_ALIGNMENT);
		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.X_AXIS));
		JLabel amountText = new JLabel("Payment Amount: ");
		amountText.setFont(new Font(amountText.getFont().getFontName(), Font.PLAIN, 20));
		amountLabel = new JLabel();
		amountLabel.setFont(new Font(amountLabel.getFont().getFontName(), Font.PLAIN, 20));
		amountPanel.add(amountText);
		amountPanel.add(amountLabel);
		JPanel creditCardPanel = new JPanel();
		creditCardPanel.setLayout(new BoxLayout(creditCardPanel, BoxLayout.X_AXIS));
		JLabel creditCardLabel = new JLabel("Credit Card Number: ");
		creditCardBox = new JComboBox<String>();
		creditCardBox.setEditable(true);
		payButton = new JButton("Pay");
		creditCardPanel.add(creditCardLabel);
		creditCardPanel.add(creditCardBox);
		creditCardPanel.add(payButton);
		midPanel.add(amountPanel);
		midPanel.add(Box.createRigidArea(new Dimension(0,100)));
		midPanel.add(creditCardPanel);
		midPanel.setBorder(new EmptyBorder(new Insets(150,200,300,200)));
		add(midPanel, BorderLayout.CENTER);
		
		backButton = new JButton("Back");
		add(backButton, BorderLayout.SOUTH);
		
	}

	/**
	 * Sets payment amount.
	 * @param paymentAmount
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
		amountLabel.setText("" + this.paymentAmount);
	}
	
	/**
	 * Populates combobox of credit card numbers
	 * @param cards: List of credit cards
	 */
	public void populateCreditCards(ArrayList<CreditCard> cards) {
		for (CreditCard card : cards) {
			creditCardBox.addItem(card.getCreditCardNumber());
		}
	}
	
	/**
	 * @return selected credit card number from combo box.
	 */
	public String getCreditCardNumber() {
		return (String)creditCardBox.getSelectedItem();
	}
	
	/**
	 * Adds ActionListener to all buttons on this page.
	 * @param l: ActionListener to handle button events.
	 */
	public void addButtonsListener(ActionListener l)  {
		backButton.addActionListener(l);
		payButton.addActionListener(l);
	}
}
