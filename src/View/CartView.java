package View;

import Model.Cart;
import Utility.CartSession;

import java.util.List;

public class CartView {

    public void DisplayCart(){
        List<Cart> cart = CartSession.getInstance().getCartList();
        if(cart.isEmpty()){
            System.out.println("Cart is empty");
            System.out.println();
        }
        else{
            CartSession.getInstance().displayCart();
            System.out.println();
        }
    }
}
