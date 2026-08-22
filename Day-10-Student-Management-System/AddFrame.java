import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddFrame extends JFrame {
	Container c;
	JLabel labRno, labName;
	JTextField txtRno, txtName;
	JButton btnSave, btnBack;
	
	AddFrame() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		labRno = new JLabel("Enter Roll No:");
		txtRno = new JTextField(7);
		labName = new JLabel("Enter Student Name");
		txtName = new JTextField(15);
		btnSave = new JButton("Save Student");
		btnBack = new JButton("Back to Main");
		
		Font f = new Font("Times New Roman", Font.BOLD, 40);
		labRno.setFont(f);
		txtRno.setFont(f);
		labName.setFont(f);
		txtName.setFont(f);
		btnSave.setFont(f);
		btnBack.setFont(f);
				
		c.add(labRno);
		c.add(txtRno);
		c.add(labName);
		c.add(txtName);
		c.add(btnSave);
		c.add(btnBack);
		
		class EH1 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				try {
					int rno = Integer.parseInt(txtRno.getText());
				} catch(NumberFormatException e) {
					String msg = "Enter valid Roll Number ";
					JOptionPane.showMessageDialog(c, msg);
				}
				

				String name = txtName.getText();
				
				if(name.isBlank()){
					String msg = "Name cannot be empty";
					JOptionPane.showMessageDialog(c, msg);
					txtName.setText("");
					txtName.requestFocus();
					return;	
				}
			
				if( !name.matches("^[A-Za-z ]+$")){
					String msg = "Alphabets Only";
					JOptionPane.showMessageDialog(c, msg);
					txtName.setText("");
					txtName.requestFocus();
					return;	
				}	   
	
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					
					String user = System.getenv("DB_USER");
					String pwd = System.getenv("DB_PASSWORD");
					String url = "jdbc:mysql://localhost:3306/sms21dec25";
					
					try(
						Connection con = DriverManager.getConnection(url, user, pwd);
						PreparedStatement pst = con.prepareStatement("insert into student values(?, ?)")) {
						int rno = Integer.parseInt(txtRno.getText());
						pst.setInt(1, rno);
						pst.setString(2, name);
						pst.executeUpdate();
						String msg = "Student added successfully!!!";	
						JOptionPane.showMessageDialog(c, msg);
						txtRno.setText("");
						txtName.setText("");
						txtRno.requestFocus();			
					} catch(SQLException e) {
						String msg = "issue " + e;
						JOptionPane.showMessageDialog(c, msg);
					}
				} catch(SQLException e) {
					String msg = "issue " + e;
					JOptionPane.showMessageDialog(c, msg);
				}
			}
		}
		btnSave.addActionListener(new EH1());
		
		class EH2 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				MainFrame mf = new MainFrame();
				dispose();
			}
		}
		btnBack.addActionListener(new EH2());

		setTitle("Add Student");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}