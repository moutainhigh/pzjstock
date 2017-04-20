package com.pzj.core.stock.context;

public class ShowResponse implements java.io.Serializable {

    private static final long serialVersionUID = -2267087637940574064L;
    /** 动态分配的座位数组*/
    private String[]          randomAssignSeats;

    public String[] getRandomAssignSeats() {
        return randomAssignSeats;
    }

    public void setRandomAssignSeats(String[] randomAssignSeats) {
        this.randomAssignSeats = randomAssignSeats;
    }

}
