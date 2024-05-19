package GUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class Withdrawal extends JFrame implements ActionListener{

    JPanel panel;
	JLabel userHeader, backgroundImgLabel;
	JLabel amount , user , account ; 
	JTextField amountField , userField , acNoField;
	JButton backBtn, addBtn, deleteBtn;
 
	
	Cursor cursor;
	
	JTable customerTable;
	DefaultTableModel model;
	
	MyFrame frame;
	// Login loginPage;

    // fonts
	Font font = new Font("poppins", Font.PLAIN, 19);
	Font boldFont = new Font("roboto", Font.BOLD, 24);
	
	// colors
	Color blueColor = new Color(25, 113, 255);
	Color redColor = new Color(250, 20, 20);

	String uname , password ; 
	String filePath = "./File/users.txt";
    User userDeposit ;

	String accountNO ;

    Withdrawal(MyFrame frame,String uname,String password){

		this.frame = frame;
		this.uname = uname; 
		this.password = password; 

		userDeposit = FileIO.profilUser(uname, password, filePath);
		accountNO = userDeposit.getSRand() ;

		// frame collect
		initialization();

		//bottom img
		backgroundImgLabel = new JLabel(Default.getResizeImage("./Assets/Images/withdraw.png", 350, 330));
		backgroundImgLabel.setBounds(550, 235, 350, 330);
		this.add(backgroundImgLabel); 

		//head txt 
        userHeader = new JLabel("WITHDRAW");
		userHeader.setBounds(380, 45, 350, 40);
		userHeader.setFont(boldFont);
		userHeader.setForeground(blueColor);
		this.add(userHeader);

		//data
        user = new JLabel("User             :");
		user.setBounds(150, 136, 200, 40);
		user.setFont(font);
		this.add(user);

        account = new JLabel("Account No. :");
		account.setBounds(150, 186, 200, 40);
		account.setFont(font);
		this.add(account);

		amount = new JLabel("Amount        :");
		amount.setBounds(150, 236, 200, 40);
		amount.setFont(font);
		this.add(amount);

        userField = new JTextField(uname);
		userField.setBounds(320, 140, 250, 35);
		userField.setFont(font);
		this.add(userField);
		
        acNoField = new JTextField(accountNO);
		acNoField.setBounds(320, 185, 250, 35);
		acNoField.setFont(font);
		this.add(acNoField);
		
        amountField = new JTextField();
		amountField.setBounds(320, 230, 250, 35);
		amountField.setFont(font);
		this.add(amountField);
		

        //Cursor
        cursor = new Cursor(Cursor.HAND_CURSOR);

		// back button
		backBtn = new JButton("< BACK");
		backBtn.setBounds(20,440,100,35);
		backBtn.setFont(font);
		backBtn.setBackground(redColor);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBorder(new EmptyBorder(0,0,0,0));
		backBtn.setCursor(cursor);
		backBtn.addActionListener(this);
		backBtn.setFocusable(false); 
		this.add(backBtn);

		//button
		addBtn = new JButton("WITHDRAW");
		addBtn.setBounds(640,192,150,40);
		addBtn.setFont(font);
		addBtn.setForeground(Color.WHITE);
		addBtn.addActionListener(this);
		addBtn.setCursor(cursor);
		addBtn.setBackground(new Color(0,204,0));
		addBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		addBtn.setFocusable(false);
		this.add(addBtn);

		
		panel = new JPanel();
		panel.setSize(1920, 1080);
		panel.setLayout(null);
		panel.setBackground(new Color(240, 245, 245));
		this.add(panel);
    }

    public void initialization(){
		this.setTitle("Withdrawal-page");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(310, 120);
        this.setSize(920,530); 
        this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("./Assets/Images/logo.png").getImage());
	}
	public void actionPerformed(ActionEvent e) {
		if (addBtn == e.getSource()) {
			String name = userField.getText().trim();
			String acNo = acNoField.getText().trim(); 
			String amnt = amountField.getText().trim();
	
			System.out.println(name);
			System.out.println(acNo);
			System.out.println(amnt);
	
			amountField.setText("");
	
			if (amnt.isEmpty() || name.isEmpty() || acNo.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill all fields!");
				return;
			}
	
			try {
				double amount = Double.parseDouble(amnt);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(this, "Invalid Amount. Please enter a value greater than zero.", 
												  "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
	
				int status = FileIO.checkoldValue(name, acNo, amount);
				System.out.println("OLD amount : " + status);
	
				switch (status) {
					case 1:
						System.out.println("status 1");
						JOptionPane.showMessageDialog(this, "Insufficient balance!");
						break;
					case 2:
						System.out.println("status 2");
						JOptionPane.showMessageDialog(this, "Account doesn't exist!");
						break;
					case 3:
						System.out.println("status 3");
						JOptionPane.showMessageDialog(this, "Withdraw Successful...");
						FileIO.withdrawUpdate(amnt, uname, accountNO);
						break;
					default:
						JOptionPane.showMessageDialog(this, "Unknown error!");
						break;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
			}
		}
	
		if (backBtn == e.getSource()) {
			frame.setVisible(true);
			this.dispose();
		}
	}
	
}


	
// if (oldAmount == 0){
// 	System.out.println("check 0 valance reach ");//balance 0 er kom ache 

//    int option = JOptionPane.showConfirmDialog(this, 
// 	   "Insufficient balance.\nWould you like to proceed with a loan?", 
// 						   "Emegency Loan", JOptionPane.YES_NO_OPTION);
//    if (option == JOptionPane.YES_OPTION) {
// 	   System.out.println(amount);
// 		if(oldAmount < -500 )
// 			JOptionPane.showMessageDialog(this, "Rafsaner baper moto batpari korle"+
// 											  "\n ---500er beshi load dibw nah---");
// 		else{
// 			JOptionPane.showMessageDialog(this, "Withdraw Successful.\nLoan processed...");
// 			FileIO.withdrawUpdate(amnt,uname,accountNO);
// 		}
//    }
// }

// else if(oldAmount == 1){
//    System.out.println(" check er 1 reach");
//    FileIO.withdrawUpdate(amnt,uname,accountNO);
//    JOptionPane.showMessageDialog(this, "Withdraw Successful.");
// }

// else{
// 		System.out.println("loan amount : " + oldAmount);
// 		JOptionPane.showMessageDialog(this, "we are unable to provide an additional loan at this time"+
// 										  "\n          as it would violate our single loan policy" +
// 										  "\n                           pay off your loan");
// }
								

	// private int inputAmount(String amnt){//why this error
	// 	Double amount = Double.parseDouble(amnt); 
	// 	if(amount == 0 )
	// 		return 1; 
	// }
	// double amount = Double.parseDouble(amnt);
    //             if (amount <= 0) {
    //                 JOptionPane.showMessageDialog(this, "Amount must be greater than zero!");
    //                 return;
    //             }
				
    //             if (FileIO.checkoldValue() == 0) {
    //                 int option = JOptionPane.showConfirmDialog(this, "Insufficient balance.\nWould you like to proceed with a loan?", 
    //                                                             "Confirmation", JOptionPane.YES_NO_OPTION);
    //                 if (option == JOptionPane.YES_OPTION) {
	// 					System.out.println(amount);
	// 					if(amount < -500 ){
	// 						JOptionPane.showMessageDialog(this, "Rafsaner baper moto batpari korle"+
	// 														  "\n ---500er beshi load dibw nah---");
                        	
	// 					}
	// 					else{
	// 						JOptionPane.showMessageDialog(this, "Withdraw Successful.\nLoan processed...");
    //                     	FileIO.withdrawUpsate(amnt);
	// 					}
    //                 }
    //             } 
	// 			else {
	// 				JOptionPane.showMessageDialog(this, "Withdraw Successful.");
    //                 FileIO.withdrawUpsate(amnt);
    //                 return ;
    //             }