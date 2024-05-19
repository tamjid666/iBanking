package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import File.*;

public class Login extends JFrame implements ActionListener {
	 
    JPanel panel;
    JLabel userNameLabel, userPassLabel, backgroundImgLabel, loginPanelText, btnDivider;
    JTextField userNameText;//setBorder(BorderFactory.createEmptyBorder()); border remover
    JPasswordField userPasswordField;
    JButton loginBtn, registerBtn;
    JCheckBox showPasswordCheckBox;
    
    // Fonts
    Font font = new Font("Poppins", Font.PLAIN, 19); // Header font: Poppins, Paragraph font: Cambria
    Font boldFont = new Font("Roboto", Font.BOLD, 35);
    Font paragraphFont = new Font("Cambria", Font.PLAIN, 18);
    
    // Colors
    Color logColor = new Color(46, 139, 87); // Login button color
    Color regColor = new Color(123, 104, 238); // Register button color

    public Login() {
        this.setTitle("iBanking - Login Page");

        backgroundImgLabel = new JLabel(Default.getResizeImage("./Assets/Images/loginpage.jpg", 460, 530));
        backgroundImgLabel.setBounds(0, 0, 460, 530);
        this.add(backgroundImgLabel);

        //upper
        loginPanelText = new JLabel("LOGIN");
        loginPanelText.setBounds(640, 30, 350, 40);
        loginPanelText.setFont(boldFont);
        loginPanelText.setForeground(new Color(25, 113, 255));
        this.add(loginPanelText);
        
        loginPanelText = new JLabel("Enter your username and password to login");
        loginPanelText.setBounds(530, 60, 350, 40);
        loginPanelText.setFont(paragraphFont);
        this.add(loginPanelText);


        //username + password
        userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(520, 120, 250, 25);
        userNameLabel.setFont(font);
        this.add(userNameLabel);

        userNameText = new JTextField();
        userNameText.setBounds(520, 155, 340, 40);
        userNameText.setFont(font);
        userNameText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        this.add(userNameText);
        
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(520, 205, 250, 25);
        userPassLabel.setFont(font);
        this.add(userPassLabel);
        
        userPasswordField = new JPasswordField();
        userPasswordField.setBounds(520, 240, 340, 40);
        userPasswordField.setFont(font);
        userPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        this.add(userPasswordField);


        //pass checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFocusable(false);
        showPasswordCheckBox.setBounds(520, 290, 150, 25);
        showPasswordCheckBox.setFont(paragraphFont);
        showPasswordCheckBox.setBackground(new Color(240, 245, 245));
        showPasswordCheckBox.addActionListener(this);
        this.add(showPasswordCheckBox);
        

        //login + register
        loginBtn = new JButton("Login");
        loginBtn.setBounds(520, 330, 340, 40);
        loginBtn.setFont(font);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(logColor);
        loginBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        loginBtn.addActionListener(this);
        loginBtn.setFocusable(false); 
        this.add(loginBtn);
        
        btnDivider = new JLabel("------------------------ or -------------------------");
        btnDivider.setForeground(new Color(145, 145, 145));
        btnDivider.setBounds(520, 380, 340, 40);
        btnDivider.setFont(paragraphFont);
        this.add(btnDivider);
        
        registerBtn = new JButton("Register Now");
        registerBtn.setBounds(520, 430, 340, 40);
        registerBtn.setFont(font);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(regColor);
        registerBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        registerBtn.addActionListener(this);
        registerBtn.setFocusable(false); 
        this.add(registerBtn);
        
        //frame a-z
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1920, 1080);
        panel.setBackground(new Color(240, 245, 245));
        this.add(panel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(920, 530);
        this.setLocation(310, 120);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("./Assets/Images/logo.png").getImage());
        this.setResizable(false);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (loginBtn == e.getSource()) {
            String name = userNameText.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            int status = FileIO.checkUser(name, password, "./File/users.txt");

            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.dispose();
                new MyFrame(this, name, password);
                userNameText.setText("");
                userPasswordField.setText("");
                
                this.dispose();
            } else if (status == 2) {
                JOptionPane.showMessageDialog(this, "Invalid Password", 
                                              "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "USER NOT REGISTERED", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (registerBtn == e.getSource()) {
            new Register(this);
            this.dispose();
        }
        if (showPasswordCheckBox == e.getSource()) {
            if (showPasswordCheckBox.isSelected()) {
                userPasswordField.setEchoChar((char) 0);
            } else {
                userPasswordField.setEchoChar('*');
            }
        }
    }
}
