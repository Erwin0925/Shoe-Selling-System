package View;

import Controller.CustomerController;
import Model.Customers;
import Utility.GlobalScanner;

import java.util.Scanner;

public class CustomerRegistration {

    private final Scanner scanner;

    public CustomerRegistration() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String RegisterCustomer(){
        try{
            System.out.println("-----Customer Registration Page-----");
            System.out.print("Please Enter your UserName : ");
            String userName = scanner.next();
            System.out.print("Please Enter your Password : ");
            String password = scanner.next();

            Customers customers = new Customers(userName,password);
            CustomerController customerController = new CustomerController();
            return customerController.registerCustomer(customers);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
