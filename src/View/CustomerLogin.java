package View;

import Controller.CustomerController;
import Model.Customers;
import Route.CustomerMenu;
import Utility.GlobalScanner;

import java.util.Scanner;

public class CustomerLogin {

    private final Scanner scanner;

    public CustomerLogin() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public void loginCustomer(){
        try{
            boolean flag = true;
            while (flag){
                System.out.println("-----Customer User Login-----");
                System.out.print("UserName : ");
                String userName = scanner.next();
                System.out.print("Password : ");
                String password = scanner.next();

                Customers customer = new Customers(userName,password);

                CustomerController customerController = new CustomerController();
                boolean result = customerController.loginCustomer(customer);

                if (result){
                    flag = false;
                }else{
                    System.out.println("Failed to login, Please try again");
                }
            }
            CustomerMenu customerMenu = new CustomerMenu();
            customerMenu.userOption();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
