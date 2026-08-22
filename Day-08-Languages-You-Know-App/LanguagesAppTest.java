import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class LanguagesApp extends JFrame {
	Container c;
	JLabel labName, labSelect, labMsg;
	JTextField txtName;
	JCheckBox cbPy, cbJs, cbJa;
	JButton btnSubmit;

	LanguagesApp() {
		c = getContentPane();
		c.setLayout(null);
		
		labName = new JLabel("Enter Name ");
		txtName = new JTextField(15);
		labSelect = new JLabel("Select Languages ");
		cbPy = new JCheckBox("Python");
		cbJs = new JCheckBox("JavaScript");
		cbJa = new JCheckBox("Java");
		btnSubmit = new JButton("Submit");
		labMsg = new JLabel();
		
		Font f = new Font("Times New Roman", Font.BOLD, 30);
		labName.setFont(f);
		txtName.setFont(f);
		labSelect.setFont(f);
		cbPy.setFont(f);
		cbJs.setFont(f);
		cbJa.setFont(f);
		btnSubmit.setFont(f);
		labMsg.setFont(f);
		
		labName.setBounds(50, 50, 200, 40);
		txtName.setBounds(300, 50, 300, 40);
		labSelect.setBounds(50, 150, 300, 40);
		cbPy.setBounds(300, 150, 200, 40);
		cbJs.setBounds(300, 200, 300, 40);	
		cbJa.setBounds(300, 250, 300, 40);
		btnSubmit.setBounds(300, 320, 300, 40);
		labMsg.setBounds(300, 400, 800, 40);
		
		c.add(labName);
		c.add(txtName);
		c.add(labSelect);
		c.add(cbPy);
		c.add(cbJs);
		c.add(cbJa);
		c.add(btnSubmit);
		c.add(labMsg);

		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String name = txtName.getText();
				if(name.isBlank()) {
					JOptionPane.showMessageDialog(c, "Please enter your name first");
					txtName.requestFocus();
					return;
				}

				if( ! name.matches("^[A-Za-z ]+$")) {
					JOptionPane.showMessageDialog(c, "Name should contain alphabets only");
					txtName.setText("");
					txtName.requestFocus();
					return;
				}
				
				String languages = "";
				if(cbPy.isSelected())		languages += " Python";
				if(cbJs.isSelected())		languages += " JavaScript";
				if(cbJa.isSelected())		languages += " Java";

				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					
					String user = System.getenv("DB_USER");
					String pwd = System.getenv("DB_PASSWORD");
					String url = "jdbc:mysql://localhost:3306/lang21jan26";
					
					try(
						Connection con = DriverManager.getConnection(url, user, pwd);
						PreparedStatement pst = con.prepareStatement("insert into person values(?, ?)")) {
						pst.setString(1, name);
						pst.setString(2, languages);
						pst.executeUpdate();
						labMsg.setText("records created");
						txtName.setText("");
						txtName.requestFocus();
						
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
		btnSubmit.addActionListener(new MyEventHandler());

		setVisible(true);
		setTitle("Languages You Know App");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class LanguagesAppTest {
	public static void main() {
		LanguagesApp la = new LanguagesApp();
	}
}