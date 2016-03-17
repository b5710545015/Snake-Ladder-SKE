package com.ske.snakebaddesign.activities;

import android.util.Log;

/**
 * Created by User on 17/3/2559.
 */
public class Player {
    private int pos;



    private Square currentLocation;

    public Square getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Square currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Player(){
        pos = 0;
    }

    public void setStart(){
        this.setPos(0);
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }


}
