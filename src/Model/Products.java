package Model;

public class Products {

    public int productId;
    public String productName;
    public int productQuantity;
    public boolean productAvailability;
    public double productPrice;
    public int product_stall_Id;

    public Products(int productId, String productName, int productQuantity,
                    boolean productAvailability, double productPrice, int product_stall_Id) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productAvailability = productAvailability;
        this.productPrice = productPrice;
        this.product_stall_Id = product_stall_Id;
    }

    public Products(String productName, int productQuantity, boolean productAvailability, double productPrice, int product_stall_Id) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productAvailability = productAvailability;
        this.productPrice = productPrice;
        this.product_stall_Id = product_stall_Id;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(boolean productAvailability) {
        this.productAvailability = productAvailability;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProduct_stall_Id() {
        return product_stall_Id;
    }

    public void setProduct_stall_Id(int product_stall_Id) {
        this.product_stall_Id = product_stall_Id;
    }

    public boolean isEmpty() {
        return productId == 0 && productName == null && productQuantity == 0 && !productAvailability && productPrice == 0.0 && product_stall_Id == 0;
    }

}
