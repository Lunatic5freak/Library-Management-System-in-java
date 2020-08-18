import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTable;

public class validuser extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterBookName;
	JInternalFrame internalFrame = new JInternalFrame("REQUEST BOOK");
	private JTable table;
	public String s;
	public FileWriter f=null;
	guidemo g2=new guidemo();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					validuser frame = new validuser();
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
	public validuser() {
		setTitle("USER INTERFACE");
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.height,screenSize.width);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		guidemo g1=new guidemo();
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setText("                              WELCOME USER");
		lblNewLabel.setBounds(69, 22, 707, 29);
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(675, 5, 130, 22);
		JMenu options=new JMenu("Options");
		
		/*GENERATING THE ISSUED BOOK REPORT*/

		try {
		f=new FileWriter("report.txt");
		f.write("book isuued		issued date			user_id");
		f.write(System.lineSeparator());
		f.write("--------------------------------------------------");
		f.write(System.lineSeparator());

		}
		catch(Exception f1) {
			JOptionPane.showMessageDialog(null, f1);
		}
		JMenuItem Isuuedbook=new JMenuItem("Isuued report");
		Isuuedbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection con=g2.connection();
					Statement sm=con.createStatement();
					String q1="select * from issued_book where user_id='"+s+"'";
					ResultSet rs=sm.executeQuery(q1);
					
					while(rs.next()) {
						try {
							f.append(rs.getString("book_name"));
							f.append("\t");
							f.append("\t");
							f.append(rs.getString("issued_date"));
							f.append("\t");
							f.append("\t");
							f.append(rs.getString("user_id"));
							f.append(System.lineSeparator());
						}
						catch(Exception o) {
							JOptionPane.showMessageDialog(null, o);
						}
					}
					f.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		/*REQUESTING BOOK FROM THE LIBRARY*/
		JMenuItem reqbook=new JMenuItem("Request/Return book ");
		reqbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(true);
			}
		});
		/*LOGGING OUT FROM THE SYSTEM*/
		JMenuItem logout=new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "Are You Sure");
				if(a==0) {
					g1.frmLogin.setVisible(true);
					dispose();
				}
			}
		});
		options.add(Isuuedbook);
		options.add(reqbook);
		menuBar.add(options);
		menuBar.add(logout);
		
		contentPane.add(menuBar);
		
		internalFrame.setBounds(179, 277, 462, 159);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblBookName = new JLabel("BOOK NAME:-");
		lblBookName.setBounds(90, 27, 111, 19);
		internalFrame.getContentPane().add(lblBookName);
		
		txtEnterBookName =new JTextField();
		txtEnterBookName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterBookName.setText("");
			}
		});
		txtEnterBookName.setText("ENTER BOOK NAME");
		txtEnterBookName.setBounds(228, 27, 119, 19);
		internalFrame.getContentPane().add(txtEnterBookName);
		txtEnterBookName.setColumns(10);
		
		/*ADDING REQUESTED BOOK TO THE DATABASE*/
		JButton btnRequest = new JButton("REQUEST");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=g2.connection();
					Statement stmt=con.createStatement();
					String q="insert into req_book(user_id,book_name)"+"values(?,?)";
					try(PreparedStatement stm=con.prepareStatement(q)) {
						stm.setString(1, s);
						stm.setString(2, txtEnterBookName.getText());
						int update=stm.executeUpdate();
					}
					catch(Exception c) {
						JOptionPane.showMessageDialog(null, c);
					}
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnRequest.setBounds(53, 75, 111, 21);
		internalFrame.getContentPane().add(btnRequest);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
			}
		});
		btnClose.setBounds(193, 75, 96, 21);
		internalFrame.getContentPane().add(btnClose);
		
		JButton btnNewButton = new JButton("RETURN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=g2.connection();
					String q1="insert into returned_book(user_id,book_name)"+"values(?,?)";
					try(PreparedStatement sm=con.prepareStatement(q1)){
						sm.setString(1, s);
						sm.setString(2, txtEnterBookName.getText());
						int update=sm.executeUpdate();
					}
					catch(Exception c2) {
						JOptionPane.showMessageDialog(null, c2);
					}
				}
				catch(Exception c1) {
					JOptionPane.showMessageDialog(null, c1);
				}
			}
		});
		btnNewButton.setBounds(329, 75, 85, 21);
		internalFrame.getContentPane().add(btnNewButton);
		internalFrame.setVisible(false);
		
		/*CODE FOR SHOWING THE BOOKS OF THE LIBRARY*/
		JTable table= new JTable();
		table.setOpaque(false);
		try {
			Connection con=g2.connection();
			String q1="select * from book_info";
			try(PreparedStatement sm=con.prepareStatement(q1)){
				ResultSet rs=sm.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception c2) {
				JOptionPane.showMessageDialog(null, c2);
			}
		}
		catch(Exception c1) {
			JOptionPane.showMessageDialog(null, c1);
		}
		table.setBounds(23, 160, 805, 568);
		table.setVisible(true);
		contentPane.add(table);
		
		JLabel lblAvelableBooks = new JLabel("AVELABLE BOOKS:-");
		lblAvelableBooks.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAvelableBooks.setBounds(23, 119, 261, 31);
		contentPane.add(lblAvelableBooks);
		
		JLabel label = new JLabel("");
		Image i2=new ImageIcon(this.getClass().getResource("/bg.jpg")).getImage();
		label.setIcon(new ImageIcon(i2));
		label.setBounds(0, 0, 850, 837);
		contentPane.add(label);


	}
}
