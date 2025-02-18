package View;

import Controller.StallController;
import Model.Stalls;
import Route.StallMenu;
import Utility.GlobalScanner;

import java.util.Scanner;

public class StallLogin {
    private final Scanner scanner;

    public StallLogin() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public void loginStall(){
        try{
            boolean flag = true;
            while (flag) {
                System.out.println("-----Stall User Login-----");
                System.out.print("UserName : ");
                String stallName = scanner.next();
                System.out.print("Password : ");
                String password = scanner.next();
                System.out.print("Press 1 to back to Main Menu : ");
                int option = scanner.nextInt();

                Stalls stall = new Stalls(stallName, password);
                StallController stallController = new StallController();
                boolean result = stallController.stallLogin(stall);
                if (result){
                    flag = false;
                }else{
                    System.out.println("Login Failed, please try again");
                }
            }
            StallMenu stallMenu = new StallMenu();
            stallMenu.getStallOption();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
