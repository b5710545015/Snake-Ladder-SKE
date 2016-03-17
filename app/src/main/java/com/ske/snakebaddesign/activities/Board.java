package com.ske.snakebaddesign.activities;

/**
 * Created by User on 17/3/2559.
 */
public class Board {

    private static Board board;
    private int size;
    private Square[] squares;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private Board(int size){
        squares = new Square[size*size];
        for(int i = 0 ; i<size*size-1;i++){
            squares[i] = new Square(i);
        }

        squares[size*size-1] = new EndSquare(size*size-1);

    }

    public static Board getInstance(){
        if(board == null)
            board = new Board(6);
        return board;
    }

    public void newBoard(){
        board = new Board(6);
    }

    public Square getSquare(int loc){
        return squares[loc];
    }

}
