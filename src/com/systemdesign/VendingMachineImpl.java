package com.systemdesign;

public class VendingMachineImpl implements VendingMachine {

    private Inventory inventory = new Inventory();

    private int amountAvailable = 0;

    @Override
    public boolean addMoney(Coin coin) {
        amountAvailable += coin.getDenomination();
        return inventory.addMoney(coin);
    }

    @Override
    public boolean selectProduct(Product product) throws Exception {
        boolean status = inventory.selectProduct(product, amountAvailable);
        if (status)
            amountAvailable -= product.getProductPrice();
        return status;
    }

    public void printBalance(){
        System.out.println(amountAvailable);
    }

    public void addProduct(Product product, int quantity){
        inventory.addProduct(product, quantity);
    }

    @Override
    public void resetMachine() {
        amountAvailable = 0;
    }
}
