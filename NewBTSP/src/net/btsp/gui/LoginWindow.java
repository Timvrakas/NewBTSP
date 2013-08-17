package net.btsp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 221, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblStatus = new JLabel("Status:");
		contentPane.add(lblStatus);
		
		JLabel lblNewLabel = new JLabel("Logged In");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblStatus, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblStatus, -6, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 68, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		Username = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, Username, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, Username, 68, SpringLayout.WEST, contentPane);
		contentPane.add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, Password, 6, SpringLayout.SOUTH, Username);
		sl_contentPane.putConstraint(SpringLayout.WEST, Password, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(Password);
		Password.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Login:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.NORTH, Username);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblStatus);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 6, SpringLayout.NORTH, Password);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("HI");
				//TODO login
				setVisible(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
