package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class PatientCards {

	private JFrame frame;
	private JTable table;
	private JTextField FirstNametxt;
	private JTextField FamilyNametxt;
	private JTextField Bdaytxt;
	private JTextField Phonetxt;
	private JTextField Adrtxt;
	private JTextField Allergytxt;
	private JComboBox<String> SexCombox;
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public PatientCards() {
		initialize();
		populateTable();
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
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 926, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane PatientTable = new JScrollPane();
		PatientTable.setBounds(10, 343, 890, 268);
		frame.getContentPane().add(PatientTable);
		
		table = new JTable();
		PatientTable.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        // Check if the selection is not adjusting and there is a selected row
		        if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            // Retrieve data from the selected row
		            String firstName = table.getValueAt(selectedRow, 1).toString();
		            String familyName = table.getValueAt(selectedRow, 2).toString();
		            String birthDate = table.getValueAt(selectedRow, 3).toString();
		            String phoneNumber = table.getValueAt(selectedRow, 4).toString();
		            String sex = table.getValueAt(selectedRow, 5).toString();
		            String address = table.getValueAt(selectedRow, 6).toString();
		            String allergy = table.getValueAt(selectedRow, 7).toString();

		            // Set the retrieved data to the text fields
		            FirstNametxt.setText(firstName);
		            FamilyNametxt.setText(familyName);
		            Bdaytxt.setText(birthDate);
		            Phonetxt.setText(phoneNumber);
		            // Assuming sexComboBox is the name of your JComboBox
		            SexCombox.setSelectedItem(sex);
		            Adrtxt.setText(address);
		            Allergytxt.setText(allergy);
		        }
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Patient Cards :");
		lblNewLabel.setForeground(new Color(81, 44, 122));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblNewLabel.setBounds(167, 1, 210, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel FirstNamelbl = new JLabel("First Name :");
		FirstNamelbl.setForeground(new Color(103, 55, 155));
		FirstNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FirstNamelbl.setBounds(30, 56, 157, 18);
		frame.getContentPane().add(FirstNamelbl);
		
		JLabel FamilyNamelbl = new JLabel("Family Name :");
		FamilyNamelbl.setForeground(new Color(103, 55, 155));
		FamilyNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FamilyNamelbl.setBounds(294, 51, 129, 23);
		frame.getContentPane().add(FamilyNamelbl);
		
		JLabel Bdaylbl = new JLabel("Date of Birth :");
		Bdaylbl.setForeground(new Color(103, 55, 155));
		Bdaylbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
		Bdaylbl.setBounds(30, 125, 129, 14);
		frame.getContentPane().add(Bdaylbl);
		
		JLabel Phonelbl = new JLabel("Phone Number :");
		Phonelbl.setForeground(new Color(103, 55, 155));
		Phonelbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
		Phonelbl.setBounds(296, 125, 142, 14);
		frame.getContentPane().add(Phonelbl);
		
		JLabel Sexlbl = new JLabel("Sex :");
		Sexlbl.setForeground(new Color(103, 55, 155));
		Sexlbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Sexlbl.setBounds(31, 194, 46, 17);
		frame.getContentPane().add(Sexlbl);
		
		JLabel Adrlbl = new JLabel("Adress :");
		Adrlbl.setForeground(new Color(103, 55, 155));
		Adrlbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Adrlbl.setBounds(296, 191, 81, 23);
		frame.getContentPane().add(Adrlbl);
		
		JLabel Allgerylbl = new JLabel("Allergy :");
		Allgerylbl.setForeground(new Color(103, 55, 155));
		Allgerylbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Allgerylbl.setBounds(30, 265, 107, 23);
		frame.getContentPane().add(Allgerylbl);
		
		FirstNametxt = new JTextField();
		FirstNametxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		FirstNametxt.setBounds(22, 81, 222, 38);
		frame.getContentPane().add(FirstNametxt);
		FirstNametxt.setColumns(10);
		
		FamilyNametxt = new JTextField();
		FamilyNametxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		FamilyNametxt.setColumns(10);
		FamilyNametxt.setBounds(286, 82, 222, 37);
		frame.getContentPane().add(FamilyNametxt);
		
		Bdaytxt = new JTextField();
		Bdaytxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Bdaytxt.setColumns(10);
		Bdaytxt.setBounds(22, 142, 222, 38);
		frame.getContentPane().add(Bdaytxt);
		
		Phonetxt = new JTextField();
		Phonetxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Phonetxt.setColumns(10);
		Phonetxt.setBounds(286, 142, 222, 37);
		frame.getContentPane().add(Phonetxt);
		
		SexCombox = new JComboBox<String>(new String[] {"Male", "Female"});
		SexCombox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SexCombox.setSelectedIndex(0);
		SexCombox.setBackground(new Color(255, 255, 255));
		SexCombox.setBounds(22, 217, 222, 37);
		frame.getContentPane().add(SexCombox);

		
		Adrtxt = new JTextField();
		Adrtxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Adrtxt.setColumns(10);
		Adrtxt.setBounds(286, 217, 222, 37);
		frame.getContentPane().add(Adrtxt);
		
		Allergytxt = new JTextField();
		Allergytxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Allergytxt.setColumns(10);
		Allergytxt.setBounds(22, 293, 222, 37);
		frame.getContentPane().add(Allergytxt);
		
		JButton ADDbutton = new JButton("ADD");
		ADDbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = FirstNametxt.getText();
		        String familyName = FamilyNametxt.getText();
		        String birthDate = Bdaytxt.getText();
		        String phoneNumber = Phonetxt.getText();
		        String sex = SexCombox.getSelectedItem().toString();
		        String address = Adrtxt.getText();
		        String allergy = Allergytxt.getText();

		        try {
		            String query = "INSERT INTO Patient (FirstName, FamilyName, bDay, Phone, Sex, Adress, Allergy) " +
		                         "VALUES ('" + firstName + "', '" + familyName + "', '" + birthDate + "', '" +
		                         phoneNumber + "', '" + sex + "', '" + address + "', '" + allergy + "')";
		            statement.executeUpdate(query);
		            
		         // Show a message to inform the user that the patient has been added
		            JOptionPane.showMessageDialog(frame, "Patient added successfully!");

		            // Update the table
		            updateTable();
		            clearFields();
		            
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
			
		});
		
		
		ADDbutton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ADDbutton.setForeground(new Color(255, 255, 255));
		ADDbutton.setBackground(new Color(146, 98, 198));
		ADDbutton.setBounds(321, 291, 150, 37);
		frame.getContentPane().add(ADDbutton);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected row index
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) { // Check if a row is selected
		            try {
		                // Retrieve the ID of the selected row
		                int patientID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

		                // Construct the DELETE query
		                String query = "DELETE FROM Patient WHERE ID=" + patientID;

		                // Execute the DELETE query using a Statement
		                statement.executeUpdate(query);

		                // Refresh the table to reflect the changes
		                updateTable();
		                clearFields();
		                // Show a message to inform the user that the patient has been deleted
		                JOptionPane.showMessageDialog(frame, "Patient deleted successfully!");
		            } catch (SQLException | NumberFormatException ex) {
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		deletebutton.setBackground(new Color(255, 255, 255));
		deletebutton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		deletebutton.setForeground(new Color(141, 91, 196));
		deletebutton.setBounds(662, 51, 195, 52);
		frame.getContentPane().add(deletebutton);
		
		JButton ModifyButton = new JButton("Modify");
		ModifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected row index
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) { // Check if a row is selected
		            // Retrieve data from text fields
		            String firstName = FirstNametxt.getText();
		            String familyName = FamilyNametxt.getText();
		            String birthDate = Bdaytxt.getText();
		            String phoneNumber = Phonetxt.getText();
		            String sex = SexCombox.getSelectedItem().toString();
		            String address = Adrtxt.getText();
		            String allergy = Allergytxt.getText();

		            try {
		            	// Retrieve the ID of the selected row
		                int patientID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		                // Update the corresponding row in the database
		                String query = "UPDATE Patient SET FirstName='" + firstName + "', FamilyName='" + familyName + 
		                               "', bDay='" + birthDate + "', Phone='" + phoneNumber + "', Sex='" + sex + 
		                               "', Adress='" + address + "', Allergy='" + allergy + "' WHERE ID=" + patientID;
		                statement.executeUpdate(query);

		                // Refresh the table to reflect the changes
		                updateTable();

		                // Clear the text fields after modification
		                clearFields();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to modify.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		ModifyButton.setBackground(new Color(255, 255, 255));
		ModifyButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ModifyButton.setForeground(new Color(141, 91, 196));
		ModifyButton.setBounds(662, 114, 195, 48);
		frame.getContentPane().add(ModifyButton);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear all text fields
		        FirstNametxt.setText("");
		        FamilyNametxt.setText("");
		        Bdaytxt.setText("");
		        Phonetxt.setText("");
		        Adrtxt.setText("");
		        Allergytxt.setText("");
			}
		});
		UpdateButton.setBackground(new Color(255, 255, 255));
		UpdateButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		UpdateButton.setForeground(new Color(141, 91, 196));
		UpdateButton.setBounds(662, 174, 195, 52);
		frame.getContentPane().add(UpdateButton);
		
		JButton MenuButton = new JButton("Menu");
		MenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open menu page
				PrincipalMenu Menupage = new PrincipalMenu();
				Menupage.getFrame().setVisible(true);

                // Dispose current frame
                frame.dispose();
			}
		});
		MenuButton.setFont(new Font("Segoe UI", Font.BOLD, 21));
		MenuButton.setForeground(new Color(255, 0, 0));
		MenuButton.setBackground(new Color(255, 255, 255));
		MenuButton.setBounds(662, 261, 195, 52);
		frame.getContentPane().add(MenuButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 119, 206));
		panel.setBounds(617, 35, 283, 298);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("DD-MM-YYYY");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(206, 0, 0));
		lblNewLabel_1.setBounds(93, 180, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	
	// Method to update the table
			private void updateTable() {
			    try {
			        // Fetch updated data from the database
			        String query = "SELECT * FROM Patient";
			        resultSet = statement.executeQuery(query);

			        // Update the table model with the new data
			        DefaultTableModel model = new DefaultTableModel();
			        ResultSetMetaData metaData = resultSet.getMetaData();
			        int columnCount = metaData.getColumnCount();

			        // Add columns to the table model
			        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			            model.addColumn(metaData.getColumnLabel(columnIndex));
			        }

			        // Add rows to the table model
			        while (resultSet.next()) {
			            Object[] rowData = new Object[columnCount];
			            for (int i = 0; i < columnCount; i++) {
			                rowData[i] = resultSet.getObject(i + 1);
			            }
			            model.addRow(rowData);
			        }

			        // Set the table model
			        table.setModel(model);
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }
			
			}
   private void populateTable() {
		        try {
		            String query = "SELECT * FROM Patient";
		            resultSet = statement.executeQuery(query);

		            // Use DefaultTableModel to populate JTable
		            DefaultTableModel model = new DefaultTableModel();
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();

		            // Add columns to model
		            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		                model.addColumn(metaData.getColumnLabel(columnIndex));
		            }

		            // Add rows to model
		            while (resultSet.next()) {
		                Object[] row = new Object[columnCount];
		                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		                    row[columnIndex - 1] = resultSet.getObject(columnIndex);
		                }
		                model.addRow(row);
		            }

		            // Set model to table
		            table.setModel(model);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }

   private void clearFields() {
	   FirstNametxt.setText("");
       FamilyNametxt.setText("");
       Bdaytxt.setText("");
       Phonetxt.setText("");
       Adrtxt.setText("");
       Allergytxt.setText("");
	}

   
	public void setVisible(boolean b) {
        frame.setVisible(b);
    }

public JFrame getFrame() {
    return frame;
}
}
