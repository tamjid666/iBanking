package GUI;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import Entity.*;

public class BalanceEnquiry extends JFrame implements ActionListener{

    JPanel panel;
	JLabel userHeader, backgroundImgLabel;
	JLabel  user , account ; 
	JTextField  userField , acNoField;
	JButton backBtn, addBtn ;
	
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
    Color newBlue = new Color(0,115,150) ; 

	String uname , password ; 
	String filePath = "./File/users.txt";
    User userDeposit ;

	String accountNO ;

	BalanceInfo balance = new BalanceInfo(); 

    BalanceEnquiry(MyFrame frame,String uname,String password){
		
		this.uname = uname; 
		this.password = password; 
		this.frame = frame;
		// this.loginPage = loginPage;
		System.out.println(uname);
		System.out.println(password);

		userDeposit = FileIO.profilUser(uname, password, filePath);
		accountNO = userDeposit.getSRand() ;
		initialization();

        userHeader = new JLabel("BALANCE ENQUIRY");
		userHeader.setBounds(330, 115, 350, 40);
		userHeader.setFont(boldFont);
		userHeader.setForeground(blueColor);
		this.add(userHeader);

        user = new JLabel("User             :");
		user.setBounds(150, 176, 200, 40);
		user.setFont(font);
		this.add(user);

        account = new JLabel("Account No. :");
		account.setBounds(150, 226, 200, 40);
		account.setFont(font);
		this.add(account);

        userField = new JTextField(uname);
		userField.setBounds(320, 180, 250, 35);
		userField.setFont(font);
		this.add(userField);
		
        acNoField = new JTextField(accountNO);
		acNoField.setBounds(320, 225, 250, 35);
		acNoField.setFont(font);
		this.add(acNoField);
		
        cursor = new Cursor(Cursor.HAND_CURSOR);
		
		addBtn = new JButton("CHECK:)");
		addBtn.setBounds(645,200,150,40);
		addBtn.setFont(font);
		addBtn.setForeground(Color.WHITE);
		addBtn.addActionListener(this);
		addBtn.setCursor(cursor);
		addBtn.setBackground(new Color(0,204,0));
		addBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		addBtn.setFocusable(false);
		this.add(addBtn);

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

		panel = new JPanel();
		panel.setSize(1920, 1080);
		panel.setLayout(null);
		panel.setBackground(new Color(240, 245, 245));
		this.add(panel);
    }
    public void initialization(){
		this.setTitle("Balance_Enquiry-page");
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
			aBox();
		}

		if(backBtn == e.getSource()){
			frame.setVisible(true);
			this.dispose();
		}
	}

	private void aBox(){
		String filePath = "./File/users.txt";
		BalanceInfo balance = FileIO.balanceReader(uname,password,accountNO,filePath);
		if (balance != null) {
            JOptionPane.showMessageDialog(this, 
			"------------Balance Info------------" +
				"\nName  ------------>  " + balance.getName() + 
                "\nEmail  ------------>  " + balance.getEmail() + 
                "\nMobile  ---------->  " + balance.getMobile() + 
                "\nAcount NO.  ---->  " + balance.getAccountNO()+
                "\nBalance  --------->  " + balance.getBalance()
				);
        }
	}
}