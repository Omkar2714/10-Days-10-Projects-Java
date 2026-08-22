import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ProfileAppTest extends JFrame implements ActionListener {

    Container c;
    JLabel lblTitle, lblName, lblMobile, lblGender, lblQual;
    JTextField txtName, txtMobile, txtQual;
    JRadioButton rbMale, rbFemale;
    ButtonGroup bgGender;
    JButton btnSave;

    ProfileAppTest() {
        setTitle("Profile App");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.CYAN);


        lblTitle = new JLabel("PROFILE APP");
        c.add(lblTitle);

        lblName = new JLabel("Name");
	c.add(lblName);

        txtName = new JTextField();
        c.add(txtName);

        lblMobile = new JLabel("Mobile no.");
        c.add(lblMobile);

        txtMobile = new JTextField();
        c.add(txtMobile);

        lblGender = new JLabel("Gender");
        c.add(lblGender);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
	
        rbMale.setBackground(new Color(220, 245, 220));
        rbFemale.setBackground(new Color(220, 245, 220));


        bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);

        c.add(rbMale);
        c.add(rbFemale);

        lblQual = new JLabel("Qualification");
        c.add(lblQual);

        txtQual = new JTextField();
        c.add(txtQual);

	Font titleFont = new Font("Times New Roman", Font.BOLD, 36);
	Font fieldFont = new Font("Times New Roman", Font.PLAIN, 28);


	lblTitle.setFont(titleFont);
	lblTitle.setBounds(190, 20, 300, 50);

 	lblName.setFont(fieldFont);
	lblName.setBounds(80, 90, 200, 45);

	txtName.setFont(fieldFont);
	txtName.setBounds(280, 90, 240, 45);

	lblMobile.setFont(fieldFont);
	lblMobile.setBounds(80, 150, 200, 45);

	txtMobile.setFont(fieldFont);
	txtMobile.setBounds(280, 150, 240, 45);

	lblGender.setFont(fieldFont);
	lblGender.setBounds(80, 210, 200, 45);

	rbMale.setFont(fieldFont);
	rbMale.setBounds(280, 210, 120, 45);

	rbFemale.setFont(fieldFont);
	rbFemale.setBounds(420, 210, 140, 45);

	lblQual.setFont(fieldFont);
	lblQual.setBounds(80, 270, 200, 45);

	txtQual.setFont(fieldFont);
	txtQual.setBounds(280, 270, 240, 45);


        btnSave = new JButton("Save Profile");
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 26));
	btnSave.setBounds(250, 340, 200, 50);
	btnSave.addActionListener(this);
        c.add(btnSave);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText();
        String mobile = txtMobile.getText();
        String qual = txtQual.getText();

        String gender = "";
        if (rbMale.isSelected())
            gender = "Male";
        else if (rbFemale.isSelected())
            gender = "Female";

	String url = "jdbc:mysql://localhost:3306/prof22jan26";
	String user = System.getenv("DB_USER");
	String pwd = System.getenv("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    url,
                    user,
                    pwd
            );

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO profile(name, mobile, gender, qualification) VALUES (?,?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, gender);
            ps.setString(4, qual);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Profile Saved Successfully");

            con.close();

            txtName.setText("");
            txtMobile.setText("");
            txtQual.setText("");
            bgGender.clearSelection();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    public static void main(String[] args) {
        new ProfileAppTest();
    }
}
