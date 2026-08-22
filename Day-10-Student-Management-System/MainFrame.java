import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {
	Container c;
	JButton addBtn, viewBtn;

	MainFrame() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		addBtn = new JButton("Add Student");
		viewBtn = new JButton("View Student");
		
		Font f = new Font("Times New Roman", Font.BOLD, 40);
		addBtn.setFont(f);
		viewBtn.setFont(f);
		
		c.add(addBtn);
		c.add(viewBtn);
		
		class EH1 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				AddFrame af = new AddFrame();
				dispose(); // it closes the MainFrame window and new AddFrame window appears
			}
		} 
		addBtn.addActionListener(new EH1());
		
		class EH2 implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				ViewFrame vf = new ViewFrame();
				dispose(); // it closes the MainFrame window and new ViewFrame window appears
			}
		}
		viewBtn.addActionListener(new EH2()); 
		
		setTitle("Student Management System");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}