package Model;

public class OrderDetails {

    public int orderDetailId;
    public int orderDetail_product_Id;
    public int orderDetail_stall_Id;

    public OrderDetails(int orderDetailId, int orderDetail_product_Id, int orderDetail_stall_Id) {
        this.orderDetailId = orderDetailId;
        this.orderDetail_product_Id = orderDetail_product_Id;
        this.orderDetail_stall_Id = orderDetail_stall_Id;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getOrderDetail_product_Id() {
        return orderDetail_product_Id;
    }

    public void setOrderDetail_product_Id(int orderDetail_product_Id) {
        this.orderDetail_product_Id = orderDetail_product_Id;
    }

    public int getOrderDetail_stall_Id() {
        return orderDetail_stall_Id;
    }

    public void setOrderDetail_stall_Id(int orderDetail_stall_Id) {
        this.orderDetail_stall_Id = orderDetail_stall_Id;
    }
}
