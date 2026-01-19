import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.time.*;
import java.io.*;

class BillGenerate extends JFrame {
	Container c;
	JLabel labHeader, labName, labItem, labQty, labPrice;
	JTextField txtName, txtItem, txtQty, txtPrice;
	JButton btnAddItem, btnGenBill;
	int qty;
	double price;
	double grandTotal = 0.0;
	boolean billStarted = false;

	BillGenerate() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 20));
		
		labHeader = new JLabel("Bill Generator App");
		labName = new JLabel("Enter Name: ");
		txtName = new JTextField(15);
		labItem = new JLabel("Enter Item Name: ");
		txtItem = new JTextField(15);
		labQty = new JLabel("Enter Quantity: ");
		txtQty = new JTextField(15);
		labPrice = new JLabel("Enter Price: ");
		txtPrice = new JTextField(15);
		btnAddItem = new JButton("Add Item");
		btnGenBill = new JButton("Generate Bill");

		Font f = new Font("Times New Roman", Font.BOLD, 27);
		labHeader.setFont(f);
		labHeader.setForeground(Color.BLUE);
		labName.setFont(f);
		txtName.setFont(f);
		labItem.setFont(f);
		txtItem.setFont(f);
		labQty.setFont(f);
		txtQty.setFont(f);
		labPrice.setFont(f);
		txtPrice.setFont(f);
		btnAddItem.setFont(f);
		btnGenBill.setFont(f);
		
		c.add(labHeader);
		c.add(labName);
		c.add(txtName);
		c.add(labItem);
		c.add(txtItem);
		c.add(labQty);
		c.add(txtQty);
		c.add(labPrice);
		c.add(txtPrice);
		c.add(btnAddItem);
		c.add(btnGenBill);

		class MyEventHandler implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String name = txtName.getText();
				if(name.isBlank()) {
					String msg = "Please enter your name";
					JOptionPane.showMessageDialog(c, msg);
					txtName.requestFocus();
					return;
				} 
				
				String iname = txtItem.getText();
				if(iname.isBlank()) {
					String msg = "Please enter item name";
					JOptionPane.showMessageDialog(c, msg);
					txtItem.requestFocus();
					return;
				} 	
				
				String q = txtQty.getText();
				if(q.isBlank()) {
					String msg = "Please enter quantity";
					JOptionPane.showMessageDialog(c, msg);
					txtQty.requestFocus();
					return;
				} 
				
				String pr = txtPrice.getText();
				if(pr.isBlank()) {
					String msg = "Please enter price";
					JOptionPane.showMessageDialog(c, msg);
					txtPrice.requestFocus();
					return;
				} 
	
				
				try {
					qty = Integer.parseInt(q);
					price = Double.parseDouble(pr);
				} catch(NumberFormatException e) {
					String msg = "Please Enter Numbers only";
					JOptionPane.showMessageDialog(c, msg);
					txtQty.setText("");
					txtPrice.setText("");
					txtQty.requestFocus();
				}
				
				String name1 = name + ".txt";
				Path p = Paths.get(name1);

				if (!billStarted) {
  					try (PrintWriter pw = new PrintWriter(
            					Files.newBufferedWriter(p, StandardOpenOption.CREATE))) {

        					LocalDateTime dt = LocalDateTime.now();
        					int bno = (int)(System.currentTimeMillis() % 100000);

        					pw.println("--------------------------------------------------");
        					pw.println("Bill No      : " + bno);
        					pw.println("Date         : " + dt);
        					pw.println("Customer Name: " + name);
        					pw.println();
        					pw.println("--------------------------------------------------");
        					pw.println("Item Name\tQty\tPrice\tTotal");
        					pw.println("--------------------------------------------------");
						billStarted = true;
    					} catch (Exception e) {
       						 JOptionPane.showMessageDialog(c, "File error: " + e);
    					}
				}

				double dtotal = price * qty;
				grandTotal += dtotal;

				try (PrintWriter pw = new PrintWriter(
        				Files.newBufferedWriter(p, StandardOpenOption.APPEND))) {

    					pw.println(iname + "\t\t" + qty + "\t" + price + "\t" + dtotal);

				} catch (Exception e) {
    					JOptionPane.showMessageDialog(c, "Append error: " + e);
				}
				txtItem.setText("");
				txtQty.setText("");
				txtPrice.setText("");
				txtItem.requestFocus();
			}
		}
		btnAddItem.addActionListener(new MyEventHandler());

		btnGenBill.addActionListener(e -> {
   			 try (PrintWriter pw = new PrintWriter(
           		 Files.newBufferedWriter(
                   		 Paths.get(txtName.getText() + ".txt"),
                   		 StandardOpenOption.APPEND))) {

       			 	 pw.println("--------------------------------------------------");
        			 pw.println("Total Bill Amount : " + grandTotal);
        			 pw.println("--------------------------------------------------");
        			 pw.println();
				

        			 JOptionPane.showMessageDialog(c, "Bill Generated!");

        		
        			grandTotal = 0.0;
        			billStarted = false;

    				} catch (Exception ex) {
        				JOptionPane.showMessageDialog(c, "Generate Bill error: " + ex);
   				}
		});


		setTitle("Bill Generator");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setBackground(new Color(245, 245, 245));
		setVisible(true);
	}
}

class BillGenerateTest {
	public static void main(String args[]) {
		BillGenerate bg = new BillGenerate();
	}
}