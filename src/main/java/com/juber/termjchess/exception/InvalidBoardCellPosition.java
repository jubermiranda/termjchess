package com.juber.termjchess.exception;

public class InvalidBoardCellPosition extends Exception {
    public InvalidBoardCellPosition(String message){
        super(message);
    }

    public InvalidBoardCellPosition(){
        super("valid chess board cells: (0,0) to (7,7)");
    }
}
