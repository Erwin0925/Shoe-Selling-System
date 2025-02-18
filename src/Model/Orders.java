package Model;

public class Orders {

    public int orderId;
    public int order_product_Id;
    public String orderDate;
    public int order_stall_Id;

    public Orders(int orderId, int order_product_Id, String orderDate, int order_stall_Id) {
        this.orderId = orderId;
        this.order_product_Id = order_product_Id;
        this.orderDate = orderDate;
        this.order_stall_Id = order_stall_Id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrder_product_Id() {
        return order_product_Id;
    }

    public void setOrder_product_Id(int order_product_Id) {
        this.order_product_Id = order_product_Id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrder_stall_Id() {
        return order_stall_Id;
    }

    public void setOrder_stall_Id(int order_stall_Id) {
        this.order_stall_Id = order_stall_Id;
    }
}
