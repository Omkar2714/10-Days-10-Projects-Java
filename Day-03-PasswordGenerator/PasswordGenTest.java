import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;


class PasswordGen extends JFrame {
	Container c;
	JLabel labTitle, labMsg;
	JTextField txtPLen;
	JCheckBox cbUC, cbDi, cbSp;
	JButton btnGen;
	
	private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
   	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	private static final String DIGITS = "0123456789";
    	private static final String SPECIAL_CHARS = "!@#$%&_?";

	private static final SecureRandom random = new SecureRandom();


	PasswordGen() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 50));
		
		labTitle = new JLabel("Password Generator App"); 
		txtPLen = new JTextField(13);
		cbUC = new JCheckBox("include UpperCase");
		cbDi = new JCheckBox("include Digits");
		cbSp = new JCheckBox("include Special Characters");
		btnGen = new JButton("Generate");
		labMsg = new JLabel();
		
		Font f = new Font("Times New Roman", Font.BOLD, 25);
		labTitle.setFont(f);
		txtPLen.setFont(f);
		cbUC.setFont(f);
		cbDi.setFont(f);
		cbSp.setFont(f);
		btnGen.setFont(f);
		labMsg.setFont(f);
		
		c.add(labTitle);
		c.add(txtPLen);
		c.add(cbUC);
		c.add(cbDi);
		c.add(cbSp);
		c.add(btnGen);
		c.add(labMsg);

		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String l = txtPLen.getText();
			
				if(l.isBlank()) {
					String msg = "Please Provide Password Length ";
					JOptionPane.showMessageDialog(c, msg);
					return;
				} 
				try {
					int length = Integer.parseInt(l);

					StringBuilder pool = new StringBuilder(LOWERCASE);

        				if (cbUC.isSelected()) {
           	 				pool.append(UPPERCASE);
        				}

        				if (cbDi.isSelected()) {
            					pool.append(DIGITS);
        				}

					if (cbSp.isSelected()) {
        		    			pool.append(SPECIAL_CHARS);
       					}

        				if (pool.length() == 0) {
           					String msg = "No characters selected for the password pool.";
						JOptionPane.showMessageDialog(c, msg);
        				}

        				StringBuilder password = new StringBuilder(length);
        				for (int i = 0; i < length; i++) {
            					int index = random.nextInt(pool.length());
            					password.append(pool.charAt(index));
        				}

					try {
	        				String pwd = password.toString();
						labMsg.setText("Generated Password: " + pwd);
					} catch(Exception e) {
						String msg = "issue " + e;
						JOptionPane.showMessageDialog(c, msg);
					}
				} catch(NumberFormatException e) {
					String msg = "Please enter valid number";
					JOptionPane.showMessageDialog(c, msg);
				}
			}
		}
		btnGen.addActionListener(new MyEventHandler());

		setVisible(true);
		setTitle("Password Gen App");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
}

class PasswordGenTest {
	public static void main(String args[]) {
		PasswordGen pg = new PasswordGen();
	}
}