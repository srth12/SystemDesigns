package com.systemdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    Map<Product, Integer> productInventory = new HashMap<>();
    Map<Coin, Integer> coinInventory = new HashMap<>();

    public void addProduct(Product product, int quantity){
        Integer productQuantity = productInventory.getOrDefault(product, 0);
        productInventory.put(product, productQuantity + quantity);
    }

    public boolean addMoney(Coin coin){
        Integer coinCount = coinInventory.getOrDefault(coin, 0);
        coinInventory.put(coin, coinCount + 1);
        return true;
    }

    public boolean selectProduct(Product product, Integer amountAvailable) throws Exception {
        if (product.getProductPrice() > amountAvailable){
            return false;
        }
        if (!productInventory.containsKey(product)){
            throw new Exception("Prodocut not available");
        }
        Integer productStock = productInventory.get(product);
        if (productStock == 0)
            throw new Exception("No Stock");

        int changeRequired = amountAvailable - product.getProductPrice();

        try {
            Map<Coin, Integer> remainingAmount = getRemainingAmount(changeRequired);
            productInventory.put(product, productStock -1);
            remainingAmount.entrySet().stream().forEach((coinStock) -> {
                coinInventory.compute(coinStock.getKey(), (key, value) -> value - coinStock.getValue());
            });
        }catch (Exception  e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Map<Coin, Integer> getRemainingAmount(int changeRequired) throws Exception {
        List<Coin> change = new ArrayList<>();
        Map<Coin, Integer> changeCollected = new HashMap<>();
        while (changeRequired > 0){
            if (Coin.TEN.getDenomination() <= changeRequired){
                changeCollected.compute(Coin.TEN, (key, value) -> value==0?1:value+1);
                changeRequired -= Coin.TEN.getDenomination();
            }else if (Coin.FIVE.getDenomination() <= changeRequired){
                changeCollected.compute(Coin.FIVE, (key, value) -> value==0?1:value+1);
                changeRequired -= Coin.FIVE.getDenomination();
            }else throw new Exception("Coin Not Available");

        }
        return changeCollected;
    }
}
