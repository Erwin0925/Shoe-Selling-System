package Controller;

import Model.Cart;
import Utility.CartSession;

import java.util.List;

public class CartController {

    public void addToCart(int productId, int productQuantity) {

        List<Cart> cartList= CartSession.getInstance().getCartList();
        int index = checkCartProduct(cartList,productId);
        if (index >= 0){
            int totalQuantity = productQuantity + cartList.get(index).getProductQuantity();
            Cart cart = new Cart(productId, totalQuantity);
            cartList.set(index,cart);
        }else{
            Cart cart = new Cart(productId, productQuantity);
            cartList.add(cart);
        }
    }

    public int checkCartProduct(List<Cart> cartList, int productId) {
        int left = 0, right = cartList.size() - 1;
        while (left <= right) {

            if (cartList.get(left).productId == productId) {
                return left;
            }

            if (cartList.get(right).productId == productId) {
                return right;
            }

            left++;
            right--;
        }
        return -1;
    }

    public String removeCartProduct(int productId, int quantity){
        List<Cart> cartList = CartSession.getInstance().getCartList();

        for(int i =0; i<cartList.size();i++){
            if (cartList.get(i).getProductId() == productId){
                int oldQuantity = cartList.get(i).getProductQuantity();
                int newQuantity = oldQuantity - quantity;
                Cart cart = new Cart(productId,newQuantity);
                cartList.set(i,cart);
                return "Remove successfully for product with id " + productId;
            }
        }
        return "Fail to remove product with id " + productId;
    }

    public String removeCartProduct(int productId){
        int quantity = CartSession.getInstance().getProductQuantity(productId);
        return removeCartProduct(productId, quantity);
    }
}
