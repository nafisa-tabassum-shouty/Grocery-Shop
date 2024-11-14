package com.example.groceryshop;

public class Users {
    String email,pass, userName;
   // String item_name;
    //int itemQuantity;

    public Users() {
    }

    public Users(String email, String pass,String userName) {
        this.email = email;
        this.pass = pass;
        this.userName = userName;
    }

   /* public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
