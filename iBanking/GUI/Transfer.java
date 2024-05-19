package GUI;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import Entity.*;

public class Transfer extends JFrame implements ActionListener{

    JPanel panel;
	JLabel userHeader, backgroundImgLabel;
	JLabel amount , user , account ; 
	JTextField amountField , userField , acNoField;
	JButton backBtn, addBtn, deleteBtn;

	Cursor cursor;
	
	JTable customerTable;
	DefaultTableModel model;
	
	MyFrame frame;

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

    Transfer(MyFrame frame,String uname,String password){
		this.frame = frame;
		this.uname = uname; 
		this.password = password; 

		userDeposit = FileIO.profilUser(uname, password, filePath);
		accountNO = userDeposit.getSRand() ;

		initialization();

		backgroundImgLabel = new JLabel(Default.getResizeImage("./Assets/Images/transfer.png", 350, 290));
		backgroundImgLabel.setBounds(525, 230, 350, 290);
		this.add(backgroundImgLabel); 

        userHeader = new JLabel("TRANSFER");
		userHeader.setBounds(380, 45, 350, 40);
		userHeader.setFont(boldFont);
		userHeader.setForeground(blueColor);
		this.add(userHeader);

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

        userField = new JTextField();
		userField.setBounds(320, 150, 250, 35);
		userField.setFont(font);
		this.add(userField);
		
        acNoField = new JTextField();
		acNoField.setBounds(320, 195, 250, 35);
		acNoField.setFont(font);
		this.add(acNoField);
		
        amountField = new JTextField();
		amountField.setBounds(320, 240, 250, 35);
		amountField.setFont(font);
		this.add(amountField);
		

        // create Cursor
        cursor = new Cursor(Cursor.HAND_CURSOR);
		
		// add button
		addBtn = new JButton("TRANSFER");
		addBtn.setBounds(640,192,150,40);
		addBtn.setFont(font);
		addBtn.setForeground(Color.WHITE);
		addBtn.addActionListener(this);
		addBtn.setCursor(cursor);
		addBtn.setBackground(new Color(0,204,0));
		addBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		addBtn.setFocusable(false);
		this.add(addBtn);
		
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
		
		// backgrond panel
		panel = new JPanel();
		panel.setSize(1920, 1080);
		panel.setLayout(null);
		panel.setBackground(new Color(240, 245, 245));
		this.add(panel);

    }
    public void initialization(){
		this.setTitle("Transfer-page");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(310, 120);
        this.setSize(920,530); 
        this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("./Assets/Images/logo.png").getImage());
	}

    @Override
    public void actionPerformed(ActionEvent e){
		if (addBtn == e.getSource()) {
			String name = userField.getText();
			String acNo = acNoField.getText(); 
			String amnt = String.valueOf(amountField.getText());

			if(amnt.isEmpty() || name.isEmpty() ||acNo.isEmpty()){
				JOptionPane.showMessageDialog(this, "Please fill all fields!");
				return;
			}

			int minusCheck = Integer.parseInt(amnt); 
            if(minusCheck <= 0 ){
                JOptionPane.showMessageDialog(this, "Invalid Amount", 
                                              "Error", JOptionPane.WARNING_MESSAGE);
            }
			else{
				amountField.setText(""); 
				if(name.equals(uname) || acNo.equals(accountNO)){
					JOptionPane.showMessageDialog(this, "You Can't Transfer Your Account !");
					return;
				}
				else{
					int inputAmount = Integer.parseInt(amnt); 
					if(FileIO.readBalance(uname,filePath) < inputAmount){
						JOptionPane.showMessageDialog(this, "You Can't Transfer!"+
														  "\nYou Hava less amount"+
														  "\nYour Balance is :" + FileIO.readBalance(uname,filePath));
						return;
					}
					if(FileIO.userExistance(name, acNo) == false){
							System.out.println("user not exitst");
							JOptionPane.showMessageDialog(this, "USER NOT REGISTERED", 
            	                                  "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(this, "Transfer Successfull");
						FileIO.depositeUpdate(amnt,name,acNo);
						FileIO.withdrawUpdate(amnt,uname, accountNO);
					}
				}
			}

			// int option = JOptionPane
            //             .showConfirmDialog(this, "Are you sure ?"
            //             , "Confirmation", JOptionPane.YES_NO_OPTION);
            // if(option == JOptionPane.YES_OPTION){
            //     System.out.println("koto taka ");
			// 	JOptionPane.showMessageDialog(this, "Transfer Successfull.....");
			// 	FileIO.depositeUpsate(amnt);
            // }
			// else
			// 	return ; 
			// System.out.println("there");
		}

		if(backBtn == e.getSource()){
			frame.setVisible(true);
			this.dispose();
		}
	}
}