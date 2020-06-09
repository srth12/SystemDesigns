package com.systemdesign;

public enum Coin {

    TEN(10), FIVE(5);

    Coin(int denomination) {
        this.denomination = denomination;
    }

    private int denomination;

    public int getDenomination(){
        return denomination;
    }
}

