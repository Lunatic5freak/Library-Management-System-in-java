import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.sql.Date;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;

import javax.swing.event.MenuEvent;
import javax.swing.JMenuBar;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Panel;
import java.awt.TextField;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

public class Admistrator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNameOfThe;
	private JTextField txtEnterAuthorName;
	private JTextField txtEnterCategory;
	private JInternalFrame internalFrame=new JInternalFrame();
	private JTextField txtEnterBookId;
	private JInternalFrame internalFrame_1 = new JInternalFrame("DELETE BOOK");
	private JTextField txtEnterUserId;
	JInternalFrame internalFrame_2 = new JInternalFrame("REMOVE USER");
	JInternalFrame internalFrame_3=new JInternalFrame("REQUEST BOOK");
	JInternalFrame internalFrame_4 = new JInternalFrame("MODIFY");
	JInternalFrame internalFrame_5 = new JInternalFrame("UPDATE");
	JInternalFrame internalFrame_6 = new JInternalFrame("ISSUE");
	private JTextField txtEnterBookId_1;
	private JTextField txtEnterBookId_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	ResultSet rs;
	JTable table = new JTable();
	JTable table_1=new JTable();
	JTable table_2=new JTable();
	JTable table_3=new JTable();
	JTable table_4=new JTable();
	private JTextField txtEnterUserid_0;
	private JTextField txtEnterBookName;
	guidemo g2=new guidemo();
	private void show_issued_book()
	{
		try {
			Connection con=g2.connection();
			String q1="select * from issued_book";
			try(PreparedStatement sm=con.prepareStatement(q1)){
				ResultSet rs=sm.executeQuery();
				table_2.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception c2) {
				JOptionPane.showMessageDialog(null, c2);
			}
		}
		catch(Exception c1) {
			JOptionPane.showMessageDialog(null, c1);
		}
	}
	
	private void show_book() {
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
	}
	
	private void show_user() {
		try {
			Connection con=g2.connection();
			String q1="select * from user_info";
			try(PreparedStatement sm=con.prepareStatement(q1)){
				ResultSet rs=sm.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception c2) {
				JOptionPane.showMessageDialog(null, c2);
			}
		}
		catch(Exception c1) {
			JOptionPane.showMessageDialog(null, c1);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admistrator frame = new Admistrator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public Admistrator() throws SQLException {

		setTitle("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.height,d.width);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("WELCOME ADMIN");
		lblWelcomeAdmin.setAutoscrolls(true);
		lblWelcomeAdmin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblWelcomeAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblWelcomeAdmin.setBounds(275, 27, 282, 28);
		contentPane.add(lblWelcomeAdmin);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		JMenu menu=new JMenu("OPTION");
		JMenu add=new JMenu("ADD");
		JMenu delete=new JMenu("REMOVE");
		JMenuItem puser=new JMenuItem("user");
		JMenuItem show_user=new JMenuItem("SHOW USER INFO");
		JMenuItem issue_book=new JMenuItem("ISSUE/REMOVE BOOK");
		issue_book.addActionListener(new ActionListener() {
			
		 public void actionPerformed(ActionEvent e) {
			 internalFrame.setVisible(false);
			 internalFrame_6.setVisible(true);
			 table.setVisible(false);
			 table_1.setVisible(false);
			 table_2.setVisible(true);
		 }
		});
		show_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setVisible(false);
				table_1.setVisible(true);
			}
		});
		JMenuItem reqbook=new JMenuItem("Show Requested Book");
		reqbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_3.setVisible(true);
				table.setVisible(false);
				table_1.setVisible(false);
				table_2.setVisible(false);
			}
		});
		puser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_2.setVisible(true);
			}
		});
		JMenuItem pbook=new JMenuItem("book");
		pbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_1.setVisible(true);
			}
		});
		JMenuItem user= new JMenuItem("user");
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newuser n=new newuser();
				n.setVisible(true);
				n.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		JMenuItem book= new JMenuItem("book");
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(true);
			}
		});
		JMenuItem returned_book=new JMenuItem("Show Returned Book");
		returned_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_4.setVisible(true);
				table.setVisible(false);
				table_1.setVisible(false);
				table_2.setVisible(false);
				table_3.setVisible(false);
			}
		});
		
		add.add(user);
		add.add(book);
		delete.add(puser);
		delete.add(pbook);
		menu.add(add);
		menu.add(delete);
		menu.add(reqbook);
		menu.add(show_user);
		menu.add(issue_book);
		menu.add(returned_book);
		menuBar.add(menu);
		
		JMenuItem mntmModify = new JMenuItem("MODIFY");
		mntmModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_4.setVisible(true);
				internalFrame.setVisible(false);
			}
		});
		menu.add(mntmModify);
		JMenuItem logout=new JMenuItem("LOGOUT");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guidemo g=new guidemo();
				int a=JOptionPane.showConfirmDialog(null, "Are You Sure");
				if(a==0) {
					g.frmLogin.setVisible(true);
					dispose();
				}
			}
		});
		menuBar.add(logout);
		menuBar.setBounds(700, 10, 125
				, 22);
		contentPane.add(menuBar);
		
		internalFrame.setTitle("ADD BOOK");
		internalFrame.setBounds(170, 0, 492, 339);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("NAME:-");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(84, 82, 71, 27);
		internalFrame.getContentPane().add(lblName);
		
		txtNameOfThe = new JTextField();
		txtNameOfThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterAuthorName.requestFocus();
					txtEnterAuthorName.setText("");
				}
			}
		});
		txtNameOfThe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNameOfThe.setText("");
			}
		});
		txtNameOfThe.setText("NAME OF THE BOOK");
		txtNameOfThe.setBounds(233, 88, 210, 19);
		internalFrame.getContentPane().add(txtNameOfThe);
		txtNameOfThe.setColumns(10);
		
		JLabel lblAuthorName = new JLabel("AUTHOR NAME:-");
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthorName.setBounds(84, 132, 112, 13);
		internalFrame.getContentPane().add(lblAuthorName);
		
		JLabel lblCategroy = new JLabel("CATEGROY:-");
		lblCategroy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategroy.setBounds(84, 177, 85, 13);
		internalFrame.getContentPane().add(lblCategroy);
		
		txtEnterAuthorName = new JTextField();
		txtEnterAuthorName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterAuthorName.setText("");
			}
		});
		txtEnterAuthorName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterCategory.setText("");
					txtEnterCategory.requestFocus();
				}
			}
		});
		txtEnterAuthorName.setText("ENTER AUTHOR NAME");
		txtEnterAuthorName.setBounds(233, 131, 210, 19);
		internalFrame.getContentPane().add(txtEnterAuthorName);
		txtEnterAuthorName.setColumns(10);
		
		txtEnterCategory = new JTextField();
		txtEnterCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterCategory.setText("");
			}
		});
		txtEnterCategory.setText("ENTER CATEGORY");
		txtEnterCategory.setBounds(233, 176, 210, 19);
		internalFrame.getContentPane().add(txtEnterCategory);
		txtEnterCategory.setColumns(10);
		
		JLabel lblBookId_1 = new JLabel("BOOK ID:-");
		lblBookId_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookId_1.setBounds(84, 59, 71, 13);
		internalFrame.getContentPane().add(lblBookId_1);
		
		txtEnterBookId_1 = new JTextField();
		txtEnterBookId_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtNameOfThe.requestFocus();
					txtNameOfThe.setText("");
				}
			}
		});
		txtEnterBookId_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterBookId_1.setText("");
			}
		});
		txtEnterBookId_1.setText("ENTER BOOK ID");
		txtEnterBookId_1.setBounds(233, 53, 210, 19);
		internalFrame.getContentPane().add(txtEnterBookId_1);
		txtEnterBookId_1.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b=txtNameOfThe.getText();
				String n,c;
				n=txtEnterAuthorName.getText();
				c=txtEnterCategory.getText();
				int i=Integer.parseInt(	txtEnterBookId_1.getText());
				try {
					Connection con=g2.connection();
					String q="insert into book_info (book_id,book_name,author_name,category,status)"+"values(?,?,?,?,?)";
					try(PreparedStatement p=con.prepareStatement(q)){
						p.setInt(1, i);
						p.setString(2, b);
						p.setString(3, n);
						p.setString(4, c);
						p.setString(5, "A/B");
						int update=p.executeUpdate();
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
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
			}
		});
		btnAdd.setBounds(153, 244, 85, 21);
		internalFrame.getContentPane().add(btnAdd);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
			}
		});
		btnClose.setBounds(278, 244, 85, 21);
		internalFrame.getContentPane().add(btnClose);
		

		internalFrame_1.setBounds(26, 447, 350, 165);
		contentPane.add(internalFrame_1);
		internalFrame_1.getContentPane().setLayout(null);
		
		JLabel lblBookId = new JLabel("BOOK ID:-");
		lblBookId.setBounds(10, 25, 58, 40);
		internalFrame_1.getContentPane().add(lblBookId);
		
		txtEnterBookId = new JTextField();
		txtEnterBookId.setBounds(89, 26, 239, 40);
		txtEnterBookId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterBookId.setText("");
			}
		});
		txtEnterBookId.setText("ENTER BOOK ID");
		internalFrame_1.getContentPane().add(txtEnterBookId);
		txtEnterBookId.setColumns(10);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Connection con=g2.connection();
				Statement stmt=con.createStatement();
				try {
				String q="delete from book_info where book_id='"+txtEnterBookId.getText()+"'";
				int update=stmt.executeUpdate(q);
				JOptionPane.showMessageDialog(null, "deleted successfully");
					}
				catch(Exception e5) {
					JOptionPane.showMessageDialog(null, e5);
				}
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				show_book();
				internalFrame_1.dispose();
				table.setVisible(true);
			}
		});
		btnDelete.setBounds(70, 90, 87, 21);
		internalFrame_1.getContentPane().add(btnDelete);
		
		JButton btnClose_1 = new JButton("CLOSE");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_1.setVisible(false);
			}
		});
		btnClose_1.setBounds(195, 90, 85, 21);
		internalFrame_1.getContentPane().add(btnClose_1);
		

		internalFrame_2.setResizable(true);
		internalFrame_2.setBounds(386, 447, 376, 165);
		contentPane.add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("USER ID:-");
		lblUserId.setBounds(42, 36, 74, 13);
		internalFrame_2.getContentPane().add(lblUserId);
		
		txtEnterUserId = new JTextField();
		txtEnterUserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterUserId.setText("");
			}
		});
		txtEnterUserId.setText("ENTER USER ID");
		txtEnterUserId.setBounds(138, 33, 158, 19);
		internalFrame_2.getContentPane().add(txtEnterUserId);
		txtEnterUserId.setColumns(10);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=g2.connection();
				Statement stmt=con.createStatement();
				try {
				String q="delete from user_info where user_id='"+txtEnterUserId.getText()+"'";
				int update=stmt.executeUpdate(q);
				JOptionPane.showMessageDialog(internalFrame_2, "deleted successfully");
					}
				catch(Exception e5) {
					JOptionPane.showMessageDialog(null, e5);
				}
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				show_user();
			}
		});
		btnRemove.setBounds(74, 87, 103, 21);
		internalFrame_2.getContentPane().add(btnRemove);
		
		JButton btnClose_2 = new JButton("CLOSE");
		btnClose_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_2.setVisible(false);
			}
		});
		btnClose_2.setBounds(197, 87, 85, 21);
		internalFrame_2.getContentPane().add(btnClose_2);
		
		internalFrame_4.setBounds(55, 319, 264, 96);
		contentPane.add(internalFrame_4);
		internalFrame_4.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOKID:-");
		lblNewLabel.setBounds(22, 10, 85, 13);
		internalFrame_4.getContentPane().add(lblNewLabel);
		
		txtEnterBookId_2 = new JTextField();
		txtEnterBookId_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterBookId_2.setText("");
			}
		});
		txtEnterBookId_2.setText("ENTER BOOK ID");
		txtEnterBookId_2.setBounds(106, 7, 146, 19);
		internalFrame_4.getContentPane().add(txtEnterBookId_2);
		txtEnterBookId_2.setColumns(10);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=g2.connection();
					Statement stmt=con.createStatement();
					String s1="select * from book_info where book_id='"+txtEnterBookId_2.getText()+"'";
					rs=stmt.executeQuery(s1);
					while(rs.next()) {
					textField.setText(rs.getString(2));
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					textField_3.setText(rs.getString(5));
					}
				}
				catch(Exception r) {
					JOptionPane.showMessageDialog(null, r);
				}
				internalFrame_5.setVisible(true);
				internalFrame_4.dispose();
			}
		});
		btnEnter.setBounds(32, 36, 99, 21);
		internalFrame_4.getContentPane().add(btnEnter);
		
		JButton btnCancel_2 = new JButton("CANCEL");
		btnCancel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_4.dispose();
			}
		});
		btnCancel_2.setBounds(140, 36, 85, 21);
		internalFrame_4.getContentPane().add(btnCancel_2);
		
		internalFrame_5.setBounds(10, 62, 325, 195);
		contentPane.add(internalFrame_5);
		internalFrame_5.getContentPane().setLayout(null);
		
		JLabel lblBookName = new JLabel("BOOK NAME:-");
		lblBookName.setBounds(29, 10, 66, 13);
		internalFrame_5.getContentPane().add(lblBookName);
		textField = new JTextField();
		textField.setBounds(130, 7, 132, 19);
		internalFrame_5.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAuthorName_1 = new JLabel("AUTHOR NAME:-");
		lblAuthorName_1.setBounds(29, 33, 79, 13);
		internalFrame_5.getContentPane().add(lblAuthorName_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 30, 132, 19);
		internalFrame_5.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCategory = new JLabel("CATEGORY:-");
		lblCategory.setBounds(29, 57, 79, 13);
		internalFrame_5.getContentPane().add(lblCategory);
		
		textField_2 = new JTextField();
		textField_2.setBounds(130, 54, 132, 19);
		internalFrame_5.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblstatus = new JLabel("STATUS:-");
		lblstatus.setBounds(29, 80, 66, 13);
		internalFrame_5.getContentPane().add(lblstatus);
		
		textField_3 = new JTextField();
		textField_3.setBounds(130, 83, 132, 19);
		internalFrame_5.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnModify = new JButton("MODIFY");	
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s2=txtEnterBookId_2.getText();
				String s3=textField.getText();
				String s4=textField_1.getText();
				String s5=textField_2.getText();
				String s6=textField_3.getText();
				try {
					Connection con=g2.connection();
					Statement stmt=con.createStatement();
					String s1="update book_info set book_name='"+s3+"',author_name='"+s4+"' ,category='"+s5+"' ,status='"+s6+"' where book_id='"+s2+"'";
					stmt.executeUpdate(s1);
				}
				catch(Exception e10) {
					JOptionPane.showMessageDialog(null, e10);
				}
				show_book();
				internalFrame_5.dispose();
				internalFrame_4.setVisible(true);
			}
		});
		btnModify.setBounds(40, 118, 85, 21);
		internalFrame_5.getContentPane().add(btnModify);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_5.dispose();
			}
		});
		btnCancel.setBounds(154, 118, 85, 21);
		internalFrame_5.getContentPane().add(btnCancel);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLUE);
		
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		show_book();
		table.setBounds(10, 393, 830, 443);
		contentPane.add(table);
		table_1.setName("USER_INFO");
		table_1.setEnabled(false);
		show_user();
		table_1.setBounds(10, 393,830,443);
		table_1.setVisible(false);
		contentPane.add(table_1);
		internalFrame_6.setBounds(485, 230, 365, 153);
		contentPane.add(internalFrame_6);
		internalFrame_6.setBackground(Color.GREEN);
		internalFrame_6.getContentPane().setLayout(null);
		
		JLabel lblUserid = new JLabel("USER_ID:-");
		lblUserid.setBounds(10, 55, 79, 13);
		internalFrame_6.getContentPane().add(lblUserid);
		
		txtEnterUserid_0= new JTextField();
		txtEnterUserid_0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					txtEnterUserId.requestFocus();
					txtEnterUserId.setText("");				}
			}
		});
		txtEnterUserid_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterUserId.setText("");
			}
		});
		txtEnterUserid_0.setText("ENTER USER_ID");
		txtEnterUserid_0.setBounds(117, 52, 129, 19);
		internalFrame_6.getContentPane().add(txtEnterUserid_0);
		txtEnterUserid_0.setColumns(20);
		
		JLabel lblBookName_1 = new JLabel("BOOK NAME:-");
		lblBookName_1.setBounds(10, 10, 97, 13);
		internalFrame_6.getContentPane().add(lblBookName_1);
		
		txtEnterBookName = new JTextField();
		txtEnterBookName.setText("ENTER BOOK NAME");
		txtEnterBookName.setBounds(117, 7, 129, 19);
		internalFrame_6.getContentPane().add(txtEnterBookName);
		txtEnterBookName.setColumns(10);
		
		JButton btnIssue = new JButton("ISSUE");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDate d=LocalDate.now();
					Date d1=Date.valueOf(d);
					Connection con=g2.connection();
					String q1="insert into issued_book(book_name,user_id,issued_date)"+ "values(?,?,?)";
					try(PreparedStatement p=con.prepareStatement(q1)){
						p.setString(1, txtEnterBookName.getText());
						p.setString(2, txtEnterUserid_0.getText());
						p.setDate(3, d1);
						int update=p.executeUpdate();
						con.close();
					}
					catch(Exception e12) {
						JOptionPane.showMessageDialog(null, e12);
					}
				}
				catch(Exception e13) {
					JOptionPane.showMessageDialog(null, e13);
				}
				show_issued_book();
			}
		});
		btnIssue.setBounds(80, 93, 85, 21);
		internalFrame_6.getContentPane().add(btnIssue);
		JButton btnRemove_1 = new JButton("REMOVE");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=g2.connection();
					String q1="delete from issued_book where book_name='"+txtEnterBookName.getText()+"'";
					try(PreparedStatement p=con.prepareStatement(q1)){
						int update=p.executeUpdate();
						con.close();
					}
					catch(Exception e12) {
						JOptionPane.showMessageDialog(null, e12);
					}
				}
				catch(Exception e13) {
					JOptionPane.showMessageDialog(null, e13);
				}
				show_issued_book();
			}
		});
		btnRemove_1.setSize(87, 28);
		btnRemove_1.setLocation(256, 32);
		internalFrame_6.getContentPane().add(btnRemove_1);
		
		JButton btnCancel_1 = new JButton("CANCEL");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame_6.dispose();
				table.setVisible(true);
				table_1.setVisible(true);
			}
		});
		btnCancel_1.setBounds(258, 93, 85, 21);
		internalFrame_6.getContentPane().add(btnCancel_1);
		table_2.setBounds(10, 393, 830, 485);
		contentPane.add(table_2);
		table_2.setEnabled(false);
		show_issued_book();
		
		table_3.setBounds(10, 393, 830, 497);
		contentPane.add(table_3);
		table_3.setEnabled(false);
		try {
			Connection con=g2.connection();
			String q1="select * from req_book";
			try(PreparedStatement sm=con.prepareStatement(q1)){
				ResultSet rs=sm.executeQuery();
				table_3.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception c2) {
				JOptionPane.showMessageDialog(null, c2);
			}
		}
		catch(Exception c1) {
			JOptionPane.showMessageDialog(null, c1);
		}
		
		table_4.setForeground(Color.BLACK);
		table_4.setBackground(Color.WHITE);
		
		table_4.setEnabled(false);
		try {
			Connection con=g2.connection();
			String q1="select * from returned_book";
			try(PreparedStatement sm=con.prepareStatement(q1)){
				ResultSet rs=sm.executeQuery();
				table_4.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(Exception c2) {
				JOptionPane.showMessageDialog(null, c2);
			}
		}
		catch(Exception c1) {
			JOptionPane.showMessageDialog(null, c1);
		}
		table_4.setBounds(10, 393, 830, 443);
		contentPane.add(table_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image i1=new ImageIcon(this.getClass().getResource("/bg.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(i1));
		lblNewLabel_1.setBounds(0, 0, 864, 1536);
		contentPane.add(lblNewLabel_1);
		

		
		internalFrame_6.setVisible(false);
		internalFrame_5.setVisible(false);
		internalFrame_4.setVisible(false);
		internalFrame_3.setLocation(156, 255);
		internalFrame_3.setSize(376, 165);
		internalFrame.setVisible(true);
		internalFrame_3.setVisible(true);
		internalFrame_3.getContentPane().setLayout(null);
		
		
	}
}
