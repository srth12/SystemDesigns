package com.systemdesign.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {

    Map<T, Integer> inventoryItem = new HashMap<>();

    public void addItem(T item, Integer stockingQuantity){
        inventoryItem.compute(item, (i, c) -> c==0?stockingQuantity: stockingQuantity + c);
    }

    public Integer getStockCount(T item){
        return inventoryItem.getOrDefault(item, 0);
    }

    public void withdrawItem(T item, Integer quantityToWithdraw) throws NotEnoughStockException {
        Integer availableStock = inventoryItem.getOrDefault(item, 0);

        if (availableStock < quantityToWithdraw){
            throw new NotEnoughStockException("Current available stock is only " + availableStock);
        }

        inventoryItem.put(item, quantityToWithdraw - availableStock);
    }

    public void resetStock(){
        inventoryItem.clear();
    }
}
