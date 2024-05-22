package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;

public class PrincipalMenu {

	private JFrame frame;
	private Timer timer;
    private JLabel dateLabel;
    private JLabel timeLabel;

	public PrincipalMenu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Medical File");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalFile medicalfilepage = new MedicalFile();
				medicalfilepage.getFrame().setVisible(true);
                frame.dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(121, 67, 182));
		btnNewButton.setBounds(80, 339, 218, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnAppointment = new JButton("Appointment");
		btnAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Appointment Appointmentpage = new Appointment();
				Appointmentpage.getFrame().setVisible(true);
                frame.dispose();
			}
		});
		btnAppointment.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAppointment.setForeground(Color.WHITE);
		btnAppointment.setBackground(new Color(121, 67, 182));
		btnAppointment.setBounds(80, 278, 218, 48);
		frame.getContentPane().add(btnAppointment);
		
		JButton btnPatientCards = new JButton("Patient Cards");
		btnPatientCards.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnPatientCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open PatientCards page
                PatientCards patientCardsPage = new PatientCards();
                patientCardsPage.getFrame().setVisible(true);
                frame.dispose();
			}
		});
		btnPatientCards.setForeground(Color.WHITE);
		btnPatientCards.setBackground(new Color(121, 67, 182));
		btnPatientCards.setBounds(80, 219, 218, 48);
		frame.getContentPane().add(btnPatientCards);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open login page
                mainUI loginPage = new mainUI();
                loginPage.getFrame().setVisible(true);

                // Dispose current frame
                frame.dispose();
			}
		});
		
		initializeTimer();
		
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(new Color(209, 20, 39));
		btnLogOut.setBounds(80, 523, 218, 48);
		frame.getContentPane().add(btnLogOut);
		
		dateLabel = new JLabel("DATE");
		dateLabel.setFont(new Font("MS Gothic", Font.BOLD, 18));
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setBounds(400, 82, 146, 20);
		frame.getContentPane().add(dateLabel);
		
		timeLabel = new JLabel("HOUR");
		timeLabel.setFont(new Font("MS Gothic", Font.BOLD, 18));
		timeLabel.setForeground(new Color(255, 255, 255));
		timeLabel.setBackground(new Color(255, 255, 255));
		timeLabel.setBounds(653, 82, 99, 20);
		frame.getContentPane().add(timeLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setForeground(new Color(132, 54, 194));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblNewLabel_1.setBounds(148, 28, 93, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalMenu.class.getResource("/main/purple.png")));
		lblNewLabel.setBounds(379, 71, 188, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PrincipalMenu.class.getResource("/main/purple.png")));
		lblNewLabel_3.setBounds(615, 71, 174, 38);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date :");
		lblNewLabel_4.setForeground(new Color(120, 63, 158));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblNewLabel_4.setBounds(400, 43, 77, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Hour :");
		lblNewLabel_4_1.setForeground(new Color(120, 63, 158));
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		lblNewLabel_4_1.setBounds(659, 43, 77, 20);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JButton btnPrescriptions = new JButton("Prescriptions");
		btnPrescriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prescriptions prescriptionpage = new Prescriptions();
				prescriptionpage.getFrame().setVisible(true);
                frame.dispose();
			}
			}
		);
		btnPrescriptions.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnPrescriptions.setForeground(Color.WHITE);
		btnPrescriptions.setBackground(new Color(121, 67, 182));
		btnPrescriptions.setBounds(80, 398, 218, 48);
		frame.getContentPane().add(btnPrescriptions);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PrincipalMenu.class.getResource("/main/logoMed.png")));
		lblNewLabel_2.setBounds(-167, -332, 510, 954);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PrincipalMenu.class.getResource("/main/menu1.png")));
		lblNewLabel_5.setBounds(-11, 59, 929, 540);
		frame.getContentPane().add(lblNewLabel_5);
		frame.setBounds(100, 100, 924, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}

	private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                dateLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                timeLabel.setText(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        });
        timer.start();
    }
	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
    public JFrame getFrame() {
        return frame;
    }
}

