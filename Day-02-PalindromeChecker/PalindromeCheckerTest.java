import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class PalindromeChecker extends JFrame {

	Container c;
	JLabel labTitle, labMsg;
	JTextField txtWord;
	JButton btnCheck;

	PalindromeChecker() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		
		labTitle = new JLabel("Palindrome Checker App");
		txtWord = new JTextField(13);
		btnCheck = new JButton("Check");
		labMsg = new JLabel();
		
		Font f = new Font("Times New Roman", Font.BOLD, 40);
		labTitle.setFont(f);
		txtWord.setFont(f);
		btnCheck.setFont(f);
		labMsg.setFont(f);
		labMsg.setForeground(Color.BLUE);

		c.add(labTitle);
		c.add(txtWord);
		c.add(btnCheck);
		c.add(labMsg);
	
		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String w1 = txtWord.getText();
				
				if(w1.isBlank()) {
					String msg = "Enter a word please !!!";
					JOptionPane.showMessageDialog(c, msg);
					return;
				}
					
				String w2 = "";
				for(int i=(w1.length()-1); i>=0; i--)
					w2 = w2 + w1.charAt(i);
				
				if(w1.equals(w2)) {
					String msg = "It is palindrome. ";
					labMsg.setText(msg);
				} else {
					String msg = "It is not palindrome. ";
					labMsg.setText(msg);
				}
			}
		}	
		btnCheck.addActionListener(new MyEventHandler());

		class Wh extends WindowAdapter
		{
			public void windowClosing(WindowEvent e) {
				int op = JOptionPane.showConfirmDialog(c, "Do You Want to Exit? ");
				if(op == JOptionPane.YES_OPTION)
					System.exit(1);
			}
		}
		this.addWindowListener(new Wh());
	
		setVisible(true);
		setTitle("Palindrome Checker App");
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
}

class PalindromeCheckerTest {
	public static void main(String args[]) {
		PalindromeChecker pc = new PalindromeChecker();
	}
}