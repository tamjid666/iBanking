package File;

import java.util.*;
import java.io.*;
import Entity.*;

public class FileIO {
    public static int checkUser(String uname, String upass, String fname) {
        int status = 0;
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cols = row.split(";");
                
                String name = cols[0];
                String pass = cols[1];

                if (uname.equals(name) && upass.equals(pass)) {
                    status = 1;
                    break;
                } 
                else if (uname.equals(name) && !upass.equals(pass)) {
                    status = 2;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read From File: " + e.getMessage());
        }
        return status;
    }

    public static boolean registerUser(String uname, String upass, String name,
                                        String email, String gender, String mobile,
                                        String fname, int acno) {

        if (checkUser(uname, upass, fname) == 0) {
            System.out.println("Registering user: " + uname);

                writeInFile(uname + ";" + upass + ";" + name + ";" 
                + email + ";" + gender + ";" + mobile +";"
                +acno + ";" + "0.0", fname, true);
            //amount index[7]
            return true;
        }
        return false;
    }

    public static void writeInFile(String line, String fname, boolean append) {
        try {
            FileWriter fw = new FileWriter(new File(fname), append);
            fw.write(line + "\n");
            fw.close();
            System.out.println("Successfully reach the file: " + fname); //check
        } catch (IOException e) {
            System.out.println("Cannot Write To File: " + e.getMessage());
        }
    }

 public static void depositeUpdate(String amount, String uname, String accountNO) {
    String fname = "./File/users.txt";
    double newValue = Double.parseDouble(amount);
    ArrayList<String> fileContent = new ArrayList<>();

    try {
        Scanner sc = new Scanner(new File(fname));
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            String[] cols = row.split(";");

            // Only update the row if this condition matches
            if (uname.equals(cols[0]) && accountNO.equals(cols[6])) {
                double oldValue = Double.parseDouble(cols[7]);
                double updatedValue = oldValue + newValue;
                cols[7] = String.valueOf(updatedValue); // Update the balance
            }

            String updatedRow = String.join(";", cols);
            fileContent.add(updatedRow);
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("Cannot Read From File: " + e.getMessage());
        return;
    }

    // Update the file with the new content
    try {
        PrintWriter pw = new PrintWriter(new File(fname));
        for (String line : fileContent) {
            pw.println(line);
        }
        pw.close();
    } catch (FileNotFoundException e) {
        System.out.println("Cannot Write To File: " + e.getMessage());
    }
}

    public static void withdrawUpdate(String amount, String uname, String accountNO) {
        String fname = "./File/users.txt";
        double newValue = Double.parseDouble(amount);
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(fname));

            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cols = row.split(";");
                String updatedRow = row;

                if (cols[0].equals(uname) && cols[6].equals(accountNO)) {
                    double oldValue = Double.parseDouble(cols[7]);
                    double updatedValue = oldValue - newValue;

                    // if (updatedValue < 0) {
                    //     System.out.println("Insufficient balance.");
                    //     sc.close();
                    //     return;
                    // }

                    cols[7] = String.valueOf(updatedValue);
                    updatedRow = String.join(";", cols);
                }

                fileContent.add(updatedRow);
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Cannot Read From File: " + e.getMessage());
            return;
        }
        //update
        try {
             PrintWriter pw = new PrintWriter(new File(fname));
             for (String line : fileContent) {
                 pw.println(line);
             }
             pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println("Cannot Write To File: " + e.getMessage());
        }
    }

    public static int checkoldValue(String name, String account, double amnt) {
        String file = "./File/users.txt"; 
        int status = 0;
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String row = sc.nextLine(); 
                String[] col = row.split(";");
                if (name.equals(col[0]) && account.equals(col[6])) {
                    double amount = Double.parseDouble(col[7]);
                    if (amount <= 0 || amnt > amount) {
                        status = 1; //balance thik nai 
                        break;
                    }
                    if (amount > 0 && amount >= amnt) {
                        status = 3; //fine
                        break; 
                    }
                } else if (name.equals(col[0]) && !account.equals(col[6])) {
                    status = 2; //account doesn't exist
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read From File: " + e.getMessage());
        }
        return status;
    }
    

    public static boolean userExistance(String userName , String userAccount){
        String file = "./File/users.txt" ; 

        boolean bol = false ; 
        try{
            Scanner sc = new Scanner(new File(file));

            while(sc.hasNextLine()){
                String row = sc.nextLine() ; 
                String col[] = row.split(";");

                System.out.println("User check ee jacche ");

                if(userName.equals(col[0]) && userAccount.equals(col[6])){
                    return true ; 
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot Read From File: " + e.getMessage());
        }
        return bol ; 
    } 

    public static BalanceInfo balanceReader(String uname, String pass,String accountNO, String filePath){
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cols = row.split(";");
                
                if (uname.equals(cols[0]) && pass.equals(cols[1]) && accountNO.equals(cols[6])) {
                    sc.close();
                    return new BalanceInfo(cols[2],cols[3],cols[5],cols[6],cols[07]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read From File: " + e.getMessage());
        }
        return null ; 
    }

    public static User profilUser(String uname, String pass, String filePath){
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cols = row.split(";");
                
                if (uname.equals(cols[0]) && pass.equals(cols[1])) {
                    sc.close();
                    return new User(cols[0], cols[1], cols[2], cols[3], cols[4], cols[5] , cols[6]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Read From File: " + e.getMessage());
        }
        return null;
    }

    public static int readBalance(String uname, String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cols = row.split(";");

                if (uname.equals(cols[0])) {
                    double oldAmount = Double.parseDouble(cols[7]);
                    return (int) oldAmount;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file: " + e.getMessage());
        }
        return 0;
    }
}
