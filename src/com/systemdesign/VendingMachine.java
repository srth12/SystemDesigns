package com.systemdesign;

public interface VendingMachine {

    public boolean addMoney(Coin coin);
    public boolean selectProduct(Product product) throws Exception;
    public void addProduct(Product product, int quantity);
    public void resetMachine();
    public void printBalance();
}
