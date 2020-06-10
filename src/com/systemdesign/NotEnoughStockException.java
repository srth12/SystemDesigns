package com.systemdesign;

public class NotEnoughStockException extends Throwable {
    public NotEnoughStockException(String reason) {
        System.out.println("Failed reason is " + reason);
    }
}
