package View;

import Controller.StallController;
import Model.Stall_User_Restriction;
import Utility.GlobalScanner;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StallApproveRequest {
    private final Scanner scanner;

    public StallApproveRequest() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public void showRequest() throws SQLException {
        StallController stallController = new StallController();
        List<Stall_User_Restriction> stallUserRestrictions = stallController.fetchCustomerRequest();

        int totalRequest = stallUserRestrictions.size();
        if (totalRequest >0){

            System.out.println("There are a total of " + totalRequest + " Customers : ");

            int i = 1;
            for (Stall_User_Restriction stallUserRestriction : stallUserRestrictions) {
                System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘ " + i++ + " ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
                System.out.println("Request ID  : " + stallUserRestriction.getStallUserRestrictId());
                System.out.println("Customer ID  : " + stallUserRestriction.getCustomerId());
                System.out.println("Stall ID  : " + stallUserRestriction.getStallId());
                System.out.println("Status  : " + stallUserRestriction.getStatus());
                System.out.println("꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷");
            }
        }else{
            System.out.println("There is still no product selling in our platform  ｡°(°.◜ᯅ◝°)°｡ ");
        }
    }

    public String approveRequest() throws SQLException {
        System.out.println("-----Request Approval Page-----");
        showRequest();
        System.out.println();
        System.out.print("Please Enter the RequestId that going to get modify : ");
        int requestId = scanner.nextInt();
        System.out.print("Please enter your updated status (Approved/Declined) : ");
        String status = scanner.next();
        if (status.equalsIgnoreCase("approved")){
            status = "Approved";
        }else if (status.equalsIgnoreCase("declined")){
            status = "Declined";
        }

        Stall_User_Restriction stallUserRestriction = new Stall_User_Restriction(requestId,status);
        StallController stallController = new StallController();
        return stallController.customerRequestApproval(stallUserRestriction);

    }
}
