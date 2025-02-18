package View;

import Controller.StallController;
import Model.Stalls;
import Utility.GlobalScanner;

import java.util.Scanner;

public class StallRegistration {
    private final Scanner scanner;

    public StallRegistration() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String registerStall(){
        try {
            System.out.println("-----Stall Registration Page-----");
            System.out.print("Please Enter your UserName : ");
            String stallName = scanner.next();
            System.out.print("Please Enter your Password : ");
            String password = scanner.next();

            Stalls stall = new Stalls(stallName, password);
            StallController stallController = new StallController();
            return stallController.stallRegistration(stall);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
