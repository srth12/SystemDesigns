package com.systemdesign;

public enum Product {
    COLA(50),
    CHOCOLATE(20);

    Product(int price) {
        this.price = price;
    }

    private int price;

    public int getProductPrice(){
        return price;
    }
}
