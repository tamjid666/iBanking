package GUI;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import File.*;
import Entity.*;

public class MyFrame extends JFrame implements ActionListener {
    Login loginPage; 
    JButton withdrawal, balanceEnquiry, deposite, transfer, logOut, profile;
    JLabel label, backgroundImgLabel, backgroundImgLabel2; 
    Cursor cursor;

    Login loginpage;
    String uname, password; 

    MyFrame(Login loginpage, String name, String password) {
        this.loginPage = loginpage;
        this.uname = name; 
        this.password = password;

        System.out.println(name);
        System.out.println(password);

        this.setLayout(null);
        this.setIconImage(new ImageIcon("./Assets/Images/logo.png").getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        backgroundImgLabel = new JLabel(Default.getResizeImage("./Assets/Images/MainLogo.png", 250, 260));
        backgroundImgLabel.setBounds(100, -40, 250, 260);
        this.add(backgroundImgLabel); 

        backgroundImgLabel2 = new JLabel(Default.getResizeImage("./Assets/Images/home.png", 460, 430));
        backgroundImgLabel2.setBounds(430, 20, 460, 430);
        this.add(backgroundImgLabel2);

        Icon depositeIcon = new ImageIcon("./Assets/ICON/deposit.png"); 
        deposite = new JButton("Deposite",depositeIcon);
        deposite.setFont(new Font("Comic Sans", Font.BOLD, 18)); 
        deposite.setFocusable(false);
        deposite.setBounds(100, 140, 250, 50); 
        deposite.setForeground(Color.white);
        deposite.setBackground(new Color(0, 115, 150));
        deposite.setBorder(BorderFactory.createLineBorder(Color.white));
        deposite.addActionListener(this);
        deposite.setCursor(cursor);

        Icon withdrawIcon = new ImageIcon("./Assets/ICON/cash-withdrawal.png"); 
        withdrawal = new JButton("Withdrawal",withdrawIcon); 
        withdrawal.setFont(new Font("Comic Sans", Font.BOLD, 18)); 
        withdrawal.setFocusable(false);
        withdrawal.setBounds(100, 200, 250, 50); 
        withdrawal.setForeground(Color.white);
        withdrawal.setBackground(new Color(0, 115, 150));
        withdrawal.setBorder(BorderFactory.createLineBorder(Color.white));
        withdrawal.addActionListener(this);
        withdrawal.setCursor(cursor);

        Icon balanceIcon = new ImageIcon("./Assets/ICON/balance.png"); 
        balanceEnquiry = new JButton("Balance Enquiry",balanceIcon);
        balanceEnquiry.setFont(new Font("Comic Sans", Font.BOLD, 18)); 
        balanceEnquiry.setFocusable(false);
        balanceEnquiry.setBounds(100, 260, 250, 50);
        balanceEnquiry.setForeground(Color.white);
        balanceEnquiry.setBackground(new Color(0, 115, 150));
        balanceEnquiry.setBorder(BorderFactory.createLineBorder(Color.white));
        balanceEnquiry.addActionListener(this);
        balanceEnquiry.setCursor(cursor);

        Icon transferIcon = new ImageIcon("./Assets/ICON/transfer.png"); 
        transfer = new JButton("Transfer",transferIcon);
        transfer.setFont(new Font("Comic Sans", Font.BOLD, 18)); 
        transfer.setFocusable(false);
        transfer.setBounds(100, 320, 250, 50);  
        transfer.setForeground(Color.white);
        transfer.setBackground(new Color(0, 115, 150));
        transfer.setBorder(BorderFactory.createLineBorder(Color.white));
        transfer.addActionListener(this);
        transfer.setCursor(cursor);

        Icon logoutIcon = new ImageIcon("./Assets/ICON/logout.png"); 
        logOut = new JButton(" Log-Out", logoutIcon); 
        logOut.setFont(new Font("Comic Sans", Font.BOLD, 18)); 
        logOut.setFocusable(false);
        logOut.setBounds(20, 440, 130, 35);
        logOut.setForeground(Color.gray);
        logOut.setOpaque(false);
        logOut.setContentAreaFilled(false);
        logOut.setBorderPainted(false);
        logOut.setFocusPainted(false);
        logOut.setBorder(BorderFactory.createLineBorder(Color.white));
        logOut.addActionListener(this);
        logOut.setCursor(cursor);

        Icon profileIcon = new ImageIcon("./Assets/ICON/profile.png"); 
        profile = new JButton(" Admin",profileIcon);
        profile.setFont(new Font("Comic Sans", Font.BOLD, 16)); 
        profile.setFocusable(false);
        profile.setBounds(760, 440, 125, 36);
        profile.setForeground(Color.gray);
        profile.setOpaque(false);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        profile.setFocusPainted(false);
        profile.setBorder(BorderFactory.createLineBorder(Color.white));
        profile.addActionListener(this);
        profile.setCursor(cursor);
        this.add(profile);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(920, 530); 
        this.setResizable(false);
        this.setTitle("iBanking - Home_Page"); 
        this.setLocation(310, 120);

        this.add(withdrawal);
        this.add(balanceEnquiry); 
        this.add(deposite); 
        this.add(transfer);
        this.add(logOut); 
        this.add(label); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (deposite == e.getSource()) {
            new Deposite(this,uname,password); 
            this.setVisible(false); 
        }
        if (e.getSource() == withdrawal) {
            new Withdrawal(this,uname,password); 
            this.setVisible(false);
        }
        if (e.getSource() == balanceEnquiry) {
            new BalanceEnquiry(this,uname,password); 
            this.setVisible(false);
        }
        if (e.getSource() == transfer) {
            new Transfer(this,uname,password); 
            this.setVisible(false);
        }
        if (e.getSource() == profile) {
            viewProfile(); 
        }
        if (logOut == e.getSource()) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure ?", 
                "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                new Login();
                this.setVisible(false);
            }
            else{
                return ; 
            }
        }
    }

    private void viewProfile() {
        String filePath = "./File/users.txt";
        User user = FileIO.profilUser(uname, password, filePath);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "------------Profile Info------------\nName: " + user.getName() + 
                "\nUsername: " + user.getUname() + 
                "\nEmail: " + user.getEmail() + 
                "\nGender: " + user.getGender() + 
                "\nMobile: " + user.getMobile() + 
                "\nAcount NO: " + user.getSRand());
        }
    }
}