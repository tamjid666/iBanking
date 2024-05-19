package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import Entity.*;

public class Deposite extends JFrame implements ActionListener {

    JLabel userHeader, backgroundImgLabel;
    JLabel amount, user, account;
    JTextField amountField, userField, acNoField;
    JButton backBtn, addBtn;

    Cursor cursor;
    MyFrame frame;

    Font font = new Font("poppins", Font.PLAIN, 19);
    Font boldFont = new Font("roboto", Font.BOLD, 24);

    Color blueColor = new Color(25, 113, 255);
    Color redColor = new Color(250, 20, 20);

    String uname, password;
    String filePath = "./File/users.txt";
    User userDeposit;

    String accountNO;

    public Deposite(MyFrame frame, String uname, String password) {
        this.uname = uname;
        this.password = password;
        this.frame = frame;

        userDeposit = FileIO.profilUser(uname, password, filePath);
        accountNO = userDeposit.getSRand();

        initialization();

        //right lower pic 
        backgroundImgLabel = new JLabel(Default.getResizeImage("./Assets/Images/deposite1.png", 250, 260));
        backgroundImgLabel.setBounds(690, 240, 245, 260);
        this.add(backgroundImgLabel);

        //top word
        userHeader = new JLabel("DEPOSITE");
        userHeader.setBounds(380, 45, 350, 40);
        userHeader.setFont(boldFont);
        userHeader.setForeground(blueColor);
        this.add(userHeader);
        
        //data taken 
        user = new JLabel("User             :");
        user.setBounds(150, 146, 200, 40);
        user.setFont(font);
        this.add(user);

        account = new JLabel("Account No. :");
        account.setBounds(150, 196, 200, 40);
        account.setFont(font);
        this.add(account);

        amount = new JLabel("Amount        :");
        amount.setBounds(150, 246, 200, 40);
        amount.setFont(font);
        this.add(amount);

        userField = new JTextField(uname);
        userField.setBounds(320, 150, 250, 35);
        userField.setFont(font);
        this.add(userField);

        acNoField = new JTextField(accountNO);
        acNoField.setBounds(320, 195, 250, 35);
        acNoField.setFont(font);
        this.add(acNoField);

        amountField = new JTextField();
        amountField.setBounds(320, 240, 250, 35);
        amountField.setFont(font);
        this.add(amountField);

        cursor = new Cursor(Cursor.HAND_CURSOR);

        addBtn = new JButton("DEPOSITE");
        addBtn.setBounds(640, 192, 150, 40);
        addBtn.setFont(font);
        addBtn.setForeground(Color.WHITE);
        addBtn.addActionListener(this);
        addBtn.setCursor(cursor);
        addBtn.setBackground(new Color(0, 204, 0));
        addBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        addBtn.setFocusable(false);
        this.add(addBtn);

        backBtn = new JButton("< BACK");
        backBtn.setBounds(20, 440, 100, 35);
        backBtn.setFont(font);
        backBtn.setBackground(redColor);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        backBtn.setCursor(cursor);
        backBtn.addActionListener(this);
        backBtn.setFocusable(false);
        this.add(backBtn);
    }

    public void initialization() {
        this.setTitle("Deposite-page");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(310, 120);
        this.setSize(920, 530);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setIconImage(new ImageIcon("./Assets/Images/logo.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (addBtn == e.getSource()) {
            String name = userField.getText();
            String acNo = acNoField.getText();
            String amnt = amountField.getText();

            System.out.println(name);
            System.out.println(acNo);
            System.out.println(amnt);

            amountField.setText("");
            if (amnt.isEmpty() || name.isEmpty() || acNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }
            int minusCheck = Integer.parseInt(amnt); 
            if(minusCheck <= 0 ){
                JOptionPane.showMessageDialog(this, "Invalid Amount", 
                                              "Error", JOptionPane.WARNING_MESSAGE);
            }
            else{
                    JOptionPane.showMessageDialog(this, "Deposit Successful");
                    FileIO.depositeUpdate(amnt, uname, accountNO);
            }
        }

        if (backBtn == e.getSource()) {
            frame.setVisible(true);
            this.dispose();
        }
    }
}
