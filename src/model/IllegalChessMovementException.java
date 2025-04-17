package model;

public class IllegalChessMovementException extends Exception {
    public IllegalChessMovementException(String message){
        super(message);
    }

    public IllegalChessMovementException(){
        super("movement not allowed in chess");
    }
}
