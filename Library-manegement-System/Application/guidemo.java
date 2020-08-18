import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class guidemo {

	public JFrame frmLogin;
	public JTextField txtEnterId;
	private JPasswordField pwdEnterPassword;
	public String user_id=null;
	JButton btnLogIn = new JButton("LOG IN");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guidemo window = new guidemo();
					window.frmLogin.setVisible(true);
					textspeech t=new textspeech();
					t.main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guidemo() {
		initialize();
	}
	public Connection connection() {
		Connection con=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","lucky7538");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return con;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("LIBRARY MANAGEMENT SYSTEM");
		Image i2=new ImageIcon(this.getClass().getResource("/lms.jfif")).getImage();
		frmLogin.setIconImage(i2);
		frmLogin.setForeground(Color.MAGENTA);
		frmLogin.setResizable(true);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JToggleButton tglbtnDxz = new JToggleButton("user");
		tglbtnDxz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnDxz.getText().equals("user")) {
					tglbtnDxz.setText("admin");
				}
				else {
					tglbtnDxz.setText("user");
				}
			}
		});
		tglbtnDxz.setBounds(110, 88, 117, 21);
		frmLogin.getContentPane().add(tglbtnDxz);
		
		JLabel lblUserId = new JLabel("USER ID:-");
		lblUserId.setForeground(Color.YELLOW);
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserId.setBounds(10, 142, 85, 26);
		frmLogin.getContentPane().add(lblUserId);
		
		JLabel lblPassword = new JLabel("PASSWORD:-");
		lblPassword.setForeground(Color.YELLOW);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(10, 178, 115, 13);
		frmLogin.getContentPane().add(lblPassword);
		
		txtEnterId = new JTextField();
		txtEnterId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					pwdEnterPassword.requestFocus();
					pwdEnterPassword.setText("");
				}
			}
		});
		txtEnterId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterId.setText("");
			}
		});
		txtEnterId.setText("ENTER ID");
		txtEnterId.setBounds(110, 146, 217, 19);
		frmLogin.getContentPane().add(txtEnterId);
		txtEnterId.setColumns(10);

		pwdEnterPassword = new JPasswordField();
		pwdEnterPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnLogIn.doClick();
				}
			}
		});
		pwdEnterPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdEnterPassword.setText("");
			}
		});
		pwdEnterPassword.setText("ENTER PASSWORD");
		pwdEnterPassword.setBounds(110, 175, 193, 19);
		frmLogin.getContentPane().add(pwdEnterPassword);

		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 user_id=txtEnterId.getText();
				char[] pass=pwdEnterPassword.getPassword();
				String pass1=String.valueOf(pass);
				if(tglbtnDxz.getText()=="user") {
					try {
						Connection con=connection();
						Statement stmt=con.createStatement();
						String q="select * from USER_INFO where user_id='"+user_id+"' and password='"+pass1+"'";
						ResultSet rs=stmt.executeQuery(q);
						if(rs.next()) {
							validuser v=new validuser();
							 v.s=user_id;
							v.setVisible(true);
							frmLogin.dispose();
						}
						else {
							txtEnterId.setText("");
							pwdEnterPassword.setText("");
							JOptionPane.showMessageDialog(null, "invalid username or password");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				else {
					try {
						Connection con=connection();
						Statement stmt=con.createStatement();
						String q="select * from adminlogin where id='"+user_id+"' and password='"+pass1+"'";
						ResultSet rs=stmt.executeQuery(q);
						if(rs.next()) {
							Admistrator a=new Admistrator();
							a.setVisible(true);
							frmLogin.dispose();
						}
						else {
							txtEnterId.setText("");
							pwdEnterPassword.setText("");
							JOptionPane.showMessageDialog(null, "invalid username or password");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				
			}
		});
		btnLogIn.setBounds(82, 223, 96, 21);
		frmLogin.getContentPane().add(btnLogIn);
		
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newuser n=new newuser();
				frmLogin.dispose();
				n.setVisible(true);
			}
		});
		btnSignUp.setBounds(187, 223, 96, 21);
		frmLogin.getContentPane().add(btnSignUp);
		
		JLabel lblWelcomeToLbd = new JLabel("                  WELCOME TO LBD");
		lblWelcomeToLbd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWelcomeToLbd.setBounds(58, 20, 271, 32);
		frmLogin.getContentPane().add(lblWelcomeToLbd);
		
		JLabel lblNewLabel = new JLabel("");
		Image icon=new ImageIcon(this.getClass().getResource("/eyelabel1.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(icon));
		lblNewLabel.setBounds(302, 175, 24, 18);
		lblNewLabel.setVisible(false);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblhide = new JLabel("");
		lblhide.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblNewLabel.setVisible(true);
				pwdEnterPassword.setEchoChar((char)0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel.setVisible(false);
				pwdEnterPassword.setEchoChar('*');
			}
		});
		lblhide.setBounds(302, 175, 24, 18);
		Image icon1=new ImageIcon(this.getClass().getResource("/eye-label.png")).getImage();
		lblhide.setIcon(new ImageIcon(icon1));
		frmLogin.getContentPane().add(lblhide);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image i=new ImageIcon(this.getClass().getResource("/bg_login.jpeg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(i));
		lblNewLabel_1.setBounds(0, 0, 396, 472);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		
//		frmLogin.setResizable(false);

		frmLogin.setBounds(10, 10, 400, 500);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
