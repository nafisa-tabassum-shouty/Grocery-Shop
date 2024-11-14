package com.example.groceryshop.Model;

import android.widget.Button;

public class CartModel {
    int pic;
    String text,productprice,quantity;
    Button add,delete;

    public CartModel(int pic, String text, String productprice, String quantity/*, Button add,Button delete*/){
        this.pic=pic;
        this.text=text;
        this.productprice=productprice;
        this.quantity=quantity;
        /*this.add=add;
        this.delete=delete;*/


    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public int getPic() {
        return pic;
    }

    public String getText() {
        return text;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setText(String text) {
        this.text = text;
    }
}
