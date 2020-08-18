import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class newuser extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextField txtEnterEmail;
	private JTextField txtMobileNumber;
	private JLabel lblSex;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnF;
	private JButton btnSubmit;
	private JTextField txtEnterUserid;
	private JTextField txtEnterPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newuser frame = new newuser();
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
	public newuser() {
		setTitle("NEW USER REGISTER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNaname = new JLabel("NAME:-");
		lblNaname.setBounds(238, 98, 46, 13);
		contentPane.add(lblNaname);
		
		txtEnterName = new JTextField();
		txtEnterName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterName.setText("");
			}
		});
		txtEnterName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterEmail.requestFocus();
					txtEnterEmail.setText("");
				}
			}
		});
		txtEnterName.setText("ENTER NAME");
		txtEnterName.setBounds(358, 95, 159, 19);
		contentPane.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		JLabel lblEmaiil = new JLabel("EMAIL:-");
		lblEmaiil.setBounds(238, 44, 46, 13);
		contentPane.add(lblEmaiil);
		
		txtEnterEmail = new JTextField();
		txtEnterEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtMobileNumber.requestFocus();
					txtMobileNumber.setText("");
				}
			}
		});
		txtEnterEmail.setText("ENTER EMAIL");
		txtEnterEmail.setBounds(358, 41, 159, 19);
		contentPane.add(txtEnterEmail);
		txtEnterEmail.setColumns(10);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterName.setText("");
					txtEnterName.requestFocus();
				}
			}
		});
		txtMobileNumber.setText("PHONE NUMBER");
		txtMobileNumber.setBounds(358, 70, 159, 19);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER:-");
		lblPhoneNumber.setBounds(238, 73, 110, 13);
		contentPane.add(lblPhoneNumber);
		
		lblSex = new JLabel("SEX:-");
		lblSex.setBounds(238, 121, 46, 13);
		contentPane.add(lblSex);
		
		rdbtnMale = new JRadioButton("M");
		rdbtnMale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnMale.isSelected()) {
									
					}
			}
		});
		rdbtnMale.setBounds(358, 120, 67, 21);
		contentPane.add(rdbtnMale);
		
		rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(443, 120, 74, 21);
		contentPane.add(rdbtnF);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c;
				if(rdbtnMale.isSelected()) {
					c="M";
				}
				else {
					c="F";
				}
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","lucky7538");
				String q="insert into user_info (user_id,email,phonenumber,fname,sex,password)"+"values(?,?,?,?,?,?)";
			try(PreparedStatement p=con.prepareStatement(q)){
				p.setString(1, txtEnterUserid.getText());
				p.setString(2, txtEnterEmail.getText());
				p.setString(3, txtMobileNumber.getText());
				p.setString(4, txtEnterName.getText());
				p.setString(5, c);
				p.setString(6, txtEnterPassword.getText());
				int update=p.executeUpdate();
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
			}
		}
		});
		btnSubmit.setBounds(331, 191, 105, 21);
		contentPane.add(btnSubmit);
		
		JLabel lblUserid = new JLabel("USER_ID:-");
		lblUserid.setBounds(238, 21, 74, 13);
		contentPane.add(lblUserid);
		
		txtEnterUserid = new JTextField();
		txtEnterUserid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterEmail.setText("");
					txtEnterEmail.requestFocus();
				}
			}
		});
		txtEnterUserid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterUserid.setText("");
			}
		});
		txtEnterUserid.setText("ENTER REGISTRATION NO");
		txtEnterUserid.setBounds(358, 18, 159, 19);
		contentPane.add(txtEnterUserid);
		txtEnterUserid.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:-");
		lblPassword.setBounds(239, 150, 91, 13);
		contentPane.add(lblPassword);
		
		txtEnterPassword = new JTextField();
		txtEnterPassword.setText("ENTER PASSWORD");
		txtEnterPassword.setBounds(358, 147, 159, 19);
		contentPane.add(txtEnterPassword);
		txtEnterPassword.setColumns(10);
		
		JLabel label = new JLabel("");
		Image i=new ImageIcon(this.getClass().getResource("/bgn.jpg")).getImage();
		label.setIcon(new ImageIcon(i));
		label.setBounds(0, 0, 228, 312);
		contentPane.add(label);
	}
}
