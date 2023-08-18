import java.util.Scanner;
import java.util.UUID;

public class SBIAccount implements  BankAccountInterface{

    private String name;
   private double balance;
    private String password;

    final String accountNo;

    final double rateOfInterest = 6.2;

    Scanner sc = new Scanner(System.in);
    public SBIAccount() {
        System.out.println("WELCOME TO State Bank of India");
       System.out.println("Hey, I am your SBI Assitant. I help you in creating your Account at SBI. Thank you for Choosing SBI");
       System.out.println("Tell me your name: ");
       this.name = sc.nextLine();

        System.out.println("Create Password-> Password contains 5 letters, it should contain atleast one character('@, %, #')");
        String pass = sc.next();
        while(pass.length()>5 || pass.length()<1 || !(pass.contains("@") || pass.contains("%") || pass.contains("#"))){
            System.out.println("Not Valid Format");
            pass = sc.next();
        }

        this.password = pass;


        System.out.println("Congratulations!!!You have successfully created your account");
        this.accountNo = String.valueOf(UUID.randomUUID());

        System.out.println("Your SBI Account Number is: "+this.accountNo);

        System.out.println("Minimum Balance required to safe your account: Rs.500");
        while(this.balance<500.0){
            System.out.println("Add Money to your account for min balance");
            this.balance += sc.nextDouble();
        }

            System.out.println("You have sufficient Amount. Curr Balance: "+ this.balance);

    }

    @Override
    public String fetchBalance(String password) {
        if(this.password == password){
            return "Current Balance: "+this.balance;
        }
        return "Incorrect Password!!";
    }

    @Override
    public String addMoney(double amount) {
        System.out.println("Amount to add: "+amount);
        this.balance += amount;
        return "Amount Added Successfully. Your curr balance is: "+this.balance;
    }

    @Override
    public String withdrawMoney(double amount, String password) {
        if(this.password != password){
            return "Incorrect Password";
        }
        if(this.balance<amount){
            return "Amount not sufficient to deduce";
        }

        this.balance -= amount;
        return "Amount Withdraw Successfully. Your curr balance is: "+this.balance;
    }

    @Override
    public String changePassword(String oldPassword, String newPassword) {
        if(this.password!=oldPassword){
            return "Incorrect OldPassword";
        }
        String pass = newPassword;
        if(pass.length()>5 || pass.length()<1 || !(pass.contains("@") || pass.contains("%") || pass.contains("#"))){
            return "Password must contains 5 letters, it should contain atleast one character('@, %, #')";
        }
        this.password = newPassword;
        return "Password Changes Successfully";
    }

    @Override
    public double calculateInterest(int year) {
        return this.balance * year * rateOfInterest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String pass = password;
        if(pass.length()>5 || pass.length()<1 || !(pass.contains("@") || pass.contains("%") || pass.contains("#"))){
            System.out.println("Password must contains 5 letters, it should contain atleast one character('@, %, #')");
        }
        else{
            this.password = pass;
        }
    }

    public String getAccountNo() {
        return accountNo;
    }


    public double getRateOfInterest() {
        return rateOfInterest;
    }
}
