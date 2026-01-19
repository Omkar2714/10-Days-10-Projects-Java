import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BMICalci extends JFrame {
	Container c;
	JLabel labTitle, labMsg, labWeight, labHeight;
	JTextField txtWeight, txtHeight;
	JButton btnCalculate;

	BMICalci() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		
		labTitle = new JLabel("BMI Calculator App");  
		labHeight = new JLabel("Enter Height (cm): ");
		txtHeight = new JTextField(12);
		labWeight = new JLabel("Enter Weight (kg): ");
		txtWeight = new JTextField(12);
		btnCalculate = new JButton("Calculate BMI");
		labMsg = new JLabel();

		Font f = new Font("Times New Roman", Font.BOLD, 30);
		labTitle.setFont(f);
		labHeight.setFont(f);
		txtHeight.setFont(f);
		labWeight.setFont(f);
		txtWeight.setFont(f);
		btnCalculate.setFont(f);
		labMsg.setFont(f);

		c.add(labTitle);  
		c.add(labHeight);
		c.add(txtHeight);
		c.add(labWeight);
		c.add(txtWeight);
		c.add(btnCalculate);
		c.add(labMsg);

		class MyEventHandler implements ActionListener{
			public void actionPerformed(ActionEvent ae) {
				
				String h = txtHeight.getText();
				if(h.isBlank()) {
					JOptionPane.showMessageDialog(c, "Please Enter Height ");
					txtHeight.requestFocus();
					return;
				}

				String w = txtWeight.getText();
				if(w.isBlank()) {
					JOptionPane.showMessageDialog(c, "Please Enter Weight ");
					txtWeight.requestFocus();
					return;
				}

				try{
					double weight = Double.parseDouble(w);
					double height = Double.parseDouble(h);
					
					double bmi = weight / ((height/100.0) * (height/100.0));
					
					String msg;
					if (bmi < 18.5)
          					msg = "Under Weight.";
       					else if (bmi < 25)
         				 	msg = "Normal Weight.";
       					else if (bmi < 30)
            					msg = "Over Weight.";
        				else
            					msg = "Obese.";
					String b = String.format("%.2f", bmi);
					labMsg.setText("BMI : " + b + " " + msg);
				} catch(NumberFormatException e) {
					String msg = "Please Enter Numbers Only";
					JOptionPane.showMessageDialog(c, msg);
				}
			}
		}
		btnCalculate.addActionListener(new MyEventHandler());
		
		setVisible(true);
		setTitle("BMI Calculator");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.CYAN);
	}
}

class BMICalciTest {
	public static void main(String args[]) {
		BMICalci bc = new BMICalci();
	}
}