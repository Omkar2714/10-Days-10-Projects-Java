import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
	Container c;
	JLabel labName, labMsg;
	JTextField txtNum;
	JButton btnSq, btnSqroot, btnCube;

	Calculator() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		
		labName = new JLabel("Small Calculator App");
		txtNum = new JTextField(15);
		btnSq = new JButton("Square");
		btnSqroot = new JButton("Square root");
		btnCube = new JButton("Cube");
		labMsg = new JLabel();
		
		Font f = new Font("Times New Roman", Font.BOLD, 40);
		labName.setFont(f); 
		txtNum.setFont(f);
		btnSq.setFont(f);
		btnSqroot.setFont(f);
		btnCube.setFont(f);
		labMsg.setFont(f);

		c.add(labName); 
		c.add(txtNum);
		c.add(btnSq);
		c.add(btnSqroot);
		c.add(btnCube);
		c.add(labMsg);

		class Eh1 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				try{
					String n = txtNum.getText();
					double num = Double.parseDouble(n);
					double square = num * num;
					String msg = "Square is " + square;
					labMsg.setText(msg);
				} catch(NumberFormatException e) {
					String msg = "Please enter numbers only";
					JOptionPane.showMessageDialog(c, msg);
				}
 			}
		}
		
		btnSq.addActionListener(new Eh1());

		class Eh2 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				try {
					String n = txtNum.getText();
					double num = Double.parseDouble(n);
					double squarert = Math.sqrt(num);
					String sqrt = String.format("%.2f", squarert);
					String msg = "Square root is " + sqrt;
					labMsg.setText(msg);
				} catch(NumberFormatException e) {
					String msg = "Please enter numbers only";
					JOptionPane.showMessageDialog(c, msg);
				}
 			}
		}
		btnSqroot.addActionListener(new Eh2());	
	
		class Eh3 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				try{
					String n = txtNum.getText();
					double num = Double.parseDouble(n);
					double cube = num * num * num;
					String msg = "Cube is " + cube;
					labMsg.setText(msg);
				} catch(Exception e) {
					String msg = "Please enter numbers only";
					JOptionPane.showMessageDialog(c, msg);
				}
 			}
		}
		btnCube.addActionListener(new Eh3());

		setVisible(true);
		setSize(600 , 700);
		setTitle("Calculator App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}

class CalculatorTest {
	public static void main(String args[]) {
		Calculator c = new Calculator();
	}
}