package Model;

public class Stalls {

    public int stallId;
    public String stallName;
    public String stallPassword;

    public Stalls(int stallId, String stallName, String stallPassword) {
        this.stallId = stallId;
        this.stallName = stallName;
        this.stallPassword = stallPassword;
    }

    public Stalls(String stallName, String stallPassword) {
        this.stallName = stallName;
        this.stallPassword = stallPassword;
    }

    public Stalls(int stallId, String stallName) {
        this.stallId = stallId;
        this.stallName = stallName;
    }

    public int getStallId() {
        return stallId;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallPassword() {
        return stallPassword;
    }

    public void setStallPassword(String stallPassword) {
        this.stallPassword = stallPassword;
    }
}
