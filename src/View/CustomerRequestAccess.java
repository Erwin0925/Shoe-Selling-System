package View;

import Controller.CustomerController;
import Controller.StallController;
import Model.Customers;
import Model.Products;
import Model.Stalls;
import Utility.GlobalScanner;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerRequestAccess {
    private final Scanner scanner;

    public CustomerRequestAccess() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String requestAccess() throws SQLException {
        dispalyStalls();
        System.out.print("Enter the Stall ID that you wanted to make access request : ");
        int stallId = scanner.nextInt();
        CustomerController customerController = new CustomerController();
        return customerController.registerStallAccess(stallId);
    }

    public void dispalyStalls() throws SQLException {
        StallController stallController = new StallController();
        List<Stalls> stalls = stallController.fetchStalls();

        int totalStalls = stalls.size();
        System.out.println("There are a total of " + totalStalls + " stall register under our platform : ");

        int i = 1;
        for (Stalls stall : stalls){
            System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘ " + i++ + " ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
            System.out.println("Stall ID  : " + stall.getStallId());
            System.out.println("Stall Name  : " + stall.getStallName());
            System.out.println("꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷");
        }
        System.out.println();
    }


}
