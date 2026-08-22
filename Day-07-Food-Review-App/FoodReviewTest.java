import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class FoodReview extends JFrame {
	Container c;
	JLabel labHeader, labName, labRating, labReview;
	JTextField txtName;
	JRadioButton rb1, rb2, rb3, rb4, rb5;
	TextArea taReview;
	JButton btnSubmit;
	
	FoodReview() {
		c = getContentPane();
		c.setLayout(null);
		
		labHeader = new JLabel("Food Review App");
		labName = new JLabel("Enter Name:");
		txtName = new JTextField(15);
		labRating = new JLabel("Select Rating:");
		rb1 = new JRadioButton("1");
		rb2 = new JRadioButton("2");
		rb3 = new JRadioButton("3");
		rb4 = new JRadioButton("4", true);
		rb5 = new JRadioButton("5");
		labReview = new JLabel("Review:");
		taReview = new TextArea(4, 30);
		btnSubmit = new JButton("Submit");
		
		Font f = new Font("Times New Roman", Font.BOLD, 40);
		labHeader.setFont(f);
		labName.setFont(f);
		txtName.setFont(f);
		labRating.setFont(f);
		rb1.setFont(f);
		rb2.setFont(f);
		rb3.setFont(f);
		rb4.setFont(f);
		rb5.setFont(f);
		labReview.setFont(f);
		taReview.setFont(f);
		btnSubmit.setFont(f);

		labHeader.setBounds(300, 20, 600, 50);
		labName.setBounds(50, 120, 300, 50);
		txtName.setBounds(400, 120, 300, 50);
		labRating.setBounds(50, 220, 300, 50);
		rb1.setBounds(400, 220, 50, 50);
		rb2.setBounds(450, 220, 50, 50);
		rb3.setBounds(500, 220, 50, 50);
		rb4.setBounds(550, 220, 50, 50);
		rb5.setBounds(600, 220, 50, 50);
		labReview.setBounds(50, 320, 300, 50);
		taReview.setBounds(400, 320, 300, 150);
		btnSubmit.setBounds(400, 520, 300, 50);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		bg.add(rb5);
		
		c.add(labHeader);
		c.add(labName);
		c.add(txtName);
		c.add(labRating);
		c.add(rb1);
		c.add(rb2);
		c.add(rb3);
		c.add(rb4);
		c.add(rb5);
		c.add(labReview);
		c.add(taReview);
		c.add(btnSubmit);
		
		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String food = txtName.getText();
				if(food.isBlank()) {
					String msg = "Food Name cannot be empty.";
					JOptionPane.showMessageDialog(c, msg);
					txtName.setText("");
					txtName.requestFocus();
					return;
				}
				
				String rating = "";
				if(rb1.isSelected())		rating = "1";
				else if(rb2.isSelected()) 	rating = "2";
				else if(rb3.isSelected()) 	rating = "3";
				else if(rb4.isSelected()) 	rating = "4";
				else if(rb5.isSelected()) 	rating = "5";
				
				String review = taReview.getText();
				
				try {
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String user = System.getenv("DB_USER");
					String pwd = System.getenv("DB_PASSWORD");
					String url = "jdbc:mysql://localhost:3306/food20jan26";
					
					try(
						Connection con = DriverManager.getConnection(url, user, pwd);
						PreparedStatement pst = con.prepareStatement("insert into person values(?,?,?)")){
					
					pst.setString(1, food);
					pst.setString(2, rating);
					pst.setString(3, review);
					pst.executeUpdate();
					String msg = "thank u for review :)";	
					JOptionPane.showMessageDialog(c, msg);
					txtName.setText("");
					rb4.setSelected(true);
					taReview.setText("");
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
			
		setTitle("Food Review App");
		setSize(800, 700);
		setLocation(200, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class FoodReviewTest {
	public static void main(String args[]) {
		FoodReview fr = new FoodReview();
	}
}