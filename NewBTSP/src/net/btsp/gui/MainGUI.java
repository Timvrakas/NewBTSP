package net.btsp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel TabArea = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, TabArea, 0, SpringLayout.SOUTH, frame.getContentPane());
		TabArea.setBackground(SystemColor.window);
		springLayout.putConstraint(SpringLayout.NORTH, TabArea, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, TabArea, 0, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(TabArea);
		
		JPanel LoginArea = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, TabArea, 0, SpringLayout.WEST, LoginArea);
		springLayout.putConstraint(SpringLayout.WEST, LoginArea, -150, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, LoginArea, 0, SpringLayout.SOUTH, frame.getContentPane());
		LoginArea.setBackground(SystemColor.window);
		SpringLayout sl_TabArea = new SpringLayout();
		TabArea.setLayout(sl_TabArea);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_TabArea.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, TabArea);
		sl_TabArea.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, TabArea);
		sl_TabArea.putConstraint(SpringLayout.EAST, tabbedPane, -10, SpringLayout.EAST, TabArea);
		TabArea.add(tabbedPane);
		
		JProgressBar progressBar = new JProgressBar();
		sl_TabArea.putConstraint(SpringLayout.SOUTH, tabbedPane, -6, SpringLayout.NORTH, progressBar);
		sl_TabArea.putConstraint(SpringLayout.WEST, progressBar, 10, SpringLayout.WEST, TabArea);
		sl_TabArea.putConstraint(SpringLayout.SOUTH, progressBar, -10, SpringLayout.SOUTH, TabArea);
		sl_TabArea.putConstraint(SpringLayout.EAST, progressBar, -10, SpringLayout.EAST, TabArea);
		TabArea.add(progressBar);
		springLayout.putConstraint(SpringLayout.NORTH, LoginArea, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, LoginArea, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(LoginArea);
		SpringLayout sl_LoginArea = new SpringLayout();
		LoginArea.setLayout(sl_LoginArea);
		
		JLabel lblAccountStatus = new JLabel("Logged in: Yes");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, lblAccountStatus, 10, SpringLayout.NORTH, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.WEST, lblAccountStatus, 10, SpringLayout.WEST, LoginArea);
		LoginArea.add(lblAccountStatus);
		
		JLabel lblUsername = new JLabel("Name: Timv");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.SOUTH, lblAccountStatus);
		sl_LoginArea.putConstraint(SpringLayout.WEST, lblUsername, 10, SpringLayout.WEST, LoginArea);
		LoginArea.add(lblUsername);
		
		JButton btnSignOut = new JButton("Sign Out");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, btnSignOut, 6, SpringLayout.SOUTH, lblUsername);
		sl_LoginArea.putConstraint(SpringLayout.WEST, btnSignOut, 10, SpringLayout.WEST, LoginArea);
		LoginArea.add(btnSignOut);
		
		JSeparator separator = new JSeparator();
		sl_TabArea.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.EAST, TabArea);
		sl_TabArea.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, TabArea);
		frame.getContentPane().add(separator);
	}
}
