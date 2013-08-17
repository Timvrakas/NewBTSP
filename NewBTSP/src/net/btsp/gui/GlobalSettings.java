package net.btsp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GlobalSettings extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlobalSettings frame = new GlobalSettings();
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
	public GlobalSettings() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		//TODO save settings
			@Override
			public void windowOpened(WindowEvent e) {
			}
		//TODO load setings
		});
		setTitle("Settings");
		setBounds(100, 100, 213, 63);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox(" Save to Launcher Location");
		chckbxNewCheckBox.setBounds(6, 6, 200, 23);
		contentPane.add(chckbxNewCheckBox);
	}

}
