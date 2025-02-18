package Route;

import Controller.CustomerController;
import Utility.GlobalScanner;
import View.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner;

    public MainMenu(){
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public int Greating(){
        try{
            System.out.println("------------------------------------------------");
            System.out.println("-----Welcome to the Happy-Go-Lucky Platform-----");
            System.out.println("------------------------------------------------");
            System.out.println("Please Choose One Of The Option To Proceed...");
            System.out.println(" 1. -- Customer Login");
            System.out.println(" 2. -- Stall Login");
            System.out.println(" 3. -- Customer Registration");
            System.out.println(" 4. -- Stall Registration");
            System.out.print("Enter your the number of choice to proceed : ");
            return scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getUserOption() throws SQLException {
        int choice = Greating();
        HandleUserOption(choice);
    }

    public void HandleUserOption(int choice) throws SQLException {
        switch(choice){
            case 1:
                CustomerLogin customerLogin = new CustomerLogin();
                customerLogin.loginCustomer();
                break;

            case 2:
                StallLogin stallLogin = new StallLogin();
                stallLogin.loginStall();
                break;

            case 3:
                CustomerRegistration customerRegistration = new CustomerRegistration();
                System.out.println(customerRegistration.RegisterCustomer());
                getUserOption();
                break;

            case 4:
                StallRegistration stallRegistration = new StallRegistration();
                System.out.println(stallRegistration.registerStall());
                getUserOption();
                break;
            default:
                System.out.println();
                System.out.println("Option unavailable, please only choose 1 - 4 only.");
                System.out.println();
                getUserOption();
                break;

        }
    }
}
