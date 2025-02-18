package Utility;

import Model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartSession {

    private static CartSession instance;
    private List<Cart> cartList;

    private CartSession(){
        System.out.println("CartSession Singleton is created");
        this.cartList = new ArrayList<>();
    }

    public static CartSession getInstance(){
        if (instance == null){
            instance = new CartSession();
        }
        return instance;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void clearCart(){
        cartList = null;
    }

    public void displayCart(){
        int i = 1;
        for (Cart item : cartList){
            System.out.println("No " + i + ". Product id : " + item.getProductId() + " quantity added to Cart : " + item.getProductQuantity());
            i++;
        }
    }

    public int getProductQuantity(int productId){
        List<Cart> cartList = CartSession.getInstance().getCartList();
        for (Cart product : cartList){
            if (product.getProductId() == productId){
                return product.getProductQuantity();
            }
        }
        return -1;
    }
}
