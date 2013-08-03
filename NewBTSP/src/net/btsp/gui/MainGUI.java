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
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class MainGUI {

	static JTextPane Console;
	private JFrame frame;
	private JTextField IPaddress;

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
		springLayout.putConstraint(SpringLayout.WEST, LoginArea, -136, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, TabArea, 0, SpringLayout.WEST, LoginArea);
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
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"lo", "lo"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		tabbedPane.addTab("Profiles", null, list, null);
		
		Console = new JTextPane();
		Console.setEditable(false);
		tabbedPane.addTab("Console", null, Console, null);
		redirectSystemStreams();
		tabbedPane.setEnabledAt(1, true);
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
		sl_LoginArea.putConstraint(SpringLayout.EAST, lblAccountStatus, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(lblAccountStatus);
		JLabel lblUsername = new JLabel("Name: Timv");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.SOUTH, lblAccountStatus);
		sl_LoginArea.putConstraint(SpringLayout.WEST, lblUsername, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, lblUsername, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(lblUsername);
		
		JButton btnSignOut = new JButton("Sign Out");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, btnSignOut, 6, SpringLayout.SOUTH, lblUsername);
		sl_LoginArea.putConstraint(SpringLayout.WEST, btnSignOut, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, btnSignOut, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(btnSignOut);
		
		JSeparator separator_1 = new JSeparator();
		sl_LoginArea.putConstraint(SpringLayout.NORTH, separator_1, -3, SpringLayout.SOUTH, btnSignOut);
		sl_LoginArea.putConstraint(SpringLayout.WEST, separator_1, 0, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, separator_1, 0, SpringLayout.EAST, LoginArea);
		separator_1.setToolTipText("");
		LoginArea.add(separator_1);
		
		JButton btnAddProfile = new JButton("Add Profile");
		sl_LoginArea.putConstraint(SpringLayout.EAST, btnAddProfile, -10, SpringLayout.EAST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.SOUTH, separator_1, -3, SpringLayout.NORTH, btnAddProfile);
		sl_LoginArea.putConstraint(SpringLayout.NORTH, btnAddProfile, 6, SpringLayout.SOUTH, btnSignOut);
		sl_LoginArea.putConstraint(SpringLayout.WEST, btnAddProfile, 10, SpringLayout.WEST, LoginArea);
		LoginArea.add(btnAddProfile);
		
		JButton btnSettings = new JButton("Settings");
		sl_LoginArea.putConstraint(SpringLayout.NORTH, btnSettings, 6, SpringLayout.SOUTH, btnAddProfile);
		sl_LoginArea.putConstraint(SpringLayout.WEST, btnSettings, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, btnSettings, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(btnSettings);
		
		JButton btnEnterGame = new JButton("Enter Game");
		sl_LoginArea.putConstraint(SpringLayout.WEST, btnEnterGame, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, btnEnterGame, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(btnEnterGame);
		
		IPaddress = new JTextField();
		sl_LoginArea.putConstraint(SpringLayout.WEST, IPaddress, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.EAST, IPaddress, -10, SpringLayout.EAST, LoginArea);
		LoginArea.add(IPaddress);
		IPaddress.setColumns(10);
		
		JLabel lblConectToServer = new JLabel("Conect to Server:");
		sl_LoginArea.putConstraint(SpringLayout.EAST, lblConectToServer, -10, SpringLayout.EAST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.NORTH, IPaddress, 6, SpringLayout.SOUTH, lblConectToServer);
		sl_LoginArea.putConstraint(SpringLayout.WEST, lblConectToServer, 10, SpringLayout.WEST, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.SOUTH, lblConectToServer, -100, SpringLayout.SOUTH, LoginArea);
		LoginArea.add(lblConectToServer);
		
		JSeparator separator = new JSeparator();
		sl_LoginArea.putConstraint(SpringLayout.SOUTH, separator, -48, SpringLayout.SOUTH, LoginArea);
		sl_LoginArea.putConstraint(SpringLayout.NORTH, btnEnterGame, 6, SpringLayout.SOUTH, separator);
		sl_LoginArea.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, separator_1);
		sl_LoginArea.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, separator_1);
		LoginArea.add(separator);
	}
	private void updateTextPane(final String text) {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        Document doc = Console.getDocument();
	        try {
	          doc.insertString(doc.getLength(), text, null);
	        } catch (BadLocationException e) {
	          throw new RuntimeException(e);
	        }
	        Console.setCaretPosition(doc.getLength() - 1);
	      }
	    });
	  }

	private void redirectSystemStreams() {
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	        updateTextPane(new String(b, off, len));
	        //net.btsp.util.log(new String(b, off, len))
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }

		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			
		}
	    };

	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	  }

}
