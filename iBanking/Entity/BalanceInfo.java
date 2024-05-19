package Entity;

import Interface.*; 

public class BalanceInfo implements IBalanceInfo{
    private String name, email, mobile, accountNO, balance;

    public BalanceInfo() {
        System.out.println("Successfully come here");
    }

    public BalanceInfo(String name, String email, String mobile, String accountNO, String balance) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.accountNO = accountNO;
        this.balance = balance;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println(name);
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println(email);
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        System.out.println(mobile);
    }

    public void setAccountNo(String accountNO) {
        this.accountNO = accountNO;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAccountNO() {
        return accountNO;
    }

    public String getBalance() {
        return balance;
    }
}
