package net.btsp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class SettingsWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField gamedir;
	private JTextField reswidth;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField resheight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow window = new SettingsWindow();
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
	public SettingsWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblProfileName = new JLabel("Profile Name:");
		frame.getContentPane().add(lblProfileName);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 4, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 149, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -132, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblProfileName, 6, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblProfileName, -6, SpringLayout.WEST, textField);
		frame.getContentPane().add(textField);
		textField.setColumns(100);
		
		JLabel lblMcVersion = new JLabel("MC Version");
		springLayout.putConstraint(SpringLayout.WEST, lblMcVersion, 26, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblMcVersion);
		
		JCheckBox chckbxEnable = new JCheckBox("Enable Minecraft \"snapshot\" versions");
		springLayout.putConstraint(SpringLayout.NORTH, lblMcVersion, 21, SpringLayout.SOUTH, chckbxEnable);
		springLayout.putConstraint(SpringLayout.EAST, chckbxEnable, 0, SpringLayout.EAST, textField);
		frame.getContentPane().add(chckbxEnable);
		
		JCheckBox chckbxGameDirectory = new JCheckBox("Game Directory:");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxGameDirectory, 6, SpringLayout.SOUTH, lblProfileName);
		springLayout.putConstraint(SpringLayout.WEST, chckbxGameDirectory, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(chckbxGameDirectory);
		
		gamedir = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, gamedir, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, gamedir, 6, SpringLayout.EAST, chckbxGameDirectory);
		springLayout.putConstraint(SpringLayout.EAST, gamedir, -132, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(gamedir);
		gamedir.setColumns(10);
		
		reswidth = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, chckbxEnable, 6, SpringLayout.SOUTH, reswidth);
		springLayout.putConstraint(SpringLayout.NORTH, reswidth, 3, SpringLayout.SOUTH, gamedir);
		frame.getContentPane().add(reswidth);
		reswidth.setColumns(10);
		
		JCheckBox resolution = new JCheckBox("Resolution");
		springLayout.putConstraint(SpringLayout.WEST, reswidth, 6, SpringLayout.EAST, resolution);
		springLayout.putConstraint(SpringLayout.EAST, reswidth, 75, SpringLayout.EAST, resolution);
		springLayout.putConstraint(SpringLayout.NORTH, resolution, 6, SpringLayout.SOUTH, chckbxGameDirectory);
		springLayout.putConstraint(SpringLayout.EAST, resolution, 0, SpringLayout.EAST, lblProfileName);
		frame.getContentPane().add(resolution);
		
		JLabel lblX = new JLabel("X");
		springLayout.putConstraint(SpringLayout.NORTH, lblX, 6, SpringLayout.NORTH, reswidth);
		frame.getContentPane().add(lblX);
		
		resheight = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblX, -6, SpringLayout.WEST, resheight);
		springLayout.putConstraint(SpringLayout.NORTH, resheight, 0, SpringLayout.NORTH, reswidth);
		springLayout.putConstraint(SpringLayout.WEST, resheight, -75, SpringLayout.EAST, gamedir);
		springLayout.putConstraint(SpringLayout.EAST, resheight, 0, SpringLayout.EAST, gamedir);
		frame.getContentPane().add(resheight);
		resheight.setColumns(10);
		
		
		
	
	}
}
