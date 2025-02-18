package Model;

public class Customers {

    public int customerId;
    public String customerName;
    public String customerPassword;

    public Customers(int customerId, String customerName, String customerPassword) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public Customers(String customerName, String customerPassword) {
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public void DisplayUsers(){
        System.out.println(getCustomerId() + " " + getCustomerName() + " " + getCustomerPassword());
    }
}
