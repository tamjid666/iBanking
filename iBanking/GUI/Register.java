package GUI;

import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import Entity.*; 

public class Register extends JFrame implements ActionListener{

	Random rand = new Random();

	

	JPanel panel;
	JLabel registerHeader, userName , password ; //backgroundImgLabel,
	
	JLabel name , email , mobile , gender; 
	JTextField nameField , emailField , mobileNoField , usernameField ; 
	JComboBox<String> genderBox;

    JPasswordField userPasswordField;
    JButton backBtn, registerBtn;
	
	Login loginPage;

	// fonts
	Font font = new Font("poppins", Font.PLAIN, 19);
	Font boldFont = new Font("roboto", Font.BOLD, 35);

	// colors
	Color greenColor = new Color(0,204,0);
	Color blueColor = new Color(25, 113, 255);
	Color redColor = new Color(250, 20, 20);
	
	public Register(Login loginPage){
		//frame show 
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1920, 1080);
		panel.setBackground(new Color(240, 245, 245));
		this.add(panel);

		this.setVisible(true);
		this.setTitle("iBanking - Register_Create_AC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(920, 530);
		this.setLocation(310, 120);
		this.setLayout(null);
		this.setIconImage(new ImageIcon("./Assets/logo.png").getImage());
		this.loginPage = loginPage;
		this.setResizable(false);

		//Top header
		registerHeader = new JLabel("REGISTER HERE");
		registerHeader.setBounds(300, 20, 300, 40);
		registerHeader.setFont(boldFont);
		registerHeader.setForeground(new Color(25, 113, 255));
		this.add(registerHeader);

		//all data taken boxes
		//left side data 
	    name = new JLabel("NAME");
	    name.setBounds(60,80,250,40);
	    name.setFont(font);
	    this.add(name);

	    nameField = new JTextField();
	    nameField.setBounds(60,120,340,40);
		nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	    nameField.setFont(font);
	    this.add(nameField);
		
	    email = new JLabel("Email");
	    email.setBounds(60,180,250,40);
	    email.setFont(font);
	    this.add(email);

	    emailField = new JTextField("@gmail.com");
	    emailField.setBounds(60,220,340,40);
		emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	    emailField.setFont(font);
	    this.add(emailField);

		gender = new JLabel("Gender");
		gender.setBounds(60,280,250, 40);
		gender.setFont(font);
		this.add(gender);

		String[] genders = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setBounds(60,320,340,40);
		genderBox.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        this.add(genderBox);

		//Right side data 
		userName = new JLabel("USER NAME");
		userName.setBounds(520,80,250,40);
	    userName.setFont(font);
	    this.add(userName);

	    usernameField = new JTextField();
	    usernameField.setBounds(520,120,340,40);
	    usernameField.setFont(font);
		usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	    this.add(usernameField);

		mobile = new JLabel("MOBILE NO.");
		mobile.setBounds(520,180,340,40);
	    mobile.setFont(font);
	    this.add(mobile);

	    mobileNoField = new JTextField();
	    mobileNoField.setBounds(520,220,340,40);
	    mobileNoField.setFont(font);
		mobileNoField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	    this.add(mobileNoField);

	    password = new JLabel("CREATE PASSWORD");
	    password.setBounds(520,280,250,40);
	    password.setFont(font);
	    this.add(password);

	    userPasswordField = new JPasswordField();
	    userPasswordField.setBounds(520,320,340,40);
	    userPasswordField.setFont(font);
		userPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
	    this.add(userPasswordField);
		
	    //Back  Btn
	    backBtn = new JButton("< Back");
	    backBtn.setBounds(20,440,100,35);
	    backBtn.setFont(font);
	    backBtn.setForeground(Color.WHITE);
	    backBtn.setBackground(redColor);
	    backBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		backBtn.addActionListener(this);
		backBtn.setFocusable(false); 
	    this.add(backBtn);

	    //Register  Btn
	    registerBtn = new JButton("Register");
	    registerBtn.setBounds(650,440, 220, 40);
	    registerBtn.setFont(font);
	    registerBtn.setForeground(Color.WHITE);
	    registerBtn.setBackground(new Color(123,104,238));
	    registerBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
	    registerBtn.addActionListener(this);
		registerBtn.setFocusable(false); 
	    this.add(registerBtn);
	}
	
	public void actionPerformed(ActionEvent e){

		String name = nameField.getText();
		String email = emailField.getText(); 
		String gender = (String) genderBox.getSelectedItem();
		String mobile = mobileNoField.getText(); 
		String uname = usernameField.getText();
		String upass = String.valueOf(userPasswordField.getPassword());
		int randNo = rand.nextInt((int)1e5);
		String sRand = Integer.toString(randNo);


		User user = new User(name,email,gender,mobile,uname,upass,sRand); 

		System.out.println("User test : "+user.getUname()); 
		System.out.println("User test : "+user.getUpass()); 
		System.out.println("User test : "+user.getName()); 
		System.out.println("User test : "+user.getEmail()); 
		System.out.println("User test : "+user.getMobile()); 
		System.out.println("User test : "+user.getGender()); 

		if(registerBtn == e.getSource()){
			if(name.isEmpty() || email.isEmpty() ||
				mobile.isEmpty() || upass.isEmpty() || 
				name.isEmpty()){
					JOptionPane.showMessageDialog(this, "Please fill all fields!");
					return;
				}

			// System.out.println("accept registation ");
			// System.out.println("User name : " + uname );
			// System.out.println("Email : " + email );
			// System.out.println("Name : " + name );
			// System.out.println("mobile: " + mobile );
			// System.out.println("Password: " + upass );
			// System.out.println("random no for acc " + randNo );
			else{
				boolean s = FileIO.registerUser(uname,upass,name,email,gender,mobile,"./File/users.txt",randNo);
				//index = {0=user; 1=pass ; 2=name; 3=email; 4=gender; 5=mobile; 6=accountno; 7=amount initially0}
				if(s == true){
					JOptionPane.showMessageDialog(this, "Register Successfull");
					clearFields();
				}
				else if ( s == false){
					JOptionPane.showMessageDialog(this, "Already Registered", 
													  "Error",JOptionPane.WARNING_MESSAGE);
				}
			}

		}
		if(backBtn == e.getSource()){
			this.dispose();
			new Login();
		}
	}

	private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        genderBox.setSelectedIndex(0);
		usernameField.setText("");
		mobileNoField.setText("");
        userPasswordField.setText("");
    }
}