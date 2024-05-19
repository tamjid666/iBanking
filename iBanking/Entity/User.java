package Entity;

import Interface.*;

public class User implements Iuser{
    private String uname, upass, name, email, gender, mobile , sRand;

    public User() {System.out.println("user test pass");}

    public User(String uname, String upass, String name, 
                String email, String gender, 
                String mobile,String sRand) {
        this.uname = uname;
        this.upass = upass;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.sRand = sRand ; 
    }
    public void setUname(String uname){
        this.uname = uname ; 
        System.out.println(uname);
    }
    public void setUpass(String upass){
        this.upass = upass ; 
        System.out.println(upass);
    }
    void setName(String name){
        this.name = name ; 
        System.out.println(name);
    }
    void setEmail(String email){
        this.email = email ; 
        System.out.println(email);
    }
    void setGender(String gender){
        this.gender = gender ; 
        System.out.println(gender);
    }
    void setMobile(String mobile){
        this.mobile= mobile ; 
        System.out.println(mobile);
    }
    void setSRand(String sRand){
        this.sRand = sRand;
    }

    public String getUname() {
        return uname;
    }

    public String getUpass() {
        return upass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }
    public String getSRand(){
        return sRand;
    }
}
