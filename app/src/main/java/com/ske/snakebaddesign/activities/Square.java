package com.ske.snakebaddesign.activities;

/**
 * Created by User on 17/3/2559.
 */
public class Square implements SquareAction{
    private int boardLocation;

    public Square(int boardLocation) {
        this.boardLocation = boardLocation;
    }

    public int getBoardLocation() {
        return boardLocation;
    }

    public void setBoardLocation(int boardLocation) {
        this.boardLocation = boardLocation;
    }

    @Override
    public int action() {
        return 0;
    }
}
