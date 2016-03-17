package com.ske.snakebaddesign.activities;

/**
 * Created by User on 17/3/2559.
 */
public class EndSquare extends Square implements SquareAction {
    public EndSquare(int loc){
        super(loc);
    }

    public int action(){
        return 1;
    }
}
