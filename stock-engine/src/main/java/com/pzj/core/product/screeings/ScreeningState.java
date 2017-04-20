package com.pzj.core.product.screeings;

/**
 * Created by Administrator on 2017-3-20.
 */
public enum  ScreeningState {
    ENABLE(1), DELETED(0);

    private int value;

    ScreeningState(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
