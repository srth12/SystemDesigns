package com.systemdesign;

public class VendingMachineImpl implements VendingMachine {

    private InventoryOld inventoryOld = new InventoryOld();

    private int amountAvailable = 0;

    @Override
    public boolean addMoney(Coin coin) {
        amountAvailable += coin.getDenomination();
        return inventoryOld.addMoney(coin);
    }

    @Override
    public boolean selectProduct(Product product) throws Exception {
        boolean status = inventoryOld.selectProduct(product, amountAvailable);
        if (status)
            amountAvailable -= product.getProductPrice();
        return status;
    }

    public void printBalance(){
        System.out.println(amountAvailable);
    }

    public void addProduct(Product product, int quantity){
        inventoryOld.addProduct(product, quantity);
    }

    @Override
    public void resetMachine() {
        amountAvailable = 0;
    }
}
