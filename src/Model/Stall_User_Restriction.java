package Model;

public class Stall_User_Restriction {

    public int StallUserRestrictId;
    public int CustomerId;
    public int StallId;
    public String Status;

    public Stall_User_Restriction(int stallUserRestrictId, int customerId, int stallId, String status) {
        StallUserRestrictId = stallUserRestrictId;
        CustomerId = customerId;
        StallId = stallId;
        Status = status;
    }

    public Stall_User_Restriction(int stallUserRestrictId, String status) {
        StallUserRestrictId = stallUserRestrictId;
        Status = status;
    }

    public int getStallUserRestrictId() {
        return StallUserRestrictId;
    }


    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getStallId() {
        return StallId;
    }

    public void setStallId(int stallId) {
        StallId = stallId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
