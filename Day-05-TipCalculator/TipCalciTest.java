import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TipCalci extends JFrame {
	Container c;
	JLabel labTitle, labBAmount, labExp,labMsg;
	JTextField txtBAmount;
	JComboBox<String> comboExp;
	JButton btnCalculate;
	
	TipCalci() {
		c = getContentPane();
		c.setLayout(null);

		labTitle = new JLabel("Tip Calculator");
		labBAmount = new JLabel("Enter Bill Amount: ");
		txtBAmount = new JTextField(13);
		labExp = new JLabel("How was your experience?");
		String items[] = {"Excellent", "OK", "Poor"};
		comboExp = new JComboBox<>(items);
		btnCalculate = new JButton("Calculate");
		labMsg = new JLabel();

		Font f = new Font("Times New Roman", Font.BOLD, 25);
		labTitle.setFont(f);
		labTitle.setForeground(Color.RED);
		labBAmount.setFont(f);
		txtBAmount.setFont(f);
		labExp.setFont(f);
		comboExp.setFont(f);
		btnCalculate.setFont(f);
		labMsg.setFont(f);

		labTitle.setBounds(40, 50, 200, 40);
		labBAmount.setBounds(40, 100, 300, 40);
		txtBAmount.setBounds(300, 100, 180, 40);
		labExp.setBounds(40, 150, 500, 40);
		comboExp.setBounds(40, 200, 350, 40);
		btnCalculate.setBounds(40, 270, 200, 40);
		labMsg.setBounds(40, 350, 300, 40);
		
		c.add(labTitle);
		c.add(labBAmount);
		c.add(txtBAmount);
		c.add(labExp);
		c.add(comboExp);
		c.add(btnCalculate);
		c.add(labMsg);

		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String ba = txtBAmount.getText();
				
				if(ba.isBlank()) {
					String msg = "Please Enter Bill Amount";
					JOptionPane.showMessageDialog(c, msg);
					txtBAmount.requestFocus();
					return;
				}
				
				double bAmount;
				try{
					bAmount = Double.parseDouble(ba);
					
					String choice = (String) comboExp.getSelectedItem();
					double fAmount = bAmount;
					switch(choice) {
						case "Excellent" -> fAmount = bAmount * 1.10; 
						case "OK" -> fAmount = bAmount * 1.05;
					}

					String msg = "Final Amount = " + String.format("%.2f", fAmount);
					labMsg.setText(msg);
				} catch(NumberFormatException e) {
					String msg = "Please enter numbers only";
					JOptionPane.showMessageDialog(c, msg);
				}

			}
		}
		btnCalculate.addActionListener(new MyEventHandler());

		setTitle("Tip Calci");
		setSize(500, 600);
		setLocationRelativeTo(null);
		c.setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class TipCalciTest {
	public static void main(String args[]) {
		TipCalci tc = new TipCalci();
	}
}