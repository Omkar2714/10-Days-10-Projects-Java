import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewFrame extends JFrame {
	Container c;
	JLabel labData;
	TextArea taViewData;
	JButton btnBack;
	
	ViewFrame() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		labData = new JLabel("Student Details:");
		taViewData = new TextArea(8, 15);
		btnBack = new JButton("Back to Main");
		
		Font f = new Font("Times New Roman", Font.BOLD, 30);
		labData.setFont(f);
		taViewData.setFont(f);
		btnBack.setFont(f);
		
		c.add(labData);
		c.add(taViewData);
		c.add(btnBack);
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					
			String user = System.getenv("DB_USER");
			String pwd = System.getenv("DB_PASSWORD");													String url = "jdbc:mysql://localhost:3306/sms21dec25";
			try(
				Connection con = DriverManager.getConnection(url, user, pwd);											PreparedStatement pst = con.prepareStatement("select * from student")) {
					ResultSet rs = pst.executeQuery();
					int rno;
					String name, msg;
					taViewData.setText("");
					while(rs.next()) {
						rno = rs.getInt("rno");
						name = rs.getString("name");
						msg = "rno = " + rno + " name = " + name + "\n";
						taViewData.append(msg);
						taViewData.append("------------------------------\n");
					}
	
				} catch(SQLException e) {
					String msg = "issue " + e;
					JOptionPane.showMessageDialog(c, msg);
				}
		} catch(SQLException e) {
			String msg = "issue " + e;
			JOptionPane.showMessageDialog(c, msg);	
		}

		
		class EH1 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				MainFrame mf = new MainFrame();
				dispose();		
			}
		}
		btnBack.addActionListener(new EH1());
		
		setTitle("View Student");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}