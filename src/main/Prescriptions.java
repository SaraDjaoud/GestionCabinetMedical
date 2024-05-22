package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Prescriptions {

	private JFrame frame;
	private JTable table;
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private JTextField medicinetxt;
	private JComboBox<String> FirstNameCombox;
	private JComboBox<String> FamilyNameCombox;
	private JComboBox<String> traitmentCombox;
	
	private int PrescriptionsIdCounter = 0;
	private JTextField pricetxt;
	private JTextField dosetxt;
	private JTextField durationtxt;

	// Method to generate unique IDs for appointments
	private int generateAppointmentId() {
	    return ++PrescriptionsIdCounter;
	}
	
	public Prescriptions() {
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
		
		JScrollPane PresciptionsTable = new JScrollPane();
		PresciptionsTable.setBounds(10, 402, 890, 209);
		frame.getContentPane().add(PresciptionsTable);
		
		table = new JTable();
		PresciptionsTable.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        // Check if the selection is not adjusting and there is a selected row
		        if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            // Retrieve data from the selected row
		            String firstName = table.getValueAt(selectedRow, 1).toString();
		            String familyName = table.getValueAt(selectedRow, 2).toString();
		            String traitement = table.getValueAt(selectedRow, 3).toString();
		            String medicine = table.getValueAt(selectedRow, 4).toString();
		            String price = table.getValueAt(selectedRow, 5).toString();
		            String dose = table.getValueAt(selectedRow, 6).toString();
		            String duration = table.getValueAt(selectedRow, 7).toString();
		            // Set the retrieved data to the text fields or any other components
		            // For example:
		            FirstNameCombox.setSelectedItem(firstName);
		            FamilyNameCombox.setSelectedItem(familyName);
		            traitmentCombox.setSelectedItem(traitement);
		            medicinetxt.setText(medicine);
		            pricetxt.setText(price);
		            dosetxt.setText(dose);
		            durationtxt.setText(duration);
		        }
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Prescriptions :");
		lblNewLabel.setForeground(new Color(81, 44, 122));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel.setBounds(152, 0, 239, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel FirstNamelbl = new JLabel("Patient First Name :");
		FirstNamelbl.setForeground(new Color(103, 55, 155));
		FirstNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FirstNamelbl.setBounds(10, 84, 172, 19);
		frame.getContentPane().add(FirstNamelbl);
		
		JLabel FamilyNamelbl = new JLabel("Patient Family Name :");
		FamilyNamelbl.setForeground(new Color(103, 55, 155));
		FamilyNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		FamilyNamelbl.setBounds(284, 80, 195, 23);
		frame.getContentPane().add(FamilyNamelbl);
		
		JLabel traitementlbl = new JLabel("Traitement :");
		traitementlbl.setForeground(new Color(103, 55, 155));
		traitementlbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		traitementlbl.setBounds(10, 165, 114, 23);
		frame.getContentPane().add(traitementlbl);
		
		JLabel medicinelbl = new JLabel("Medicine :");
		medicinelbl.setForeground(new Color(103, 55, 155));
		medicinelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		medicinelbl.setBounds(10, 239, 107, 23);
		frame.getContentPane().add(medicinelbl);
		
		
		
		JButton ADDbutton = new JButton("ADD");
		ADDbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		       
				// Generate a unique ID for the appointment
		        int appointmentId = generateAppointmentId();

		        // Get other values
		        String medicine = medicinetxt.getText();
		        String firstName = FirstNameCombox.getSelectedItem().toString();
		        String familyName = FamilyNameCombox.getSelectedItem().toString();
		        String traitement = traitmentCombox.getSelectedItem().toString();
		        String price = pricetxt.getText();
		        String dose = dosetxt.getText();
		        String duration = durationtxt.getText();

		        try {
		            // Construct the INSERT query with the generated ID
		        	String query = "INSERT INTO Prescription (ID, FirstName, FamilyName, Traitement, Medicine, Price, Dose ,Duration) " +
		                    "VALUES (Prescription_seq.nextval, '" + firstName + "', '" + familyName + "', '" + traitement + "', '" + medicine + "', '" + price + "','" + dose + "','" + duration + "')";

		            statement.executeUpdate(query);

		            // Show a message to inform the user that the appointment has been added
		            JOptionPane.showMessageDialog(frame, "Prescription added successfully!");

		            // Update the table
		            updateTable();
  
		            clearFields();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
			
		});
		
		
		ADDbutton.setFont(new Font("Segoe UI", Font.BOLD, 21));
		ADDbutton.setForeground(new Color(255, 255, 255));
		ADDbutton.setBackground(new Color(146, 98, 198));
		ADDbutton.setBounds(662, 344, 195, 52);
		frame.getContentPane().add(ADDbutton);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the selected row index
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) { // Check if a row is selected
		            try {
		                // Retrieve the ID of the selected row
		                int prescriptionsID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

		                // Construct the DELETE query
		                String query = "DELETE FROM Prescription WHERE ID=" + prescriptionsID;

		                // Execute the DELETE query using a Statement
		                statement.executeUpdate(query);

		                // Refresh the table to reflect the changes
		                updateTable();

		                clearFields();
		                
		                // Show a message to inform the user that the appointment has been deleted
		                JOptionPane.showMessageDialog(frame, "Prescription deleted successfully!");
		            } catch (SQLException | NumberFormatException ex) {
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		deletebutton.setBackground(new Color(255, 255, 255));
		deletebutton.setFont(new Font("Segoe UI", Font.BOLD, 21));
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
		        	String medicine = medicinetxt.getText();
			        String firstName = FirstNameCombox.getSelectedItem().toString();
			        String familyName = FamilyNameCombox.getSelectedItem().toString();
			        String traitement = traitmentCombox.getSelectedItem().toString();
			        String price = pricetxt.getText();
			        String dose = dosetxt.getText();
			        String duration = durationtxt.getText();

		            try {
		                // Retrieve the ID of the selected row
		                int PrescriptionID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

		                // Update the corresponding row in the database
		                String query = "UPDATE Prescription SET FirstName='" + firstName + "', FamilyName='" + familyName + 
		                               "',  Traitement='" + traitement + "', Medicine='" + medicine + "', Price='" + price + "', Dose='" + dose + "', Duration='" + duration + "' WHERE ID=" + PrescriptionID;
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
		ModifyButton.setFont(new Font("Segoe UI", Font.BOLD, 21));
		ModifyButton.setForeground(new Color(141, 91, 196));
		ModifyButton.setBounds(662, 114, 195, 48);
		frame.getContentPane().add(ModifyButton);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       medicinetxt.setText("");
			       FirstNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
			       FamilyNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
			       traitmentCombox.setSelectedIndex(0);
			       pricetxt.setText("");
			       dosetxt.setText("");
			       durationtxt.setText("");
			}
		});
		UpdateButton.setBackground(new Color(255, 255, 255));
		UpdateButton.setFont(new Font("Segoe UI", Font.BOLD, 21));
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
		FirstNameCombox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		FirstNameCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select"}));
		FirstNameCombox.setBounds(10, 110, 239, 44);
		frame.getContentPane().add(FirstNameCombox);
		
		FamilyNameCombox = new JComboBox<>();
		FamilyNameCombox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		FamilyNameCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select"}));
		FamilyNameCombox.setBounds(284, 110, 239, 44);
		frame.getContentPane().add(FamilyNameCombox);

		traitmentCombox = new JComboBox<>();
		traitmentCombox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		traitmentCombox.setModel(new DefaultComboBoxModel<>(new String[] {"Select"}));
		traitmentCombox.setBounds(10, 191, 239, 44);
		frame.getContentPane().add(traitmentCombox);
		
		populateFirstNameComboBox();
		populateFamilyNameComboBox();
		populateTraitementComboBox();
		
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the selected first name, family name, and treatment
		        String firstName = FirstNameCombox.getSelectedItem().toString();
		        String familyName = FamilyNameCombox.getSelectedItem().toString();
		        String treatment = traitmentCombox.getSelectedItem().toString();

		        try {
		            // Query to fetch medicine details based on the selected first name, family name, and treatment
		            String query = "SELECT Medicine, Price, Dose, Duration FROM Medicalfile WHERE FirstName='" + firstName + "' AND FamilyName='" + familyName + "' AND Traitement='" + treatment + "'";
		            resultSet = statement.executeQuery(query);

		            // Check if the result set has data
		            if (resultSet.next()) {
		                // Retrieve medicine details from the result set
		                String medicine = resultSet.getString("Medicine");
		                String price = resultSet.getString("Price");
		                String dose = resultSet.getString("Dose");
		                String duration = resultSet.getString("Duration");

		                // Set the retrieved medicine details to the text fields
		                medicinetxt.setText(medicine);
		                pricetxt.setText(price);
		                dosetxt.setText(dose);
		                durationtxt.setText(duration);
		            } else {
		                // If no medicine details found, display a message
		                JOptionPane.showMessageDialog(frame, "No medicine details found for the selected patient and treatment.", "No Data Found", JOptionPane.WARNING_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		ConfirmButton.setFont(new Font("Segoe UI", Font.BOLD, 17));
		ConfirmButton.setForeground(new Color(255, 255, 255));
		
		ConfirmButton.setBackground(new Color(152, 106, 202));
		ConfirmButton.setBounds(342, 190, 107, 44);
		frame.getContentPane().add(ConfirmButton);
		
		medicinetxt = new JTextField();
		medicinetxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		medicinetxt.setColumns(10);
		medicinetxt.setBounds(10, 267, 239, 46);
		frame.getContentPane().add(medicinetxt);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(163, 122, 207));
		panel.setBounds(617, 35, 283, 298);
		frame.getContentPane().add(panel);
		
		pricetxt = new JTextField();
		pricetxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pricetxt.setColumns(10);
		pricetxt.setBounds(284, 267, 239, 46);
		frame.getContentPane().add(pricetxt);
		
		JLabel pricelbl = new JLabel("Price :");
		pricelbl.setForeground(new Color(103, 55, 155));
		pricelbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		pricelbl.setBounds(284, 239, 107, 23);
		frame.getContentPane().add(pricelbl);
		
		dosetxt = new JTextField();
		dosetxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dosetxt.setColumns(10);
		dosetxt.setBounds(10, 344, 239, 47);
		frame.getContentPane().add(dosetxt);
		
		JLabel doselbl = new JLabel("Dose :");
		doselbl.setForeground(new Color(103, 55, 155));
		doselbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		doselbl.setBounds(10, 318, 107, 23);
		frame.getContentPane().add(doselbl);
		
		JLabel durationlbl = new JLabel("Duratioon of intake :");
		durationlbl.setForeground(new Color(103, 55, 155));
		durationlbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		durationlbl.setBounds(284, 318, 195, 23);
		frame.getContentPane().add(durationlbl);
		
		durationtxt = new JTextField();
		durationtxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		durationtxt.setColumns(10);
		durationtxt.setBounds(284, 344, 239, 47);
		frame.getContentPane().add(durationtxt);
		
	
		
		
	}
	
	
	// Method to update the table
			private void updateTable() {
			    try {
			        // Fetch updated data from the database
			        String query = "SELECT * FROM Prescription";
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
		            String query = "SELECT * FROM Prescription";
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
   
   private void populateTraitementComboBox() {
       try {
           String query = "SELECT Traitement FROM Medicalfile";
           resultSet = statement.executeQuery(query);
           while (resultSet.next()) {
               String traitement = resultSet.getString("Traitement");
               traitmentCombox.addItem(traitement);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   private void clearFields() {
	   medicinetxt.setText("");
       FirstNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
       FamilyNameCombox.setSelectedIndex(0); // Assuming index 0 is "Select"
       traitmentCombox.setSelectedIndex(0);
       pricetxt.setText("");
       dosetxt.setText("");
       durationtxt.setText("");
	}

   
	public void setVisible(boolean b) {
        frame.setVisible(b);
    }

public JFrame getFrame() {
    return frame;
}
}
