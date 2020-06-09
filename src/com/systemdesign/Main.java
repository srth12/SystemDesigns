package com.systemdesign;

public class Main {

    public static void main(String[] args) throws Exception {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.addMoney(Coin.TEN);
        vendingMachine.addMoney(Coin.FIVE);
        vendingMachine.addMoney(Coin.FIVE);
        vendingMachine.addProduct(Product.COLA, 10);
        vendingMachine.addProduct(Product.CHOCOLATE, 5);

        vendingMachine.printBalance();
        boolean chocoReturn = vendingMachine.selectProduct(Product.CHOCOLATE);
        vendingMachine.printBalance();
        System.out.println(chocoReturn);

        boolean colaReturn = vendingMachine.selectProduct(Product.COLA);
        vendingMachine.printBalance();
        System.out.println(colaReturn);

    }
}
