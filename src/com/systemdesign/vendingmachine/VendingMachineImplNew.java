package com.systemdesign.vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class VendingMachineImplNew implements VendingMachine {

    Inventory<Coin> coinInventory = new Inventory<>();
    Inventory<Product> productInventory = new Inventory<>();

    long currentAmountAvailable = 0;
    Product currentProductSelected;

    @Override
    public boolean addMoney(Coin coin) {
        currentAmountAvailable += coin.getDenomination();
        coinInventory.addItem(coin, 1);
        return false;
    }

    @Override
    public boolean selectProduct(Product product) throws Exception {
        currentProductSelected = product;



        return true;
    }

    private void releaseProduct() throws NotEnoughCoinException, InsufficientFundException {
        int priceOfProduct = currentProductSelected.getProductPrice();
        List<Coin> coinsToDeduce;
        if (priceOfProduct <= currentAmountAvailable){
            long balanceRequired = currentAmountAvailable - priceOfProduct;
            coinsToDeduce = checkEnoughStockOfBalance(balanceRequired);
        }else throw new InsufficientFundException();

        reduceCoinsFromInventory(coinsToDeduce);
    }

    private void reduceCoinsFromInventory(List<Coin> coinsToDeduce) {
        //Implement it
    }

    private List<Coin> checkEnoughStockOfBalance(long balanceRequired) throws NotEnoughCoinException {
        List<Coin> coinsToDisburse = new ArrayList<>();
        Coin[] coins = Coin.values();
        Collections.sort(Arrays.asList(coins));
        List<Coin> sorted = Arrays.asList(coins).stream().sorted((a, b) -> b.compare(a)).collect(Collectors.toList());
        while (balanceRequired >= 0){
            boolean foundMatch = false;
            for (Coin coin: sorted){
                if (coin.getDenomination()/balanceRequired >= 1){
                    long numberOfTimes = coin.getDenomination()/balanceRequired;
                    addCoinXtimesToList(coin, numberOfTimes, coinsToDisburse);
                }
            }
            if (!foundMatch)
                throw new NotEnoughCoinException();
        }

        return coinsToDisburse;
    }

    private void addCoinXtimesToList(Coin coin, long numberOfTimes, List<Coin> listToAdd) {
        LongStream.range(0, numberOfTimes).forEach(x -> listToAdd.add(coin));
    }

    @Override
    public void addProduct(Product product, int quantity) {
        productInventory.addItem(product, quantity);
    }

    @Override
    public void resetMachine() {
        currentAmountAvailable = 0;
        currentProductSelected = null;
        coinInventory.resetStock();
        productInventory.resetStock();
    }

    @Override
    public void printBalance() {
        System.out.println("Available balance is " + currentAmountAvailable);
    }
}
