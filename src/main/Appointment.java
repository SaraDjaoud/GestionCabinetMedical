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
import javax.swing.*;



public class Appointment {

	private JFrame frame;
	private JTable table;
	private JTextField Allergytxt;
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private JTextField Datetxt;
	private JComboBox<String> FirstNameCombox;
	private JComboBox<String> FamilyNameCombox;
	private JComboBox<String> HourCombox;

	private int appointmentIdCounter = 0;

	// Method to generate unique IDs for appointments
	private int generateAppointmentId() {
	    return ++appointmentIdCounter;
	}
	
	public Appointment() {
		initialize();
		populateTable();
		
	}

	

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
		
		JScrollPane AppointmentTable = new JScrollPane();
		AppointmentTable.setBounds(10, 350, 890, 261);
		frame.getContentPane().add(AppointmentTable);
		
		table = new JTable();
		AppointmentTable.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        // Check if the selection is not adjusting and there is a selected row
		        if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            // Retrieve data from the selected row
		            String firstName = table.getValueAt(selectedRow, 1).toString();
		            String familyName = table.getValueAt(selectedRow, 2).toString();
		            String allergy = table.getValueAt(selectedRow, 3).toString();
		            String hour = table.getValueAt(selectedRow, 4).toString();
		            String date = table.getValueAt(selectedRow, 5).toString();

