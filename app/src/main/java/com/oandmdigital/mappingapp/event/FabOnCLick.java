package com.oandmdigital.mappingapp.event;


public class FabOnCLick {

    private boolean onClick;

    public FabOnCLick(boolean onClick) {
        this.onClick = onClick;
    }


    public boolean isOnClick() {
        return onClick;
    }
}
