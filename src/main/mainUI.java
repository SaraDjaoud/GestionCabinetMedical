package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainUI extends JFrame {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private JFrame frame;
	private JTextField usernametxt;
	private JPasswordField passwordf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
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
	public mainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "djaoud";
        String password = "sara";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection= DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 926, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cabinet Medical");
		lblNewLabel.setForeground(new Color(35, 26, 121));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 45));
		lblNewLabel.setBounds(171, 95, 394, 45);
		frame.getContentPane().add(lblNewLabel);
		
		usernametxt = new JTextField();
		usernametxt.setFont(new Font("Segoe UI", Font.BOLD, 20));
		usernametxt.setBounds(224, 224, 288, 45);
		frame.getContentPane().add(usernametxt);
		usernametxt.setColumns(1);
		
		JLabel usernamelbl = new JLabel("username :");
		usernamelbl.setForeground(new Color(35, 26, 121));
		usernamelbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		usernamelbl.setBounds(321, 192, 137, 21);
		frame.getContentPane().add(usernamelbl);
		
		JLabel passwordlbl = new JLabel("password :");
		passwordlbl.setForeground(new Color(35, 26, 121));
		passwordlbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		passwordlbl.setBounds(321, 273, 101, 26);
		frame.getContentPane().add(passwordlbl);
		
		passwordf = new JPasswordField();
		passwordf.setFont(new Font("Segoe UI", Font.BOLD, 20));
		passwordf.setToolTipText("");
		passwordf.setBounds(224, 310, 288, 45);
		frame.getContentPane().add(passwordf);
		
		JButton loginButton = new JButton("login");
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username=usernametxt.getText();
				String password= new String(passwordf.getPassword());
				
                // Debugging statements
                System.out.println("Entered Username: " + username);
                System.out.println("Entered Password: " + password);
				
				try {
					String query = "SELECT * FROM users WHERE username ='"+username+"' and mdp  ='"+password+"'";
					 // Debugging statement
                    System.out.println("Executing Query: " + query);
					
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
					
					usernametxt.setText("");
					passwordf.setText("");
					
					 if (resultSet.next()) {
						// Debugging statement
						 System.out.println("Login successful");
						 PrincipalMenu principalmenu = new PrincipalMenu();
						 principalmenu.setVisible(true);
						 frame.dispose();
					 } else {
						 JOptionPane.showMessageDialog(null,"Invalid username or password !");
						 
					 }
				} catch (Exception ex) {
					ex.printStackTrace();}
			}});
		loginButton.setBackground(new Color(131, 120, 226));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(397, 388, 115, 45);
		frame.getContentPane().add(loginButton);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setForeground(new Color(131, 120, 226));
		exitButton.setBackground(new Color(255, 255, 255));
		exitButton.setBounds(224, 388, 115, 45);
		frame.getContentPane().add(exitButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(mainUI.class.getResource("/main/testt1.png")));
		lblNewLabel_2.setBounds(0, 0, 910, 630);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
    public JFrame getFrame() {
        return frame;
    }
}