		            // Set the retrieved data to the text fields or any other components
		            // For example:
		            FirstNameCombox.setSelectedItem(firstName);
		            FamilyNameCombox.setSelectedItem(familyName);
		            Allergytxt.setText(allergy);
		            HourCombox.setSelectedItem(hour);
		            Datetxt.setText(date);
		        }
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Appointment");
		lblNewLabel.setForeground(new Color(81, 44, 122));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel.setBounds(155, 11, 255, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel FirstNamelbl = new JLabel("First Name :");
		FirstNamelbl.setForeground(new Color(103, 55, 155));
		FirstNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FirstNamelbl.setBounds(10, 80, 107, 23);
		frame.getContentPane().add(FirstNamelbl);
		
		JLabel FamilyNamelbl = new JLabel("Family Name :");
		FamilyNamelbl.setForeground(new Color(103, 55, 155));
		FamilyNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FamilyNamelbl.setBounds(274, 81, 166, 23);
		frame.getContentPane().add(FamilyNamelbl);
		
		JLabel Allergylbl = new JLabel("Allergy :");
		Allergylbl.setForeground(new Color(103, 55, 155));
		Allergylbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Allergylbl.setBounds(10, 165, 99, 25);
		frame.getContentPane().add(Allergylbl);
		
		JLabel Hourlbl = new JLabel("Hour :");
		Hourlbl.setForeground(new Color(103, 55, 155));
		Hourlbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Hourlbl.setBounds(274, 166, 114, 23);
		frame.getContentPane().add(Hourlbl);
		
		JLabel Datelbl = new JLabel("Date :");
		Datelbl.setForeground(new Color(103, 55, 155));
		Datelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Datelbl.setBounds(10, 256, 107, 23);
		frame.getContentPane().add(Datelbl);
		
		Allergytxt = new JTextField();
		Allergytxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Allergytxt.setColumns(10);
		Allergytxt.setBounds(10, 201, 238, 44);
		frame.getContentPane().add(Allergytxt);
		
		
		
		JButton ADDbutton = new JButton("ADD");
		ADDbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		       
				// Generate a unique ID for the appointment
		        int appointmentId = generateAppointmentId();

		        // Get other values
		        String Allergy = Allergytxt.getText();
		        String date = Datetxt.getText();
		        String firstName = FirstNameCombox.getSelectedItem().toString();
		        String familyName = FamilyNameCombox.getSelectedItem().toString();
		        String Hour = HourCombox.getSelectedItem().toString();

		        try {
		            // Construct the INSERT query with the generated ID
		        	String query = "INSERT INTO Appointment (ID, FirstName, FamilyName, Allergy, Heur, Dat) " +
		                    "VALUES (appointment_seq.nextval, '" + firstName + "', '" + familyName + "', '" + Allergy + "', '" + Hour + "', '" + date + "')";

		            statement.executeUpdate(query);

		            // Show a message to inform the user that the appointment has been added
		            JOptionPane.showMessageDialog(frame, "Appointment added successfully!");

		            // Update the table
		            updateTable();
  
		            clearFields();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
			
		});
		
		
		ADDbutton.setFont(new Font("Segoe UI", Font.BOLD, 19));
		ADDbutton.setForeground(new Color(255, 255, 255));
		ADDbutton.setBackground(new Color(146, 98, 198));
		ADDbutton.setBounds(312, 278, 158, 44);
		frame.getContentPane().add(ADDbutton);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the selected row index
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) { // Check if a row is selected
		            try {
		                // Retrieve the ID of the selected row
		                int appointmentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

		                // Construct the DELETE query
		                String query = "DELETE FROM Appointment WHERE ID=" + appointmentID;

		                // Execute the DELETE query using a Statement
		                statement.executeUpdate(query);

		                // Refresh the table to reflect the changes
		                updateTable();

		                clearFields();
		                
		                // Show a message to inform the user that the appointment has been deleted
		                JOptionPane.showMessageDialog(frame, "Appointment deleted successfully!");
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
		            String allergy = Allergytxt.getText();
		            String date = Datetxt.getText();
		            String firstName = FirstNameCombox.getSelectedItem().toString();
		            String familyName = FamilyNameCombox.getSelectedItem().toString();
		            String hour = HourCombox.getSelectedItem().toString();

		            try {
		                // Retrieve the ID of the selected row
		                int appointmentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

		                // Update the corresponding row in the database
		                String query = "UPDATE Appointment SET FirstName='" + firstName + "', FamilyName='" + familyName + 
		                               "', Allergy='" + allergy + "', Heur='" + hour + "', Dat='" + date + "' WHERE ID=" + appointmentID;
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
		        Allergytxt.setText("");
		        Datetxt.setText("");
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
		
		FirstNameCombox = new JComboBox<>();
		FirstNameCombox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		FirstNameCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select"}));
		FirstNameCombox.setBounds(10, 110, 238, 44);
		frame.getContentPane().add(FirstNameCombox);
		
		FamilyNameCombox = new JComboBox<>();
		FamilyNameCombox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		FamilyNameCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select"}));
		FamilyNameCombox.setBounds(267, 110, 184, 44);
		frame.getContentPane().add(FamilyNameCombox);
		
		HourCombox = new JComboBox<>();
		HourCombox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		HourCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select","8H00", "9H00","10H00", "11H00", "12H00", "13H00", "14H00", "15H00", "16H00"}));
		HourCombox.setBounds(268, 200, 238, 44);
		frame.getContentPane().add(HourCombox);

		populateFirstNameComboBox();
		populateFamilyNameComboBox();
		
		JButton SelectFirstButton = new JButton("");
		SelectFirstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFirstName = (String) FirstNameCombox.getSelectedItem();
		        String selectedFamilyName = (String) FamilyNameCombox.getSelectedItem();
		        
		        try {
		            String query = "SELECT Allergy FROM Patient WHERE FirstName = '" + selectedFirstName + "' AND FamilyName = '" + selectedFamilyName + "'";
		            ResultSet rs = statement.executeQuery(query);
		            
		            if (rs.next()) {
		                String allergy = rs.getString("Allergy");
		                Allergytxt.setText(allergy);
		            } else {
		                JOptionPane.showMessageDialog(frame, "No allergy found for the selected patient.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		SelectFirstButton.setBackground(new Color(152, 106, 202));
		SelectFirstButton.setBounds(456, 110, 50, 44);
		frame.getContentPane().add(SelectFirstButton);
		
		Datetxt = new JTextField();
		Datetxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Datetxt.setColumns(10);
		Datetxt.setBounds(10, 280, 238, 44);
		frame.getContentPane().add(Datetxt);
		
		JLabel lblNewLabel_1 = new JLabel("DD-MM-YYYY");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(202, 0, 0));
		lblNewLabel_1.setBounds(84, 325, 103, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(163, 122, 207));
		panel.setBounds(617, 35, 283, 298);
		frame.getContentPane().add(panel);
	}
	
	
	// Method to update the table
			private void updateTable() {
			    try {
			        // Fetch updated data from the database
			        String query = "SELECT * FROM Appointment";
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
		            String query = "SELECT * FROM Appointment";
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
   private void populateFirstNameComboBox() {
		try {
			String query = "SELECT FirstName FROM patient";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String firstName = resultSet.getString("FirstName");
				FirstNameCombox.addItem(firstName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   
   private void populateFamilyNameComboBox() {
       try {
           String query = "SELECT FamilyName FROM patient";
           resultSet = statement.executeQuery(query);
           while (resultSet.next()) {
               String familyName = resultSet.getString("FamilyName");
               FamilyNameCombox.addItem(familyName);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   private void clearFields() {
	   Allergytxt.setText("");
       Datetxt.setText("");
       FirstNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
       FamilyNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
       HourCombox.setSelectedIndex(0);
	}

   
	public void setVisible(boolean b) {
        frame.setVisible(b);
    }

public JFrame getFrame() {
    return frame;
}
}
